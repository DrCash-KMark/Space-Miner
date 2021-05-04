package main;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * Azon p�rbesz�dablakok absztrakt �se, ahol a felhaszn�l� h�rom param�tert ad meg a m�velet sz�m�ra.
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
	 * A kiv�laszt�s gomb esem�nykezel�je.
	 * @param e
	 */
	public abstract void selectAction(ActionEvent e);

}
