package com.ddjohn;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class DajoMenu extends JMenuBar {

	private static final long serialVersionUID = 1086697550657909998L;
	private JMenu file = new JMenu("File");
	private JMenu about = new JMenu("About");
	
	public DajoMenu() {
		this.add(file);
		file.add(new JMenuItem("Exit..."));
		
		this.add(about);
	}
}
