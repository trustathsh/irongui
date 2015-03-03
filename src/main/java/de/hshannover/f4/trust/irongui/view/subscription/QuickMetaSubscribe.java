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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class QuickMetaSubscribe extends JPanel {

	private static final long serialVersionUID = -9105336717460904279L;
	public JTextField mTextFieldResultFilter;
	public JTextField mTextFieldMatchLinks;
	private JSpinner mMaxDepth;
	private JSpinner mMaxSize;
	private JCheckBox mChckbxAr;
	private JCheckBox mChckbxId;
	private JCheckBox mChckbxIp;
	private JCheckBox mChckbxDevice;
	private JCheckBox mChckbxMac;

	/**
	 * Create the panel.
	 */
	public QuickMetaSubscribe() {
		setAlignmentY(2.0f);
		setAlignmentX(2.0f);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] {0, 29, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblTerminals = new JLabel("terminals");
		GridBagConstraints gbcLblTerminals = new GridBagConstraints();
		gbcLblTerminals.anchor = GridBagConstraints.EAST;
		gbcLblTerminals.insets = new Insets(0, 0, 5, 5);
		gbcLblTerminals.gridx = 0;
		gbcLblTerminals.gridy = 0;
		add(lblTerminals, gbcLblTerminals);

		mChckbxMac = new JCheckBox("mac");
		GridBagConstraints gbcChckbxMac = new GridBagConstraints();
		gbcChckbxMac.anchor = GridBagConstraints.WEST;
		gbcChckbxMac.insets = new Insets(0, 0, 5, 5);
		gbcChckbxMac.gridx = 1;
		gbcChckbxMac.gridy = 0;
		add(mChckbxMac, gbcChckbxMac);

		mChckbxDevice = new JCheckBox("dev");
		GridBagConstraints gbcChckbxDevice = new GridBagConstraints();
		gbcChckbxDevice.anchor = GridBagConstraints.WEST;
		gbcChckbxDevice.insets = new Insets(0, 0, 5, 5);
		gbcChckbxDevice.gridx = 2;
		gbcChckbxDevice.gridy = 0;
		add(mChckbxDevice, gbcChckbxDevice);

		mChckbxIp = new JCheckBox("ip");
		GridBagConstraints gbcChckbxIp = new GridBagConstraints();
		gbcChckbxIp.anchor = GridBagConstraints.WEST;
		gbcChckbxIp.insets = new Insets(0, 0, 5, 5);
		gbcChckbxIp.gridx = 3;
		gbcChckbxIp.gridy = 0;
		add(mChckbxIp, gbcChckbxIp);

		mChckbxId = new JCheckBox("id");
		GridBagConstraints gbcChckbxId = new GridBagConstraints();
		gbcChckbxId.anchor = GridBagConstraints.WEST;
		gbcChckbxId.insets = new Insets(0, 0, 5, 5);
		gbcChckbxId.gridx = 4;
		gbcChckbxId.gridy = 0;
		add(mChckbxId, gbcChckbxId);

		mChckbxAr = new JCheckBox("ar");
		GridBagConstraints gbcChckbxAr = new GridBagConstraints();
		gbcChckbxAr.anchor = GridBagConstraints.WEST;
		gbcChckbxAr.insets = new Insets(0, 0, 5, 0);
		gbcChckbxAr.gridx = 5;
		gbcChckbxAr.gridy = 0;
		add(mChckbxAr, gbcChckbxAr);

		JLabel lblMaxdepth = new JLabel("max-depth");
		GridBagConstraints gbcLblMaxdepth = new GridBagConstraints();
		gbcLblMaxdepth.anchor = GridBagConstraints.EAST;
		gbcLblMaxdepth.insets = new Insets(0, 0, 5, 5);
		gbcLblMaxdepth.gridx = 0;
		gbcLblMaxdepth.gridy = 1;
		add(lblMaxdepth, gbcLblMaxdepth);

		mMaxDepth = new JSpinner();
		mMaxDepth.setModel(new SpinnerNumberModel(new Integer(8), null, null,
				new Integer(1)));
		GridBagConstraints gbcMaxDepth = new GridBagConstraints();
		gbcMaxDepth.fill = GridBagConstraints.HORIZONTAL;
		gbcMaxDepth.insets = new Insets(0, 0, 5, 5);
		gbcMaxDepth.gridx = 1;
		gbcMaxDepth.gridy = 1;
		add(mMaxDepth, gbcMaxDepth);

		JLabel lblMaxsize = new JLabel("max-size");
		GridBagConstraints gbcLblMaxsize = new GridBagConstraints();
		gbcLblMaxsize.anchor = GridBagConstraints.EAST;
		gbcLblMaxsize.insets = new Insets(0, 0, 5, 5);
		gbcLblMaxsize.gridx = 2;
		gbcLblMaxsize.gridy = 1;
		add(lblMaxsize, gbcLblMaxsize);

		mMaxSize = new JSpinner();
		mMaxSize.setModel(new SpinnerNumberModel(new Integer(64384), null, null,
				new Integer(1)));
		GridBagConstraints gbcMaxSize = new GridBagConstraints();
		gbcMaxSize.fill = GridBagConstraints.HORIZONTAL;
		gbcMaxSize.gridwidth = 2;
		gbcMaxSize.insets = new Insets(0, 0, 5, 5);
		gbcMaxSize.gridx = 3;
		gbcMaxSize.gridy = 1;
		add(mMaxSize, gbcMaxSize);

		JLabel lblResultfilter = new JLabel("result-filter");
		GridBagConstraints gbcLblResultfilter = new GridBagConstraints();
		gbcLblResultfilter.anchor = GridBagConstraints.EAST;
		gbcLblResultfilter.insets = new Insets(0, 0, 5, 5);
		gbcLblResultfilter.gridx = 0;
		gbcLblResultfilter.gridy = 2;
		add(lblResultfilter, gbcLblResultfilter);

		mTextFieldResultFilter = new JTextField();
		GridBagConstraints gbcTextFieldResultFilter = new GridBagConstraints();
		gbcTextFieldResultFilter.gridwidth = 5;
		gbcTextFieldResultFilter.insets = new Insets(0, 0, 5, 0);
		gbcTextFieldResultFilter.fill = GridBagConstraints.HORIZONTAL;
		gbcTextFieldResultFilter.gridx = 1;
		gbcTextFieldResultFilter.gridy = 2;
		add(mTextFieldResultFilter, gbcTextFieldResultFilter);
		mTextFieldResultFilter.setColumns(10);

		JLabel lblMatchlinks = new JLabel("match-links");
		GridBagConstraints gbcLblMatchlinks = new GridBagConstraints();
		gbcLblMatchlinks.anchor = GridBagConstraints.EAST;
		gbcLblMatchlinks.insets = new Insets(0, 0, 0, 5);
		gbcLblMatchlinks.gridx = 0;
		gbcLblMatchlinks.gridy = 3;
		add(lblMatchlinks, gbcLblMatchlinks);

		mTextFieldMatchLinks = new JTextField();
		GridBagConstraints gbcTextFieldMatchLinks = new GridBagConstraints();
		gbcTextFieldMatchLinks.gridwidth = 5;
		gbcTextFieldMatchLinks.fill = GridBagConstraints.HORIZONTAL;
		gbcTextFieldMatchLinks.gridx = 1;
		gbcTextFieldMatchLinks.gridy = 3;
		add(mTextFieldMatchLinks, gbcTextFieldMatchLinks);
		mTextFieldMatchLinks.setColumns(10);

	}

	public int getMaxDepth() {
		int l = 0;
		try {
			l = Integer.parseInt(String.valueOf(mMaxDepth.getValue()));
		} catch (NumberFormatException err) {
			return l;
		}
		return l;
	}

	public int getMaxSize() {
		int l = 0;
		try {
			l = Integer.parseInt(String.valueOf(mMaxSize.getValue()));
		} catch (NumberFormatException err) {
			return l;
		}
		return l;
	}

	public String getResultFilter() {
		return mTextFieldResultFilter.getText();
	}

	public String getMatchLinks() {
		return mTextFieldMatchLinks.getText();
	}

	public String[] getTerminals() {
		ArrayList<String> arr = new ArrayList<String>();

		if (mChckbxIp.isSelected()) {
			arr.add(mChckbxIp.getText());
		}
		if (mChckbxAr.isSelected()) {
			arr.add(mChckbxAr.getText());
		}
		if (mChckbxMac.isSelected()) {
			arr.add(mChckbxMac.getText());
		}
		if (mChckbxId.isSelected()) {
			arr.add(mChckbxId.getText());
		}
		if (mChckbxDevice.isSelected()) {
			arr.add(mChckbxDevice.getText());
		}

		String[] arrString = new String[arr.size()];

		for (int i = 0; i < arr.size(); i++) {
			arrString[i] = arr.get(i);
		}
		arr = null;
		return arrString;
	}

}
