package de.uni_koeln.idh.converter;

public class TaggerOutputData {
	
	private String token;
	private String nerTag;
	public TaggerOutputData(String token, String nerTag) {
		super();
		this.token = token;
		this.nerTag = nerTag;
	}
	public String getToken() {
		return token;
	}
	public String getNerTag() {
		return nerTag;
	}
	
	public String toString() {
		return token + "\t" + nerTag;
	}
	

}
