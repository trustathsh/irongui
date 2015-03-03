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
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import de.hshannover.f4.trust.irongui.util.ResourceHelper;
import de.hshannover.f4.trust.irongui.view.subscription.AccessRequestPanel;
import de.hshannover.f4.trust.irongui.view.subscription.DevicePanel;
import de.hshannover.f4.trust.irongui.view.subscription.IdentityPanel;
import de.hshannover.f4.trust.irongui.view.subscription.IpAddressPanel;
import de.hshannover.f4.trust.irongui.view.subscription.MacAddressPanel;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class Subscription_GUI extends JFrame {

	/*
	 * { //Set Look & Feel try {
	 * javax.swing.UIManager.setLookAndFeel(javax.swing
	 * .UIManager.getSystemLookAndFeelClassName()); } catch(Exception e) {
	 * e.printStackTrace(); } }
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 5038771778831689820L;
	public JPanel mContentPane;
	private final ButtonGroup mButtonGroup = new ButtonGroup();
	public AccessRequestPanel mAccessrequestpanel;
	JTextField mJTextField2;
	JTextField mJTextField1;
	JSpinner mJSpinner2;
	JSpinner mJSpinner1;
	public DevicePanel mDevicepanel;
	public IdentityPanel mIdentitypanel;
	IpAddressPanel mIpAddressPanel;
	public IpAddressPanel mIpaddresspanel;
	public MacAddressPanel mMacaddresspanel;
	private JPanel mAttributesPanel;
	public JRadioButton mRdbtnIpaddress;
	public static SubscribeDialog mSo;
	public JRadioButton mRdbtnMacaddress;
	public JRadioButton rdbtnAccessrequest;
	public JRadioButton rdbtnIdentity;
	public JRadioButton rdbtnDevice;

	public JCheckBox radioButton;
	public JCheckBox radioButton_1;
	public JCheckBox radioButton_2;
	public JCheckBox radioButton_3;
	public JCheckBox radioButton_4;
	public JButton btnSubscribe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Subscription_GUI frame = new Subscription_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setCallback(SubscribeDialog s) {
		mSo = s;
	}

	/**
	 * Create the frame.
	 */
	public Subscription_GUI() {
		setIconImage(ResourceHelper.getAppIconImage().getImage());
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(0, 0, 715, 639);
		this.setTitle("Subscribe to Metadata");
		mContentPane = new JPanel();
		mContentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(mContentPane);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		mContentPane.add(splitPane, BorderLayout.CENTER);
		splitPane.setDividerSize(3);

		JPanel identifierPanel = new JPanel();
		splitPane.setLeftComponent(identifierPanel);
		identifierPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel5 = new JPanel();
		FlowLayout panel5Layout = new FlowLayout();
		FlowLayout flowLayout1 = (FlowLayout) panel5.getLayout();
		flowLayout1.setAlignment(FlowLayout.LEFT);
		identifierPanel.add(panel5, BorderLayout.NORTH);
		panel5.setLayout(panel5Layout);
		panel5.setSize(707, 148);
		panel5Layout.setAlignment(FlowLayout.LEFT);
		panel5Layout.setAlignOnBaseline(true);
		{
			JPanel choosePanel = new JPanel();
			panel5.add(choosePanel);
			choosePanel.setLayout(new GridLayout(0, 1, 0, 0));
			choosePanel.setBorder(new TitledBorder(null, "IdentifierData type",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			{
				mRdbtnIpaddress = new JRadioButton("ip-address");
				choosePanel.add(mRdbtnIpaddress);
				mRdbtnIpaddress.setSelected(true);
				mRdbtnIpaddress.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (mIpaddresspanel == null) {
							mIpaddresspanel = new IpAddressPanel();
						}
						if (mAttributesPanel.getComponentCount() > 0) {
							mAttributesPanel.remove(0);
						}
						mAttributesPanel.add(mIpaddresspanel);
						mAttributesPanel.getParent().repaint();
						mAttributesPanel.revalidate();
					}
				});
				mButtonGroup.add(mRdbtnIpaddress);
				mRdbtnIpaddress.setVerticalAlignment(SwingConstants.TOP);
			}
			{
				mRdbtnMacaddress = new JRadioButton("mac-address");
				choosePanel.add(mRdbtnMacaddress);
				mRdbtnMacaddress.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (mMacaddresspanel == null) {
							mMacaddresspanel = new MacAddressPanel();
						}
						if (mAttributesPanel.getComponentCount() > 0) {
							mAttributesPanel.remove(0);
						}
						mAttributesPanel.add(mMacaddresspanel);
						mAttributesPanel.getParent().repaint();
						mAttributesPanel.revalidate();
					}
				});
				mButtonGroup.add(mRdbtnMacaddress);
				mRdbtnMacaddress.setVerticalAlignment(SwingConstants.TOP);
			}
			{
				rdbtnAccessrequest = new JRadioButton("access-request");
				choosePanel.add(rdbtnAccessrequest);
				rdbtnAccessrequest.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (mAccessrequestpanel == null) {
							mAccessrequestpanel = new AccessRequestPanel();
						}
						if (mAttributesPanel.getComponentCount() > 0) {
							mAttributesPanel.remove(0);
						}
						mAttributesPanel.add(mAccessrequestpanel);
						mAttributesPanel.getParent().repaint();
						mAttributesPanel.revalidate();
					}
				});
				mButtonGroup.add(rdbtnAccessrequest);
				rdbtnAccessrequest.setVerticalAlignment(SwingConstants.TOP);
			}
			{
				rdbtnIdentity = new JRadioButton("identity");
				choosePanel.add(rdbtnIdentity);
				rdbtnIdentity.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (mIdentitypanel == null) {
							mIdentitypanel = new IdentityPanel();
						}
						if (mAttributesPanel.getComponentCount() > 0) {
							mAttributesPanel.remove(0);
						}
						mAttributesPanel.add(mIdentitypanel);
						mAttributesPanel.getParent().repaint();
						mAttributesPanel.revalidate();
					}
				});
				mButtonGroup.add(rdbtnIdentity);
				rdbtnIdentity.setVerticalAlignment(SwingConstants.TOP);
			}
			{
				rdbtnDevice = new JRadioButton("device");
				choosePanel.add(rdbtnDevice);
				rdbtnDevice.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (mDevicepanel == null) {
							mDevicepanel = new DevicePanel();
						}
						if (mAttributesPanel.getComponentCount() > 0) {
							mAttributesPanel.remove(0);
						}
						mAttributesPanel.add(mDevicepanel);
						mAttributesPanel.getParent().repaint();
						mAttributesPanel.revalidate();
					}
				});
				mButtonGroup.add(rdbtnDevice);
				rdbtnDevice.setVerticalAlignment(SwingConstants.TOP);
			}
		}

		mAttributesPanel = new JPanel();
		FlowLayout attributesPanelLayout = new FlowLayout();
		attributesPanelLayout.setHgap(0);
		attributesPanelLayout.setVgap(0);
		attributesPanelLayout.setAlignment(FlowLayout.LEFT);
		mAttributesPanel.setLayout(attributesPanelLayout);
		panel5.add(mAttributesPanel);
		mAttributesPanel.setBorder(BorderFactory.createTitledBorder(null,
				"IdentifierData value", TitledBorder.LEADING,
				TitledBorder.DEFAULT_POSITION));
		mAttributesPanel.setPreferredSize(new java.awt.Dimension(557, 155));
		{
			mIpAddressPanel = new IpAddressPanel();
			mAttributesPanel.add(mIpAddressPanel);
			mIpAddressPanel.setPreferredSize(new java.awt.Dimension(445, 84));
		}
		FlowLayout flAttributesPanel = (FlowLayout) mAttributesPanel
				.getLayout();
		flAttributesPanel.setHgap(0);
		flAttributesPanel.setVgap(0);

		JPanel defaultPanel = new JPanel();
		identifierPanel.add(defaultPanel, BorderLayout.CENTER);
		FlowLayout defaultPanelLayout = new FlowLayout();
		defaultPanelLayout.setAlignment(FlowLayout.LEFT);
		defaultPanel.setLayout(defaultPanelLayout);

		JPanel filterPanel = new JPanel();
		defaultPanel.add(filterPanel);
		filterPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel terminalPanel = new JPanel();
			filterPanel.add(terminalPanel, BorderLayout.WEST);
			terminalPanel.setLayout(new GridLayout(0, 1, 0, 0));
			terminalPanel.setBorder(new TitledBorder(null,
					"terminal-identifier", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			{
				radioButton = new JCheckBox("identity");
				terminalPanel.add(radioButton);
				radioButton.setVerticalAlignment(SwingConstants.TOP);
			}
			{
				radioButton_1 = new JCheckBox("access-request");
				terminalPanel.add(radioButton_1);
				radioButton_1.setVerticalAlignment(SwingConstants.TOP);
			}
			{
				radioButton_2 = new JCheckBox("mac-address");
				terminalPanel.add(radioButton_2);
				radioButton_2.setVerticalAlignment(SwingConstants.TOP);
			}
			{
				radioButton_3 = new JCheckBox("ip-address");
				terminalPanel.add(radioButton_3);
				radioButton_3.setVerticalAlignment(SwingConstants.TOP);
			}
			{
				radioButton_4 = new JCheckBox("device");
				terminalPanel.add(radioButton_4);
			}
		}
		{
			JPanel panel3 = new JPanel();
			filterPanel.add(panel3, BorderLayout.CENTER);
			GridBagLayout panel3Layout = new GridBagLayout();
			panel3Layout.rowWeights = new double[] {0.1, 0.1, 0.1 };
			panel3Layout.rowHeights = new int[] {7, 7, 7 };
			panel3Layout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0,
					0.1 };
			panel3Layout.columnWidths = new int[] {76, 64, 72, 64, 7 };
			panel3.setLayout(panel3Layout);
			panel3.setPreferredSize(new java.awt.Dimension(560, 156));
			panel3.setBorder(BorderFactory.createTitledBorder("filter"));
			{
				JLabel label = new JLabel("match-links");
				panel3.add(label, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.NONE,
						new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				JLabel lblMatchlinks = new JLabel("result-filter");
				panel3.add(lblMatchlinks, new GridBagConstraints(0, 1, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				lblMatchlinks.setHorizontalAlignment(SwingConstants.RIGHT);
			}
			{
				JLabel lblMaxdepth = new JLabel("max-depth");
				panel3.add(lblMaxdepth, new GridBagConstraints(0, 0, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				lblMaxdepth.setHorizontalAlignment(SwingConstants.RIGHT);
			}
			{
				JLabel lblMaxsize = new JLabel("max-size");
				panel3.add(lblMaxsize, new GridBagConstraints(2, 0, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				lblMaxsize.setHorizontalAlignment(SwingConstants.RIGHT);
			}
			{
				mJSpinner1 = new JSpinner();
				panel3.add(mJSpinner1, new GridBagConstraints(1, 0, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0),
						0, 0));
				mJSpinner1.setModel(new SpinnerNumberModel(new Integer(8),
						new Integer(0), new Integer(9999), new Integer(1)));
			}
			{
				mJSpinner2 = new JSpinner();
				panel3.add(mJSpinner2, new GridBagConstraints(3, 0, 1, 1, 0.0,
						0.0, GridBagConstraints.WEST,
						GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0),
						0, 0));
				mJSpinner2.setModel(new SpinnerNumberModel(new Integer(16384),
						new Integer(1), new Integer(999999), new Integer(1)));
			}
			{
				mJTextField1 = new JTextField();
				panel3.add(mJTextField1, new GridBagConstraints(1, 1, 4, 1,
						0.0, 0.0, GridBagConstraints.WEST,
						GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0),
						0, 0));
			}
			{
				mJTextField2 = new JTextField();
				panel3.add(mJTextField2, new GridBagConstraints(1, 2, 4, 1,
						0.0, 0.0, GridBagConstraints.WEST,
						GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0),
						0, 0));
			}
		}

		JPanel okPanel = new JPanel();
		FlowLayout okPanelLayout = new FlowLayout();
		identifierPanel.add(okPanel, BorderLayout.SOUTH);
		okPanel.setLayout(okPanelLayout);
		FlowLayout flowLayout4 = (FlowLayout) okPanel.getLayout();
		flowLayout4.setAlignment(FlowLayout.CENTER);

		btnSubscribe = new JButton("Subscribe");

		okPanel.add(btnSubscribe);

		JPanel subscriptionPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) subscriptionPanel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		splitPane.setRightComponent(subscriptionPanel);
		subscriptionPanel.setPreferredSize(new java.awt.Dimension(707, 129));
	}
}
