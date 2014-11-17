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
 * This file is part of irongui, version 0.4.5,
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
	private JPanel contentPane;
	private JTextField textFieldEndpoint;
	private JTextField textFieldKeystore;
	private JPasswordField passwordFieldKeystore;
	private JTextField textFieldTruststore;
	private JPasswordField passwordFieldTruststore;
	private JTextField textFieldName;
	private JTextField textFieldUsername;
	private JPasswordField textFieldPassword;
	private JLabel labelUsername = null;
	private JLabel labelPassword;
	private ConnectionParameter previousParam = null;
	private JCheckBox checkBoxBasicAuth;
	public JTextArea testPanel;
	public JButton buttonClose;
	public DefaultListModel mListModel;
	public JCheckBox checkBoxDump;
	public JCheckBox checkBoxAutoConnect;
	public JList mConnectionList;
	private JPanel panelCenter;
	private JPanel panelParameter;
	private JPanel panelBlank;
	private int dupCounter = 0;
	public JButton buttonTest;
	private JTextField textFieldMaxPoll;

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
			param.setName(textFieldName.getText().trim());
			param.setEndpoint(textFieldEndpoint.getText().trim());
			param.setTruststore(textFieldTruststore.getText().trim());
			param.setTruststorePass(new String(passwordFieldTruststore
					.getPassword()));
			param.setKeystore(textFieldKeystore.getText().trim());
			param.setKeystorePass(new String(passwordFieldKeystore
					.getPassword()));
			param.setBasicAuthEnabled(checkBoxBasicAuth.isSelected());
			param.setBasicauthUser(textFieldUsername.getText().trim());
			param.setBasicauthPass(new String(textFieldPassword.getPassword()));
			param.setDump(checkBoxDump.isSelected());
			param.setAutoConnect(checkBoxAutoConnect.isSelected());
			try {
				param.setMaxPollSize(Integer.parseInt(textFieldMaxPoll.getText().trim()));
			}
			catch(NumberFormatException err ){
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);

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
				if (previousParam != null) {
					previousParam.setName(textFieldName.getText().trim());
					previousParam.setEndpoint(textFieldEndpoint.getText()
							.trim());
					previousParam.setTruststore(textFieldTruststore.getText()
							.trim());
					previousParam.setTruststorePass(new String(
							passwordFieldTruststore.getPassword()));
					previousParam.setKeystore(textFieldKeystore.getText()
							.trim());
					previousParam.setKeystorePass(new String(
							passwordFieldKeystore.getPassword()));
					previousParam.setBasicAuthEnabled(checkBoxBasicAuth
							.isSelected());
					previousParam.setBasicauthUser(textFieldUsername.getText()
							.trim());
					previousParam.setBasicauthPass(new String(textFieldPassword
							.getPassword()));
					previousParam.setDump(checkBoxDump.isSelected());
					previousParam.setAutoConnect(checkBoxAutoConnect.isSelected());
					try {
						previousParam.setMaxPollSize(Integer.parseInt(textFieldMaxPoll.getText().trim()));
					}
					catch(NumberFormatException err ){
						previousParam.setMaxPollSize(0);
					}					
				}

				// fill fields with actual parameters
				textFieldName.setText(param.getName());
				textFieldEndpoint.setText(param.getEndpoint());
				textFieldKeystore.setText(param.getKeystore());
				if (param.getKeystorePass() != null) {
					passwordFieldKeystore.setText(new String(param
							.getKeystorePass()));
				} else {
					passwordFieldKeystore.setText(null);
				}
				textFieldTruststore.setText(param.getTruststore());
				if (param.getTruststorePass() != null) {
					passwordFieldTruststore.setText(new String(param
							.getTruststorePass()));
				} else {
					passwordFieldTruststore.setText(null);
				}
				checkBoxBasicAuth.setSelected(param.isBasicAuthEnabled());
				textFieldUsername.setText(param.getBasicauthUser());
				if (param.getBasicauthPass() != null) {
					textFieldPassword.setText(new String(param
							.getBasicauthPass()));
				} else {
					textFieldPassword.setText(null);
				}
				if(param.getMaxPollSize() != 0) {
					textFieldMaxPoll.setText(String.valueOf(param.getMaxPollSize()));
				}
				else {
					textFieldMaxPoll.setText(null);
				}
				checkBoxDump.setSelected(param.isDump());
				checkBoxAutoConnect.setSelected(param.isAutoConnect());
				labelUsername.setEnabled(param.isBasicAuthEnabled());
				textFieldUsername.setEnabled(param.isBasicAuthEnabled());
				labelPassword.setEnabled(param.isBasicAuthEnabled());
				textFieldPassword.setEnabled(param.isBasicAuthEnabled());
				previousParam = param;
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

		panelCenter = new JPanel();
		panelCenter.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		splitPane.setRightComponent(panelCenter);
		panelCenter.setLayout(new BorderLayout(0, 0));

		panelParameter = new JPanel();

		panelBlank = new JPanel();
		panelCenter.add(panelBlank, BorderLayout.CENTER);
		panelBlank.setLayout(null);

		JLabel lblNoConnectionsYet = new JLabel("No connections yet.");
		lblNoConnectionsYet.setBounds(0, 234, 497, 16);
		lblNoConnectionsYet.setHorizontalAlignment(SwingConstants.CENTER);
		panelBlank.add(lblNoConnectionsYet);
		panelParameter.setLayout(null);
		// panelCenter.add(panelParameter, BorderLayout.CENTER);

		JLabel label = new JLabel("Endpoint");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(3, 38, 130, 16);
		panelParameter.add(label);

		textFieldEndpoint = new JTextField();
		textFieldEndpoint.setColumns(10);
		textFieldEndpoint.setBounds(145, 38, 336, 22);
		panelParameter.add(textFieldEndpoint);

		JLabel label_1 = new JLabel("Truststore");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(3, 126, 130, 16);
		panelParameter.add(label_1);

		JLabel label_2 = new JLabel("Truststore password");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(3, 155, 130, 16);
		panelParameter.add(label_2);

		textFieldKeystore = new JTextField();
		textFieldKeystore.setColumns(10);
		textFieldKeystore.setBounds(145, 67, 227, 22);
		panelParameter.add(textFieldKeystore);

		JButton button = new JButton("Choose...");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(new File("."));
				int returnVal = chooser.showOpenDialog(ConnectionDialog.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					textFieldKeystore.setText(chooser.getSelectedFile()
							.getAbsolutePath());
				}
			}
		});
		button.setBounds(384, 66, 97, 25);
		panelParameter.add(button);

		passwordFieldKeystore = new JPasswordField();
		passwordFieldKeystore.setBounds(145, 96, 227, 22);
		panelParameter.add(passwordFieldKeystore);

		JLabel label_3 = new JLabel("Keystore");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(3, 67, 130, 16);
		panelParameter.add(label_3);

		JLabel label_4 = new JLabel("Keystore password");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(3, 96, 130, 16);
		panelParameter.add(label_4);

		textFieldTruststore = new JTextField();
		textFieldTruststore.setColumns(10);
		textFieldTruststore.setBounds(145, 126, 227, 22);
		panelParameter.add(textFieldTruststore);

		passwordFieldTruststore = new JPasswordField();
		passwordFieldTruststore.setBounds(145, 155, 227, 22);
		panelParameter.add(passwordFieldTruststore);

		JButton button_1 = new JButton("Choose...");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(new File("."));
				int returnVal = chooser.showOpenDialog(ConnectionDialog.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					textFieldTruststore.setText(chooser.getSelectedFile()
							.getAbsolutePath());
				}
			}
		});
		button_1.setBounds(384, 125, 97, 25);
		panelParameter.add(button_1);

		JLabel label_5 = new JLabel("Name");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBounds(0, 9, 133, 16);
		panelParameter.add(label_5);

		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(145, 9, 227, 22);
		panelParameter.add(textFieldName);

		JLabel label_6 = new JLabel("Basic Authentication");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setBounds(3, 184, 130, 16);
		panelParameter.add(label_6);

		checkBoxBasicAuth = new JCheckBox("");
		checkBoxBasicAuth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean selected = checkBoxBasicAuth.isSelected();
				labelUsername.setEnabled(selected);
				textFieldUsername.setEnabled(selected);
				labelPassword.setEnabled(selected);
				textFieldPassword.setEnabled(selected);
			}
		});
		checkBoxBasicAuth.setBounds(141, 180, 25, 25);
		panelParameter.add(checkBoxBasicAuth);

		labelUsername = new JLabel("Username");
		labelUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		labelUsername.setEnabled(false);
		labelUsername.setBounds(3, 217, 130, 16);
		panelParameter.add(labelUsername);

		textFieldUsername = new JTextField();
		textFieldUsername.setEnabled(false);
		textFieldUsername.setColumns(10);
		textFieldUsername.setBounds(145, 214, 227, 22);
		panelParameter.add(textFieldUsername);

		labelPassword = new JLabel("Password");
		labelPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPassword.setEnabled(false);
		labelPassword.setBounds(3, 252, 130, 16);
		panelParameter.add(labelPassword);

		textFieldPassword = new JPasswordField();
		textFieldPassword.setEnabled(false);
		textFieldPassword.setBounds(145, 249, 227, 22);
		panelParameter.add(textFieldPassword);

		buttonTest = new JButton("Test connection");
		buttonTest.setBounds(145, 380, 154, 25);
		panelParameter.add(buttonTest);

		testPanel = new JTextArea();
		testPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		testPanel.setBounds(145, 413, 336, 150);
		panelParameter.add(testPanel);
		testPanel.setLayout(null);

		JLabel lblDump = new JLabel("Dump");
		lblDump.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDump.setBounds(36, 323, 97, 27);
		panelParameter.add(lblDump);

		checkBoxDump = new JCheckBox("");
		checkBoxDump.setBounds(145, 323, 25, 25);
		panelParameter.add(checkBoxDump);

		JLabel lblAutoConnect = new JLabel("Connect on start");
		lblAutoConnect.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAutoConnect.setBounds(3, 349, 130, 27);
		panelParameter.add(lblAutoConnect);

		checkBoxAutoConnect = new JCheckBox("");
		checkBoxAutoConnect.setBounds(145, 349, 25, 25);
		panelParameter.add(checkBoxAutoConnect);

		JLabel lblMaxpollsize = new JLabel("max-poll-result-size");
		lblMaxpollsize.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaxpollsize.setBounds(3, 281, 130, 16);
		panelParameter.add(lblMaxpollsize);
		
		JLabel lbloptional = new JLabel("(optional)");
		lbloptional.setBounds(77, 298, 56, 16);
		panelParameter.add(lbloptional);
		
		textFieldMaxPoll = new JTextField();
		textFieldMaxPoll.setBounds(145, 284, 116, 22);
		panelParameter.add(textFieldMaxPoll);
		textFieldMaxPoll.setColumns(10);
		checkBoxDump.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (checkBoxDump.isSelected()) {
					JOptionPane
							.showMessageDialog(
									contentPane,
									"Dumping is NOT IF-MAP 2.0 compliant and can only be used with irond.",
									"Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		splitPane.setDividerLocation(150);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

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
				panelCenter.remove(panelBlank);
				panelCenter.add(panelParameter, BorderLayout.CENTER);
				panelCenter.updateUI();
				mConnectionList.setSelectedIndex(mListModel.getSize() - 1);

			}
		});
		panel_1.add(addButton);

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
						panelCenter.remove(panelParameter);
						panelCenter.add(panelBlank, BorderLayout.CENTER);
						panelCenter.updateUI();
					}
				}
			}
		});
		panel_1.add(removeButton);

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
								+ (++dupCounter) + ")";
					} else {
						name = param.getName() + " (" + (++dupCounter) + ")";
					}
					param.setName(name);
					mListModel.add(mListModel.getSize(), param);
					panelCenter.remove(panelBlank);
					panelCenter.add(panelParameter, BorderLayout.CENTER);
					panelCenter.updateUI();
					mConnectionList.setSelectedIndex(mListModel.getSize() - 1);
				}
			}
		});
		panel_1.add(copyButton);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panel_2, BorderLayout.SOUTH);

		buttonClose = new JButton("Close");
		panel_2.add(buttonClose);

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
			panelCenter.remove(panelBlank);
			panelCenter.add(panelParameter, BorderLayout.CENTER);
			panelCenter.updateUI();
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
