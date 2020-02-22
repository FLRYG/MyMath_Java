package sudoku;

public class Sudoku {

	//テスト用
	public static void main(String[] args) {
		int[] grid = {
				0, 0, 0, 9, 0, 0, 0, 3, 0,
				0, 1, 0, 0, 0, 5, 0, 0, 0,
				4, 0, 0, 0, 0, 0, 8, 0, 0,
				0, 9, 3, 0, 4, 0, 0, 0, 1,
				0, 5, 1, 0, 0, 0, 0, 6, 0,
				0, 0, 0, 0, 8, 0, 0, 0, 0,
				0, 8, 0, 0, 0, 4, 1, 0, 0,
				2, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 3, 0, 0, 0, 0, 2
		};
		Sudoku s = new Sudoku(grid);
		s.solve();
		System.out.println(s.getSolvedGrid());
	}

	private int[][][] binaryGrid = new int[9][9][9];
	private int[] solvedGrid = new int[81];

	public Sudoku(int[] grid) {
		for(int i = 0; i < 81; i++) {
			if(1 <= grid[i] && grid[i] <= 9) {
				binaryGrid[i / 9][i % 9][grid[i] - 1] = 1;
			} else {
				binaryGrid[i / 9][i % 9][grid[i]] = 0;
			}
		}
	}

	public int[] getSolvedGrid() {
		return solvedGrid;
	}

	public void solve() {
		boolean b = false;
		do {
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					for(int k = 0; k < 9; k++) {
						if(sumRow(i, j, k) + sumColumn(i, j, k) + sumDepth(i, j, k) + sumBrock(i, j, k) == 0) {
							binaryGrid[i][j][k] = 1;
							b = true;
						}
					}
				}
			}
		} while(b);

		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				for(int k = 0; k < 9; k++) {
					if(binaryGrid[i][j][k] == 1) {
						solvedGrid[i * 9 + j] = k;
					}
				}
			}
		}
	}

	private int sumRow(int i, int j, int k) {
		int sum = 0;
		for(int n = 0; n < 9; n++) {
			sum += binaryGrid[i][n][k];
		}
		return sum;
	}

	private int sumColumn(int i, int j, int k) {
		int sum = 0;
		for(int n = 0; n < 9; n++) {
			sum += binaryGrid[n][j][k];
		}
		return sum;
	}

	private int sumDepth(int i, int j, int k) {
		int sum = 0;
		for(int n = 0; n < 9; n++) {
			sum += binaryGrid[i][j][n];
		}
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
		return sum;
	}

}
