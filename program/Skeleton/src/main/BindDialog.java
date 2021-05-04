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
 * A Bind-hoz használt dialógusablak.
 * @author Totya
 *
 */
public class BindDialog extends DialogSelect2 {
	
	/**
	 * A Bind-hoz használt dialógusablak konstruktora.
	 * @param g: Game a játékot reprezentáló osztály,
	 * @param cont: A játék vezérlõje.
	 */
	public BindDialog(Game g, Controller cont) {
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
		JLabel textLabel = new JLabel("Bind");
		panel.add(textLabel, c);
		
		//Második label elhelyezése.
		c.gridy = 1;
		c.gridx = 0;
		JLabel buildTypeLabel = new JLabel("Type");
		panel.add(buildTypeLabel, c);
		
		//Típusválasztó combobox lehelyezése.
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		String[] types = new String[] {"Setteler", "Asteroid"};
		this.comboTop = new JComboBox<String>(types);
		//Eseménykezelõ a felsõ combo boxhoz, amivle megmondhatjuk milyen típust akarunk bindolni.
		this.comboTop.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				JComboBox<String> combo = (JComboBox<String>) e.getSource();
				String selectedType = (String) combo.getSelectedItem();
				if(selectedType == "Settler") {
					this.comboBottom.removeAllItems();
					String[] ids = game.getSettlerIDs();
					for(int i = 0; i < ids.length; i++) {
						this.comboBottom.addItem(ids[i]);
					}
				}
				else if(selectedType == "Asteroid") {
					this.comboBottom.removeAllItems();
					String[] ids = game.getAsteroidIDs();
					for(int i = 0; i < ids.length; i++) {
						this.comboBottom.addItem(ids[i]);
					}
				}
			}
		});
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
		this.comboBottom = new JComboBox<String>();
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

	public void selectAction(ActionEvent e) {
		controller.handleBind(getWarningString(), getName());
		this.setVisible(false);
	}
	
	/**
	 * A dialógusablak megjelenítése.
	 * Ebben az esetben, mikor újra megnyitjuk, csak kiveszem az alsó ablakból a régi értékeket.
	 */
	public void show() {
		this.comboBottom.removeAllItems();
	}

}
