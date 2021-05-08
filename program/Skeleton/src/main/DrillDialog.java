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
 * A f�r�s param�tereinek bek�r�s�hez haszn�lt dial�gusablak.
 * @author Totya
 */
public class DrillDialog extends DialogSelect1 {
	
	/**
	 * A DrillDialog konstruktora.
	 * Feliratozza a labeleket, valamint hozz�adja az esem�nykezel�st a sz�ks�ges elemekhez.
	 * @param g: A j�t�kot reprezent�l� oszt�ly, amit�l a sz�ks�ges adatokat lek�rheti.
	 * @param cont: A kontroller p�ld�nya, amin a kiadott parancshoz tartoz� kezel�ket h�vhatjuk meg.
	 */
	public DrillDialog(Game g, Controller cont) {
		super(g, cont);
		this.labelTitle.setText("Drill with Settler");
		this.labelTop.setText("Settler ID:");
		DrillSelectListener drillListener = new DrillSelectListener(this, this.controller, this.comboTop);
		this.bSelect.addActionListener(drillListener);
	}
	/**
	 * A dial�gusablak megjelen�t�s�n�l leszedem a comboBoxokr�l az eddigi elemeket.
	 * Ezut�n lek�rdezem a j�t�kt�l, hogy mib�l �l�nk �s az felrakom.
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
	 * A f�r�s esem�nykezel�s�nek priv�t oszt�lya.
	 * A DrillDialog bels� oszt�lya.
	 * @author Totya
	 */
	private class DrillSelectListener implements ActionListener{
		private DialogSelect1 parentDialog;
		private Controller controller;
		private JComboBox<String> box;
		/**
		 * Az f�r�s esem�nykezel�s�nek konstruktora.
		 * @param dial: DialogSelect1: A dial�gusablak, amit esetleg be kell majd z�rni.
		 * @param cont: Controller: A kontroller, amin f�ggv�nyt h�vja.
		 * @param b: JComboBox<String> A doboz, amib�l kiolvassa a param�tert.
		 */
		public DrillSelectListener(DialogSelect1 dial, Controller cont, JComboBox<String> b) {
			parentDialog = dial;
			controller = cont;
			box = b;
		}
		/**
		 * Amikor megnyomjuk a select gombot, f�runk a kiv�lasztott telepessel, majd bez�rjuk a dial�gusablakot.
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
