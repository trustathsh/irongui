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
 * This file is part of irongui, version 0.4.8,
 * implemented by the Trust@HsH research group at the Hochschule Hannover.
 * %%
 * Copyright (C) 2010 - 2015 Trust@HsH
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
	public JToggleButton mButtonConnections;
	public JToggleButton mButtonTree;
	public JList mPanelList;
	public TreePanel mPanelTree;
	private final ButtonGroup mButtonGroup = new ButtonGroup();
	private JScrollPane mScrollPane = new JScrollPane();
	private JPanel mPanelBottom;
	private JToolBar mActionPanel;
	public JToggleButton mButtonPlay;
	public JToggleButton mButtonSubscribe;

	public NavigationPanel() {
		setPreferredSize(new Dimension(151, 433));
		setLayout(new BorderLayout(0, 0));

		JToolBar panelTop = new JToolBar();
		panelTop.setFloatable(false);
		add(panelTop, BorderLayout.NORTH);

		mButtonConnections = new JToggleButton();
		mButtonConnections.setFocusable(false);
		mButtonConnections.setSelected(true);
		mButtonGroup.add(mButtonConnections);
		// buttonConnections.setPreferredSize(new Dimension(32, 32));
		mButtonConnections.setIcon(ResourceHelper.getImage("server_client.png"));
		panelTop.add(mButtonConnections);

		mButtonTree = new JToggleButton();
		mButtonTree.setFocusable(false);
		mButtonGroup.add(mButtonTree);
		// buttonTree.setPreferredSize(new Dimension(32, 32));
		mButtonTree.setIcon(ResourceHelper.getImage("node-tree_24x24.png"));
		panelTop.add(mButtonTree);

		mPanelBottom = new JPanel();
		mPanelBottom.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(mPanelBottom, BorderLayout.CENTER);
		mPanelBottom.setLayout(new BorderLayout(0, 0));

		mActionPanel = new JToolBar();
		mActionPanel.setFloatable(false);
		mPanelBottom.add(mActionPanel, BorderLayout.NORTH);

		mButtonPlay = new JToggleButton();
		mButtonPlay.setToolTipText("Start session with current selection");
		mButtonPlay.setFocusable(false);
		mButtonPlay.setIcon(ResourceHelper.getImage("control_play_blue.png"));
		mButtonPlay.setSelectedIcon(ResourceHelper
				.getImage("control_stop_blue.png"));
		mActionPanel.add(mButtonPlay);

		mButtonSubscribe = new JToggleButton();
		mButtonSubscribe.setToolTipText("Subscribe to metadata...");
		mButtonSubscribe.setFocusable(false);
		mButtonSubscribe.setIcon(ResourceHelper.getImage("feed_add_16x16.png"));
		mActionPanel.add(mButtonSubscribe);

		mPanelList = new JList();
		mPanelList.setModel(new DefaultListModel());
		mPanelList.setCellRenderer(new ConnectionListRenderer());
		mPanelList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mPanelBottom.add(mScrollPane);
		mScrollPane.setViewportView(mPanelList);

		mPanelTree = new TreePanel();
	}

	public void removeView(Component c) {
		mScrollPane.getViewport().remove(c);
	}

	public void addView(Component c) {
		mScrollPane.setViewportView(c);
	}
}
