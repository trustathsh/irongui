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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicButtonUI;

@SuppressWarnings("serial")
public class CustomTabComponent extends JPanel {
	private JTabbedPane mPane;

	public CustomTabComponent(String name, JTabbedPane pane) {
		super(new FlowLayout(FlowLayout.LEFT, 5, 1));
		mPane = pane;
		setOpaque(false);
		this.add(new JLabel(new ImageIcon(getClass().getClassLoader()
				.getResource("tabConnect.png"))));
		this.add(new JLabel(name));
		CustomButton button = new CustomButton();
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int i = mPane.indexOfTabComponent(CustomTabComponent.this);
				System.err.println(i);
				if (i != -1) {
					mPane.remove(i);
				}
			}
		});
		this.add(button);
	}

	private class CustomButton extends JButton {
		public CustomButton() {
			setPreferredSize(new Dimension(16, 16));
			setToolTipText("close this tab");
			setUI(new BasicButtonUI());
			setContentAreaFilled(false);
			setFocusable(false);
			setRolloverEnabled(true);
		}

		public void updateUI() {
		}

		// paint the cross
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setStroke(new BasicStroke(2));

			g2.setColor(Color.BLACK);

			if (getModel().isPressed()) {
				g2.setColor(Color.RED);
			}

			g2.drawLine(5, 5, getHeight() - 5, getWidth() - 5);
			g2.drawLine(5, getHeight() - 5, getWidth() - 5, 5);

			if (getModel().isRollover()) {
				g2.drawRect(2, 2, g2.getClipBounds().width - 3,
						g2.getClipBounds().height - 3);
			}

			g2.dispose();
		}
	}

}
