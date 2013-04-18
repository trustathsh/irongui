package de.fhhannover.inform.ifmap.view.dialog;

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

import de.fhhannover.inform.ifmap.util.ResourceHelper;
import de.fhhannover.inform.ifmap.view.subscription.AccessRequestPanel;
import de.fhhannover.inform.ifmap.view.subscription.DevicePanel;
import de.fhhannover.inform.ifmap.view.subscription.IdentityPanel;
import de.fhhannover.inform.ifmap.view.subscription.IpAddressPanel;
import de.fhhannover.inform.ifmap.view.subscription.MacAddressPanel;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class Subscription_GUI extends JFrame {

	/*{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}*/


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5038771778831689820L;
	public JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public AccessRequestPanel accessrequestpanel;
	JTextField jTextField2;
	JTextField jTextField1;
	JSpinner jSpinner2;
	JSpinner jSpinner1;
	public DevicePanel devicepanel;
	public IdentityPanel identitypanel;
	IpAddressPanel ipAddressPanel;
	public IpAddressPanel ipaddresspanel;
	public MacAddressPanel macaddresspanel;	
	private JPanel attributesPanel;
	public  JRadioButton rdbtnIpaddress;
	public static SubscribeDialog so;
	public JRadioButton rdbtnMacaddress;
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

	public void setCallback(SubscribeDialog s){
		so = s;
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
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane, BorderLayout.CENTER);
		splitPane.setDividerSize(3);

		JPanel identifierPanel = new JPanel();
		splitPane.setLeftComponent(identifierPanel);
		identifierPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		FlowLayout panel_5Layout = new FlowLayout();		
		FlowLayout flowLayout_1 = (FlowLayout) panel_5.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		identifierPanel.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(panel_5Layout);
		panel_5.setSize(707, 148);
		panel_5Layout.setAlignment(FlowLayout.LEFT);	
		panel_5Layout.setAlignOnBaseline(true);
		{
			JPanel choosePanel = new JPanel();
			panel_5.add(choosePanel);
			choosePanel.setLayout(new GridLayout(0, 1, 0, 0));
			choosePanel.setBorder(new TitledBorder(null, "IdentifierData type", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			{
				rdbtnIpaddress = new JRadioButton("ip-address");
				choosePanel.add(rdbtnIpaddress);
				rdbtnIpaddress.setSelected(true);
				rdbtnIpaddress.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(ipaddresspanel == null){
							ipaddresspanel = new IpAddressPanel();
						}
						if(attributesPanel.getComponentCount() > 0)
							attributesPanel.remove(0);						
						attributesPanel.add(ipaddresspanel);
						attributesPanel.getParent().repaint();
						attributesPanel.revalidate();
					}
				});
				buttonGroup.add(rdbtnIpaddress);
				rdbtnIpaddress.setVerticalAlignment(SwingConstants.TOP);
			}
			{
				rdbtnMacaddress = new JRadioButton("mac-address");
				choosePanel.add(rdbtnMacaddress);
				rdbtnMacaddress.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(macaddresspanel == null){
							macaddresspanel = new MacAddressPanel();
						}
						if(attributesPanel.getComponentCount() > 0)
							attributesPanel.remove(0);
						attributesPanel.add(macaddresspanel);
						attributesPanel.getParent().repaint();
						attributesPanel.revalidate();
					}
				});
				buttonGroup.add(rdbtnMacaddress);
				rdbtnMacaddress.setVerticalAlignment(SwingConstants.TOP);
			}
			{
				rdbtnAccessrequest = new JRadioButton("access-request");
				choosePanel.add(rdbtnAccessrequest);
				rdbtnAccessrequest.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(accessrequestpanel == null){
							accessrequestpanel = new AccessRequestPanel();
						}
						if(attributesPanel.getComponentCount() > 0)
							attributesPanel.remove(0);
						attributesPanel.add(accessrequestpanel);
						attributesPanel.getParent().repaint();
						attributesPanel.revalidate();
					}
				});
				buttonGroup.add(rdbtnAccessrequest);
				rdbtnAccessrequest.setVerticalAlignment(SwingConstants.TOP);
			}
			{
				rdbtnIdentity = new JRadioButton("identity");
				choosePanel.add(rdbtnIdentity);
				rdbtnIdentity.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(identitypanel == null){
							identitypanel = new IdentityPanel();							
						}
						if(attributesPanel.getComponentCount() > 0)
							attributesPanel.remove(0);
						attributesPanel.add(identitypanel);
						attributesPanel.getParent().repaint();
						attributesPanel.revalidate();
					}
				});
				buttonGroup.add(rdbtnIdentity);
				rdbtnIdentity.setVerticalAlignment(SwingConstants.TOP);
			}
			{
				rdbtnDevice = new JRadioButton("device");
				choosePanel.add(rdbtnDevice);
				rdbtnDevice.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(devicepanel == null){
							devicepanel = new DevicePanel();
						}
						if(attributesPanel.getComponentCount() > 0)
							attributesPanel.remove(0);
						attributesPanel.add(devicepanel);
						attributesPanel.getParent().repaint();
						attributesPanel.revalidate();
					}
				});
				buttonGroup.add(rdbtnDevice);
				rdbtnDevice.setVerticalAlignment(SwingConstants.TOP);
			}
		}

		attributesPanel = new JPanel();
		FlowLayout attributesPanelLayout = new FlowLayout();
		attributesPanelLayout.setHgap(0);
		attributesPanelLayout.setVgap(0);
		attributesPanelLayout.setAlignment(FlowLayout.LEFT);
		attributesPanel.setLayout(attributesPanelLayout);
		panel_5.add(attributesPanel);
		attributesPanel.setBorder(BorderFactory.createTitledBorder(null, "IdentifierData value", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION));
		attributesPanel.setPreferredSize(new java.awt.Dimension(557, 155));
		{
			ipAddressPanel = new IpAddressPanel();
			attributesPanel.add(ipAddressPanel);
			ipAddressPanel.setPreferredSize(new java.awt.Dimension(445, 84));
		}
		FlowLayout fl_attributesPanel = (FlowLayout) attributesPanel.getLayout();
		fl_attributesPanel.setHgap(0);
		fl_attributesPanel.setVgap(0);

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
			terminalPanel.setBorder(new TitledBorder(null, "terminal-identifier", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
			JPanel panel_3 = new JPanel();
			filterPanel.add(panel_3, BorderLayout.CENTER);
			GridBagLayout panel_3Layout = new GridBagLayout();
			panel_3Layout.rowWeights = new double[] {0.1, 0.1, 0.1};
			panel_3Layout.rowHeights = new int[] {7, 7, 7};
			panel_3Layout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.1};
			panel_3Layout.columnWidths = new int[] {76, 64, 72, 64, 7};	
			panel_3.setLayout(panel_3Layout);
			panel_3.setPreferredSize(new java.awt.Dimension(560, 156));
			panel_3.setBorder(BorderFactory.createTitledBorder("filter"));
			{
				JLabel label = new JLabel("match-links");
				panel_3.add(label, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				JLabel lblMatchlinks = new JLabel("result-filter");
				panel_3.add(lblMatchlinks, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				lblMatchlinks.setHorizontalAlignment(SwingConstants.RIGHT);
			}
			{
				JLabel lblMaxdepth = new JLabel("max-depth");
				panel_3.add(lblMaxdepth, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				lblMaxdepth.setHorizontalAlignment(SwingConstants.RIGHT);
			}
			{
				JLabel lblMaxsize = new JLabel("max-size");
				panel_3.add(lblMaxsize, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				lblMaxsize.setHorizontalAlignment(SwingConstants.RIGHT);
			}
			{			
				jSpinner1 = new JSpinner();
				panel_3.add(jSpinner1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
				jSpinner1.setModel(new SpinnerNumberModel(new Integer(8), new Integer(0), new Integer(9999), new Integer(1)));
			}
			{		
				jSpinner2 = new JSpinner();
				panel_3.add(jSpinner2, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
				jSpinner2.setModel(new SpinnerNumberModel(new Integer(16384), new Integer(1), new Integer(999999), new Integer(1)));
			}
			{
				jTextField1 = new JTextField();
				panel_3.add(jTextField1, new GridBagConstraints(1, 1, 4, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jTextField2 = new JTextField();
				panel_3.add(jTextField2, new GridBagConstraints(1, 2, 4, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
		}

		JPanel okPanel = new JPanel();
		FlowLayout okPanelLayout = new FlowLayout();		
		identifierPanel.add(okPanel, BorderLayout.SOUTH);
		okPanel.setLayout(okPanelLayout);
		FlowLayout flowLayout_4 = (FlowLayout) okPanel.getLayout();
		flowLayout_4.setAlignment(FlowLayout.CENTER);
		
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
