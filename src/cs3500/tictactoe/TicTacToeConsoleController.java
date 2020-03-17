package cs3500.tictactoe;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Public class representing the console version of the TicTacToe controller.
 */
public class TicTacToeConsoleController implements TicTacToeController {
  private final Appendable out;
  private final Scanner scan;
  
  /**
   * Primary constructor that creates a new instance of a TicTacToeConsoleController.
   *
   * @param in  Readable user input
   * @param out Appendable system output
   */
  public TicTacToeConsoleController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
  }
  
  @Override
  public void playGame(TicTacToe m) {
    try {
      appendStateAndPrompt(m);
      Integer mvRow = null;
      Integer mvCol = null;
      String tok = "";
      while (!m.isGameOver()) {
        tok = scan.next();
        if (tok.equalsIgnoreCase("q")) {
          break;
        }
        try {
          int v = Integer.parseInt(tok);
          if (mvRow == null) {
            mvRow = v;
          } else {
            mvCol = v;
            m.move(mvRow - 1, mvCol - 1);
            if (m.isGameOver()) {
              out.append(m.toString()).append("\n");
              out.append("Game is over! ");
              if (m.getWinner() != null) {
                out.append(m.getWinner().toString()).append(" wins.\n");
              } else {
                out.append("Tie game.\n");
              }
              break;
            }
            appendStateAndPrompt(m);
            mvRow = mvCol = null;
          }
        } catch (NumberFormatException nfe) {
          out.append("Not a valid number: ").append(tok).append("\n");
        } catch (IllegalArgumentException iae) {
          out.append("Not a valid move: " + mvRow + ", " + mvCol + "\n");
          mvRow = mvCol = null;
        }
      }
      if (!m.isGameOver() && tok.equalsIgnoreCase("q")) {
        out.append("Game quit! Ending game state:\n").append(m.toString()).append("\n");
      } else if (!m.isGameOver()) {
        throw new IllegalStateException("Ran out of inputs");
      }
    } catch (IOException ioe) {
      scan.close();
      throw new IllegalStateException("append failed", ioe);
    } catch (NoSuchElementException e) {
      throw new IllegalStateException("failed to read from readable");
    }
    scan.close();
  }
  
  private void appendStateAndPrompt(TicTacToe m) throws IOException {
    out.append(m.toString()).append("\n");
    out.append("Enter a move for ").append(m.getTurn().toString()).append(":\n");
  }
  
  @Override
  public void handleCellClick(int row, int col) {
    // do nothing because this is the console controller
  }
}
