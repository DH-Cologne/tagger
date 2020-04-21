package de.uni_koeln.idh.converter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataFileReaders {
	
	/**
	 * Reads HIPE_CLEF data from specified file to a CLEFDataFile 
	 * @param inputFileName File to parse
	 * @return CLEFDataFile with data from the specified file
	 * @throws IOException
	 */
	public static CLEFDataFile readCLEFFile(String inputFileName) throws IOException {
		CLEFDataFile toReturn = new CLEFDataFile(inputFileName);
		BufferedReader in = new BufferedReader(new FileReader(new File(inputFileName)));
		String line = in.readLine();
        toReturn.addItem(new CLEFData(line));
        int lineNumber = 1;
        line = in.readLine();
		while(line!=null) {
			lineNumber ++;
			if(line.startsWith("#") || line.trim().isEmpty()) {
				toReturn.addItem(new CLEFData(line));
			}
			else {
				String[] segments = line.split("\\t");
				String token = segments[0];
				String nerTag = segments[1];
				StringBuffer tail = new StringBuffer(); 
				for(int i=2; i<segments.length; i++) {
					tail.append("\t" + segments[i]);
				}
				toReturn.addItem(new CLEFData(token, nerTag, tail.toString(), lineNumber));
			}
			line = in.readLine();
		}
		in.close();
		return toReturn;
	}
	
	public static CONLDataFile readCONLDataFile(String inputFileName) throws IOException {
		CONLDataFile toReturn = new CONLDataFile(inputFileName);
		BufferedReader in = new BufferedReader(new FileReader(new File(inputFileName)));
		String line = in.readLine();
		while(line!=null) {
			String[] split = line.split("\t");
			if(split.length<4) {
				toReturn.addItem(new CONLData());
			}
			else {
				CONLData next = new CONLData(split[0], split[1], Integer.parseInt(split[2]));
				next.updateToken(split[0], Integer.parseInt(split[3]));
				toReturn.addItem(next);
			}
			line = in.readLine();
		}
		in.close();
		return toReturn;
		
	}
	
	public static List<TaggerOutputData> readTaggerOutput(String fileName) throws IOException{
		List<TaggerOutputData> toReturn = new ArrayList<TaggerOutputData>();
		BufferedReader in = new BufferedReader(new FileReader(new File(fileName)));
		String line = in.readLine();
		while(line!=null) {
			String[] words = line.split(" ");
			for (String word : words) {
				String[] split = word.split("__");
				toReturn.add(new TaggerOutputData(split[0], split[1]));
			}
			toReturn.add(new TaggerOutputData("",""));
			line = in.readLine();
		}
		in.close();
		return toReturn;
	}
	
	public static CONLDataFile readGermEvalData(String folderName) throws IOException{
		CONLDataFile toReturn = new CONLDataFile(folderName);
		File folder = new File(folderName);
		if(folder.isDirectory()) {
			File[] listFiles = folder.listFiles();
			for (File file : listFiles) {
				BufferedReader in = new BufferedReader(new FileReader(file));
				String line = in.readLine();
				while(line!=null) {
					if(!line.startsWith("#")) {
						String[] items = line.split("\t");
						if(items.length!=4) {
						     toReturn.addItem(new CONLData());
						}
						else {
							String nerTag = items[2];
							// cut additional Info
							if(nerTag.length()>5) {
								nerTag = nerTag.substring(0, 5);
							}
							
							toReturn.addItem(new CONLData(items[1],nerTag, 0));
						}
						
					}
					line = in.readLine();
				}
				in.close();
			}
		}
		return toReturn;
	}
	

}
