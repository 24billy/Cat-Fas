package tw.com.billy.fastcat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.ejml.simple.SimpleMatrix;

public class CatFasParameterEstimate {

	public static void main(String[] args) {
		// Double stopingRule1 = new Double(0.01d);
		// Double stopingRule2 = new Double(0.02d);

		// Double[][] covArray = new Double[][] { { 80.39, 48.56, 24.876, 14.72
		// }, { 48.56, 37.564, 19.128, 10.252 },{ 24.876, 19.128, 13.829, 7.246
		// }, { 14.72, 10.252, 7.246, 4.046 } };
		Double[] muArray = new Double[] { -0.858d, 1.086d, 0.324d, -0.893 };
		Double[] tVar = new Double[] { 59.8037, 29.5658, 11.756, 3.3292 };
		// scoring matrix 不同項度有各自的scoring matrix
		List<Double[][]> scoringMatrixList = new ArrayList<Double[][]>();
		Double[][] scoringMatrix1 = new Double[][] { { 0d, 0d, 0d, 0d }, { 1d, 0d, 0d, 0d }, { 2d, 0d, 0d, 0d },
				{ 3d, 0d, 0d, 0d } };
		Double[][] scoringMatrix2 = new Double[][] { { 0d, 0d, 0d, 0d }, { 0d, 1d, 0d, 0d }, { 0d, 2d, 0d, 0d },
				{ 0d, 3d, 0d, 0d } };
		Double[][] scoringMatrix3 = new Double[][] { { 0d, 0d, 0d, 0d }, { 0d, 0d, 1d, 0d }, { 0d, 0d, 2d, 0d },
				{ 0d, 0d, 3d, 0d } };
		Double[][] scoringMatrix4 = new Double[][] { { 0d, 0d, 0d, 0d }, { 0d, 0d, 0d, 1d }, { 0d, 0d, 0d, 2d },
				{ 0d, 0d, 0d, 3d } };
		scoringMatrixList.add(scoringMatrix1);
		scoringMatrixList.add(scoringMatrix2);
		scoringMatrixList.add(scoringMatrix3);
		scoringMatrixList.add(scoringMatrix4);

		// design matrix
		Double[][] designMatrix = new Double[][] { { 0d, 0d, 0d, 0d }, { -1d, -1d, 0d, 0d }, { -2d, -1d, -1d, 0d },
				{ -3d, -1d, -1d, -1d } };

		// variance/covariance matrix反矩陣
		Double[][] covInverseArray = new Double[][] { { 0.103390219, -0.119570491, 0.288036755, -0.58902167 },
				{ -0.119570491, 0.230065601, -0.415799009, 0.59671891 },
				{ 0.288036755, -0.415799009, 2.050500604, -3.666598361 },
				{ -0.58902167, 0.59671891, -3.666598361, 7.444638765 } };

		// 初始能力
		Double[] initAbility = new Double[] { 0d, 0d, 0d, 0d };
		Double[][] priorInfo = new Double[][] { { 0d, 0d, 0d, 0d }, { 0d, 0d, 0d, 0d }, { 0d, 0d, 0d, 0d },
				{ 0d, 0d, 0d, 0d } };
		List<Item> itemList = initItem();

		// 作答反應序
		List<Double> responseList = new ArrayList<Double>();
		responseList.add(2d);
		responseList.add(0d);
		responseList.add(3d);
		responseList.add(2d);
		responseList.add(0d);
		responseList.add(2d);
		responseList.add(3d);
		responseList.add(0d);
		responseList.add(0d);
		responseList.add(0d);

		// 第一題
		// 1-1.選題
		List<Item> choosedItemList = new ArrayList<Item>();
		Item choosedItem = chooseItem(initAbility, priorInfo, itemList, scoringMatrixList, designMatrix,
				covInverseArray, muArray);

		// 1-2.作答
		choosedItem.setResponse(responseList.get(0));
		System.out.println("作答結果:" + choosedItem.getResponse());

		// 加入已選題、刪除可選題
		choosedItemList.add(choosedItem);
		itemList.remove(choosedItem);

		// 1-3.能力估計
		AbilityVO ability = new AbilityVO();
		ability.setAbility(new Double[] { 0d, 0d, 0d, 0d });

		ability = loopAbilityEstimate(choosedItemList, ability, scoringMatrixList, designMatrix, covInverseArray,
				muArray);

		System.out.println("第一次能力估計：");
		System.out.println(ability);
		System.out.println();

		// 第二題
		// 2-1.選題
		choosedItem = chooseItem(ability.getOriginalAbility(), ability.getInfomationMatrix(), itemList,
				scoringMatrixList, designMatrix, covInverseArray, muArray);

		// 2-2.作答
		choosedItem.setResponse(responseList.get(1));
		System.out.println("作答結果:" + choosedItem.getResponse());

		// 加入已選題、刪除可選題
		choosedItemList.add(choosedItem);
		itemList.remove(choosedItem);

		// 2-3.能力估計
		ability = loopAbilityEstimate(choosedItemList, ability, scoringMatrixList, designMatrix, covInverseArray,
				muArray);

		System.out.println("第二次能力估計：");
		System.out.println(ability);
		System.out.println();

		// 第三題
		// 3-1.選題
		choosedItem = chooseItem(ability.getOriginalAbility(), ability.getInfomationMatrix(), itemList,
				scoringMatrixList, designMatrix, covInverseArray, muArray);

		// 3-2.作答
		choosedItem.setResponse(responseList.get(2));
		System.out.println("作答結果:" + choosedItem.getResponse());

		// 加入已選題、刪除可選題
		choosedItemList.add(choosedItem);
		itemList.remove(choosedItem);

		// 3-3.能力估計
		ability = loopAbilityEstimate(choosedItemList, ability, scoringMatrixList, designMatrix, covInverseArray,
				muArray);

		System.out.println("第三次能力估計：");
		System.out.println(ability);
		System.out.println();

		// 4
		choosedItem = chooseItem(ability.getOriginalAbility(), ability.getInfomationMatrix(), itemList,
				scoringMatrixList, designMatrix, covInverseArray, muArray);
		choosedItem.setResponse(responseList.get(3));
		System.out.println("作答結果:" + choosedItem.getResponse());
		choosedItemList.add(choosedItem);
		itemList.remove(choosedItem);
		ability = loopAbilityEstimate(choosedItemList, ability, scoringMatrixList, designMatrix, covInverseArray,
				muArray);
		System.out.println("第4次能力估計：");
		System.out.println(ability);
		System.out.println();

		// 5
		choosedItem = chooseItem(ability.getOriginalAbility(), ability.getInfomationMatrix(), itemList,
				scoringMatrixList, designMatrix, covInverseArray, muArray);
		choosedItem.setResponse(responseList.get(4));
		System.out.println("作答結果:" + choosedItem.getResponse());
		choosedItemList.add(choosedItem);
		itemList.remove(choosedItem);
		ability = loopAbilityEstimate(choosedItemList, ability, scoringMatrixList, designMatrix, covInverseArray,
				muArray);
		System.out.println("第5次能力估計：");
		System.out.println(ability);
		System.out.println();

		// 6
		choosedItem = chooseItem(ability.getOriginalAbility(), ability.getInfomationMatrix(), itemList,
				scoringMatrixList, designMatrix, covInverseArray, muArray);
		choosedItem.setResponse(responseList.get(5));
		System.out.println("作答結果:" + choosedItem.getResponse());
		choosedItemList.add(choosedItem);
		itemList.remove(choosedItem);
		ability = loopAbilityEstimate(choosedItemList, ability, scoringMatrixList, designMatrix, covInverseArray,
				muArray);
		System.out.println("第6次能力估計：");
		System.out.println(ability);
		System.out.println();

		// 7
		choosedItem = chooseItem(ability.getOriginalAbility(), ability.getInfomationMatrix(), itemList,
				scoringMatrixList, designMatrix, covInverseArray, muArray);
		choosedItem.setResponse(responseList.get(6));
		System.out.println("作答結果:" + choosedItem.getResponse());
		choosedItemList.add(choosedItem);
		itemList.remove(choosedItem);
		ability = loopAbilityEstimate(choosedItemList, ability, scoringMatrixList, designMatrix, covInverseArray,
				muArray);
		System.out.println("第7次能力估計：");
		System.out.println(ability);
		System.out.println();

		// 8
		choosedItem = chooseItem(ability.getOriginalAbility(), ability.getInfomationMatrix(), itemList,
				scoringMatrixList, designMatrix, covInverseArray, muArray);
		choosedItem.setResponse(responseList.get(7));
		System.out.println("作答結果:" + choosedItem.getResponse());
		choosedItemList.add(choosedItem);
		itemList.remove(choosedItem);
		ability = loopAbilityEstimate(choosedItemList, ability, scoringMatrixList, designMatrix, covInverseArray,
				muArray);
		System.out.println("第8次能力估計：");
		System.out.println(ability);
		System.out.println();

		// 9
		choosedItem = chooseItem(ability.getOriginalAbility(), ability.getInfomationMatrix(), itemList,
				scoringMatrixList, designMatrix, covInverseArray, muArray);
		choosedItem.setResponse(responseList.get(8));
		System.out.println("作答結果:" + choosedItem.getResponse());
		choosedItemList.add(choosedItem);
		itemList.remove(choosedItem);
		ability = loopAbilityEstimate(choosedItemList, ability, scoringMatrixList, designMatrix, covInverseArray,
				muArray);
		System.out.println("第9次能力估計：");
		System.out.println(ability);
		System.out.println();

		// 10
		choosedItem = chooseItem(ability.getOriginalAbility(), ability.getInfomationMatrix(), itemList,
				scoringMatrixList, designMatrix, covInverseArray, muArray);
		choosedItem.setResponse(responseList.get(9));
		System.out.println("作答結果:" + choosedItem.getResponse());
		choosedItemList.add(choosedItem);
		itemList.remove(choosedItem);
		ability = loopAbilityEstimate(choosedItemList, ability, scoringMatrixList, designMatrix, covInverseArray,
				muArray);
		System.out.println("第10次能力估計：");
		System.out.println(ability);
		System.out.println();
	}

	public static AbilityVO loopAbilityEstimate(List<Item> itemList, AbilityVO initAbility,
			List<Double[][]> scoringMatrixList, Double[][] designMatrix, Double[][] covInverseArray, Double[] muArray) {
		AbilityVO ability = abilityEstimate(itemList, initAbility.getAbility(), scoringMatrixList, designMatrix,
				covInverseArray, muArray);
		ability.setIterateTimes(1);
		// System.out.println(ability);

		while (!ability.isConverged) {
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

		getReliability(ability, new Double[] { 59.8037, 29.5658, 11.756, 3.3292 });
		
		return ability;
	}

	public static void getReliability(AbilityVO ability, Double[] tVar) {
		Double[][] infomationMatrix = ability.getInfomationMatrix();

		double[][] tempMatrix = new double[4][4];

		for (int i = 0; i < infomationMatrix.length; i++) {
			for (int j = 0; j < infomationMatrix.length; j++)
				tempMatrix[i][j] = infomationMatrix[i][j];
		}

		SimpleMatrix invereTwoLevelMap = new SimpleMatrix(tempMatrix).invert();

		Double[][] inversedInfomationMatrix = new Double[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				inversedInfomationMatrix[i][j] = invereTwoLevelMap.get(i, j);
			}
		}

		// TVar 59.8037,29.5658,11.756,3.3292
		List<Double> dimentionSE = new ArrayList<Double>();
		dimentionSE.add(Math.sqrt(inversedInfomationMatrix[0][0]));
		dimentionSE.add(Math.sqrt(inversedInfomationMatrix[1][1]));
		dimentionSE.add(Math.sqrt(inversedInfomationMatrix[2][2]));
		dimentionSE.add(Math.sqrt(inversedInfomationMatrix[3][3]));

		List<Double> dimentionReliability = new ArrayList<Double>();
		dimentionReliability.add(1 - (inversedInfomationMatrix[0][0] / tVar[0]));
		dimentionReliability.add(1 - (inversedInfomationMatrix[1][1] / tVar[1]));
		dimentionReliability.add(1 - (inversedInfomationMatrix[2][2] / tVar[2]));
		dimentionReliability.add(1 - (inversedInfomationMatrix[3][3] / tVar[3]));

		ability.setSe(dimentionSE);
		ability.setReliability(dimentionReliability);
	}

	public static Item chooseItem(Double[] initAbility, Double[][] priorInfo, List<Item> itemList,
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
			/**
			Double determinant = 0d;
			determinant += informationMatrix.get(i)[0][0]
					* (informationMatrix.get(i)[1][1] * informationMatrix.get(i)[2][2] * informationMatrix.get(i)[3][3]
							+ informationMatrix.get(i)[1][2] * informationMatrix.get(i)[2][3]
									* informationMatrix.get(i)[3][1]
					+ informationMatrix.get(i)[1][3] * informationMatrix.get(i)[2][1] * informationMatrix.get(i)[3][2]
					- informationMatrix.get(i)[1][3] * informationMatrix.get(i)[2][2] * informationMatrix.get(i)[3][1]
					- informationMatrix.get(i)[1][2] * informationMatrix.get(i)[2][1] * informationMatrix.get(i)[3][3]
					- informationMatrix.get(i)[1][1] * informationMatrix.get(i)[2][3] * informationMatrix.get(i)[3][2]);

			determinant -= informationMatrix.get(i)[0][1]
					* (informationMatrix.get(i)[1][0] * informationMatrix.get(i)[2][2] * informationMatrix.get(i)[3][3]
							+ informationMatrix.get(i)[1][2] * informationMatrix.get(i)[2][3]
									* informationMatrix.get(i)[3][0]
					+ informationMatrix.get(i)[1][3] * informationMatrix.get(i)[2][0] * informationMatrix.get(i)[3][2]
					- informationMatrix.get(i)[1][3] * informationMatrix.get(i)[2][2] * informationMatrix.get(i)[3][0]
					- informationMatrix.get(i)[1][2] * informationMatrix.get(i)[2][0] * informationMatrix.get(i)[3][3]
					- informationMatrix.get(i)[1][0] * informationMatrix.get(i)[2][3] * informationMatrix.get(i)[3][2]);

			determinant += informationMatrix.get(i)[0][2]
					* (informationMatrix.get(i)[1][0] * informationMatrix.get(i)[2][1] * informationMatrix.get(i)[3][3]
							+ informationMatrix.get(i)[1][1] * informationMatrix.get(i)[2][3]
									* informationMatrix.get(i)[3][0]
					+ informationMatrix.get(i)[1][3] * informationMatrix.get(i)[2][0] * informationMatrix.get(i)[3][1]
					- informationMatrix.get(i)[1][3] * informationMatrix.get(i)[2][1] * informationMatrix.get(i)[3][0]
					- informationMatrix.get(i)[1][1] * informationMatrix.get(i)[2][0] * informationMatrix.get(i)[3][3]
					- informationMatrix.get(i)[1][0] * informationMatrix.get(i)[2][3] * informationMatrix.get(i)[3][1]);

			determinant -= informationMatrix.get(i)[0][3]
					* (informationMatrix.get(i)[1][0] * informationMatrix.get(i)[2][1] * informationMatrix.get(i)[3][2]
							+ informationMatrix.get(i)[1][1] * informationMatrix.get(i)[2][2]
									* informationMatrix.get(i)[3][0]
					+ informationMatrix.get(i)[1][2] * informationMatrix.get(i)[2][0] * informationMatrix.get(i)[3][1]
					- informationMatrix.get(i)[1][2] * informationMatrix.get(i)[2][1] * informationMatrix.get(i)[3][0]
					- informationMatrix.get(i)[1][1] * informationMatrix.get(i)[2][0] * informationMatrix.get(i)[3][2]
					- informationMatrix.get(i)[1][0] * informationMatrix.get(i)[2][2] * informationMatrix.get(i)[3][1]);


			determinantList.add(determinant);
			*/
		}

		System.out.println("Max information value : " + Collections.max(determinantList));
		Integer itemIndex = determinantList.indexOf(Collections.max(determinantList));
		System.out.println("choose Item : " + itemList.get(itemIndex).getNum());
		System.out.println("determinantList : " + determinantList);

		return itemList.get(itemIndex);
	}

	public static AbilityVO abilityEstimate(List<Item> itemList, Double[] initAbility,
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
			Item item = itemList.get(itemIndex);

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
			// System.out.println("機率分子:");
			// System.out.println(probList);

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
			// System.out.println("機率0~2:");
			// System.out.println(probSumList);

			// 期望值向量 = scoringMatrix * 機率0~2 array
			List<Double> expectList = new ArrayList<Double>();
			for (int i = 0; i < probSumList.size(); i++) {
				Double expect = 0d;
				for (int j = 0; j < probSumList.size(); j++) {
					expect += scoringMatrix[j][i] * probSumList.get(j);
				}

				expectList.add(expect);
			}

			// System.out.println("期望值向量:");
			// System.out.println(expectList);

			Double[][] expectMatrix = new Double[4][4];
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					Double expect = 0d;
					expect = expectList.get(i) * expectList.get(j);
					expectMatrix[i][j] = expect;
				}
			}

			// System.out.println("期望值矩陣:");
			// printDoubleMatrix(expectMatrix);

			List<Double> firstLevelMle = new ArrayList<Double>();
			for (int i = 0; i < 4; i++) {
				Double expect = dimensionScoring[i] - expectList.get(i);
				firstLevelMle.add(expect);
			}

			// System.out.println("一階MLE:");
			// System.out.println(firstLevelMle);

			// 加總
			List<Double> tempFirstLEvelMleSum = new ArrayList<Double>();
			for (int i = 0; i < 4; i++) {
				Double tempFirstLEvelMle = firstLevelMleSum.get(i) + firstLevelMle.get(i);
				tempFirstLEvelMleSum.add(tempFirstLEvelMle);
			}
			firstLevelMleSum = tempFirstLEvelMleSum;

			// System.out.println("一階MLE(加總):");
			// System.out.println(firstLevelMleSum);

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

			// System.out.println("二階MAP:");
			// printDoubleMatrix(twoLevelMapUnit);
			// System.out.println("訊息量矩陣:");
			// printDoubleMatrix(informaionMatrixUnit);

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

		// System.out.println("二階MAP減題目參數變異數反矩陣:");
		// printDoubleMatrix(twoLevelMapSum);
		// System.out.println("訊息量矩陣:");
		// printDoubleMatrix(informaionMatrixSum);

		SimpleMatrix invereTwoLevelMap = new SimpleMatrix(twoLevelMapSum).invert();
		
		// System.out.println("二階MAP反矩陣:");
		// System.out.println(invereTwoLevelMap);

		List<Double> firstLevelPrior = new ArrayList<Double>();

		for (int i = 0; i < 4; i++) {
			Double prior = covInverseArray[i][0] * (initAbility[0] - muArray[0])
					+ covInverseArray[i][1] * (initAbility[1] - muArray[1])
					+ covInverseArray[i][2] * (initAbility[2] - muArray[2])
					+ covInverseArray[i][3] * (initAbility[3] - muArray[3]);

			firstLevelPrior.add(prior);
		}

		// System.out.println("一階Prior");
		// System.out.println(firstLevelPrior);

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

		// updated ability
		Double[] updatedAbility = new Double[4];
		// System.out.println("更新後能力變化量");

		for (int i = 0; i < 4; i++) {
			// System.out.print(deltaAbility[i] + ", ");
			updatedAbility[i] = initAbility[i] - deltaAbility[i];
			// System.out.print(updatedAbility[i] + ", ");
		}
		// System.out.println();

		AbilityVO ability = new AbilityVO();
		ability.setAbility(updatedAbility);
		ability.setInfomationMatrix(informaionMatrixSum);
		ability.setIsConverged(isDeltaConverged);
		ability.setOriginalAbility(initAbility);

		return ability;
	}

	public static List<Item> initItem() {
		// 題目參數
		List<Item> itemList = new ArrayList<Item>();
		// 1-5
		Item item = new Item(1, 1, 3, -4.262d, -3.631d, 3.631d, 0d);
		itemList.add(item);
		item = new Item(2, 1, 3, -4.597d, -3.932d, 3.932d, 0d);
		itemList.add(item);
		item = new Item(3, 1, 3, -2.266d, -2.528d, 2.528d, 0d);
		itemList.add(item);
		item = new Item(4, 1, 3, -2.119d, -2.037d, 2.037d, 0d);
		itemList.add(item);
		item = new Item(5, 1, 3, -3.442d, -2.347d, 2.347d, 0d);
		itemList.add(item);
		// 6-10
		item = new Item(6, 1, 3, -2.321d, -1.966d, 1.966d, 0d);
		itemList.add(item);
		item = new Item(7, 1, 3, -2.367d, -1.922d, 1.922d, 0d);
		itemList.add(item);
		item = new Item(8, 1, 3, -1.922d, -1.962d, 1.962d, 0d);
		itemList.add(item);
		item = new Item(9, 1, 3, -1.799d, -1.287d, 1.287d, 0d);
		itemList.add(item);
		item = new Item(10, 1, 3, -0.389d, -0.125d, 0.125d, 0d);
		itemList.add(item);
		// 11-15
		item = new Item(11, 1, 3, 0.9d, -0.167d, 0.167d, 0d);
		itemList.add(item);
		item = new Item(12, 1, 3, 0.773d, -0.189d, 0.189d, 0d);
		itemList.add(item);
		item = new Item(13, 1, 3, 1.864d, 0.482d, -0.482d, 0d);
		itemList.add(item);
		item = new Item(14, 1, 3, 3.518d, -1.758d, 1.758d, 0d);
		itemList.add(item);
		item = new Item(15, 1, 3, 2.984d, -1.02d, 1.02d, 0d);
		itemList.add(item);
		// 16-20
		item = new Item(16, 1, 3, 1.033d, -1.303d, 1.303d, 0d);
		itemList.add(item);
		item = new Item(17, 1, 3, 0.062d, -1.634d, 1.634d, 0d);
		itemList.add(item);
		item = new Item(18, 1, 3, 1.17d, -1.456d, 1.456d, 0d);
		itemList.add(item);
		item = new Item(19, 1, 3, 0.045d, -1.594d, 1.594d, 0d);
		itemList.add(item);
		item = new Item(20, 1, 3, 1.831d, -1.669d, 1.669d, 0d);
		itemList.add(item);
		// 21-25
		item = new Item(21, 1, 3, -0.075d, -1.664d, 1.664d, 0d);
		itemList.add(item);
		item = new Item(22, 1, 3, 2.298d, 0.574d, -0.574d, 0d);
		itemList.add(item);
		item = new Item(23, 1, 3, 2.116d, -0.726d, 0.726d, 0d);
		itemList.add(item);
		item = new Item(24, 1, 3, 2.826d, -0.923d, 0.923d, 0d);
		itemList.add(item);
		item = new Item(25, 1, 3, 1.93d, -1.326d, 1.326d, 0d);
		itemList.add(item);
		// 26-30
		item = new Item(26, 1, 3, 2.207d, -1.051d, 1.051d, 0d);
		itemList.add(item);
		item = new Item(27, 2, 3, -2.656d, -2.48d, 2.48d, 0d);
		itemList.add(item);
		item = new Item(28, 2, 3, -2.51d, -1.779d, 1.779d, 0d);
		itemList.add(item);
		item = new Item(29, 2, 3, -2.858d, -1.581d, 1.581d, 0d);
		itemList.add(item);
		item = new Item(30, 2, 3, -2.527d, -1.687d, 1.687d, 0d);
		itemList.add(item);
		// 31-35
		item = new Item(31, 2, 3, -2.722d, -1.568d, 1.568d, 0d);
		itemList.add(item);
		item = new Item(32, 2, 3, -1.065d, -1.098d, 1.098d, 0d);
		itemList.add(item);
		item = new Item(33, 2, 3, 0.4d, -0.759d, 0.759d, 0d);
		itemList.add(item);
		item = new Item(34, 2, 3, 2.033d, -0.954d, 0.954d, 0d);
		itemList.add(item);
		item = new Item(35, 2, 3, 3.455d, -0.498d, 0.498d, 0d);
		itemList.add(item);
		// 36-40
		item = new Item(36, 2, 3, 3.992d, -0.587d, 0.587d, 0d);
		itemList.add(item);
		item = new Item(37, 2, 3, 4.458d, -1.009d, 1.009d, 0d);
		itemList.add(item);
		item = new Item(38, 3, 4, -2.486d, -0.217d, -0.429d, 0.645d);
		itemList.add(item);
		item = new Item(39, 3, 4, -1.333d, -0.351d, -0.209d, 0.56d);
		itemList.add(item);
		item = new Item(40, 3, 4, 0.892d, -0.025d, -0.823d, 0.848d);
		itemList.add(item);
		// 41-45
		item = new Item(41, 3, 4, 4.712d, -1.003d, 0.138d, 0.865d);
		itemList.add(item);
		item = new Item(42, 3, 4, 5.906d, -0.006d, -0.362d, 0.368d);
		itemList.add(item);
		item = new Item(43, 3, 4, -4.198d, -0.561d, -0.998d, 1.559d);
		itemList.add(item);
		item = new Item(44, 3, 4, -2.228d, -1.483d, -0.326d, 1.809d);
		itemList.add(item);
		item = new Item(45, 3, 4, -1.039d, -2.055d, 0.046d, 2.009d);
		itemList.add(item);
		// 46-50
		item = new Item(46, 3, 4, -1.279d, -1.269d, -0.32d, 1.589d);
		itemList.add(item);
		item = new Item(47, 3, 4, -0.122d, -1.013d, 0.026d, 0.987d);
		itemList.add(item);
		item = new Item(48, 3, 4, 0.232d, -0.358d, -0.288d, 0.647d);
		itemList.add(item);
		item = new Item(49, 3, 4, 0.943d, 1.111d, -0.381d, -0.73d);
		itemList.add(item);
		item = new Item(50, 4, 2, 3.111d, 0d, 0d, 0d);
		itemList.add(item);
		// 51-55
		item = new Item(51, 4, 2, -1.524d, 0d, 0d, 0d);
		itemList.add(item);
		item = new Item(52, 4, 3, 1.065d, 0.116d, -0.116d, 0d);
		itemList.add(item);
		item = new Item(53, 4, 3, -2.576d, 1.185d, -1.185d, 0d);
		itemList.add(item);
		item = new Item(54, 4, 3, -2.214d, 1.568d, -1.568d, 0d);
		itemList.add(item);
		item = new Item(55, 4, 3, 0.668d, -0.251d, 0.251d, 0d);
		itemList.add(item);
		// 56-58
		item = new Item(56, 4, 4, -0.641d, -0.485d, -0.209d, 0.694d);
		itemList.add(item);
		item = new Item(57, 4, 4, 0.358d, 4.103d, -6.093d, 1.99d);
		itemList.add(item);
		item = new Item(58, 4, 3, 1.753d, -0.877d, 0.877d, 0d);
		itemList.add(item);

		return itemList;
	}

	public static void printDoubleMatrix(Double[][] matrix) {
		for (Double[] dd : matrix) {
			for (Double ddd : dd) {
				System.out.print(ddd + ", ");
			}
			System.out.println();
		}
	}

	public static void printDoubleMatrix(double[][] matrix) {
		for (double[] dd : matrix) {
			for (double ddd : dd) {
				System.out.print(ddd + ", ");
			}
			System.out.println();
		}
	}

}
