package main;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * Azon párbeszédablakok absztrakt õse, ahol a felhasználó hérom paramétert ad meg a mûvelet számára.
 * @author Totya
 */
public abstract class DialogSelect3 extends JDialog {
	protected Game game;
	protected Controller controller;
	protected JComboBox<String> comboTop;
	protected JComboBox<String> comboMiddle;
	protected JComboBox<String> comboBottom;
	protected JButton bSelect;
	protected JLabel labelTop;
	protected JLabel labelMiddle;
	protected JLabel labelBottom;
	
	/**
	 * A kiválasztás gomb eseménykezelõje.
	 * @param e
	 */
	public abstract void selectAction(ActionEvent e);

}
