package de.uni_koeln.idh.converter;

public class CLEFData {
	
	private boolean isRowDelimiter;
	private boolean isHyphen;
	private boolean isComment;
	private boolean isEmptyLine;
	private String token;
	private String nerTag;
	private String tail;
	private int lineNum;
	
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

	public boolean isRowDelimiter() {
		return isRowDelimiter;
	}
	
	public boolean isComment() {
		return isComment;
	}
	
	public boolean isEmptyLine() {
		return isEmptyLine;
	}

	public boolean isHyphen() {
		return isHyphen;
	}

	public String getToken() {
		return token;
	}

	public String getNerTag() {
		return nerTag;
	}

	public int getLineNum() {
		return lineNum;
	}
	
	public void updateNERTag(String newTag) {
		// This could e nice place to return an evaluation number
		this.nerTag = newTag;
	}
	
	public String toString() {
		if(this.isComment) {
			return tail;
		}
		return token + "\t" + nerTag + "\t" + tail;
	}
	

}
