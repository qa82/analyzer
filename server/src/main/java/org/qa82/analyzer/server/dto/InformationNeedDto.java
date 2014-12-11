package org.qa82.analyzer.server.dto;


public class InformationNeedDto {

	private InformationTypeDto expectedInformationType;

	private ParameterListDto parameterList;

	/**
	 * Empty constructor for serialization.
	 */
	public InformationNeedDto() {
	}

	public InformationNeedDto(InformationTypeDto expectedInformationType, ParameterListDto parameterList) {
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

	public ParameterListDto getParameterList() {
		return parameterList;
	}

	public void setParameterList(ParameterListDto parameterList) {
		this.parameterList = parameterList;
	}
}
