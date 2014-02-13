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



package de.hshannover.f4.trust.irongui.view.dialog;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

import de.hshannover.f4.trust.irongui.datastructure.IfmapDataType;
import de.hshannover.f4.trust.irongui.datastructure.Metadata;
import de.hshannover.f4.trust.irongui.event.PublisherColorChangedReceiver;
import de.hshannover.f4.trust.irongui.util.ResourceHelper;

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
public class PublisherDialog extends JFrame {

	private static final long serialVersionUID = 4987920837309147302L;
	private final HashMap<String, Color> mPublisher;
	private JPanel publisherPanel;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JPanel defaultPanel;
	private JButton cancelButton;
	public JButton okButton;
	private JPanel jPanel1;
	private Color DEFAULT_IDENTIFIER_COLOR = new Color(153, 153, 255);
	private Color DEFAULT_METADATA_COLOR = new Color(255, 153, 102);
	// private Color DEFAULT_PUBLISHER_COLOR = new Color(200, 153, 102);
	private ArrayList<PublisherColorChangedReceiver> mReceiver = new ArrayList<PublisherColorChangedReceiver>();
	private PublisherDialog mPublisherPanel = this;
	private int CURRENT_PUBLISHER_LINE_NO = 0;

	public PublisherDialog() {
		super();		
		mPublisher = new HashMap<String, Color>();
		initGUI();
	}

	public PublisherDialog(Color ci, Color cm) {
		super();
		mPublisher = new HashMap<String, Color>();
		if (ci != null)
			DEFAULT_IDENTIFIER_COLOR = ci;
		if (cm != null)
			DEFAULT_METADATA_COLOR = cm;
		initGUI();
	}

	public PublisherDialog(HashMap<String, Color> publisher, Color ci, Color cm) {
		this(ci, cm);
		this.addPublisher(publisher);
	}

	public void registerPublisherColorChangedReceiver(
			PublisherColorChangedReceiver rec) {
		this.mReceiver.add(rec);
	}

	private void initGUI() {
		try {
			setIconImage(ResourceHelper.getAppIconImage().getImage());
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			setBackground(new java.awt.Color(255, 255, 255));			
			setTitle("Publishers");
			// setResizable(false);
			getContentPane().setLayout(new BorderLayout(0, 0));
			{
				publisherPanel = new JPanel();
				getContentPane().add(publisherPanel, BorderLayout.CENTER);
				publisherPanel.setBorder(BorderFactory
						.createTitledBorder("Publisher"));
				GridBagLayout gbl_publisherPanel = new GridBagLayout();
				gbl_publisherPanel.rowWeights = new double[] {};
				gbl_publisherPanel.columnWeights = new double[] {};
				publisherPanel.setLayout(gbl_publisherPanel);
				/*
				 * { btnNewButton_2 = new JButton("New button");
				 * GridBagConstraints gbc_btnNewButton_2 = new
				 * GridBagConstraints(); gbc_btnNewButton_2.anchor =
				 * GridBagConstraints.EAST; gbc_btnNewButton_2.insets = new
				 * Insets(0, 0, 5, 5); gbc_btnNewButton_2.gridx = 0;
				 * gbc_btnNewButton_2.gridy = 0;
				 * publisherPanel.add(btnNewButton_2, gbc_btnNewButton_2); } {
				 * btnNewButton_3 = new JButton("New button");
				 * GridBagConstraints gbc_btnNewButton_3 = new
				 * GridBagConstraints(); gbc_btnNewButton_3.anchor =
				 * GridBagConstraints.WEST; gbc_btnNewButton_3.insets = new
				 * Insets(0, 0, 5, 0); gbc_btnNewButton_3.gridx = 1;
				 * gbc_btnNewButton_3.gridy = 0;
				 * publisherPanel.add(btnNewButton_3, gbc_btnNewButton_3); } {
				 * btnNewButton_4 = new JButton("New button");
				 * GridBagConstraints gbc_btnNewButton_4 = new
				 * GridBagConstraints(); gbc_btnNewButton_4.anchor =
				 * GridBagConstraints.EAST; gbc_btnNewButton_4.insets = new
				 * Insets(0, 0, 0, 5); gbc_btnNewButton_4.gridx = 0;
				 * gbc_btnNewButton_4.gridy = 1;
				 * publisherPanel.add(btnNewButton_4, gbc_btnNewButton_4); } {
				 * btnNewButton_5 = new JButton("New button");
				 * GridBagConstraints gbc_btnNewButton_5 = new
				 * GridBagConstraints(); gbc_btnNewButton_5.anchor =
				 * GridBagConstraints.WEST; gbc_btnNewButton_5.gridx = 1;
				 * gbc_btnNewButton_5.gridy = 1;
				 * publisherPanel.add(btnNewButton_5, gbc_btnNewButton_5); }
				 */
			}
			{
				jPanel1 = new JPanel();
				FlowLayout jPanel1Layout = new FlowLayout();
				jPanel1Layout.setAlignment(FlowLayout.RIGHT);
				jPanel1.setLayout(jPanel1Layout);
				getContentPane().add(jPanel1, BorderLayout.SOUTH);
				{
					okButton = new JButton();
					jPanel1.add(okButton);
					okButton.setText("Ok");
				}
				{
					cancelButton = new JButton();
					cancelButton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							mPublisherPanel.setVisible(false);
						}
					});
					jPanel1.add(cancelButton);
					cancelButton.setText("Cancel");
				}
			}
			{
				defaultPanel = new JPanel();
				defaultPanel.setBorder(new TitledBorder(UIManager
						.getBorder("TitledBorder.border"), "Default",
						TitledBorder.LEADING, TitledBorder.TOP, null,
						new Color(0, 0, 0)));
				getContentPane().add(defaultPanel, BorderLayout.NORTH);
				GridBagLayout gbl_defaultPanel = new GridBagLayout();
				gbl_defaultPanel.columnWeights = new double[] { 0.0, 0.0 };

				defaultPanel.setLayout(gbl_defaultPanel);
				{
					jLabel1 = new JLabel();
					GridBagConstraints gbc_jLabel1 = new GridBagConstraints();
					gbc_jLabel1.anchor = GridBagConstraints.EAST;
					gbc_jLabel1.insets = new Insets(0, 0, 5, 5);
					gbc_jLabel1.gridx = 0;
					gbc_jLabel1.gridy = 0;
					defaultPanel.add(jLabel1, gbc_jLabel1);
					jLabel1.setText("Identifier");
				}
				{
					final ColorButton but = new ColorButton(
							DEFAULT_IDENTIFIER_COLOR, 64, 32);
					GridBagConstraints gbc_but = new GridBagConstraints();
					gbc_but.anchor = GridBagConstraints.WEST;
					gbc_but.insets = new Insets(0, 0, 5, 5);
					gbc_but.gridx = 1;
					gbc_but.gridy = 0;
					defaultPanel.add(but, gbc_but);
					but.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							Color newColor = JColorChooser.showDialog(but,
									"Choose Color", DEFAULT_IDENTIFIER_COLOR);
							if (newColor != null) {
								but.setColor(newColor);
								DEFAULT_IDENTIFIER_COLOR = newColor;
							}
						}
					});
				}
				{
					jLabel2 = new JLabel();
					GridBagConstraints gbc_jLabel2 = new GridBagConstraints();
					gbc_jLabel2.anchor = GridBagConstraints.EAST;
					gbc_jLabel2.insets = new Insets(0, 0, 0, 5);
					gbc_jLabel2.gridx = 0;
					gbc_jLabel2.gridy = 1;
					defaultPanel.add(jLabel2, gbc_jLabel2);
					jLabel2.setText("Metadata");
				}
				{
					final ColorButton but2 = new ColorButton(
							DEFAULT_METADATA_COLOR, 64, 32);
					GridBagConstraints gbc_but2 = new GridBagConstraints();
					gbc_but2.anchor = GridBagConstraints.WEST;
					gbc_but2.insets = new Insets(0, 0, 0, 5);
					gbc_but2.gridx = 1;
					gbc_but2.gridy = 1;
					defaultPanel.add(but2, gbc_but2);
					but2.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							Color newColor = JColorChooser.showDialog(but2,
									"Choose Color", DEFAULT_METADATA_COLOR);
							if (newColor != null) {
								but2.setColor(newColor);
								DEFAULT_METADATA_COLOR = newColor;
							}
						}
					});
				}
			}
			this.pack();
			this.repaint();
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	public HashMap<String, Color> getPublisher() {
		return mPublisher;
	}

	public Color getDefaultIdentifierColor() {
		return DEFAULT_IDENTIFIER_COLOR;
	}

	public Color getDefaultMetadataColor() {
		return DEFAULT_METADATA_COLOR;
	}

	private void addToPanel(final String publisher, final Color c) {
		JLabel label = new JLabel(publisher);
		final ColorButton but = new ColorButton(c, 64, 32);
		but.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Color newColor = JColorChooser.showDialog(but, "Choose Color",
						c);
				if (newColor != null) {
					mPublisher.put(publisher, newColor);
					but.setColor(newColor);
				}
			}
		});

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.EAST;
		// gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = 1;
		gbc.gridy = CURRENT_PUBLISHER_LINE_NO;
		publisherPanel.add(label, gbc);

		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.anchor = GridBagConstraints.WEST;
		// gbc2.insets = new Insets(0, 0, 5, 0);
		gbc2.gridx = 2;
		gbc2.gridy = CURRENT_PUBLISHER_LINE_NO;
		CURRENT_PUBLISHER_LINE_NO++;
		publisherPanel.add(but, gbc2);
	}

	public void addPublisher(HashMap<String, Color> publisher) {
		for (String key : publisher.keySet()) {
			if (!this.mPublisher.containsKey(key)) {
				this.mPublisher.put(key, publisher.get(key));
				this.addToPanel(key, publisher.get(key));
			}
		}
		this.pack();
		this.repaint();
	}

	public void processNewPollResult(ArrayList<IfmapDataType> newData,
			ArrayList<IfmapDataType> updateData,
			ArrayList<IfmapDataType> deleteData) {
		Set<String> uniquePublishers = new HashSet<String>();

		if (newData != null) {
			uniquePublishers.addAll(extractPublishersFromData(newData));
		}

		if (updateData != null) {			
			uniquePublishers.addAll(extractPublishersFromData(updateData));
		}

		if (uniquePublishers.size() > 0) {
			updatePublisherColors(uniquePublishers);
		}
	}

	/**
	 * Helper to do whatever is done here...
	 */
	private void updatePublisherColors(Set<String> uniquePublishers) {

		for (String p : uniquePublishers) {
			Color c = mPublisher.get(p);
			if (c == null) {
				mPublisher.put(p, DEFAULT_METADATA_COLOR);
				addToPanel(p, DEFAULT_METADATA_COLOR);
			}
		}

		for (PublisherColorChangedReceiver rec : mReceiver) {
			rec.setNewColorSelection(mPublisher, DEFAULT_IDENTIFIER_COLOR,
					DEFAULT_METADATA_COLOR);
		}

		this.pack();
		this.repaint();
	}

	/**
	 * Helper to get a unique set of publisher-ids.from data.
	 */
	private Set<String> extractPublishersFromData(Collection<IfmapDataType> data) {
		Set<String> ret = new HashSet<String>();

		for (IfmapDataType i : data)
			for (Metadata m : i.getMetadata())
				ret.add(m.getPublisher()); // it's a set!

		return ret;
	}
}

@SuppressWarnings("serial")
class ColorButton extends JButton {

	private Color mColor;

	public ColorButton() {
		super();
	}

	ColorButton(Color color) {
		super();
		mColor = color;
	}

	ColorButton(Color color, int width, int heigth) {
		super();
		mColor = color;
		Dimension d = new Dimension(width, heigth);
		setSize(d);
		setPreferredSize(d);
		setFocusable(false);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(mColor);
		g.fillRect(10, 10, this.getWidth() - 20, this.getHeight() - 20);
	}

	public void setColor(Color c) {
		mColor = c;
		revalidate();
	}
}
