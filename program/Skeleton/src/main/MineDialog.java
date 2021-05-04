package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A b�ny�sz�s elv�gz�s�hez sz�ks�ges dial�gusablak.
 * @author Totya
 *
 */
public class MineDialog extends DialogSelect1 {
	/**
	 * 
	 * @param g: A j�t�kot reprezent�l� oszt�ly, amit�l a sz�ks�ges adatokat lek�rheti.
	 * @param cont: A kontroller p�ld�nya, amin a kiadott parancshoz tartoz� kezel�ket h�vhatjuk meg.
	 */
	public MineDialog(Game g, Controller cont) {
		super();
		game = g;
		controller = cont;
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridheight = 4;
		c.gridwidth = 3;
		
		//Legels� label elhelyez�se.
		c.gridy = 0;
		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		JLabel textLabel = new JLabel("Mine with Settler");
		panel.add(textLabel, c);
		
		//M�sodik label elhelyez�se.
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		JLabel settlerLabel = new JLabel("Settler ID:");
		panel.add(settlerLabel, c);
		
		//Combobox elhelyez�se.
		c.gridy = 2;
		c.gridx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.comboTop = new JComboBox<String>(game.getSettlerIDs());
		panel.add(this.comboTop, c);
		
		//Select gomb elhelyez�se.
		c.gridy = 3;
		c.gridx = 1;
		c.fill = GridBagConstraints.NONE;
		this.bSelect = new JButton("Select");
		//Ez itt nem lesz j�, kell egy saj�t actionListener oszt�ly.
		//De itt lenne az esem�nykezel�s.
		this.bSelect.addActionListener(selectAction);
		panel.add(this.bSelect, c);
	}
	/**
	 * A kattint�s esem�nykezel�se.
	 */
	public void selectAction(ActionEvent e) {
		controller.handleMine((String) this.comboTop.getSelectedItem());
		this.setVisible(false);
	}
	
	/**
	 * A dial�gusablak megjelen�t�se.
	 */
	public void show() {
		this.comboTop.removeAllItems();
		String[] ids = game.getSettlerIDs();
		for(int i = 0; i < ids.length; i++) {
			this.comboTop.addItem(ids[i]);
		}
	}

}
