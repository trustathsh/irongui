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



package de.hshannover.f4.trust.irongui.view;




import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import de.hshannover.f4.trust.ifmapj.IfmapJ;
import de.hshannover.f4.trust.ifmapj.exception.IfmapErrorResult;
import de.hshannover.f4.trust.ifmapj.exception.IfmapException;
import de.hshannover.f4.trust.ifmapj.identifier.Identifier;
import de.hshannover.f4.trust.ifmapj.identifier.IdentifierFactory;
import de.hshannover.f4.trust.ifmapj.identifier.IdentityType;
import de.hshannover.f4.trust.ifmapj.identifier.IpAddress;
import de.hshannover.f4.trust.irongui.Client;
import de.hshannover.f4.trust.irongui.communication.Connection;
import de.hshannover.f4.trust.irongui.communication.ConnectionFactory;
import de.hshannover.f4.trust.irongui.communication.ConnectionParameter;
import de.hshannover.f4.trust.irongui.control.IfmapFacade;
import de.hshannover.f4.trust.irongui.datastructure.IdentifierData;
import de.hshannover.f4.trust.irongui.datastructure.IfmapDataType;
import de.hshannover.f4.trust.irongui.datastructure.SubscriptionRepository;
import de.hshannover.f4.trust.irongui.event.IdentifierChangedReceiver;
import de.hshannover.f4.trust.irongui.exception.PropertiesNotFoundException;
import de.hshannover.f4.trust.irongui.util.ResourceHelper;
import de.hshannover.f4.trust.irongui.view.component.CustomTabComponent;
import de.hshannover.f4.trust.irongui.view.component.GraphPanel;
import de.hshannover.f4.trust.irongui.view.dialog.ConnectionDialog;
import de.hshannover.f4.trust.irongui.view.dialog.PublisherDialog;
import de.hshannover.f4.trust.irongui.view.dialog.SubscribeDialog;
import de.hshannover.f4.trust.irongui.view.navigation.TreePanel;
import de.hshannover.f4.trust.irongui.view.subscription.QuickSubscribeAccessRequest;
import de.hshannover.f4.trust.irongui.view.subscription.QuickSubscribeDevice;
import de.hshannover.f4.trust.irongui.view.subscription.QuickSubscribeIdentity;
import de.hshannover.f4.trust.irongui.view.subscription.QuickSubscribeIp;
import de.hshannover.f4.trust.irongui.view.subscription.QuickSubscribeMac;
import de.hshannover.f4.trust.irongui.view.subscription.QuickSubscribePanel;

public final class ViewController implements IdentifierChangedReceiver {

	private final MainFrame mMainFrame;
	private SubscribeDialog mSubDialog;
	private PublisherDialog mPubDialog;
	private ConnectionDialog mConDialog;
	private final IfmapFacade mIfmapFacade;
	private HashMap<Connection, GraphPanel> mConnection2GraphPanel;
	private HashMap<GraphPanel, Connection> mGraphPanel2Connection;
	private HashMap<Connection, TreePanel> mConnection2TreePanel;
	private Connection mSelectedConnection;
	private JPopupMenu mQuickPopup;
	private IdentifierFactory mIdentifierFactory;

	private JMenu menu1;
	private JMenu menu2;
	private JMenu menu3;
	private JMenu menu4;
	private JMenu menu5;

	private final int INIT_WIDTH = 1024;
	private final int INIT_HEIGHT = 768;

	public ViewController(IfmapFacade ifacade) {
		mIfmapFacade = ifacade;
		mIfmapFacade.addUpdatesListener(this);
		mMainFrame = new MainFrame(this);
		mConDialog = new ConnectionDialog(null);
		mSubDialog = new SubscribeDialog();
		mIdentifierFactory = IfmapJ.createIdentifierFactory();
		mConnection2GraphPanel = new HashMap<Connection, GraphPanel>();
		mGraphPanel2Connection = new HashMap<GraphPanel, Connection>();
		mConnection2TreePanel = new HashMap<Connection, TreePanel>();
		init();
	}

	private void init() {
		// Main Frame
		mMainFrame.setSize(INIT_WIDTH, INIT_HEIGHT);
		mMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mMainFrame.setTitle("irongui " + Client.VERSION);
		mMainFrame.setIconImage(new ImageIcon(getClass().getClassLoader()
				.getResource("esukom.png")).getImage());
		// Fill datastructures
		final Connection[] cons = mIfmapFacade.getConnections();
		if (cons != null && cons.length > 0) {
			DefaultListModel lm = (DefaultListModel) mMainFrame.navigationPanel.panelList
					.getModel();
			for (int i = 0; i < cons.length; i++) {
				final Connection con = cons[i];
				// nav. panel
				lm.add(i, con);
				// menu
				JMenuItem mi = new JMenuItem(con.getName());
				mi.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						selectConnection(con);
						toggleConnection();
					}
				});
				mMainFrame.menuConnectTo.add(mi);
				if (con.getConnectionParameters().isAutoConnect()) {
					selectConnection(con);
					toggleConnection();
				}
			}
		}

		mPubDialog = new PublisherDialog(mIfmapFacade.getPublisherAndColor(),
				mIfmapFacade.getDefaultIdentifierColor(),
				mIfmapFacade.getDefaultMetadataColor());

		// Menu connections
		mMainFrame.menuConnectTo.addMenuListener(new MenuListener() {
			@Override
			public void menuCanceled(MenuEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void menuDeselected(MenuEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void menuSelected(MenuEvent arg0) {
				mMainFrame.menuConnectTo.removeAll();
				final Connection[] cons = mIfmapFacade.getConnections();
				for (int i = 0; i < cons.length; i++) {
					final Connection con = cons[i];
					JMenuItem mi = new JMenuItem(con.getName());
					mi.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							selectConnection(con);
							toggleConnection();
						}
					});
					if (con.isConnected()) {
						mi.setEnabled(false);
						mi.setIcon(ResourceHelper
								.getImage("bullet_green.png"));
					} else {
						mi.setIcon(ResourceHelper
								.getImage("bullet_red.png"));
					}
					mMainFrame.menuConnectTo.add(mi);
				}
			}
		});

		// Conn. Dialog
		mConDialog.buttonClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveConnections();
				mConDialog.setVisible(false);
			}
		});

		// Conn. Test
		mConDialog.buttonTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mConDialog.testPanel.setText(null);
				mConDialog.testPanel.setLineWrap(true);
				mConDialog.buttonTest.setEnabled(false);
				saveConnections();
				ConnectionParameter param = (ConnectionParameter) mConDialog.mConnectionList
						.getSelectedValue();
				try {
					Connection con = ConnectionFactory.createConnection(param);
					mConDialog.testPanel.append("Trying newSession...");
					mIfmapFacade.startSession(con);
					mConDialog.testPanel.append("ok!\r\n");
					mConDialog.testPanel.append("PID: " + con.getPublisherID()
							+ "\r\nSID: " + con.getSessionID() + "\r\n");
					mConDialog.testPanel.append("Trying endSession...");
					mIfmapFacade.stopSession(con);
					mConDialog.testPanel.append("ok!");
				} catch (Exception e) {
					mConDialog.testPanel.append("\r\n\r\nException: "
							+ e.getLocalizedMessage());
					mConDialog.buttonTest.setEnabled(true);
					e.printStackTrace();
				}

				mConDialog.buttonTest.setEnabled(true);
			}
		});
		// Subs. Dialog
		mSubDialog.buttonSubscribe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				if (mSelectedConnection == null) {
					return;
				}
				mSubDialog.update();
				mSubDialog.setVisible(false);
				IdentifierData data = new IdentifierData(mSubDialog
						.getIdentifier());
				subscribeToIdentifier(data, mSubDialog.getDepth(),
						mSubDialog.getSize(), mSubDialog.getFilter(),
						mSubDialog.getLinks(), mSubDialog.getTerminals());
			}
		});

		// Publisher Dialog

		mPubDialog.okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				HashMap<String, Color> publisher = mPubDialog.getPublisher();
				Color default_id = mPubDialog.getDefaultIdentifierColor();
				Color default_me = mPubDialog.getDefaultMetadataColor();
				Collection<GraphPanel> graphs = mConnection2GraphPanel.values();
				for (GraphPanel g : graphs) {
					g.setNewMetadataColorAction(publisher, default_id,
							default_me);
				}
				mIfmapFacade.addPublisher(publisher, default_id, default_me);
				mPubDialog.setVisible(false);
			}
		});

		// Quick subscribe

		mQuickPopup = new JPopupMenu();
		mQuickPopup.addPopupMenuListener(new PopupMenuListener() {

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				mMainFrame.navigationPanel.buttonSubscribe.setSelected(false);
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				mMainFrame.navigationPanel.buttonSubscribe.setSelected(false);
			}
		});

		menu1 = new JMenu("ip-address");
		menu2 = new JMenu("mac-address");
		menu3 = new JMenu("access-request");
		menu4 = new JMenu("identity");
		menu5 = new JMenu("device");

		menu1.add(new QuickSubscribePanel(new QuickSubscribeIp(), this));
		menu2.add(new QuickSubscribePanel(new QuickSubscribeMac(), this));
		menu3.add(new QuickSubscribePanel(new QuickSubscribeAccessRequest(),
				this));
		menu4.add(new QuickSubscribePanel(new QuickSubscribeIdentity(), this));
		menu5.add(new QuickSubscribePanel(new QuickSubscribeDevice(), this));

		// Object preventHide =
		// qIdent.comboBoxKey.getClientProperty("doNotCancelPopup");
		// mQuickPopup.putClientProperty("doNotCancelPopup",preventHide);

		mQuickPopup.add(menu1);
		mQuickPopup.add(menu2);
		mQuickPopup.add(menu3);
		mQuickPopup.add(menu4);
		mQuickPopup.add(menu5);
	}

	private void subscribeToIdentifier(IdentifierData data, int depth,
			int size, String result, String links, String[] terms) {
		if (data != null && mSelectedConnection != null) {
			// if (!SubscriptionRepository.getInstance().isAlreadySubscribed(
			// mSelectedConnection, data)) {

			// erase subscribtions if dump is not enabled
			String erased = null;
			if (!mSelectedConnection.getConnectionParameters()
					.isDump()) {
				HashMap<String, IdentifierData> subscriptions = SubscriptionRepository
						.getInstance().getSubscriptions(mSelectedConnection);
				if (subscriptions != null && !subscriptions.isEmpty()) {
					if (subscriptions.size() > 1) {
						erased = "Subscriptions removed ("
								+ subscriptions.size() + ") / ";
					} else {
						erased = "Subscription removed ("
								+ subscriptions.keySet().toArray()[0] + ") / ";
					}
					mSelectedConnection.subscribeDelete(subscriptions.keySet());
					SubscriptionRepository.getInstance().removeSubscriptions(
							mSelectedConnection, subscriptions.keySet());
					GraphPanel graph = mConnection2GraphPanel
							.get(mSelectedConnection);
					graph.clear();
					graph.reset();
					graph.clearTable();
					mConnection2TreePanel.get(mSelectedConnection)
							.removeAllNodes();
				}
			}
			String uuid = mIfmapFacade.subscribe(mSelectedConnection,
					data.getRequestObject(), depth, size, result, links, terms);
			if (uuid != null) {
				data.setSubscriptionName(uuid);
				SubscriptionRepository.getInstance().addSubscription(
						mSelectedConnection, data);
				String prefix = (erased == null) ? "" : erased;
				mMainFrame.status.setText(prefix + "Subscription added ("
						+ uuid + ")");
				selectConnection(mSelectedConnection);
			} else {
				mMainFrame.status.setText("Subscription failed!");
			}
			// mMainFrame.toolbarPanel.buttonConnect.setSelected(mSelectedConnection.isConnected());
		}
		// }
	}

	public void subscribe() {
		IdentifierData data = null;
		Identifier ident = null;
		QuickSubscribePanel pan = null;
		if (mSelectedConnection != null) {
			if (menu1.isSelected()) {
				pan = (QuickSubscribePanel) menu1.getMenuComponent(0);
				QuickSubscribeIp ip = (QuickSubscribeIp) pan
						.getQuickComponent();
				String ip_value = ip.textFieldIp.getText().trim();
				String ip_admin = ip.textFieldDomain.getText().trim();
				if (!ip_value.equals("")) {
					IpAddress ipadrr;
					if (ip.ip4.isSelected()) {
						ipadrr = mIdentifierFactory.createIp4(ip_value);
						
					} else {
						ipadrr = mIdentifierFactory.createIp6(ip_value);
					}
					if (!ip_admin.equals("")) {
						ipadrr.setAdministrativeDomain(ip_admin);
					}
					ident = ipadrr;
				}
			} else if (menu2.isSelected()) {
				pan = (QuickSubscribePanel) menu2.getMenuComponent(0);
				QuickSubscribeMac mac = (QuickSubscribeMac) pan
						.getQuickComponent();
				String mac_val = mac.textFieldValue.getText().trim();
				String mac_admin = mac.textFieldDomain.getText().trim();
				if (!mac_val.equals("")) {
					ident = mIdentifierFactory.createMac(mac_val, mac_admin);
				}
			} else if (menu3.isSelected()) {
				pan = (QuickSubscribePanel) menu3.getMenuComponent(0);
				QuickSubscribeAccessRequest ar = (QuickSubscribeAccessRequest) pan
						.getQuickComponent();
				String name = ar.textFieldName.getText().trim();
				String admin = ar.textFieldAdmin.getText().trim();
				if (!name.equals("")) {
					ident = mIdentifierFactory.createAr(name, admin);
				}
			} else if (menu4.isSelected()) {
				pan = (QuickSubscribePanel) menu4.getMenuComponent(0);
				QuickSubscribeIdentity id = (QuickSubscribeIdentity) pan
						.getQuickComponent();
				String name = id.textFieldName.getText().trim();
				String other = id.textFieldOther.getText().trim();
				String admin = id.textFieldDomain.getText().trim();

				if (!name.equals("")) {
					String type = id.type.getSelection().getActionCommand();
					if (type.equals(IdentityType.other.toString())) {
						if (!other.equals("")) {
							ident = mIdentifierFactory.createIdentity(name,
									admin, other);
						}
					} else {
						ident = mIdentifierFactory.createIdentity(type, name,
								admin);
					}
				}

			} else if (menu5.isSelected()) {
				pan = (QuickSubscribePanel) menu5.getMenuComponent(0);
				QuickSubscribeDevice dev = (QuickSubscribeDevice) pan
						.getQuickComponent();
				String dev_val = dev.textFieldName.getText().trim();
				if (!dev_val.equals("")) {
					ident = mIdentifierFactory.createDev(dev_val);
				}
			}
		}
		mQuickPopup.setVisible(false);
		if (ident != null) {
			data = new IdentifierData(ident);
			mMainFrame.status.setText("Trying to subscribe...");
			subscribeToIdentifier(data, pan.mMetaPanel.getMaxDepth(),
					pan.mMetaPanel.getMaxSize(),
					pan.mMetaPanel.getResultFilter(),
					pan.mMetaPanel.getMatchLinks(),
					pan.mMetaPanel.getTerminals());
		} else {
			mMainFrame.status.setText("Subscribe canceled.");
		}
	}

	public void showQuickSubscribe(Component c) {
		mQuickPopup.show(c, 0, c.getHeight());
	}

	public void showConnectionDialog() {
		Connection[] cons = mIfmapFacade.getConnections();
		mConDialog.addConnections(cons);
		mConDialog.setLocationRelativeTo(null);
		mConDialog
				.setSelectedConnection((Connection) mMainFrame.navigationPanel.panelList
						.getSelectedValue());
		mConDialog.setVisible(true);
	}

	public void tabChanged() {
		Connection con = mGraphPanel2Connection.get(mMainFrame.tabbedPane
				.getSelectedComponent());
		mSelectedConnection = con;
		if (con != null) {
			if (mMainFrame.navigationPanel.buttonTree.isSelected()) {
				mMainFrame.navigationPanel
						.removeView(mMainFrame.navigationPanel.panelTree);
				TreePanel tree = mConnection2TreePanel.get(con);
				mMainFrame.navigationPanel.addView(tree);
				mMainFrame.navigationPanel.panelTree = mConnection2TreePanel
						.get(con);
				mMainFrame.navigationPanel.panelTree.setVisible(true);
				mMainFrame.navigationPanel.updateUI();
			}
			DefaultListModel model = (DefaultListModel) mMainFrame.navigationPanel.panelList
					.getModel();
			for (int i = 0; i < model.getSize(); i++) {
				Connection c = (Connection) model.get(i);
				if (c.equals(con)) {
					mMainFrame.navigationPanel.panelList.setSelectedIndex(i);
				}
			}

		}
		boolean selected = (mSelectedConnection != null && mSelectedConnection
				.isConnected()) ? true : false;
		mMainFrame.navigationPanel.buttonPlay.setSelected(selected);
		if(selected) {
			mMainFrame.toolbarPanel.buttonAnimation.setSelected(!mConnection2GraphPanel.get(mSelectedConnection).isAnimationEnabled());
		}
		else {
			mMainFrame.toolbarPanel.buttonAnimation.setSelected(false);
		}
	}

	public void toggleConnection() {
		GraphPanel graph = null;
		try {
			if (mSelectedConnection != null) {
				graph = mConnection2GraphPanel.get(mSelectedConnection);
				if (!mSelectedConnection.isConnected()) {
					if (mSelectedConnection.getConnectionParameters()
							.isDump()) {
						enableDump(true);
					} else {
						mIfmapFacade.startSession(mSelectedConnection);
						mMainFrame.navigationPanel.panelList.updateUI();
						mMainFrame.status.setText("Session "
								+ mSelectedConnection.getSessionID()
								+ " established on "
								+ mSelectedConnection.getEndpoint()
								+ " as publisher "
								+ mSelectedConnection.getPublisherID());
					}
					// GraphPanel graph = mConnection2GraphPanel
					// .get(mSelectedConnection);
					// graph.pan(graph.getWidth() / 2, graph.getHeight() / 2);
				} else {
					HashMap<String, IdentifierData> subs = SubscriptionRepository
							.getInstance()
							.getSubscriptions(mSelectedConnection);
					if (subs != null) {
						final String[] keys = subs.keySet().toArray(
								new String[0]);
						SubscriptionRepository.getInstance()
								.removeSubscriptions(mSelectedConnection, keys);
					}
					if (mSelectedConnection.getConnectionParameters()
							.isDump()) {
						enableDump(false);
					}
					mIfmapFacade.stopSession(mSelectedConnection);
					graph.clear();
					graph.reset();
					graph.clearTable();
					graph.enableAnimation(true);
					mMainFrame.metadataPanel.label.setText("");
					mConnection2TreePanel.get(mSelectedConnection)
							.removeAllNodes();
					mMainFrame.navigationPanel.revalidate();
					mMainFrame.status.setText("Session "
							+ mSelectedConnection.getEndpoint() + "terminated");
					mMainFrame.navigationPanel.panelList.updateUI();
					graph.updateUI();
					graph.repaint();					
				}
				mMainFrame.navigationPanel.buttonPlay
						.setSelected(mSelectedConnection.isConnected());
				mMainFrame.toolbarPanel.buttonAnimation.setSelected(!graph.isAnimationEnabled());
			}

		} catch (Exception e) {
			mSelectedConnection.brokenDisconnect();
			mMainFrame.status.setText(mSelectedConnection.getName() + " - "
					+ e.getLocalizedMessage());
			if (mMainFrame.navigationPanel.buttonPlay.isSelected()) {
				mMainFrame.navigationPanel.buttonPlay.setSelected(false);
			}
			if (graph != null) {
				graph.updateUI();
				graph.repaint();
			}
			mMainFrame.navigationPanel.panelList.updateUI();
		}
	}

	public void enableDump(boolean enable) throws FileNotFoundException,
			PropertiesNotFoundException, IOException, IfmapErrorResult,
			IfmapException, InterruptedException {
		if (mSelectedConnection != null) {
			if (enable) {
				mIfmapFacade.startSession(mSelectedConnection);
				mMainFrame.navigationPanel.panelList.updateUI();
				mSelectedConnection.enableDumping(true);
				mMainFrame.status.setText("Start dumping on "
						+ mSelectedConnection.getEndpoint());
				// GraphPanel graph = mConnection2GraphPanel
				// .get(mSelectedConnection);
				// System.err.println("width: " +graph.getWidth());
				// System.err.println("height: " +graph.getHeight());
				// graph.pan(graph.getWidth() / 2, graph.getHeight() / 2);
			} else {
				HashMap<String, IdentifierData> subs = SubscriptionRepository
						.getInstance().getSubscriptions(mSelectedConnection);
				if (subs != null) {
					final String[] keys = subs.keySet().toArray(new String[0]);
					mIfmapFacade.unsubscribe(mSelectedConnection, keys);
					SubscriptionRepository.getInstance().removeSubscriptions(
							mSelectedConnection, keys);
				}
				mIfmapFacade.stopSession(mSelectedConnection);
				GraphPanel graph = mConnection2GraphPanel
						.get(mSelectedConnection);
				graph.clear();
				graph.reset();
				graph.clearTable();
				mMainFrame.status.setText("Session "
						+ mSelectedConnection.getEndpoint() + "terminated");
				mMainFrame.navigationPanel.panelList.updateUI();
			}
		}
	}

	public void selectConnectionWithoutCreatingTab(Connection con) {
		mMainFrame.navigationPanel.buttonPlay.setSelected(con.isConnected());
		mSelectedConnection = con;
	}
	
	public void selectConnection(Connection con) {
		GraphPanel graph = mConnection2GraphPanel.get(con);
		if (graph == null) {
			graph = new GraphPanel();
			graph.addNodeSelectedListener(mMainFrame.metadataPanel);
			graph.setTable(mMainFrame.metadataPanel.table, null);
			graph.setNewMetadataColorAction(
					mIfmapFacade.getPublisherAndColor(),
					mIfmapFacade.getDefaultIdentifierColor(),
					mIfmapFacade.getDefaultMetadataColor());
			TreePanel tree = new TreePanel(graph);
			mConnection2GraphPanel.put(con, graph);
			mGraphPanel2Connection.put(graph, con);
			mConnection2TreePanel.put(con, tree);
			mMainFrame.tabbedPane.addTab(con.getName(), new ImageIcon(
					getClass().getClassLoader().getResource("tabConnect.png")),
					graph, con.getName());
			mMainFrame.tabbedPane.setTabComponentAt(
					mMainFrame.tabbedPane.getTabCount() - 1,
					new CustomTabComponent(con.getName(), mMainFrame.tabbedPane));
		}

		boolean b = false;
		for (int i = 0; i < mMainFrame.tabbedPane.getTabCount(); i++) {
			Component c = mMainFrame.tabbedPane.getComponentAt(i);
			if (c == graph)
				b = true;
		}

		if (!b) {			
			mMainFrame.tabbedPane.addTab(con.getName(), new ImageIcon(
					getClass().getClassLoader().getResource("tabConnect.png")),
					graph, con.getName());
			mMainFrame.tabbedPane.setTabComponentAt(
					mMainFrame.tabbedPane.getTabCount() - 1,
					new CustomTabComponent(con.getName(), mMainFrame.tabbedPane));
		}

		mMainFrame.tabbedPane.setSelectedComponent(graph);
		
		mMainFrame.navigationPanel.buttonPlay.setSelected(con.isConnected());
		mMainFrame.toolbarPanel.buttonAnimation.setSelected(!graph.isAnimationEnabled());
		graph.revalidate();
		mSelectedConnection = con;
	}

	public void treeButtonClicked() {
		mMainFrame.navigationPanel.removeView(mMainFrame.navigationPanel.panelList);
		if (mSelectedConnection != null) {
			TreePanel tree = mConnection2TreePanel.get(mSelectedConnection);
			if(tree != null) {
				mMainFrame.navigationPanel.addView(tree);
				mMainFrame.navigationPanel.panelTree = tree;
				mMainFrame.navigationPanel.panelTree.setVisible(true);
			}
		}
		mMainFrame.navigationPanel.updateUI();
		
	}

	public void conButtonClicked() {
		mMainFrame.navigationPanel.removeView(mMainFrame.navigationPanel.panelTree);
		mMainFrame.navigationPanel.addView(mMainFrame.navigationPanel.panelList);
		mMainFrame.navigationPanel.updateUI();
		mMainFrame.navigationPanel.panelList.setVisible(true);
	}

	public void showPublisherDialog() {
		mPubDialog.pack();
		mPubDialog.setLocationRelativeTo(null);
		mPubDialog.setVisible(true);
	}

	public void showSubsriptionDialog() {
		if(mSelectedConnection != null) {
			mSubDialog.center();
			mSubDialog.setVisible(true);
		}
	}

	public void showMainFrame() {
		mMainFrame.setLocationRelativeTo(null);
		mMainFrame.setVisible(true);
	}

	public void toggleAnimation() {
		if(mSelectedConnection != null) {
			GraphPanel p = mConnection2GraphPanel.get(mSelectedConnection);
			p.toggleAnimation();
		}
	}
	
	@Override
	public void processNewPollResult(Connection con,
			ArrayList<IfmapDataType> newData,
			ArrayList<IfmapDataType> updateData,
			ArrayList<IfmapDataType> deleteData) {
		synchronized (this) {
			if (con != null) {
				GraphPanel graph = mConnection2GraphPanel.get(con);
				graph.processNewPollResult(newData, updateData, deleteData);
				TreePanel tree = mConnection2TreePanel.get(con);
				tree.processNewPollResult(newData, updateData, deleteData);
				mPubDialog
						.processNewPollResult(newData, updateData, deleteData);
				graph.centerGraph();
			}
		}
	}

	private void saveConnections() {
		DefaultListModel lm = (DefaultListModel) mMainFrame.navigationPanel.panelList
				.getModel();
		lm.clear();
		mConDialog.assignParamsToObject();
		Connection[] cons = mConDialog.getConnections();
		mIfmapFacade.updateConnections(cons);
		if (cons != null) {
			for (int i = 0; i < cons.length; i++) {
				lm.add(i, cons[i]);
			}
		}
	}
}
