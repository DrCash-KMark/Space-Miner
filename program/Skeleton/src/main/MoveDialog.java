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
 * A mozgás elindításához használt dialógusablak osztálya.
 * @author Totya
 *
 */
public class MoveDialog extends DialogSelect3 {
	
	/**
	 * A mozgás dialógusablalkának konstruktora.
	 * @param g: Game: A játék, aki a szükséges adatokat tárolja.
	 * @param cont: Controller: A kontroller, aki vezérli a mozgást.
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
	 * Dialógusablak megjelenítésekor levesszük az eddigi telepeseket, majd feltöltjük az aktuális adatokkal az alsó dobozt.
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
	 * A telepest választó combobox eseménykezelõ osztálya.
	 * A mozgást kezelõ Dialógusablak belsõ osztálya.
	 * @author Totya
	 */
	private class SettlerSelectListener implements ItemListener{
		private Game game;
		private JComboBox<String> comboDestType;
		private JComboBox<String> comboDestID;
		
		/**
		 * A telepes választó combobox eseménykezelõjének konstruktora.
		 * @param g: Game: A játék, akitõl lekérhetem az aktuális választható adatokat.
		 * @param cdesttype: JComboBox<String>: A ComboBox, ami tartalmazza az úticél típusát.
		 * @param cdestid: JComboBox<String>: A ComboBox, ami majd tartalmazza a választhaó célok azonosítóit.
		 */
		public SettlerSelectListener(Game g, JComboBox<String> cdesttype, JComboBox<String> cdestid) {
			game = g;
			comboDestType = cdesttype;
			comboDestID = cdestid;
		}
		
		/**
		 * Telepes választása esetén fut le.
		 * A választott típustól függõen feltölti az úticélok dobozát a lehetséges azonosítókkal.
		 */
		public void itemStateChanged(ItemEvent e) {
			comboDestID.removeAllItems();
			JComboBox<String> src = (JComboBox<String>) e.getSource();
			String settlerID = (String) src.getSelectedItem();
			String selectedDestType = (String) comboDestType.getSelectedItem();
			//Ha nincs kiválasztott uticéltípus, visszatérünk.
			if(selectedDestType == null || selectedDestType == "") {
				return;
			}
			//Ha nincs kiválaszott telepes visszatérünk.
			if(settlerID == null || settlerID == "") {
				return;
			}
			//Megnézzük milyen úticél van kiválasztva, majd hogy hová léphet a kiválasztott telepes.
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
	 * A mozgást elindító gomb eseménykezelõ osztálya.
	 * A mozgást kezelõ Dialógusablak belsõ osztálya.
	 * @author Totya
	 */
	private class MoveListener implements ActionListener {
		private DialogSelect3 parentDialog;
		private Controller controller;
		private JComboBox<String> comboDestType;
		private JComboBox<String> comboDestID;
		private JComboBox<String> comboSettlerID;
		
		/**
		 * A mozgást elindító gomb eseménykezelõ osztályának konstruktora.
		 * @param dialog: DialogSelect3: A dialógusablak, amit a végén bekéne zárni.
		 * @param cont: Controller: A mozgást végrehajtó kontroller.
		 * @param destType: JComboBox<String>: A mozgás céljának típusát tartalmazó combobox.
		 * @param destID: JComboBox<String>: Az úticél azonosítóját tartalmazó combobox.
		 * @param settlerID: JComboBox<String>: A mozgó telepes azonosítóját tartalmazó combobox.
		 */
		public MoveListener(DialogSelect3 dial, Controller cont, JComboBox<String> destType, JComboBox<String> destID, JComboBox<String> settlerID) {
			parentDialog = dial;
			controller = cont;
			comboDestType = destType;
			comboDestID = destID;
			comboSettlerID = settlerID;
		}
		/**
		 * A mozgást végrehajtó eseménykezelés.
		 * A végén bezárja a dialógusablakot.
		 */
		public void actionPerformed(ActionEvent e) {
			String destType = (String) comboDestType.getSelectedItem();
			String destID = (String) comboDestID.getSelectedItem();
			String settlerID = (String) comboSettlerID.getSelectedItem();
			//Ha bármelyi kiválasztott paraméter üres lenne, visszatérünk.
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
