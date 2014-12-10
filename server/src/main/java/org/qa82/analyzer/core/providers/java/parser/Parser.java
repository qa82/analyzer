package org.qa82.analyzer.core.providers.java.parser;

import java.io.File;

/**
 * This interface defines the method definitions, which is implemented by any parser
 * Created by Pascal Giessler on 05.12.14.
 */
public interface Parser {

    /**
     * This method is responsible for parsing a given file
     * @param file the file, which should be parsed
     */
    public void parseFile(File file);
}
