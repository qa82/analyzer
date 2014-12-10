package org.qa82.analyzer.core.providers.java.parser.jaxrs;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is responsible for parsing JAX RS services made with the Jersey framework in version 2.
 * Created by Pascal Giessler on 04.12.14.
 */
public class JaxRs_Parser implements JaxRs_Compatibility {

    private ArrayList<HTTPMethods> supportedMethods;
    private String resourceName;
    private String className;
    private Logger logger = Logger.getLogger(JaxRs_Parser.class.getName());

    @Override
    public String getSupportedJaxRSVersion() {
        return "2.0";
    }

    @Override
    public String getSupportedReferenceImplementation() {
        return "Jersey";
    }

    @Override
    public String getResourceName() {
        return this.resourceName;
    }

    @Override
    public ArrayList<HTTPMethods> getSupportedMethods() {
        return this.supportedMethods;
    }

    @Override
    public String getClassName() {
        return this.className;
    }

    @Override
    public boolean isFileAJaxRsClass() {
        return this.isThisAResourceClass();
    }

    /**
     * This method is responsible for starting the parser
     *
     * @param file The File that should be parsed
     */
    public void parseFile(File file) {
        this.iterateOverAllLinesOfFile(file);
    }

    /**
     * This is the default constructor of the Jersey JAX RS Parser, which is responsible for initialization of important instance variables
     */
    public JaxRs_Parser() {
        this.supportedMethods = new ArrayList<>();
    }

    /**
     * This methods iterates over all lines of the given file object
     *
     * @param file the files, which shall be analyzed
     */
    private void iterateOverAllLinesOfFile(File file) {
        try {
            for (String line : Files.readAllLines(file.toPath())) {
                this.checkForResourceClass(line);
                if (this.isThisAResourceClass()) {
                    this.extractClassName(line);
                    this.checkForHTTPMethods(line);
                }
            }
        } catch (IOException e) {
            logger.debug("Error while iterating over the lines of " + file.toPath());
        } catch (NullPointerException e) {
            logger.error("You have to provide a file object");
        }
    }

    /**
     * Extract the classname of the parsed class
     *
     * @param line The line, which should be checked for a class name
     */
    private void extractClassName(String line) {
        Pattern classNamePattern = Pattern.compile("class ([a-zA-Z0-9_-]*)");
        Matcher matcherForClassNamePattern = classNamePattern.matcher(line);
        if (matcherForClassNamePattern.find()) {
            this.setClassName(matcherForClassNamePattern.group(1));
        }
    }

    /**
     * This method checks, if the given line contains a resource definition
     *
     * @param line The line, which should be checked
     */
    void checkForResourceClass(String line) {
        Pattern pathAnnotationPattern = Pattern.compile("@Path\\(\"([a-zA-Z0-9_-]*)\"\\)");
        Matcher matcherForPathAnnotationPattern = pathAnnotationPattern.matcher(line);
        if (matcherForPathAnnotationPattern.find()) {
            this.setResourceName(matcherForPathAnnotationPattern.group(1));
        }
    }

    /**
     * This method checks, if the given line contains a definition for HTTP methods
     *
     * @param line The line, which should be checked
     */
    void checkForHTTPMethods(String line) {
        if (this.matchRegularExpression("@GET", line)) {
            this.getSupportedMethods().add(HTTPMethods.GET);
        }
        if (this.matchRegularExpression("@POST", line)) {
            this.getSupportedMethods().add(HTTPMethods.POST);
        }
        if (this.matchRegularExpression("@PUT", line)) {
            this.getSupportedMethods().add(HTTPMethods.PUT);
        }
        if (this.matchRegularExpression("@DELETE", line)) {
            this.getSupportedMethods().add(HTTPMethods.DELETE);
        }
    }

    /**
     * This method is a generic for matching single regular expressions of given value
     *
     * @param regularExpression the pattern, which is written in a regular expression
     * @param value             the string, which should be checked
     * @return True, if the pattern was matched in the given value
     */
    private boolean matchRegularExpression(String regularExpression, String value) {
        Pattern pattern = Pattern.compile(regularExpression);
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }

    /**
     * This methods examines, if the current file is a resource class or not
     *
     * @return True, if the current file is a resource class
     */
    boolean isThisAResourceClass() {
        return this.getResourceName() != null;
    }

    private void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    private void setClassName(String className) {
        this.className = className;
    }

/*    public static void main (String[] args) {
        Jersey_JaxRS_Parser parser = new Jersey_JaxRS_Parser();
        parser.parseFile(new File("/Users/pascalgiessler/Developer/KIT/analyzer/server/src/test/java/org/qa82/analyzer/core/providers/java/parser/jaxrs/JerseyParserTestFile.java"));
    }*/
}
