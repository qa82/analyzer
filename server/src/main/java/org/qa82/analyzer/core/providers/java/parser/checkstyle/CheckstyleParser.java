package org.qa82.analyzer.core.providers.java.parser.checkstyle;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.ConfigurationLoader;
import com.puppycrawl.tools.checkstyle.PropertiesExpander;
import com.puppycrawl.tools.checkstyle.api.AuditEvent;
import com.puppycrawl.tools.checkstyle.api.AuditListener;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import org.qa82.analyzer.core.providers.java.parser.Parser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public class CheckstyleParser implements Parser {

    private Collection<CheckstyleError> results;

    private String configurationFile;

    public void setConfigurationFile(String file) {
        Preconditions.checkArgument(new File(file).isFile());
        this.configurationFile = file;
    }

    @Override
    public void parseFile(File file) {
        Preconditions.checkNotNull(configurationFile);

        results = new ArrayList<>();

        try {
            Checker checker = new Checker();
            checker.setModuleClassLoader(Checker.class.getClassLoader());
            checker.configure(ConfigurationLoader.loadConfiguration(
                    configurationFile,
                    new PropertiesExpander(System.getProperties())
            ));
            checker.addListener(listener);
            checker.process(Lists.newArrayList(file));
        } catch (CheckstyleException e) {
            e.printStackTrace();
        }
    }

    public Collection<CheckstyleError> getLastResults() {
        return Lists.newArrayList(results);
    }

    private final AuditListener listener = new AuditListener() {
        @Override
        public void auditStarted(AuditEvent aEvt) {
        }

        @Override
        public void auditFinished(AuditEvent aEvt) {
        }

        @Override
        public void fileStarted(AuditEvent aEvt) {
        }

        @Override
        public void fileFinished(AuditEvent aEvt) {
        }

        @Override
        public void addError(AuditEvent aEvt) {
            results.add(new CheckstyleError(aEvt.getFileName(), aEvt.getLine(), aEvt.getMessage()));
        }

        @Override
        public void addException(AuditEvent aEvt, Throwable aThrowable) {
            aThrowable.printStackTrace();
        }
    };

}
