package org.qa82.analyzer.server.dto;


public class InformationDto {

	private InformationTypeDto informationType;

	private String name;

	private String value;

	/**
	 * Constructor for serialization.
	 */
	public InformationDto() {
		super();
	}

	public InformationDto(InformationTypeDto informationType, String name, String value) {
		super();
		this.setInformationType(informationType);
		this.setName(name);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
