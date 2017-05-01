package com.ddjohn;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

import com.android.ddmlib.AdbCommandRejectedException;
import com.android.ddmlib.MultiLineReceiver;
import com.android.ddmlib.ShellCommandUnresponsiveException;
import com.android.ddmlib.TimeoutException;
import com.ddjohn.utils.DajoADB;

public class DajoShellPanel extends MultiLineReceiver implements KeyListener {

	private final JTextArea area = new JTextArea("adb> ");
	private String input = new String();
	
	public DajoShellPanel() {
		area.addKeyListener(this);
		area.setBorder(BorderFactory.createTitledBorder("Shell"));
		area.setPreferredSize(new Dimension(600, 100));
	    area.getCaret().setVisible(true);
		area.setCaretPosition(area.getText().length());
	}

	public JTextArea getArea() {
		return area;
	}
	
	@Override
	public void keyReleased(KeyEvent ke) {
		// TODO Auto-generated method stub
		if(ke.getKeyCode() == 10) {
			
			try {
				DajoADB.getInstance().executeShellCommand(input, this);
			} 
			catch(TimeoutException|AdbCommandRejectedException|ShellCommandUnresponsiveException|IOException e) {
				e.printStackTrace();
			}
			input = "";
		}
		else {
			input += ke.getKeyChar();
		}
	}

	@Override
	public void keyTyped(KeyEvent ke) {}
	
	@Override
	public void keyPressed(KeyEvent ke) {}


	@Override
	public boolean isCancelled() {
		return false;
	}

	@Override
	public void processNewLines(String[] lines) {
		for (String line : lines) {
			area.append("out> " + line + "\r\n");
		}
		
		area.append("adb> ");
		area.setCaretPosition(area.getText().length());
	}
}
