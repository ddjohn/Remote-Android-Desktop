package com.ddjohn;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class DajoButton extends JButton {

	private static final long serialVersionUID = -3815503760910699622L;

	DajoButton(ActionListener clazz, String name, ImageIcon imageIcon) {
		super(imageIcon);
		this.addActionListener(clazz);
		this.setName(name);
	}
}

