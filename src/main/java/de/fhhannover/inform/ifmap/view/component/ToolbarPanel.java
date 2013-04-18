package de.fhhannover.inform.ifmap.view.component;

/*
 * #%L
 * ====================================================
 *   _____                _     ____  _____ _   _ _   _
 *  |_   _|_ __ _   _ ___| |_  / __ \|  ___| | | | | | |
 *    | | | '__| | | / __| __|/ / _` | |_  | |_| | |_| |
 *    | | | |  | |_| \__ \ |_| | (_| |  _| |  _  |  _  |
 *    |_| |_|   \__,_|___/\__|\ \__,_|_|   |_| |_|_| |_|
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
 * Website: http://trust.inform.fh-hannover.de/
 * 
 * This file is part of irongui, version 0.4.1, implemented by the Trust@FHH 
 * research group at the Hochschule Hannover, a program to visualize the content
 * of a MAP Server (MAPS), a crucial component within the TNC architecture.
 * 
 * The development was started within the bachelor
 * thesis of Tobias Ruhe at Hochschule Hannover (University of
 * Applied Sciences and Arts Hannover). irongui is now maintained
 * and extended within the ESUKOM research project. More information
 * can be found at the Trust@FHH website.
 * %%
 * Copyright (C) 2010 - 2013 Trust@FHH
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


import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import de.fhhannover.inform.ifmap.util.ResourceHelper;

public class ToolbarPanel extends JToolBar {
	private static final long serialVersionUID = 2814752100004120354L;
	
	public JButton buttonConnections;
	public JButton buttonPublisher;
	public JToggleButton buttonAnimation;
	
	private JLabel labelIron;
	private JLabel labelEsukom;
	private JLabel labelTrust;		
	
	public ToolbarPanel() {
		
		buttonConnections = new JButton("");		
		add(buttonConnections);
		buttonConnections.setToolTipText("Manage IF-MAP connections");
		buttonConnections.setFocusable(false);
		buttonConnections.setIcon(ResourceHelper.getImage("computer_add.png"));
		
		buttonPublisher = new JButton("");
		add(buttonPublisher);
		buttonPublisher.setToolTipText("Show known publisher and change their colors");
		buttonPublisher.setFocusable(false);
		buttonPublisher.setIcon(ResourceHelper.getImage("color_management.png"));
		
		buttonAnimation = new JToggleButton("");
		add(buttonAnimation);
		buttonAnimation.setToolTipText("Start / stop the animation");
		buttonAnimation.setFocusable(false);
		buttonAnimation.setIcon(ResourceHelper.getImage("stop.png"));
		
		add(Box.createHorizontalGlue());
		
		labelTrust = new JLabel("");
		add(labelTrust);
		labelTrust.setHorizontalAlignment(SwingConstants.RIGHT);
		labelTrust.setIcon(ResourceHelper.getImage("logo_tnc.png"));
		labelTrust.setToolTipText("Visit the Trust@FHH Website!");
		labelTrust.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent evt) {
				labelTrust.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			public void mouseEntered(MouseEvent evt) {
				labelTrust.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			public void mouseClicked(MouseEvent evt) {
				de.fhhannover.inform.ifmap.util.AppLauncher.openWebpageInBrowser("http://trust.inform.fh-hannover.de");
			}
		});
		
		labelIron = new JLabel("");
		add(labelIron);
		labelIron.setIcon(ResourceHelper.getImage("logo_iron.png"));
		labelIron.setHorizontalAlignment(SwingConstants.RIGHT);
		labelIron.setToolTipText("More interesting IFMAP-stuff");
		labelIron.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent evt) {
				labelIron.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			public void mouseEntered(MouseEvent evt) {
				labelIron.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			public void mouseClicked(MouseEvent evt) {
				de.fhhannover.inform.ifmap.util.AppLauncher.openWebpageInBrowser("http://trust.inform.fh-hannover.de/joomla/index.php/projects/iron");
			}
		});
		
		labelEsukom = new JLabel("");
		add(labelEsukom);
		labelEsukom.setHorizontalAlignment(SwingConstants.RIGHT);
		labelEsukom.setIcon(ResourceHelper.getImage("esukom_32x32.png"));
		labelEsukom.setToolTipText("ESUKOM Project-Homepage");
		labelEsukom.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent evt) {
				labelEsukom.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			public void mouseEntered(MouseEvent evt) {
				labelEsukom.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			public void mouseClicked(MouseEvent evt) {
				de.fhhannover.inform.ifmap.util.AppLauncher.openWebpageInBrowser("http://www.esukom.de/");
			}
		});
	}
}
