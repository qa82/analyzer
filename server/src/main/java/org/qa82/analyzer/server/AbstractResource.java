package org.qa82.analyzer.server;

import org.qa82.analyzer.core.Analyzer;
import org.qa82.analyzer.core.Repository;
import org.qa82.analyzer.core.bean.Project;
import org.qa82.analyzer.core.impl.FileSystemRepository;
import org.qa82.analyzer.core.impl.SimpleAnalyzer;

public abstract class AbstractResource {
	
	protected Analyzer analyzer;
	
	public AbstractResource() {
		this.analyzer = new SimpleAnalyzer(new Project());
		Repository repository = new FileSystemRepository("c:/analyzer_test/"); 
		this.analyzer.getProject().addRepository(repository);
	}

}
