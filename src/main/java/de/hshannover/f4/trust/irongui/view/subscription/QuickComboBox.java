/*
 * #%L
 * =====================================================
 *   _____                _     ____  _   _       _   _
 *  |_   _|_ __ _   _ ___| |_  / __ \| | | | ___ | | | |
 *    | | | '__| | | / __| __|/ / _` | |_| |/ __|| |_| |
 *    | | | |  | |_| \__ \ |_| | (_| |  _  |\__ \|  _  |
 *    |_| |_|   \__,_|___/\__|\ \__,_|_| |_||___/|_| |_|
 *                             \____/
 * 
 * =====================================================
 * 
 * Hochschule Hannover
 * (University of Applied Sciences and Arts, Hannover)
 * Faculty IV, Dept. of Computer Science
 * Ricklinger Stadtweg 118, 30459 Hannover, Germany
 * 
 * Email: trust@f4-i.fh-hannover.de
 * Website: http://trust.f4.hs-hannover.de/
 * 
 * This file is part of irongui, version 0.4.2,
 * implemented by the Trust@HsH research group at the Hochschule Hannover.
 * %%
 * Copyright (C) 2010 - 2013 Trust@HsH
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */



package de.hshannover.f4.trust.irongui.view.subscription;




import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.MenuElement;
import javax.swing.MenuSelectionManager;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class QuickComboBox extends JComboBox implements MenuElement {

	private static final long serialVersionUID = 5676645360606860268L;

	public QuickComboBox() {
		//setPopupVisible(false);
		this.addPopupMenuListener(new PopupMenuListener() {
			
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				
				
			}
			
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	public  void hidePopup() {
		System.err.println("hidePopup");
	}
	
	@Override
	public void processMouseEvent(MouseEvent event, MenuElement[] path,
			MenuSelectionManager manager) {
		if (event.getID() == MouseEvent.MOUSE_RELEASED) {
			for (Component c : getComponents()) {
				MouseEvent me = new MouseEvent(c, event.getID(),
						event.getWhen(), event.getModifiers(), event.getX(),
						event.getY(), event.getClickCount(),
						event.isPopupTrigger(), event.getButton());
				for (MouseListener ml : c.getMouseListeners()) {
					ml.mouseReleased(me);
				}
			}
		}
		processMouseEvent(event);
	}

	@Override
	public void processKeyEvent(KeyEvent event, MenuElement[] path,
			MenuSelectionManager manager) {
		processKeyEvent(event);
	}

	@Override
	public void menuSelectionChanged(boolean isIncluded) {
		// TODO Auto-generated method stub

	}

	@Override
	public MenuElement[] getSubElements() {		
		return new MenuElement[0];
	}

	@Override
	public Component getComponent() {
		return this;
	}

}
