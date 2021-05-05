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
 * A mozgás elindításához használt dialógusablak osztálya.
 * @author Totya
 *
 */
public class MoveDialog extends DialogSelect3 {
	
	public MoveDialog(Game g, Controller cont) {
		game = g;
		controller = cont;
		this.comboMiddle = new JComboBox<String>();
		this.comboBottom = new JComboBox<String>();
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridheight = 8;
		c.gridwidth = 3;
		
		//Címet kijelölõ label.
		c.gridy = 0;
		c.gridx = 1;
		JLabel titleLabel = new JLabel("Move with Settler");
		panel.add(titleLabel, c);
		
		//Elsõ combobox label-je
		c.gridy = 1;
		c.gridx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		JLabel firstLabel = new JLabel("Destination Type:");
		panel.add(firstLabel, c);
		
		//Elsõ combobox
		c.gridy = 2;
		String[] destTypes = new String[] {"Asteroid", "StarGate"};
		this.comboTop = new JComboBox<String>(destTypes);
		this.comboTop.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				JComboBox<String> combo = (JComboBox<String>) e.getSource();
				String selectedType = (String) combo.getSelectedItem();
				//Itt a kiválasztott célnak megfelelõen feltöltöm a középsõ comboboxot.
				if(selectedType == "Asteroid") {
					this.comboMiddle.removeAllItems();
					String[] ids = game.getAsteroidIds();
					for(int i = 0; i < ids.length; i++) {
						this.comboMiddle.addItem(ids[i]);
					}
				}
				else if(selectedType == "StarGate") {
					this.comboMiddle.removeAllItems();
					String[] ids = game.getStarGateIds();
					for(int i = 0; i < ids.length; i++) {
						this.comboMiddle.addItem(ids[i]);
					}
				}
			}
		});
		panel.add(this.comboTop, c);
		
		//Második combobox label-je
		c.gridy = 3;
		JLabel secondLabel = new JLabel("Id:");
		panel.add(secondLabel, c);
		
		//Második combobox
		c.gridy = 4;
		panel.add(comboMiddle, c);
		
		//Harmadik combobox label-je
		c.gridy = 5;
		JLabel thirdLabel = new JLabel("Settler Id:");
		panel.add(thirdLabel, c);
		
		//Harmadik combobox
		c.gridy = 6;
		this.comboBottom = new JComboBox<String>(game.getSettlerIDs());
		panel.add(this.comboBottom, c);
		
		//Select gomb elhelyezése.
		c.gridy = 7;
		c.gridx = 1;
		c.fill = GridBagConstraints.NONE;
		this.bSelect = new JButton("Select");
		//Ez itt nem lesz jó, kell egy saját actionListener osztály.
		//De itt lenne az eseménykezelés.
		this.bSelect.addActionListener(selectAction);
		panel.add(this.bSelect, c);	
	}
	
	//Select gomb lenyomása.
	public void selectAction(ActionEvent e) {
		controller.handleMove((String) this.comboBottom.getSelectedItem(), (String) this.comboMiddle.getSelectedItem(), (String) this.comboTop.getSelectedItem());
		this.setVisible(false);		
	}

}
