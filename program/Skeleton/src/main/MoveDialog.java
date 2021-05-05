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
 * A mozg�s elind�t�s�hoz haszn�lt dial�gusablak oszt�lya.
 * @author Totya
 *
 */
public class MoveDialog extends DialogSelect3 {
	
	/**
	 * A mozg�s dial�gusablalk�nak konstruktora.
	 * @param g: Game: A j�t�k, aki a sz�ks�ges adatokat t�rolja.
	 * @param cont: Controller: A kontroller, aki vez�rli a mozg�st.
	 */
	public MoveDialog(Game g, Controller cont) {
		super(g, cont);		
		this.labelTitle.setText("Move with Settler");
		this.labelTop.setText("Destination Type:");
		String[] destTypes = new String[] {"Asteroid", "StarGate"};
		for(int i = 0; i < destTypes.length; i++) {
			this.comboTop.addItem(destTypes[i]);
		}
		this.labelMiddle.setText("Id:");
		this.labelBottom.setText("Settler Id:");
		SettlerSelectListener settlerListener = new SettlerSelectListener(this.game, this.comboTop, this.comboMiddle);
		this.comboBottom.addItemListener(settlerListener);
		MoveListener moveListener = new MoveListener(this, this.controller, this.comboTop, this.comboMiddle, this.comboBottom);
		this.bSelect.addActionListener(moveListener);
	}
	
	/**
	 * Dial�gusablak megjelen�t�sekor levessz�k az eddigi telepeseket, majd felt�ltj�k az aktu�lis adatokkal az als� dobozt.
	 */
	public void show() {
		this.comboBottom.removeAllItems();
		this.comboMiddle.removeAllItems();
		String[] settlerIDs = game.getSettlerIds();
		for(int i = 0; i < settlerIDs.length; i++) {
			this.comboBottom.addItem(settlerIDs[i]);
		}
	}
	
	/**
	 * A telepest v�laszt� combobox esem�nykezel� oszt�lya.
	 * A mozg�st kezel� Dial�gusablak bels� oszt�lya.
	 * @author Totya
	 */
	private class SettlerSelectListener implements ItemListener{
		private Game game;
		private JComboBox<String> comboDestType;
		private JComboBox<String> comboDestID;
		
		/**
		 * A telepes v�laszt� combobox esem�nykezel�j�nek konstruktora.
		 * @param g: Game: A j�t�k, akit�l lek�rhetem az aktu�lis v�laszthat� adatokat.
		 * @param cdesttype: JComboBox<String>: A ComboBox, ami tartalmazza az �tic�l t�pus�t.
		 * @param cdestid: JComboBox<String>: A ComboBox, ami majd tartalmazza a v�lasztha� c�lok azonos�t�it.
		 */
		public SettlerSelectListener(Game g, JComboBox<String> cdesttype, JComboBox<String> cdestid) {
			game = g;
			comboDestType = cdesttype;
			comboDestID = cdestid;
		}
		
		/**
		 * Telepes v�laszt�sa eset�n fut le.
		 * A v�lasztott t�pust�l f�gg�en felt�lti az �tic�lok doboz�t a lehets�ges azonos�t�kkal.
		 */
		public void itemStateChanged(ItemEvent e) {
			comboDestID.removeAllItems();
			JComboBox<String> src = (JComboBox<String>) e.getSource();
			String settlerID = (String) src.getSelectedItem();
			String selectedDestType = (String) comboDestType.getSelectedItem();
			//Ha nincs kiv�lasztott utic�lt�pus, visszat�r�nk.
			if(selectedDestType == null || selectedDestType == "") {
				return;
			}
			//Ha nincs kiv�laszott telepes visszat�r�nk.
			if(settlerID == null || settlerID == "") {
				return;
			}
			//Megn�zz�k milyen �tic�l van kiv�lasztva, majd hogy hov� l�phet a kiv�lasztott telepes.
			if(selectedDestType == "Asteroid") {
				String[] destIDs = game.getSettlersNeighboursID(settlerID);
				for(int i = 0; i < destIDs.length; i++) {
					comboDestID.addItem(destIDs[i]);
				}
			}
			else if(selectedDestType == "StarGate") {
				String[] destIDs = game.getSettlersStargatesID(settlerID);
				for(int i = 0; i < destIDs.length; i++) {
					comboDestID.addItem(destIDs[i]);
				}
			}			
		}		
	}
	
	/**
	 * A mozg�st elind�t� gomb esem�nykezel� oszt�lya.
	 * A mozg�st kezel� Dial�gusablak bels� oszt�lya.
	 * @author Totya
	 */
	private class MoveListener implements ActionListener {
		private DialogSelect3 parentDialog;
		private Controller controller;
		private JComboBox<String> comboDestType;
		private JComboBox<String> comboDestID;
		private JComboBox<String> comboSettlerID;
		
		/**
		 * A mozg�st elind�t� gomb esem�nykezel� oszt�ly�nak konstruktora.
		 * @param dialog: DialogSelect3: A dial�gusablak, amit a v�g�n bek�ne z�rni.
		 * @param cont: Controller: A mozg�st v�grehajt� kontroller.
		 * @param destType: JComboBox<String>: A mozg�s c�lj�nak t�pus�t tartalmaz� combobox.
		 * @param destID: JComboBox<String>: Az �tic�l azonos�t�j�t tartalmaz� combobox.
		 * @param settlerID: JComboBox<String>: A mozg� telepes azonos�t�j�t tartalmaz� combobox.
		 */
		public MoveListener(DialogSelect3 dial, Controller cont, JComboBox<String> destType, JComboBox<String> destID, JComboBox<String> settlerID) {
			parentDialog = dial;
			controller = cont;
			comboDestType = destType;
			comboDestID = destID;
			comboSettlerID = settlerID;
		}
		/**
		 * A mozg�st v�grehajt� esem�nykezel�s.
		 * A v�g�n bez�rja a dial�gusablakot.
		 */
		public void actionPerformed(ActionEvent e) {
			String destType = (String) comboDestType.getSelectedItem();
			String destID = (String) comboDestID.getSelectedItem();
			String settlerID = (String) comboSettlerID.getSelectedItem();
			//Ha b�rmelyi kiv�lasztott param�ter �res lenne, visszat�r�nk.
			if (destType == null || destType == "") {
				return;
			}
			if (destID == null || destID == "") {
				return;
			}
			if (settlerID == null || settlerID == "") {
				return;
			}
			controller.handleMove(settlerID, destType, destID);
			parentDialog.setVisible(false);
		}
		
	}
	

}
