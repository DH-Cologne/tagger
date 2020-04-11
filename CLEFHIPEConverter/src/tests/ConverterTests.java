package tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.junit.jupiter.api.Test;

import de.uni_koeln.idh.converter.CLEFData;
import de.uni_koeln.idh.converter.CLEFDataFile;
import de.uni_koeln.idh.converter.CLEFDataReader;
import de.uni_koeln.idh.converter.CONLData;
import de.uni_koeln.idh.converter.CONLDataFile;
import de.uni_koeln.idh.converter.Converter;

class ConverterTests {

	@Test
	void testReader() throws IOException {
		CLEFDataFile data = CLEFDataReader.readFile("training-v1.1/de/HIPE-data-v1.1-dev-de.tsv");
		List<CLEFData> items = data.getItems();
		for (CLEFData clefData : items) {
				System.out.println(clefData);
		}
	}
	
	@Test
	void testConverter() throws IOException {
		CLEFDataFile data = CLEFDataReader.readFile("training-v1.1/de/HIPE-data-v1.1-dev-de.tsv");
		//List<CLEFData> items = data.getItems();
		CONLDataFile convData = Converter.convertForward(data);
		List<CONLData> items = convData.getItems();
		for (CONLData conlData : items) {
			System.out.println(conlData);
		}
		PrintWriter out = new PrintWriter(new FileWriter(new File(convData.getFileName())));
		for (CONLData conlData : items) {
			out.println(conlData);
		}
		out.flush();
		out.close();
		System.out.println(convData.getFileName());
	}
	
	@Test
	void testReverse() throws IOException {
		CLEFDataFile data = CLEFDataReader.readFile("training-v1.1/de/HIPE-data-v1.1-dev-de.tsv");
		List<CLEFData> items3 = data.getItems();
		for (CLEFData clefData : items3) {
			//System.out.println(clefData);
		}
		//List<CLEFData> items = data.getItems();
		CONLDataFile convData = Converter.convertForward(data);
		List<CONLData> items = convData.getItems();
		for (CONLData conlData : items) {
			//System.out.println(conlData);
		}
		CLEFDataFile newFile = Converter.convertBackwards(convData, data);
		List<CLEFData> items2 = newFile.getItems();
		int i=0;
		for (CLEFData clefData : items2) {
			System.out.println(clefData);
			i++;
			if(i>500) break;
		}
		
	}

}
