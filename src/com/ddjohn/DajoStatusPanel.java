package com.ddjohn;

import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.ddjohn.utils.DajoADB;
import com.ddjohn.utils.DajoUtils;


public class DajoStatusPanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 5420578714108190008L;
	
	private JTextField adbStatus = new JTextField("ADB: unknown");
	private JTextField orientation = new JTextField("Orientation: unknown");
	
	public DajoStatusPanel() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBorder(BorderFactory.createTitledBorder("Status"));
	
		this.add(new JTextField("Hello"));
		this.add(adbStatus);
		this.add(orientation);
		
		new Thread(this).start();
	}
		
	@Override
	public void run() {
		while(true) {
			if(DajoADB.getInstance().isConnected()) {
				adbStatus.setText("ADB: connected");
			}
			else {
				adbStatus.setText("ADB: disconnected");
			}
			
			if(DajoADB.getLandscape()) {
				orientation.setText("Orientation: landscpe");
			}
			else {
				orientation.setText("Orientation: portraite");
			}
			
			DajoUtils.sleep(2000);
		}
	}
}
