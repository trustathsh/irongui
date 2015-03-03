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
	public JTextField mTextFieldIp;
	public final ButtonGroup mType = new ButtonGroup();
	public JRadioButton mIp4;
	public JRadioButton mIp6;
	public JTextField mTextFieldDomain;

	/**
	 * Create the panel.
	 */
	public QuickSubscribeIp() {
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) null));
		setLayout(new BorderLayout(5, 5));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		GridBagLayout gblPanel = new GridBagLayout();
		gblPanel.columnWidths = new int[] {69, 69, 69, 0 };
		gblPanel.rowHeights = new int[] {25, 0, 0, 0 };
		gblPanel.columnWeights = new double[] {0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gblPanel.rowWeights = new double[] {0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gblPanel);

		JLabel lblIpaddress = new JLabel("value");
		GridBagConstraints gbcLblIpaddress = new GridBagConstraints();
		gbcLblIpaddress.anchor = GridBagConstraints.EAST;
		gbcLblIpaddress.fill = GridBagConstraints.VERTICAL;
		gbcLblIpaddress.insets = new Insets(0, 0, 5, 5);
		gbcLblIpaddress.gridx = 0;
		gbcLblIpaddress.gridy = 0;
		panel.add(lblIpaddress, gbcLblIpaddress);

		mTextFieldIp = new JTextField();
		GridBagConstraints gbcTextFieldDomain = new GridBagConstraints();
		gbcTextFieldDomain.gridwidth = 2;
		gbcTextFieldDomain.fill = GridBagConstraints.BOTH;
		gbcTextFieldDomain.insets = new Insets(0, 0, 5, 0);
		gbcTextFieldDomain.gridx = 1;
		gbcTextFieldDomain.gridy = 0;
		panel.add(mTextFieldIp, gbcTextFieldDomain);
		mTextFieldIp.setColumns(10);

		JLabel lblIptype = new JLabel("type");
		GridBagConstraints gbcLblIptype = new GridBagConstraints();
		gbcLblIptype.anchor = GridBagConstraints.EAST;
		gbcLblIptype.fill = GridBagConstraints.VERTICAL;
		gbcLblIptype.insets = new Insets(0, 0, 5, 5);
		gbcLblIptype.gridx = 0;
		gbcLblIptype.gridy = 1;
		panel.add(lblIptype, gbcLblIptype);

		mIp4 = new JRadioButton("IPv4");
		GridBagConstraints gbcIp4 = new GridBagConstraints();
		gbcIp4.fill = GridBagConstraints.BOTH;
		gbcIp4.insets = new Insets(0, 0, 5, 5);
		gbcIp4.gridx = 1;
		gbcIp4.gridy = 1;
		panel.add(mIp4, gbcIp4);
		mType.add(mIp4);
		mIp4.setSelected(true);

		mIp6 = new JRadioButton("IPv6");
		GridBagConstraints gbcIp6 = new GridBagConstraints();
		gbcIp6.insets = new Insets(0, 0, 5, 0);
		gbcIp6.fill = GridBagConstraints.BOTH;
		gbcIp6.gridx = 2;
		gbcIp6.gridy = 1;
		panel.add(mIp6, gbcIp6);
		mType.add(mIp6);

		JLabel label = new JLabel("administrative-domain");
		GridBagConstraints gbcLabel = new GridBagConstraints();
		gbcLabel.anchor = GridBagConstraints.EAST;
		gbcLabel.insets = new Insets(0, 0, 0, 5);
		gbcLabel.gridx = 0;
		gbcLabel.gridy = 2;
		panel.add(label, gbcLabel);

		mTextFieldDomain = new JTextField();
		mTextFieldDomain.setColumns(10);
		GridBagConstraints gbcTextFieldDomain1 = new GridBagConstraints();
		gbcTextFieldDomain1.gridwidth = 2;
		gbcTextFieldDomain1.insets = new Insets(0, 0, 0, 5);
		gbcTextFieldDomain1.fill = GridBagConstraints.HORIZONTAL;
		gbcTextFieldDomain1.gridx = 1;
		gbcTextFieldDomain1.gridy = 2;
		panel.add(mTextFieldDomain, gbcTextFieldDomain1);
	}

}
