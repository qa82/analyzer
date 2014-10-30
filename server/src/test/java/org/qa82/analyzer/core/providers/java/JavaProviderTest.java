package org.qa82.analyzer.core.providers.java;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.qa82.analyzer.core.Parameters;
import org.qa82.analyzer.core.impl.InformationNeed;

@RunWith(MockitoJUnitRunner.class)
public class JavaProviderTest {
	
	private JavaProvider javaProvider;
	@Mock private Parameters parameters;

	@Before
	public void setUp() throws Exception {
		this.javaProvider = new JavaProvider(null);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSupport() {
		//given
		InformationNeed informationNeed = new InformationNeed("http://cos.ontoware.org/cos#web-service");
		//when
		Boolean supportsWebServices = javaProvider.supports(informationNeed, parameters);
		//then
		assertTrue("The java jax-rs provider should support the analysis of Webservices.", supportsWebServices);
	}

}
