package de.fhhannover.inform.ifmap.view;

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
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.fhhannover.inform.ifmap.communication.Connection;
import de.fhhannover.inform.ifmap.datastructure.Db4oStorageImpl;
import de.fhhannover.inform.ifmap.datastructure.IfmapDataType;
import de.fhhannover.inform.ifmap.util.IfmapUtils;
import de.fhhannover.inform.ifmap.util.ResourceHelper;
import de.fhhannover.inform.ifmap.view.component.MetadataPanel;
import de.fhhannover.inform.ifmap.view.component.NavigationPanel;
import de.fhhannover.inform.ifmap.view.component.ToolbarPanel;
import de.fhhannover.inform.trust.Feature;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	ViewController mViewController;
	MetadataPanel metadataPanel;
	private JPanel statusPanel;
	NavigationPanel navigationPanel;
	ToolbarPanel toolbarPanel;
	JTabbedPane tabbedPane;
	JLabel status;
	private JMenuBar menuBar;
	private JMenu mnConnection;
	private JMenu mnEdit;
	private JMenu mnAction;
	private JMenuItem mntmManageConnections;
	private JSeparator separator;
	private JSeparator separator_1;
	public JMenu menuConnectTo;
	private JMenuItem mntmQuitProgram;
	private JMenuItem mntmPublisherColor;
	private JMenuItem mntmSubscribe;
	private JMenu mnAbout;
	private JMenuItem mntmIrongui;
	private JMenuItem mntmOpenDboFile;

	public MainFrame(ViewController vc) {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnConnection = new JMenu("Connection");
		menuBar.add(mnConnection);

		mntmOpenDboFile = new JMenuItem("Open db4o file...");
		mntmOpenDboFile.setVisible(false);
		mnConnection.add(mntmOpenDboFile);
		mntmOpenDboFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(MainFrame.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String path = fc.getSelectedFile().getAbsolutePath();
					ArrayList<IfmapDataType> features = IfmapUtils.dbo2Ifmap(path);
					mViewController.addDb4oGraph(features);
				}
			}
		});

		mntmManageConnections = new JMenuItem("Manage connections...");
		mntmManageConnections.setMnemonic(KeyEvent.VK_M);
		mntmManageConnections.setIcon(ResourceHelper
				.getImage("computer_add_16x16.png"));
		mnConnection.add(mntmManageConnections);
		mntmManageConnections.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mViewController.showConnectionDialog();
			}
		});

		separator = new JSeparator();
		mnConnection.add(separator);

		menuConnectTo = new JMenu("Connect to");
		menuConnectTo.setIcon(ResourceHelper.getImage("control_play_blue.png"));
		mnConnection.add(menuConnectTo);

		separator_1 = new JSeparator();
		mnConnection.add(separator_1);

		mntmQuitProgram = new JMenuItem("Exit");
		mntmQuitProgram.setIcon(ResourceHelper.getImage("cancel.png"));
		mntmQuitProgram.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});
		mnConnection.add(mntmQuitProgram);

		mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		mntmPublisherColor = new JMenuItem("Publisher color...");
		mntmPublisherColor.setIcon(ResourceHelper
				.getImage("color_management_16x16.png"));
		mntmPublisherColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mViewController.showPublisherDialog();
			}
		});
		mnEdit.add(mntmPublisherColor);

		/*
		 * mnAction = new JMenu("Action"); menuBar.add(mnAction);
		 * 
		 * mntmSubscribe = new JMenuItem("Subscribe...");
		 * mntmSubscribe.setIcon(ResourceHelper.getImage("rss_16x16.png"));
		 * mntmSubscribe.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) {
		 * mViewController.showSubsriptionDialog(); } });
		 * 
		 * mnAction.add(mntmSubscribe);
		 */
		menuBar.add(Box.createHorizontalGlue());

		mnAbout = new JMenu("About");
		menuBar.add(mnAbout);

		mntmIrongui = new JMenuItem("Irongui ...");
		mntmIrongui.setIcon(ResourceHelper.getImage("information.png"));
		mntmIrongui.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane
						.showMessageDialog(
								MainFrame.this,
								"irongui 0.4.0\n"
										+ "Keep in mind that irongui is an experimental MAPC.\n"
										+ "You might encounter bugs, primarily when metadata\n"
										+ "is missing or metadata that was deleted is still\n"
										+ "rendered by irongui. Work around: restart irongui\n"
										+ "and start over again ;)\n", "About",
								JOptionPane.INFORMATION_MESSAGE,
								ResourceHelper.getAppIconImage());
			}
		});
		mnAbout.add(mntmIrongui);
		mViewController = vc;
		init();
	}

	private void init() {
		Container mainPanel = this.getContentPane();
		mainPanel.setLayout(new BorderLayout());

		toolbarPanel = new ToolbarPanel();
		getContentPane().add(toolbarPanel, BorderLayout.NORTH);

		JSplitPane splitPane1 = new JSplitPane();
		splitPane1.setOneTouchExpandable(true);
		splitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane1, BorderLayout.CENTER);

		JSplitPane splitPane2 = new JSplitPane();
		splitPane2.setOneTouchExpandable(true);
		splitPane1.setLeftComponent(splitPane2);

		navigationPanel = new NavigationPanel();
		splitPane2.setLeftComponent(navigationPanel);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setFocusable(false);
		splitPane2.setRightComponent(tabbedPane);

		metadataPanel = new MetadataPanel();

		splitPane1.setRightComponent(metadataPanel);
		splitPane1.setDividerLocation(450);

		statusPanel = new JPanel();
		statusPanel
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		FlowLayout flowLayout = (FlowLayout) statusPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(statusPanel, BorderLayout.SOUTH);
		statusPanel.setPreferredSize(new Dimension(getWidth(), 24));

		status = new JLabel("irongui started successfully.");
		statusPanel.add(status);

		/*********************************************************************************
		 * ActionListener
		 */

		// Connection Button
		toolbarPanel.buttonConnections.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mViewController.showConnectionDialog();
			}
		});

		// Quick subscribe
		navigationPanel.buttonSubscribe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mViewController
						.showQuickSubscribe(navigationPanel.buttonSubscribe);
			}
		});

		// Tab selected
		tabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				mViewController.tabChanged();
			}
		});

		// A single connection in Navigator-Panel is selected
		navigationPanel.panelList.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					DefaultListModel model = (DefaultListModel) navigationPanel.panelList
							.getModel();
					Connection con = (Connection) model
							.get(navigationPanel.panelList.getSelectedIndex());
					mViewController.selectConnection(con);
				}
			}
		});

		navigationPanel.panelList
				.addListSelectionListener(new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (navigationPanel.panelList.getSelectedIndex() != -1) {
							DefaultListModel model = (DefaultListModel) navigationPanel.panelList
									.getModel();
							Connection con = (Connection) model
									.get(navigationPanel.panelList
											.getSelectedIndex());
							mViewController
									.selectConnectionWithoutCreatingTab(con);
						}
					}
				});
		// Connect
		navigationPanel.buttonPlay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						final int index = navigationPanel.panelList
								.getSelectedIndex();
						if (index > -1) {
							DefaultListModel model = (DefaultListModel) navigationPanel.panelList
									.getModel();
							Connection con = (Connection) model.get(index);
							mViewController.selectConnection(con);
							mViewController.toggleConnection();
						} else {
							navigationPanel.buttonPlay.setSelected(false);
							JOptionPane
									.showMessageDialog(MainFrame.this,
											"Please select a connection on the left side first.");
						}
					}
				});
			}
		});

		// Start dump button selected
		/*
		 * toolbarPanel.buttonStartDump.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) {
		 * mViewController.startDump(); } });
		 */

		// Subscribe...
		/*
		 * navigationPanel.buttonSubscribe.addActionListener(new
		 * ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) {
		 * mViewController.showSubsriptionDialog(); } });
		 */
		// Tree button
		navigationPanel.buttonTree.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if (state == ItemEvent.SELECTED) {
					mViewController.treeButtonClicked();
				}
			}
		});

		// Connection button
		navigationPanel.buttonConnections.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if (state == ItemEvent.SELECTED) {
					mViewController.conButtonClicked();
				}
			}
		});

		// Publisher button

		toolbarPanel.buttonPublisher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mViewController.showPublisherDialog();
			}
		});
		
		// Stop animation
		
		toolbarPanel.buttonAnimation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mViewController.toggleAnimation();
			}
		});
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		MainFrame f = new MainFrame(null);
		f.setSize(1024, 768);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
}
