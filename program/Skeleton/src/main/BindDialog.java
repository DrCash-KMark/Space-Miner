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
 * A Bind-hoz haszn�lt dial�gusablak.
 * @author Totya
 *
 */
public class BindDialog extends DialogSelect2 {
	
	/**
	 * A Bind-hoz haszn�lt dial�gusablak konstruktora.
	 * @param g: Game a j�t�kot reprezent�l� oszt�ly,
	 * @param cont: A j�t�k vez�rl�je.
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
	 * A dial�gusablak megjelen�t�se.
	 * Ebben az esetben, mikor �jra megnyitjuk, csak kiveszem az als� ablakb�l a r�gi �rt�keket.
	 * �jra ki kell majd v�lasztani a fajt�t.
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
	 * A bind m�velet esem�nykezel� oszt�lya.
	 * @author Totya
	 */
	private class BindListener implements ActionListener {
		private DialogSelect2 parentDialog;
		private Controller controller;
		private JComboBox<String> comboType;
		private JComboBox<String> comboSelected;
		/**
		 * A bind m�velet esem�nykezel�j�nek konstruktora.
		 * @param dial: DialogSelect2: A dial�gusablak, amit a m�velet v�g�n be kell z�rni.
		 * @param cont: Controller: A vez�rl� ami a bind m�veletet v�gzi.
		 * @param type: JComboBox<String>: A t�pust kiv�laszt� combobox, amit bindolunk.
		 * @param selected: Az azonos�t� amit bindolunk.
		 */
		public BindListener(DialogSelect2 dial, Controller cont, JComboBox<String> type, JComboBox<String> selected) {
			parentDialog = dial;
			controller = cont;
			comboType = type;
			comboSelected = selected;
		}
		/**
		 * A bindol�s esem�nye.
		 * @param e
		 */
		public void actionPerformed(ActionEvent e) {
			String selectedType = (String) comboType.getSelectedItem();
			String selectedID = (String) comboSelected.getSelectedItem();
			//Ha b�rmi �res, vissza.
			if((selectedType == null || selectedType == "") || (selectedID == null || selectedID == "")) {
				return;
			}
			controller.handleBind(selectedID, selectedType);
			parentDialog.setVisible(false);
		}	
	}
	
	/**
	 * Bindolt t�pus v�laszt�s�nak esem�nykezel� oszt�lya.
	 * A bind dial�gus bels� oszt�lya.
	 * @author Totya.
	 */
	private class SelectTypeListener implements ItemListener {
		private Game game;
		private JComboBox<String> selectableCombo;
		/**
		 * A T�pusv�laszt� esem�nykezel� konstruktora.
		 * @param g: Game: A j�t�k, amit� lek�rdezz�k az adatokat.
		 * @param gc: JComboBox<String> tc: A t�pusokat tartalmaz� comboBox.
		 */
		public SelectTypeListener(Game g, JComboBox<String> tc) {
			game = g;
			selectableCombo = tc;
		}
		/**
		 * Az eddigi azonos�t�kat tartalmaz� comboBoxot ki�r�ti, majd lek�ri a j�t�kt�l az �j adatokat, a kiv�laszott t�pus alapj�n.
		 * Ezut�n felt�lti az azonos�t�kat tartalmaz� comboboxot.
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
