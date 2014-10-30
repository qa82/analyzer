package org.qa82.analyzer.core.impl;

import java.util.Optional;

public class ResolvedInformation {

	/** The information resolved by an analyzer. The resolved informationen corresponds to an information need. */
	private Object information;
	
	/** The type of this information. */
	private String uri = "";
	
	public ResolvedInformation(Object information) {
		this.information = information;
	}
	
	public ResolvedInformation(Object information, String uri) {
		this.information = information;
		this.uri = uri;
	}
	
	public String getUri() {
		return uri;
	}
	
	public Object getInformation() {
		return information;
	}
	
	public Boolean isInformationPresent() {
		return information != null;
	}
	
	public Optional<Element> getAsElement() {
		return Optional.ofNullable((information != null && information instanceof Element) ? (Element) information : null);   
	}
	
	public Optional<Boolean> getAsBoolean() {
		return Optional.ofNullable((information != null && information instanceof Boolean) ? (Boolean) information : null);   
	}
	
	public Optional<String> getAsString() {
		return Optional.ofNullable((information != null && information instanceof String) ? (String) information : null);   
	}
	
	public Optional<Double> getAsDouble() {
		return Optional.ofNullable((information != null && information instanceof Double) ? (Double) information : null);   
	}
}
