package de.uni_koeln.idh.converter;

import java.io.IOException;
import java.util.List;

/**
 * Converts HIPE-CLEF-items to CONLL-like items (forwards) and vice versa (ackwards)
 * @author jhermes
 */
public class Converter {

	/**
	 * Converts HIPE-CLEF-items to CONLL-like items. Format of converted Data: 
	 * Token TAB Ner-Tag TAB starting line from source TAB end line from source (0 if == starting line) ENTER  
	 * @param clefDataFile Data to convert
	 * @return converted Data
	 */
	public static CONLDataFile convertForward(CLEFDataFile clefDataFile) {
		String fileName = clefDataFile.getFileName();
		String newName = fileName.replace("HIPE", "CONLL");
		CONLDataFile toReturn = new CONLDataFile(newName);
		List<CLEFData> items = clefDataFile.getItems();
		for (int i = 1; i < items.size(); i++) {
			CLEFData clefData = items.get(i);
			if (clefData.isComment()) {
				if (clefData.isEmptyLine()) {
					toReturn.addItem(new CONLData());
				}
			} else {
				if (clefData.isHyphen()) {
					i = i + 1;
					clefData = items.get(i);
					if (clefData.isRowDelimiter()) {
						i = i + 1;
						clefData = items.get(i);
						CONLData lastItem = toReturn.getLastItem();
						lastItem.updateToken(lastItem.getToken().concat(clefData.getToken()), clefData.getLineNum());
					}
				} else {
					if (!clefData.isComment()) {
						toReturn.addItem(
								new CONLData(clefData.getToken(), clefData.getNerTag(), clefData.getLineNum()));
					}
				}
			}
		}
		return toReturn;
	}

	/**
	 * Converts CONLL-like data to HIPE-CLEF format.
	 * @param conlDataFile Data to convert
	 * @param originalFile Original HIPE-CLEF File
	 * @return converted data
	 */
	public static CLEFDataFile convertBackwards(CONLDataFile conlDataFile, CLEFDataFile originalFile) {
		List<CONLData> items = conlDataFile.getItems();

		for (CONLData conlData : items) {
			int line = conlData.getStartLine();
			if (line != 0) {
				String nerTag = conlData.getNerTag();
				CLEFData itemAt = originalFile.getItemAt(line);
				itemAt.updateNERTag(nerTag);
				if (conlData.getEndLine() != 0) {
					itemAt = originalFile.getItemAt(line + 1);
					nerTag = nerTag.replace("B", "I");
					itemAt.updateNERTag(nerTag);
					itemAt = originalFile.getItemAt(line + 3);
					itemAt.updateNERTag(nerTag);
				}
			}
		}
		return originalFile;
	}

}
