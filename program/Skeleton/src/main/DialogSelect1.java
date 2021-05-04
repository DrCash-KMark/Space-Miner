package main;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * Azon párbeszédablakok absztrakt õse, ahol a felhasználó csak egyetlen paramétert szeretne kiválasztani.
 * @author Totya
 */
public abstract class DialogSelect1 extends JDialog {
	protected Game game;
	protected Controller controller;
	protected JComboBox<String> comboTop;
	protected JButton bSelect;
	protected JLabel labelTop;
	
	/**
	 * A kiválasztás gomb eseménykezelõje.
	 * @param e
	 */
	public abstract void selectAction(ActionEvent e);
}
