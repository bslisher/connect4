package connect4;

public class Grid {

	public DiscColor currentDiscColor = DiscColor.Black;

	public Disc[][] discs = new Disc[7][6];

	public boolean dropDisc(int column) {

		if (column > 6 || column < 0) {
			return false;
		}

		if (this.discs[column][0] != null) {
			return false;
		}

		for (int i = 5; ; i--) {
			if (this.discs[column][i] == null) {
				this.discs[column][i] = new Disc(this.currentDiscColor);
				break;
			}
		}

		this.currentDiscColor = this.currentDiscColor == DiscColor.Black ? DiscColor.Red : DiscColor.Black;

		return true;
	}

	public GameState getGameState() {

		// https://codereview.stackexchange.com/a/127105

		final int HEIGHT = this.discs.length;
		final int WIDTH = this.discs[0].length;
		for (int r = 0; r < HEIGHT; r++) { // iterate rows, bottom to top
			for (int c = 0; c < WIDTH; c++) { // iterate columns, left to right

				if (this.discs[r][c] == null)
					continue; // don't check empty slots

				DiscColor color = this.discs[r][c].getColor();
				DiscColor winningColor = null;

				if (c + 3 < WIDTH &&
						(this.discs[r][c+1] != null && color == this.discs[r][c+1].getColor()) && // look right
						(this.discs[r][c+2] != null && color == this.discs[r][c+2].getColor()) &&
						(this.discs[r][c+3] != null && color == this.discs[r][c+3].getColor()))
					winningColor = color;
				if (r + 3 < HEIGHT) {
					if ((this.discs[r+1][c] != null && color == this.discs[r+1][c].getColor()) && // look up
							(this.discs[r+2][c] != null && color == this.discs[r+2][c].getColor()) &&
							(this.discs[r+3][c] != null && color == this.discs[r+3][c].getColor()))
						winningColor = color;
					if (c + 3 < WIDTH &&
							(this.discs[r+1][c+1] != null && color == this.discs[r+1][c+1].getColor()) && // look up & right
							(this.discs[r+2][c+2] != null && color == this.discs[r+2][c+2].getColor()) &&
							(this.discs[r+3][c+3] != null && color == this.discs[r+3][c+3].getColor()))
						winningColor = color;
					if (c - 3 >= 0 &&
							(this.discs[r+1][c-1] != null && color == this.discs[r+1][c-1].getColor()) && // look up & left
							(this.discs[r+2][c-2] != null && color == this.discs[r+2][c-2].getColor()) &&
							(this.discs[r+3][c-3] != null && color == this.discs[r+3][c-3].getColor()))
						winningColor = color;
				}

				if (winningColor == DiscColor.Red) {
					return GameState.RedWins;
				}
				else if (winningColor == DiscColor.Black) {
					return GameState.BlackWins;
				}
			}
		}

		// No terminal state found	
		return GameState.InProgress;
	}
}
