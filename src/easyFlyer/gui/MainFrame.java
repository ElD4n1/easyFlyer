package easyFlyer.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import easyFlyer.api.GuiApi;
import easyFlyer.api.GuiApi_Impl;
import easyFlyer.dataTypes.ListColor;
import easyFlyer.model.ImageComponent;
import easyFlyer.tools.LoupeTool;
import easyFlyer.tools.SelTool;
import easyFlyer.tools.ToolPalette;

public class MainFrame {

	JFrame frame;
	Container contentpane;
	
	GuiApi model;
	
	private BufferedImage curPicture;
	private String curText;

	// Konstanten
	public static final int ICONSIZE = 15;
	public static final Dimension NUMBERFIELD_DIMENSION = new Dimension(40, 20);
	public static final Dimension TEXTFIELD_DIMENSION = new Dimension(250, 20);
	public static final Dimension SLIDER_DIMENSION = new Dimension(100, 16);
	
	// Zeichenfeld
	public JPanel drawingPanel;
	
	//Datenfelder
	private boolean saved = false; 		//True wenn alle Änderungen gespeichert sind

	// Felder für textcontrolpanel
	private final String[] POSITIONSTRING = { "Linksbündig", "Mittig",
			"Rechtsbündig" };
	// private final Font[] FONTLIST =
	// GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
	private final String[] FONTSTRING = GraphicsEnvironment
			.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	private final ListColor[] COLORLIST = { new ListColor("Schwarz", Color.black), new ListColor("Grau", Color.gray), new ListColor("Hellgrau", Color.lightGray), new ListColor("Weis", Color.white), new ListColor("Blau", Color.blue), new ListColor("Rot", Color.red), new ListColor("Grün", Color.green), new ListColor("Gelb", Color.yellow), new ListColor("Pink", Color.pink)};
	private JComboBox<String> tfontchooser;
	private JTextField tsizefield;
	private JComboBox<ListColor> tcolorchooser;
	private JComboBox<String> tposition;
//	private JSlider ttransslider;
//	private JTextField ttransfield;

	// Layoutmanager für Controllpanel
	private LayoutManager controllLayout = new GridLayout(0,1);
	
	
	// Felder für picturcontrolpanel
	private JTextField ploadfile;
	private JButton pfilechooserdbtn;
	private JButton ploadbtn;
	private JSlider ptransslider;
	private JTextField pskalefield;
	private JTextField pframewidth;
	private JTextField pframeheight;
	private JToggleButton pframebtn;
	private JComboBox<ListColor> pframecolor;
	private JTextField pframepixels;
	private JTextField ptransfield;

	
	
	// Felder für picturecontrolpanel
	//TODO Ändern auf Klasse Shape die sich selber zeichen kann (zb. Clonbares objekt)
	private final String[] SHAPESTRING = { "Rechteck", "Kreis", "Dreieck",
			"Pfeil" };
	private JComboBox<String> sshapechooser;
	private JComboBox<ListColor> scolorchooser;
	private JTextField swidth;
	private JTextField sheight;
	private JTextField stransfield;
	private JSlider stransslider;
	

	// Felder für edittoolpanel
	JSlider zoomslider;
	JLabel zoomfaktor;
	ToolPalette toolPalet;
	
	// Menuhandler
	private ActionListener newFileHandler;
	private ActionListener openFileHandler;
	private ActionListener saveFileHander;
	private ActionListener saveasFileHandler;
	private ActionListener exportFileHandler;
	private ActionListener exportProjectHandler;
	private ActionListener docsettingHandler;
	private ActionListener closeFileHandler;
	
	// Picturehandler
	private ActionListener loadPictureHandler;
	private ActionListener insertPictureHandler;
	private ChangeListener ptransHandler;
	private ActionListener ptransfieldHandler;
	private ActionListener pskaleHandler;
	private ActionListener frameEventHandler;
	
	//TextHandler
	private ActionListener textchangedHandler;
	
	//SchapeHandler
	private ActionListener shapeHandler;
	private ActionListener stransfieldHandler;
	private ChangeListener stranssliderHandler;
	
	

	//Texthandler
	
	public MainFrame() {
		initUI();
	}

	private void initMenu() {
		// Menü bauen
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("Datei");

		// Neues Projekt starten (Altes vielleicht speichern und Schließen)
		JMenuItem newFile = new JMenuItem("Neues Projekt");
		JMenuItem openFile = new JMenuItem("Öffnen");
		JMenuItem saveFile = new JMenuItem("Speichern");
		JMenuItem saveasFile = new JMenuItem("Speichern unter");
		JMenuItem exportFile = new JMenuItem("Export");
		JMenuItem exportProject = new JMenuItem("Als Ordner exportieren");
		JMenuItem documentSetting = new JMenuItem("Dokumenteigenschaften");
		JMenuItem closeFile = new JMenuItem("Datei Schließen");

		newFile.addActionListener(newFileHandler);
		openFile.addActionListener(openFileHandler);
		saveFile.addActionListener(saveFileHander);
		saveasFile.addActionListener(saveasFileHandler);
		exportFile.addActionListener(exportFileHandler);
		exportProject.addActionListener(exportProjectHandler);
		documentSetting.addActionListener(docsettingHandler);
		closeFile.addActionListener(closeFileHandler);

		fileMenu.add(newFile);
		fileMenu.addSeparator();
		fileMenu.add(openFile);
		fileMenu.add(saveFile);
		fileMenu.add(saveasFile);
		fileMenu.addSeparator();
		fileMenu.add(exportFile);
		fileMenu.add(exportProject);
		fileMenu.addSeparator();
		fileMenu.add(documentSetting);
		fileMenu.addSeparator();
		fileMenu.add(closeFile);

		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
	}

	private void initPicturControl(JPanel pane) {

		
		pane.setLayout(controllLayout);

		JPanel pfilechooserpane = new JPanel();
		JPanel ptranspane = new JPanel();
		JPanel pskalpane = new JPanel();
		JPanel psizepane = new JPanel();
		JPanel pframepane = new JPanel();
		JPanel pframecolorpane = new JPanel();
		JPanel pframesizepane = new JPanel();

		pfilechooserpane.setLayout(new FlowLayout(FlowLayout.LEADING));
		ptranspane.setLayout(new FlowLayout(FlowLayout.LEADING));
		pskalpane.setLayout(new FlowLayout(FlowLayout.LEADING));
		psizepane.setLayout(new FlowLayout(FlowLayout.LEADING));
		pframepane.setLayout(new FlowLayout(FlowLayout.LEADING));
		pframecolorpane.setLayout(new FlowLayout(FlowLayout.LEADING));
		pframesizepane.setLayout(new FlowLayout(FlowLayout.LEADING));

		
		ploadfile = new JTextField("Dateiname");
		pfilechooserdbtn = new JButton("Durchsuchen");
		ploadbtn = new JButton("Laden");
		ptransslider = new JSlider();
		ptransslider.setPreferredSize(SLIDER_DIMENSION);
		ptransslider.setMinimum(0);
		ptransslider.setMaximum(100);
		ptransslider.setValue(100);
		pskalefield = new JTextField("100");
		pskalefield.setPreferredSize(NUMBERFIELD_DIMENSION);
		pframewidth = new JTextField("100");
		pframewidth.setPreferredSize(NUMBERFIELD_DIMENSION);
		pframeheight = new JTextField("100");
		pframeheight.setPreferredSize(NUMBERFIELD_DIMENSION);
		pframebtn = new JToggleButton("Ramen einblenden", false);
		pframecolor = new JComboBox<ListColor>(COLORLIST);
		pframepixels = new JTextField("1");
		pframepixels.setPreferredSize(NUMBERFIELD_DIMENSION);
		ptransfield = new JTextField("100");
		ptransfield.setPreferredSize(NUMBERFIELD_DIMENSION);
		
		//Actionlistener
		pfilechooserdbtn.addActionListener(loadPictureHandler);
		ploadbtn.addActionListener(insertPictureHandler);
		ptransslider.addChangeListener(ptransHandler);
		ptransfield.addActionListener(ptransfieldHandler);
		pskalefield.addActionListener(pskaleHandler);
		pframewidth.addActionListener(frameEventHandler);
		pframeheight.addActionListener(frameEventHandler);
		pframebtn.addActionListener(frameEventHandler);
		pframecolor.addActionListener(frameEventHandler);
		pframepixels.addActionListener(frameEventHandler);
		
		
		pfilechooserpane.add(pfilechooserdbtn);
		pfilechooserpane.add(ploadfile);
		pfilechooserpane.add(ploadbtn);
		ptranspane.add(new JLabel("Transparenz"));
		ptranspane.add(ptransfield);
		ptranspane.add(ptransslider);
		pskalpane.add(new JLabel("Skalierfaktor %"));
		pskalpane.add(pskalefield);
		psizepane.add(new JLabel("Ramenbreite"));
		psizepane.add(pframewidth);
		psizepane.add(new JLabel("Höhe"));
		psizepane.add(pframeheight);
		pframepane.add(new JLabel("Ramen"));
		pframepane.add(pframebtn);
		pframecolorpane.add(new JLabel("Ramenfarbe"));
		pframecolorpane.add(pframecolor);
		pframesizepane.add(new JLabel("Ramendicke"));
		pframesizepane.add(pframepixels);

		pane.add(pfilechooserpane);
		pane.add(ptranspane);
		pane.add(pskalpane);
		pane.add(psizepane);
		pane.add(pframepane);
		pane.add(pframecolorpane);
		pane.add(pframesizepane);
	}

	private void initTextControl(JPanel pane) {
		pane.setLayout(controllLayout);

		JPanel tfontpane = new JPanel();
		JPanel tsizepane = new JPanel();
		JPanel tcolorpane = new JPanel();
		JPanel tpositionpane = new JPanel();
		


		tfontpane.setLayout(new FlowLayout(FlowLayout.LEADING));
		tsizepane.setLayout(new FlowLayout(FlowLayout.LEADING));
		tcolorpane.setLayout(new FlowLayout(FlowLayout.LEADING));
		tpositionpane.setLayout(new FlowLayout(FlowLayout.LEADING));


		// Daten für die Auswahl
		tfontchooser = new JComboBox<String>(FONTSTRING);
		tsizefield = new JTextField("12");
		tsizefield.setPreferredSize(NUMBERFIELD_DIMENSION);
		tcolorchooser = new JComboBox<ListColor>(COLORLIST);
		tposition = new JComboBox<String>(POSITIONSTRING);		
		
		tfontchooser.addActionListener(textchangedHandler);
		tsizefield.addActionListener(textchangedHandler);
		tcolorchooser.addActionListener(textchangedHandler);
		tposition.addActionListener(textchangedHandler);
		

		tfontpane.add(new JLabel("Schriftart"));
		tfontpane.add(tfontchooser);
		tsizepane.add(new JLabel("Schriftgröße"));
		tsizepane.add(tsizefield);
		tcolorpane.add(new JLabel("Schriftfarbe"));
		tcolorpane.add(tcolorchooser);
		tpositionpane.add(new JLabel("Ausrichtung"));
		tpositionpane.add(tposition);
//		ttranspane.add(new JLabel("Tranparenz"));
//		ttranspane.add(ttransfield);
//		ttranspane.add(ttransslider);

		pane.add(tfontpane);
		pane.add(tsizepane);
		pane.add(tcolorpane);
		pane.add(tpositionpane);
//		pane.add(ttranspane);
	}

	private void initShapeControl(JPanel pane) {
		pane.setLayout(controllLayout);

		JPanel schooserpane = new JPanel();
		JPanel scolorpane = new JPanel();
		JPanel ssizepane = new JPanel();
		JPanel stranspane = new JPanel();

		schooserpane.setLayout(new FlowLayout(FlowLayout.LEADING));
		scolorpane.setLayout(new FlowLayout(FlowLayout.LEADING));
		ssizepane.setLayout(new FlowLayout(FlowLayout.LEADING));
		stranspane.setLayout(new FlowLayout(FlowLayout.LEADING));

		sshapechooser = new JComboBox<String>(SHAPESTRING);
		scolorchooser = new JComboBox<ListColor>(COLORLIST);
		swidth = new JTextField("100");
		swidth.setPreferredSize(NUMBERFIELD_DIMENSION);
		sheight = new JTextField("100");
		sheight.setPreferredSize(NUMBERFIELD_DIMENSION);
		stransfield = new JTextField("100");
		stransfield.setPreferredSize(NUMBERFIELD_DIMENSION);
		stransslider = new JSlider();
		stransslider.setPreferredSize(SLIDER_DIMENSION);
		stransslider.setMinimum(0);
		stransslider.setMaximum(100);
		stransslider.setValue(100);
		
		sshapechooser.addActionListener(shapeHandler);
		stransfield.addActionListener(stransfieldHandler);
		stransslider.addChangeListener(stranssliderHandler);

		schooserpane.add(new JLabel("Form auswählen"));
		schooserpane.add(sshapechooser);
		scolorpane.add(new JLabel("Farbe"));
		scolorpane.add(scolorchooser);
		ssizepane.add(new JLabel("Breite"));
		ssizepane.add(swidth);
		ssizepane.add(new JLabel("Höhe"));
		ssizepane.add(sheight);
		stranspane.add(new JLabel("Transparenz"));
		stranspane.add(stransfield);
		stranspane.add(stransslider);

		pane.add(schooserpane);
		pane.add(scolorpane);
		pane.add(ssizepane);
		pane.add(stranspane);
	}

	private void initEditControl(JPanel pane) {
		pane.setLayout(new FlowLayout());
	
		toolPalet = new ToolPalette();
		
		
		JPanel mousecontpane = new JPanel();
		JPanel zoompane = new JPanel();
		
		mousecontpane.add(toolPalet);
		
		toolPalet.addTool(new SelTool(toolPalet, model));
		toolPalet.addTool(new LoupeTool(toolPalet));
		//  TODO Add Tools
		
		zoomslider = new JSlider();
		zoomslider.setMinimum(50);
		zoomslider.setMaximum(200);
		zoomslider.setValue(100);
		zoomfaktor = new JLabel("100 %");
		

		zoompane.add(zoomslider);
		zoompane.add(zoomfaktor);

		pane.add(mousecontpane);
		pane.add(zoompane);
	}

	private void initUI() {

		// MainFrame initialisieren
		frame = new JFrame("easyFlyer");
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		// Handler erzeugen
		initHandler();
		
		// Menü bauen
		initMenu();

		// Model erzeugen
		this.model = new GuiApi_Impl();
		
		// Panel im Frame aufbauen
		contentpane = frame.getContentPane();
		contentpane.setLayout(new BorderLayout());

		drawingPanel = new JPanel();
		JTabbedPane controlPanel = new JTabbedPane();
		JPanel edittoolPanel = new JPanel();

		edittoolPanel.setBorder(BorderFactory
				.createTitledBorder("Bearbeitungs Werkzeuge"));
		drawingPanel.setBorder(BorderFactory.createEtchedBorder());
		controlPanel.setBorder(BorderFactory
				.createTitledBorder("Einfüge Werkzeuge"));

		JPanel picturpane = new JPanel();
		JPanel textpane = new JPanel();
		JPanel shapepane = new JPanel();

		controlPanel.addTab("Bild", picturpane);
		controlPanel.addTab("Text", textpane);
		controlPanel.addTab("Form", shapepane);

		// Bild Kontrolle
		initPicturControl(picturpane);
		// Text Kontrolle
		initTextControl(textpane);
		// Form Kontrolle
		initShapeControl(shapepane);
		// Bearbeitungswerkzeuge
		initEditControl(edittoolPanel);
		
		//Drawing Panel
		drawingPanel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				model.chooseComponent(e);
				
			}
		});

		contentpane.add(edittoolPanel, BorderLayout.NORTH);
		contentpane.add(drawingPanel, BorderLayout.CENTER);
		contentpane.add(controlPanel, BorderLayout.EAST);

		frame.setPreferredSize(new Dimension(700, 700));
		frame.pack();
		frame.setVisible(true);
	}

	// Handler
	private void initHandler() {
		newFileHandler = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				DokumentSettingFrame settings = new DokumentSettingFrame();
				JOptionPane.showMessageDialog(frame, settings.getContentpane());
				JFileChooser chooser = new JFileChooser();
				if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					model.newFile(settings.getAutor(), settings.getDescripton(), settings.getName(),file.getPath(), settings.getHeight(), settings.getWidth(), settings.getBorder()); 
				}
			}
		};
		openFileHandler = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					model.loadFile(file);
				} 
			}
		};
		saveFileHander = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		};
		saveasFileHandler = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				saveas();
			}
		};
		
		exportFileHandler = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					model.exportFile(file);
				}
			}
		};
		exportProjectHandler = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					model.exportFolder(file);
				}
			}
		};
		docsettingHandler = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DokumentSettingFrame settings = new DokumentSettingFrame();
				JOptionPane.showMessageDialog(frame, settings.getContentpane());
				model.changeDoumentSettings(settings.getName(), settings.getHeight(), settings.getWidth(), settings.getBorder());
			}
		};
		closeFileHandler = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Close (Abfrage ob schon gespeichert wurde
				if(saved){
					frame.dispose();
				} else {
					if( JOptionPane.showConfirmDialog(frame, "Wollen Sie vorher speichern?") == JOptionPane.OK_OPTION ) {
						save();
					}
				}
			}
		};
		loadPictureHandler = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				File file;
				if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
					file = chooser.getSelectedFile();
					try {
						curPicture = ImageIO.read(file);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(frame, "Bild konnte nicht geladen werden.");
					}
				}	
			}
		};
		insertPictureHandler = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.addPicture(curPicture);
			}
		};
		ptransHandler = new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				ptransfield.setText(ptransslider.getValue()+"");
				// TODO Transparenz ändern image direkt an Paul
			}
		};
		ptransfieldHandler = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					int value = Integer.parseInt(ptransfield.getText());
					if(value<0){
						value =0;
					} else if(value>100){
						value =100;
					}
					ptransfield.setText(value+"");
					ptransslider.setValue(value);
			} catch (NumberFormatException ex) {
				//Fehlerfeld falsche eingabe
				ptransfield.setText("");
			}
			}
		};
		pskaleHandler = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO resize the image direkt an Paul
				System.out.println("Bild skalieren Faktor: " + pskalefield.getText());
			}
		};
		frameEventHandler = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO (Paul) größe des dargestellten bildes ändern, oder decorater für ramen ändern/hinzufügen
				//Frame wurde umgestellt. alle informationen abfragen (von allen 
				//Im Model ein decorator pattern vor dem Bild erzeugen/ändern
			}
		};
		textchangedHandler= new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				model.addText(curText);
				
				
			}
		};
		shapeHandler = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Änderungen im Model speichern
				
			}
		};
		stransfieldHandler = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int value = Integer.parseInt(stransfield.getText());
					if(value<0){
						value =0;
					} else if(value>100){
						value =100;
					}
					stransfield.setText(value+"");
					stransslider.setValue(value);
				} catch (NumberFormatException ex) {
					//Fehlerfeld falsche eingabe
					stransfield.setText("");
				}
			}
		};
		stranssliderHandler = new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				stransfield.setText(stransslider.getValue()+"");
			}
		};
	}
	
	private void saveas(){
		JFileChooser chooser = new JFileChooser();
		if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			model.saveFile(file);
		}
		saved=true;
	}
	
	private void save(){
		File file = model.getFilePath();
		if(file== null) {
			saveas();
		} else {
			model.saveFile(file);
			saved=true;
		}
	}
}
