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
		this.setResizable(false);
		this.setModal(true);
		JPanel panel = new JPanel(new GridBagLayout());
		this.setPreferredSize(new Dimension(400, 300));
		panel.setPreferredSize(new Dimension(400, 300));
		GridBagConstraints c = new GridBagConstraints();
		c.ipady = 10;
		
		//Cím label lehelyezése
		c.gridy = 0;
		c.gridx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		labelTitle = new JLabel();
		labelStyleSet(labelTitle);
		panel.add(labelTitle, c);
		
		//Elsõ label lehelyezése
		c.gridy = 1;
		c.gridx = 0;
		labelTop = new JLabel();
		labelStyleSet(labelTop);
		panel.add(labelTop, c);
		
		//Elsõ combobox lehelyezése
		c.gridy = 2;
		comboTop = new JComboBox<String>();
		comboBoxStyleSet(comboTop);
		panel.add(comboTop, c);
		
		//Második label elhelyezése.
		c.gridy = 3;
		this.labelMiddle = new JLabel();
		labelStyleSet(labelMiddle);
		panel.add(labelMiddle, c);
		
		//Középsõ comboBox lehelyezése.
		c.gridy = 4;
		this.comboMiddle = new JComboBox<String>();
		comboBoxStyleSet(comboMiddle);
		panel.add(comboMiddle, c);
		
		//Harmadik label elhelyezése.
		c.gridy = 5;
		this.labelBottom = new JLabel();
		labelStyleSet(labelBottom);
		panel.add(labelBottom, c);
		
		//Alsó comboBox lehelyezése.
		c.gridy = 6;
		this.comboBottom = new JComboBox<String>();
		comboBoxStyleSet(comboBottom);
		panel.add(comboBottom, c);
		
		//Select gomb elhelyezése.
		c.gridy = 7;
		c.fill = GridBagConstraints.NONE;
		this.bSelect = new JButton("Select");
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
