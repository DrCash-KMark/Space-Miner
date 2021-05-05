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
 * Azon p�rbesz�dablakok �se, ahol a felhaszn�l� csak egyetlen param�tert szeretne kiv�lasztani.
 * @author Totya
 */
public class DialogSelect1 extends JDialog {
	protected Game game;
	protected Controller controller;
	protected JComboBox<String> comboTop;
	protected JButton bSelect;
	protected JLabel labelTitle;
	protected JLabel labelTop;
	
	/**
	 * Az egyparam�teres p�rbesz�dablakok konstruktora.
	 * Gondoskodik az elemek elhelyez�s�r�l.
	 * @param g: Game: A j�t�kot reprezent�l� param�ter.
	 * @param cont: A kontroller param�tere.
	 */
	public DialogSelect1(Game g, Controller cont) {
		super();
		game = g;
		controller = cont;
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridheight = 4;
		c.gridwidth = 3;
		
		//C�m label elhelyez�se.
		c.gridy = 0;
		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		labelTitle = new JLabel();
		panel.add(labelTitle, c);
		
		//Els� label lehelyez�se.
		c.gridy = 1;
		c.gridx = 0;
		labelTop = new JLabel();
		panel.add(labelTop, c);
		
		//Combobox lehelyez�se.
		c.gridy = 2;
		comboTop = new JComboBox<String>();
		panel.add(comboTop, c);
		
		//Select gomb lehelyez�se.
		c.gridy = 3;
		c.gridx = 1;
		c.fill = GridBagConstraints.NONE;
		bSelect = new JButton("Select");
		panel.add(bSelect, c);
		
		//Panel elhelyez�se a dial�gusablakon.
		this.add(panel);
		
	}

}
