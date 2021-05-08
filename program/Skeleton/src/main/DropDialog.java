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
 * Nyersanyag eldob�s�t ir�ny�t� dial�gusablak
 * @author Totya
 *
 */
public class DropDialog extends DialogSelect2 {
	
	/**
	 * Nyersanyag eldob�s�hoz sz�ks�ges dial�gusablak konstruktora.
	 * @param g: Game a j�t�kot vez�rl� oszt�ly.
	 * @param cont: A Controller p�ld�nya.
	 */
	public DropDialog(Game g, Controller cont) {
		super(g, cont);	
		this.labelTitle.setText("Drop Material with Settler");
		this.labelTop.setText("Material Id:");
		this.labelBottom.setText("Settler ID:");
		SelectSettlerListener settlerListener = new SelectSettlerListener(this.game, this.comboTop);
		this.comboBottom.addItemListener(settlerListener);
		DropListener dropListener = new DropListener(this, this.controller, this.comboTop, this.comboBottom);
		bSelect.addActionListener(dropListener);
	}	
	/**
	 * A dial�gusablak megjelen�t�se.
	 */
	public void show() {
		super.show();
		this.comboBottom.removeAllItems();
		String[] ids = game.getSettlerIds();
		for(int i = 0; i < ids.length; i++) {
			this.comboBottom.addItem(ids[i]);
		}
	}
	
	/**
	 * Telepes v�laszt�s�nak esem�nykezel� oszt�lya.
	 * Az eldob�s dial�gus bels� oszt�lya.
	 * @author Totya.
	 */
	private class SelectSettlerListener implements ItemListener {
		private Game game;
		private JComboBox<String> materialCombo;
		/**
		 * A Telepesv�laszt� esem�nykezel� konstruktora.
		 * @param g: Game: A j�t�k, amit� lek�rdezz�k az adatokat.
		 * @param gc: JComboBox<String> mc: A nyersanyagokat tartalmaz� comboBox.
		 */
		public SelectSettlerListener(Game g, JComboBox<String> mc) {
			game = g;
			materialCombo = mc;
		}
		/**
		 * Az eddigi nyersanyagokat tartalmaz� comboBoxot ki�r�ti, majd lek�ri a j�t�kt�l az �j adatokat, a kiv�laszott telepes alapj�n.
		 * Ezut�n felt�lti a nyersanyagokat tartalmaz� comboboxot.
		 */
		public void itemStateChanged(ItemEvent e) {
			materialCombo.removeAllItems();
			JComboBox<String> src = (JComboBox<String>) e.getSource();
			String settlerID = (String) src.getSelectedItem();
			String[] availableItems = game.getSettlersMaterials(settlerID);
			for(int i = 0; i < availableItems.length; i++) {
				materialCombo.addItem(availableItems[i]);
			}
		}
	}
	
	/**
	 * Az eldob�s kattint�s�nak esem�nykezel� oszt�lya.
	 * A eldob�s dial�guasablak�nak bels� oszt�lya.
	 * @author Totya
	 */
	private class DropListener implements ActionListener {
		private DialogSelect2 parentDialog;
		private Controller controller;
		private JComboBox<String> comboSelected;
		private JComboBox<String> comboSettler;
		/**
		 * A lehelyez�s esem�nykezel�j�nek konstruktora.
		 * @param dialog: DialogSelect2: Az ablak, amit majd v�laszt�s ut�n be kell z�rni.
		 * @param cont: Controller: Az eldob�st v�grehajt� kontroller.
		 * @param selected: JComboBox<String>: A nyersanyag comboBox.
		 * @param settler: JComboBox<String>: A telepesv�laszt� comboBox.
		 */
		public DropListener(DialogSelect2 dialog, Controller cont, JComboBox<String> selected, JComboBox<String> settler) {
			parentDialog = dialog;
			controller = cont;
			comboSelected = selected;
			comboSettler = settler;
		}
		/**
		 * Nyeranyag eldob�s�nak esem�nykezel�se
		 * Lek�rdezi a sz�ks�ges param�terket, majd v�grehajtja, majd bez�r.
		 */
		public void actionPerformed(ActionEvent e) {
			String selectedMaterial = (String) comboSelected.getSelectedItem();
			String settlerID = (String) comboSettler.getSelectedItem();
			//Ha b�rmi �res, vissza.
			if((selectedMaterial == null || selectedMaterial == "") && (settlerID == null || settlerID == "")) {
				return;
			}
			//Megh�vom a kontrollert, majd bez�rom.
			controller.handleDrop(settlerID, selectedMaterial);
			parentDialog.setVisible(false);
		}

	}
}