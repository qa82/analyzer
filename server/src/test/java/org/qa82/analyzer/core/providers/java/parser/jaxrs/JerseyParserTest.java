package org.qa82.analyzer.core.providers.java.parser.jaxrs;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * This class represents the tests for the Jersey JAX RS parser
 * Created by Pascal Giessler on 04.12.14.
 */
public class JerseyParserTest {

    private Jersey_JaxRS_Parser parser;
    private File testFile1;
    private File testFile2;

    @Before
    public void init() {
        this.parser = new Jersey_JaxRS_Parser();
        this.testFile1 = new File("src/test/java/org/qa82/analyzer/core/providers/java/parser/jaxrs/JerseyParserTestFile.java");
        this.testFile2 = new File("src/test/java/org/qa82/analyzer/core/providers/java/parser/jaxrs/JerseyParserTestFile2.java");
    }

    @Test
    public void shouldReturnAVersionNumber() {
        assertNotNull(this.parser.getSupportedJaxRSVersion());
    }

    @Test
    public void shouldReturnTheNameOfTheSupportedReferenceImplementation() {
        assertNotNull(this.parser.getSupportedReferenceImplementation());
    }

    @Test
    public void shouldCatchNotGivenFileObject() {
        this.parser.parseFile(null);
    }

    @Test
    public void shouldReturnResourceName() {
        this.parser.parseFile(this.testFile1);

        assertEquals("myresource", this.parser.getResourceName());
    }

    @Test
    public void shouldReturnOneSupportedHTTPMethods() {
        this.parser.parseFile(this.testFile1);

        assertTrue(this.parser.getSupportedMethods().size() == 1);
        assertEquals(HTTPMethods.GET, this.parser.getSupportedMethods().get(0));
    }

    @Test
    public void shouldReturnTwoSupportedHTTPMethods() {
        this.parser.parseFile(this.testFile2);

        assertTrue(this.parser.getSupportedMethods().size() == 2);
        assertEquals(HTTPMethods.GET, this.parser.getSupportedMethods().get(0));
        assertEquals(HTTPMethods.POST, this.parser.getSupportedMethods().get(1));
    }

    @Test
    public void shouldReturnCorrectClassName() {
        this.parser.parseFile(this.testFile1);
        assertEquals("JerseyParserTestFile", this.parser.getClassName());
    }
}
