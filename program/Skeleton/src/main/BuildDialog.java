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
 * �p�t�shez haszn�lt dial�gusablak oszt�lya.
 * @author Totya
 *
 */
public class BuildDialog extends DialogSelect2 {
	/**
	 * �p�tkez� dial�gusablak konstruktora.
	 * @param g: Game a j�t�kot vez�rl� oszt�ly.
	 * @param cont: A Controller p�ld�nya.
	 */
	public BuildDialog(Game g, Controller cont) {
		super(g, cont);
		//C�m feliratoz�sa
		this.labelTitle.setText("Build with Settler");
		//Els� label felirata
		this.labelTop.setText("Buildable:");
		//Els� combobox elemei
		//Mivel �lland�ak, ez�rt a konstruktorban adom meg az elemeket.
		String[] buildings = new String[] {"Robot", "StarGate", "Base"};
		for(int i = 0; i < buildings.length; i++) {
			this.comboTop.addItem(buildings[i]);
		}
		//Als� label felirata.
		this.labelBottom.setText("Settler ID:");
		BuildSelectListener buildListener = new BuildSelectListener(this, this.controller, this.comboTop, this.comboBottom);
		this.bSelect.addActionListener(buildListener);
	}
	/**
	 * A dial�gusablak megjelen�t�se.
	 * Az als� comboboxbol elveszi az elemeket, majd felteszi az �jakat.
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
	 * Az �p�t�s esem�nykezel�s�nek priv�t oszt�lya.
	 * @author Totya
	 */
	private class BuildSelectListener implements ActionListener{
		private DialogSelect2 parentDialog;
		private Controller controller;
		private JComboBox<String> comboBuilding;
		private JComboBox<String> comboSettler;
		/**
		 * Az �p�t�s esem�nykezel� oszt�ly konsturktora.
		 * @param dialog: DialogSelect2: A dial�gusablak, amit esetleg be kell csukni.
		 * @param cont: Controller: A kontorller, amin megh�vjuk az �p�t�st.
		 * @param building: JComboBox<String>: A ComboBox, amiben az �p�letet v�lasztjuk ki.
		 * @param settler: JComboBox<String>: A ComboBox, amiben a telepest v�lasztjuk ki.
		 */
		public BuildSelectListener(DialogSelect2 dialog, Controller cont, JComboBox<String> building, JComboBox<String> settler) {
			parentDialog = dialog;
			controller = cont;
			comboBuilding = building;
			comboSettler = settler;
		}
		public void actionPerformed(ActionEvent e) {
			//Lek�rj�k a comboBoxokb�l a param�tereket.
			String buildingType = (String) comboBuilding.getSelectedItem();
			String settlerID = (String) comboSettler.getSelectedItem();
			//Ha b�rmi �res, vissza.
			if((buildingType == null || buildingType == "") && (settlerID == null || settlerID == "")) {
				return;
			}
			//Megh�vom a kontrollert, majd bez�rom.
			controller.handleBuild(settlerID, buildingType);
			parentDialog.setVisible(false);
		}
	}
}
