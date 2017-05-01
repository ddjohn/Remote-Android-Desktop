package com.ddjohn.utils;

import java.awt.Font;

import javax.swing.UIManager;

public class DajoUtils {

	public static boolean sleep(int ms) {
		try {
			Thread.sleep(ms);
			return true;
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}	
	}

	public static void lookAndFeel() {
		UIManager.put("TextField.font", new Font("Serif", Font.PLAIN, 36));
		UIManager.put("Menu.font", new Font("Serif", Font.PLAIN, 36));
		UIManager.put("MenuItem.font", new Font("Serif", Font.PLAIN, 36));
		UIManager.put("Button.font", new Font("Serif", Font.PLAIN, 36));
		UIManager.put("TextArea.font", new Font("Courier", Font.PLAIN, 24));
		UIManager.put("TitledBorder.font",  new Font("Serif", Font.PLAIN, 18));
		
		UIManager.put("OptionPane.font",  new Font("Serif", Font.PLAIN, 36));
	}
}
