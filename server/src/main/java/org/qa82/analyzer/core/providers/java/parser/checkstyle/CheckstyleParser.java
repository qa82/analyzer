package org.qa82.analyzer.core.providers.java.parser.checkstyle;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.ConfigurationLoader;
import com.puppycrawl.tools.checkstyle.PropertiesExpander;
import com.puppycrawl.tools.checkstyle.api.AuditEvent;
import com.puppycrawl.tools.checkstyle.api.AuditListener;
import com.puppycrawl.tools.checkstyle.api.Check;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import org.qa82.analyzer.core.impl.CodeRelatedInformation;
import org.qa82.analyzer.core.providers.java.parser.Parser;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * A Parser based on Checkstyle static code analysis
 *
 * @author Max Vogler, Karlsruhe Institute of Technology, Germany
 */
public class CheckstyleParser implements Parser {

    private Collection<CodeRelatedInformation> results = Collections.emptyList();

    private File configurationFile;

    /**
     * Applies a Checkstyle XML-configuration file.
     * This resets and overwrites any previous configuration.
     *
     * @param file an existing, readable Checkstyle configuration file.
     */
    public void setConfigurationFile(@NotNull File file) {
        Preconditions.checkNotNull(file);
        Preconditions.checkArgument(file.exists(), file.getAbsoluteFile() + " does not exist.");
        Preconditions.checkArgument(file.isFile(), file.getAbsolutePath() + " is not a file.");

        this.configurationFile = file;
    }

    /**
     * Applies a simple Checkstyle configuration, which executes all provided checks.
     * This resets and overwrites any previous configuration.
     *
     * @param checks the Checks to execute
     */
    public void setConfiguration(@NotNull Collection<Class<? extends Check>> checks) {
        Preconditions.checkNotNull(checks);

        try {
            configurationFile = File.createTempFile("checkstyle-simple-config", "xml");
            PrintWriter writer = new PrintWriter(configurationFile);

            writer.println("<?xml version='1.0'?>");
            writer.println("<!DOCTYPE module PUBLIC");
            writer.println("\t'-//Puppy Crawl//DTD Check Configuration 1.3//EN'");
            writer.println("\t'http://www.puppycrawl.com/dtds/configuration_1_3.dtd'>");
            writer.println("<module name='Checker'>");
            writer.println("\t<module name='TreeWalker'>");

            checks.stream()
                    .map(Class::getCanonicalName)
                    .map(name -> String.format("\t\t<module name='%s'/>", name))
                    .forEach(writer::println);

            writer.println("\t</module>");
            writer.println("</module>");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            configurationFile = null;
        }
    }

    @Override
    public void parseFile(@NotNull File file) {
        Preconditions.checkNotNull(configurationFile, "CheckstyleParser.parseFile() requires a configuration! Use setConfigurationFile() or setConfiguration() to create one.");
        Preconditions.checkNotNull(file);

        results = new ArrayList<>();

        try {
            // The initialization is inspired by com.puppycrawl.tools.checkstyle.Main.main()
            Checker checker = new Checker();
            checker.setModuleClassLoader(Checker.class.getClassLoader());
            checker.configure(ConfigurationLoader.loadConfiguration(
                    configurationFile.getAbsolutePath(),
                    new PropertiesExpander(System.getProperties())
            ));
            checker.addListener(listener);
            checker.process(Lists.newArrayList(file));
        } catch (CheckstyleException e) {
            e.printStackTrace();
        }
    }

    @NotNull
    public Collection<CodeRelatedInformation> getLastResults() {
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
            results.add(new CodeRelatedInformation(aEvt.getFileName(), aEvt.getLine(), aEvt.getMessage()));
        }

        @Override
        public void addException(AuditEvent aEvt, Throwable aThrowable) {
            aThrowable.printStackTrace();
        }
    };

}
