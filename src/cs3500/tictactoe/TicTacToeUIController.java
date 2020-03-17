package cs3500.tictactoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Public class representing a visual controller of the TicTacToe game.
 */
public class TicTacToeUIController implements TicTacToeController, ActionListener {
  // The View cannot be null.
  private TTTView view;
  private TicTacToe model;
  
  /**
   * Primary public constructor that creates a new TicTacToeUIController.
   *
   * @param v the given View passed in
   *
   * @throws IllegalArgumentException if the given View is null
   */
  public TicTacToeUIController(TTTView v) {
    if (v == null) {
      throw new IllegalArgumentException("Given view cannot be null!");
    }
    this.view = v;
  }
  
  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    JButton resetButton;
    if (e.getActionCommand().equals("Reset Button")) {
      resetButton = new JButton("Reset");
      resetButton.setActionCommand("Reset Button");
      //this.add(resetButton);
    }
  }
  
  /**
   * Execute a single game of tic tac toe given a tic tac toe Model. When the game is over,
   * the playGame method ends.
   *
   * @param m a non-null tic tac toe Model
   */
  @Override
  public void playGame(TicTacToe m) {
    if (m == null) {
      throw new IllegalArgumentException("Given model cannot be null!");
    }
    this.model = m;
    
    view.addClickListener(this);
    view.makeVisible();
  }
  
  /**
   * Handle an action in a single cell of the board, such as to make a move.
   *
   * @param row the row of the clicked cell
   * @param col the column of the clicked cell
   */
  @Override
  public void handleCellClick(int row, int col) {
    try {
      model.move(row, col);
      view.refresh();
    } catch (IllegalArgumentException | IllegalStateException e) {
      // do nothing since move is illegal
    }
  }
  
  /**
   * Handles the game being reset so it could be played again.
   */
  public void handleReset() {
    //model.reset();
  }
}
