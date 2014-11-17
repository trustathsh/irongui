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
 * This file is part of irongui, version 0.4.5,
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



package de.hshannover.f4.trust.irongui.view.navigation;




import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import de.hshannover.f4.trust.irongui.datastructure.IdentifierConstants.IdentifierType;
import de.hshannover.f4.trust.irongui.datastructure.IdentifierData;
import de.hshannover.f4.trust.irongui.datastructure.IfmapDataType;
import de.hshannover.f4.trust.irongui.datastructure.Link;
import de.hshannover.f4.trust.irongui.datastructure.Metadata;
import de.hshannover.f4.trust.irongui.view.component.GraphPanel;

public class TreePanel extends JTree implements TreeSelectionListener {

	private static final long serialVersionUID = -7542566619876278915L;
	private DefaultTreeModel mModel;
	private HashMap<IdentifierData, DefaultMutableTreeNode> ident2node;
	private GraphPanel mPrefuse;
	private JScrollPane scrollPane = new JScrollPane();

	public TreePanel() {
		this(null);
	}

	public TreePanel(GraphPanel prefuse) {
		super();
		mPrefuse = prefuse;
		ident2node = new HashMap<IdentifierData, DefaultMutableTreeNode>();
		mModel = new JTreeModel().getDefaultTreeModel();
		this.setModel(mModel);
		this.setBackground(Color.WHITE);
		this.setRootVisible(false);
		this.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		this.addTreeSelectionListener(this);
		scrollPane.add(this);
	}

	public void processNewPollResult(ArrayList<IfmapDataType> newData,
			ArrayList<IfmapDataType> updateData,
			ArrayList<IfmapDataType> deleteData) {
		if (newData != null) {
			for (IfmapDataType i : newData)
				this.addNode(i);
		}
		if (updateData != null) {
			for (IfmapDataType j : updateData)
				this.addNode(j);
		}
		if (deleteData != null) {			
			for (IfmapDataType m : deleteData){
				this.removeNode(m);
			}
			this.clearSelection();
		}
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				updateUI();
			}
		});
	}

	private void addNode(IfmapDataType ifmap) {
		if (ifmap instanceof IdentifierData) {
			addIdentifier((IdentifierData) ifmap);
		} else if (ifmap instanceof Link) {
			addLink((Link) ifmap);
		}
	}

	private void addIdentifier(IdentifierData identifier) {
		if (identifier != null) {
			IdentifierType type = identifier.getType();
			DefaultMutableTreeNode root = (DefaultMutableTreeNode) mModel
					.getRoot();
			int size = root.getChildCount();
			for (int i = 0; i < size; i++) {
				DefaultMutableTreeNode identNode = (DefaultMutableTreeNode) root
						.getChildAt(i);
				// IP-ADDRESS, MAC-ADDRESS, IDENTITY, ACCESS-REQUEST, DEVICE
				IdentifierType nodeType = (IdentifierType) identNode
						.getUserObject();
				if (type.equals(nodeType)) {
					DefaultMutableTreeNode treeNode = ident2node
							.get(identifier);
					if (treeNode == null) {
						treeNode = new DefaultMutableTreeNode(identifier);
						ident2node.put(identifier, treeNode);
					}

					// treeNode.setParent(identNode);
					int s = identNode.getChildCount();
					boolean add = true;
					DefaultMutableTreeNode child = null;
					for (int j = 0; j < s; j++) {
						DefaultMutableTreeNode node = (DefaultMutableTreeNode) identNode
								.getChildAt(j);
						if (node.getUserObject().toString()
								.equals(treeNode.getUserObject().toString())) {
							add = false;
							child = node;
						}
					}
					if (add) {
						identNode.add(treeNode);
						child = treeNode;
					}

					ArrayList<Metadata> metadata = identifier.getMetadata();
					for (Metadata meta : metadata) {
						DefaultMutableTreeNode cnode = new DefaultMutableTreeNode(
								meta, false);
						int count = child.getChildCount();
						boolean addMeta = true;
						for (int l = 0; l < count; l++) {
							DefaultMutableTreeNode node = (DefaultMutableTreeNode) child
									.getChildAt(l);
							if (node.getUserObject().equals(
									cnode.getUserObject())) {
								addMeta = false;

							}
						}
						if (addMeta) {
							child.add(cnode);
						}
					}
				}
			}
		}
	}

	private void addLink(Link link) {
		IdentifierData i1 = link.getIdentifier1();
		IdentifierData i2 = link.getIdentifier2();
		this.addIdentifier(i1);
		this.addIdentifier(i2);
		DefaultMutableTreeNode i1node = ident2node.get(i1);
		DefaultMutableTreeNode i2node = ident2node.get(i2);

		ArrayList<Metadata> metadata = link.getMetadata();
		for (Metadata meta : metadata) {
			DefaultMutableTreeNode cnode = new DefaultMutableTreeNode(meta,
					false);
			DefaultMutableTreeNode cnode2 = new DefaultMutableTreeNode(meta,
					false);
			// Identifier 1
			int count = i1node.getChildCount();
			boolean addMeta = true;
			for (int i = 0; i < count; i++) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) i1node
						.getChildAt(i);
				if (node.getUserObject().equals(cnode.getUserObject())) {
					addMeta = false;
				}
			}
			if (addMeta) {
				i1node.add(cnode);
			}

			// Identifier 2
			int count2 = i2node.getChildCount();
			boolean addMeta2 = true;
			for (int l = 0; l < count2; l++) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) i2node
						.getChildAt(l);
				if (node.getUserObject().equals(cnode2.getUserObject())) {
					addMeta2 = false;
				}
			}
			if (addMeta2) {
				i2node.add(cnode2);
			}
		}

	}

	public void setPrefuse(GraphPanel mPrefuse) {
		this.mPrefuse = mPrefuse;
	}

	private void removeNode(IfmapDataType ifmap) {
		if (ifmap instanceof IdentifierData) {
			removeIdentifier((IdentifierData) ifmap);
		} else if (ifmap instanceof Link) {
			removeLink((Link) ifmap);
		}
	}

	private void removeLink(Link ifmap) {
		removeIdentifier(ifmap.getIdentifier1());
		removeIdentifier(ifmap.getIdentifier2());
		DefaultMutableTreeNode node = ident2node.get(ifmap.getIdentifier1());
		DefaultMutableTreeNode node2 = ident2node.get(ifmap.getIdentifier2());
		ArrayList<Metadata> meta = ifmap.getMetadata();
		if (meta != null && !meta.isEmpty()) {
			if (node != null) {
				int count = node.getChildCount();
				ArrayList<DefaultMutableTreeNode> nodes = new ArrayList<DefaultMutableTreeNode>();
				for (Metadata m : meta) {
					for (int l = 0; l < count; l++) {
						DefaultMutableTreeNode n = (DefaultMutableTreeNode) node
								.getChildAt(l);
						if (n.getUserObject().equals(m)) {
							nodes.add(n);
						}
					}
				}

				for (DefaultMutableTreeNode ns : nodes) {
					node.remove(ns);
				}

				if (node.getChildCount() == 0) {
					node.removeFromParent();
					ident2node.remove(ifmap.getIdentifier1());
				}
			}

			if (node2 != null) {
				int count2 = node2.getChildCount();
				ArrayList<DefaultMutableTreeNode> nodes2 = new ArrayList<DefaultMutableTreeNode>();
				for (Metadata m : meta) {
					for (int l = 0; l < count2; l++) {
						DefaultMutableTreeNode n = (DefaultMutableTreeNode) node2
								.getChildAt(l);
						if (n.getUserObject().equals(m)) {
							nodes2.add(n);
						}
					}
				}

				for (DefaultMutableTreeNode ns : nodes2) {
					node2.remove(ns);
				}

				if (node2.getChildCount() == 0) {
					node2.removeFromParent();
					ident2node.remove(ifmap.getIdentifier2());
				}
			}
		}
	}

	private void removeIdentifier(IdentifierData ifmap) {
		if (ifmap != null) {
			DefaultMutableTreeNode node = ident2node.get(ifmap);
			if (node != null) {
				ArrayList<Metadata> meta = ifmap.getMetadata();
				if (meta != null && !meta.isEmpty()) {
					int count = node.getChildCount();
					ArrayList<DefaultMutableTreeNode> nodes = new ArrayList<DefaultMutableTreeNode>();
					for (Metadata m : meta) {
						for (int l = 0; l < count; l++) {
							DefaultMutableTreeNode n = (DefaultMutableTreeNode) node
									.getChildAt(l);
							if (n.getUserObject().equals(m)) {
								nodes.add(n);
							}
						}
					}
					for (DefaultMutableTreeNode ns : nodes) {
						node.remove(ns);
					}
				}
				/*
				 * if(node.getChildCount() == 0){ MutableTreeNode parent =
				 * (MutableTreeNode)node.getParent(); parent.remove(node); }
				 */
			}
		}
	}

	public void removeAllNodes() {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)mModel.getRoot();		
		int size = node.getChildCount();
		for (int i = 0; i < size; i++) {
			DefaultMutableTreeNode identNode = (DefaultMutableTreeNode) node
					.getChildAt(i);
			identNode.removeAllChildren();
		}
		mModel.reload();
		ident2node.clear();
	}
	
	@Override
	public String convertValueToText(Object value, boolean selected,
			boolean expanded, boolean leaf, int row, boolean hasFocus) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		if (node.getUserObject() instanceof Metadata) {
			Metadata meta = (Metadata) node.getUserObject();
			return super.convertValueToText(
					meta.getName() + " (" + meta.getTimestamp() + ")",
					selected, expanded, leaf, row, hasFocus);
		} else if (node.getUserObject() instanceof IdentifierData) {
			IdentifierData ident = (IdentifierData) node.getUserObject();
			return super.convertValueToText(ident.getRequestObject(), selected,
					expanded, leaf, row, hasFocus);
		}
		return super.convertValueToText(value, selected, expanded, leaf, row,
				hasFocus);
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) getLastSelectedPathComponent();

		if (node == null)
			return;

		Object nodeObject = node.getUserObject();
		if (nodeObject instanceof IdentifierData) {
			IdentifierData ident = (IdentifierData) nodeObject;
			mPrefuse.panToNode(ident);
		} else if (nodeObject instanceof Metadata) {
			Metadata meta = (Metadata) nodeObject;
			DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node
					.getParent();
			IdentifierData ident = (IdentifierData) parent.getUserObject();
			mPrefuse.panToNode(meta, ident);
		}
	}

}
