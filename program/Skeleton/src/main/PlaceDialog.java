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
 * Kapuk elhelyez�s�t lebonyol�t� p�rbesz�dablak oszt�lya.
 * @author Totya
 */

public class PlaceDialog extends DialogSelect2 {
	/**
	 * Kaput lehelyez� dial�gusablak konstruktora.
	 * Fleiratozza a dial�gusablakot, majd felveszi az esem�nykezel�ket.
	 * @param g: Game a j�t�kot vez�rl� oszt�ly.
	 * @param cont: A Controller p�ld�nya.
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
	 * A dial�gusablak megjelen�t�se.
	 * Felt�lti a settler-valszt� dobozt, az aktu�lis azonos�t�kkal.
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
	 * Telepes v�laszt�s�nak esem�nykezel� oszt�lya.
	 * A Lehelyez�s dial�gus bels� oszt�lya.
	 * @author Totya.
	 */
	private class SelectSettlerListener implements ItemListener {
		private Game game;
		private JComboBox<String> gateCombo;
		/**
		 * A Telepesv�laszt� esem�nykezel� konstruktora.
		 * @param g: Game: A j�t�k, amit� lek�rdezz�k az adatokat.
		 * @param gc: JComboBox<String> gc: A kapukat tartalmaz� comboBox.
		 */
		public SelectSettlerListener(Game g, JComboBox<String> gc) {
			game = g;
			gateCombo = gc;
		}
		/**
		 * Az eddigi kapukat tartalmaz� comboBoxot ki�r�ti, majd lek�ri a j�t�kt�l az �j adatokat, a kiv�laszott telepes alapj�n.
		 * Ezut�n felt�lti a kapukat tartalmaz� combo boxot.
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
	 * A lehelyez�s kattint�s�nak esem�nykezel� oszt�lya.
	 * A lehelyez�s dial�guasablak�nak bels� oszt�lya.
	 * @author Totya
	 */
	private class PlaceBuildingListener implements ActionListener {
		private DialogSelect2 parentDialog;
		private Controller controller;
		private JComboBox<String> comboSelected;
		private JComboBox<String> comboSettler;
		/**
		 * A lehelyez�s esem�nykezel�j�nek konstruktora.
		 * @param dialog: DialogSelect2: Az ablak, amit majd v�laszt�s ut�n be kell z�rni.
		 * @param cont: Controller: A lehelyez�st v�grehajt� kontroller.
		 * @param selected: JComboBox<String>: A kapuv�laszt� comboBox.
		 * @param settler: JComboBox<String>: A telepesv�laszt� comboBox.
		 */
		public PlaceBuildingListener(DialogSelect2 dialog, Controller cont, JComboBox<String> selected, JComboBox<String> settler) {
			parentDialog = dialog;
			controller = cont;
			comboSelected = selected;
			comboSettler = settler;
		}
		/**
		 * �p�let lehelyez�s�nek esem�nykezel�se.
		 * Lek�rdezi a sz�ks�ges param�terket, majd v�grehajtja, majd bez�r.
		 */
		public void actionPerformed(ActionEvent e) {
			String selectedBuilding = (String) comboSelected.getSelectedItem();
			String settlerID = (String) comboSettler.getSelectedItem();
			//Ha b�rmi �res, vissza.
			if((selectedBuilding == null || selectedBuilding == "") && (settlerID == null || settlerID == "")) {
				return;
			}
			//Megh�vom a kontrollert, majd bez�rom.
			controller.handlePlace(settlerID, selectedBuilding);
			parentDialog.setVisible(false);
		}
	}
}
