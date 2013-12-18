package de.hshannover.f4.trust.irongui.view.component;




import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;

import de.hshannover.f4.trust.irongui.util.ResourceHelper;
import de.hshannover.f4.trust.irongui.view.navigation.TreePanel;
import javax.swing.JToolBar;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class NavigationPanel extends JPanel {
	private static final long serialVersionUID = 4248002471880371362L;
	public JToggleButton buttonConnections;
	public JToggleButton buttonTree;
	public JList panelList;
	public TreePanel panelTree;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JScrollPane scrollPane = new JScrollPane();
	private JPanel panelBottom;
	private JToolBar actionPanel;
	public  JToggleButton buttonPlay;
	public JToggleButton buttonSubscribe;
	public NavigationPanel() {
		setPreferredSize(new Dimension(151, 433));
		setLayout(new BorderLayout(0, 0));		
		
		JToolBar panelTop = new JToolBar();
		panelTop.setFloatable(false);
		add(panelTop, BorderLayout.NORTH);				
		
		buttonConnections = new JToggleButton();
		buttonConnections.setFocusable(false);
		buttonConnections.setSelected(true);
		buttonGroup.add(buttonConnections);
		//buttonConnections.setPreferredSize(new Dimension(32, 32));
		buttonConnections.setIcon(ResourceHelper.getImage("server_client.png"));
		panelTop.add(buttonConnections);
		
		buttonTree = new JToggleButton();		
		buttonTree.setFocusable(false);
		buttonGroup.add(buttonTree);
		//buttonTree.setPreferredSize(new Dimension(32, 32));
		buttonTree.setIcon(ResourceHelper.getImage("node-tree_24x24.png"));
		panelTop.add(buttonTree);
		
		panelBottom = new JPanel();
		panelBottom.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panelBottom, BorderLayout.CENTER);
		panelBottom.setLayout(new BorderLayout(0, 0));
		
		actionPanel = new JToolBar();
		actionPanel.setFloatable(false);
		panelBottom.add(actionPanel, BorderLayout.NORTH);
		
		buttonPlay = new JToggleButton();		
		buttonPlay.setToolTipText("Start session with current selection");
		buttonPlay.setFocusable(false);
		buttonPlay.setIcon(ResourceHelper.getImage("control_play_blue.png"));
		buttonPlay.setSelectedIcon(ResourceHelper.getImage("control_stop_blue.png"));
		actionPanel.add(buttonPlay);
		
		buttonSubscribe = new JToggleButton();
		buttonSubscribe.setToolTipText("Subscribe to metadata...");
		buttonSubscribe.setFocusable(false);
		buttonSubscribe.setIcon(ResourceHelper.getImage("feed_add_16x16.png"));
		actionPanel.add(buttonSubscribe);
		
		panelList = new JList();
		panelList.setModel(new DefaultListModel());
		panelList.setCellRenderer(new ConnectionListRenderer());
		panelList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelBottom.add(scrollPane);
		scrollPane.setViewportView(panelList);
		
		panelTree = new TreePanel();
	}
	
	public void removeView(Component c) {
		scrollPane.getViewport().remove(c);
	}
	
	public void addView(Component c) {
		scrollPane.setViewportView(c);
	}
}
