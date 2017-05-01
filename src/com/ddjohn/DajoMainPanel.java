package com.ddjohn;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;
import javax.swing.JPanel;
import com.ddjohn.utils.DajoADB;
import com.ddjohn.utils.DajoUtils;

public class DajoMainPanel extends JPanel implements Runnable {

	private static final long serialVersionUID = -5875211743686897390L;
	private final static Logger log = Logger.getLogger(DajoMainPanel.class.getName());

	private DajoADB adb = DajoADB.getInstance();
	BufferedImage image = null;
	
	public DajoMainPanel() {
		this.setPreferredSize(new Dimension(600, 600));
		new Thread(this).start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		log.fine("paint");

		if(image != null)
			g.drawImage(image.getScaledInstance(this.getWidth(),  this.getHeight(), Image.SCALE_SMOOTH), 0, 0, this);
	}

	public void run() {
		while(true) {
			this.repaint();
			image = adb.getImage();
			log.info("repaint");
			this.repaint();
			//DajoUtils.sleep(1000);
		}
	}
}
