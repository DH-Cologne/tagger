package tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.junit.jupiter.api.Test;

import de.uni_koeln.idh.converter.CONLData;
import de.uni_koeln.idh.converter.CONLDataFile;
import de.uni_koeln.idh.converter.DataFileReaders;

class ReaderTests {

	@Test
	void testTDReader() throws IOException {
		CONLDataFile readGermEvalData = DataFileReaders.readGermEvalData("trainings_vGERM_Eval");
		List<CONLData> items = readGermEvalData.getItems();
		PrintWriter out = new PrintWriter(new FileWriter(new File("training-v1.1-conv-sentences/de/CONLL-data-v1.2-train-de.txt")));
		BufferedReader in = new BufferedReader(new FileReader(new File("training-v1.1-conv-sentences/de/CONLL-data-v1.1-train-de.txt")));
		String line = in.readLine();
		while(line!=null) {
			out.println(line);
			line = in.readLine();
		}
		out.println();
		for (CONLData conlData : items) {
			out.println(conlData.getToken() + " " + conlData.getNerTag());
		}
		out.flush();
		out.close();
	}

}
