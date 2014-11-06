package org.qa82.analyzer.core.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.qa82.analyzer.core.Analyzer;
import org.qa82.analyzer.core.Information;
import org.qa82.analyzer.core.InformationProvider;
import org.qa82.analyzer.core.Parameters;
import org.qa82.analyzer.core.bean.InformationType;
import org.qa82.analyzer.core.exceptions.InformationNeedNotResolvableException;

@RunWith(MockitoJUnitRunner.class)
public class SimpleAnalyzerTest {

	private Analyzer analyzer = null;
	@Mock private Project project;
    @Mock
    private InformationProvider informationProvider;
	@Mock private Parameters parameters;
	
	@Before
	public void setUp() throws Exception {
		analyzer = new SimpleAnalyzer(project);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test (expected=InformationNeedNotResolvableException.class)
	public void testResolveWithNullValue() throws InformationNeedNotResolvableException {
		//given
		InformationType expectedInformation = null;
		//when
		analyzer.resolve(expectedInformation, parameters);
		//then expect InformationNeedNotResolvableException
	}

	@Test (expected=InformationNeedNotResolvableException.class)
	public void testResolveWithUnsupportedInformationNeed() throws InformationNeedNotResolvableException {
		//given
		InformationType expectedInformation = new InformationType(Element.class, "Unsupported with cryptic name");
		//when
		analyzer.resolve(expectedInformation, parameters);
		//then expect InformationNeedNotResolvableException
	}

	@Test
	public void testResolveSupportedNeed() throws InformationNeedNotResolvableException {
		// given
		InformationType expectedInformation = new InformationType(Element.class, "Some information type");

		when(informationProvider.provides(expectedInformation, parameters)).thenReturn(true);
		when(informationProvider.resolve(expectedInformation, parameters)).thenReturn(new Element(""));
        analyzer.addInformationProvider(informationProvider);
		// when
		Information resolvedInformation = analyzer.resolve(expectedInformation, parameters);
		// then
		assertTrue("The analyzer should offer the information of the information provider.", resolvedInformation.isInformationPresent());
	}

    @Test(expected = InformationNeedNotResolvableException.class)
	public void testResolveUnsupportedNeed() throws InformationNeedNotResolvableException {
		//given
		InformationType unsupportedInformation = new InformationType(Element.class, "Some unsupported information type");
		when(informationProvider.provides(unsupportedInformation, parameters)).thenReturn(false);
        analyzer.addInformationProvider(informationProvider);
		//when
        analyzer.resolve(unsupportedInformation, parameters);
        // then expect a InformationNeedNotResolvableException
	}
	

}
