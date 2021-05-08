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
 * Nyersanyag eldobását irányító dialógusablak
 * @author Totya
 *
 */
public class DropDialog extends DialogSelect2 {
	
	/**
	 * Nyersanyag eldobásához szükséges dialógusablak konstruktora.
	 * @param g: Game a játékot vezérlõ osztály.
	 * @param cont: A Controller példánya.
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
	 * A dialógusablak megjelenítése.
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
	 * Telepes választásának eseménykezelõ osztálya.
	 * Az eldobás dialógus belsõ osztálya.
	 * @author Totya.
	 */
	private class SelectSettlerListener implements ItemListener {
		private Game game;
		private JComboBox<String> materialCombo;
		/**
		 * A Telepesválasztó eseménykezelõ konstruktora.
		 * @param g: Game: A játék, amitõ lekérdezzük az adatokat.
		 * @param gc: JComboBox<String> mc: A nyersanyagokat tartalmazó comboBox.
		 */
		public SelectSettlerListener(Game g, JComboBox<String> mc) {
			game = g;
			materialCombo = mc;
		}
		/**
		 * Az eddigi nyersanyagokat tartalmazó comboBoxot kiüríti, majd lekéri a játéktól az új adatokat, a kiválaszott telepes alapján.
		 * Ezután feltölti a nyersanyagokat tartalmazó comboboxot.
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
	 * Az eldobás kattintásának eseménykezelõ osztálya.
	 * A eldobás dialóguasablakának belsõ osztálya.
	 * @author Totya
	 */
	private class DropListener implements ActionListener {
		private DialogSelect2 parentDialog;
		private Controller controller;
		private JComboBox<String> comboSelected;
		private JComboBox<String> comboSettler;
		/**
		 * A lehelyezés eseménykezelõjének konstruktora.
		 * @param dialog: DialogSelect2: Az ablak, amit majd választás után be kell zárni.
		 * @param cont: Controller: Az eldobást végrehajtó kontroller.
		 * @param selected: JComboBox<String>: A nyersanyag comboBox.
		 * @param settler: JComboBox<String>: A telepesválasztó comboBox.
		 */
		public DropListener(DialogSelect2 dialog, Controller cont, JComboBox<String> selected, JComboBox<String> settler) {
			parentDialog = dialog;
			controller = cont;
			comboSelected = selected;
			comboSettler = settler;
		}
		/**
		 * Nyeranyag eldobásának eseménykezelése
		 * Lekérdezi a szükséges paraméterket, majd végrehajtja, majd bezár.
		 */
		public void actionPerformed(ActionEvent e) {
			String selectedMaterial = (String) comboSelected.getSelectedItem();
			String settlerID = (String) comboSettler.getSelectedItem();
			//Ha bármi üres, vissza.
			if((selectedMaterial == null || selectedMaterial == "") && (settlerID == null || settlerID == "")) {
				return;
			}
			//Meghívom a kontrollert, majd bezárom.
			controller.handleDrop(settlerID, selectedMaterial);
			parentDialog.setVisible(false);
		}

	}
}