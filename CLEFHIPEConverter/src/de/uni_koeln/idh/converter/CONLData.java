package de.uni_koeln.idh.converter;

/**
 * Class to represent kind-of-CONLL data training / dev files
 * @author jhermes
 */
public class CONLData {
	
	private String token;
	private String nerTag;
	private int startLine;
	private int endLine;
	private boolean isSpace;
	
	/**
	 * Creates a new tokenn CLEFData object 
	 * @param token Token
	 * @param nerTag NER tag
	 * @param startLine starting line in original document
	 */
	public CONLData(String token, String nerTag, int startLine) {
		super();
		this.token = token;
		this.nerTag = nerTag;
		this.startLine = startLine;
	}
	
	/**
	 * Creates a new empty-line CLEFData object 
	 */
	public CONLData() {
		this.isSpace = true;
	}
	
	/**
	 * @return Token of item
	 */
	public String getToken() {
		return token;
	}
	
	/**
	 * @return NER tag of item
	 */
	public String getNerTag() {
		return nerTag;
	}
	
	/**
	 * @return starting line of item in original document
	 */
	public int getStartLine() {
		return startLine;
	}
	
	/**
	 * @return ending line of item in original document (0 if endLine == startLine)
	 */
	public int getEndLine() {
		return endLine;
	}
	
	/**
	 * @return true, if item is empty line
	 */
	public boolean isSpace() {
		return isSpace;
	}

	/**
	 * Updates token for token ranges > 1 line
	 * @param token Combined token 
	 * @param endLine ending line in original document
	 */
	public void updateToken(String token, int endLine) {
		this.token = token;
		this.endLine = endLine;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if(token==null) {
			return "";
		}
		return token + "\t" + nerTag + "\t" + startLine + "\t" + endLine;
	}

}
