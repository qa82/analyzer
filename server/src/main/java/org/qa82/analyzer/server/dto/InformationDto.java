package org.qa82.analyzer.server.dto;


public class InformationDto {

	private InformationTypeDto informationType;

	private String value;

	/**
	 * Constructor for serialization.
	 */
	public InformationDto() {
		super();
	}

	public InformationDto(InformationTypeDto informationType, String value) {
		super();
		this.setInformationType(informationType);
		this.setValue(value);
	}

	public InformationTypeDto getInformationType() {
		return informationType;
	}

	public void setInformationType(InformationTypeDto informationType) {
		this.informationType = informationType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
