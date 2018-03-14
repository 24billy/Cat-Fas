package tw.com.billy.fastcat.core.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.ejml.simple.SimpleMatrix;

import tw.com.billy.fastcat.core.common.Constant;
import tw.com.billy.fastcat.core.vo.AbilityVO;
import tw.com.billy.fastcat.core.vo.ItemVO;

public class CatUtil {

	public static ItemVO itemSelection(Double[] initAbility, Double[][] priorInfo, List<ItemVO> itemList,
			List<Double[][]> scoringMatrixList, Double[][] designMatrix, Double[][] covInverseArray, Double[] muArray) {
		// 機率分子
		List<List<Double>> probMatrix = new ArrayList<List<Double>>();
		List<List<Double>> probResultMatrix = new ArrayList<List<Double>>();

		for (int itemIndex = 0; itemIndex < itemList.size(); itemIndex++) {
			Double delta = itemList.get(itemIndex).getDelta();
			Double step1 = itemList.get(itemIndex).getStep1();
			Double step2 = itemList.get(itemIndex).getStep2();
			Double step3 = itemList.get(itemIndex).getStep3();
			Integer dimension = itemList.get(itemIndex).getDimension();

			Double[] difList = new Double[] { delta, step1, step2, step3 };
			List<Double> probList = new ArrayList<Double>();

			for (int i = 0; i < 4; i++) {
				Double prob = 0d;

				for (int j = 0; j < 4; j++) {
					// 機率分子 = scoring matrix * 初始能力 + design matrix * 階難度
					prob += scoringMatrixList.get(dimension - 1)[i][j] * initAbility[j];
					prob += designMatrix[i][j] * difList[j];
				}

				probList.add(Math.exp(prob));
			}

			if (step3 == 0d && step2 == 0d) {
				probList.remove(3);
				probList.remove(2);
			} else if (step3 == 0d && step2 != 0d) {
				probList.remove(3);
			}

			probMatrix.add(probList);

			// 處理各機率分子，計算各分數機率
			Double sum = 0d;
			for (Double prob : probList) {
				sum += prob;
			}

			List<Double> probSumList = new ArrayList<Double>();
			for (Double prob : probList) {
				Double probByIndex = prob / (sum);
				probSumList.add(probByIndex);
			}

			if (step3 == 0d && step2 == 0d) {
				probSumList.add(0d);
				probSumList.add(0d);
			} else if (step3 == 0d && step2 != 0d) {
				probSumList.add(0d);
			}

			probResultMatrix.add(probSumList);
		}

		for (int i = 0; i < probMatrix.size(); i++) {
			// System.out.println(probMatrix.get(i));
		}

		for (int i = 0; i < probResultMatrix.size(); i++) {
			// System.out.println(probResultMatrix.get(i));
		}

		// 向度期望值 = scroring matrix * probResultMatrix
		List<List<Double>> dimensionExpection = new ArrayList<List<Double>>();
		for (int i = 0; i < probResultMatrix.size(); i++) {
			Integer dimension = itemList.get(i).getDimension();
			Double[][] scoringMaxtrix = scoringMatrixList.get(dimension - 1);
			List<Double> dimensionExpectionUnit = new ArrayList<Double>();

			for (int j = 0; j < probResultMatrix.get(i).size(); j++) {
				Double sumDimensionExpection = 0d;
				for (int k = 0; k < probResultMatrix.get(i).size(); k++) {
					sumDimensionExpection += probResultMatrix.get(i).get(k) * scoringMaxtrix[k][j];
				}
				dimensionExpectionUnit.add(sumDimensionExpection);
			}
			dimensionExpection.add(dimensionExpectionUnit);
		}

		List<Double[][]> expectionMatrix = new ArrayList<Double[][]>();
		for (int i = 0; i < dimensionExpection.size(); i++) {
			Double[][] matrix = new Double[4][4];
			for (int j = 0; j < dimensionExpection.get(i).size(); j++) {
				for (int k = 0; k < dimensionExpection.get(i).size(); k++) {
					matrix[j][k] = dimensionExpection.get(i).get(j) * dimensionExpection.get(i).get(k);
				}
			}
			expectionMatrix.add(matrix);
		}

		List<Double[][]> twoLevelMap = new ArrayList<Double[][]>();
		List<double[][]> informationMatrix = new ArrayList<double[][]>();

		for (int itemIndex = 0; itemIndex < itemList.size(); itemIndex++) {
			Integer dimension = itemList.get(itemIndex).getDimension();
			Double[][] scoringMatrix = scoringMatrixList.get(dimension - 1);
			Double[][] twoLevelMapUnit = new Double[4][4];
			double[][] informaionMatrixUnit = new double[4][4];

			for (int x = 0; x < 4; x++) {
				for (int y = 0; y < 4; y++) {
					Double scoringProb = 0d;
					for (int z = 0; z < 4; z++) {
						scoringProb += scoringMatrix[z][x] * scoringMatrix[z][y]
								* probResultMatrix.get(itemIndex).get(z);
					}
					scoringProb = (scoringProb - expectionMatrix.get(itemIndex)[y][x]) * -1 - covInverseArray[y][x];
					// 計算2階MAP Matrix
					twoLevelMapUnit[x][y] = scoringProb;
					// 計算訊息量 + 事前訊息量
					informaionMatrixUnit[x][y] = scoringProb - priorInfo[x][y];
				}
			}

			twoLevelMap.add(twoLevelMapUnit);
			informationMatrix.add(informaionMatrixUnit);
		}

		/**
		 * 行列式計算 11*22*33*44 12*23*34*41 13*24*31*42 14*21*32*43 - 11*24*33*42
		 * 12*21*34*43 13*22*31*44 14*23*32*41
		 */
		List<Double> determinantList = new ArrayList<Double>();

		for (int i = 0; i < informationMatrix.size(); i++) {
			Double determinant = new SimpleMatrix(informationMatrix.get(i)).determinant();
			determinantList.add(determinant);
		}

		System.out.println("Max information value : " + Collections.max(determinantList));
		Integer itemIndex = determinantList.indexOf(Collections.max(determinantList));
		System.out.println("choose Item : " + itemList.get(itemIndex).getNum());

		return itemList.get(itemIndex);
	}

	public static List<ItemVO> initItem() {
		// 題目參數
		List<ItemVO> itemList = new ArrayList<ItemVO>();
		// 1-5
		ItemVO item = new ItemVO(1, 1, 3, -4.262d, -3.631d, 3.631d, 0d);
		itemList.add(item);
		item = new ItemVO(2, 1, 3, -4.597d, -3.932d, 3.932d, 0d);
		itemList.add(item);
		item = new ItemVO(3, 1, 3, -2.266d, -2.528d, 2.528d, 0d);
		itemList.add(item);
		item = new ItemVO(4, 1, 3, -2.119d, -2.037d, 2.037d, 0d);
		itemList.add(item);
		item = new ItemVO(5, 1, 3, -3.442d, -2.347d, 2.347d, 0d);
		itemList.add(item);
		// 6-10
		item = new ItemVO(6, 1, 3, -2.321d, -1.966d, 1.966d, 0d);
		itemList.add(item);
		item = new ItemVO(7, 1, 3, -2.367d, -1.922d, 1.922d, 0d);
		itemList.add(item);
		item = new ItemVO(8, 1, 3, -1.922d, -1.962d, 1.962d, 0d);
		itemList.add(item);
		item = new ItemVO(9, 1, 3, -1.799d, -1.287d, 1.287d, 0d);
		itemList.add(item);
		item = new ItemVO(10, 1, 3, -0.389d, -0.125d, 0.125d, 0d);
		itemList.add(item);
		// 11-15
		item = new ItemVO(11, 1, 3, 0.9d, -0.167d, 0.167d, 0d);
		itemList.add(item);
		item = new ItemVO(12, 1, 3, 0.773d, -0.189d, 0.189d, 0d);
		itemList.add(item);
		item = new ItemVO(13, 1, 3, 1.864d, 0.482d, -0.482d, 0d);
		itemList.add(item);
		item = new ItemVO(14, 1, 3, 3.518d, -1.758d, 1.758d, 0d);
		itemList.add(item);
		item = new ItemVO(15, 1, 3, 2.984d, -1.02d, 1.02d, 0d);
		itemList.add(item);
		// 16-20
		item = new ItemVO(16, 1, 3, 1.033d, -1.303d, 1.303d, 0d);
		itemList.add(item);
		item = new ItemVO(17, 1, 3, 0.062d, -1.634d, 1.634d, 0d);
		itemList.add(item);
		item = new ItemVO(18, 1, 3, 1.17d, -1.456d, 1.456d, 0d);
		itemList.add(item);
		item = new ItemVO(19, 1, 3, 0.045d, -1.594d, 1.594d, 0d);
		itemList.add(item);
		item = new ItemVO(20, 1, 3, 1.831d, -1.669d, 1.669d, 0d);
		itemList.add(item);
		// 21-25
		item = new ItemVO(21, 1, 3, -0.075d, -1.664d, 1.664d, 0d);
		itemList.add(item);
		item = new ItemVO(22, 1, 3, 2.298d, 0.574d, -0.574d, 0d);
		itemList.add(item);
		item = new ItemVO(23, 1, 3, 2.116d, -0.726d, 0.726d, 0d);
		itemList.add(item);
		item = new ItemVO(24, 1, 3, 2.826d, -0.923d, 0.923d, 0d);
		itemList.add(item);
		item = new ItemVO(25, 1, 3, 1.93d, -1.326d, 1.326d, 0d);
		itemList.add(item);
		// 26-30
		item = new ItemVO(26, 1, 3, 2.207d, -1.051d, 1.051d, 0d);
		itemList.add(item);
		item = new ItemVO(27, 2, 3, -2.656d, -2.48d, 2.48d, 0d);
		itemList.add(item);
		item = new ItemVO(28, 2, 3, -2.51d, -1.779d, 1.779d, 0d);
		itemList.add(item);
		item = new ItemVO(29, 2, 3, -2.858d, -1.581d, 1.581d, 0d);
		itemList.add(item);
		item = new ItemVO(30, 2, 3, -2.527d, -1.687d, 1.687d, 0d);
		itemList.add(item);
		// 31-35
		item = new ItemVO(31, 2, 3, -2.722d, -1.568d, 1.568d, 0d);
		itemList.add(item);
		item = new ItemVO(32, 2, 3, -1.065d, -1.098d, 1.098d, 0d);
		itemList.add(item);
		item = new ItemVO(33, 2, 3, 0.4d, -0.759d, 0.759d, 0d);
		itemList.add(item);
		item = new ItemVO(34, 2, 3, 2.033d, -0.954d, 0.954d, 0d);
		itemList.add(item);
		item = new ItemVO(35, 2, 3, 3.455d, -0.498d, 0.498d, 0d);
		itemList.add(item);
		// 36-40
		item = new ItemVO(36, 2, 3, 3.992d, -0.587d, 0.587d, 0d);
		itemList.add(item);
		item = new ItemVO(37, 2, 3, 4.458d, -1.009d, 1.009d, 0d);
		itemList.add(item);
		item = new ItemVO(38, 3, 4, -2.486d, -0.217d, -0.429d, 0.645d);
		itemList.add(item);
		item = new ItemVO(39, 3, 4, -1.333d, -0.351d, -0.209d, 0.56d);
		itemList.add(item);
		item = new ItemVO(40, 3, 4, 0.892d, -0.025d, -0.823d, 0.848d);
		itemList.add(item);
		// 41-45
		item = new ItemVO(41, 3, 4, 4.712d, -1.003d, 0.138d, 0.865d);
		itemList.add(item);
		item = new ItemVO(42, 3, 4, 5.906d, -0.006d, -0.362d, 0.368d);
		itemList.add(item);
		item = new ItemVO(43, 3, 4, -4.198d, -0.561d, -0.998d, 1.559d);
		itemList.add(item);
		item = new ItemVO(44, 3, 4, -2.228d, -1.483d, -0.326d, 1.809d);
		itemList.add(item);
		item = new ItemVO(45, 3, 4, -1.039d, -2.055d, 0.046d, 2.009d);
		itemList.add(item);
		// 46-50
		item = new ItemVO(46, 3, 4, -1.279d, -1.269d, -0.32d, 1.589d);
		itemList.add(item);
		item = new ItemVO(47, 3, 4, -0.122d, -1.013d, 0.026d, 0.987d);
		itemList.add(item);
		item = new ItemVO(48, 3, 4, 0.232d, -0.358d, -0.288d, 0.647d);
		itemList.add(item);
		item = new ItemVO(49, 3, 4, 0.943d, 1.111d, -0.381d, -0.73d);
		itemList.add(item);
		item = new ItemVO(50, 4, 2, 3.111d, 0d, 0d, 0d);
		itemList.add(item);
		// 51-55
		item = new ItemVO(51, 4, 2, -1.524d, 0d, 0d, 0d);
		itemList.add(item);
		item = new ItemVO(52, 4, 3, 1.065d, 0.116d, -0.116d, 0d);
		itemList.add(item);
		item = new ItemVO(53, 4, 3, -2.576d, 1.185d, -1.185d, 0d);
		itemList.add(item);
		item = new ItemVO(54, 4, 3, -2.214d, 1.568d, -1.568d, 0d);
		itemList.add(item);
		item = new ItemVO(55, 4, 3, 0.668d, -0.251d, 0.251d, 0d);
		itemList.add(item);
		// 56-58
		item = new ItemVO(56, 4, 4, -0.641d, -0.485d, -0.209d, 0.694d);
		itemList.add(item);
		item = new ItemVO(57, 4, 4, 0.358d, 4.103d, -6.093d, 1.99d);
		itemList.add(item);
		item = new ItemVO(58, 4, 3, 1.753d, -0.877d, 0.877d, 0d);
		itemList.add(item);

		return itemList;
	}

	public static AbilityVO abilityEstimate(List<ItemVO> itemList, Double[] initAbility,
			List<Double[][]> scoringMatrixList, Double[][] designMatrix, Double[][] covInverseArray, Double[] muArray) {
		// 一階Mle加總
		List<Double> firstLevelMleSum = new ArrayList<Double>();
		firstLevelMleSum.add(0d);
		firstLevelMleSum.add(0d);
		firstLevelMleSum.add(0d);
		firstLevelMleSum.add(0d);

		// 二階Map加總
		double[][] twoLevelMapSum = new double[][] { { 0d, 0d, 0d, 0d }, { 0d, 0d, 0d, 0d }, { 0d, 0d, 0d, 0d },
				{ 0d, 0d, 0d, 0d } };
		Double[][] informaionMatrixSum = new Double[][] { { 0d, 0d, 0d, 0d }, { 0d, 0d, 0d, 0d }, { 0d, 0d, 0d, 0d },
				{ 0d, 0d, 0d, 0d } };

		for (int itemIndex = 0; itemIndex < itemList.size(); itemIndex++) {
			ItemVO item = itemList.get(itemIndex);

			Double[] dimensionScoring = new Double[] { 0d, 0d, 0d, 0d };
			Double delta = item.getDelta();
			Double step1 = item.getStep1();
			Double step2 = item.getStep2();
			Double step3 = item.getStep3();
			Double[] difficulty = new Double[] { delta, step1, step2, step3 };
			Integer dimension = item.getDimension();
			Integer category = item.getCategoryNumber();

			// 加入作答反應
			Double response = item.getResponse();
			dimensionScoring[(dimension - 1)] = response;
			Double[][] scoringMatrix = scoringMatrixList.get(dimension - 1);

			// 機率分子 = scoring * init + desing * difficulty
			List<Double> probList = new ArrayList<Double>();
			Double divSum = 0d;

			for (int i = 0; i < category; i++) {
				Double prob = Math.exp(initAbility[0] * scoringMatrix[i][0] + initAbility[1] * scoringMatrix[i][1]
						+ initAbility[2] * scoringMatrix[i][2] + initAbility[3] * scoringMatrix[i][3]
						+ designMatrix[i][0] * difficulty[0] + designMatrix[i][1] * difficulty[1]
						+ designMatrix[i][2] * difficulty[2] + designMatrix[i][3] * difficulty[3]);

				probList.add(prob);
				divSum += prob;
			}

			List<Double> probSumList = new ArrayList<Double>();
			for (int i = 0; i < probList.size(); i++) {
				Double probSum = probList.get(i) / divSum;
				probSumList.add(probSum);
			}

			// 補滿機率
			if (probSumList.size() == 2) {
				probSumList.add(0d);
				probSumList.add(0d);
			} else if (probSumList.size() == 3) {
				probSumList.add(0d);
			}

			// 期望值向量 = scoringMatrix * 機率0~2 array
			List<Double> expectList = new ArrayList<Double>();
			for (int i = 0; i < probSumList.size(); i++) {
				Double expect = 0d;
				for (int j = 0; j < probSumList.size(); j++) {
					expect += scoringMatrix[j][i] * probSumList.get(j);
				}

				expectList.add(expect);
			}

			Double[][] expectMatrix = new Double[4][4];
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					Double expect = 0d;
					expect = expectList.get(i) * expectList.get(j);
					expectMatrix[i][j] = expect;
				}
			}

			List<Double> firstLevelMle = new ArrayList<Double>();
			for (int i = 0; i < 4; i++) {
				Double expect = dimensionScoring[i] - expectList.get(i);
				firstLevelMle.add(expect);
			}

			// 加總
			List<Double> tempFirstLEvelMleSum = new ArrayList<Double>();
			for (int i = 0; i < 4; i++) {
				Double tempFirstLEvelMle = firstLevelMleSum.get(i) + firstLevelMle.get(i);
				tempFirstLEvelMleSum.add(tempFirstLEvelMle);
			}
			firstLevelMleSum = tempFirstLEvelMleSum;

			// 二階Map
			double[][] twoLevelMapUnit = new double[4][4];
			Double[][] informaionMatrixUnit = new Double[4][4];

			// 二階Map = scoringMatrix * scoringMatrix * prbSum - expectMatrix -
			// covInverseMatrix
			for (int x = 0; x < 4; x++) {
				for (int y = 0; y < 4; y++) {
					Double scoringProb = 0d;

					for (int z = 0; z < 4; z++) {
						scoringProb += scoringMatrix[z][x] * scoringMatrix[z][y] * probSumList.get(z);
					}
					scoringProb = (scoringProb - expectMatrix[y][x]) * -1;
					// 計算2階MAP Matrix
					twoLevelMapUnit[x][y] = scoringProb;
					// 計算訊息量 + 事前訊息量
					informaionMatrixUnit[x][y] = scoringProb * -1d;
				}
			}

			// 加總
			double[][] tempTwoLevelMapUnit = new double[4][4];

			for (int x = 0; x < 4; x++) {
				for (int y = 0; y < 4; y++) {
					double twoLevelMap = twoLevelMapSum[x][y] + twoLevelMapUnit[x][y];

					tempTwoLevelMapUnit[x][y] = twoLevelMap;
				}
			}

			twoLevelMapSum = tempTwoLevelMapUnit;

			if (itemIndex == (itemList.size() - 1)) {
				for (int x = 0; x < 4; x++) {
					for (int y = 0; y < 4; y++) {
						twoLevelMapSum[x][y] = twoLevelMapSum[x][y] - covInverseArray[y][x];
						informaionMatrixSum[x][y] = twoLevelMapSum[x][y] * -1d;
					}
				}
			}
		}

		SimpleMatrix invereTwoLevelMap = new SimpleMatrix(twoLevelMapSum).invert();
		List<Double> firstLevelPrior = new ArrayList<Double>();

		for (int i = 0; i < 4; i++) {
			Double prior = covInverseArray[i][0] * (initAbility[0] - muArray[0])
					+ covInverseArray[i][1] * (initAbility[1] - muArray[1])
					+ covInverseArray[i][2] * (initAbility[2] - muArray[2])
					+ covInverseArray[i][3] * (initAbility[3] - muArray[3]);

			firstLevelPrior.add(prior);
		}

		Double[] deltaAbility = new Double[4];
		Boolean isDeltaConverged = true;

		for (int i = 0; i < 4; i++) {
			Double deltaUnit = 0d;
			for (int j = 0; j < 4; j++) {
				deltaUnit += invereTwoLevelMap.get(i, j) * (firstLevelMleSum.get(j) - firstLevelPrior.get(j));
			}

			deltaAbility[i] = deltaUnit;

			// 決定是否收斂
			if (Math.abs(deltaUnit) > 0.001) {
				isDeltaConverged = false;
			}
		}

		// updated ability 更新後能力變化量
		Double[] updatedAbility = new Double[4];

		for (int i = 0; i < 4; i++) {
			updatedAbility[i] = initAbility[i] - deltaAbility[i];
		}

		AbilityVO ability = new AbilityVO();
		ability.setAbility(updatedAbility);
		ability.setInfomationMatrix(informaionMatrixSum);
		ability.setIsConverged(isDeltaConverged);
		ability.setOriginalAbility(initAbility);

		return ability;
	}

	public static AbilityVO loopAbilityEstimate(List<ItemVO> itemList, AbilityVO initAbility,
			List<Double[][]> scoringMatrixList, Double[][] designMatrix, Double[][] covInverseArray, Double[] muArray) {
		AbilityVO ability = abilityEstimate(itemList, initAbility.getAbility(), scoringMatrixList, designMatrix,
				covInverseArray, muArray);
		ability.setIterateTimes(1);

		while (!ability.getIsConverged()) {
			Integer count = ability.getIterateTimes();

			if (count < 30) {
				ability = abilityEstimate(itemList, ability.getAbility(), scoringMatrixList, designMatrix,
						covInverseArray, muArray);
				ability.setIterateTimes(count + 1);
			} else {
				System.out.println("超過30次迭代");
				break;
			}
		}

		getStatistic(ability);

		return ability;
	}

	public static void getStatistic(AbilityVO ability) {
		Double[][] infomationMatrix = ability.getInfomationMatrix();

		double[][] tempMatrix = new double[4][4];

		for (int i = 0; i < infomationMatrix.length; i++) {
			for (int j = 0; j < infomationMatrix.length; j++) {
				tempMatrix[i][j] = infomationMatrix[i][j];
			}
		}

		SimpleMatrix invereTwoLevelMap = new SimpleMatrix(tempMatrix).invert();

		Double[][] inversedInfomationMatrix = new Double[4][4];

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				inversedInfomationMatrix[i][j] = invereTwoLevelMap.get(i, j);
			}
		}

		List<Double> dimentionSE = new ArrayList<Double>();
		dimentionSE.add(Math.sqrt(inversedInfomationMatrix[0][0]));
		dimentionSE.add(Math.sqrt(inversedInfomationMatrix[1][1]));
		dimentionSE.add(Math.sqrt(inversedInfomationMatrix[2][2]));
		dimentionSE.add(Math.sqrt(inversedInfomationMatrix[3][3]));

		List<Double> dimentionReliability = new ArrayList<Double>();
		dimentionReliability.add(1 - (inversedInfomationMatrix[0][0] / Constant.tVar[0]));
		dimentionReliability.add(1 - (inversedInfomationMatrix[1][1] / Constant.tVar[1]));
		dimentionReliability.add(1 - (inversedInfomationMatrix[2][2] / Constant.tVar[2]));
		dimentionReliability.add(1 - (inversedInfomationMatrix[3][3] / Constant.tVar[3]));

		ability.setSe(dimentionSE);
		ability.setReliability(dimentionReliability);

		// TScore
		List<Double> tScore = new ArrayList<Double>();
		// 95%信賴區間上界
		// =(((ability+1.96*se)-PriorAbilityAverate)/SQRT(PriortVar))*10+50
		// 95%信賴區間下界
		// =(((ability-1.96*E2)-PriorAbilityAverate)/SQRT(PriortVar))*10+50
		List<Double> criUpper = new ArrayList<Double>();
		List<Double> criLower = new ArrayList<Double>();

		for (int i = 0; i < 4; i++) {
			double t = (ability.getOriginalAbility()[i] - Constant.abilityAverage[i]) / Math.sqrt(Constant.tVar[i]) * 10
					+ 50;
			tScore.add(t);

			double upper = ((ability.getOriginalAbility()[i] + (1.96 * ability.getSe().get(i))
					- Constant.abilityAverage[i]) / Math.sqrt(Constant.tVar[i])) * 10 + 50;
			criUpper.add(upper);

			double lower = ((ability.getOriginalAbility()[i] - (1.96 * ability.getSe().get(i))
					- Constant.abilityAverage[i]) / Math.sqrt(Constant.tVar[i])) * 10 + 50;
			criLower.add(lower);
		}

		ability.settScore(tScore);
		ability.setCriUpper(criUpper);
		ability.setCriLower(criLower);
	}

	public static void getPercentileLevel(AbilityVO ability, List<Map<String, Object>> percentileList) {
		Double[] abilityArray = ability.getOriginalAbility();

		int percentLevel1 = 0;
		int percentLevel2 = 0;
		int percentLevel3 = 0;
		int percentLevel4 = 0;

		for (int i = 0; i < percentileList.size(); i++) {
			Double dimension1 = (Double) percentileList.get(i).get("dimension1");
			Double dimension2 = (Double) percentileList.get(i).get("dimension2");
			Double dimension3 = (Double) percentileList.get(i).get("dimension3");
			Double dimension4 = (Double) percentileList.get(i).get("dimension4");

			if (abilityArray[0] > dimension1) {
				percentLevel1++;
			}
			if (abilityArray[1] > dimension2) {
				percentLevel2++;
			}
			if (abilityArray[2] > dimension3) {
				percentLevel3++;
			}
			if (abilityArray[3] > dimension4) {
				percentLevel4++;
			}
		}

		percentLevel1 = percentileList.size() - percentLevel1 + 1;
		percentLevel2 = percentileList.size() - percentLevel2 + 1;
		percentLevel3 = percentileList.size() - percentLevel3 + 1;
		percentLevel4 = percentileList.size() - percentLevel4 + 1;

		int rank1 = (percentileList.size() - percentLevel1) * 100 / percentileList.size();
		int rank2 = (percentileList.size() - percentLevel2) * 100 / percentileList.size();
		int rank3 = (percentileList.size() - percentLevel3) * 100 / percentileList.size();
		int rank4 = (percentileList.size() - percentLevel4) * 100 / percentileList.size();

		List<Integer> result = new ArrayList<Integer>();
		result.add(rank1);
		result.add(rank2);
		result.add(rank3);
		result.add(rank4);

		ability.setPercentileLevel(result);
	}

	public static List<Integer> getChooseItem(List<ItemVO> itemList) {
		List<Integer> result = new ArrayList<Integer>();

		for (int i = 0; i < itemList.size(); i++) {
			result.add(itemList.get(i).getNum().intValue());
		}

		return result;
	}

	public static List<Integer> getAnswer(List<ItemVO> itemList) {
		List<Integer> result = new ArrayList<Integer>();

		for (int i = 0; i < itemList.size(); i++) {
			result.add(itemList.get(i).getResponse().intValue());
		}

		return result;
	}

}
