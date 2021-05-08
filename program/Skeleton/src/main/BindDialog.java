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
 * A Bind-hoz használt dialógusablak.
 * @author Totya
 *
 */
public class BindDialog extends DialogSelect2 {
	
	/**
	 * A Bind-hoz használt dialógusablak konstruktora.
	 * @param g: Game a játékot reprezentáló osztály,
	 * @param cont: A játék vezérlõje.
	 */
	public BindDialog(Game g, Controller cont) {
		super(g, cont);
		this.labelTitle.setText("Bind");
		this.labelTop.setText("Type");
		String[] types = new String[] {"Settler", "Asteroid"};
		for(int i= 0; i < types.length; i++) {
			comboTop.addItem(types[i]);
		}
		this.labelBottom.setText("ID:");
		SelectTypeListener typeChangeListener = new SelectTypeListener(this.game, this.comboBottom);
		this.comboTop.addItemListener(typeChangeListener);
		BindListener bindListener = new BindListener(this, this.controller, this.comboTop, this.comboBottom);
		this.bSelect.addActionListener(bindListener);
	}	
	/**
	 * A dialógusablak megjelenítése.
	 * Ebben az esetben, mikor újra megnyitjuk, csak kiveszem az alsó ablakból a régi értékeket.
	 * Újra ki kell majd választani a fajtát.
	 */
	public void show() {
		this.comboBottom.removeAllItems();
		this.comboTop.setSelectedItem("Settler");
		String[] availableSettlers = game.getSettlerIds();
		for(int i = 0; i < availableSettlers.length; i++) {
			comboBottom.addItem(availableSettlers[i]);
		}
		super.show();
	}
	
	/**
	 * A bind mûvelet eseménykezelõ osztálya.
	 * @author Totya
	 */
	private class BindListener implements ActionListener {
		private DialogSelect2 parentDialog;
		private Controller controller;
		private JComboBox<String> comboType;
		private JComboBox<String> comboSelected;
		/**
		 * A bind mûvelet eseménykezelõjének konstruktora.
		 * @param dial: DialogSelect2: A dialógusablak, amit a mûvelet végén be kell zárni.
		 * @param cont: Controller: A vezérlõ ami a bind mûveletet végzi.
		 * @param type: JComboBox<String>: A típust kiválasztó combobox, amit bindolunk.
		 * @param selected: Az azonosító amit bindolunk.
		 */
		public BindListener(DialogSelect2 dial, Controller cont, JComboBox<String> type, JComboBox<String> selected) {
			parentDialog = dial;
			controller = cont;
			comboType = type;
			comboSelected = selected;
		}
		/**
		 * A bindolás eseménye.
		 * @param e
		 */
		public void actionPerformed(ActionEvent e) {
			String selectedType = (String) comboType.getSelectedItem();
			String selectedID = (String) comboSelected.getSelectedItem();
			//Ha bármi üres, vissza.
			if((selectedType == null || selectedType == "") || (selectedID == null || selectedID == "")) {
				return;
			}
			controller.handleBind(selectedID, selectedType);
			parentDialog.setVisible(false);
		}	
	}
	
	/**
	 * Bindolt típus választásának eseménykezelõ osztálya.
	 * A bind dialógus belsõ osztálya.
	 * @author Totya.
	 */
	private class SelectTypeListener implements ItemListener {
		private Game game;
		private JComboBox<String> selectableCombo;
		/**
		 * A Típusválasztó eseménykezelõ konstruktora.
		 * @param g: Game: A játék, amitõ lekérdezzük az adatokat.
		 * @param gc: JComboBox<String> tc: A típusokat tartalmazó comboBox.
		 */
		public SelectTypeListener(Game g, JComboBox<String> tc) {
			game = g;
			selectableCombo = tc;
		}
		/**
		 * Az eddigi azonosítókat tartalmazó comboBoxot kiüríti, majd lekéri a játéktól az új adatokat, a kiválaszott típus alapján.
		 * Ezután feltölti az azonosítókat tartalmazó comboboxot.
		 */
		public void itemStateChanged(ItemEvent e) {
			selectableCombo.removeAllItems();
			JComboBox<String> src = (JComboBox<String>) e.getSource();
			String selectedType = (String) src.getSelectedItem();
			if(selectedType.equals("Asteroid")) {
				String[] availableAsteroids = game.getAsteroidIds();
				for(int i = 0; i < availableAsteroids.length; i++) {
					selectableCombo.addItem(availableAsteroids[i]);
				}
			}
			else if(selectedType.equals("Settler")) {
				String[] availableSettlers = game.getSettlerIds();
				for(int i = 0; i < availableSettlers.length; i++) {
					selectableCombo.addItem(availableSettlers[i]);
				}
			}
			selectableCombo.revalidate();
		}
	}
}
