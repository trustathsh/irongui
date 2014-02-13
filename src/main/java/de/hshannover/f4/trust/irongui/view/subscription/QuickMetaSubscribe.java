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
	public JTextField textFieldResultFilter;
	public JTextField textFieldMatchLinks;
	private JSpinner maxDepth;
	private JSpinner maxSize;
	private JCheckBox chckbxAr;
	private JCheckBox chckbxId;
	private JCheckBox chckbxIp;
	private JCheckBox chckbxDevice;
	private JCheckBox chckbxMac;
	
	/**
	 * Create the panel.
	 */
	public QuickMetaSubscribe() {
		setAlignmentY(2.0f);
		setAlignmentX(2.0f);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 29, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblTerminals = new JLabel("terminals");
		GridBagConstraints gbc_lblTerminals = new GridBagConstraints();
		gbc_lblTerminals.anchor = GridBagConstraints.EAST;
		gbc_lblTerminals.insets = new Insets(0, 0, 5, 5);
		gbc_lblTerminals.gridx = 0;
		gbc_lblTerminals.gridy = 0;
		add(lblTerminals, gbc_lblTerminals);
		
		chckbxMac = new JCheckBox("mac");
		GridBagConstraints gbc_chckbxMac = new GridBagConstraints();
		gbc_chckbxMac.anchor = GridBagConstraints.WEST;
		gbc_chckbxMac.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxMac.gridx = 1;
		gbc_chckbxMac.gridy = 0;
		add(chckbxMac, gbc_chckbxMac);
		
		chckbxDevice = new JCheckBox("dev");
		GridBagConstraints gbc_chckbxDevice = new GridBagConstraints();
		gbc_chckbxDevice.anchor = GridBagConstraints.WEST;
		gbc_chckbxDevice.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxDevice.gridx = 2;
		gbc_chckbxDevice.gridy = 0;
		add(chckbxDevice, gbc_chckbxDevice);
		
		chckbxIp = new JCheckBox("ip");
		GridBagConstraints gbc_chckbxIp = new GridBagConstraints();
		gbc_chckbxIp.anchor = GridBagConstraints.WEST;
		gbc_chckbxIp.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxIp.gridx = 3;
		gbc_chckbxIp.gridy = 0;
		add(chckbxIp, gbc_chckbxIp);
		
		chckbxId = new JCheckBox("id");
		GridBagConstraints gbc_chckbxId = new GridBagConstraints();
		gbc_chckbxId.anchor = GridBagConstraints.WEST;
		gbc_chckbxId.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxId.gridx = 4;
		gbc_chckbxId.gridy = 0;
		add(chckbxId, gbc_chckbxId);
		
		chckbxAr = new JCheckBox("ar");
		GridBagConstraints gbc_chckbxAr = new GridBagConstraints();
		gbc_chckbxAr.anchor = GridBagConstraints.WEST;
		gbc_chckbxAr.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxAr.gridx = 5;
		gbc_chckbxAr.gridy = 0;
		add(chckbxAr, gbc_chckbxAr);
		
		JLabel lblMaxdepth = new JLabel("max-depth");
		GridBagConstraints gbc_lblMaxdepth = new GridBagConstraints();
		gbc_lblMaxdepth.anchor = GridBagConstraints.EAST;
		gbc_lblMaxdepth.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaxdepth.gridx = 0;
		gbc_lblMaxdepth.gridy = 1;
		add(lblMaxdepth, gbc_lblMaxdepth);
		
		maxDepth = new JSpinner();
		maxDepth.setModel(new SpinnerNumberModel(new Integer(8), null, null, new Integer(1)));
		GridBagConstraints gbc_maxDepth = new GridBagConstraints();
		gbc_maxDepth.fill = GridBagConstraints.HORIZONTAL;
		gbc_maxDepth.insets = new Insets(0, 0, 5, 5);
		gbc_maxDepth.gridx = 1;
		gbc_maxDepth.gridy = 1;
		add(maxDepth, gbc_maxDepth);
		
		JLabel lblMaxsize = new JLabel("max-size");
		GridBagConstraints gbc_lblMaxsize = new GridBagConstraints();
		gbc_lblMaxsize.anchor = GridBagConstraints.EAST;
		gbc_lblMaxsize.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaxsize.gridx = 2;
		gbc_lblMaxsize.gridy = 1;
		add(lblMaxsize, gbc_lblMaxsize);
		
		maxSize = new JSpinner();
		maxSize.setModel(new SpinnerNumberModel(new Integer(64384), null, null, new Integer(1)));
		GridBagConstraints gbc_maxSize = new GridBagConstraints();
		gbc_maxSize.fill = GridBagConstraints.HORIZONTAL;
		gbc_maxSize.gridwidth = 2;
		gbc_maxSize.insets = new Insets(0, 0, 5, 5);
		gbc_maxSize.gridx = 3;
		gbc_maxSize.gridy = 1;
		add(maxSize, gbc_maxSize);
		
		JLabel lblResultfilter = new JLabel("result-filter");
		GridBagConstraints gbc_lblResultfilter = new GridBagConstraints();
		gbc_lblResultfilter.anchor = GridBagConstraints.EAST;
		gbc_lblResultfilter.insets = new Insets(0, 0, 5, 5);
		gbc_lblResultfilter.gridx = 0;
		gbc_lblResultfilter.gridy = 2;
		add(lblResultfilter, gbc_lblResultfilter);
		
		textFieldResultFilter = new JTextField();
		GridBagConstraints gbc_textFieldResultFilter = new GridBagConstraints();
		gbc_textFieldResultFilter.gridwidth = 5;
		gbc_textFieldResultFilter.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldResultFilter.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldResultFilter.gridx = 1;
		gbc_textFieldResultFilter.gridy = 2;
		add(textFieldResultFilter, gbc_textFieldResultFilter);
		textFieldResultFilter.setColumns(10);
		
		JLabel lblMatchlinks = new JLabel("match-links");
		GridBagConstraints gbc_lblMatchlinks = new GridBagConstraints();
		gbc_lblMatchlinks.anchor = GridBagConstraints.EAST;
		gbc_lblMatchlinks.insets = new Insets(0, 0, 0, 5);
		gbc_lblMatchlinks.gridx = 0;
		gbc_lblMatchlinks.gridy = 3;
		add(lblMatchlinks, gbc_lblMatchlinks);
		
		textFieldMatchLinks = new JTextField();
		GridBagConstraints gbc_textFieldMatchLinks = new GridBagConstraints();
		gbc_textFieldMatchLinks.gridwidth = 5;
		gbc_textFieldMatchLinks.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldMatchLinks.gridx = 1;
		gbc_textFieldMatchLinks.gridy = 3;
		add(textFieldMatchLinks, gbc_textFieldMatchLinks);
		textFieldMatchLinks.setColumns(10);

	}
	
	public int getMaxDepth(){
		int l = 0;
		try{
			l = Integer.parseInt(String.valueOf(maxDepth.getValue()));
		}
		catch(NumberFormatException err){
			return l;
		}
		return l;
	}
	
	public int getMaxSize(){
		int l = 0;
		try{
			l = Integer.parseInt(String.valueOf(maxSize.getValue()));
		}
		catch(NumberFormatException err){
			return l;
		}
		return l;
	}
	
	public String getResultFilter(){
		return textFieldResultFilter.getText();
	}
	
	public String getMatchLinks(){
		return textFieldMatchLinks.getText();
	}
	
	public String[] getTerminals(){
		ArrayList<String> arr = new ArrayList<String>();
		
		if(chckbxIp.isSelected())
			arr.add(chckbxIp.getText());
		if(chckbxAr.isSelected())
			arr.add(chckbxAr.getText());
		if(chckbxMac.isSelected())
			arr.add(chckbxMac.getText());
		if(chckbxId.isSelected())
			arr.add(chckbxId.getText());
		if(chckbxDevice.isSelected())
			arr.add(chckbxDevice.getText());
		
		String[] arrString = new String[arr.size()];
		
		for(int i = 0; i < arr.size(); i++){
			arrString[i] = arr.get(i);
		}
		arr = null;
		return arrString;		
	}

}
