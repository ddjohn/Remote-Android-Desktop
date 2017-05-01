package com.ddjohn;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import com.android.ddmlib.AdbCommandRejectedException;
import com.android.ddmlib.ShellCommandUnresponsiveException;
import com.android.ddmlib.TimeoutException;
import com.ddjohn.utils.DajoUtils;

public class DajoMain extends JFrame implements WindowListener {

	private static final long serialVersionUID = 6049532511605286252L;
	private static final String TITLE = "Android Remote Desktop";
	
	DajoMain() throws InterruptedException, TimeoutException, AdbCommandRejectedException, IOException, ShellCommandUnresponsiveException {
		super(TITLE);

		this.getContentPane().add(new DajoMainPanel(), BorderLayout.CENTER);
		this.getContentPane().add(new DajoStatusPanel(), BorderLayout.SOUTH);
		this.getContentPane().add(new JScrollPane(new DajoShellPanel().getArea(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.EAST);
		this.getContentPane().add(new DajoToolBar(), BorderLayout.NORTH);
		
		this.setJMenuBar(new DajoMenu());
		
		this.setIconImage(ImageIO.read(new File("img/Android.png")));
		
		this.addWindowListener(this);
		this.setVisible(true);
		this.pack();
	}
	
	public void windowClosing(WindowEvent we) {
		if(JOptionPane.showConfirmDialog(this, "Are you sure?", "Exiting!", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
			System.exit(0);
	}
	
	public static void main(String args[]) throws InterruptedException, TimeoutException, AdbCommandRejectedException, IOException, ShellCommandUnresponsiveException {
		DajoUtils.lookAndFeel();
		new DajoMain();
	}

	public void windowActivated(WindowEvent we) {}
	public void windowClosed(WindowEvent we) {}
	public void windowDeactivated(WindowEvent we) {}
	public void windowDeiconified(WindowEvent we) {}
	public void windowIconified(WindowEvent we) {}
	public void windowOpened(WindowEvent we) {}
}
