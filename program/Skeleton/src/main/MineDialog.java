package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A bányászás elvégzéséhez szükséges dialógusablak.
 * @author Totya
 */
public class MineDialog extends DialogSelect1 {
	/**
	 * A bányászás dialógusablakának konstruktora.
	 * Feliratozza a labelek-et, valamint hozzáadja az eseménykezelést.
	 * @param g: A játékot reprezentáló osztály, amitõl a szükséges adatokat lekérheti.
	 * @param cont: A kontroller példánya, amin a kiadott parancshoz tartozó kezelõket hívhatjuk meg.
	 */
	public MineDialog(Game g, Controller cont) {
		super(g, cont);
		this.labelTitle.setText("Mine with Settler");
		this.labelTop.setText("Settler ID:");
		MineSelectListener mineListener = new MineSelectListener(this, this.controller, this.comboTop);
		this.bSelect.addActionListener(mineListener);
	}	
	/**
	 * A dialógusablak megjelenítése.
	 * A dobozok régi értékeit eltávolítja, lekérdezi az újakat a játéktól.
	 */
	public void show() {
		this.comboTop.removeAllItems();
		String[] ids = game.getSettlerIDs();
		for(int i = 0; i < ids.length; i++) {
			this.comboTop.addItem(ids[i]);
		}
	}
	/**
	 * A fúrás eseménykezelésének privát osztálya.
	 * A DrillDialog belsõ osztálya.
	 * @author Totya
	 */
	private class MineSelectListener implements ActionListener{
		private DialogSelect1 parentDialog;
		private Controller controller;
		private JComboBox<String> box;
		/**
		 * Az bányászás eseménykezelésének konstruktora.
		 * @param dial: DialogSelect1: A dialógusablak, amit esetleg be kell majd zárni.
		 * @param cont: Controller: A kontroller, amin függvényt hívja.
		 * @param b: JComboBox<String> A doboz, amibõl kiolvassa a paramétert.
		 */
		public MineSelectListener(DialogSelect1 dial, Controller cont, JComboBox<String> b) {
			parentDialog = dial;
			controller = cont;
			box = b;
		}
		/**
		 * Megnyomjuk a select gombot, bányászunk, majd bezárjuk.
		 */
		public void actionPerformed(ActionEvent e) {
			String settlerID = (String) box.getSelectedItem();
			if(settlerID == null || settlerID == "") {
				return;
			}
			controller.handleMine(settlerID);
			parentDialog.setVisible(false);
		}
	}	
}
