/**
 * Copyright 2022 Evan
 * Subject to the MIT License, available at https://bigun27.github.io/MITLicense.html
 */
package dev.bigun.aec.elastic.gui;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.filechooser.FileNameExtensionFilter;

import dev.bigun.aec.elastic.manager.ColorManager;

/**
 * Controls the settings window
 *
 * @author Evan
 *
 */
public class SettingsWindow {

	/**
	 * Constructor for the settings window
	 */
	public SettingsWindow() {
		JFileChooser inputFile = new JFileChooser();
		inputFile.setMultiSelectionEnabled(false);
		inputFile.setFileFilter(new FileNameExtensionFilter("DBF Database File", "dbf", "DBF"));
		JFileChooser outputFile = new JFileChooser();
		outputFile.setMultiSelectionEnabled(false);
		outputFile.setFileFilter(new FileNameExtensionFilter("CSV Comma Delimited", "csv", "CSV"));
		JFileChooser errorFile = new JFileChooser();
		errorFile.setMultiSelectionEnabled(false);
		errorFile.setFileFilter(new FileNameExtensionFilter("TXT Text File", "txt", "TXT"));

		try {
			inputFile.setSelectedFile(new File(ColorManager.getInputPath()));
			if (!WindowManager.errorFlag && !inputFile.getSelectedFile().exists()) {
				throw new IllegalArgumentException("The input file listed in the properties file is invalid.");
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Invalid File", JOptionPane.ERROR_MESSAGE);
		}

		try {
			outputFile.setSelectedFile(new File(ColorManager.getOutputPath()));
			if (!WindowManager.errorFlag && outputFile.getSelectedFile().isDirectory()) {
				throw new IllegalArgumentException("The output file listed in the properties file is invalid.");
			}
			if (!WindowManager.errorFlag && outputFile.getSelectedFile().exists()) {
				throw new IllegalArgumentException(
						"The output file listed in the properties file already exists. It will be deleted.");
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Invalid File", JOptionPane.ERROR_MESSAGE);
		}

		try {
			errorFile.setSelectedFile(new File(ColorManager.getErrorPath()));
			if (!WindowManager.errorFlag && !errorFile.getSelectedFile().exists()) {
				throw new IllegalArgumentException("The error file listed in the properties file is invalid.");
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Invalid File", JOptionPane.ERROR_MESSAGE);
		}
		WindowManager.errorFlag = false;

		JDialog dialog = new JDialog(WindowManager.frame, ModalityType.DOCUMENT_MODAL);
		dialog.setTitle("Settings");
		dialog.setLayout(null);
		dialog.setBounds(500, 300, 700, 350);

		JLabel inputPathLabel = new JLabel("Input file location");
		inputPathLabel.setBounds(15, 20, 420, 25);
		JLabel outputPathLabel = new JLabel("Output file location");
		outputPathLabel.setBounds(15, 50, 420, 25);
		JLabel errorPathLabel = new JLabel("Errors are written to a file at this location");
		errorPathLabel.setBounds(15, 80, 420, 25);
		JLabel notesLabel = new JLabel("Include the 'notes' column (can cause display issues)");
		notesLabel.setBounds(15, 110, 420, 25);
		JLabel headerLabel = new JLabel("Include a header row with the names of each column");
		headerLabel.setBounds(15, 140, 420, 25);
		JLabel emptyLabel = new JLabel("Include empty rows");
		emptyLabel.setBounds(15, 170, 420, 25);
		JLabel convertToSpaceLabel = new JLabel("Convert all whitespace characters into spaces");
		convertToSpaceLabel.setBounds(15, 200, 420, 25);
		JLabel bypassLabel = new JLabel("Bypass error checking of the input file for table corruption");
		bypassLabel.setBounds(15, 230, 420, 25);

		JButton inputButton = new JButton("Select File");
		inputButton.setBounds(530, 20, 155, 25);
		inputButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = inputFile.showOpenDialog(new JFrame());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					if (inputFile.getSelectedFile() == null || !inputFile.getSelectedFile().exists()) {
						JOptionPane.showMessageDialog(new JFrame(), "The input file selected is invalid.",
								"Invalid Input File", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
		});

		JButton outputButton = new JButton("Select File");
		outputButton.setBounds(530, 50, 155, 25);
		outputButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = outputFile.showSaveDialog(new JFrame());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					if (outputFile.getSelectedFile() == null) {
						JOptionPane.showMessageDialog(new JFrame(), "The output file selected is invalid.",
								"Invalid Output File", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (outputFile.getSelectedFile().isDirectory()) {
						JOptionPane.showMessageDialog(new JFrame(),
								"The output file selected is a directory. " + "You must choose a different file.",
								"Directory", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (outputFile.getSelectedFile().exists()) {
						JOptionPane.showMessageDialog(new JFrame(),
								"The output file selected already exists. "
										+ "If you do not change it, the original will be deleted.",
								"Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

		JButton errorButton = new JButton("Select File");
		errorButton.setBounds(530, 80, 155, 25);
		errorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = errorFile.showSaveDialog(new JFrame());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					if (errorFile.getSelectedFile() == null) {
						JOptionPane.showMessageDialog(new JFrame(), "The error file selected is invalid.",
								"Invalid Error File", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (errorFile.getSelectedFile().isDirectory()) {
						JOptionPane.showMessageDialog(new JFrame(),
								"The error file selected is a directory. " + "You must choose a different file.",
								"Directory", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (errorFile.getSelectedFile().exists()) {
						JOptionPane.showMessageDialog(new JFrame(),
								"The error file selected already exists. "
										+ "If you do not change it, the original will be deleted.",
								"Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

		JRadioButton notesTrue = new JRadioButton("True");
		notesTrue.setBounds(530, 110, 75, 25);
		JRadioButton notesFalse = new JRadioButton("False");
		notesFalse.setBounds(610, 110, 75, 25);
		notesTrue.setSelected(ColorManager.getNotes());
		notesFalse.setSelected(!ColorManager.getNotes());
		ButtonGroup notesGroup = new ButtonGroup();
		notesGroup.add(notesTrue);
		notesGroup.add(notesFalse);

		JRadioButton headerTrue = new JRadioButton("True");
		headerTrue.setBounds(530, 140, 75, 25);
		JRadioButton headerFalse = new JRadioButton("False");
		headerFalse.setBounds(610, 140, 75, 25);
		headerTrue.setSelected(ColorManager.getHeader());
		headerFalse.setSelected(!ColorManager.getHeader());
		ButtonGroup headerGroup = new ButtonGroup();
		headerGroup.add(headerTrue);
		headerGroup.add(headerFalse);

		JRadioButton emptyTrue = new JRadioButton("True");
		emptyTrue.setBounds(530, 170, 75, 25);
		JRadioButton emptyFalse = new JRadioButton("False");
		emptyFalse.setBounds(610, 170, 75, 25);
		emptyTrue.setSelected(ColorManager.getEmpty());
		emptyFalse.setSelected(!ColorManager.getEmpty());
		ButtonGroup emptyGroup = new ButtonGroup();
		emptyGroup.add(emptyTrue);
		emptyGroup.add(emptyFalse);

		JRadioButton convertToSpaceTrue = new JRadioButton("True");
		convertToSpaceTrue.setBounds(530, 200, 75, 25);
		JRadioButton convertToSpaceFalse = new JRadioButton("False");
		convertToSpaceFalse.setBounds(610, 200, 75, 25);
		convertToSpaceTrue.setSelected(ColorManager.getConvertToSpace());
		convertToSpaceFalse.setSelected(!ColorManager.getConvertToSpace());
		ButtonGroup convertToSpaceGroup = new ButtonGroup();
		convertToSpaceGroup.add(convertToSpaceTrue);
		convertToSpaceGroup.add(convertToSpaceFalse);

		JRadioButton bypassTrue = new JRadioButton("True");
		bypassTrue.setBounds(530, 230, 75, 25);
		JRadioButton bypassFalse = new JRadioButton("False");
		bypassFalse.setBounds(610, 230, 75, 25);
		bypassTrue.setSelected(ColorManager.getBypassErrorCheck());
		bypassFalse.setSelected(!ColorManager.getBypassErrorCheck());
		ButtonGroup bypassGroup = new ButtonGroup();
		bypassGroup.add(bypassTrue);
		bypassGroup.add(bypassFalse);

		JButton saveButton = new JButton("Save");
		saveButton.setBounds(300, 275, 100, 25);
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ColorManager.setInputPath(inputFile.getSelectedFile().getPath());
				ColorManager.setOutputPath(outputFile.getSelectedFile().getPath());
				ColorManager.setErrorPath(errorFile.getSelectedFile().getPath());
				ColorManager.setNotes(notesTrue.isSelected());
				ColorManager.setHeader(headerTrue.isSelected());
				ColorManager.setEmpty(emptyTrue.isSelected());
				ColorManager.setConvertToSpace(convertToSpaceTrue.isSelected());
				ColorManager.setBypassErrorCheck(bypassTrue.isSelected());
				dialog.setVisible(false);
			}
		});

		dialog.add(inputPathLabel);
		dialog.add(inputButton);

		dialog.add(outputPathLabel);
		dialog.add(outputButton);

		dialog.add(errorPathLabel);
		dialog.add(errorButton);

		dialog.add(notesLabel);
		dialog.add(notesTrue);
		dialog.add(notesFalse);

		dialog.add(headerLabel);
		dialog.add(headerTrue);
		dialog.add(headerFalse);

		dialog.add(emptyLabel);
		dialog.add(emptyTrue);
		dialog.add(emptyFalse);

		dialog.add(convertToSpaceLabel);
		dialog.add(convertToSpaceTrue);
		dialog.add(convertToSpaceFalse);

		dialog.add(bypassLabel);
		dialog.add(bypassTrue);
		dialog.add(bypassFalse);

		dialog.add(saveButton);

		dialog.setVisible(true);
	}
}
