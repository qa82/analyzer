package org.qa82.analyzer.core.impl;

import org.qa82.analyzer.core.Information;

public class EmptyInformation implements Information {

	@Override
	public String getType() {
		return null;
	}

	@Override
	public Object getValue() {
		return null;
	}

	@Override
	public Boolean isInformationPresent() {
		return false;
	}

}
