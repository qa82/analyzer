package org.qa82.analyzer.core.providers.java.parser.jaxrs;

import org.qa82.analyzer.core.providers.java.parser.Parser;

import java.io.File;

/**
 * Created by Pascal Giessler on 05.12.14.
 */
public class JaxRs_ProjectParser implements Parser {

    private JaxRs_Compatibility jaxRSParser;

    public JaxRs_ProjectParser(JaxRs_Compatibility parser) {
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

    private JaxRs_Compatibility getJaxRSParser() {
        return jaxRSParser;
    }

}
