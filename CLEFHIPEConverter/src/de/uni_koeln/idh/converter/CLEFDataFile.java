package de.uni_koeln.idh.converter;

import java.util.ArrayList;
import java.util.List;

public class CLEFDataFile {
	
	private String fileName;
	
	private List<CLEFData> items;
	
	public CLEFDataFile(String fileName) {
		super();
		this.fileName = fileName;
		items = new ArrayList<CLEFData>(); 
	}

	public String getFileName() {
		return fileName;
	}
	
	public void addItem(CLEFData item) {
		this.items.add(item);
	}

	public List<CLEFData> getItems() {
		return items;
	}
	
	public CLEFData getItemAt(int index) {
		return items.get(index-1);
	}
	
	

}
