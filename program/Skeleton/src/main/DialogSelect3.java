package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Azon p�rbesz�dablakok absztrakt �se, ahol a felhaszn�l� h�rom param�tert ad meg a m�velet sz�m�ra.
 * @author Totya
 */
public class DialogSelect3 extends JDialog {
	protected Game game;
	protected Controller controller;
	protected JComboBox<String> comboTop;
	protected JComboBox<String> comboMiddle;
	protected JComboBox<String> comboBottom;
	protected JButton bSelect;
	protected JLabel labelTitle;
	protected JLabel labelTop;
	protected JLabel labelMiddle;
	protected JLabel labelBottom;
	
	public DialogSelect3(Game g, Controller cont) {
		game = g;
		controller = cont;
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridheight = 8;
		c.gridwidth = 3;
		
		//C�m label lehelyez�se
		c.gridy = 0;
		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		labelTitle = new JLabel();
		panel.add(labelTitle, c);
		
		//Els� label lehelyez�se
		c.gridy = 1;
		c.gridx = 0;
		labelTop = new JLabel();
		panel.add(labelTop, c);
		
		//Els� combobox lehelyez�se
		c.gridy = 2;
		comboTop = new JComboBox<String>();
		panel.add(comboTop, c);
		
		//M�sodik label elhelyez�se.
		c.gridy = 3;
		this.labelMiddle = new JLabel();
		panel.add(labelMiddle, c);
		
		//Als� comboBox lehelyez�se.
		c.gridy = 4;
		this.comboMiddle = new JComboBox<String>();
		panel.add(comboMiddle, c);
		
		//M�sodik label elhelyez�se.
		c.gridy = 5;
		this.labelBottom = new JLabel();
		panel.add(labelBottom, c);
		
		//Als� comboBox lehelyez�se.
		c.gridy = 6;
		this.comboBottom = new JComboBox<String>();
		panel.add(comboBottom, c);
		
		//Select gomb elhelyez�se.
		c.gridy = 7;
		c.gridx = 1;
		c.fill = GridBagConstraints.NONE;
		this.bSelect = new JButton("Select");
		panel.add(bSelect, c);
		
		//Panel elhelyez�se a dial�gusablakon.
		this.add(panel);
	}

}
