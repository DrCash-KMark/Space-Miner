package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

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
		JLabel textLabel = new JLabel("Build with Settler");
		panel.add(textLabel, c);
		
		//Második label elhelyezése.
		c.gridy = 1;
		c.gridx = 0;
		JLabel buildTypeLabel = new JLabel("Buildable:");
		panel.add(buildTypeLabel, c);
		
		//Épületválasztó combobox elhelyezése.
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		String epitheto[] = {"Robot", "Teleportkapu", "Bázis"};
		this.comboTop = new JComboBox<String>(epitheto);
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
	/**
	 * A kattintás eseménykezelése.
	 */
	public void selectAction(ActionEvent e) {
		controller.handleBuild((String) this.comboBottom.getSelectedItem(),(String) this.comboTop.getSelectedItem());
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
