package org.qa82.analyzer.core.impl;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.qa82.analyzer.core.Repository;

public class FileSystemRepository implements Repository {

	private File repositoryRoot;

	//TODO: rootPath should be a directory. Need to be checked
	public FileSystemRepository(String rootPath) {
		this.repositoryRoot = new File(rootPath);
	}

	@Override
	public String getAddress() {
		return repositoryRoot.getAbsolutePath();
	}

	@Override
	public String getDescription() {
		return "Repository based on the file system with root directory \"" + getAddress() + "\"";
	}

	@Override
	public Set<File> searchFileMatching(Pattern pattern) {
		return searchFileMatching(pattern, repositoryRoot, new HashSet<File>());
	}

	@Override
	public Set<File> searchFileEndingWith(String suffix) {
		return searchFileMatching(Pattern.compile(suffix + "$"));
	}

	private Set<File> searchFileMatching(Pattern pattern, File file, Set<File> matchingFiles) {
		if (file.isDirectory() && file.canRead()) {
			for (File temp : file.listFiles()) {
			    if (temp.isDirectory()) {
			    	searchFileMatching(pattern, temp, matchingFiles);
			    } else if (pattern.matcher(temp.getName()).find()) {
				    matchingFiles.add(temp);
			    }
		
			}
		}
		return matchingFiles;
	}
}
