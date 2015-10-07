package com.example.multifunctional;

public class Definition {

	private int wordID;
	private int definitionID;
	private String type;
	private String def;
	
	public Definition(int wordID, int definitionID, String type, String def)
	{
		this.wordID = wordID;
		this.definitionID = definitionID;
		this.type = type;
		this.def = def;
	}
	
	public Definition(String type, String def)
	{
		this.type = type;
		this.def = def;
	}

	public int getWordID() {
		return wordID;
	}

	public void setWordID(int wordID) {
		this.wordID = wordID;
	}

	public int getDefinitionID() {
		return definitionID;
	}

	public void setDefinitionID(int definitionID) {
		this.definitionID = definitionID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}
	
}
