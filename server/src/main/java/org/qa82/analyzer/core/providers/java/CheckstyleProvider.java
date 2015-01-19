package org.qa82.analyzer.core.providers.java;

import com.google.common.collect.Lists;
import org.qa82.analyzer.core.Analyzer;
import org.qa82.analyzer.core.Information;
import org.qa82.analyzer.core.bean.InformationNeedDescription;
import org.qa82.analyzer.core.bean.InformationType;
import org.qa82.analyzer.core.bean.ParameterList;
import org.qa82.analyzer.core.impl.AbstractInformationProvider;
import org.qa82.analyzer.core.impl.CodeRelatedInformation;
import org.qa82.analyzer.core.impl.Element;
import org.qa82.analyzer.core.impl.StringInformation;
import org.qa82.analyzer.core.providers.java.parser.checkstyle.CheckstyleParser;
import org.qa82.analyzer.core.providers.java.parser.checkstyle.checks.RandomSourceCheck;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An information provider for code quality attributes, derived by static code analysis using Checkstyle.
 *
 * @author Max Vogler, Karlsruhe Institute of Technology, Germany
 */
public class CheckstyleProvider extends AbstractInformationProvider {

    public CheckstyleProvider(Analyzer analyzer) {
        super(analyzer);
    }

    @Override
    public String getName() {
        return "StaticCodeAnalysis.Checkstyle";
    }

    @Override
    public String getDescription() {
        return "StaticCodeAnalysis.Checkstyle";
    }

    @Override
    public List<Information> resolve(InformationType expectedInformation, ParameterList parameters) {
        // TODO: Load configuration from ParameterList
        CheckstyleParser parser = new CheckstyleParser();
        parser.setConfiguration(Lists.newArrayList(RandomSourceCheck.class));

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

    @Override
    public InformationNeedDescription getProvidedInformation() {
        // TODO: Provide correct InformationNeedDescription

        return new InformationNeedDescription(
                new InformationType(
                        Element.class,
                        "http://cos.ontoware.org/cos#checkstyle",
                        this.getDescription()
                ),
                
                Collections.emptyList()
        );
    }

}
