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

package de.hshannover.f4.trust.irongui.view;

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

import javax.swing.Box;
import javax.swing.DefaultListModel;
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

import de.hshannover.f4.trust.irongui.communication.Connection;
import de.hshannover.f4.trust.irongui.util.ResourceHelper;
import de.hshannover.f4.trust.irongui.view.component.MetadataPanel;
import de.hshannover.f4.trust.irongui.view.component.NavigationPanel;
import de.hshannover.f4.trust.irongui.view.component.ToolbarPanel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	ViewController mViewController;
	MetadataPanel mMetadataPanel;
	private JPanel mStatusPanel;
	NavigationPanel mNavigationPanel;
	ToolbarPanel mToolbarPanel;
	JTabbedPane mTabbedPane;
	JLabel mStatus;
	private JMenuBar mMenuBar;
	private JMenu mMnConnection;
	private JMenu mMnEdit;
	private JMenuItem mMntmManageConnections;
	private JSeparator mSeparator;
	private JSeparator mSeparator1;
	public JMenu mMenuConnectTo;
	private JMenuItem mMntmQuitProgram;
	private JMenuItem mMntmPublisherColor;
	private JMenu mMnAbout;
	private JMenuItem mMntmIrongui;

	public MainFrame(ViewController vc) {
		mMenuBar = new JMenuBar();
		setJMenuBar(mMenuBar);

		mMnConnection = new JMenu("Connection");
		mMenuBar.add(mMnConnection);

		mMntmManageConnections = new JMenuItem("Manage connections...");
		mMntmManageConnections.setMnemonic(KeyEvent.VK_M);
		mMntmManageConnections.setIcon(ResourceHelper
				.getImage("computer_add_16x16.png"));
		mMnConnection.add(mMntmManageConnections);
		mMntmManageConnections.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mViewController.showConnectionDialog();
			}
		});

		mSeparator = new JSeparator();
		mMnConnection.add(mSeparator);

		mMenuConnectTo = new JMenu("Connect to");
		mMenuConnectTo.setIcon(ResourceHelper.getImage("control_play_blue.png"));
		mMnConnection.add(mMenuConnectTo);

		mSeparator1 = new JSeparator();
		mMnConnection.add(mSeparator1);

		mMntmQuitProgram = new JMenuItem("Exit");
		mMntmQuitProgram.setIcon(ResourceHelper.getImage("cancel.png"));
		mMntmQuitProgram.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});
		mMnConnection.add(mMntmQuitProgram);

		mMnEdit = new JMenu("Edit");
		mMenuBar.add(mMnEdit);

		mMntmPublisherColor = new JMenuItem("Publisher color...");
		mMntmPublisherColor.setIcon(ResourceHelper
				.getImage("color_management_16x16.png"));
		mMntmPublisherColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mViewController.showPublisherDialog();
			}
		});
		mMnEdit.add(mMntmPublisherColor);

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
		mMenuBar.add(Box.createHorizontalGlue());

		mMnAbout = new JMenu("About");
		mMenuBar.add(mMnAbout);

		mMntmIrongui = new JMenuItem("Irongui ...");
		mMntmIrongui.setIcon(ResourceHelper.getImage("information.png"));
		mMntmIrongui.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane
						.showMessageDialog(
								MainFrame.this,
								"irongui 0.4.2\n"
										+ "Keep in mind that irongui is an experimental MAPC.\n"
										+ "You might encounter bugs, primarily when metadata\n"
										+ "is missing or metadata that was deleted is still\n"
										+ "rendered by irongui. Work around: restart irongui\n"
										+ "and start over again ;)\n", "About",
								JOptionPane.INFORMATION_MESSAGE,
								ResourceHelper.getAppIconImage());
			}
		});
		mMnAbout.add(mMntmIrongui);
		mViewController = vc;
		init();
	}

	private void init() {
		Container mainPanel = this.getContentPane();
		mainPanel.setLayout(new BorderLayout());

		mToolbarPanel = new ToolbarPanel();
		getContentPane().add(mToolbarPanel, BorderLayout.NORTH);

		JSplitPane splitPane1 = new JSplitPane();
		splitPane1.setOneTouchExpandable(true);
		splitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane1, BorderLayout.CENTER);

		JSplitPane splitPane2 = new JSplitPane();
		splitPane2.setOneTouchExpandable(true);
		splitPane1.setLeftComponent(splitPane2);

		mNavigationPanel = new NavigationPanel();
		splitPane2.setLeftComponent(mNavigationPanel);

		mTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		mTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		mTabbedPane.setFocusable(false);
		splitPane2.setRightComponent(mTabbedPane);

		mMetadataPanel = new MetadataPanel();

		splitPane1.setRightComponent(mMetadataPanel);
		splitPane1.setDividerLocation(450);

		mStatusPanel = new JPanel();
		mStatusPanel
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		FlowLayout flowLayout = (FlowLayout) mStatusPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(mStatusPanel, BorderLayout.SOUTH);
		mStatusPanel.setPreferredSize(new Dimension(getWidth(), 24));

		mStatus = new JLabel("irongui started successfully.");
		mStatusPanel.add(mStatus);

		/*********************************************************************************
		 * ActionListener
		 */

		// Connection Button
		mToolbarPanel.mButtonConnections.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mViewController.showConnectionDialog();
			}
		});

		// Quick subscribe
		mNavigationPanel.mButtonSubscribe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mViewController
						.showQuickSubscribe(mNavigationPanel.mButtonSubscribe);
			}
		});

		// Tab selected
		mTabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				mViewController.tabChanged();
			}
		});

		// A single connection in Navigator-Panel is selected
		mNavigationPanel.mPanelList.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					DefaultListModel model = (DefaultListModel) mNavigationPanel.mPanelList
							.getModel();
					Connection con = (Connection) model
							.get(mNavigationPanel.mPanelList.getSelectedIndex());
					mViewController.selectConnection(con);
				}
			}
		});

		mNavigationPanel.mPanelList
				.addListSelectionListener(new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (mNavigationPanel.mPanelList.getSelectedIndex() != -1) {
							DefaultListModel model = (DefaultListModel) mNavigationPanel.mPanelList
									.getModel();
							Connection con = (Connection) model
									.get(mNavigationPanel.mPanelList
											.getSelectedIndex());
							mViewController
									.selectConnectionWithoutCreatingTab(con);
						}
					}
				});
		// Connect
		mNavigationPanel.mButtonPlay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						final int index = mNavigationPanel.mPanelList
								.getSelectedIndex();
						if (index > -1) {
							DefaultListModel model = (DefaultListModel) mNavigationPanel.mPanelList
									.getModel();
							Connection con = (Connection) model.get(index);
							mViewController.selectConnection(con);
							mViewController.toggleConnection();
						} else {
							mNavigationPanel.mButtonPlay.setSelected(false);
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
		mNavigationPanel.mButtonTree.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if (state == ItemEvent.SELECTED) {
					mViewController.treeButtonClicked();
				}
			}
		});

		// Connection button
		mNavigationPanel.mButtonConnections.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if (state == ItemEvent.SELECTED) {
					mViewController.conButtonClicked();
				}
			}
		});

		// Publisher button

		mToolbarPanel.mButtonPublisher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mViewController.showPublisherDialog();
			}
		});

		// Stop animation

		mToolbarPanel.mButtonAnimation.addActionListener(new ActionListener() {

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
