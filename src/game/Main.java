package game;

import java.util.ArrayList;
import java.util.Random;

import util.Static;
import util.UserInput;

public class Main {

	private Screen screen;
	private int[][] board;
	private boolean[][] combine;
	private Random rand;

	public Main(int width, int height) {
		screen = new Screen("2048", width * 152, height * 152);
		board = new int[width][height];
		combine = new boolean[width][height];
		rand = new Random();
		initBoard();
	}

	private void initBoard() {
		resetBoard();
		resetCombine();
		spawnRandomTile();
		spawnRandomTile();
	}

	public void update() {
		if (UserInput.isCurrentKeyPressed(Static.UP)) {
			moveUp();
		} else if (UserInput.isCurrentKeyPressed(Static.DOWN)) {
			moveDown();
		} else if (UserInput.isCurrentKeyPressed(Static.LEFT)) {
			moveLeft();
		} else if (UserInput.isCurrentKeyPressed(Static.RIGHT)) {
			moveRight();
		}
		screen.update();
	}

	public void render() {
		screen.render(board);
	}

	public void moveUp() {
		resetCombine();
		int amount = board[0].length;
		boolean spawn = false;
		while (amount > 1) {
			for (int y = 1; y < amount; y++) {
				for (int x = 0; x < board.length; x++) {
					if (board[x][y] != 0) {
						if (board[x][y] == board[x][y - 1]) {
							if (combine[x][y] && combine[x][y - 1]) {
								board[x][y - 1]++;
								board[x][y] = 0;
								combine[x][y - 1] = false;
								combine[x][y] = true;
								spawn = true;
							}
						} else if (board[x][y - 1] == 0) {
							board[x][y - 1] = board[x][y];
							board[x][y] = 0;
							spawn = true;
						}
					}
				}
			}
			amount--;
		}
		if (spawn) {
			spawnRandomTile();
		}
	}

	public void moveDown() {
		resetCombine();
		int amount = -1;
		boolean spawn = false;
		while (amount < board[0].length - 2) {
			for (int y = board[0].length - 2; y > amount; y--) {
				for (int x = 0; x < board.length; x++) {
					if (board[x][y] != 0) {
						if (board[x][y] == board[x][y + 1]) {
							if (combine[x][y] && combine[x][y + 1]) {
								board[x][y + 1]++;
								board[x][y] = 0;
								combine[x][y + 1] = false;
								combine[x][y] = true;
								spawn = true;
							}
						} else if (board[x][y + 1] == 0) {
							board[x][y + 1] = board[x][y];
							board[x][y] = 0;
							spawn = true;
						}
					}
				}
			}
			amount++;
		}
		if (spawn) {
			spawnRandomTile();
		}
	}

	public void moveLeft() {
		resetCombine();
		int amount = board.length;
		boolean spawn = false;
		while (amount > 1) {
			for (int y = 0; y < board[0].length; y++) {
				for (int x = 1; x < amount; x++) {
					if (board[x][y] != 0) {
						if (board[x][y] == board[x - 1][y]) {
							if (combine[x][y] && combine[x - 1][y]) {
								board[x-1][y]++;
								board[x][y] = 0;
								combine[x-1][y] = false;
								combine[x][y] = true;
								spawn = true;
							}
						} else if (board[x-1][y] == 0) {
							board[x-1][y] = board[x][y];
							board[x][y] = 0;
							spawn = true;
						}
					}
				}
			}
			amount--;
		}
		if (spawn) {
			spawnRandomTile();
		}
	}

	public void moveRight() {
		resetCombine();
		int amount = -1;
		boolean spawn = false;
		while (amount < board[0].length - 2) {
			for (int y = 0; y < board[0].length; y++) {
				for (int x = board.length - 2; x > amount; x--) {
					if (board[x][y] != 0) {
						if (board[x][y] == board[x + 1][y]) {
							if (combine[x][y] && combine[x + 1][y]) {
								board[x + 1][y]++;
								board[x][y] = 0;
								combine[x + 1][y] = false;
								combine[x][y] = true;
								spawn = true;
							}
						} else if (board[x + 1][y] == 0) {
							board[x + 1][y] = board[x][y];
							board[x][y] = 0;
							spawn = true;
						}
					}
				}
			}
			amount++;
		}
		if (spawn) {
			spawnRandomTile();
		}
	}

	private void resetBoard() {
		for (int y = 0; y < board[0].length; y++) {
			for (int x = 0; x < board.length; x++) {
				board[x][y] = 0;
			}
		}
	}

	private void resetCombine() {
		for (int y = 0; y < combine[0].length; y++) {
			for (int x = 0; x < combine.length; x++) {
				combine[x][y] = true;
			}
		}
	}

	private void spawnRandomTile() {
		ArrayList<Integer> xs = new ArrayList<Integer>();
		ArrayList<Integer> ys = new ArrayList<Integer>();
		for (int y = 0; y < board[0].length; y++) {
			for (int x = 0; x < board.length; x++) {
				if (board[x][y] == 0) {
					xs.add(x);
					ys.add(y);
				}
			}
		}
		int loc = rand.nextInt(xs.size());
		int num = (rand.nextInt(5));
		int value;
		if (num == 4) {
			value = 2;
		} else {
			value = 1;
		}
		board[xs.get(loc)][ys.get(loc)] = value;
	}

}
