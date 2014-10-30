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

package org.qa82.analyzer.ui;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public class PreferencesDialog extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public PreferencesDialog() {
		super("Preferences"); // Set window caption

		center();
        setClosable(true);
        setModal(true);;
        setResizable(false);
        
        FormLayout layout = new FormLayout();
        
        // Form for editing the bean
        final BeanFieldGroup<UserData> binder =
                new BeanFieldGroup<UserData>(UserData.class);

        UserData userData = ((AnalyzerUI)UI.getCurrent()).getUserData();
        binder.setItemDataSource(userData);
        layout.addComponent(binder.buildAndBind("Repository", "repository"));

        // Buffer the form content
        binder.setBuffered(true);
        layout.addComponent(new Button("Save", new ClickListener() {
        	
			private static final long serialVersionUID = 1L;

			@Override
            public void buttonClick(ClickEvent event) {
                try {
                    binder.commit();
                    close();
                } catch (CommitException e) {
                }
            }
        }));
        
        setContent(layout);
	}	
}
