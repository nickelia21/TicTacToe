package cs3500.tictactoe;

import java.awt.*;

import javax.swing.*;

public class TicTacToePanel extends JPanel {
  private ReadonlyTTTModel model;
  
  public TicTacToePanel(ReadonlyTTTModel model) {
    super();
    this.setBackground(Color.WHITE);
    this.model = model;
  }
  
  // Constants
  private int numCells = TicTacToeModel.numCells;
  public static final int FONT_SIZE = 30;
  public static final String FONT_FACE = "Helvetica";
  //public static final Font FONT = new Font(new HashMap<String, Integer>(FONT_FACE, FONT_SIZE));
  
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
        
        // I left in the pictures because I thought they were funny
        // - Still left implementation for X and O
        if (model.getMarkAt(row - 1, col - 1) != null) {
          if (model.getMarkAt(row - 1, col - 1) == Player.X) {
            //g2.drawString("X", xPos, yPos);
            g2.drawImage(new ImageIcon("resources/Jellyfish.jpg").getImage(), xPos - 50,
                yPos - 50, 100,
                100, this);
          } else {
            //g2.drawString("O", xPos, yPos);
            g2.drawImage(new ImageIcon("resources/HsifylleJ.jpg").getImage(), xPos - 50,
                yPos - 50, 100,
                100, this);
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