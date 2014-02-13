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



package de.hshannover.f4.trust.irongui.view.component;




import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import prefuse.visual.VisualItem;

import de.hshannover.f4.trust.irongui.datastructure.IdentifierData;
import de.hshannover.f4.trust.irongui.datastructure.Metadata;
import de.hshannover.f4.trust.irongui.event.NodeSelectedReceiver;

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
