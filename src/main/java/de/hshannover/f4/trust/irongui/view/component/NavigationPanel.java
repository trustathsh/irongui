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
 * This file is part of irongui, version 0.4.3,
 * implemented by the Trust@HsH research group at the Hochschule Hannover.
 * %%
 * Copyright (C) 2010 - 2014 Trust@HsH
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



package de.hshannover.f4.trust.irongui.view.component;




import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;

import de.hshannover.f4.trust.irongui.util.ResourceHelper;
import de.hshannover.f4.trust.irongui.view.navigation.TreePanel;

public class NavigationPanel extends JPanel {
	private static final long serialVersionUID = 4248002471880371362L;
	public JToggleButton buttonConnections;
	public JToggleButton buttonTree;
	public JList panelList;
	public TreePanel panelTree;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JScrollPane scrollPane = new JScrollPane();
	private JPanel panelBottom;
	private JToolBar actionPanel;
	public  JToggleButton buttonPlay;
	public JToggleButton buttonSubscribe;
	public NavigationPanel() {
		setPreferredSize(new Dimension(151, 433));
		setLayout(new BorderLayout(0, 0));		
		
		JToolBar panelTop = new JToolBar();
		panelTop.setFloatable(false);
		add(panelTop, BorderLayout.NORTH);				
		
		buttonConnections = new JToggleButton();
		buttonConnections.setFocusable(false);
		buttonConnections.setSelected(true);
		buttonGroup.add(buttonConnections);
		//buttonConnections.setPreferredSize(new Dimension(32, 32));
		buttonConnections.setIcon(ResourceHelper.getImage("server_client.png"));
		panelTop.add(buttonConnections);
		
		buttonTree = new JToggleButton();		
		buttonTree.setFocusable(false);
		buttonGroup.add(buttonTree);
		//buttonTree.setPreferredSize(new Dimension(32, 32));
		buttonTree.setIcon(ResourceHelper.getImage("node-tree_24x24.png"));
		panelTop.add(buttonTree);
		
		panelBottom = new JPanel();
		panelBottom.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panelBottom, BorderLayout.CENTER);
		panelBottom.setLayout(new BorderLayout(0, 0));
		
		actionPanel = new JToolBar();
		actionPanel.setFloatable(false);
		panelBottom.add(actionPanel, BorderLayout.NORTH);
		
		buttonPlay = new JToggleButton();		
		buttonPlay.setToolTipText("Start session with current selection");
		buttonPlay.setFocusable(false);
		buttonPlay.setIcon(ResourceHelper.getImage("control_play_blue.png"));
		buttonPlay.setSelectedIcon(ResourceHelper.getImage("control_stop_blue.png"));
		actionPanel.add(buttonPlay);
		
		buttonSubscribe = new JToggleButton();
		buttonSubscribe.setToolTipText("Subscribe to metadata...");
		buttonSubscribe.setFocusable(false);
		buttonSubscribe.setIcon(ResourceHelper.getImage("feed_add_16x16.png"));
		actionPanel.add(buttonSubscribe);
		
		panelList = new JList();
		panelList.setModel(new DefaultListModel());
		panelList.setCellRenderer(new ConnectionListRenderer());
		panelList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelBottom.add(scrollPane);
		scrollPane.setViewportView(panelList);
		
		panelTree = new TreePanel();
	}
	
	public void removeView(Component c) {
		scrollPane.getViewport().remove(c);
	}
	
	public void addView(Component c) {
		scrollPane.setViewportView(c);
	}
}
