
import java.util.*;

/*Class containing main method*/
public class SudokuSolver {
	public static void main(String[] arg) {
		Sudoku sample = new Sudoku();
		sample.getSudokuBoards();
		sample.solve();
	}
}



class Sudoku {
	ArrayList<SudokuBoard> oldSudokuBoards;
	ArrayList<SudokuBoard> newSudokuBoards;

	public Sudoku() {
		oldSudokuBoards = new ArrayList<SudokuBoard>();
		newSudokuBoards = new ArrayList<SudokuBoard>();
	}

	public void getSudokuBoards() {
		Scanner sc = new Scanner(System.in);
		int count = sc.nextInt();
		long[] inputSudokuBoard = new long[9];
		for (int i = 0; i < count; i++) {
			for (int k = 0; k < 9; k++) {
				inputSudokuBoard[k] = sc.nextLong();
			}
			oldSudokuBoards.add(new SudokuBoard(inputSudokuBoard));
			for (int k = 0; k < 9; k++) {
				inputSudokuBoard[k] = sc.nextLong();
			}
			newSudokuBoards.add(new SudokuBoard(inputSudokuBoard));
			sc.nextLine();
		}
		sc.close();
	}

	public void solve() {
		for (int i = 0; i < oldSudokuBoards.size(); i++) {
			if (solve(oldSudokuBoards.get(i), newSudokuBoards.get(i)) == true) {
				System.out.println("Yes");
			}
			else {
				System.out.println("No");
			}
		}
	}

	private boolean solve(SudokuBoard oldsudokuboard, SudokuBoard newsudokuboard) {
		for (int rot = 0; rot < 2; rot++) {
			for (int rowseg = 0; rowseg < 6; rowseg++) {
				for (int row0 = 0; row0 < 6; row0++) {
					for (int row1 = 0; row1 < 6; row1++) {
						for (int row2 = 0; row2 < 6; row2++) {
							for (int colseg = 0; colseg < 6; colseg++) {
								if (oldsudokuboard.colsegIsPermutation(newsudokuboard, 0)) {
									if (oldsudokuboard.colsegIsPermutation(newsudokuboard, 1)) {
										if (oldsudokuboard.colsegIsPermutation(newsudokuboard, 2)) {
											return true;
										}
									}
								}
								if (colseg % 2 == 0) {
									oldsudokuboard = oldsudokuboard.exchangeColumsSections(1, 2);
								}
								else {
									oldsudokuboard = oldsudokuboard.exchangeColumsSections(0, 2);
								}
							}
							if (row2 % 2 == 0) {
								oldsudokuboard = oldsudokuboard.exchangeRows(7, 8); 
							}
							else {
								oldsudokuboard = oldsudokuboard.exchangeRows(6, 8); 
							}
						}
						if (row1 % 2 == 0) {
							oldsudokuboard = oldsudokuboard.exchangeRows(4, 5); 
						}
						else {
							oldsudokuboard = oldsudokuboard.exchangeRows(3, 5); 
						}
					}
					if (row0 % 2 == 0) {
						oldsudokuboard = oldsudokuboard.exchangeRows(1, 2);
					}
					else {
						oldsudokuboard = oldsudokuboard.exchangeRows(0, 2);
					}
				}
				if (rowseg % 2 == 0) {
					oldsudokuboard = oldsudokuboard.exchangeRowSections(1, 2);
				}
				else {
					oldsudokuboard = oldsudokuboard.exchangeRowSections(0, 2);
				}
			}
			oldsudokuboard = oldsudokuboard.rotate();
		}
		return false;
	}
}




class SudokuBoard {
	private int[][] numbers = new int[9][9];

	public SudokuBoard (long[] input) {
		for (int row = 0; row < 9; row++) {
			for (int col = 8; col >= 0; col--) {
				numbers[row][col] = (int) input[row] % 10;
				input[row] = input[row] / 10;
			}
		}
	}

	public SudokuBoard(int[][] input) {
		numbers = input;
	}

	public SudokuBoard rotate() {
		int[][] rotated = new int[9][9];
		for (int fromrow = 0, tocol = 8; fromrow < 9; fromrow++, tocol--) {
			for (int fromcol = 0, torow = 0; fromcol < 9; fromcol++, torow++) {
				rotated[torow][tocol] = numbers[fromrow][fromcol];
			}
		}
		return new SudokuBoard(rotated);
	}

	public SudokuBoard exchangeColumns(int first, int second) {
		SudokuBoard returnValue = new SudokuBoard(numbers);
		int temp;
		for (int row = 0; row < 9; row++) {
			temp = numbers[row][first];
			numbers[row][first] = numbers[row][second];
			numbers[row][second] = temp;
		}
		return returnValue;
	}

	public SudokuBoard exchangeRows(int first, int second) {
		SudokuBoard returnValue = new SudokuBoard(numbers);
		int temp;
		for (int col = 0; col < 9; col++) {
			temp = numbers[first][col];
			numbers[first][col] = numbers[second][col];
			numbers[second][col] = temp;
		}
		return returnValue;
	}

	public SudokuBoard exchangeColumsSections(int first, int second) {
		SudokuBoard returnValue = new SudokuBoard(numbers);
		int temp;
		for (int col = 0; col < 3; col++) {
			for (int row = 0; row < 9; row++) {
				temp = numbers[row][first*3 + col];
				numbers[row][first*3 + col] = numbers[row][second*3 + col];
				numbers[row][second*3 + col] = temp;
			}
		}
		return returnValue;
	}

	public SudokuBoard exchangeRowSections(int first, int second) {
		SudokuBoard returnValue = new SudokuBoard(numbers);
		int temp;
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 9; col++) {
				temp = numbers[first*3 + row][col];
				numbers[first*3 + row][col] = numbers[second*3 + row][col];
				numbers[second*3 + row][col] = temp;
			}
		}
		return returnValue;
	}
		
	public boolean colsegIsPermutation(SudokuBoard in, int colseg) {
		for (int perm = 0; perm < 6; perm++) {
			if (colsegTest(in, colseg) == true) {
				return true;
			}
			if (perm % 2 == 0) {
				this.exchangeColumns(colseg*3+1, colseg*3+2);
			}
			else {
				this.exchangeColumns(colseg*3+0, colseg*3+2);
			}
		}
		return false;
	}
	
	public boolean colsegTest(SudokuBoard in, int colseg) {
		int[] permutations = new int[10];
		for (int row = 0; row < 9; row++) {
			for (int col = colseg*3; col < colseg*3+3; col++) {
				if (in.numbers[row][col] != 0) {
					if (numbers[row][col] != permutations[in.numbers[row][col]] && permutations[in.numbers[row][col]] != 0) {
						return false;
					}
					else {
						permutations[in.numbers[row][col]] = numbers[row][col];
					}
				}
			}
		}
		return true;
	}
}


    
