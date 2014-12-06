package org.qa82.analyzer.core.providers.java.parser.jaxrs;

import org.qa82.analyzer.core.providers.java.parser.Parser;

import java.io.File;

/**
 * Created by Pascal Giessler on 05.12.14.
 */
public class JaxRS_ProjectParser implements Parser {

    private JaxRS_Parser jaxRSParser;

    public JaxRS_ProjectParser(JaxRS_Parser parser) {
        this.jaxRSParser = parser;
    }

    @Override
    public void parseFile(File file) {
        if (file.isDirectory()) {
            for (File parsingFile : file.listFiles()) {
                this.parseFile(parsingFile);
            }
        } else {
            this.getJaxRSParser().parseFile(file);
            //TODO Extract the parsed information
        }
    }

    private JaxRS_Parser getJaxRSParser() {
        return jaxRSParser;
    }

}
