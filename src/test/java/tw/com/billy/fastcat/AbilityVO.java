package tw.com.billy.fastcat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AbilityVO {

	Double[] originalAbility;
	Double[] ability;
	Double[][] infomationMatrix;
	Boolean isConverged;
	Integer iterateTimes;
	List<Double> se;
	List<Double> reliability;

	public Double[] getAbility() {
		return ability;
	}

	public void setAbility(Double[] ability) {
		this.ability = ability;
	}

	public Double[][] getInfomationMatrix() {
		return infomationMatrix;
	}

	public void setInfomationMatrix(Double[][] infomationMatrix) {
		this.infomationMatrix = infomationMatrix;
	}

	public Boolean getIsConverged() {
		return isConverged;
	}

	public void setIsConverged(Boolean isConverged) {
		this.isConverged = isConverged;
	}

	public Integer getIterateTimes() {
		return iterateTimes;
	}

	public void setIterateTimes(Integer iterateTimes) {
		this.iterateTimes = iterateTimes;
	}

	public Double[] getOriginalAbility() {
		return originalAbility;
	}

	public void setOriginalAbility(Double[] originalAbility) {
		this.originalAbility = originalAbility;
	}

	public List<Double> getSe() {
		return se;
	}

	public void setSe(List<Double> se) {
		this.se = se;
	}

	public List<Double> getReliability() {
		return reliability;
	}

	public void setReliability(List<Double> reliability) {
		this.reliability = reliability;
	}

	public void showAbility(Double[] ability) {
		for (int i = 0; i < ability.length; i++) {
			if (i == 0) {
				System.out.print("[" + ability[i]);
			} else if (i == ability.length - 1) {
				System.out.print(", " + ability[i] + "]");
			} else {
				System.out.print(", " + ability[i]);
			}

		}
	}

	public void showInformaionMatrix(Double[][] infomationMatrix) {
		for (Double[] row : infomationMatrix) {
			int i = 0;
			System.out.print("[");
			for (Double col : row) {
				if (i == 0) {
					System.out.print(col);
				} else {
					System.out.print(", " + col);
				}

				i++;
			}
			System.out.println("]");
		}
	}

	public List<List<Double>> getInformaionMatrix(Double[][] infomationMatrix) {
		List<List<Double>> infomation = new ArrayList<List<Double>>();
		for (Double[] row : infomationMatrix) {
			List<Double> unit = new ArrayList<Double>();
			for (Double col : row) {
				unit.add(col);
			}

			infomation.add(unit);
		}

		return infomation;
	}

	@Override
	public String toString() {
		return "AbilityVO [" + "iterateTimes=" + iterateTimes.toString() + ", isConverged=" + isConverged + "\n"
				+ "ability=" + Arrays.toString(ability) + "\n" + "originalAbility=" + Arrays.toString(originalAbility)
				+ "\n" + "infomationMatrix=" + getInformaionMatrix(infomationMatrix) + "\n" + "se=" + se + "\n"
				+ "reliability=" + reliability + "\n" + "]";
	}

}
