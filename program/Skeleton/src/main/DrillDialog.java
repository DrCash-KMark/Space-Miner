package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * A fúrás paramétereinek bekéréséhez használt dialógusablak.
 * @author Totya
 */
public class DrillDialog extends DialogSelect1 {
	
	/**
	 * A DrillDialog konstruktora.
	 * Feliratozza a labeleket, valamint hozzáadja az eseménykezelést a szükséges elemekhez.
	 * @param g: A játékot reprezentáló osztály, amitõl a szükséges adatokat lekérheti.
	 * @param cont: A kontroller példánya, amin a kiadott parancshoz tartozó kezelõket hívhatjuk meg.
	 */
	public DrillDialog(Game g, Controller cont) {
		super(g, cont);
		this.labelTitle.setText("Drill with Settler");
		this.labelTop.setText("Settler ID:");
		DrillSelectListener drillListener = new DrillSelectListener(this, this.controller, this.comboTop);
		this.bSelect.addActionListener(drillListener);
	}
	/**
	 * A dialógusablak megjelenítésénél leszedem a comboBoxokról az eddigi elemeket.
	 * Ezután lekérdezem a játéktól, hogy mibõl élünk és az felrakom.
	 */
	public void show() {
		this.comboTop.removeAllItems();
		String[] settlerIDs = game.getSettlerIds();
		for(int i = 0; i < settlerIDs.length; i++) {
			this.comboTop.addItem(settlerIDs[i]);
		}
		super.show();
	}	
	/**
	 * A fúrás eseménykezelésének privát osztálya.
	 * A DrillDialog belsõ osztálya.
	 * @author Totya
	 */
	private class DrillSelectListener implements ActionListener{
		private DialogSelect1 parentDialog;
		private Controller controller;
		private JComboBox<String> box;
		/**
		 * Az fúrás eseménykezelésének konstruktora.
		 * @param dial: DialogSelect1: A dialógusablak, amit esetleg be kell majd zárni.
		 * @param cont: Controller: A kontroller, amin függvényt hívja.
		 * @param b: JComboBox<String> A doboz, amibõl kiolvassa a paramétert.
		 */
		public DrillSelectListener(DialogSelect1 dial, Controller cont, JComboBox<String> b) {
			parentDialog = dial;
			controller = cont;
			box = b;
		}
		/**
		 * Amikor megnyomjuk a select gombot, fúrunk a kiválasztott telepessel, majd bezárjuk a dialógusablakot.
		 */
		public void actionPerformed(ActionEvent e) {
			String settlerID = (String) box.getSelectedItem();
			if(settlerID == null || settlerID == "") {
				return;
			}
			controller.handleDrill(settlerID);
			parentDialog.setVisible(false);
		}	
	}
}
