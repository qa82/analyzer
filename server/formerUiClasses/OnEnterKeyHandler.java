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

import com.vaadin.event.FieldEvents;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.TextField;

public abstract class OnEnterKeyHandler {

     final ShortcutListener enterShortCut = new ShortcutListener(
                "EnterOnTextAreaShorcut", ShortcutAction.KeyCode.ENTER, null) {
                    /**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
                    public void handleAction(Object sender, Object target) {
                        onEnterKeyPressed();
                    }
                };

     public void installOn(final TextField component)
     {
        component.addFocusListener(
                new FieldEvents.FocusListener() {

                    /**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
                    public void focus(FieldEvents.FocusEvent event
                    ) {
                        component.addShortcutListener(enterShortCut);
                    }

                }
        );

        component.addBlurListener(
                new FieldEvents.BlurListener() {

                    /**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
                    public void blur(FieldEvents.BlurEvent event
                    ) {
                        component.removeShortcutListener(enterShortCut);
                    }

                }
        );
     }

     public abstract void onEnterKeyPressed();

}