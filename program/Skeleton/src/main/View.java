package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class View {
	private JFrame fMainWindow;
	private JPanel pMainWindow;
	private JPanel pMainWindowborder;
	private JPanel pMainLabel;
	private JLabel lMainLabel;
	private JPanel pMainBottomLabel;
	private JLabel lMainBottomLabel;
	
	private JPanel pTurnEvents;
	private JPanel pLabelTurnEvents;
	private JLabel lTurnEvents;
	private JPanel mTurnEvents;
	private JPanel bSetTurnEvents;
	private JButton bNew;
	private JButton bSave;
	private JButton bLoad;
	private JButton bExit;
	private JButton bNextTurn;
	
	private JPanel pGraphicView;
	private JPanel pPictureGraphicView;
	private JPanel bSetGraphicView;
	private JButton bMove;
	private JButton bDrill;
	private JButton bMine;
	private JButton bDrop;
	private JButton bBuild;
	private JButton bPlace;
	
	private JPanel pProperties;
	private JLabel lProperties;
	private JPanel pLabelProperties;
	private JPanel mProperties;
	private JPanel bSetProperties;
	private JButton bBind;
	
	public View(){
		fMainWindow = new JFrame();
		pMainWindow = new JPanel();
		pMainWindowborder = new JPanel();
		pMainLabel = new JPanel();
		lMainLabel = new JLabel("Space-Miner");
		
		pTurnEvents = new JPanel();
		lTurnEvents = new JLabel("Turn events");
		pLabelTurnEvents = new JPanel();
		mTurnEvents = new JPanel();
		bSetTurnEvents = new JPanel();
		bNextTurn = new JButton("Next Turn");
		bNew = new JButton("New");
		bSave = new JButton("Save");
		bLoad = new JButton("Load");
		bExit = new JButton("Exit");
		
		pGraphicView = new JPanel();
		pPictureGraphicView = new JPanel();
		bSetGraphicView = new JPanel();
		bMove= new JButton("Move");
		bDrill= new JButton("Drill");
		bMine= new JButton("Mine");
		bDrop= new JButton("Drop");
		bBuild= new JButton("Build");
		bPlace= new JButton("Place");
		
		pProperties = new JPanel();
		lProperties = new JLabel("Properties");
		pLabelProperties = new JPanel();
		mProperties = new JPanel();
		bSetProperties = new JPanel();
		bBind = new JButton("Bind");
		
		pMainBottomLabel = new JPanel();
		lMainBottomLabel = new JLabel("Created by: Brainstormers");
		BuildView();
		fMainWindow.setVisible(true);
	}
	
	public void BuildView() {
		fMainWindow.setBackground(Color.WHITE);
		pGraphicView.setBackground(Color.WHITE);
		pMainLabel.setBackground(Color.BLACK);
		pMainBottomLabel.setBackground(Color.BLACK);
		pPictureGraphicView.setBackground(Color.WHITE);
		bSetGraphicView.setBackground(Color.WHITE);
		
		pMainLabel.setPreferredSize(new Dimension(1515, 50));
		pMainBottomLabel.setPreferredSize(new Dimension(1515, 25));

		pMainWindowborder.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Color.BLACK));
		
	    BorderLayout mainLayout = new BorderLayout();
	    mainLayout.setHgap(5);
	    mainLayout.setVgap(5);
	    pMainWindow.setLayout(mainLayout);
	    
	    lMainLabel.setFont(new Font("Broadway", Font.BOLD, 30));
	    lMainLabel.setForeground(Color.WHITE);
	    
	    lMainBottomLabel.setFont(new Font("Broadway", Font.BOLD, 15));
	    lMainBottomLabel.setForeground(Color.WHITE);
	    
	    pMainWindow.add(pTurnEvents, BorderLayout.LINE_START);
	    pMainWindow.add(pGraphicView, BorderLayout.CENTER);
	    pMainWindow.add(pProperties, BorderLayout.LINE_END);
	    pMainLabel.add(lMainLabel,BorderLayout.CENTER);
	    pMainWindow.add(pMainLabel, BorderLayout.PAGE_START);
	    
	    viewTurnEvents();
	    viewProperties();
	    viewGraphicView();
	    
	    pMainBottomLabel.add(lMainBottomLabel, BorderLayout.LINE_END);
	    pMainWindow.add(pMainBottomLabel, BorderLayout.PAGE_END);
	    pMainWindowborder.add(pMainWindow);
	    fMainWindow.add(pMainWindowborder);
	    fMainWindow.pack();
	}
	public void viewGraphicView() {
		pGraphicView.setBackground(Color.WHITE);
		pPictureGraphicView.setBackground(Color.WHITE);
		bSetGraphicView.setBackground(Color.WHITE);
		
		pPictureGraphicView.setPreferredSize(new Dimension(500, 500));
		pPictureGraphicView.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Color.BLACK));
		pGraphicView.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
		
		viewGraphicButtonSet();
		
		GridBagLayout GraphicLayout = new GridBagLayout();
		pGraphicView.setLayout(GraphicLayout);
		setSidePanel(pGraphicView, pPictureGraphicView, 0, 0);
		setSidePanel(pGraphicView, bSetGraphicView, 0, 1);
	}
	
	public void viewGraphicButtonSet() {
		buttonStyleSet(bMove,162,75);
		buttonStyleSet(bDrill,162,75);
		buttonStyleSet(bMine,162,75);
		buttonStyleSet(bDrop,162,75);
		buttonStyleSet(bBuild,162,75);
		buttonStyleSet(bPlace,162,75);

		
		GridBagLayout buttonSet = new GridBagLayout();
		bSetGraphicView.setLayout(buttonSet);
		
		buttonPositionSet(bSetGraphicView,bMove,new Insets(5,5,5,0), 0, 0, 1, 1);
		buttonPositionSet(bSetGraphicView,bDrill,new Insets(5,5,5,0), 1, 0, 1, 1);
		buttonPositionSet(bSetGraphicView,bMine,new Insets(5,5,5,5), 2, 0, 1, 1);
		buttonPositionSet(bSetGraphicView,bDrop,new Insets(0,5,33,0), 0, 1, 1, 1);
		buttonPositionSet(bSetGraphicView,bBuild,new Insets(0,5,33,0), 1, 1, 1, 1);
		buttonPositionSet(bSetGraphicView,bPlace,new Insets(0,5,33,5), 2, 1, 1, 1);
	}
	
	public void viewProperties() {
		pProperties.setBackground(Color.WHITE);
		pLabelProperties.setBackground(Color.GRAY);
		mProperties.setBackground(Color.WHITE);
		bSetProperties.setBackground(Color.WHITE);
		
		pLabelProperties.setPreferredSize(new Dimension(500, 50));
		mProperties.setPreferredSize(new Dimension(500, 500));
		
		pProperties.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
		pLabelProperties.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Color.BLACK));
		bSetProperties.setBorder(BorderFactory.createMatteBorder(4, 0, 0, 0, Color.BLACK));
		
		lProperties.setFont(new Font("Broadway", Font.BOLD, 25));
		lProperties.setForeground(Color.BLACK);
		
		pLabelProperties.add(lProperties);
		viewPropertiesSet();
		
		GridBagLayout labelPropertiesLayout = new GridBagLayout();
		pProperties.setLayout(labelPropertiesLayout);
		setSidePanel(pProperties, pLabelProperties, 0, 0);
		setSidePanel(pProperties, mProperties, 0, 1);
		setSidePanel(pProperties, bSetProperties, 0, 2);
	}
	
	public void viewPropertiesSet() {
		buttonStyleSet(bBind,500,80);
		
		GridBagLayout buttonSet = new GridBagLayout();
		bSetProperties.setLayout(buttonSet);
		
		buttonPositionSet(bSetProperties,bBind,new Insets(5,5,165,5), 0, 0, 1, 1);
	}
	
	public void viewTurnEvents() {
		pTurnEvents.setBackground(Color.WHITE);
		pLabelTurnEvents.setBackground(Color.GRAY);
		mTurnEvents.setBackground(Color.WHITE);
		bSetTurnEvents.setBackground(Color.WHITE);
		
		pLabelTurnEvents.setPreferredSize(new Dimension(500, 50));
		mTurnEvents.setPreferredSize(new Dimension(500, 500));
		
		pTurnEvents.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
		pLabelTurnEvents.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Color.BLACK));
		bSetTurnEvents.setBorder(BorderFactory.createMatteBorder(4, 0, 0, 0, Color.BLACK));
		
		lTurnEvents.setFont(new Font("Broadway", Font.BOLD, 25));
		lTurnEvents.setForeground(Color.BLACK);
		
		pLabelTurnEvents.add(lTurnEvents);
		viewTurnEventsButtonSet();
		
		GridBagLayout labelTurnEventsLayout = new GridBagLayout();
		pTurnEvents.setLayout(labelTurnEventsLayout);
		setSidePanel(pTurnEvents, pLabelTurnEvents, 0, 0);
		setSidePanel(pTurnEvents, mTurnEvents, 0, 1);
		setSidePanel(pTurnEvents, bSetTurnEvents, 0, 2);
	}
	
	public void setSidePanel(JPanel panel, JPanel contained, int gridx, int gridy) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = gridx;
		gbc.gridy = gridy;
	    panel.add(contained, gbc);
	}
	
	public void viewTurnEventsButtonSet() {
		buttonStyleSet(bNextTurn,500,80);
		buttonStyleSet(bNew,162,75);
		buttonStyleSet(bSave,162,75);
		buttonStyleSet(bLoad,162,75);
		buttonStyleSet(bExit,162,75);

		
		GridBagLayout buttonSet = new GridBagLayout();
		bSetTurnEvents.setLayout(buttonSet);
		
		buttonPositionSet(bSetTurnEvents,bNextTurn,new Insets(5,5,5,5), 0, 0, 3, 1);
		buttonPositionSet(bSetTurnEvents,bNew,new Insets(0,5,0,0), 0, 1, 1, 1);
		buttonPositionSet(bSetTurnEvents,bSave,new Insets(0,5,0,0), 1, 1, 1, 1);
		buttonPositionSet(bSetTurnEvents,bLoad,new Insets(0,5,0,5), 2, 1, 1, 1);
		buttonPositionSet(bSetTurnEvents,bExit,new Insets(5,5,5,0), 1, 2, 1, 1);
	}
	
	void buttonStyleSet(JButton button,int width, int height) {
		button.setBackground(Color.GRAY);
		button.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
		button.setFont(new Font("Broadway", Font.BOLD, 30));
		button.setForeground(Color.BLACK);
		button.setPreferredSize(new Dimension(width, height));
	}
	
	void buttonPositionSet(JPanel panel,JButton buttun,Insets ins, int gridx, int gridy, int gridwidth, int gridheight) {
		GridBagConstraints gbcButtonSet = new GridBagConstraints();
		gbcButtonSet.fill = GridBagConstraints.BOTH;
		gbcButtonSet.insets = ins;
		gbcButtonSet.gridx = gridx;
		gbcButtonSet.gridy = gridy;
		gbcButtonSet.gridwidth = gridwidth;
		gbcButtonSet.gridheight = gridheight;
		panel.add(buttun,gbcButtonSet);
	}
}
