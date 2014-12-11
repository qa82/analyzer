package org.qa82.analyzer.server.dto;

import org.qa82.analyzer.core.Information;
import org.qa82.analyzer.core.bean.InformationType;

public class InformationTypeDto {

	private Class<? extends Information> classType = Information.class;
	private String type = "";
	private String description = "";

	/**
	 * Constructor for serialization.
	 */
	public InformationTypeDto() {
	}

	public InformationTypeDto(InformationType expectedInformationType) {
		setClassType(expectedInformationType.getClassType());
		setType(expectedInformationType.getType());
		setDescription(expectedInformationType.getDescription());
	}


	public Class<? extends Information> getClassType() {
		return classType;
	}

	public String getDescription() {
		return description;
	}
	public String getType() {
		return type;
	}

	public void setClassType(Class<? extends Information> classType) {
		this.classType = classType;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public InformationType convertToInformationType() {
		InformationType informationType = new InformationType(getClassType(), getType(), getDescription());
		return informationType;
	}

}
