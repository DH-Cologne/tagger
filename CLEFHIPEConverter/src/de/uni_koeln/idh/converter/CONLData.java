package de.uni_koeln.idh.converter;

public class CONLData {
	
	private String token;
	private String nerTag;
	private int startLine;
	private int endLine;
	private boolean isSpace;
	
	public CONLData(String token, String nerTag, int startLine) {
		super();
		this.token = token;
		this.nerTag = nerTag;
		this.startLine = startLine;
	}
	
	public CONLData() {
		this.isSpace = true;
	}
	
	public String getToken() {
		return token;
	}
	
	public String getNerTag() {
		return nerTag;
	}
	
	public int getStartLine() {
		return startLine;
	}
	
	public int getEndLine() {
		return endLine;
	}
	
	public boolean isSpace() {
		return isSpace;
	}

	public void updateToken(String token, int endLine) {
		this.token = token;
		this.endLine = endLine;
	}
	
	public String toString() {
		if(token==null) {
			return "";
		}
		return token + "\t" + nerTag + "\t" + startLine + "\t" + endLine;
	}

}
