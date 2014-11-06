package org.qa82.analyzer.core;

import java.util.List;

import org.qa82.analyzer.core.bean.InformationType;
import org.qa82.analyzer.core.exceptions.InformationNeedNotResolvableException;
import org.qa82.analyzer.core.impl.Project;

public interface Analyzer {

	public abstract Information resolve(InformationType expectedInformation, Parameters parameters)
			throws InformationNeedNotResolvableException;

	public abstract void setProject(Project project);

	public abstract void setProviders(List<InformationProvider> providers);

	public abstract void addInformationProvider(InformationProvider informationProvider);

	public abstract List<InformationProvider> getProviders();

}