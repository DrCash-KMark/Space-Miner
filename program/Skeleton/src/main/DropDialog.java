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
		game = g;
		controller = cont;
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridheight = 6;
		c.gridwidth = 3;
		
		//Legelsõ label elhelyezése.
		c.gridy = 0;
		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		JLabel textLabel = new JLabel("Drop Material with Settler");
		panel.add(textLabel, c);
		
		//Második label elhelyezése.
		c.gridy = 1;
		c.gridx = 0;
		JLabel buildTypeLabel = new JLabel("Material Id:");
		panel.add(buildTypeLabel, c);
		
		//Nyersanyagválasztó combobox lehelyezése.
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.comboTop = new JComboBox<String>();
		panel.add(this.comboTop, c);
		
		//Második label elhelyezése.
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		JLabel settlerLabel = new JLabel("Settler ID:");
		panel.add(settlerLabel, c);
		
		//Combobox elhelyezése.
		c.gridy = 4;
		c.gridx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.comboBottom = new JComboBox<String>(game.getSettlerIDs());
		panel.add(this.comboTop, c);
		this.comboBottom.addItemListener(new ItemListener() {
			//Fúúú na itt hogy érem el a comboTop-ot?????
			public void itemStateChanged(ItemEvent e) {
				JComboBox<String> combo = (JComboBox<String>) e.getSource();
				String selectedID = (String) combo.getSelectedItem();
				//Ez itt nincs még meg, kéne egy fv, ami visszaadja egy adott telepes összes nyersanyagának azonosítóját.
				String[] gates = game.getSettlersMaterials(selectedID);
				
			}
		});
		//Select gomb elhelyezése.
		c.gridy = 5;
		c.gridx = 1;
		c.fill = GridBagConstraints.NONE;
		this.bSelect = new JButton("Select");
		//Ez itt nem lesz jó, kell egy saját actionListener osztály.
		//De itt lenne az eseménykezelés.
		this.bSelect.addActionListener(selectAction);
		panel.add(this.bSelect, c);
	}

	public void selectAction(ActionEvent e) {
		controller.handleDrop(getWarningString(), getName());
		this.setVisible(false);
	}
	
	/**
	 * A dialógusablak megjelenítése.
	 */
	public void show() {
		this.comboBottom.removeAllItems();
		String[] ids = game.getSettlerIDs();
		for(int i = 0; i < ids.length; i++) {
			this.comboBottom.addItem(ids[i]);
		}
	}
	

}
