import org.junit.Test;

import java.io.StringReader;
import java.util.Arrays;

import cs3500.tictactoe.TicTacToe;
import cs3500.tictactoe.TicTacToeConsoleController;
import cs3500.tictactoe.TicTacToeController;
import cs3500.tictactoe.TicTacToeModel;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the tic tac toe controller, using mocks for readable and appendable.
 */
public class TicTacToeUIControllerTest {
  
  @Test
  public void testSingleValidMove() {
    TicTacToe m = new TicTacToeModel();
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(new StringReader("2 2 q"), gameLog);
    c.playGame(m);
    assertEquals("   |   |  \n"
                     + "-----------\n"
                     + "   |   |  \n"
                     + "-----------\n"
                     + "   |   |  \n"
                     + "Enter a move for X:\n"
                     + "   |   |  \n"
                     + "-----------\n"
                     + "   | X |  \n"
                     + "-----------\n"
                     + "   |   |  \n"
                     + "Enter a move for O:\n"
                     + "Game quit! Ending game state:\n"
                     + "   |   |  \n"
                     + "-----------\n"
                     + "   | X |  \n"
                     + "-----------\n"
                     + "   |   |  \n", gameLog.toString());
  }
  
  @Test
  public void testBogusInputAsRow() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("!#$ 2 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    // split the output into an array of lines
    String[] lines = gameLog.toString().split("\n");
    // check that it's the correct number of lines
    assertEquals(lines.length, 13);
    // check that the last 6 lines are correct
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 6, lines.length));
    assertEquals("Game quit! Ending game state:\n"
                     + "   |   |  \n"
                     + "-----------\n"
                     + "   |   |  \n"
                     + "-----------\n"
                     + "   |   |  ", lastMsg);
    // note no trailing \n here, because of the earlier split
  }
  
  @Test
  public void testBogusInputAsCol() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 !#$ q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(lines.length, 13);
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 6, lines.length));
    assertEquals("Game quit! Ending game state:\n"
                     + "   |   |  \n"
                     + "-----------\n"
                     + "   |   |  \n"
                     + "-----------\n"
                     + "   |   |  ", lastMsg);
  }
  
  @Test
  public void testInputOutsideOfBounds() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("99 99 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(lines.length, 13);
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 7, lines.length - 6));
    assertEquals("Not a valid move: 99, 99", lastMsg);
  }
  
  @Test
  public void testInputCellOccupied() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 1 1 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(lines.length, 19);
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 7, lines.length - 6));
    assertEquals("Not a valid move: 1, 1", lastMsg);
  }
  
  @Test
  public void testInputMultipleCellsOccupied() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 2 2 1 1 2 2 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(lines.length, 26);
    String posMsg1 = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 8, lines.length - 7));
    String posMsg2 = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 7, lines.length - 6));
    assertEquals("Not a valid move: 1, 1", posMsg1);
    assertEquals("Not a valid move: 2, 2", posMsg2);
  }
  
  @Test
  public void testTieGame() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(60, lines.length);
    assertEquals("Game is over! Tie game.", lines[lines.length - 1]);
  }
  
  @Test
  public void testWinStraight() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 2 1 1 2 2 2 1 3");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(36, lines.length);
    assertEquals("Game is over! X wins.", lines[lines.length - 1]);
  }
  
  @Test
  public void testWinDiagonal() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 2 1 2 2 3 1 3 3");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(36, lines.length);
    assertEquals("Game is over! X wins.", lines[lines.length - 1]);
  }
  
  @Test
  public void testWinAfterInvalids() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 1 1 2 1 #!$ 1 2 99 99 2 2 1 3 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(39, lines.length);
    assertEquals("Not a valid move: 1, 1", lines[12]);
    assertEquals("Not a valid number: #!$", lines[19]);
    assertEquals("Not a valid move: 99, 99", lines[26]);
    assertEquals("Game is over! X wins.", lines[lines.length - 1]);
  }
  
  @Test
  public void testQFirst() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(lines.length, 12);
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 6, lines.length));
    assertEquals("Game quit! Ending game state:\n"
                     + "   |   |  \n"
                     + "-----------\n"
                     + "   |   |  \n"
                     + "-----------\n"
                     + "   |   |  ", lastMsg);
  }
  
  @Test
  public void testQBeforeRow() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(18, lines.length);
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 6, lines.length));
    assertEquals("Game quit! Ending game state:\n"
                     + " X |   |  \n"
                     + "-----------\n"
                     + "   |   |  \n"
                     + "-----------\n"
                     + "   |   |  ", lastMsg);
  }
  
  @Test
  public void testQBeforeCol() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 2 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(18, lines.length);
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 6, lines.length));
    assertEquals("Game quit! Ending game state:\n"
                     + " X |   |  \n"
                     + "-----------\n"
                     + "   |   |  \n"
                     + "-----------\n"
                     + "   |   |  ", lastMsg);
  }
  
  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    Appendable gameLog = new FailingAppendable();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
  }
  
}
