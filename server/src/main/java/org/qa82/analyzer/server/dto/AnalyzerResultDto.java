package org.qa82.analyzer.server.dto;

import org.qa82.analyzer.core.AnalyzerResult;

import java.util.ArrayList;
import java.util.List;

public class AnalyzerResultDto {

	private List<InformationDto> information = new ArrayList<InformationDto>();

	public AnalyzerResultDto() {
		super();
	}

	public AnalyzerResultDto(List<InformationDto> information) {
		super();
		this.information = information;
	}

	public AnalyzerResultDto(AnalyzerResult result) {
		result.getInformation().forEach((information) -> {
			this.information.add(new InformationDto(null, information.getName(), information.getValue().toString()));
		});
	}

	public void setInformation(List<InformationDto> information) {
		this.information = information;
	}

	public List<InformationDto> getInformation() {
		return information;
	}
}
