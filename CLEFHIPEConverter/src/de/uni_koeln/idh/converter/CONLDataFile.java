package de.uni_koeln.idh.converter;

import java.util.ArrayList;
import java.util.List;

public class CONLDataFile {
	
	private String fileName;
	
	private List<CONLData> items;
	
	public CONLDataFile(String fileName) {
		super();
		this.fileName = fileName;
		items = new ArrayList<CONLData>(); 
	}

	public String getFileName() {
		return fileName;
	}
	
	public void addItem(CONLData item) {
		this.items.add(item);
	}

	public List<CONLData> getItems() {
		return items;
	}

	public CONLData getLastItem() {
		return items.get(items.size()-1);
	}
	
	

}
