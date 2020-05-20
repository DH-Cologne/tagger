package de.uni_koeln.idh.converter.applications;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import de.uni_koeln.idh.converter.CLEFDataFile;
import de.uni_koeln.idh.converter.DataFileReaders;
import de.uni_koeln.idh.converter.CONLData;
import de.uni_koeln.idh.converter.CONLDataFile;
import de.uni_koeln.idh.converter.Converter;

/**
 * Converts files of a specified directory from HIPE-format to a kind of CONLL-format.
 * Token TAB Ner-Tag TAB starting line from source TAB end line from source (0 if == starting line) ENTER  
 * @author jhermes
 */
public class ForwardConversionApp {

	private static List<String> fileNames = new ArrayList<String>();
	
	public static void main(String[] args) throws IOException {
		listFiles("training-v1.2");
		for (String fileName : fileNames) {
			CLEFDataFile clefDataFile = DataFileReaders.readCLEFFile(fileName);
			CONLDataFile conllDataFile = Converter.convertForward(clefDataFile);
			String outputFileName = fileName.replace("training-v1.2", "training-v1.2-conv").replace("/HIPE", "/CONLL").replace("/Users/jhermes/workspace2017/CLEFCONLLConverter/", ""); 
			File outputFile = new File(outputFileName);
			//outputFile.createNewFile();
			System.out.println(outputFileName);
			PrintWriter out = new PrintWriter(new FileWriter(outputFile));
			List<CONLData> items = conllDataFile.getItems();
			for (CONLData conlData : items) {
				out.println(conlData);
			}
			out.flush();
			out.close();
		}
	}

	private static void listFiles(String path) {
		File folder = new File(path);

		File[] files = folder.listFiles();

		for (File file : files) {
			if (file.isFile()) {
				System.out.println(file.getPath());
				fileNames.add(file.getPath());
			} else if (file.isDirectory()) {
				listFiles(file.getAbsolutePath());
			}
		}
	}
	///Users/jhermes/Documents/GitHub/tagger/CLEFCONLLConverter/training-v1.2-conv/de/CONLL-data-v1.2-dev-de.tsv
	///Users/jhermes/Documents/GitHub/tagger/CLEFCONLLConverter/training-v1.2-conv/de/CONLL-data-v1.2-dev-de.tsv
	///CLEFHIPEConverter/training-v1.2-conv/de/CONLL-data-v1.2-dev-de.tsv
}
