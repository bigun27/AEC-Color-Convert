/**
 * Copyright 2022 Evan
 * Subject to the MIT License, available at https://bigun27.github.io/MITLicense.html
 */
package dev.bigun.aec.elastic.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import dev.bigun.aec.elastic.data.Color;
import dev.bigun.aec.elastic.gui.WindowManager;
import dev.bigun.aec.elastic.io.Reader;
import dev.bigun.aec.elastic.io.Writer;

/**
 * Manages and exposes functions of the program
 *
 * @author Evan
 */
public class ColorManager {
	/** File containing the properties */
	private static final String PROP_FILE = "properties/elastic.properties";
	/** String path to input file */
	private static String inputPath = "";
	/** String path to output file */
	private static String outputPath = "";
	/** Property to include notes */
	private static boolean notes = false;
	/** Property to include a header line */
	private static boolean header = false;
	/** Property to include empty lines */
	private static boolean empty = false;
	/** Property to convert whitespace to spaces */
	private static boolean convertToSpace = false;
	/** Property to include error check bypassing */
	private static boolean bypassErrorCheck = false;
	/** String path to error file */
	private static String errorPath = "error/error.txt";
	/** Array of Color objects */
	private static Color[] colorArray = null;
	/** True if GUI is enabled */
	private static boolean gui = false;

	/**
	 * Gets the bypassErrorCheck
	 *
	 * @return the bypassErrorCheck
	 */
	public static boolean getBypassErrorCheck() {
		return bypassErrorCheck;
	}

	/**
	 * Gets the colorArray
	 *
	 * @return the colorArray
	 */
	public static Color[] getColorArray() {
		return colorArray;
	}

	/**
	 * Gets the convertToSpace
	 *
	 * @return the convertToSpace
	 */
	public static boolean getConvertToSpace() {
		return convertToSpace;
	}

	/**
	 * Gets the empty
	 *
	 * @return the empty
	 */
	public static boolean getEmpty() {
		return empty;
	}

	/**
	 * Gets the errorPath
	 *
	 * @return the errorPath
	 */
	public static String getErrorPath() {
		return errorPath;
	}

	/**
	 * Gets the gui
	 *
	 * @return the gui
	 */
	public static boolean getGUI() {
		return gui;
	}

	/**
	 * Gets the header
	 *
	 * @return the header
	 */
	public static boolean getHeader() {
		return header;
	}

	/**
	 * Gets the input path
	 *
	 * @return the input path
	 */
	public static String getInputPath() {
		return inputPath;
	}

	/**
	 * Gets the notes
	 *
	 * @return the notes
	 */
	public static boolean getNotes() {
		return notes;
	}

	/**
	 * Gets the output path
	 *
	 * @return the output path
	 */
	public static String getOutputPath() {
		return outputPath;
	}

	/**
	 * Gets the properties file path
	 *
	 * @return the properties file path
	 */
	public static String getPropFile() {
		return PROP_FILE;
	}

	/**
	 * Starts the program, main flow logic
	 *
	 * @param args Command line arguments
	 * @throws IOException              throws if file cannot be found
	 * @throws NullPointerException     if any required properties are not given
	 * @throws IllegalArgumentException if there is an unexpected issue
	 */
	public static void main(String[] args) {
		try {
			if (args.length == 1 && "--gui".equals(args[0])) {
				gui = true;
				new WindowManager();
			} else {
				Properties prop = new Properties();
				try (InputStream input = new FileInputStream(PROP_FILE)) {
					prop.load(input);
					inputPath = prop.getProperty("input");
					outputPath = prop.getProperty("output");
					errorPath = prop.getProperty("error");
					notes = Boolean.parseBoolean(prop.getProperty("notes"));
					header = Boolean.parseBoolean(prop.getProperty("header"));
					empty = Boolean.parseBoolean(prop.getProperty("empty"));
					convertToSpace = Boolean.parseBoolean(prop.getProperty("convertToSpace"));
					bypassErrorCheck = Boolean.parseBoolean(prop.getProperty("bypassErrorCheck"));
				} catch (IOException | NullPointerException e) {
					String formattedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
					PrintWriter writer = new PrintWriter("error/error" + formattedDate + ".txt");
					writer.write("Cannot find properties file or there are errors.");
					writer.close();
					throw new IllegalArgumentException("Cannot find properties file or there are errors.");
				}
				System.out.println("Welcome to Elastic Color Convert\n");
				System.out.println("Starting to read from file. This should only take a moment.");
				colorArray = Reader.readInputAsArray(inputPath);

				// for (int i = 0; i < Reader.getIndex(); i++) {
				// if (colorArray[i] != null) {
				// if (empty.equals("1") && colorArray[i].isEmpty()) {
				// System.out.println("Found one! " + (i + 1));
				// continue;
				// }
				// System.out.println(colorArray[i].toCSV());
				// }
				// }

				System.out.println("Reading was a success. Writing to output.");
				Writer.writeColorsToFile(colorArray, outputPath);
				System.out.println("Complete success. Closing program.");
			}
		} catch (IOException e) {
			System.out.println("There was an unrecoverable file-related error");
			return;
		}
	}

	/**
	 * Sets the bypassErrorCheck
	 *
	 * @param bypassErrorCheck the bypassErrorCheck to set
	 */
	public static void setBypassErrorCheck(boolean bypassErrorCheck) {
		ColorManager.bypassErrorCheck = bypassErrorCheck;
	}

	/**
	 * Sets the colorArray
	 *
	 * @param colorArray the colorArray to set
	 */
	public static void setColorArray(Color[] colorArray) {
		ColorManager.colorArray = colorArray;
	}

	/**
	 * Sets the convertToSpace
	 *
	 * @param convertToSpace the convertToSpace to set
	 */
	public static void setConvertToSpace(boolean convertToSpace) {
		ColorManager.convertToSpace = convertToSpace;
	}

	/**
	 * Sets the empty
	 *
	 * @param empty the empty to set
	 */
	public static void setEmpty(boolean empty) {
		ColorManager.empty = empty;
	}

	/**
	 * Sets the errorPath
	 *
	 * @param errorPath the errorPath to set
	 */
	public static void setErrorPath(String errorPath) {
		ColorManager.errorPath = errorPath;
	}

	/**
	 * Sets the header
	 *
	 * @param header the header to set
	 */
	public static void setHeader(boolean header) {
		ColorManager.header = header;
	}

	/**
	 * Sets the inputPath
	 *
	 * @param inputPath the inputPath to set
	 */
	public static void setInputPath(String inputPath) {
		ColorManager.inputPath = inputPath;
	}

	/**
	 * Sets the notes
	 *
	 * @param notes the notes to set
	 */
	public static void setNotes(boolean notes) {
		ColorManager.notes = notes;
	}

	/**
	 * Sets the outputPath
	 *
	 * @param outputPath the outputPath to set
	 */
	public static void setOutputPath(String outputPath) {
		ColorManager.outputPath = outputPath;
	}
}
