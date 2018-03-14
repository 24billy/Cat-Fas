package tw.com.billy.fastcat.core.common;

import java.util.ArrayList;
import java.util.List;

public class Constant {

	public static final Double[] muArray = new Double[] { -0.858d, 1.086d, 0.324d, -0.893 };
	public static final Double[] tVar = new Double[] { 59.8037, 29.5658, 11.756, 3.3292 };
	public static final Double[] abilityAverage = new Double[] { -0.5861, 1.2504, 0.3911, -0.8443 };

	// scoring matrix 不同項度有各自的scoring matrix
	public static List<Double[][]> scoringMatrixList = new ArrayList<Double[][]>() {
		private static final long serialVersionUID = 1L;
		{
			add(new Double[][] { { 0d, 0d, 0d, 0d }, { 1d, 0d, 0d, 0d }, { 2d, 0d, 0d, 0d }, { 3d, 0d, 0d, 0d } });
			add(new Double[][] { { 0d, 0d, 0d, 0d }, { 0d, 1d, 0d, 0d }, { 0d, 2d, 0d, 0d }, { 0d, 3d, 0d, 0d } });
			add(new Double[][] { { 0d, 0d, 0d, 0d }, { 0d, 0d, 1d, 0d }, { 0d, 0d, 2d, 0d }, { 0d, 0d, 3d, 0d } });
			add(new Double[][] { { 0d, 0d, 0d, 0d }, { 0d, 0d, 0d, 1d }, { 0d, 0d, 0d, 2d }, { 0d, 0d, 0d, 3d } });
		}
	};

	// design matrix
	public static final Double[][] designMatrix = new Double[][] { { 0d, 0d, 0d, 0d }, { -1d, -1d, 0d, 0d },
			{ -2d, -1d, -1d, 0d }, { -3d, -1d, -1d, -1d } };

	// variance/covariance matrix反矩陣
	public static final Double[][] covInverseArray = new Double[][] {
			{ 0.103390219, -0.119570491, 0.288036755, -0.58902167 },
			{ -0.119570491, 0.230065601, -0.415799009, 0.59671891 },
			{ 0.288036755, -0.415799009, 2.050500604, -3.666598361 },
			{ -0.58902167, 0.59671891, -3.666598361, 7.444638765 } };

	// 初始能力
	public static final Double[] initAbility = new Double[] { 0d, 0d, 0d, 0d };

	// 初始先驗分布
	public static final Double[][] priorInfo = new Double[][] { { 0d, 0d, 0d, 0d }, { 0d, 0d, 0d, 0d },
			{ 0d, 0d, 0d, 0d }, { 0d, 0d, 0d, 0d } };

}