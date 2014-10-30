package org.qa82.analyzer.ui;

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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.qa82.analyzer.core.impl.Analyzer;
import org.qa82.analyzer.core.impl.Element;
import org.qa82.analyzer.core.impl.Project;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("analyzer")
public class AnalyzerUI extends UI {

	private UserData userData;
	
	private VerticalLayout resultLayout;
	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = AnalyzerUI.class)
	public static class Servlet extends VaadinServlet {
	}
	
	public UserData getUserData() {
		System.out.println(userData);
		return userData;
	}

	protected HorizontalLayout getMenu() {
		final HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSpacing(true);
		
		Project project = new Project();
		project.setRepository(userData.getRepository());
		
		Analyzer analyzer = new Analyzer(project);
		List<String> uris = analyzer.getResultUris(List.class, null);
		System.out.println(uris.size());
		
		ComboBox elements = new ComboBox();
		elements.addItems(uris);
		
		TextField condition = new TextField();
		elements.setImmediate(true);

		OnEnterKeyHandler onEnterHandler=new OnEnterKeyHandler(){
		            @Override
		            public void onEnterKeyPressed() {
		                Notification.show("Test",
		                    Notification.Type.HUMANIZED_MESSAGE);
		            }
		        };
		onEnterHandler.installOn(condition);
		
		Button executeButton = new Button("Run");
		
		executeButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				Project project = new Project();
				project.setRepository(userData.getRepository());
				
				Analyzer analyzer = new Analyzer(project);
				Object result = analyzer.execute((String)elements.getValue(), null);
				showResult(result);				
			}
		});
		
		Button preferencesButton = new Button("Preferences");
		
		preferencesButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				PreferencesDialog dialog = new PreferencesDialog();
				
				UI.getCurrent().addWindow(dialog);
			}
		});
		
		horizontalLayout.addComponent(elements);
		horizontalLayout.addComponent(condition);
		horizontalLayout.addComponent(executeButton);
		horizontalLayout.addComponent(preferencesButton);
		
		return horizontalLayout;
	}

	@Override
	protected void init(VaadinRequest request) {
		userData = new UserData();		
		resultLayout = new VerticalLayout();
		
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		
		layout.addComponent(getMenu());
		layout.addComponent(resultLayout);
		
		setContent(layout);
	}
	
	private void showResult(Object result) {
		resultLayout.removeAllComponents();
		
		String label;
		if (result == null) {
			resultLayout.addComponent(new Label("No Result"));
			return;
		} 
		
		if (result instanceof String) {
			label = (String)result;
			resultLayout.addComponent(new Label(label));
			return;
		}
		
		if (result instanceof List) {
			List<Element> resultList = (List<Element>)result;
			
			BeanItemContainer<Element> dataSource = new BeanItemContainer<Element>(Element.class);
			dataSource.addAll(resultList);
		    
			Table table = new Table();
			table.setContainerDataSource(dataSource);
			table.setSizeFull();
		    // create generated column and specify our "generator/formatter"
		    // table.addGeneratedColumn("rules", new RuleGenerator());
		    
		
			resultLayout.addComponent(table);
			return;
		}		
	}

}