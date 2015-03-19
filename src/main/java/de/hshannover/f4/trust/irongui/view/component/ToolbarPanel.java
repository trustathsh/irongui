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
 * This file is part of irongui, version 0.4.8,
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

package de.hshannover.f4.trust.irongui.view.component;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import de.hshannover.f4.trust.irongui.util.ResourceHelper;

public class ToolbarPanel extends JToolBar {
	private static final long serialVersionUID = 2814752100004120354L;

	public JButton mButtonConnections;
	public JButton mButtonPublisher;
	public JToggleButton mButtonAnimation;

	private JLabel mLabelIron;
	private JLabel mLabelEsukom;
	private JLabel mLabelTrust;

	public ToolbarPanel() {

		mButtonConnections = new JButton("");
		add(mButtonConnections);
		mButtonConnections.setToolTipText("Manage IF-MAP connections");
		mButtonConnections.setFocusable(false);
		mButtonConnections.setIcon(ResourceHelper.getImage("computer_add.png"));

		mButtonPublisher = new JButton("");
		add(mButtonPublisher);
		mButtonPublisher
				.setToolTipText("Show known publisher and change their colors");
		mButtonPublisher.setFocusable(false);
		mButtonPublisher
				.setIcon(ResourceHelper.getImage("color_management.png"));

		mButtonAnimation = new JToggleButton("");
		add(mButtonAnimation);
		mButtonAnimation.setToolTipText("Start / stop the animation");
		mButtonAnimation.setFocusable(false);
		mButtonAnimation.setIcon(ResourceHelper.getImage("stop.png"));

		add(Box.createHorizontalGlue());

		mLabelTrust = new JLabel("");
		add(mLabelTrust);
		mLabelTrust.setHorizontalAlignment(SwingConstants.RIGHT);
		mLabelTrust.setIcon(ResourceHelper.getImage("logo_trustathsh_f4.png"));
		mLabelTrust.setToolTipText("Visit the Trust@HsH Website!");
		mLabelTrust.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent evt) {
				mLabelTrust.setCursor(Cursor
						.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}

			public void mouseEntered(MouseEvent evt) {
				mLabelTrust.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseClicked(MouseEvent evt) {
				de.hshannover.f4.trust.irongui.util.AppLauncher
						.openWebpageInBrowser("http://trust.f4.hs-hannover.de");
			}
		});

		mLabelIron = new JLabel("");
		add(mLabelIron);
		mLabelIron.setIcon(ResourceHelper.getImage("logo_iron.png"));
		mLabelIron.setHorizontalAlignment(SwingConstants.RIGHT);
		mLabelIron.setToolTipText("More interesting IFMAP-stuff");
		mLabelIron.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent evt) {
				mLabelIron.setCursor(Cursor
						.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}

			public void mouseEntered(MouseEvent evt) {
				mLabelIron.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseClicked(MouseEvent evt) {
				de.hshannover.f4.trust.irongui.util.AppLauncher
						.openWebpageInBrowser("http://trust.f4.hs-hannover.de/projects/iron.html");
			}
		});

		mLabelEsukom = new JLabel("");
		add(mLabelEsukom);
		mLabelEsukom.setHorizontalAlignment(SwingConstants.RIGHT);
		mLabelEsukom.setIcon(ResourceHelper.getImage("esukom_32x32.png"));
		mLabelEsukom.setToolTipText("ESUKOM Project-Homepage");
		mLabelEsukom.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent evt) {
				mLabelEsukom.setCursor(Cursor
						.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}

			public void mouseEntered(MouseEvent evt) {
				mLabelEsukom.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseClicked(MouseEvent evt) {
				de.hshannover.f4.trust.irongui.util.AppLauncher
						.openWebpageInBrowser("http://www.esukom.de/");
			}
		});
	}
}
