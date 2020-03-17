package cs3500.tictactoe;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Font;

import javax.swing.JPanel;

/**
 * Public class that does the drawing of the game board and elements.
 */
public class TicTacToePanel extends JPanel {
  private ReadonlyTTTModel model;
  
  /**
   * Primary constructor that creates a new instance of a TicTacToeØPanel.
   *
   * @param model the read-only version of the given model.
   */
  public TicTacToePanel(ReadonlyTTTModel model) {
    super();
    this.setBackground(Color.WHITE);
    this.model = model;
  }
  
  // Constants
  private int numCells = TicTacToeModel.NUM_CELLS;
  public static final int FONT_SIZE = 40;
  public static final String FONT_FACE = "Helvetica";
  
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    
    // Set the font
    g2.setFont(new Font(FONT_FACE, Font.PLAIN, FONT_SIZE));
    
    // Model attributes
    Player turn = model.getTurn();
    Player winner = model.getWinner();
    
    // Panel Dimensions
    int w = TicTacToeView.WIDTH;
    int h = TicTacToeView.HEIGHT;
    int sh = TicTacToeView.STATUS_HEIGHT;
    
    // draw grid lines - 2 vert and 2 horiz
    g2.drawLine(w / 3, 0, w / 3, h - sh);
    g2.drawLine(w * 2 / 3, 0, w * 2 / 3, h - sh);
    g2.drawLine(0, (h - sh) / 3, w, (h - sh) / 3);
    g2.drawLine(0, (h - sh) * 2 / 3, w, (h - sh) * 2 / 3);
    
    // iterate over board, draw X/O in appropriate cell
    for (int row = 1; row <= numCells; row++) {
      for (int col = 1; col <= numCells; col++) {
        int xPos = (w * col / numCells) - (w / (numCells * 2));
        int yPos = ((h - sh) * row / numCells) - ((h - sh) / (numCells * 2));
        
        if (model.getMarkAt(row - 1, col - 1) != null) {
          if (model.getMarkAt(row - 1, col - 1) == Player.X) {
            g2.drawString("X", xPos - (FONT_SIZE / 2), yPos + (FONT_SIZE / 2));
          } else {
            g2.drawString("O", xPos - (FONT_SIZE / 2), yPos + (FONT_SIZE / 2));
          }
        }
      }
    }
    // Check if winner, then print
    if (winner != null) {
      g2.drawString("Player " + winner.toString() + " has won!", w / 3, h - (sh / 2));
    } else if (model.isGameOver()) {
      g2.drawString("Tie game.", w / 3, h - (sh / 2));
    } else {
      // turn statuses
      g2.drawString("TURN: " + turn.toString(), w / 6, h - (sh / 2));
    }
  }
}