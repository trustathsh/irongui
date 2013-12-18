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
 * This file is part of irongui, version 0.4.2,
 * implemented by the Trust@HsH research group at the Hochschule Hannover.
 * %%
 * Copyright (C) 2010 - 2013 Trust@HsH
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



package de.hshannover.f4.trust.irongui.view.subscription;




import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class QuickSubscribeIp extends JPanel {
	private static final long serialVersionUID = -9105336717460904279L;
	public JTextField textFieldIp;
	public final ButtonGroup type = new ButtonGroup();
	public JRadioButton ip4;
	public JRadioButton ip6;
	public JTextField textFieldDomain;

	/**
	 * Create the panel.
	 */
	public QuickSubscribeIp() {
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) null));
		setLayout(new BorderLayout(5, 5));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{69, 69, 69, 0};
		gbl_panel.rowHeights = new int[]{25, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblIpaddress = new JLabel("value");
		GridBagConstraints gbc_lblIpaddress = new GridBagConstraints();
		gbc_lblIpaddress.anchor = GridBagConstraints.EAST;
		gbc_lblIpaddress.fill = GridBagConstraints.VERTICAL;
		gbc_lblIpaddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblIpaddress.gridx = 0;
		gbc_lblIpaddress.gridy = 0;
		panel.add(lblIpaddress, gbc_lblIpaddress);
		
		textFieldIp = new JTextField();
		GridBagConstraints gbc_textFieldDomain = new GridBagConstraints();
		gbc_textFieldDomain.gridwidth = 2;
		gbc_textFieldDomain.fill = GridBagConstraints.BOTH;
		gbc_textFieldDomain.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDomain.gridx = 1;
		gbc_textFieldDomain.gridy = 0;
		panel.add(textFieldIp, gbc_textFieldDomain);
		textFieldIp.setColumns(10);
		
		JLabel lblIptype = new JLabel("type");
		GridBagConstraints gbc_lblIptype = new GridBagConstraints();
		gbc_lblIptype.anchor = GridBagConstraints.EAST;
		gbc_lblIptype.fill = GridBagConstraints.VERTICAL;
		gbc_lblIptype.insets = new Insets(0, 0, 5, 5);
		gbc_lblIptype.gridx = 0;
		gbc_lblIptype.gridy = 1;
		panel.add(lblIptype, gbc_lblIptype);
		
		ip4 = new JRadioButton("IPv4");
		GridBagConstraints gbc_ip4 = new GridBagConstraints();
		gbc_ip4.fill = GridBagConstraints.BOTH;
		gbc_ip4.insets = new Insets(0, 0, 5, 5);
		gbc_ip4.gridx = 1;
		gbc_ip4.gridy = 1;
		panel.add(ip4, gbc_ip4);
		type.add(ip4);
		ip4.setSelected(true);
		
		ip6 = new JRadioButton("IPv6");
		GridBagConstraints gbc_ip6 = new GridBagConstraints();
		gbc_ip6.insets = new Insets(0, 0, 5, 0);
		gbc_ip6.fill = GridBagConstraints.BOTH;
		gbc_ip6.gridx = 2;
		gbc_ip6.gridy = 1;
		panel.add(ip6, gbc_ip6);
		type.add(ip6);
		
		JLabel label = new JLabel("administrative-domain");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 2;
		panel.add(label, gbc_label);
		
		textFieldDomain = new JTextField();
		textFieldDomain.setColumns(10);
		GridBagConstraints gbc_textFieldDomain1 = new GridBagConstraints();
		gbc_textFieldDomain1.gridwidth = 2;
		gbc_textFieldDomain1.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldDomain1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDomain1.gridx = 1;
		gbc_textFieldDomain1.gridy = 2;
		panel.add(textFieldDomain, gbc_textFieldDomain1);
	}

}
