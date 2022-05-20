/**
 * Copyright 2022 Evan
 * Subject to the MIT License, available at https://bigun27.github.io/MITLicense.html
 */
package dev.bigun.aec.elastic.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Controls the about window
 *
 * @author Evan
 *
 */
public class AboutWindow {

	/**
	 * Constructor for the about window
	 */
	public AboutWindow() {

		JDialog dialog = new JDialog(WindowManager.frame, ModalityType.DOCUMENT_MODAL);
		dialog.setTitle("About");
		dialog.setBounds(500, 300, 600, 150);
		dialog.setMinimumSize(new Dimension(600, 150));

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		dialog.add(panel);

		JLabel label1 = new JLabel("Elastic Color Convert - Version 1.2.4 (22.05.20)");
		label1.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel label2 = new JLabel("Copyright (c) 2022 Evan");
		label2.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel label3 = new JLabel("The source code is released under the ");
		JLabel label4 = new JLabel("MIT license");
		JPanel combine1 = new JPanel();
		combine1.setLayout(new BoxLayout(combine1, BoxLayout.X_AXIS));
		combine1.add(label3);
		combine1.add(label4);
		combine1.setAlignmentX(Component.CENTER_ALIGNMENT);
		label4.setForeground(Color.BLUE.darker());
		label4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label4.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://bigun27.github.io/MITLicense.html"));
				} catch (IOException | URISyntaxException e1) {
					JOptionPane.showMessageDialog(new JFrame(),
							"Default internet browser cannot be found " + "so the link cannot be opened.",
							"Browser Not Found", JOptionPane.ERROR_MESSAGE);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});

		JLabel label5 = new JLabel("Source code is available at ");
		JLabel label6 = new JLabel("https://github.com/bigun27/AEC-Elastic-Color-Convert");
		JPanel combine2 = new JPanel();
		combine2.setLayout(new BoxLayout(combine2, BoxLayout.X_AXIS));
		combine2.add(label5);
		combine2.add(label6);
		combine2.setAlignmentX(Component.CENTER_ALIGNMENT);
		label6.setForeground(Color.BLUE.darker());
		label6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label6.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://github.com/bigun27/AEC-Elastic-Color-Convert"));
				} catch (IOException | URISyntaxException e1) {
					JOptionPane.showMessageDialog(new JFrame(),
							"Default internet browser cannot be found " + "so the link cannot be opened.",
							"Browser Not Found", JOptionPane.ERROR_MESSAGE);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});

		panel.add(label1);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(label2);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(combine1);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(combine2);

		dialog.setVisible(true);
	}
}
