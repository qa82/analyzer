package org.qa82.analyzer.server.dto;


import java.util.ArrayList;

public class InformationNeedDto {

	private InformationTypeDto expectedInformationType;

	private ArrayList<InformationDto> parameterList;

	/**
	 * Empty constructor for serialization.
	 */
	public InformationNeedDto() {
	}

	public InformationNeedDto(InformationTypeDto expectedInformationType,  ArrayList<InformationDto> parameterList) {
		super();
		this.expectedInformationType = expectedInformationType;
		this.parameterList = parameterList;
	}

	public InformationTypeDto getExpectedInformationType() {
		return expectedInformationType;
	}

	public void setExpectedInformationType(InformationTypeDto expectedInformationType) {
		this.expectedInformationType = expectedInformationType;
	}

	public  ArrayList<InformationDto> getParameterList() {
		return parameterList;
	}

	public void setParameterList( ArrayList<InformationDto> parameterList) {
		this.parameterList = parameterList;
	}
}
