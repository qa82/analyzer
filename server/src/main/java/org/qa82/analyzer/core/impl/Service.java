package org.qa82.analyzer.core.impl;

public class Service extends Element {
	
	@Override
	public String getUri() {
		return "http://cos.ontoware.org/cos#web-service";
	}
	
	@Override
	public void setUri(String uri) {
	}

	public Service(String name) {
		super.setName(name);
	}

}
