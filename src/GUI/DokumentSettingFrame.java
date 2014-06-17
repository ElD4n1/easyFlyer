package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


import dataTyps.Format;

public class DokumentSettingFrame {
	
	private Container contentpane;
	
	// Formate anlegen
	private Format[] formats = {new Format("A0", 841,1189), new Format("A1", 594,841), new Format("A2", 420, 594), new Format("A3", 297, 420), new Format("A4", 210, 297), new Format("A5", 148, 210), new Format ("A6", 105, 148)};
	
	
	//Datenfelder
	private JTextField name;
	private JComboBox<Format> format;
	private JTextField width;
	private JTextField height;
	private JButton switchbtn;
	private JTextField border;
	
	private ActionListener formatHandler = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Format choosen = (Format)format.getSelectedItem();
			width.setText(choosen.getWidth()+"");
			height.setText(choosen.getHeight()+"");
		}
	};
	
	private ActionListener switchHandler = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String swap = width.getText();
			width.setText(height.getText());
			height.setText(swap);
		}
	};
	
//	public Model getModel(){
//		//Return Model
//	}
	// Übergabeparameter soll das Model sein, in welches die Daten gespeichert werden und falls vorhanden geladen
	public DokumentSettingFrame(){
		initUI();
	}
	//Methode soll später das Model zurück geben
	public String getModel(){
		// Erstellen des Model und Rückgabe
		
		return name.getText();
	}
	
	private void initUI(){
		
		contentpane = new Container();
		
		contentpane.setLayout(new VerticalBagLayout());
		
		name = new JTextField();
		name.setPreferredSize(MainFrame.TEXTFIELD_DIMENSION);
		format= new JComboBox<Format>(formats);
		format.addActionListener(formatHandler);
		width= new JTextField();
		width.setPreferredSize(MainFrame.NUMBERFIELD_DIMENSION);
		switchbtn = new JButton("Tauschen");
		switchbtn.addActionListener(switchHandler );
		height = new JTextField();
		height.setPreferredSize(MainFrame.NUMBERFIELD_DIMENSION);
		border = new JTextField("0");
		border.setPreferredSize(MainFrame.NUMBERFIELD_DIMENSION);
		
		JPanel namepane = new JPanel();
		JPanel formatpane = new JPanel();
		JPanel widthpane = new JPanel();
		JPanel heightpane = new JPanel();
		JPanel borderpane = new JPanel();
		
		namepane.setLayout(new FlowLayout(FlowLayout.LEADING));
		formatpane.setLayout(new FlowLayout(FlowLayout.LEADING));
		widthpane.setLayout(new FlowLayout(FlowLayout.LEADING));
		heightpane.setLayout(new FlowLayout(FlowLayout.LEADING));
		borderpane.setLayout(new FlowLayout(FlowLayout.LEADING));
		
		
		namepane.add(new JLabel("Name"));
		namepane.add(name);
		formatpane.add(new JLabel("Format"));
		formatpane.add(format);
		widthpane.add(new JLabel("Breite"));
		widthpane.add(width);
		widthpane.add(new JLabel("mm"));
		widthpane.add(switchbtn);
		heightpane.add(new JLabel("Höhe"));
		heightpane.add(height);
		heightpane.add(new JLabel("mm"));
		borderpane.add(new JLabel("Stege"));
		borderpane.add(border);
		
		contentpane.add(namepane);
		contentpane.add(formatpane);
		contentpane.add(widthpane);
		contentpane.add(heightpane);
		contentpane.add(borderpane);
	}
	
	public Container getContentpane() {
		return contentpane;
	}
	
}
