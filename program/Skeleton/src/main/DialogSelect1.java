package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
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
		this.setModal(true);
		this.setResizable(false);
		this.game = g;
		this.controller = cont;
		JPanel panel = new JPanel(new GridBagLayout());
		this.setPreferredSize(new Dimension(200, 200));
		panel.setPreferredSize(new Dimension(200, 200));
		GridBagConstraints c = new GridBagConstraints();
		c.ipady = 10;
		
		//Cím label elhelyezése.
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		labelTitle = new JLabel();
		labelStyleSet(labelTitle);
		panel.add(labelTitle, c);
		
		//Elsõ label lehelyezése.
		c.gridy = 1;
		labelTop = new JLabel();
		labelStyleSet(labelTop);
		panel.add(labelTop, c);
		
		//Combobox lehelyezése.
		c.gridy = 2;
		comboTop = new JComboBox<String>();
		comboBoxStyleSet(comboTop);
		panel.add(comboTop, c);
		c.ipady = 10;
		
		//Select gomb lehelyezése.
		c.gridy = 3;
		bSelect = new JButton("Select");
		buttonStyleSet(bSelect, 80, 30, 12);	
		panel.add(bSelect, c);
		
		//Panel elhelyezése a dialógusablakon.
		this.setLocationRelativeTo(null);
		this.add(panel);
		this.pack();
	}
	/**
	 * Gombok kinézetének beállítása.
	 * @param button: JButton: A gomb, aminek új kinézetet akarunk.
	 * @param width: int: Milyen széles legyen.
	 * @param height: int: Milyen magas legyen.
	 * @param fontsize: int: A betûk mekkorák legyenek.
	 */
	void buttonStyleSet(JButton button,int width, int height, int fontsize) {
		button.setBackground(Color.GRAY);
		button.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		button.setFont(new Font("Broadway", Font.BOLD, fontsize));
		button.setForeground(Color.BLACK);
		button.setPreferredSize(new Dimension(width, height));
	}
	
	/**
	 * Label-ek kinézetének módosítása.
	 * @param label: JLabel: A label, amit módosítani akarunk.
	 */
	void labelStyleSet(JLabel label) {
		label.setFont(new Font("Broadway", Font.BOLD, 12));
		label.setBackground(Color.GRAY);
		label.setForeground(Color.BLACK);
	}
	/**
	 * Combobox kinézetének módosítása.
	 * @param box: JComboBox: A combobox, aminek új kinézetet akarunk.
	 */
	void comboBoxStyleSet(JComboBox box) {
		box.setFont(new Font("Broadway", Font.BOLD, 12));
		box.setBackground(Color.GRAY);
		box.setForeground(Color.BLACK);
	}

}
