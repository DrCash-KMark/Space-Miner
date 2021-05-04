package main;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * Azon p�rbesz�dablakok absztrakt �se, ahol a felhaszn�l� k�t param�tert ad meg.
 * @author Totya
 *
 */
public abstract class DialogSelect2 extends JDialog {
	protected Game game;
	protected Controller controller;
	protected JComboBox<String> comboTop;
	protected JComboBox<String> comboBottom;
	protected JButton bSelect;
	protected JLabel labelTop;
	protected JLabel labelBottom;
	
	/**
	 * A kiv�laszt�s gomb esem�nykezel�je.
	 * @param e
	 */
	public abstract void selectAction(ActionEvent e);

}
