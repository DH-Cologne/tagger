package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import de.uni_koeln.idh.converter.CONLData;
import de.uni_koeln.idh.converter.CONLDataFile;
import de.uni_koeln.idh.converter.DataFileReaders;
import de.uni_koeln.idh.converter.TaggerOutputData;

class UpdaterTests {

	//@Test
	void testConlFileReader() throws IOException {
		CONLDataFile conlDF = DataFileReaders.readCONLDataFile("test-v1.1-output/test-input.tsv");
		List<CONLData> items = conlDF.getItems();
		for (CONLData conlData : items) {
			System.out.println(conlData);
		}	
	}
	
	//@Test
	void testTaggerOutputFileReader() throws IOException {
		List<TaggerOutputData> readTaggerOutput = DataFileReaders.readTaggerOutput("test-v1.1-output/test-output.txt");
		for (TaggerOutputData taggerOutputData : readTaggerOutput) {
			System.out.println(taggerOutputData);
		}
	}
	
	
	@Test
	void testUpdater() throws IOException {
		CONLDataFile conlDF = DataFileReaders.readCONLDataFile("test-v1.1-output/test-input.tsv");
		List<CONLData> items = conlDF.getItems();
		for (CONLData conlData : items) {
			System.out.println(conlData);
		}	
		List<TaggerOutputData> readTaggerOutput = DataFileReaders.readTaggerOutput("test-v1.1-output/test-output.txt");
		for (TaggerOutputData taggerOutputData : readTaggerOutput) {
			System.out.println(taggerOutputData);
		}
		
		conlDF.updateCONLDataFile(readTaggerOutput);
		items = conlDF.getItems();
		for (CONLData conlData : items) {
			System.out.println(conlData);
		}	
	}

}
