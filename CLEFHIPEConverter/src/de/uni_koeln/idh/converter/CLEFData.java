package de.uni_koeln.idh.converter;

/**
 * Class to represent data from HIPE-CLEF training / dev files
 * @author jhermes
 */
public class CLEFData {
	
	private boolean isRowDelimiter;
	private boolean isHyphen;
	private boolean isComment;
	private boolean isEmptyLine;
	private String token;
	private String nerTag;
	private String tail;
	private int lineNum;
	
	/** 
	 * Creates a new comment CLEFData object 
	 * @param commentLine
	 */
	public CLEFData(String commentLine) {
		this.isComment=true;
		if(commentLine.startsWith("# segment_iiif_link = _")) {
			this.isRowDelimiter=true;
		}
		if(commentLine.trim().isEmpty()) {
			isEmptyLine = true;
		}
		this.tail = commentLine;
	}
	
	/**
	 * Creates a new token CLEFData oject
	 * @param token token string (first column)
	 * @param nerTag NER Tag (second column)
	 * @param tail all information following the nerTag (column 2-n)
	 * @param lineNum line number to identify token
	 */
	public CLEFData(String token, String nerTag, String tail, int lineNum) {
		super();
		this.token = token;
		this.nerTag = nerTag;
		this.lineNum = lineNum;
		this.tail = tail;
		if(token.startsWith("¬")) {
			this.isHyphen=true;
		}
	}

	/** 
	 * @return true if item is a row delimiter comment, false otherwise
	 */
	public boolean isRowDelimiter() {
		return isRowDelimiter;
	}
	
	/**
	 * @return true, if item is a comment, false otherwise
	 */
	public boolean isComment() {
		return isComment;
	}
	
	/**
	 * @return true, if item is empty line, false otherwise
	 */
	public boolean isEmptyLine() {
		return isEmptyLine;
	}

	/**
	 * @return true, if item is an in-word-hyphen, false otherwise
	 */
	public boolean isHyphen() {
		return isHyphen;
	}

	/**
	 * @return token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @return NER tag
	 */
	public String getNerTag() {
		return nerTag;
	}

	/**
	 * @return line number in the original document 
	 */
	public int getLineNum() {
		return lineNum;
	}
	
	/** Updates the NER Tag (should be used to perform NER tagging)
	 * @param newTag NER Tag
	 */
	public void updateNERTag(String newTag) {
		// This could e nice place to return an evaluation number
		this.nerTag = newTag;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if(this.isComment) {
			return tail;
		}
		return token + "\t" + nerTag + "\t" + tail;
	}
	

}
