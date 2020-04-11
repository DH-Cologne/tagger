package de.uni_koeln.idh.converter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CLEFDataReader {
	
	/**
	 * Reads HIPE_CLEF data from specified file to a CLEFDataFile 
	 * @param inputFileName File to parse
	 * @return CLEFDataFile with data from the specified file
	 * @throws IOException
	 */
	public static CLEFDataFile readFile(String inputFileName) throws IOException {
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
		return toReturn;
	}
	

}
