package de.uni_koeln.idh.converter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent list of CLEFData items from specified file.
 * @author jhermes
 *
 */
public class CLEFDataFile {
	
	private String fileName;
	
	private List<CLEFData> items;
	
	/**
	 * Creates a new CLEFDataFile
	 * @param fileName Name of the file beeing processed
	 */
	public CLEFDataFile(String fileName) {
		super();
		this.fileName = fileName;
		items = new ArrayList<CLEFData>(); 
	}

	/**
	 * @return filename of the original file
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * @param item Item to add
	 */
	public void addItem(CLEFData item) {
		this.items.add(item);
	}

	/**
	 * @return List of items
	 */
	public List<CLEFData> getItems() {
		return items;
	}
	
	/**
	 * @param index
	 * @return Item from specified index
	 */
	public CLEFData getItemAt(int index) {
		return items.get(index-1);
	}
	
	

}
