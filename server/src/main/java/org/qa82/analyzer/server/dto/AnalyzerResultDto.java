package org.qa82.analyzer.server.dto;

import java.util.ArrayList;
import java.util.List;

import org.qa82.analyzer.core.AnalyzerResult;

public class AnalyzerResultDto {

	private List<String> information = new ArrayList<String>();

	public AnalyzerResultDto() {
		super();
	}

	public AnalyzerResultDto(List<String> information) {
		super();
		this.information = information;
	}

	public AnalyzerResultDto(AnalyzerResult result) {
		result.getInformation().forEach((information) -> this.information.add(information.getValue().toString()));
	}

	public void setInformation(List<String> information) {
		this.information = information;
	}

	public List<String> getInformation() {
		return information;
	}
}
