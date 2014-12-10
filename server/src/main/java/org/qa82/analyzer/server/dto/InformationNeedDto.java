package org.qa82.analyzer.server.dto;


public class InformationNeedDto {

	private InformationTypeDto exptectedInformationType;

	private ParameterListDto parameterList;

	/**
	 * Empty constructor for serialization.
	 */
	public InformationNeedDto() {
	}

	public InformationNeedDto(InformationTypeDto exptectedInformationType, ParameterListDto parameterList) {
		super();
		this.exptectedInformationType = exptectedInformationType;
		this.parameterList = parameterList;
	}

	public InformationTypeDto getExptectedInformationType() {
		return exptectedInformationType;
	}

	public void setExptectedInformationType(InformationTypeDto exptectedInformationType) {
		this.exptectedInformationType = exptectedInformationType;
	}

	public ParameterListDto getParameterList() {
		return parameterList;
	}

	public void setParameterList(ParameterListDto parameterList) {
		this.parameterList = parameterList;
	}
}
