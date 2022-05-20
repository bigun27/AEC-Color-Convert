/**
 * Copyright 2022 Evan
 * Subject to the MIT License, available at https://bigun27.github.io/MITLicense.html
 */
package dev.bigun.aec.elastic.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;

import dev.bigun.aec.elastic.data.Color;
import dev.bigun.aec.elastic.manager.ColorManager;

/**
 * Writes colors to a file
 *
 * @author Evan
 */
public class Writer {

	/**
	 * Writes the given array of Colors to file
	 *
	 * @param colorArray array of Colors to write
	 * @param fileName   file to write to
	 * @throws FileNotFoundException if the file cannot be opened
	 */
	public static void writeColorsToFile(Color[] colorArray, String fileName) throws FileNotFoundException {

		if (colorArray == null || fileName == null) {
			throw new IllegalArgumentException("Invalid output file");
		}

		try (PrintStream fileWriter = new PrintStream(new File(fileName))) {
			if (ColorManager.getHeader()) {
				if (ColorManager.getNotes()) {
					fileWriter.println(
							"CODE,DESC,MFG,NOTES,MFG_CODE,YCOUNT1," + "YCOUNT2,YCOUNT3,YCOUNT4,YCOUNT5,YCOUNT6,YCOUNT7,"
									+ "YCOUNT8,MFG1,MFG2,MFG3,MFG4,MFG5,MFG6,MFG7,MFG8,"
									+ "MFG_CODE1,MFG_CODE2,MFG_CODE3,MFG_CODE4,MFG_CODE5,"
									+ "MFG_CODE6,MFG_CODE7,MFG_CODE8,ITEM_CODE1,ITEM_CODE2,"
									+ "ITEM_CODE3,ITEM_CODE4,ITEM_CODE5,ITEM_CODE6," + "ITEM_CODE7,ITEM_CODE8");
				} else {
					fileWriter.println(
							"CODE,DESC,MFG,MFG_CODE,YCOUNT1," + "YCOUNT2,YCOUNT3,YCOUNT4,YCOUNT5,YCOUNT6,YCOUNT7,"
									+ "YCOUNT8,MFG1,MFG2,MFG3,MFG4,MFG5,MFG6,MFG7,MFG8,"
									+ "MFG_CODE1,MFG_CODE2,MFG_CODE3,MFG_CODE4,MFG_CODE5,"
									+ "MFG_CODE6,MFG_CODE7,MFG_CODE8,ITEM_CODE1,ITEM_CODE2,"
									+ "ITEM_CODE3,ITEM_CODE4,ITEM_CODE5,ITEM_CODE6," + "ITEM_CODE7,ITEM_CODE8");
				}
			}
			for (int i = 0; i < Reader.getIndex(); i++) {
				if (colorArray[i] != null) {
					if (ColorManager.getEmpty() && colorArray[i].isEmpty()) {
						continue;
					}
					if (!ColorManager.getGUI()) {
						System.out.print("Writing color " + (i + 1) + " of " + Reader.getIndex() + "\r");
						System.out.flush();
					}
					fileWriter.println(colorArray[i].toCSV());
				}
			}
			fileWriter.close();
			if (!ColorManager.getGUI()) {
				System.out.print("\n");
			}
		} catch (FileNotFoundException e) {
			if (!ColorManager.getGUI()) {
				System.out.print("\n");
			}
			PrintWriter writer = new PrintWriter(ColorManager.getErrorPath());
			writer.write("Input was processed without error but the output file cannot be written");
			writer.close();
			throw new FileNotFoundException("Input was processed without error but the output file cannot be written");
		}
	}
}
