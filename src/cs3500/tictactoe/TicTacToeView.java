package cs3500.tictactoe;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

/**
 * ...............
 */
public class TicTacToeView extends JFrame implements TTTView {
  private JButton resetButton;
  private JPanel panel;
  
  // Public Constants
  public static int WIDTH = 500;
  public static int HEIGHT = 600;
  public static int STATUS_HEIGHT = 100;
  public static int CELL_DIM = WIDTH / 3;
  
  /**
   * ...............
   *
   * @param m The TicTacToeModelReadOnly reference passed in that is visualized by this view.
   */
  public TicTacToeView(String windowTitle, ReadonlyTTTModel m) {
    super(windowTitle);
    
    setSize(WIDTH, HEIGHT);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
    
    panel = new TicTacToePanel(m);
    this.add(panel);
    
    // resetButton
    resetButton = new JButton("RESET");
    resetButton.setActionCommand("Reset Button");
    panel.add(resetButton);
    
    makeVisible();
  }
  
  /**
   * Set up the controller to handle click events in this view.
   *
   * @param listener the controller
   */
  @Override
  public void addClickListener(TicTacToeController listener) {
    MouseListener ml = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int row = e.getY() / CELL_DIM;
        int col = e.getX() / CELL_DIM;
        listener.handleCellClick(row, col);
      }
    };
    panel.addMouseListener(ml);
  }
  
  /**
   * Refresh the view to reflect any changes in the game state.
   */
  @Override
  public void refresh() {
    this.repaint();
  }
  
  /**
   * Make the view visible to start the game session.
   */
  @Override
  public void makeVisible() {
    this.pack();
    this.setVisible(true);
  }
}
