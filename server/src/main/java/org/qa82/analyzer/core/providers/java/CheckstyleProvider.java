package org.qa82.analyzer.core.providers.java;

import com.google.common.base.Preconditions;
import org.qa82.analyzer.core.Analyzer;
import org.qa82.analyzer.core.Information;
import org.qa82.analyzer.core.bean.InformationType;
import org.qa82.analyzer.core.bean.ParameterList;
import org.qa82.analyzer.core.impl.AbstractInformationProvider;
import org.qa82.analyzer.core.impl.CodeRelatedInformation;
import org.qa82.analyzer.core.impl.StringInformation;
import org.qa82.analyzer.core.providers.java.parser.checkstyle.CheckstyleParser;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An information provider for code quality attributes, derived by static code analysis using Checkstyle.
 *
 * @author Max Vogler, Karlsruhe Institute of Technology, Germany
 */
public abstract class CheckstyleProvider extends AbstractInformationProvider {

    public CheckstyleProvider(Analyzer analyzer) {
        super(analyzer);
    }

    @Override
    @Nonnull
    public List<Information> resolve(InformationType expectedInformation, ParameterList parameters) {
        CheckstyleParser parser = createParser(expectedInformation, parameters);

        Preconditions.checkNotNull(parser.getConfigurationFile(), "CheckstyleProvider.resolve() requires a configured parser. " +
                "Override CheckstyleProvider.createParser() to call CheckstyleParser.setConfigurationFile() or " +
                "CheckstyleParser.setConfiguration() to create a configuration.");

        // TODO: Add client support for CodeRelatedInformation
        return analyzer
                .getProject().getRepositories().stream() // loop over all project repositories
                .flatMap(repository -> repository.searchFileEndingWith("java").stream()) // find all java files
                .flatMap(file -> { // run the Checkstyle parser on each file and collect Checkstyle errors
                    parser.parseFile(file);
                    return parser.getLastResults().stream();
                })
                .map(CodeRelatedInformation::toString).map(StringInformation::new) // convert each error to a StringInformation
                .collect(Collectors.toList()); // finally return the list of error strings
    }

    /**
     * Create and configure a CheckstyleParser. Override this method to configure the CheckstyleParser.
     *
     * @param expectedInformation
     * @param parameters
     * @return a configured CheckstyleParser
     */
    @Nonnull
    protected CheckstyleParser createParser(InformationType expectedInformation, ParameterList parameters) {
        return new CheckstyleParser();
    }

}
