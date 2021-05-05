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
 * Azon párbeszédablakok absztrakt õse, ahol a felhasználó hérom paramétert ad meg a mûvelet számára.
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
		
		//Cím label lehelyezése
		c.gridy = 0;
		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		labelTitle = new JLabel();
		panel.add(labelTitle, c);
		
		//Elsõ label lehelyezése
		c.gridy = 1;
		c.gridx = 0;
		labelTop = new JLabel();
		panel.add(labelTop, c);
		
		//Elsõ combobox lehelyezése
		c.gridy = 2;
		comboTop = new JComboBox<String>();
		panel.add(comboTop, c);
		
		//Második label elhelyezése.
		c.gridy = 3;
		this.labelMiddle = new JLabel();
		panel.add(labelMiddle, c);
		
		//Alsó comboBox lehelyezése.
		c.gridy = 4;
		this.comboMiddle = new JComboBox<String>();
		panel.add(comboMiddle, c);
		
		//Második label elhelyezése.
		c.gridy = 5;
		this.labelBottom = new JLabel();
		panel.add(labelBottom, c);
		
		//Alsó comboBox lehelyezése.
		c.gridy = 6;
		this.comboBottom = new JComboBox<String>();
		panel.add(comboBottom, c);
		
		//Select gomb elhelyezése.
		c.gridy = 7;
		c.gridx = 1;
		c.fill = GridBagConstraints.NONE;
		this.bSelect = new JButton("Select");
		panel.add(bSelect, c);
		
		//Panel elhelyezése a dialógusablakon.
		this.add(panel);
	}

}
