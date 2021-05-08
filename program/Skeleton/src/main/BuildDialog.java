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
 * Építéshez használt dialógusablak osztálya.
 * @author Totya
 *
 */
public class BuildDialog extends DialogSelect2 {
	/**
	 * Építkezõ dialógusablak konstruktora.
	 * @param g: Game a játékot vezérlõ osztály.
	 * @param cont: A Controller példánya.
	 */
	public BuildDialog(Game g, Controller cont) {
		super(g, cont);
		//Cím feliratozása
		this.labelTitle.setText("Build with Settler");
		//Elsõ label felirata
		this.labelTop.setText("Buildable:");
		//Elsõ combobox elemei
		//Mivel állandóak, ezért a konstruktorban adom meg az elemeket.
		String[] buildings = new String[] {"Robot", "StarGate", "Base"};
		for(int i = 0; i < buildings.length; i++) {
			this.comboTop.addItem(buildings[i]);
		}
		//Alsó label felirata.
		this.labelBottom.setText("Settler ID:");
		BuildSelectListener buildListener = new BuildSelectListener(this, this.controller, this.comboTop, this.comboBottom);
		this.bSelect.addActionListener(buildListener);
	}
	/**
	 * A dialógusablak megjelenítése.
	 * Az alsó comboboxbol elveszi az elemeket, majd felteszi az újakat.
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
	 * Az építés eseménykezelésének privát osztálya.
	 * @author Totya
	 */
	private class BuildSelectListener implements ActionListener{
		private DialogSelect2 parentDialog;
		private Controller controller;
		private JComboBox<String> comboBuilding;
		private JComboBox<String> comboSettler;
		/**
		 * Az építés eseménykezelõ osztály konsturktora.
		 * @param dialog: DialogSelect2: A dialógusablak, amit esetleg be kell csukni.
		 * @param cont: Controller: A kontorller, amin meghívjuk az építést.
		 * @param building: JComboBox<String>: A ComboBox, amiben az épületet választjuk ki.
		 * @param settler: JComboBox<String>: A ComboBox, amiben a telepest választjuk ki.
		 */
		public BuildSelectListener(DialogSelect2 dialog, Controller cont, JComboBox<String> building, JComboBox<String> settler) {
			parentDialog = dialog;
			controller = cont;
			comboBuilding = building;
			comboSettler = settler;
		}
		public void actionPerformed(ActionEvent e) {
			//Lekérjük a comboBoxokból a paramétereket.
			String buildingType = (String) comboBuilding.getSelectedItem();
			String settlerID = (String) comboSettler.getSelectedItem();
			//Ha bármi üres, vissza.
			if((buildingType == null || buildingType == "") && (settlerID == null || settlerID == "")) {
				return;
			}
			//Meghívom a kontrollert, majd bezárom.
			controller.handleBuild(settlerID, buildingType);
			parentDialog.setVisible(false);
		}
	}
}
