package org.qa82.analyzer.core.providers.java.parser.jaxrs;

import java.util.ArrayList;
import org.qa82.analyzer.core.providers.java.parser.Parser;

/**
 * This interface represents the method definitions, which should be implemented in any JAX RS parser
 * Created by Pascal Giessler on 04.12.14.
 */
public interface JaxRS_Parser extends Parser {

    /**
     * This method returns supported version number of the JAX RS reference implementation
     * @return supported version number
     */
    public String getSupportedJaxRSVersion();

    /**
     * This method returns the name of the supported JAX RS reference implemenation.
     * @return name of the JAX RS reference implementation
     */
    public String getSupportedReferenceImplementation();

    /**
     * This method return the name of the parsed java class
     * @return name of the parsed java class
     */
    public String getClassName();

    /**
     * This method returns the name of the resource
     * @return the name of the resource
     */
    public String getResourceName();

    /**
     * This method returns the supported methods of the current ressource
     * @return the supported HTTP methods
     */
    public ArrayList<HTTPMethods> getSupportedMethods();
}
