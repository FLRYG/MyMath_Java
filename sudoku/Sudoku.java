package sudoku;

//Version 1.0
public class Sudoku {

	//テスト用
	public static void main(String[] args) {
		int[] grid = {
				2, 0, 6, 1, 0, 0, 0, 9, 5,
				0, 0, 0, 0, 9, 0, 0, 0, 0,
				0, 7, 0, 2, 3, 0, 0, 0, 0,
				0, 6, 3, 9, 0, 0, 0, 0, 0,
				0, 4, 8, 0, 0, 0, 0, 7, 6,
				0, 0, 0, 6, 0, 0, 0, 4, 8,
				0, 0, 0, 5, 0, 9, 0, 0, 7,
				0, 0, 0, 0, 0, 3, 0, 0, 0,
				0, 5, 4, 7, 0, 0, 1, 2, 0
		};
		Sudoku s = new Sudoku(grid);
		System.out.println(s.solve());
		System.out.println(s.stringResult());
		System.out.println(s.getLog());

		//binary表示
		/* for(int i = 0; i < 9; i++) {
		 * System.out.print(i + 1 + "\n");
		 * for(int j = 0; j < 9; j++) {
		 * for(int k = 0; k < 9; k++) {
		 * System.out.print(s.binaryGrid[j][k][i]);
		 * }
		 * System.out.print("\n");
		 * }
		 * System.out.print("\n");
		 * } */
	}

	private int[][][] binaryGrid = new int[9][9][9];
	private int[][] checker = new int[9][9];
	private int[] solvedGrid = new int[81];
	private StringBuilder log;

	/**
	 * 数独
	 */
	public Sudoku(int[] grid) {
		if(grid.length != 81) {
			throw new IllegalArgumentException("要素数は 81 個である必要があります");
		}
		//binaryGrid を1で初期化
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				for(int k = 0; k < 9; k++) {
					binaryGrid[i][j][k] = 1;
				}
			}
		}
		//初期値を binaryGrid に反映する
		for(int i = 0; i < 81; i++) {
			if(1 <= grid[i] && grid[i] <= 9) {
				confirm(i / 9, i % 9, grid[i] - 1);
			}
		}
	}

	/**
	 * 計算結果を配列の状態で返す
	 */
	public int[] getResult() {
		return solvedGrid;
	}

	/**
	 * 計算結果を整理された文字列の状態で返す
	 */
	public String stringResult() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 81; i++) {
			if(i % 27 == 0 && i != 0) {
				sb.append("---+---+---\n");
			}
			if(i % 9 == 8) {
				sb.append(solvedGrid[i] + "\n");
			} else if(i % 3 == 2) {
				sb.append(solvedGrid[i] + "|");
			} else {
				sb.append(solvedGrid[i]);
			}
		}
		return sb.toString();
	}

	/**
	 * 計算ログを返す
	 */
	public String getLog() {
		return log != null ? log.toString() : "";
	}

	/**
	 * 数独を解く。解けた場合は {@code true} , 解けない場合は {@code false} を返す
	 */
	public boolean solve() {
		//計算ログの初期化
		log = new StringBuilder();
		//解く
		boolean b;
		do {
			b = false;
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					for(int k = 0; k < 9; k++) {
						if(binaryGrid[i][j][k] == 1) {
							if(sumDepth(i, j, k) == 1 || sumRow(i, j, k) == 1 ||
									sumColumn(i, j, k) == 1 || sumBrock(i, j, k) == 1) {
								if(checker[i][j] == 0) {
									confirm(i, j, k);
									log.append("(" + (i + 1) + ", " + (j + 1) + ") = " + (k + 1) + '\n');
									b = true;
									break;
								}
							}
						}
					}
				}
			}
		} while(b);
		//正しく解けたかチェック
		boolean result = true;
		s: for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(checker[i][j] == 0) {
					result = false;
					break s;
				}
				/* for(int k = 0; k < 9; k++) {
				 * if(!(sumDepth(i, j, k) == 1 && sumRow(i, j, k) == 1 &&
				 * sumColumn(i, j, k) == 1 && sumBrock(i, j, k) == 1)) {
				 * result = false;
				 * break s;
				 * }
				 * } */
			}
		}
		//変換してsolvedGridに代入
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(checker[i][j] == 1) {
					for(int k = 0; k < 9; k++) {
						if(binaryGrid[i][j][k] == 1) {
							solvedGrid[i * 9 + j] = k + 1;
							break;
						}
					}
				}
			}
		}
		return result;
	}

	//確定させるx
	private void confirm(int i, int j, int k) {
		for(int n = 0; n < 9; n++) {
			binaryGrid[n][j][k] = 0;
			binaryGrid[i][n][k] = 0;
			binaryGrid[i][j][n] = 0;
		}
		for(int m = (i / 3) * 3; m < (i / 3) * 3 + 3; m++) {
			for(int n = (j / 3) * 3; n < (j / 3) * 3 + 3; n++) {
				binaryGrid[m][n][k] = 0;
			}
		}
		binaryGrid[i][j][k] = 1;
		checker[i][j] = 1;
	}

	private int sumRow(int i, int j, int k) {
		int sum = 0;
		for(int n = 0; n < 9; n++) {
			sum += binaryGrid[n][j][k];
		}
		//System.out.println("row");
		return sum;
	}

	private int sumColumn(int i, int j, int k) {
		int sum = 0;
		for(int n = 0; n < 9; n++) {
			sum += binaryGrid[i][n][k];
		}
		//System.out.println("column");
		return sum;
	}

	private int sumDepth(int i, int j, int k) {
		int sum = 0;
		for(int n = 0; n < 9; n++) {
			sum += binaryGrid[i][j][n];
		}
		//System.out.println("depth");
		return sum;
	}

	private int sumBrock(int i, int j, int k) {
		int sum = 0;
		int s = (i / 3) * 3;
		int t = (j / 3) * 3;
		for(int m = s; m < s + 3; m++) {
			for(int n = t; n < t + 3; n++) {
				sum += binaryGrid[m][n][k];
			}
		}
		//System.out.println("brock");
		return sum;
	}

}
