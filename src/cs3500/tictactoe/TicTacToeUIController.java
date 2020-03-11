package cs3500.tictactoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

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
    JButton echoButton, exitButton;
    
//    switch (e.getActionCommand()) {
//      case "Echo Button":
//        echoButton = new JButton("Echo");
//        echoButton.setActionCommand("Echo Button");
//        //this.add(echoButton);
//      case "Exit Button":
//        exitButton = new JButton("Exit");
//        exitButton.setActionCommand("Exit Button");
//        //this.add(exitButton);
//    }
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
}
