package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.plaf.basic.BasicScrollBarUI;

//
//
//  @ Project : Space-Miner
//  @ File Name : View.java
//  @ Date : 07/05/2021
//  @ Author : Bárkányi Csaba
//
//



/**
 * Az alkalmazás nézetét megvalósító osztály.
 * @author Bárkányi
 *
 */
public class View {
	//A nézet egyes fő elemei.
		//Főablak.
	private JFrame fMainWindow;
	private JPanel pMainWindow;
	private JPanel pMainWindowborder;
	private JPanel pMainLabel;
	private JLabel lMainLabel;
	private JPanel pMainBottomLabel;
	private JLabel lMainBottomLabel;
		//Bal oldali ablakrész.
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
		//Az ablak középső részének elemei..
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
	private GraphicalPanel image;
		//A jobb oldali ablak elemei.
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
	
	/**
	 * Konstruktor.
	 * Létrehozza az ablak elemeit,valamint beállítja a kezdő szöveget és
	 * meghívja a további ablakfelépítő függvényeket.
	 */
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
		bNextTurn.setEnabled(false);
		bNew = new JButton("New");
		bSave = new JButton("Save");
		bSave.setEnabled(false);
		bLoad = new JButton("Load");
		bExit = new JButton("Exit");
		tbTurnEvents = new JTextArea();
		tbTurnEvents.setEditable(false);
		
		pGraphicView = new JPanel();
		pPictureGraphicView = new JPanel();
		bSetGraphicView = new JPanel();
		bMove= new JButton("Move");
		bMove.setEnabled(false);
		bDrill= new JButton("Drill");
		bDrill.setEnabled(false);
		bMine= new JButton("Mine");
		bMine.setEnabled(false);
		bDrop= new JButton("Drop");
		bDrop.setEnabled(false);
		bBuild= new JButton("Build");
		bBuild.setEnabled(false);
		bPlace= new JButton("Place");
		bPlace.setEnabled(false);
		
		pProperties = new JPanel();
		lProperties = new JLabel("Properties");
		pLabelProperties = new JPanel();
		mProperties = new JPanel();
		bSetProperties = new JPanel();
		bBind = new JButton("Bind");
		bBind.setEnabled(false);
		tbProperties = new JTextArea();
		tbProperties.setEditable(false);
		
		pMainBottomLabel = new JPanel();
		lMainBottomLabel = new JLabel("Created by: Brainstormers");
		//Kezdőkép beállítása.
		image = new GraphicalPanel(new ImageIcon("graphical elements/main.png").getImage());
		image.setPreferredSize(new Dimension(500, 545));
		pPictureGraphicView.add(image);
	}
	
//Get/Set-----------------------------------------------------------------
	
	/**
	 * Setter, amely beállítja a controller tagváltozót.
	 * @param boolean: isRadio
	 */
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	/**
	 * Setter, amely beállítja a game tagváltozót.
	 * @param boolean: isRadio
	 */
	public void setGame(Game game) {
		this.game = game;
	}

//Own methods:----------------------------------------------------------------------------
	
	/**
	 * Beállítja a gombok eseménykezelőit, valamint
	 * létrehozza a dialógusablakokat és továbbhívja az
	 * ablak létrehozásának következő metódusát.
	 */
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
		
		//Csak minden beállítása után jelenik meg az ablak.
		Image icon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE);
		fMainWindow.setIconImage(icon);
		fMainWindow.setLocationRelativeTo(null);
		fMainWindow.setVisible(true);
	}
	
	/**
	 * Elvégzi az ablak alapvető rendezését és beállításait.
	 */
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
	    
	    //Továbbhívja az ablak egyes részeinek létrehozására.
	    viewTurnEvents();
	    viewProperties();
	    viewGraphicView();
	    
	    pMainBottomLabel.add(lMainBottomLabel, BorderLayout.LINE_END);
	    pMainWindow.add(pMainBottomLabel, BorderLayout.PAGE_END);
	    pMainWindowborder.add(pMainWindow);
	    fMainWindow.add(pMainWindowborder);
	    fMainWindow.pack();
	}
	
	/**
	 * Elrendezi a középső ablakrész elemeit.
	 */
	public void viewGraphicView() {
		pGraphicView.setBackground(Color.WHITE);
		pPictureGraphicView.setBackground(Color.WHITE);
		bSetGraphicView.setBackground(Color.WHITE);
		
		pPictureGraphicView.setPreferredSize(new Dimension(500, 555));
		pPictureGraphicView.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Color.BLACK));
		pGraphicView.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
		
		//Sets the buttons position and other properties of the buttons.
		viewGraphicButtonSet();
		
		GridBagLayout GraphicLayout = new GridBagLayout();
		pGraphicView.setLayout(GraphicLayout);
		setSidePanel(pGraphicView, pPictureGraphicView, 0, 0);
		setSidePanel(pGraphicView, bSetGraphicView, 0, 1);
	}
	
	/**
	 * BEállítja a középső ablak gombjaiknak helyzetét és stílusát.
	 */
	public void viewGraphicButtonSet() {
		buttonStyleSet(bMove,162,75);
		buttonStyleSet(bDrill,162,75);
		buttonStyleSet(bMine,162,75);
		buttonStyleSet(bDrop,162,75);
		buttonStyleSet(bBuild,162,75);
		buttonStyleSet(bPlace,162,75);

		
		GridBagLayout buttonSet = new GridBagLayout();
		bSetGraphicView.setLayout(buttonSet);
		//Beállítja a gombok helyzetét a megadottra. 
		buttonPositionSet(bSetGraphicView,bMove,new Insets(5,5,5,0), 0, 0, 1, 1);
		buttonPositionSet(bSetGraphicView,bDrill,new Insets(5,5,5,0), 1, 0, 1, 1);
		buttonPositionSet(bSetGraphicView,bMine,new Insets(5,5,5,5), 2, 0, 1, 1);
		buttonPositionSet(bSetGraphicView,bDrop,new Insets(0,5,90,0), 0, 1, 1, 1);
		buttonPositionSet(bSetGraphicView,bBuild,new Insets(0,5,90,0), 1, 1, 1, 1);
		buttonPositionSet(bSetGraphicView,bPlace,new Insets(0,5,90,5), 2, 1, 1, 1);
	}
	
	/**
	 * A jobb oldali ablak elrendezését hajtja végre.
	 */
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
		//Hozzáadja az adott elemeket a középső ablakhoz.
		setSidePanel(pProperties, pLabelProperties, 0, 0);
		setSidePanel(pProperties, mProperties, 0, 1);
		setSidePanel(pProperties, bSetProperties, 0, 2);
		
		ScrolledPaneForText(mProperties, tbProperties);
	}
	
	/**
	 * Beállítja a középső ablak gombjainak elhelyezését és stílusát.
	 */
	public void viewPropertiesSet() {
		buttonStyleSet(bBind,500,80);
		
		GridBagLayout buttonSet = new GridBagLayout();
		bSetProperties.setLayout(buttonSet);
		
		buttonPositionSet(bSetProperties,bBind,new Insets(5,5,165,5), 0, 0, 1, 1);
	}
	
	/**
	 * Létrehozza a bal oldali panel elrendezését.
	 */
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
	
	/**
	 * Beállítja a jobb oldali panel helyzetét és elemeit.
	 */
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
	
	/**
	 * Létrehoz a szöveges mezők számára egy ScrollPane-t, hogy a nem
	 * látható részek is elérhetőek legyenek.
	 * 
	 * @param JPanel panel: Panel, amelyre el akarjuk helyezni.
	 * @param JTextArea textArea, A szöveges mező, amelyre használni akarjuk.
	 */
	public void ScrolledPaneForText(JPanel panel, JTextArea textArea) {
		JScrollPane scroll = new JScrollPane (textArea, 
				   JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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
		textArea.setFont(new Font("Consolas", Font.BOLD, 20));
		panel.add(scroll);
	}
	
	
	/**
	 * Beállítja az oldalsó panelek elmeinek a megadott értékeket.
	 * @param JPanel panel: tartalmazó panel
	 * @param JPanel contained: tartalmazott panel
	 * @param int gridx: beállítandó helyzet
	 * @param int gridy: beállítandó helyzet
	 */
	public void setSidePanel(JPanel panel, JPanel contained, int gridx, int gridy) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = gridx;
		gbc.gridy = gridy;
	    panel.add(contained, gbc);
	}
	
	/**
	 * Beállítja a gomb stílusát.
	 * @param JButton button : cél gomb
	 * @param int width: beállítandó szélesség
	 * @param int height: beállítandó magasság
	 */
	void buttonStyleSet(JButton button,int width, int height) {
		button.setBackground(Color.GRAY);
		button.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
		button.setFont(new Font("Broadway", Font.BOLD, 30));
		button.setForeground(Color.BLACK);
		button.setPreferredSize(new Dimension(width, height));
	}
	
	/**
	 * Beállítja az adott panelra a gomb helyzetét.
	 * @param JPanel panel: tartalmazó panel
	 * @param JButton buttun: beállítandó gomb
	 * @param Insets ins: tartás beállítása
	 * @param int gridx: x pozíció
	 * @param int gridy: y pozíció
	 * @param int gridwidth: szélesség
	 * @param int gridheight: magasság
	 */
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
	
	/**
	 * Beállítja a középső képet settler részére vagy
	 * asteroida számára megfelelőnek.
	 */
	public void setBindedAndRefresh(){
		if(controller.getBoundAsteroid()!=null) {
			pPictureGraphicView.remove(image);
			image.setImage(new ImageIcon("graphical elements/asteroid.png").getImage());
			pPictureGraphicView.add(image);
			tbProperties.setText(null);
			tbProperties.append(controller.getBoundAsteroid().genUIString());
			pPictureGraphicView.revalidate();
		}
		else if(controller.getBoundSettler()!=null){
			pPictureGraphicView.remove(image);
			image.setImage(new ImageIcon("graphical elements/settler.png").getImage());
			pPictureGraphicView.add(image);
			tbProperties.setText(null);
			tbProperties.append(controller.getBoundSettler().genUIString());
			pPictureGraphicView.revalidate();
		}
	}
	
	/**
	 * Beállítja a játék végén, hogy mely
	 * gombok elérhetők a felhasználók számára.
	 */
	public void buttonSetInWinAndGameOver() {
		bNextTurn.setEnabled(false);
		bNew.setEnabled(true);
		bSave.setEnabled(false);
		bLoad.setEnabled(true);
		bExit.setEnabled(true);
		
		bMove.setEnabled(false);
		bDrill.setEnabled(false);
		bMine.setEnabled(false);
		bDrop.setEnabled(false);
		bBuild.setEnabled(false);
		bPlace.setEnabled(false);
		
		bBind.setEnabled(false);
	}
	
	/**
	 * A játék megnyeréséhez kapcsolódó felületi beállítások.
	 */
	public void youWin() {
		pPictureGraphicView.remove(image);
		image.setImage(new ImageIcon("graphical elements/gamewin.png").getImage());
		pPictureGraphicView.add(image);
		pPictureGraphicView.revalidate();
		buttonSetInWinAndGameOver();
	}
	
	/**
	 * A játék elvesztéséhez kapcsolódó felületi beállítások.
	 */
	public void gameOver() {
		pPictureGraphicView.remove(image);
		image.setImage(new ImageIcon("graphical elements/gameover.png").getImage());
		pPictureGraphicView.add(image);
		pPictureGraphicView.revalidate();
		buttonSetInWinAndGameOver();
	}
	
	/**
	 * Beállítja az a játék elkezdése utána kezdő állípotokat.
	 */
	public void gameStartSet() {
		tbTurnEvents.setText(null);
		tbProperties.setText(null);
		pPictureGraphicView.remove(image);
		image.setImage(new ImageIcon("graphical elements/settler.png").getImage());
		pPictureGraphicView.add(image);
		tbProperties.append(controller.getBoundSettler().genUIString());
		pPictureGraphicView.revalidate();
		
		bNextTurn.setEnabled(true);
		bMove.setEnabled(true);
		bMine.setEnabled(true);
		bPlace.setEnabled(true);
		bDrill.setEnabled(true);
		bDrop.setEnabled(true);
		bBuild.setEnabled(true);
		bBind.setEnabled(true);
		bSave.setEnabled(true);
	}
//Internal classes-----------------------------------------------------------------
	
	/**
	 * Eseménykezelő a NextTrun gom részére.
	 */
	private class NextTurnListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			controller.handdleNextTurn();
			tbTurnEvents.setText(null);
			tbTurnEvents.append(game.listTurnEvents());
			setBindedAndRefresh();
		}
	}
	
	/**
	 * Eseménykezelő a New gomb részére.
	 */
	private class NewListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			controller.handleNew();

			gameStartSet();
		}
	}
	
	/**
	 * Eseménykezelő a Load gomb részére.
	 */
	private class LoadListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			controller.handleLoad();
			
			gameStartSet();
		}
	}
	
	/**
	 * Eseménykezelő a Save gomb részére.
	 */
	private class SaveListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			controller.handleSave();
		}
	}
	
	/**
	 * Eseménykezelő a Exit gomb részére.
	 */
	private class ExitListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			controller.handleExit();
		}
	}
	
	/**
	 * Eseménykezelő a Move gomb részére.
	 */
	private class MoveListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			moveDialog.show();
		}
	}
	
	/**
	 * Eseménykezelő a Mine gomb részére.
	 */
	private class MineListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			mineDialog.show();
		}
	}
	
	/**
	 * Eseménykezelő a Drill gomb részére.
	 */
	private class DrillListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			drillDialog.show();
		}
	}
	
	/**
	 * Eseménykezelő a Build gomb részére.
	 */
	private class BuildListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			buildDialog.show();
		}
	}
	
	/**
	 * Eseménykezelő a PLace gomb részére.
	 */
	private class PlaceListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			placeDialog.show();
		}
	}
	
	/**
	 * Eseménykezelő a Drop gomb részére.
	 */
	private class DropListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			dropDialog.show();
		}
	}
	
	/**
	 * Eseménykezelő a Move gomb részére.
	 */
	private class BindListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			bindDialog.show();	
			setBindedAndRefresh();
		}
	}
	
	/**
	 * Egy kép panelt reprezentál, amelyre be lehet állítani a
	 * megadott képet.
	 */
	private class GraphicalPanel extends JPanel {

		private static final long serialVersionUID = 1518683176124655681L;
		
		private Image img;

		  public GraphicalPanel(Image img) {
			  setImage(img);
		  }
		  
		  public void setImage(Image img) {
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


