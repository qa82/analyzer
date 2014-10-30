package org.qa82.analyzer.core;

public class Parameter {

	private Object value;
	private String uri;
	
	public Parameter(String uri, Object value) {
		this.uri = uri;
		this.value = value;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public String getUri() {
		return uri;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	public Object getValue() {
		return value;
	}
}
