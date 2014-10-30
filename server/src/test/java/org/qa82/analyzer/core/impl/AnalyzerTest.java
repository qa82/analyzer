package org.qa82.analyzer.core.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.qa82.analyzer.core.Parameters;
import org.qa82.analyzer.core.exceptions.InformationNeedNotResolvableException;

@RunWith(MockitoJUnitRunner.class)
public class AnalyzerTest {

	private Analyzer analyzer = null;
	@Mock private Project project;
	@Mock private InformationProvider informationProvider;
	@Mock private Parameters parameters;
	@Mock private ResolvedInformation resolvedInformation;
	
	@Before
	public void setUp() throws Exception {
		analyzer = new Analyzer(project);
		analyzer.addInformationProvider(informationProvider);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test (expected=InformationNeedNotResolvableException.class)
	public void testResolveWithNullValue() throws InformationNeedNotResolvableException {
		//given
		InformationNeed informationNeed = null;
		//when
		analyzer.resolve(informationNeed, parameters);
		//then expect InformationNeedNotResolvableException
	}

	@Test (expected=InformationNeedNotResolvableException.class)
	public void testResolveWithUnsupportedInformationNeed() throws InformationNeedNotResolvableException {
		//given
		InformationNeed informationNeed = new InformationNeed("Unsupported with cryptic name");
		//when
		analyzer.resolve(informationNeed, parameters);
		//then expect InformationNeedNotResolvableException
	}

	@Test
	public void testResolveSupportedNeed() throws InformationNeedNotResolvableException {
		//given
		InformationNeed informationNeed = new InformationNeed("http://cos.ontoware.org/cos#web-service");
		//when
		when(informationProvider.supports(informationNeed, parameters)).thenReturn(true);
		when(informationProvider.resolve(informationNeed, parameters)).thenReturn(resolvedInformation);
		//then
		ResolvedInformation resolvedInformation = analyzer.resolve(informationNeed, parameters);
		assertTrue("The analyzer should offer the information of the information provider.", resolvedInformation.isInformationPresent());
	}

	@Test
	public void testResolveUnsupportedNeed() throws InformationNeedNotResolvableException {
		//given
		InformationNeed unsupportedNeed = new InformationNeed("unsupported");
		//when
		when(informationProvider.supports(unsupportedNeed, parameters)).thenReturn(false);
		//then
		ResolvedInformation resolvedInformation = analyzer.resolve(unsupportedNeed, parameters);
		assertFalse("The analyzer should not find any information.", resolvedInformation.isInformationPresent());
	}
	

}
