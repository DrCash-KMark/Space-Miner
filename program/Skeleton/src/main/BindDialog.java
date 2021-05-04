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
 * A Bind-hoz haszn�lt dial�gusablak.
 * @author Totya
 *
 */
public class BindDialog extends DialogSelect2 {
	
	/**
	 * A Bind-hoz haszn�lt dial�gusablak konstruktora.
	 * @param g: Game a j�t�kot reprezent�l� oszt�ly,
	 * @param cont: A j�t�k vez�rl�je.
	 */
	public BindDialog(Game g, Controller cont) {
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
		JLabel textLabel = new JLabel("Bind");
		panel.add(textLabel, c);
		
		//M�sodik label elhelyez�se.
		c.gridy = 1;
		c.gridx = 0;
		JLabel buildTypeLabel = new JLabel("Type");
		panel.add(buildTypeLabel, c);
		
		//T�pusv�laszt� combobox lehelyez�se.
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		String[] types = new String[] {"Setteler", "Asteroid"};
		this.comboTop = new JComboBox<String>(types);
		//Esem�nykezel� a fels� combo boxhoz, amivle megmondhatjuk milyen t�pust akarunk bindolni.
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
		
		//M�sodik label elhelyez�se.
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		JLabel settlerLabel = new JLabel("Settler ID:");
		panel.add(settlerLabel, c);
		
		//Combobox elhelyez�se.
		c.gridy = 4;
		c.gridx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.comboBottom = new JComboBox<String>();
		panel.add(this.comboTop, c);
		
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
		controller.handleBind(getWarningString(), getName());
		this.setVisible(false);
	}
	
	/**
	 * A dial�gusablak megjelen�t�se.
	 * Ebben az esetben, mikor �jra megnyitjuk, csak kiveszem az als� ablakb�l a r�gi �rt�keket.
	 */
	public void show() {
		this.comboBottom.removeAllItems();
	}

}
