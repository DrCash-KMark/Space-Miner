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
 * Azon párbeszédablakok őse, ahol a felhasználó két paramétert ad meg.
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
	 * A kétparaméteres párbeszédablakok konstruktora.
	 * Gondoskodik az elemek elhelyezéséről.
	 * @param g: Game: A játékot reprezentáló paraméter.
	 * @param cont: A kontroller paramétere.
	 */
	public DialogSelect2(Game g, Controller cont) {
		super();
		this.game = g;
		this.controller = cont;
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridheight = 6;
		c.gridwidth = 3;
		
		//Cím label elhelyezése.
		c.gridy = 0;
		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		labelTitle = new JLabel();
		panel.add(labelTitle, c);
		
		//Legelső label elhelyezése.
		c.gridy = 1;
		c.gridx = 0;
		labelTop = new JLabel();
		panel.add(labelTop, c);
		
		//Felső comboBox lehelyezése.
		c.gridy = 2;
		this.comboTop = new JComboBox<String>();
		panel.add(this.comboTop, c);
		
		//Második label elhelyezése.
		c.gridy = 3;
		this.labelBottom = new JLabel();
		panel.add(labelBottom, c);
		
		//Alsó comboBox lehelyezése.
		c.gridy = 4;
		this.comboBottom = new JComboBox<String>();
		panel.add(comboBottom, c);
		
		//Select gomb elhelyezése.
		c.gridy = 5;
		c.gridx = 1;
		c.fill = GridBagConstraints.NONE;
		this.bSelect = new JButton("Select");
		panel.add(bSelect, c);
		
		//Panel elhelyezése a dialógusablakon.
		this.add(panel);
	}
	

}
