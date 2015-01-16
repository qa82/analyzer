package org.qa82.analyzer.core.providers.java;

import com.puppycrawl.tools.checkstyle.api.Check;
import org.qa82.analyzer.core.Analyzer;
import org.qa82.analyzer.core.Information;
import org.qa82.analyzer.core.bean.InformationNeedDescription;
import org.qa82.analyzer.core.bean.InformationType;
import org.qa82.analyzer.core.bean.ParameterList;
import org.qa82.analyzer.core.bean.ParametersTypes;
import org.qa82.analyzer.core.impl.AbstractInformationProvider;
import org.qa82.analyzer.core.impl.Element;
import org.qa82.analyzer.core.impl.StringInformation;
import org.qa82.analyzer.core.providers.java.parser.checkstyle.CheckstyleError;
import org.qa82.analyzer.core.providers.java.parser.checkstyle.CheckstyleParser;
import org.qa82.analyzer.core.providers.java.parser.checkstyle.checks.RandomSourceCheck;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CheckstyleProvider extends AbstractInformationProvider {

    private final CheckstyleParser parser;

    public CheckstyleProvider(Analyzer analyzer) {
        super(analyzer);

        parser = new CheckstyleParser();
        parser.setConfigurationFile(createExampleConfigurationFile());
    }

    @Override
    public String getName() {
        return "Checkstyle";
    }

    @Override
    public String getDescription() {
        return "Get information about the code quality.";
    }

    @Override
    public List<Information> resolve(InformationType expectedInformation, ParameterList parameters) {
        return analyzer
                // loop over all project repositories
                .getProject().getRepositories().stream()

                        // find all java files
                .flatMap(repository -> repository.searchFileEndingWith("java").stream())

                        // run the Checkstyle parser on each file and collect Checkstyle errors
                .flatMap(file -> {
                    parser.parseFile(file);
                    return parser.getLastResults().stream();
                })

                        // convert each error to a StringInformation
                .map(CheckstyleError::toString).map(StringInformation::new)

                        // finally return the list of error strings
                .collect(Collectors.toList());
    }

    @Override
    public InformationNeedDescription getProvidedInformation() {
        // TODO: Provide correct InformationNeedDescription
        return new InformationNeedDescription(new InformationType(
                Element.class,
                "http://cos.ontoware.org/cos#web-service#checkstyle",
                this.getDescription()),
                new ParametersTypes()
        );
    }

    protected String createExampleConfigurationFile() {
        File file = null;

        // Add additional checks here:
        Stream<Class<? extends Check>> checks = Stream.of(RandomSourceCheck.class);

        try {
            file = File.createTempFile("checkstyle-example-config", "xml");
            PrintWriter writer = new PrintWriter(file);

            writer.println("<?xml version=\"1.0\"?>");
            writer.println("<!DOCTYPE module PUBLIC");
            writer.println("  \"-//Puppy Crawl//DTD Check Configuration 1.3//EN\"");
            writer.println("  \"http://www.puppycrawl.com/dtds/configuration_1_3.dtd\">");
            writer.println("<module name=\"Checker\">");
            writer.println("  <module name=\"TreeWalker\">");

            checks.map(cls -> cls.getCanonicalName())
                    .map(name -> "    <module name=\"" + name + "\"/>")
                    .forEach(writer::println);

            writer.println("  </module>");
            writer.println("</module>");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file.getAbsolutePath();
    }
}
