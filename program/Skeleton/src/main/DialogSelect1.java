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
 * Azon párbeszédablakok õse, ahol a felhasználó csak egyetlen paramétert szeretne kiválasztani.
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
	 * Az egyparaméteres párbeszédablakok konstruktora.
	 * Gondoskodik az elemek elhelyezésérõl.
	 * @param g: Game: A játékot reprezentáló paraméter.
	 * @param cont: A kontroller paramétere.
	 */
	public DialogSelect1(Game g, Controller cont) {
		super();
		game = g;
		controller = cont;
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridheight = 4;
		c.gridwidth = 3;
		
		//Cím label elhelyezése.
		c.gridy = 0;
		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		labelTitle = new JLabel();
		panel.add(labelTitle, c);
		
		//Elsõ label lehelyezése.
		c.gridy = 1;
		c.gridx = 0;
		labelTop = new JLabel();
		panel.add(labelTop, c);
		
		//Combobox lehelyezése.
		c.gridy = 2;
		comboTop = new JComboBox<String>();
		panel.add(comboTop, c);
		
		//Select gomb lehelyezése.
		c.gridy = 3;
		c.gridx = 1;
		c.fill = GridBagConstraints.NONE;
		bSelect = new JButton("Select");
		panel.add(bSelect, c);
		
		//Panel elhelyezése a dialógusablakon.
		this.add(panel);
		
	}

}
