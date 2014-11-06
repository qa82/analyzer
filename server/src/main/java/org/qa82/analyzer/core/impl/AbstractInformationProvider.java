/*******************************************************************************
* Copyright (c) 2014 Michael Gebhart (michael.gebhart@qa82.org).
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
* Michael Gebhart - initial idea and concept
* 
*******************************************************************************/

package org.qa82.analyzer.core.impl;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.qa82.analyzer.core.InformationProvider;
import org.qa82.analyzer.core.annotations.Parameter;
import org.qa82.analyzer.core.annotations.ProvidedFunction;

/**
 * Offers default methods for InformationProviders of any kind.
 * 
 * @author Roland Steinegger, Karlsruhe Institute of Technology, Germany
 */
public abstract class AbstractInformationProvider implements InformationProvider {
	protected SimpleAnalyzer analyzer;
	
	public AbstractInformationProvider(SimpleAnalyzer analyzer) {
		this.analyzer = analyzer;
	}

	private List<Method> getProvidedFunctions(InformationProvider provider) {
		List<Method> methods = new ArrayList<Method>();

		for (Method method : provider.getClass().getMethods()) {
			if (method.isAnnotationPresent(ProvidedFunction.class)) {
				methods.add(method);
			}
		}

		return methods;
	}

	/**
	 * Executes the given resultUri with the provided parameters
	 * 
	 * @param resultUri
	 * @param parameters
	 * @return Result of resultUri and parameters
	 */
	public Object execute(String resultUri, Map<String, Object> parameters) {

		// TODO: Merging
		for (InformationProvider provider : analyzer.getProviders()) {

			for (Method method : getProvidedFunctions(provider)) {

				Parameter[] parameterAnnotations = method.getAnnotatedReturnType().getAnnotationsByType(Parameter.class);

				for (Parameter parameterAnnotation : parameterAnnotations) {
					if (parameterAnnotation.uri().equals(resultUri)) {
						System.out.println("URI found");

						try {
							if (method.getParameterCount() == 0) {
								Object result = method.invoke(provider);
								return result;
							} else {
								Object result = method.invoke(provider, parameters);
								return result;
							}
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {

							e.printStackTrace();
						}
					}
				}
			}
		}

		return null;
	}

	/**
	 * Executes the given resultUri with parameters and returns the result as list of elements
	 * 
	 * @param resultUri
	 * @param parameters
	 * @return Result of the executed resultUri
	 */
	@SuppressWarnings("unchecked")
	public List<Element> getList(String resultUri, Map<String, Object> parameters) {
		Object result = execute(resultUri, parameters);

		if (result != null && result instanceof List)
			return ((List<Element>) result);

		return null;
	}

	public List<Element> getList(String resultUri) {
		return getList(resultUri, null);
	}

	/**
	 * Transform array of URIs and parameters into a map. URIs are the keys and parameters are the values.
	 * 
	 * @param uris
	 * @param parameters
	 * @return created map
	 */
	private Map<String, Object> createMap(String[] uris, Object[] parameters) {
		Map<String, Object> result = new HashMap<String, Object>();

		if (uris == null || parameters == null)
			return result;

		if (uris.length != parameters.length)
			return result;

		for (int i = 0; i < uris.length; i++) {
			result.put(uris[i], parameters[i]);
		}

		return result;
	}

	/**
	 * Transform a URI and parameter into a map with exactly one value. URI is the key of this map, parameter is the value.
	 * 
	 * @param uri
	 * @param parameter
	 * @return created map
	 */
	private Map<String, Object> createMap(String uri, Object parameter) {
		Map<String, Object> result = new HashMap<String, Object>();

		if (uri == null || parameter == null)
			return result;

		result.put(uri, parameter);

		return result;
	}

	/**
	 * Executes the given resultUri with a set of uris and parameters and returns the result as list of elements.
	 * 
	 * @param resultUri
	 * @param uris
	 * @param parameters
	 * @return
	 */
	public List<Element> getList(String resultUri, String[] uris, Object[] parameters) {
		return getList(resultUri, createMap(uris, parameters));
	}

	public List<Element> getList(String resultUri, String uri, Object parameter) {
		return getList(resultUri, createMap(uri, parameter));
	}

	/**
	 * Executes the given resultUri with parameters provided and returns the result as one element.
	 * 
	 * @param resultUri
	 * @param parameters
	 * @return Result of resultUri and parameters
	 */
	public Element getElement(String resultUri, Map<String, Object> parameters) {
		Object result = execute(resultUri, parameters);

		if (result != null && result instanceof Element)
			return (Element) result;

		return null;
	}

	/**
	 * Executes the resultUri and returns the result as element
	 * 
	 * @param resultUri
	 * @return
	 */
	public Element getElement(String resultUri) {
		return getElement(resultUri, null);
	}

	/**
	 * Executes the resultUri with parameters (provided as set of uris and parameters) and returns the result as element
	 * 
	 * @param resultUri
	 * @return
	 */
	public Element getElement(String resultUri, String[] uris, Object[] parameters) {
		return getElement(resultUri, createMap(uris, parameters));
	}

	/**
	 * Executes the resultUri with the one parameter (provided as uri and parameter) and returns the result as element
	 * 
	 * @param resultUri
	 * @return
	 */
	public Element getElement(String resultUri, String uri, Object parameter) {
		return getElement(resultUri, createMap(uri, parameter));
	}

	/**
	 * Returns the available resultUris
	 * 
	 * @param resultType
	 * @param inputUris
	 * @return
	 */
	public List<String> getResultUris(Class<?> resultType, List<String> inputUris) {
		List<String> resultUris = new ArrayList<String>();

		for (InformationProvider provider : analyzer.getProviders()) {
			for (Method method : getProvidedFunctions(provider)) {
				AnnotatedType type = method.getAnnotatedReturnType();
				Parameter[] parameterAnnotations = type.getAnnotationsByType(Parameter.class);
				if (parameterAnnotations.length != 1)
					continue;

				// TODO: Other cases instead of 0
				List<String> parameterUris = getParameterUris(method.getParameters());

				if (method.getParameterCount() == 0 && inputUris == null
						|| (inputUris != null && inputUris.containsAll(parameterUris) && parameterUris.containsAll(inputUris))) {

					if (method.getReturnType().equals(resultType)) {
						resultUris.add(parameterAnnotations[0].uri());
					}
				}
			}
		}

		return resultUris;
	}

	private List<String> getParameterUris(java.lang.reflect.Parameter[] parameters) {
		List<String> result = new ArrayList<String>();

		for (java.lang.reflect.Parameter parameter : parameters) {
			Parameter[] parameterAnnotations = parameter.getAnnotationsByType(Parameter.class);

			if (parameterAnnotations.length != 1)
				continue;

			result.add(parameterAnnotations[0].uri());
		}

		return result;
	}
}
