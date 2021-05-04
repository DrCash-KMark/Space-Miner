package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
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
		game = g;
		controller = cont;
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridheight = 6;
		c.gridwidth = 3;
		
		//Legels� label elhelyez�se.
		c.gridy = 0;
		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		JLabel textLabel = new JLabel("Drop Material with Settler");
		panel.add(textLabel, c);
		
		//M�sodik label elhelyez�se.
		c.gridy = 1;
		c.gridx = 0;
		JLabel buildTypeLabel = new JLabel("Material Id:");
		panel.add(buildTypeLabel, c);
		
		//Nyersanyagv�laszt� combobox lehelyez�se.
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.comboTop = new JComboBox<String>();
		panel.add(this.comboTop, c);
		
		//M�sodik label elhelyez�se.
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		JLabel settlerLabel = new JLabel("Settler ID:");
		panel.add(settlerLabel, c);
		
		//Combobox elhelyez�se.
		c.gridy = 4;
		c.gridx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.comboBottom = new JComboBox<String>(game.getSettlerIDs());
		panel.add(this.comboTop, c);
		this.comboBottom.addItemListener(new ItemListener() {
			//F��� na itt hogy �rem el a comboTop-ot?????
			public void itemStateChanged(ItemEvent e) {
				JComboBox<String> combo = (JComboBox<String>) e.getSource();
				String selectedID = (String) combo.getSelectedItem();
				//Ez itt nincs m�g meg, k�ne egy fv, ami visszaadja egy adott telepes �sszes nyersanyag�nak azonos�t�j�t.
				String[] gates = game.getSettlersMaterials(selectedID);
				
			}
		});
		//Select gomb elhelyez�se.
		c.gridy = 5;
		c.gridx = 1;
		c.fill = GridBagConstraints.NONE;
		this.bSelect = new JButton("Select");
		//Ez itt nem lesz j�, kell egy saj�t actionListener oszt�ly.
		//De itt lenne az esem�nykezel�s.
		this.bSelect.addActionListener(selectAction);
		panel.add(this.bSelect, c);
	}

	public void selectAction(ActionEvent e) {
		controller.handleDrop(getWarningString(), getName());
		this.setVisible(false);
	}
	
	/**
	 * A dial�gusablak megjelen�t�se.
	 */
	public void show() {
		this.comboBottom.removeAllItems();
		String[] ids = game.getSettlerIDs();
		for(int i = 0; i < ids.length; i++) {
			this.comboBottom.addItem(ids[i]);
		}
	}
	

}
