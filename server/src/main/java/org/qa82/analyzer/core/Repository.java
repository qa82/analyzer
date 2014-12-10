package org.qa82.analyzer.core;

import java.io.File;
import java.util.Set;
import java.util.regex.Pattern;

public interface Repository {

	public String getAddress();
	
	public String getDescription();
	
	public Set<File> searchFileMatching(Pattern pattern);
	
	public Set<File> searchFileEndingWith(String suffix);
}
