package cs3500.tictactoe;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Public class representing the View for the TicTacToe game.
 */
public class TicTacToeView extends JFrame implements TTTView {
  private JPanel panel;
  
  // Public Constants
  public static int WIDTH = 500;
  public static int HEIGHT = 600;
  public static int STATUS_HEIGHT = 100;
  public static int CELL_DIM = WIDTH / 3;
  
  /**
   * Primary constructor that creates a new instance of a TicTacToeView.
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
