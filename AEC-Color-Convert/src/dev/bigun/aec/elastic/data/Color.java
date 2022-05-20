/**
 * Copyright 2022 Evan
 * Subject to the MIT License, available at https://bigun27.github.io/MITLicense.html
 */
package dev.bigun.aec.elastic.data;

import dev.bigun.aec.elastic.manager.ColorManager;

/**
 * Contains information about each color
 *
 * @author Evan
 */
public class Color {

	/** Field in Color record */
	private String code;
	/** Field in Color record */
	private String desc;
	/** Field in Color record */
	private String mfg;
	/** Field in Color record */
	private String notes;
	/** Field in Color record */
	private String mfgCode;
	/** Field in Color record */
	private String ycount1;
	/** Field in Color record */
	private String ycount2;
	/** Field in Color record */
	private String ycount3;
	/** Field in Color record */
	private String ycount4;
	/** Field in Color record */
	private String ycount5;
	/** Field in Color record */
	private String ycount6;
	/** Field in Color record */
	private String ycount7;
	/** Field in Color record */
	private String ycount8;
	/** Field in Color record */
	private String mfg1;
	/** Field in Color record */
	private String mfg2;
	/** Field in Color record */
	private String mfg3;
	/** Field in Color record */
	private String mfg4;
	/** Field in Color record */
	private String mfg5;
	/** Field in Color record */
	private String mfg6;
	/** Field in Color record */
	private String mfg7;
	/** Field in Color record */
	private String mfg8;
	/** Field in Color record */
	private String mfgCode1;
	/** Field in Color record */
	private String mfgCode2;
	/** Field in Color record */
	private String mfgCode3;
	/** Field in Color record */
	private String mfgCode4;
	/** Field in Color record */
	private String mfgCode5;
	/** Field in Color record */
	private String mfgCode6;
	/** Field in Color record */
	private String mfgCode7;
	/** Field in Color record */
	private String mfgCode8;
	/** Field in Color record */
	private String itemCode1;
	/** Field in Color record */
	private String itemCode2;
	/** Field in Color record */
	private String itemCode3;
	/** Field in Color record */
	private String itemCode4;
	/** Field in Color record */
	private String itemCode5;
	/** Field in Color record */
	private String itemCode6;
	/** Field in Color record */
	private String itemCode7;
	/** Field in Color record */
	private String itemCode8;
	/** Field in Color record */
	private boolean empty;

	/**
	 * Main constructor for Color
	 *
	 * @param code      String from reader
	 * @param desc      String from reader
	 * @param mfg       String from reader
	 * @param notes     String from reader
	 * @param mfgCode   String from reader
	 * @param ycount1   String from reader
	 * @param ycount2   String from reader
	 * @param ycount3   String from reader
	 * @param ycount4   String from reader
	 * @param ycount5   String from reader
	 * @param ycount6   String from reader
	 * @param ycount7   String from reader
	 * @param ycount8   String from reader
	 * @param mfg1      String from reader
	 * @param mfg2      String from reader
	 * @param mfg3      String from reader
	 * @param mfg4      String from reader
	 * @param mfg5      String from reader
	 * @param mfg6      String from reader
	 * @param mfg7      String from reader
	 * @param mfg8      String from reader
	 * @param mfgCode1  String from reader
	 * @param mfgCode2  String from reader
	 * @param mfgCode3  String from reader
	 * @param mfgCode4  String from reader
	 * @param mfgCode5  String from reader
	 * @param mfgCode6  String from reader
	 * @param mfgCode7  String from reader
	 * @param mfgCode8  String from reader
	 * @param itemCode1 String from reader
	 * @param itemCode2 String from reader
	 * @param itemCode3 String from reader
	 * @param itemCode4 String from reader
	 * @param itemCode5 String from reader
	 * @param itemCode6 String from reader
	 * @param itemCode7 String from reader
	 * @param itemCode8 String from reader
	 * @param empty     true if all fields are empty
	 */
	public Color(String code, String desc, String mfg, String notes, String mfgCode, String ycount1, String ycount2,
			String ycount3, String ycount4, String ycount5, String ycount6, String ycount7, String ycount8, String mfg1,
			String mfg2, String mfg3, String mfg4, String mfg5, String mfg6, String mfg7, String mfg8, String mfgCode1,
			String mfgCode2, String mfgCode3, String mfgCode4, String mfgCode5, String mfgCode6, String mfgCode7,
			String mfgCode8, String itemCode1, String itemCode2, String itemCode3, String itemCode4, String itemCode5,
			String itemCode6, String itemCode7, String itemCode8, boolean empty) {
		this.code = code;
		this.desc = desc;
		this.mfg = mfg;
		this.notes = notes;
		this.mfgCode = mfgCode;
		this.ycount1 = ycount1;
		this.ycount2 = ycount2;
		this.ycount3 = ycount3;
		this.ycount4 = ycount4;
		this.ycount5 = ycount5;
		this.ycount6 = ycount6;
		this.ycount7 = ycount7;
		this.ycount8 = ycount8;
		this.mfg1 = mfg1;
		this.mfg2 = mfg2;
		this.mfg3 = mfg3;
		this.mfg4 = mfg4;
		this.mfg5 = mfg5;
		this.mfg6 = mfg6;
		this.mfg7 = mfg7;
		this.mfg8 = mfg8;
		this.mfgCode1 = mfgCode1;
		this.mfgCode2 = mfgCode2;
		this.mfgCode3 = mfgCode3;
		this.mfgCode4 = mfgCode4;
		this.mfgCode5 = mfgCode5;
		this.mfgCode6 = mfgCode6;
		this.mfgCode7 = mfgCode7;
		this.mfgCode8 = mfgCode8;
		this.itemCode1 = itemCode1;
		this.itemCode2 = itemCode2;
		this.itemCode3 = itemCode3;
		this.itemCode4 = itemCode4;
		this.itemCode5 = itemCode5;
		this.itemCode6 = itemCode6;
		this.itemCode7 = itemCode7;
		this.itemCode8 = itemCode8;
		this.empty = empty;
	}

	/**
	 * Gets the empty
	 *
	 * @return the empty
	 */
	public boolean isEmpty() {
		return empty;
	}

	/**
	 * Prints the color information delimited by commas
	 *
	 * @return a comma delimited string
	 */
	public String toCSV() {
		StringBuilder builder = new StringBuilder();
		builder.append(code);
		builder.append(",");
		builder.append(desc);
		builder.append(",");
		builder.append(mfg);
		builder.append(",");
		if (ColorManager.getNotes()) {
			builder.append(notes);
			builder.append(",");
		}
		builder.append(mfgCode);
		builder.append(",");
		builder.append(ycount1);
		builder.append(",");
		builder.append(ycount2);
		builder.append(",");
		builder.append(ycount3);
		builder.append(",");
		builder.append(ycount4);
		builder.append(",");
		builder.append(ycount5);
		builder.append(",");
		builder.append(ycount6);
		builder.append(",");
		builder.append(ycount7);
		builder.append(",");
		builder.append(ycount8);
		builder.append(",");
		builder.append(mfg1);
		builder.append(",");
		builder.append(mfg2);
		builder.append(",");
		builder.append(mfg3);
		builder.append(",");
		builder.append(mfg4);
		builder.append(",");
		builder.append(mfg5);
		builder.append(",");
		builder.append(mfg6);
		builder.append(",");
		builder.append(mfg7);
		builder.append(",");
		builder.append(mfg8);
		builder.append(",");
		builder.append(mfgCode1);
		builder.append(",");
		builder.append(mfgCode2);
		builder.append(",");
		builder.append(mfgCode3);
		builder.append(",");
		builder.append(mfgCode4);
		builder.append(",");
		builder.append(mfgCode5);
		builder.append(",");
		builder.append(mfgCode6);
		builder.append(",");
		builder.append(mfgCode7);
		builder.append(",");
		builder.append(mfgCode8);
		builder.append(",");
		builder.append(itemCode1);
		builder.append(",");
		builder.append(itemCode2);
		builder.append(",");
		builder.append(itemCode3);
		builder.append(",");
		builder.append(itemCode4);
		builder.append(",");
		builder.append(itemCode5);
		builder.append(",");
		builder.append(itemCode6);
		builder.append(",");
		builder.append(itemCode7);
		builder.append(",");
		builder.append(itemCode8);
		return builder.toString();
	}

	/**
	 * Prints the color information delimited by spaces
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(code);
		builder.append(" ");
		builder.append(desc);
		builder.append(" ");
		builder.append(mfg);
		builder.append(" ");
		builder.append(notes);
		builder.append(" ");
		builder.append(mfgCode);
		builder.append(" ");
		builder.append(ycount1);
		builder.append(" ");
		builder.append(ycount2);
		builder.append(" ");
		builder.append(ycount3);
		builder.append(" ");
		builder.append(ycount4);
		builder.append(" ");
		builder.append(ycount5);
		builder.append(" ");
		builder.append(ycount6);
		builder.append(" ");
		builder.append(ycount7);
		builder.append(" ");
		builder.append(ycount8);
		builder.append(" ");
		builder.append(mfg1);
		builder.append(" ");
		builder.append(mfg2);
		builder.append(" ");
		builder.append(mfg3);
		builder.append(" ");
		builder.append(mfg4);
		builder.append(" ");
		builder.append(mfg5);
		builder.append(" ");
		builder.append(mfg6);
		builder.append(" ");
		builder.append(mfg7);
		builder.append(" ");
		builder.append(mfg8);
		builder.append(" ");
		builder.append(mfgCode1);
		builder.append(" ");
		builder.append(mfgCode2);
		builder.append(" ");
		builder.append(mfgCode3);
		builder.append(" ");
		builder.append(mfgCode4);
		builder.append(" ");
		builder.append(mfgCode5);
		builder.append(" ");
		builder.append(mfgCode6);
		builder.append(" ");
		builder.append(mfgCode7);
		builder.append(" ");
		builder.append(mfgCode8);
		builder.append(" ");
		builder.append(itemCode1);
		builder.append(" ");
		builder.append(itemCode2);
		builder.append(" ");
		builder.append(itemCode3);
		builder.append(" ");
		builder.append(itemCode4);
		builder.append(" ");
		builder.append(itemCode5);
		builder.append(" ");
		builder.append(itemCode6);
		builder.append(" ");
		builder.append(itemCode7);
		builder.append(" ");
		builder.append(itemCode8);
		builder.append(" ");
		builder.append(empty);
		return builder.toString();
	}

}
