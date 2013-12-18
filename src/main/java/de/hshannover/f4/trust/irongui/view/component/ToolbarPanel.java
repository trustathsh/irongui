package de.hshannover.f4.trust.irongui.view.component;




import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import de.hshannover.f4.trust.irongui.util.ResourceHelper;

public class ToolbarPanel extends JToolBar {
	private static final long serialVersionUID = 2814752100004120354L;
	
	public JButton buttonConnections;
	public JButton buttonPublisher;
	public JToggleButton buttonAnimation;
	
	private JLabel labelIron;
	private JLabel labelEsukom;
	private JLabel labelTrust;		
	
	public ToolbarPanel() {
		
		buttonConnections = new JButton("");		
		add(buttonConnections);
		buttonConnections.setToolTipText("Manage IF-MAP connections");
		buttonConnections.setFocusable(false);
		buttonConnections.setIcon(ResourceHelper.getImage("computer_add.png"));
		
		buttonPublisher = new JButton("");
		add(buttonPublisher);
		buttonPublisher.setToolTipText("Show known publisher and change their colors");
		buttonPublisher.setFocusable(false);
		buttonPublisher.setIcon(ResourceHelper.getImage("color_management.png"));
		
		buttonAnimation = new JToggleButton("");
		add(buttonAnimation);
		buttonAnimation.setToolTipText("Start / stop the animation");
		buttonAnimation.setFocusable(false);
		buttonAnimation.setIcon(ResourceHelper.getImage("stop.png"));
		
		add(Box.createHorizontalGlue());
		
		labelTrust = new JLabel("");
		add(labelTrust);
		labelTrust.setHorizontalAlignment(SwingConstants.RIGHT);
		labelTrust.setIcon(ResourceHelper.getImage("logo_trustathsh_f4.png"));
		labelTrust.setToolTipText("Visit the Trust@HsH Website!");
		labelTrust.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent evt) {
				labelTrust.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			public void mouseEntered(MouseEvent evt) {
				labelTrust.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			public void mouseClicked(MouseEvent evt) {
				de.hshannover.f4.trust.irongui.util.AppLauncher.openWebpageInBrowser("http://trust.f4.hs-hannover.de");
			}
		});
		
		labelIron = new JLabel("");
		add(labelIron);
		labelIron.setIcon(ResourceHelper.getImage("logo_iron.png"));
		labelIron.setHorizontalAlignment(SwingConstants.RIGHT);
		labelIron.setToolTipText("More interesting IFMAP-stuff");
		labelIron.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent evt) {
				labelIron.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			public void mouseEntered(MouseEvent evt) {
				labelIron.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			public void mouseClicked(MouseEvent evt) {
				de.hshannover.f4.trust.irongui.util.AppLauncher.openWebpageInBrowser("http://trust.f4.hs-hannover.de/projects/iron.html");
			}
		});
		
		labelEsukom = new JLabel("");
		add(labelEsukom);
		labelEsukom.setHorizontalAlignment(SwingConstants.RIGHT);
		labelEsukom.setIcon(ResourceHelper.getImage("esukom_32x32.png"));
		labelEsukom.setToolTipText("ESUKOM Project-Homepage");
		labelEsukom.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent evt) {
				labelEsukom.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			public void mouseEntered(MouseEvent evt) {
				labelEsukom.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			public void mouseClicked(MouseEvent evt) {
				de.hshannover.f4.trust.irongui.util.AppLauncher.openWebpageInBrowser("http://www.esukom.de/");
			}
		});
	}
}
