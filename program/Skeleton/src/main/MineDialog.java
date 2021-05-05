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
 * A b�ny�sz�s elv�gz�s�hez sz�ks�ges dial�gusablak.
 * @author Totya
 */
public class MineDialog extends DialogSelect1 {
	/**
	 * A b�ny�sz�s dial�gusablak�nak konstruktora.
	 * Feliratozza a labelek-et, valamint hozz�adja az esem�nykezel�st.
	 * @param g: A j�t�kot reprezent�l� oszt�ly, amit�l a sz�ks�ges adatokat lek�rheti.
	 * @param cont: A kontroller p�ld�nya, amin a kiadott parancshoz tartoz� kezel�ket h�vhatjuk meg.
	 */
	public MineDialog(Game g, Controller cont) {
		super(g, cont);
		this.labelTitle.setText("Mine with Settler");
		this.labelTop.setText("Settler ID:");
		MineSelectListener mineListener = new MineSelectListener(this, this.controller, this.comboTop);
		this.bSelect.addActionListener(mineListener);
	}	
	/**
	 * A dial�gusablak megjelen�t�se.
	 * A dobozok r�gi �rt�keit elt�vol�tja, lek�rdezi az �jakat a j�t�kt�l.
	 */
	public void show() {
		this.comboTop.removeAllItems();
		String[] ids = game.getSettlerIDs();
		for(int i = 0; i < ids.length; i++) {
			this.comboTop.addItem(ids[i]);
		}
	}
	/**
	 * A f�r�s esem�nykezel�s�nek priv�t oszt�lya.
	 * A DrillDialog bels� oszt�lya.
	 * @author Totya
	 */
	private class MineSelectListener implements ActionListener{
		private DialogSelect1 parentDialog;
		private Controller controller;
		private JComboBox<String> box;
		/**
		 * Az b�ny�sz�s esem�nykezel�s�nek konstruktora.
		 * @param dial: DialogSelect1: A dial�gusablak, amit esetleg be kell majd z�rni.
		 * @param cont: Controller: A kontroller, amin f�ggv�nyt h�vja.
		 * @param b: JComboBox<String> A doboz, amib�l kiolvassa a param�tert.
		 */
		public MineSelectListener(DialogSelect1 dial, Controller cont, JComboBox<String> b) {
			parentDialog = dial;
			controller = cont;
			box = b;
		}
		/**
		 * Megnyomjuk a select gombot, b�ny�szunk, majd bez�rjuk.
		 */
		public void actionPerformed(ActionEvent e) {
			String settlerID = (String) box.getSelectedItem();
			if(settlerID == null || settlerID == "") {
				return;
			}
			controller.handleMine(settlerID);
			parentDialog.setVisible(false);
		}
	}	
}
