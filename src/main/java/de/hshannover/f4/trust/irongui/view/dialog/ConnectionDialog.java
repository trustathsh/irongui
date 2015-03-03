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
 * This file is part of irongui, version 0.4.7,
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

package de.hshannover.f4.trust.irongui.view.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.hshannover.f4.trust.irongui.communication.Connection;
import de.hshannover.f4.trust.irongui.communication.ConnectionFactory;
import de.hshannover.f4.trust.irongui.communication.ConnectionParameter;
import de.hshannover.f4.trust.irongui.exception.ConnectionCreationException;
import de.hshannover.f4.trust.irongui.util.ResourceHelper;

public class ConnectionDialog extends JFrame {

	private static final long serialVersionUID = 6756137933419928418L;
	private JPanel mContentPane;
	private JTextField mTextFieldEndpoint;
	private JTextField mTextFieldKeystore;
	private JPasswordField mPasswordFieldKeystore;
	private JTextField mTextFieldTruststore;
	private JPasswordField mPasswordFieldTruststore;
	private JTextField mTextFieldName;
	private JTextField mTextFieldUsername;
	private JPasswordField mTextFieldPassword;
	private JLabel mLabelUsername = null;
	private JLabel mLabelPassword;
	private ConnectionParameter mPreviousParam = null;
	private JCheckBox mCheckBoxBasicAuth;
	public JTextArea mTestPanel;
	public JButton buttonClose;
	public DefaultListModel mListModel;
	public JCheckBox mCheckBoxDump;
	public JCheckBox mCheckBoxAutoConnect;
	public JList mConnectionList;
	private JPanel mPanelCenter;
	private JPanel mPanelParameter;
	private JPanel mPanelBlank;
	private int mDupCounter = 0;
	public JButton buttonTest;
	private JTextField mTextFieldMaxPoll;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectionDialog frame = new ConnectionDialog(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void assignParamsToObject() {
		ConnectionParameter param = (ConnectionParameter) mConnectionList
				.getSelectedValue();
		if (param != null) {
			param.setName(mTextFieldName.getText().trim());
			param.setEndpoint(mTextFieldEndpoint.getText().trim());
			param.setTruststore(mTextFieldTruststore.getText().trim());
			param.setTruststorePass(new String(mPasswordFieldTruststore
					.getPassword()));
			param.setKeystore(mTextFieldKeystore.getText().trim());
			param.setKeystorePass(new String(mPasswordFieldKeystore
					.getPassword()));
			param.setBasicAuthEnabled(mCheckBoxBasicAuth.isSelected());
			param.setBasicauthUser(mTextFieldUsername.getText().trim());
			param.setBasicauthPass(new String(mTextFieldPassword.getPassword()));
			param.setDump(mCheckBoxDump.isSelected());
			param.setAutoConnect(mCheckBoxAutoConnect.isSelected());
			try {
				param.setMaxPollSize(Integer.parseInt(mTextFieldMaxPoll
						.getText().trim()));
			} catch (NumberFormatException err) {
				param.setMaxPollSize(0);
			}
		}
	}

	/**
	 * Create the frame.
	 */
	public ConnectionDialog(Connection[] cons) {
		setResizable(false);
		setTitle("Manage IF-MAP connections");
		setIconImage(ResourceHelper.getAppIconImage().getImage());
		setBounds(100, 100, 671, 700);
		mContentPane = new JPanel();
		mContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		mContentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(mContentPane);

		JSplitPane splitPane = new JSplitPane();
		mContentPane.add(splitPane, BorderLayout.CENTER);

		mConnectionList = new JList();
		// mConnectionList.setCellRenderer(new ConnectionListRenderer());
		mConnectionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mConnectionList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				ConnectionParameter param = (ConnectionParameter) mConnectionList
						.getSelectedValue();
				if (param == null) {
					return;
				}
				if (mPreviousParam != null) {
					mPreviousParam.setName(mTextFieldName.getText().trim());
					mPreviousParam.setEndpoint(mTextFieldEndpoint.getText()
							.trim());
					mPreviousParam.setTruststore(mTextFieldTruststore.getText()
							.trim());
					mPreviousParam.setTruststorePass(new String(
							mPasswordFieldTruststore.getPassword()));
					mPreviousParam.setKeystore(mTextFieldKeystore.getText()
							.trim());
					mPreviousParam.setKeystorePass(new String(
							mPasswordFieldKeystore.getPassword()));
					mPreviousParam.setBasicAuthEnabled(mCheckBoxBasicAuth
							.isSelected());
					mPreviousParam.setBasicauthUser(mTextFieldUsername.getText()
							.trim());
					mPreviousParam.setBasicauthPass(new String(mTextFieldPassword
							.getPassword()));
					mPreviousParam.setDump(mCheckBoxDump.isSelected());
					mPreviousParam.setAutoConnect(mCheckBoxAutoConnect
							.isSelected());
					try {
						mPreviousParam.setMaxPollSize(Integer
								.parseInt(mTextFieldMaxPoll.getText().trim()));
					} catch (NumberFormatException err) {
						mPreviousParam.setMaxPollSize(0);
					}
				}

				// fill fields with actual parameters
				mTextFieldName.setText(param.getName());
				mTextFieldEndpoint.setText(param.getEndpoint());
				mTextFieldKeystore.setText(param.getKeystore());
				if (param.getKeystorePass() != null) {
					mPasswordFieldKeystore.setText(new String(param
							.getKeystorePass()));
				} else {
					mPasswordFieldKeystore.setText(null);
				}
				mTextFieldTruststore.setText(param.getTruststore());
				if (param.getTruststorePass() != null) {
					mPasswordFieldTruststore.setText(new String(param
							.getTruststorePass()));
				} else {
					mPasswordFieldTruststore.setText(null);
				}
				mCheckBoxBasicAuth.setSelected(param.isBasicAuthEnabled());
				mTextFieldUsername.setText(param.getBasicauthUser());
				if (param.getBasicauthPass() != null) {
					mTextFieldPassword.setText(new String(param
							.getBasicauthPass()));
				} else {
					mTextFieldPassword.setText(null);
				}
				if (param.getMaxPollSize() != 0) {
					mTextFieldMaxPoll.setText(String.valueOf(param
							.getMaxPollSize()));
				} else {
					mTextFieldMaxPoll.setText(null);
				}
				mCheckBoxDump.setSelected(param.isDump());
				mCheckBoxAutoConnect.setSelected(param.isAutoConnect());
				mLabelUsername.setEnabled(param.isBasicAuthEnabled());
				mTextFieldUsername.setEnabled(param.isBasicAuthEnabled());
				mLabelPassword.setEnabled(param.isBasicAuthEnabled());
				mTextFieldPassword.setEnabled(param.isBasicAuthEnabled());
				mPreviousParam = param;
			}
		});
		mConnectionList.setBorder(new LineBorder(
				SystemColor.activeCaptionBorder));
		mListModel = new DefaultListModel();
		mConnectionList.setModel(mListModel);
		if (cons != null) {
			for (Connection c : cons) {
				mListModel.add(mListModel.getSize(),
						c.getConnectionParameters());
			}
		}

		splitPane.setLeftComponent(mConnectionList);

		mPanelCenter = new JPanel();
		mPanelCenter.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		splitPane.setRightComponent(mPanelCenter);
		mPanelCenter.setLayout(new BorderLayout(0, 0));

		mPanelParameter = new JPanel();

		mPanelBlank = new JPanel();
		mPanelCenter.add(mPanelBlank, BorderLayout.CENTER);
		mPanelBlank.setLayout(null);

		JLabel lblNoConnectionsYet = new JLabel("No connections yet.");
		lblNoConnectionsYet.setBounds(0, 234, 497, 16);
		lblNoConnectionsYet.setHorizontalAlignment(SwingConstants.CENTER);
		mPanelBlank.add(lblNoConnectionsYet);
		mPanelParameter.setLayout(null);
		// panelCenter.add(panelParameter, BorderLayout.CENTER);

		JLabel label = new JLabel("Endpoint");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(3, 38, 130, 16);
		mPanelParameter.add(label);

		mTextFieldEndpoint = new JTextField();
		mTextFieldEndpoint.setColumns(10);
		mTextFieldEndpoint.setBounds(145, 38, 336, 22);
		mPanelParameter.add(mTextFieldEndpoint);

		JLabel label1 = new JLabel("Truststore");
		label1.setHorizontalAlignment(SwingConstants.RIGHT);
		label1.setBounds(3, 126, 130, 16);
		mPanelParameter.add(label1);

		JLabel label2 = new JLabel("Truststore password");
		label2.setHorizontalAlignment(SwingConstants.RIGHT);
		label2.setBounds(3, 155, 130, 16);
		mPanelParameter.add(label2);

		mTextFieldKeystore = new JTextField();
		mTextFieldKeystore.setColumns(10);
		mTextFieldKeystore.setBounds(145, 67, 227, 22);
		mPanelParameter.add(mTextFieldKeystore);

		JButton button = new JButton("Choose...");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(new File("."));
				int returnVal = chooser.showOpenDialog(ConnectionDialog.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					mTextFieldKeystore.setText(chooser.getSelectedFile()
							.getAbsolutePath());
				}
			}
		});
		button.setBounds(384, 66, 97, 25);
		mPanelParameter.add(button);

		mPasswordFieldKeystore = new JPasswordField();
		mPasswordFieldKeystore.setBounds(145, 96, 227, 22);
		mPanelParameter.add(mPasswordFieldKeystore);

		JLabel label3 = new JLabel("Keystore");
		label3.setHorizontalAlignment(SwingConstants.RIGHT);
		label3.setBounds(3, 67, 130, 16);
		mPanelParameter.add(label3);

		JLabel label4 = new JLabel("Keystore password");
		label4.setHorizontalAlignment(SwingConstants.RIGHT);
		label4.setBounds(3, 96, 130, 16);
		mPanelParameter.add(label4);

		mTextFieldTruststore = new JTextField();
		mTextFieldTruststore.setColumns(10);
		mTextFieldTruststore.setBounds(145, 126, 227, 22);
		mPanelParameter.add(mTextFieldTruststore);

		mPasswordFieldTruststore = new JPasswordField();
		mPasswordFieldTruststore.setBounds(145, 155, 227, 22);
		mPanelParameter.add(mPasswordFieldTruststore);

		JButton button1 = new JButton("Choose...");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(new File("."));
				int returnVal = chooser.showOpenDialog(ConnectionDialog.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					mTextFieldTruststore.setText(chooser.getSelectedFile()
							.getAbsolutePath());
				}
			}
		});
		button1.setBounds(384, 125, 97, 25);
		mPanelParameter.add(button1);

		JLabel label5 = new JLabel("Name");
		label5.setHorizontalAlignment(SwingConstants.RIGHT);
		label5.setBounds(0, 9, 133, 16);
		mPanelParameter.add(label5);

		mTextFieldName = new JTextField();
		mTextFieldName.setColumns(10);
		mTextFieldName.setBounds(145, 9, 227, 22);
		mPanelParameter.add(mTextFieldName);

		JLabel label6 = new JLabel("Basic Authentication");
		label6.setHorizontalAlignment(SwingConstants.RIGHT);
		label6.setBounds(3, 184, 130, 16);
		mPanelParameter.add(label6);

		mCheckBoxBasicAuth = new JCheckBox("");
		mCheckBoxBasicAuth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean selected = mCheckBoxBasicAuth.isSelected();
				mLabelUsername.setEnabled(selected);
				mTextFieldUsername.setEnabled(selected);
				mLabelPassword.setEnabled(selected);
				mTextFieldPassword.setEnabled(selected);
			}
		});
		mCheckBoxBasicAuth.setBounds(141, 180, 25, 25);
		mPanelParameter.add(mCheckBoxBasicAuth);

		mLabelUsername = new JLabel("Username");
		mLabelUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		mLabelUsername.setEnabled(false);
		mLabelUsername.setBounds(3, 217, 130, 16);
		mPanelParameter.add(mLabelUsername);

		mTextFieldUsername = new JTextField();
		mTextFieldUsername.setEnabled(false);
		mTextFieldUsername.setColumns(10);
		mTextFieldUsername.setBounds(145, 214, 227, 22);
		mPanelParameter.add(mTextFieldUsername);

		mLabelPassword = new JLabel("Password");
		mLabelPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		mLabelPassword.setEnabled(false);
		mLabelPassword.setBounds(3, 252, 130, 16);
		mPanelParameter.add(mLabelPassword);

		mTextFieldPassword = new JPasswordField();
		mTextFieldPassword.setEnabled(false);
		mTextFieldPassword.setBounds(145, 249, 227, 22);
		mPanelParameter.add(mTextFieldPassword);

		buttonTest = new JButton("Test connection");
		buttonTest.setBounds(145, 380, 154, 25);
		mPanelParameter.add(buttonTest);

		mTestPanel = new JTextArea();
		mTestPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		mTestPanel.setBounds(145, 413, 336, 150);
		mPanelParameter.add(mTestPanel);
		mTestPanel.setLayout(null);

		JLabel lblDump = new JLabel("Dump");
		lblDump.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDump.setBounds(36, 323, 97, 27);
		mPanelParameter.add(lblDump);

		mCheckBoxDump = new JCheckBox("");
		mCheckBoxDump.setBounds(145, 323, 25, 25);
		mPanelParameter.add(mCheckBoxDump);

		JLabel lblAutoConnect = new JLabel("Connect on start");
		lblAutoConnect.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAutoConnect.setBounds(3, 349, 130, 27);
		mPanelParameter.add(lblAutoConnect);

		mCheckBoxAutoConnect = new JCheckBox("");
		mCheckBoxAutoConnect.setBounds(145, 349, 25, 25);
		mPanelParameter.add(mCheckBoxAutoConnect);

		JLabel lblMaxpollsize = new JLabel("max-poll-result-size");
		lblMaxpollsize.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaxpollsize.setBounds(3, 281, 130, 16);
		mPanelParameter.add(lblMaxpollsize);

		JLabel lbloptional = new JLabel("(optional)");
		lbloptional.setBounds(77, 298, 56, 16);
		mPanelParameter.add(lbloptional);

		mTextFieldMaxPoll = new JTextField();
		mTextFieldMaxPoll.setBounds(145, 284, 116, 22);
		mPanelParameter.add(mTextFieldMaxPoll);
		mTextFieldMaxPoll.setColumns(10);
		mCheckBoxDump.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (mCheckBoxDump.isSelected()) {
					JOptionPane
							.showMessageDialog(
									mContentPane,
									"Dumping is NOT IF-MAP 2.0 compliant and can only be used with irond.",
									"Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		splitPane.setDividerLocation(150);

		JPanel panel1 = new JPanel();
		mContentPane.add(panel1, BorderLayout.NORTH);
		panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JButton addButton = new JButton("New");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = "New Connection (" + (mListModel.getSize() + 1)
						+ ")";
				ConnectionParameter param = null;
				try {
					param = ConnectionFactory.createConnectionParameter(name);
				} catch (ConnectionCreationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mListModel.add(mListModel.getSize(), param);
				mPanelCenter.remove(mPanelBlank);
				mPanelCenter.add(mPanelParameter, BorderLayout.CENTER);
				mPanelCenter.updateUI();
				mConnectionList.setSelectedIndex(mListModel.getSize() - 1);

			}
		});
		panel1.add(addButton);

		JButton removeButton = new JButton("Delete");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = mConnectionList.getSelectedIndex();
				if (index >= 0) {
					mListModel.remove(index);
					if (!mListModel.isEmpty()) {
						index = (index == 0) ? 0 : index - 1;
						mConnectionList.setSelectedIndex(index);
					} else {
						mPanelCenter.remove(mPanelParameter);
						mPanelCenter.add(mPanelBlank, BorderLayout.CENTER);
						mPanelCenter.updateUI();
					}
				}
			}
		});
		panel1.add(removeButton);

		JButton copyButton = new JButton("Duplicate");
		copyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = mConnectionList.getSelectedIndex();
				if (index >= 0) {
					ConnectionParameter param = ((ConnectionParameter) mListModel
							.getElementAt(index)).clone();
					String name = null;
					int id = param.getName().lastIndexOf('(');
					if (id > -1) {
						name = param.getName().substring(0, id - 1) + " ("
								+ (++mDupCounter) + ")";
					} else {
						name = param.getName() + " (" + (++mDupCounter) + ")";
					}
					param.setName(name);
					mListModel.add(mListModel.getSize(), param);
					mPanelCenter.remove(mPanelBlank);
					mPanelCenter.add(mPanelParameter, BorderLayout.CENTER);
					mPanelCenter.updateUI();
					mConnectionList.setSelectedIndex(mListModel.getSize() - 1);
				}
			}
		});
		panel1.add(copyButton);

		JPanel panel2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel2.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		mContentPane.add(panel2, BorderLayout.SOUTH);

		buttonClose = new JButton("Close");
		panel2.add(buttonClose);

		if (!mListModel.isEmpty()) {
			mConnectionList.setSelectedIndex(0);
		}
	}

	public Connection[] getConnections() {
		Connection[] connections = null;
		int length = mListModel.getSize();
		if (length > 0) {
			connections = new Connection[length];
			for (int i = 0; i < length; i++) {
				ConnectionParameter param = (ConnectionParameter) mListModel
						.getElementAt(i);
				try {
					Connection con = ConnectionFactory.createConnection(param);
					connections[i] = con;
				} catch (ConnectionCreationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return connections;
	}

	public void addConnections(Connection[] cons) {
		if (cons != null && cons.length > 0) {
			mListModel.clear();
			for (int i = 0; i < cons.length; i++) {
				mListModel.add(i, cons[i].getConnectionParameters());
			}
			mPanelCenter.remove(mPanelBlank);
			mPanelCenter.add(mPanelParameter, BorderLayout.CENTER);
			mPanelCenter.updateUI();
			if (!mListModel.isEmpty()) {
				mConnectionList.setSelectedIndex(0);
			}
		}
	}

	public void setSelectedConnection(Connection con) {
		int length = mListModel.getSize();
		if (con != null && length > 0) {
			for (int i = 0; i < length; i++) {
				ConnectionParameter param = (ConnectionParameter) mListModel
						.getElementAt(i);
				if (con.getConnectionParameters().toString()
						.equals(param.toString())) {
					mConnectionList.setSelectedIndex(i);
				}
			}
		}
	}
}
