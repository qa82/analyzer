package org.qa82.analyzer.core.providers.java;

import com.google.common.collect.Lists;
import org.qa82.analyzer.core.Analyzer;
import org.qa82.analyzer.core.bean.InformationNeedDescription;
import org.qa82.analyzer.core.bean.InformationType;
import org.qa82.analyzer.core.bean.ParameterList;
import org.qa82.analyzer.core.impl.Element;
import org.qa82.analyzer.core.providers.java.parser.checkstyle.CheckstyleParser;
import org.qa82.analyzer.core.providers.java.parser.checkstyle.checks.WeakRandomSourceCheck;

import javax.annotation.Nonnull;
import java.util.Collections;

/**
 * An information provider, which checks Java code repositories for cryptographic weak pseudo random number generators.
 *
 * @author Max Vogler, Karlsruhe Institute of Technology, Germany
 */
public class Security_WeakRandomSourceProvider extends CheckstyleProvider {

    public Security_WeakRandomSourceProvider(Analyzer analyzer) {
        super(analyzer);
    }

    @Override
    public String getName() {
        return "Security.WeakRandomSource";
    }

    @Override
    public String getDescription() {
        return "Security.WeakRandomSource";
    }


    @Override
    @Nonnull
    protected CheckstyleParser createParser(InformationType expectedInformation, ParameterList parameters) {
        CheckstyleParser parser = super.createParser(expectedInformation, parameters);
        parser.setConfiguration(Lists.newArrayList(WeakRandomSourceCheck.class));
        return parser;
    }

    @Override
    public InformationNeedDescription getProvidedInformation() {
        return new InformationNeedDescription(
                new InformationType(
                        Element.class,
                        "http://cos.ontoware.org/cos#security#weakrandomsource",
                        getDescription()
                ),

                Collections.emptyList()
        );
    }
}
