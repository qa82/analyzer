/*******************************************************************************
* Copyright (c) 2014 Michael Gebhart (michael.gebhart@qa82.org).
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
* Michael Gebhart - initial idea and concept
* 
*******************************************************************************/

package org.qa82.analyzer.core.bean;

import java.util.HashSet;
import java.util.Set;

import org.qa82.analyzer.core.Repository;

public class Project {
	
	private Set<Repository> repositories = new HashSet<Repository>();
	
	public Set<Repository> getRepositories() {
		return repositories;
	}
	
	public void setRepositories(Set<Repository> repositories) {
		this.repositories = repositories;
	}
	
	public void addRepository(Repository repository) {
		this.repositories.add(repository);
	}
}
