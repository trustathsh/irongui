package de.fhhannover.inform.ifmap.view.component;

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


import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import prefuse.visual.VisualItem;

import de.fhhannover.inform.ifmap.datastructure.IdentifierData;
import de.fhhannover.inform.ifmap.datastructure.Metadata;
import de.fhhannover.inform.ifmap.event.NodeSelectedReceiver;

import java.awt.BorderLayout;

public class MetadataPanel extends JPanel implements NodeSelectedReceiver {
	private static final long serialVersionUID = 1999982246037981208L;
	
	private JPanel ifmapMetadata;
	public JTable table;
	public JLabel label;
	
	public MetadataPanel() {
		setPreferredSize(new Dimension(getWidth(), 150));
		setBackground(new java.awt.Color(255, 255, 255));
		setLayout(new BorderLayout());		
		label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setHorizontalTextPosition(SwingConstants.LEFT);
		//label.setPreferredSize(new Dimension(getWidth(), 20));
		
		ifmapMetadata = new JPanel();
		ifmapMetadata.setPreferredSize(new Dimension(100, 24));
		ifmapMetadata.setLayout(new FlowLayout(FlowLayout.LEFT));
		ifmapMetadata.add(label);		
		add(ifmapMetadata, BorderLayout.NORTH);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new java.awt.Color(255, 255, 255));				
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setBackground(new java.awt.Color(255, 255, 255));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
		add(scrollPane, BorderLayout.CENTER);
	}

	@Override
	public void nodeSelected(VisualItem item) {
		StringBuffer buf = new StringBuffer();
		if (item.get("object") instanceof IdentifierData) {
			IdentifierData ident = (IdentifierData) item.get("object");
			buf.append("<html><strong>");
			buf.append(ident.getName());			
			buf.append("</strong></html>");
			label.setText(buf.toString());
		} else if (item.get("object") instanceof Metadata) {
			Metadata meta = (Metadata) item.get("object");
			buf.append("<html><strong>");
			buf.append(meta.getName());
			buf.append("</strong> [ <i>ifmap-publisher</i>: <strong>");
			buf.append(meta.getPublisher());
			buf.append("</strong>   <i>ifmap-timestamp</i>: <strong>");
			buf.append(meta.getTimestamp());
			buf.append("</strong>   <i>ifmap-cardinality</i>: <strong>");
			buf.append(meta.getCardinality());
			buf.append("</strong> ]</html>");
			label.setText(buf.toString());
		}		
	}	
}
