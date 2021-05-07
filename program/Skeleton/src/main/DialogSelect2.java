package main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Azon p�rbesz�dablakok �se, ahol a felhaszn�l� k�t param�tert ad meg.
 * @author Totya
 */
public class DialogSelect2 extends JDialog {
	protected Game game;
	protected Controller controller;
	protected JComboBox<String> comboTop;
	protected JComboBox<String> comboBottom;
	protected JButton bSelect;
	protected JLabel labelTitle;
	protected JLabel labelTop;
	protected JLabel labelBottom;
	
	/**
	 * A k�tparam�teres p�rbesz�dablakok konstruktora.
	 * Gondoskodik az elemek elhelyez�s�r�l.
	 * @param g: Game: A j�t�kot reprezent�l� param�ter.
	 * @param cont: A kontroller param�tere.
	 */
	public DialogSelect2(Game g, Controller cont) {
		super();
		this.setModal(true);
		this.game = g;
		this.controller = cont;
		JPanel panel = new JPanel(new GridBagLayout());
		this.setPreferredSize(new Dimension(200, 200));
		panel.setPreferredSize(new Dimension(200, 200));
		GridBagConstraints c = new GridBagConstraints();
		//c.gridheight = 60;
		//c.gridwidth = 30;
		
		//C�m label elhelyez�se.
		c.gridy = 0;
		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		labelTitle = new JLabel();
		panel.add(labelTitle, c);
		
		//Legels� label elhelyez�se.
		c.gridy = 1;
		c.gridx = 0;
		labelTop = new JLabel();
		panel.add(labelTop, c);
		
		//Fels� comboBox lehelyez�se.
		c.gridy = 2;
		this.comboTop = new JComboBox<String>();
		panel.add(this.comboTop, c);
		
		//M�sodik label elhelyez�se.
		c.gridy = 3;
		this.labelBottom = new JLabel();
		panel.add(labelBottom, c);
		
		//Als� comboBox lehelyez�se.
		c.gridy = 4;
		this.comboBottom = new JComboBox<String>();
		panel.add(comboBottom, c);
		
		//Select gomb elhelyez�se.
		c.gridy = 5;
		c.gridx = 1;
		c.fill = GridBagConstraints.NONE;
		this.bSelect = new JButton("Select");
		panel.add(bSelect, c);
		
		//Panel elhelyez�se a dial�gusablakon.
		this.setLocationRelativeTo(null);
		this.add(panel);
		this.pack();
	}
	

}
