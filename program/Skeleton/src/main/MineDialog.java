package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A bányászás elvégzéséhez szükséges dialógusablak.
 * @author Totya
 *
 */
public class MineDialog extends DialogSelect1 {
	/**
	 * 
	 * @param g: A játékot reprezentáló osztály, amitõl a szükséges adatokat lekérheti.
	 * @param cont: A kontroller példánya, amin a kiadott parancshoz tartozó kezelõket hívhatjuk meg.
	 */
	public MineDialog(Game g, Controller cont) {
		super();
		game = g;
		controller = cont;
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridheight = 4;
		c.gridwidth = 3;
		
		//Legelsõ label elhelyezése.
		c.gridy = 0;
		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		JLabel textLabel = new JLabel("Mine with Settler");
		panel.add(textLabel, c);
		
		//Második label elhelyezése.
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		JLabel settlerLabel = new JLabel("Settler ID:");
		panel.add(settlerLabel, c);
		
		//Combobox elhelyezése.
		c.gridy = 2;
		c.gridx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.comboTop = new JComboBox<String>(game.getSettlerIDs());
		panel.add(this.comboTop, c);
		
		//Select gomb elhelyezése.
		c.gridy = 3;
		c.gridx = 1;
		c.fill = GridBagConstraints.NONE;
		this.bSelect = new JButton("Select");
		//Ez itt nem lesz jó, kell egy saját actionListener osztály.
		//De itt lenne az eseménykezelés.
		this.bSelect.addActionListener(selectAction);
		panel.add(this.bSelect, c);
	}
	/**
	 * A kattintás eseménykezelése.
	 */
	public void selectAction(ActionEvent e) {
		controller.handleMine((String) this.comboTop.getSelectedItem());
		this.setVisible(false);
	}
	
	/**
	 * A dialógusablak megjelenítése.
	 */
	public void show() {
		this.comboTop.removeAllItems();
		String[] ids = game.getSettlerIDs();
		for(int i = 0; i < ids.length; i++) {
			this.comboTop.addItem(ids[i]);
		}
	}

}
