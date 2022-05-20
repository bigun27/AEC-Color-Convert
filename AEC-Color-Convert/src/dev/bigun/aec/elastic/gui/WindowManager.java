/**
 * Copyright 2022 Evan
 * Subject to the MIT License, available at https://bigun27.github.io/MITLicense.html
 */
package dev.bigun.aec.elastic.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import dev.bigun.aec.elastic.io.Reader;
import dev.bigun.aec.elastic.io.Writer;
import dev.bigun.aec.elastic.manager.ColorManager;

/**
 * Manages the Window objects
 *
 * @author Evan
 *
 */
public class WindowManager implements ActionListener {

	/** Frame to use **/
	protected static JFrame frame = new JFrame();
	/** Pass flag to settings so as to avoid message spam */
	protected static boolean errorFlag = false;
	/** Panel in the frame **/
	private JPanel panel = new JPanel();
	/** Button to start the conversion **/
	private JButton executeButton = new JButton();
	/** Text area showing the log **/
	private JTextArea log;
	/** Bar of menu options across the top */
	private JMenuBar menuBar;
	/** Menu listing in the bar for options */
	private JMenu optionsMenu;
	/** Menu listing in the bar for help */
	private JMenu helpMenu;
	/** Item in the menu for settings */
	private JMenuItem settingsMenuItem;
	/** Item in the menu for about */
	private JMenuItem aboutMenuItem;
	/** An invalid properties file */
	private boolean invalidProp = false;

	/**
	 * Constructor for the Window Manager
	 *
	 * @throws FileNotFoundException if the error file cannot be written
	 */
	public WindowManager() {

		Properties prop = new Properties();
		try (InputStream input = new FileInputStream(ColorManager.getPropFile())) {
			prop.load(input);
			ColorManager.setInputPath(prop.getProperty("input"));
			ColorManager.setOutputPath(prop.getProperty("output"));
			ColorManager.setErrorPath(prop.getProperty("error"));
			ColorManager.setNotes(Boolean.parseBoolean(prop.getProperty("notes")));
			ColorManager.setHeader(Boolean.parseBoolean(prop.getProperty("header")));
			ColorManager.setEmpty(Boolean.parseBoolean(prop.getProperty("empty")));
			ColorManager.setConvertToSpace(Boolean.parseBoolean(prop.getProperty("convertToSpace")));
			ColorManager.setBypassErrorCheck(Boolean.parseBoolean(prop.getProperty("bypassErrorCheck")));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Cannot find properties file or there are errors. "
							+ "Please fix them in the properties file or settings menu.",
					"Warning", JOptionPane.ERROR_MESSAGE);
			invalidProp = true;
		}

		if (invalidProp) {
			errorFlag = true;
			new SettingsWindow();
		}

		menuBar = new JMenuBar();
		optionsMenu = new JMenu("Options");
		optionsMenu.setMnemonic(KeyEvent.VK_O);
		optionsMenu.getAccessibleContext().setAccessibleDescription("Options menu");

		helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		helpMenu.getAccessibleContext().setAccessibleDescription("Help menu");

		menuBar.add(optionsMenu);
		menuBar.add(helpMenu);

		settingsMenuItem = new JMenuItem("Settings", KeyEvent.VK_S);
		settingsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		settingsMenuItem.getAccessibleContext().setAccessibleDescription("Opens the settings menu");
		settingsMenuItem.addActionListener(this);

		aboutMenuItem = new JMenuItem("About", KeyEvent.VK_A);
		aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		aboutMenuItem.getAccessibleContext().setAccessibleDescription("Opens the about menu");
		aboutMenuItem.addActionListener(this);

		optionsMenu.add(settingsMenuItem);
		helpMenu.add(aboutMenuItem);

		frame.setJMenuBar(menuBar);

		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBounds(500, 300, 700, 350);
		frame.setMinimumSize(new Dimension(350, 325));

		executeButton.setText("Start Conversion");
		executeButton.addActionListener(this);
		executeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		log = new JTextArea(10, 25);
		log.setAlignmentX(Component.CENTER_ALIGNMENT);
		log.setMargin(new Insets(5, 5, 5, 5));
		log.setEditable(false);
		log.append("Welcome to Elastic Color Convert\n");

		panel.add(executeButton);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(log);

		frame.add(panel);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setTitle("Elastic Color Convert");
		frame.pack();
		frame.setVisible(true);

		try {
			File f = new File(ColorManager.getInputPath());
			if (!f.exists()) {
				throw new IllegalArgumentException("The input file listed in the properties file is invalid. "
						+ "You can choose a different file for this run in settings.");
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Invalid File", JOptionPane.ERROR_MESSAGE);
			errorFlag = true;
		}

		try {
			File f = new File(ColorManager.getOutputPath());
			if (f.isDirectory()) {
				throw new IllegalArgumentException(
						"The output file listed in the properties file is actually a directory. "
								+ "You must choose a different file for this run in settings.");
			}
			if (f.exists()) {
				throw new IllegalArgumentException("The output file listed in the properties file already exists. "
						+ "If you run the program it will be erased. You can choose a different file for this run in settings.");
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
			errorFlag = true;
		}

		try {
			File f = new File(ColorManager.getErrorPath());
			if (!f.exists()) {
				throw new IllegalArgumentException("The error file listed in the properties file is invalid. "
						+ "You can choose a different file for this run in settings.");
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Invalid File", JOptionPane.ERROR_MESSAGE);
			errorFlag = true;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == settingsMenuItem) {
				new SettingsWindow();
			}
			if (e.getSource() == aboutMenuItem) {
				new AboutWindow();
			}
			if (e.getSource() == executeButton) {
				File f = new File(ColorManager.getInputPath());
				if (ColorManager.getInputPath() == null || ColorManager.getInputPath().isBlank() || f.isDirectory()) {
					throw new IllegalArgumentException();
				}
				f = new File(ColorManager.getOutputPath());
				if (ColorManager.getOutputPath() == null || ColorManager.getOutputPath().isBlank() || f.isDirectory()) {
					throw new NullPointerException();
				}
				log.setText("");
				log.append("Starting to read from file.\nThis should only take a moment.\n");
				try {
					ColorManager.setColorArray(Reader.readInputAsArray(ColorManager.getInputPath()));
				} catch (IllegalArgumentException | FileNotFoundException ee) {
					JOptionPane.showMessageDialog(new JFrame(), ee.getMessage(), "Error While Reading",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				log.append("Reading was a success.\nWriting to output.\n");
				try {
					Writer.writeColorsToFile(ColorManager.getColorArray(), ColorManager.getOutputPath());
				} catch (IllegalArgumentException | FileNotFoundException ee) {
					JOptionPane.showMessageDialog(new JFrame(), ee.getMessage(), "Error While Writing",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				log.append("\nComplete success.\n");
				JOptionPane.showMessageDialog(new JFrame(), "The conversion was a success.", "Success",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (IllegalArgumentException ee) {
			JOptionPane.showMessageDialog(new JFrame(), "The input file selected is invalid.", "Invalid Input File",
					JOptionPane.ERROR_MESSAGE);
			return;
		} catch (NullPointerException ee) {
			JOptionPane.showMessageDialog(new JFrame(), "The output file selected is invalid.", "Invalid Output File",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
}
