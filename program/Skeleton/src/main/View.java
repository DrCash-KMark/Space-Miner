package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

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
	private JTextArea tbTurnEvents;
	
	private JPanel pGraphicView;
	private JPanel pPictureGraphicView;
	private JPanel bSetGraphicView;
	private JButton bMove;
	private JButton bDrill;
	private JButton bMine;
	private JButton bDrop;
	private JButton bBuild;
	private JButton bPlace;
	private MoveDialog moveDialog;
	private DrillDialog drillDialog;
	private MineDialog mineDialog;
	private DropDialog dropDialog;
	private BuildDialog buildDialog;
	private PlaceDialog placeDialog;
	private ImagePanel image;
	
	private JPanel pProperties;
	private JLabel lProperties;
	private JPanel pLabelProperties;
	private JPanel mProperties;
	private JPanel bSetProperties;
	private JButton bBind;
	private JTextArea tbProperties;
	private BindDialog bindDialog;
	
	private Controller controller;
	private Game game;
	
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
		tbTurnEvents = new JTextArea();
		
		pGraphicView = new JPanel();
		pPictureGraphicView = new JPanel();
		bSetGraphicView = new JPanel();
		bMove= new JButton("Move");
		bDrill= new JButton("Drill");
		bMine= new JButton("Mine");
		bDrop= new JButton("Drop");
		bBuild= new JButton("Build");
		bPlace= new JButton("Place");
		/*moveDialog = new MoveDialog(game, controller);
		drillDialog = new DrillDialog(game, controller);
		mineDialog = new MineDialog(game, controller);
		dropDialog  = new DropDialog(game, controller);
		buildDialog = new BuildDialog(game, controller);
		placeDialog  = new PlaceDialog(game, controller);*/
		
		
		pProperties = new JPanel();
		lProperties = new JLabel("Properties");
		pLabelProperties = new JPanel();
		mProperties = new JPanel();
		bSetProperties = new JPanel();
		bBind = new JButton("Bind");
		//bindDialog= new BindDialog(game, controller);
		tbProperties = new JTextArea();
		
		pMainBottomLabel = new JPanel();
		lMainBottomLabel = new JLabel("Created by: Brainstormers");
		
		image = new ImagePanel(new ImageIcon("main.png").getImage());
		image.setPreferredSize(new Dimension(500, 545));
		pPictureGraphicView.add(image);
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public void Display() {
		BuildView();
		
		moveDialog = new MoveDialog(game, controller);
		drillDialog = new DrillDialog(game, controller);
		mineDialog = new MineDialog(game, controller);
		dropDialog  = new DropDialog(game, controller);
		buildDialog = new BuildDialog(game, controller);
		placeDialog  = new PlaceDialog(game, controller);
		
		bindDialog= new BindDialog(game, controller);
		
		bNextTurn.addActionListener( new NextTurnListener());
		bNew.addActionListener( new NewListener());
		bSave.addActionListener( new SaveListener());
		bLoad.addActionListener( new LoadListener());
		bExit.addActionListener( new ExitListener());
		
		bMove.addActionListener( new MoveListener());
		bDrill.addActionListener( new DrillListener());
		bMine.addActionListener( new MineListener());
		bDrop.addActionListener( new DropListener());
		bBuild.addActionListener( new BuildListener());
		bPlace.addActionListener( new PlaceListener());
		
		bBind.addActionListener( new BindListener());
		
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
		
		pPictureGraphicView.setPreferredSize(new Dimension(500, 555));
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
		buttonPositionSet(bSetGraphicView,bDrop,new Insets(0,5,90,0), 0, 1, 1, 1);
		buttonPositionSet(bSetGraphicView,bBuild,new Insets(0,5,90,0), 1, 1, 1, 1);
		buttonPositionSet(bSetGraphicView,bPlace,new Insets(0,5,90,5), 2, 1, 1, 1);
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
		
		ScrolledPaneForText(mProperties, tbProperties);
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
		
		ScrolledPaneForText(mTurnEvents, tbTurnEvents);
	}
	
	public void ScrolledPaneForText(JPanel panel, JTextArea textArea) {
		JScrollPane scroll = new JScrollPane (textArea, 
				   JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(500, 500));
		scroll.getVerticalScrollBar().setBackground(Color.WHITE);
		scroll.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE) );
		scroll.getVerticalScrollBar().setUI(new BasicScrollBarUI()
	    {   
		    @Override
		    protected void configureScrollBarColors() {
		        this.thumbColor = Color.DARK_GRAY;
		    }
	        @Override
	        protected JButton createDecreaseButton(int orientation) {
	            return createZeroButton();
	        }
	        @Override    
	        protected JButton createIncreaseButton(int orientation) {
	            return createZeroButton();
	        }
	        private JButton createZeroButton() {
	            JButton jbutton = new JButton();
	            jbutton.setPreferredSize(new Dimension(0, 0));
	            jbutton.setMinimumSize(new Dimension(0, 0));
	            jbutton.setMaximumSize(new Dimension(0, 0));
	            return jbutton;
	        }
	    });
		textArea.setFont(new Font("Broadway", Font.BOLD, 25));
		panel.add(scroll);
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
	
	private class NextTurnListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			controller.handdleNextTurn();
			tbTurnEvents.append(game.listTurnEvents());
		}
	}
	
	private class NewListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			controller.handleNew();
		}
	}
	
	private class LoadListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			controller.handleLoad();
		}
	}
	
	private class SaveListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			controller.handleSave();
		}
	}
	
	private class ExitListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			controller.handleExit();
		}
	}
	
	private class MoveListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			moveDialog.show();
		}
	}
	
	private class MineListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			mineDialog.show();
		}
	}
	
	private class DrillListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			drillDialog.show();
		}
	}
	
	private class BuildListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			buildDialog.show();
		}
	}
	
	private class PlaceListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			placeDialog.show();
		}
	}
	
	private class DropListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			dropDialog.show();
		}
	}
	
	private class BindListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			bindDialog.show();

			if(controller.getBoundAsteroid()==null)
				tbProperties.append("alma");
			
			if(controller.getBoundAsteroid()!=null) {
				pPictureGraphicView.remove(image);
				image = new ImagePanel(new ImageIcon("asteroid.png").getImage());
				image.setPreferredSize(new Dimension(500, 545));
				pPictureGraphicView.add(image);
				tbProperties.append(controller.getBoundAsteroid().genUIString());
				BuildView();
			}
			else if(controller.getBoundSettler()!=null){
				pPictureGraphicView.remove(image);
				image = new ImagePanel(new ImageIcon("settler.png").getImage());
				image.setPreferredSize(new Dimension(500, 545));
				pPictureGraphicView.add(image);
				tbProperties.append(controller.getBoundSettler().genUIString());
				BuildView();
			}
		}
	}
	
	private class ImagePanel extends JPanel {

		  private Image img;

		  public ImagePanel(String img) {
		    this(new ImageIcon(img).getImage());
		  }

		  public ImagePanel(Image img) {
		    this.img = img;
		    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		    setPreferredSize(size);
		    setMinimumSize(size);
		    setMaximumSize(size);
		    setSize(size);
		    setLayout(null);
		  }

		  public void paintComponent(Graphics g) {
		    g.drawImage(img, 0, 0, null);
		  }

		}
}


