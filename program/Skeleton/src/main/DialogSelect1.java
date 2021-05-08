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
		this.setModal(true);
		this.setResizable(false);
		this.game = g;
		this.controller = cont;
		JPanel panel = new JPanel(new GridBagLayout());
		this.setPreferredSize(new Dimension(200, 200));
		panel.setPreferredSize(new Dimension(200, 200));
		GridBagConstraints c = new GridBagConstraints();
		c.ipady = 10;
		
		//C�m label elhelyez�se.
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		labelTitle = new JLabel();
		labelStyleSet(labelTitle);
		panel.add(labelTitle, c);
		
		//Els� label lehelyez�se.
		c.gridy = 1;
		labelTop = new JLabel();
		labelStyleSet(labelTop);
		panel.add(labelTop, c);
		
		//Combobox lehelyez�se.
		c.gridy = 2;
		comboTop = new JComboBox<String>();
		comboBoxStyleSet(comboTop);
		panel.add(comboTop, c);
		c.ipady = 10;
		
		//Select gomb lehelyez�se.
		c.gridy = 3;
		bSelect = new JButton("Select");
		buttonStyleSet(bSelect, 80, 30, 12);	
		panel.add(bSelect, c);
		
		//Panel elhelyez�se a dial�gusablakon.
		this.setLocationRelativeTo(null);
		this.add(panel);
		this.pack();
	}
	/**
	 * Gombok kin�zet�nek be�ll�t�sa.
	 * @param button: JButton: A gomb, aminek �j kin�zetet akarunk.
	 * @param width: int: Milyen sz�les legyen.
	 * @param height: int: Milyen magas legyen.
	 * @param fontsize: int: A bet�k mekkor�k legyenek.
	 */
	void buttonStyleSet(JButton button,int width, int height, int fontsize) {
		button.setBackground(Color.GRAY);
		button.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		button.setFont(new Font("Broadway", Font.BOLD, fontsize));
		button.setForeground(Color.BLACK);
		button.setPreferredSize(new Dimension(width, height));
	}
	
	/**
	 * Label-ek kin�zet�nek m�dos�t�sa.
	 * @param label: JLabel: A label, amit m�dos�tani akarunk.
	 */
	void labelStyleSet(JLabel label) {
		label.setFont(new Font("Broadway", Font.BOLD, 12));
		label.setBackground(Color.GRAY);
		label.setForeground(Color.BLACK);
	}
	/**
	 * Combobox kin�zet�nek m�dos�t�sa.
	 * @param box: JComboBox: A combobox, aminek �j kin�zetet akarunk.
	 */
	void comboBoxStyleSet(JComboBox box) {
		box.setFont(new Font("Broadway", Font.BOLD, 12));
		box.setBackground(Color.GRAY);
		box.setForeground(Color.BLACK);
	}

}
