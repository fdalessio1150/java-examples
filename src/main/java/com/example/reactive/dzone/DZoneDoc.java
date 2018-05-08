package com.example.reactive.dzone;

public class DZoneDoc {

	private String name;
	private String type;

	private DZoneDoc() {}

	public static DZoneDoc create(String name, String type) {
		DZoneDoc doc = new DZoneDoc();
		doc.setName(name);
		doc.setType(type);
		return doc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "DZoneDoc [name=" + name + ", type=" + type + "]";
	}

}
