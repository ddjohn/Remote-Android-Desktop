package com.ddjohn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;

import com.ddjohn.utils.DajoADB;

public class DajoToolBar extends JToolBar implements ActionListener {

	private static final long serialVersionUID = 3185317197501200267L;
	private final static Logger log = Logger.getLogger(DajoToolBar.class.getName());

	public DajoToolBar() throws IOException {
		this.add(new DajoButton(this, "portrait", new ImageIcon(ImageIO.read(new File("img/Portrait.png")))));
		this.add(new DajoButton(this, "landscape", new ImageIcon(ImageIO.read(new File("img/Landscape.png")))));
		this.setBorder(BorderFactory.createTitledBorder("Toolbar"));

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
			DajoButton b = (DajoButton) ae.getSource();
			log.severe("Button: " + b.getName());

			DajoADB.setLandscape(b.getName().compareTo("landscape") == 0);		
	}
}
