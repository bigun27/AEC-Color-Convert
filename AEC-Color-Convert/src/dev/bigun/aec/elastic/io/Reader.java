/**
 * Copyright 2022 Evan
 * Subject to the MIT License, available at https://bigun27.github.io/MITLicense.html
 */
package dev.bigun.aec.elastic.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import dev.bigun.aec.elastic.data.Color;
import dev.bigun.aec.elastic.manager.ColorManager;

/**
 * Reads colors from the database file
 *
 * @author Evan
 */
public class Reader {

	/** The number of records stored */
	private static int index;
	/** The number of records processed, including empty */
	private static int count;
	/** The number of records as recorded by the database */
	private static long realCount;

	/**
	 * Gets the index
	 *
	 * @return the index
	 */
	public static int getIndex() {
		return index;
	}

	/**
	 * Returns the input file as an array of Color objects when given a file path
	 *
	 * @param filePath - the path to the input CSV file
	 * @return an array of Color objects
	 * @throws FileNotFoundException    if the file cannot be opened
	 * @throws IllegalArgumentException if there is an unexpected issue
	 */
	public static Color[] readInputAsArray(String filePath) throws FileNotFoundException, IllegalArgumentException {

		if (filePath == null) {
			throw new IllegalArgumentException("Invalid input file");
		}

		Color[] list = new Color[1000];
		try (FileInputStream file = new FileInputStream(filePath)) {
			index = 0;
			count = 0;
			file.skip(4);
			byte[] bytes = new byte[4];
			bytes[3] = (byte) file.read();
			bytes[2] = (byte) file.read();
			bytes[1] = (byte) file.read();
			bytes[0] = (byte) file.read();
			realCount = ByteBuffer.wrap(bytes).getInt();
			bytes[1] = (byte) file.read();
			bytes[0] = (byte) file.read();
			final short headerSize = ByteBuffer.wrap(bytes).getShort();
			bytes[1] = (byte) file.read();
			bytes[0] = (byte) file.read();
			final short recordSize = ByteBuffer.wrap(bytes).getShort();
			final long estimatedCount = (Files.size(Paths.get(filePath)) - headerSize) / recordSize;
			final long estimatedCountR = (Files.size(Paths.get(filePath)) - headerSize) % recordSize;
			if (!ColorManager.getBypassErrorCheck() && (realCount != estimatedCount || estimatedCountR > 1)) {
				PrintWriter writer = new PrintWriter(ColorManager.getErrorPath());
				writer.write("The file is improperly sized, the table could be corrupted.");
				writer.close();
				throw new IllegalArgumentException("The file is improperly sized, the table could be corrupted.");
			}
			file.skip(headerSize - 12 + 1);
			byte[] array = new byte[recordSize + 1];
			int i = 0;
			while ((i = file.readNBytes(array, 0, recordSize - 1)) == recordSize - 1) {
				if (!ColorManager.getBypassErrorCheck() && i < recordSize - 1) {
					PrintWriter writer = new PrintWriter(ColorManager.getErrorPath());
					writer.write("Record " + (index + 1) + " is too short! It should be " + (recordSize - 1)
							+ " but it is " + i);
					writer.close();
					throw new IllegalArgumentException("Record " + index + " is too short!");
				}
				if (index >= list.length) {
					list = Arrays.copyOf(list, list.length * 2 + 1);
				}
				count++;
				Color holdColor = processLine(array);
				if (holdColor != null) {
					list[index] = holdColor;
					index++;
				}
				file.skip(1);
			}
		} catch (FileNotFoundException e) {
			if (!ColorManager.getGUI()) {
				System.out.print("\n");
			}
			PrintWriter writer = new PrintWriter(ColorManager.getErrorPath());
			writer.write(e.toString());
			writer.close();
			throw new FileNotFoundException("File not found: " + e.getMessage());
		} catch (IOException e) {
			if (!ColorManager.getGUI()) {
				System.out.print("\n");
			}
			PrintWriter writer = new PrintWriter(ColorManager.getErrorPath());
			writer.write(e.toString() + ". This often occurs when trying to process an invalid file.");
			writer.close();
			throw new IllegalArgumentException(
					"IO issue: " + e.getMessage() + ". This often occurs when trying to process an invalid file.");
		}
		if (!ColorManager.getGUI()) {
			System.out.print("\n");
		}
		return list;
	}

	/**
	 * Processes a single line from the input file to construct an Color.
	 *
	 * @param line the input line from the input file
	 * @return a Color representation of the input line
	 */
	private static Color processLine(byte[] line) {

		if (!ColorManager.getGUI()) {
			System.out.print("Reading color " + count + " of " + realCount + "\r");
			System.out.flush();
		}

		if (ColorManager.getConvertToSpace()) {
			for (int i = 0; i < line.length; i++) {
				if (Character.isWhitespace(line[i])) {
					line[i] = ' ';
				}
			}
		}

		int pos = 0;
		String code = new String(Arrays.copyOfRange(line, pos, pos + 4));
		pos += 4;
		String desc = new String(Arrays.copyOfRange(line, pos, pos + 30));
		pos += 30;
		String mfg = new String(Arrays.copyOfRange(line, pos, pos + 20));
		pos += 20;
		String notes = new String(Arrays.copyOfRange(line, pos, pos + 4));
		pos += 4;
		String mfgCode = new String(Arrays.copyOfRange(line, pos, pos + 15));
		pos += 15;
		String ycount1 = new String(Arrays.copyOfRange(line, pos, pos + 8));
		pos += 8;
		String ycount2 = new String(Arrays.copyOfRange(line, pos, pos + 8));
		pos += 8;
		String ycount3 = new String(Arrays.copyOfRange(line, pos, pos + 8));
		pos += 8;
		String ycount4 = new String(Arrays.copyOfRange(line, pos, pos + 8));
		pos += 8;
		String ycount5 = new String(Arrays.copyOfRange(line, pos, pos + 8));
		pos += 8;
		String ycount6 = new String(Arrays.copyOfRange(line, pos, pos + 8));
		pos += 8;
		String ycount7 = new String(Arrays.copyOfRange(line, pos, pos + 8));
		pos += 8;
		String ycount8 = new String(Arrays.copyOfRange(line, pos, pos + 8));
		pos += 8;
		String mfg1 = new String(Arrays.copyOfRange(line, pos, pos + 20));
		pos += 20;
		String mfg2 = new String(Arrays.copyOfRange(line, pos, pos + 20));
		pos += 20;
		String mfg3 = new String(Arrays.copyOfRange(line, pos, pos + 20));
		pos += 20;
		String mfg4 = new String(Arrays.copyOfRange(line, pos, pos + 20));
		pos += 20;
		String mfg5 = new String(Arrays.copyOfRange(line, pos, pos + 20));
		pos += 20;
		String mfg6 = new String(Arrays.copyOfRange(line, pos, pos + 20));
		pos += 20;
		String mfg7 = new String(Arrays.copyOfRange(line, pos, pos + 20));
		pos += 20;
		String mfg8 = new String(Arrays.copyOfRange(line, pos, pos + 20));
		pos += 20;
		String mfgCode1 = new String(Arrays.copyOfRange(line, pos, pos + 15));
		pos += 15;
		String mfgCode2 = new String(Arrays.copyOfRange(line, pos, pos + 15));
		pos += 15;
		String mfgCode3 = new String(Arrays.copyOfRange(line, pos, pos + 15));
		pos += 15;
		String mfgCode4 = new String(Arrays.copyOfRange(line, pos, pos + 15));
		pos += 15;
		String mfgCode5 = new String(Arrays.copyOfRange(line, pos, pos + 15));
		pos += 15;
		String mfgCode6 = new String(Arrays.copyOfRange(line, pos, pos + 15));
		pos += 15;
		String mfgCode7 = new String(Arrays.copyOfRange(line, pos, pos + 15));
		pos += 15;
		String mfgCode8 = new String(Arrays.copyOfRange(line, pos, pos + 15));
		pos += 15;
		String itemCode1 = new String(Arrays.copyOfRange(line, pos, pos + 15));
		pos += 15;
		String itemCode2 = new String(Arrays.copyOfRange(line, pos, pos + 15));
		pos += 15;
		String itemCode3 = new String(Arrays.copyOfRange(line, pos, pos + 15));
		pos += 15;
		String itemCode4 = new String(Arrays.copyOfRange(line, pos, pos + 15));
		pos += 15;
		String itemCode5 = new String(Arrays.copyOfRange(line, pos, pos + 15));
		pos += 15;
		String itemCode6 = new String(Arrays.copyOfRange(line, pos, pos + 15));
		pos += 15;
		String itemCode7 = new String(Arrays.copyOfRange(line, pos, pos + 15));
		pos += 15;
		String itemCode8 = new String(Arrays.copyOfRange(line, pos, pos + 15));
		pos += 15;
		boolean empty = true;
		for (int i = 0; i < line.length; i++) {
			if (ColorManager.getNotes()) {
				if (i >= 54 && i < 58) {
					continue;
				}
			}
			if (line[i] != ' ' && line[i] != 0x0) {
				empty = false;
				break;
			}
		}

		if (!ColorManager.getEmpty() && empty) {
			return null;
		}

		return new Color(code, desc, mfg, notes, mfgCode, ycount1, ycount2, ycount3, ycount4, ycount5, ycount6, ycount7,
				ycount8, mfg1, mfg2, mfg3, mfg4, mfg5, mfg6, mfg7, mfg8, mfgCode1, mfgCode2, mfgCode3, mfgCode4,
				mfgCode5, mfgCode6, mfgCode7, mfgCode8, itemCode1, itemCode2, itemCode3, itemCode4, itemCode5,
				itemCode6, itemCode7, itemCode8, empty);

	}
}
