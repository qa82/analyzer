package org.qa82.analyzer.core.providers.java;

import org.qa82.analyzer.core.Analyzer;
import org.qa82.analyzer.core.Information;
import org.qa82.analyzer.core.Repository;
import org.qa82.analyzer.core.bean.InformationNeedDescription;
import org.qa82.analyzer.core.bean.InformationType;
import org.qa82.analyzer.core.bean.ParameterList;
import org.qa82.analyzer.core.impl.AbstractInformationProvider;
import org.qa82.analyzer.core.impl.Element;
import org.qa82.analyzer.core.providers.java.parser.jaxrs.JaxRs_Compatibility;
import org.qa82.analyzer.core.providers.java.parser.jaxrs.JaxRs_Parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Pascal Giessler on 10.12.2014.
 */
public class JaxRs_ServiceProvider extends AbstractInformationProvider {

    private JaxRs_Compatibility parser;

    public JaxRs_ServiceProvider(Analyzer analyzer) {
        super(analyzer);
        this.parser = new JaxRs_Parser();
    }

    @Override
    public String getName() {
        return "JaxRs.Service";
    }

    @Override
    public String getDescription() {
        return "JaxRs.Service";
    }

    @Override
    public InformationNeedDescription getProvidedInformation() {
		return new InformationNeedDescription(new InformationType(Element.class, "http://cos.ontoware.org/cos#web-service",this.getDescription()), new ArrayList<>());
    }

    @Override
	public List<Information> resolve(InformationType expectedInformation, ParameterList parameters) {
        int id = 0;
        ArrayList<Information> informationList = new ArrayList<>();

        Set<Repository> repositories = this.analyzer.getProject().getRepositories();
        for(Repository r : repositories) {
            for (File javaFile : r.searchFileEndingWith("java")) {
                parser.parseFile(javaFile);
                if(parser.isFileAJaxRsClass()) {
                    informationList.add(new Element(new Integer(id).toString()));
                    id++;
                }
            }
        }
		return informationList;
    }
}
