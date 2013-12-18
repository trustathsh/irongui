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
