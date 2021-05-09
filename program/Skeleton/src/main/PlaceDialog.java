package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Kapuk elhelyezését lebonyolító párbeszédablak osztálya.
 * @author Totya
 */

public class PlaceDialog extends DialogSelect2 {
	/**
	 * Kaput lehelyezõ dialógusablak konstruktora.
	 * Fleiratozza a dialógusablakot, majd felveszi az eseménykezelõket.
	 * @param g: Game a játékot vezérlõ osztály.
	 * @param cont: A Controller példánya.
	 */
	public PlaceDialog(Game g, Controller cont) {
		super(g, cont);
		labelTitle.setText("Place Gate with Settler");
		labelTop.setText("Gate Id:");
		labelBottom.setText("Settler ID:");
		SelectSettlerListener settlerListener = new SelectSettlerListener(this.game, this.comboTop);
		this.comboBottom.addItemListener(settlerListener);
		PlaceBuildingListener placeListener = new PlaceBuildingListener(this, this.controller, this.comboTop, this.comboBottom);
		this.bSelect.addActionListener(placeListener);
	}
	
	/**
	 * A dialógusablak megjelenítése.
	 * Feltölti a settler-valsztó dobozt, az aktuális azonosítókkal.
	 */
	public void show() {
		this.comboBottom.removeAllItems();
		String[] ids = game.getSettlerIds();
		for(int i = 0; i < ids.length; i++) {
			this.comboBottom.addItem(ids[i]);
		}
		super.show();
	}
	
	/**
	 * Telepes választásának eseménykezelõ osztálya.
	 * A Lehelyezés dialógus belsõ osztálya.
	 * @author Totya.
	 */
	private class SelectSettlerListener implements ItemListener {
		private Game game;
		private JComboBox<String> gateCombo;
		/**
		 * A Telepesválasztó eseménykezelõ konstruktora.
		 * @param g: Game: A játék, amitõ lekérdezzük az adatokat.
		 * @param gc: JComboBox<String> gc: A kapukat tartalmazó comboBox.
		 */
		public SelectSettlerListener(Game g, JComboBox<String> gc) {
			game = g;
			gateCombo = gc;
		}
		/**
		 * Az eddigi kapukat tartalmazó comboBoxot kiüríti, majd lekéri a játéktól az új adatokat, a kiválaszott telepes alapján.
		 * Ezután feltölti a kapukat tartalmazó combo boxot.
		 */
		public void itemStateChanged(ItemEvent e) {
			gateCombo.removeAllItems();
			JComboBox<String> src = (JComboBox<String>) e.getSource();
			String settlerID = (String) src.getSelectedItem();
			if(settlerID == null || settlerID == "") {
				return;
			}
			String[] availableGates = game.getSettlersGates(settlerID);
			for(int i = 0; i < availableGates.length; i++) {
				gateCombo.addItem(availableGates[i]);
			}
		}
	}
	
	/**
	 * A lehelyezés kattintásának eseménykezelõ osztálya.
	 * A lehelyezés dialóguasablakának belsõ osztálya.
	 * @author Totya
	 */
	private class PlaceBuildingListener implements ActionListener {
		private DialogSelect2 parentDialog;
		private Controller controller;
		private JComboBox<String> comboSelected;
		private JComboBox<String> comboSettler;
		/**
		 * A lehelyezés eseménykezelõjének konstruktora.
		 * @param dialog: DialogSelect2: Az ablak, amit majd választás után be kell zárni.
		 * @param cont: Controller: A lehelyezést végrehajtó kontroller.
		 * @param selected: JComboBox<String>: A kapuválasztó comboBox.
		 * @param settler: JComboBox<String>: A telepesválasztó comboBox.
		 */
		public PlaceBuildingListener(DialogSelect2 dialog, Controller cont, JComboBox<String> selected, JComboBox<String> settler) {
			parentDialog = dialog;
			controller = cont;
			comboSelected = selected;
			comboSettler = settler;
		}
		/**
		 * Épület lehelyezésének eseménykezelése.
		 * Lekérdezi a szükséges paraméterket, majd végrehajtja, majd bezár.
		 */
		public void actionPerformed(ActionEvent e) {
			String selectedBuilding = (String) comboSelected.getSelectedItem();
			String settlerID = (String) comboSettler.getSelectedItem();
			//Ha bármi üres, vissza.
			if((selectedBuilding == null || selectedBuilding == "") && (settlerID == null || settlerID == "")) {
				return;
			}
			//Meghívom a kontrollert, majd bezárom.
			controller.handlePlace(settlerID, selectedBuilding);
			parentDialog.setVisible(false);
		}
	}
}
