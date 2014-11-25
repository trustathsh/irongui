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
 * This file is part of irongui, version 0.4.6,
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

/**
 * The Implementation of a Prefuse-Graph
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.assignment.StrokeAction;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.controls.ControlAdapter;
import prefuse.controls.FocusControl;
import prefuse.controls.NeighborHighlightControl;
import prefuse.controls.PanControl;
import prefuse.controls.SubtreeDragControl;
import prefuse.controls.ToolTipControl;
import prefuse.controls.WheelZoomControl;
import prefuse.controls.ZoomControl;
import prefuse.controls.ZoomToFitControl;
import prefuse.data.Graph;
import prefuse.data.Node;
import prefuse.data.expression.Predicate;
import prefuse.data.expression.parser.ExpressionParser;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.EdgeRenderer;
import prefuse.render.LabelRenderer;
import prefuse.util.ColorLib;
import prefuse.util.GraphicsLib;
import prefuse.util.display.DisplayLib;
import prefuse.util.force.DragForce;
import prefuse.util.force.ForceSimulator;
import prefuse.util.force.NBodyForce;
import prefuse.util.force.RungeKuttaIntegrator;
import prefuse.util.force.SpringForce;
import prefuse.visual.VisualItem;
import de.hshannover.f4.trust.irongui.datastructure.IdentifierData;
import de.hshannover.f4.trust.irongui.datastructure.IfmapDataType;
import de.hshannover.f4.trust.irongui.datastructure.Link;
import de.hshannover.f4.trust.irongui.datastructure.Metadata;
import de.hshannover.f4.trust.irongui.datastructure.MetadataEntry;
import de.hshannover.f4.trust.irongui.datastructure.MultiHashMap;
import de.hshannover.f4.trust.irongui.event.NodeSelectedReceiver;

@SuppressWarnings("serial")
public class GraphPanel extends Display implements ComponentListener {

	private MultiHashMap<IdentifierData, Node> mIdentifier2node = new MultiHashMap<IdentifierData, Node>();
	private MultiHashMap<Link, Node> mLink2node = new MultiHashMap<Link, Node>();
	private final Semaphore mSemaphore = new Semaphore(1);
	private final Semaphore mSemaphore2 = new Semaphore(1);
	private Graph mGraph;
	private Visualization mVis;
	private LinkedHashSet<String> mPublishers = new LinkedHashSet<String>();
	private Color DEFAULT_IDENTIFIER_COLOR = new Color(153, 153, 255);
	private Color DEFAULT_METADATA_COLOR = new Color(255, 153, 102);
	private Predicate mPredicateIdentifier;
	private Predicate mPredicateMetadata;
	private JTable mTable;
	private ArrayList<NodeSelectedReceiver> mReceiver = new ArrayList<NodeSelectedReceiver>();
	private String[] mMetatableColumns = new String[] {"Element",
			"Value / Attribute-Name", "Attribute-Value" };
	private boolean mAnimation = true;
	// private Logger mLogger = Logger.getLogger(GraphPanel.class);
	private ActionList mLayout;
	private RepaintAction mRepaintAction;
	private ForceDirectedLayout mForceLayoutAction;
	private int mLastSelectedItem = -1;
	private int mLastSelectedColor = 0;
	private Font mLastSelectedFont = null;

	public GraphPanel() {
		setBackground(Color.WHITE);
		setHighQuality(true);
		init();
	}

	public void addNodeSelectedListener(NodeSelectedReceiver r) {
		if (!mReceiver.contains(r)) {
			mReceiver.add(r);
		}
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		revalidate();
		pan(getParent().getWidth() / 2, getParent().getHeight() / 2);
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	public String[] getPublishers() {
		mPublishers.clear();
		for (IdentifierData ident : mIdentifier2node.keys()) {
			for (Metadata meta : ident.getMetadata()) {
				mPublishers.add(meta.getPublisher());
			}
		}
		for (Link link : mLink2node.keys()) {
			for (Metadata meta : link.getMetadata()) {
				mPublishers.add(meta.getPublisher());
			}
		}

		if (mPublishers.size() > 0) {
			String[] pub = new String[mPublishers.size()];
			Iterator<String> it = mPublishers.iterator();
			int i = 0;
			while (it.hasNext()) {
				pub[i++] = it.next();
			}
			return pub;
		} else {
			return null;
		}
	}

	public void notifyNodeSelectedReceiver(VisualItem item) {
		for (NodeSelectedReceiver r : mReceiver) {
			r.nodeSelected(item);
		}
	}

	public synchronized void processNewPollResult(
			ArrayList<IfmapDataType> newData,
			ArrayList<IfmapDataType> updateData,
			ArrayList<IfmapDataType> deleteData) {
		try {
			mSemaphore.acquire();
			this.stopActions();
			if (newData != null) {
				for (IfmapDataType i : newData)
					this.addNode(i);
			}
			if (updateData != null) {
				for (IfmapDataType j : updateData)
					this.addNode(j);
			}
			if (deleteData != null) {
				for (IfmapDataType m : deleteData)
					this.removeNode(m);
			}
			this.startActions();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mSemaphore.release();
		}
		mSemaphore.release();
	}

	public void setNewMetadataColorAction(HashMap<String, Color> map,
			Color iColor, Color mColor) {
		this.stopActions();
		ActionList fill = new ActionList();
		ColorAction fc1 = new ColorAction("graph.nodes", mPredicateIdentifier,
				VisualItem.FILLCOLOR, ColorLib.color(iColor));
		ColorAction fc2 = new ColorAction("graph.nodes", mPredicateMetadata,
				VisualItem.FILLCOLOR, ColorLib.color(mColor));
		fill.add(fc1);
		fill.add(fc2);
		if (map != null && map.size() > 0) {
			ArrayList<String> arr = new ArrayList<String>();
			arr.addAll(map.keySet());

			int[] palette = new int[arr.size()];
			int i = 0;
			for (String key : arr) {
				Color c = map.get(key);
				palette[i] = ColorLib.color(c);
				Predicate predicate = ExpressionParser
						.predicate("publisher = '" + key + "'");
				ColorAction fillColor = new ColorAction("graph.nodes",
						predicate, VisualItem.FILLCOLOR, palette[i]);
				fill.add(fillColor);
				i++;
			}
		}
		mVis.putAction("fill", fill);
		this.DEFAULT_IDENTIFIER_COLOR = iColor;
		this.DEFAULT_METADATA_COLOR = mColor;
		this.startActions();
	}

	public void setTable(JTable tab, JLabel label) {
		this.mTable = tab;
		// this.mLabel = label;
	}

	public synchronized void toggleAnimation() {
		if (mAnimation) {
			// disable it
			mVis.cancel("layout");
		} else {
			// enable it
			mVis.run("layout");
		}
		// update flag
		mAnimation = !mAnimation;
	}

	public synchronized void enableAnimation(boolean e) {
		if (!e) {
			// disable it
			mVis.cancel("layout");
		} else {
			// enable it
			mVis.run("layout");
		}
		// update flag
		mAnimation = e;
	}

	public synchronized boolean isAnimationEnabled() {
		return mAnimation;
	}

	private void addIdentifier(IdentifierData identifier) {
		Node iNode = null;
		ArrayList<Metadata> metaListNew = identifier.getMetadata();
		if (!mIdentifier2node.hasKey(identifier)) {
			Node n = mGraph.addNode();
			setIdentifierNodeValue(n, identifier);
			mIdentifier2node.put(identifier, n);
			iNode = n;
			for (Metadata meta : metaListNew) {
				Node m = mGraph.addNode();
				setMetadataNodeValue(m, meta);
				mGraph.addEdge(m, iNode);
				mIdentifier2node.put(identifier, m);
				this.mPublishers.add(meta.getPublisher());
			}
		} else {
			iNode = mIdentifier2node.get(identifier).get(0);
			ArrayList<Metadata> metaListOld = mIdentifier2node
					.getKey(identifier).getMetadata();
			if (metaListNew != null && !metaListNew.isEmpty()) {
				IdentifierData oldIdent = (IdentifierData) mIdentifier2node
						.getKey(identifier);
				for (Metadata meta : metaListNew) {
					if (!metaListOld.contains(meta)) {
						Node m = mGraph.addNode();
						setMetadataNodeValue(m, meta);
						mGraph.addEdge(m, iNode);
						mIdentifier2node.put(identifier, m);
						this.mPublishers.add(meta.getPublisher());
						if (oldIdent != null) {
							oldIdent.addOrReplaceMetadata(meta);
						}
					} else {
						Metadata mOld = metaListOld.get(metaListOld
								.indexOf(meta));
						if (mOld.getTimestamp().equalsIgnoreCase(
								meta.getTimestamp())) {
							return;
						}
						if (!meta.isSingleValue()) {
							Node m = mGraph.addNode();
							setMetadataNodeValue(m, meta);
							mGraph.addEdge(m, iNode);
							mIdentifier2node.put(identifier, m);
							if (oldIdent != null) {
								oldIdent.addOrReplaceMetadata(meta);
							}
						}
					}

				}
			}
		}
	}

	private void addLink(Link link) {
		synchronized (this) {
			// first we create the identifiers if they not exists
			IdentifierData i1 = link.getIdentifier1();
			IdentifierData i2 = link.getIdentifier2();
			this.addIdentifier(i1);
			this.addIdentifier(i2);
			ArrayList<Metadata> metaData = link.getMetadata();
			if (!mLink2node.hasKey(link)) {
				for (Metadata meta : metaData) {
					Node l = mGraph.addNode();
					setMetadataNodeValue(l, meta);

					// add edges
					mGraph.addEdge(mIdentifier2node.get(i1).get(0), l);
					mGraph.addEdge(mIdentifier2node.get(i2).get(0), l);

					mLink2node.put(link, l);
					this.mPublishers.add(meta.getPublisher());
				}
			} else {
				Link oldLink = (Link) mLink2node.getKey(link);
				if (oldLink != null) {
					for (Metadata meta : link.getMetadata()) {
						oldLink.addOrReplaceMetadata(meta);
					}
					ArrayList<Node> nodeList = mLink2node.get(oldLink);
					for (Node node : nodeList) {
						mGraph.removeNode(node);
					}
					nodeList.clear();
					for (Metadata meta : oldLink.getMetadata()) {
						Node l = mGraph.addNode();
						setMetadataNodeValue(l, meta);
						// add edges
						mGraph.addEdge(mIdentifier2node.get(i1).get(0), l);
						mGraph.addEdge(mIdentifier2node.get(i2).get(0), l);
						nodeList.add(l);
					}
				}
			}
		}
	}

	private void addNode(IfmapDataType ifmap) {
		if (ifmap instanceof IdentifierData) {
			addIdentifier((IdentifierData) ifmap);
		} else if (ifmap instanceof Link) {
			addLink((Link) ifmap);
		}
	}

	// private void enableAnimation(boolean b) {
	// if (b) {
	// if (!mVis.getAction("layout").isRunning()) {
	// mVis.run("layout");
	// }
	// } else {
	// mVis.cancel("layout");
	// }
	// mAnimation = b;
	// }

	private boolean hasLinks(IdentifierData ident) {
		boolean hasLinks = false;
		for (Link link : this.mLink2node.keys()) {
			if (link.getIdentifier1().equals(ident)
					|| link.getIdentifier2().equals(ident)) {
				hasLinks = true;
			}
		}
		return hasLinks;
	}

	private void init() {
		if (mGraph == null) {
			mGraph = new Graph(false);
			mGraph.getNodeTable().addColumn("name", String.class);
			mGraph.getNodeTable().addColumn("value", String.class);
			mGraph.getNodeTable().addColumn("type", String.class);
			mGraph.getNodeTable().addColumn("publisher", String.class);
			mGraph.getNodeTable().addColumn("desc", String.class);
			mGraph.getNodeTable().addColumn("image", String.class);
			mGraph.getNodeTable().addColumn("none", String.class);
			mGraph.getNodeTable().addColumn("object", Object.class);
			mGraph.getNodeTable().addColumn(VisualItem.SHAPE, Integer.class);
		}

		// suppress ExpressionParser output and initialize the predicates
		java.util.logging.Logger logger = Logger
				.getLogger(prefuse.data.expression.parser.ExpressionParser.class
						.getName());
		logger.setLevel(Level.WARNING);
		mPredicateIdentifier = ExpressionParser.predicate("type = '0'");
		mPredicateMetadata = ExpressionParser.predicate("type = '1'");

		// nodes

		LabelRenderer labelRend = new LabelRenderer("value");
		labelRend.setRenderType(LabelRenderer.RENDER_TYPE_DRAW_AND_FILL);
		labelRend.setImagePosition(Constants.TOP);
		labelRend.setRoundedCorner(4, 4);
		labelRend.setHorizontalPadding(4);
		labelRend.setVerticalPadding(4);

		// edges
		EdgeRenderer edgeRend = new EdgeRenderer(Constants.EDGE_TYPE_CURVE);

		// create a new Visualisation
		mVis = new Visualization();
		mVis.add("graph", mGraph);
		mVis.setRendererFactory(new DefaultRendererFactory(labelRend, edgeRend));

		// set all identifiers to the same color
		ColorAction borderColor = new ColorAction("graph.nodes",
				VisualItem.STROKECOLOR, ColorLib.rgb(0, 0, 0));
		ColorAction textColor = new ColorAction("graph.nodes",
				VisualItem.TEXTCOLOR, ColorLib.rgb(0, 0, 0));
		ColorAction edgeColor = new ColorAction("graph.edges",
				VisualItem.STROKECOLOR, ColorLib.rgb(0, 0, 0));
		ColorAction arrowColor = new ColorAction("graph.edges",
				VisualItem.FILLCOLOR, ColorLib.rgb(0, 0, 0));
		ColorAction fillColor = new ColorAction("graph.nodes",
				mPredicateIdentifier, VisualItem.FILLCOLOR,
				ColorLib.color(DEFAULT_IDENTIFIER_COLOR));
		ColorAction fillColor2 = new ColorAction("graph.nodes",
				mPredicateMetadata, VisualItem.FILLCOLOR,
				ColorLib.color(DEFAULT_METADATA_COLOR));
		ActionList fill = new ActionList();
		fill.add(fillColor);
		fill.add(fillColor2);
		mVis.putAction("fill", fill);

		ActionList color = new ActionList();
		color.add(borderColor);
		color.add(textColor);
		color.add(edgeColor);
		color.add(arrowColor);

		// the stroke
		ActionList stroke = new ActionList();
		stroke.add(new StrokeAction("graph.edges", new BasicStroke(2)));

		mLayout = new ActionList(ActionList.INFINITY);
		mForceLayoutAction = new ForceDirectedLayout("graph", false);
		ForceSimulator fsim = new ForceSimulator(new RungeKuttaIntegrator());
		float gravConstant = -40f; // the more negative, the more repelling
		float minDistance = 300f; // -1 for always on, the more positive, the
									// more space between nodes
		float theta = 0.3f; // the lower, the more single-node repell
							// calculation
		float drag = 0.01f;
		float springCoeff = 1E-4f; // 1E-4
		fsim.addForce(new NBodyForce(gravConstant, minDistance, theta));
		fsim.addForce(new DragForce(drag));
		fsim.addForce(new SpringForce(springCoeff, 5));
		fsim.getForces()[2].setParameter(SpringForce.SPRING_LENGTH, 150f);
		mForceLayoutAction.setForceSimulator(fsim);

		mRepaintAction = new RepaintAction();

		mLayout.add(0, mForceLayoutAction);
		mLayout.add(1, mRepaintAction);

		// putting it all in the Visualisation
		mVis.putAction("color", color);
		mVis.putAction("stroke", stroke);
		mVis.putAction("layout", mLayout);
		// mVis.putAction("shape", mLayout);
		mVis.putAction("fill", fill);

		// create display and controls
		this.setVisualization(mVis);
		this.addControlListener(new SubtreeDragControl());
		this.addControlListener(new PanControl());
		this.addControlListener(new ZoomControl());
		this.addControlListener(new ZoomToFitControl());
		this.addControlListener(new FocusControl(2, "layout"));
		this.addControlListener(new WheelZoomControl());
		this.addControlListener(new NeighborHighlightControl());

		// listener for node selection
		this.addControlListener(new ControlAdapter() {
			private int mOldColor;
			private Font mOldFont;

			public void itemPressed(VisualItem item, MouseEvent e) {
				// mLastSelectedNode.setFillColor(mLastSelectedColor);
				// item.setFont(mLastSelectedFont);
				if (item.canGet("object", Object.class)) {
					/*
					 * if(mLastSelectedItem != null){
					 * mLastSelectedItem.setFillColor(oldColor);
					 * mLastSelectedItem.setFont(oldFont); }
					 */
					mOldColor = item.getFillColor();
					mOldFont = item.getFont();

					mLastSelectedItem = ((Node) item.getSourceTuple()).getRow();
					mLastSelectedColor = item.getFillColor();
					mLastSelectedFont = item.getFont();
					item.setFillColor(ColorLib.color(Color.RED));
					item.setFont(mOldFont.deriveFont(Font.BOLD));
					showMetaInTable(item);
					notifyNodeSelectedReceiver(item);

					if (e.getClickCount() == 2) {
						Point2D point = new Point2D.Double(item.getX(), item
								.getY());
						animatePanToAbs(point, 1000);
					}
				}
			}

			public void itemReleased(VisualItem item, MouseEvent e) {
				item.setFillColor(mOldColor);
				item.setFont(mOldFont);
			}
		});

		addControlListener(new ToolTipControl("name") {
			{
				javax.swing.ToolTipManager.sharedInstance().setDismissDelay(
						60000);
				javax.swing.ToolTipManager.sharedInstance().setInitialDelay(0);
			}

			public void itemEntered(VisualItem item, java.awt.event.MouseEvent e) {
				Display d = (Display) e.getComponent();
				if (item.canGet("object", Object.class)) {
					StringBuffer toolTipText = new StringBuffer();
					toolTipText.append("<html>");
					if (item.get("object") instanceof Metadata) {
						Metadata meta = (Metadata) item.get("object");
						toolTipText.append("<strong><u>" + meta.getName()
								+ "</u></strong><br>");
						toolTipText.append("<i>ifmap-publisher-id:</i> "
								+ meta.getPublisher() + "<br>");
						toolTipText.append("<i>ifmap-timestamp:</i> "
								+ meta.getTimestamp() + "<br>");
						toolTipText.append("<i>ifmap-cardinality:</i> "
								+ meta.getCardinality() + "<br>");
						if (meta.hasAttributes()) {
							toolTipText
									.append("<strong>Attributes</strong><br>");
							for (String key : meta.getAttributes().keySet()) {
								toolTipText.append("<i>" + key + ":</i> "
										+ meta.getAttribute(key) + "<br>");
							}
						}
						if (meta.hasElements()) {
							toolTipText.append("<strong>Elements</strong><br>");
							for (MetadataEntry entry : meta.getElements()) {
								if (!entry.hasAttributes()) {
									toolTipText.append("<i>" + entry.getName()
											+ ":</i> " + entry.getValue()
											+ "<br>");
								} else {
									toolTipText.append("<i>" + entry.getName()
											+ ":</i><br>");
									HashMap<String, String> attr = entry
											.getAttributes();
									for (String key : attr.keySet()) {
										toolTipText
												.append("<span>&#160;&#160;&#160;</span>");
										toolTipText.append("<i>" + key
												+ ":</i> " + attr.get(key));
										toolTipText.append("<br>");
									}
								}
							}
						}
					} else if (item.get("object") instanceof IdentifierData) {
						IdentifierData ident = (IdentifierData) item
								.get("object");
						toolTipText.append("<strong><u>" + ident.getName()
								+ "</u></strong><br>");
						Map<String, String> attr = ident.getAttributes();
						if (attr != null && attr.size() > 0) {
							for (String key : attr.keySet()) {
								toolTipText.append("<i>" + key + ":</i> "
										+ attr.get(key) + "<br>");
							}
						}
					}
					toolTipText.append("</html>");
					d.setToolTipText(toolTipText.toString());
				}
			}
		});

		this.zoom(getLocation(), 0.9);
		this.startActions();
	}

	private void removeIdentifier(IdentifierData identifier) {
		if (mIdentifier2node.hasKey(identifier)) {
			IdentifierData oldIdent = (IdentifierData) mIdentifier2node
					.getKey(identifier);
			if (oldIdent != null) {
				ArrayList<Node> nodeList = mIdentifier2node.get(oldIdent);
				ArrayList<Metadata> metaListOld = oldIdent.getMetadata();
				ArrayList<Metadata> metaListNew = identifier.getMetadata();
				for (int i = 0; i < metaListNew.size(); i++) {
					Metadata meta = metaListNew.get(i);
					if (metaListOld.contains(meta)) {
						// mLogger.trace("removing metadata: " + meta);
						// find node that matches meta object
						for (int j = 0; j < nodeList.size(); j++) {
							Object tmp = nodeList.get(j).get("object");
							// check if meta object matches the one to be
							// removed
							if (tmp instanceof Metadata && tmp.equals(meta)) {
								synchronized (this) {
									clearTable();
									mGraph.removeNode(nodeList.get(j));
									metaListOld.remove(tmp);
									/*
									 * if(mLastSelectedItem != null) { Node
									 * tmpNode = (Node)
									 * mLastSelectedItem.getSourceTuple(); if
									 * (tmpNode == nodeList.get(j)) { tmpNode =
									 * null; } }
									 */
									nodeList.remove(j);
								}
							}
						}
					}
				}
				if (nodeList.size() == 1) {
					if (!hasLinks(identifier)) {
						// mLogger.trace("identifier has no metadata left, removing it ... "
						// + identifier);
						synchronized (this) {
							clearTable();
							mGraph.removeNode(nodeList.get(0));
							mIdentifier2node.remove(identifier);
							nodeList.remove(0);

						}
					}
				}
			}
		}

	}

	private void removeLink(Link link) {
		if (mLink2node.hasKey(link)) {
			Link oldLink = (Link) mLink2node.getKey(link);
			if (oldLink != null) {
				ArrayList<Node> nodeList = mLink2node.get(oldLink);
				ArrayList<Metadata> metaListOld = oldLink.getMetadata();
				ArrayList<Metadata> metaListNew = link.getMetadata();
				for (int i = 0; i < metaListNew.size(); i++) {
					Metadata meta = metaListNew.get(i);
					if (metaListOld.contains(meta)) {
						for (int j = 0; j < nodeList.size(); j++) {
							Node node = nodeList.get(j);
							Metadata metaOld = (Metadata) node.get("object");
							if (metaOld.equals(meta)) {
								// mLogger.trace("removing metadata from link: "
								// + meta);
								synchronized (this) {
									clearTable();
									mGraph.removeNode(node);
									metaListOld.remove(meta);
									nodeList.remove(node);
									/*
									 * Node tmpNode = (Node)
									 * mLastSelectedItem.getSourceTuple(); if
									 * (tmpNode == node) { tmpNode = null; }
									 */
								}
							}
						}
					}
					if (nodeList.size() == 0) {
						// mLogger.trace("link has no metadata left, removing it ... "
						// + link);
						mLink2node.remove(link);
						this.removeIdentifier(oldLink.getIdentifier1());
						this.removeIdentifier(oldLink.getIdentifier2());
					}
				}
			}
		}
	}

	private void removeNode(IfmapDataType ifmap) {
		if (ifmap instanceof IdentifierData) {
			removeIdentifier((IdentifierData) ifmap);
		} else if (ifmap instanceof Link) {
			removeLink((Link) ifmap);
		}
	}

	private void setIdentifierNodeValue(Node node, IdentifierData ident) {
		node.setString("image", "ball_blue.png");
		node.setString("name", ident.getName());
		StringBuffer buf = new StringBuffer();
		Map<String, String> map = ident.getAttributes();
		buf.append(ident.getName());
		buf.append("\n");
		for (String key : ident.getAttributes().keySet()) {
			String value = map.get(key);
			if (value != null && !value.equals("")) {
				buf.append("[ ");
				buf.append(key);
				buf.append("=");
				buf.append(value);
				buf.append(" ]");
				buf.append("\n");
			}
		}
		buf.delete(buf.length() - 1, buf.length());
		node.setString("value", buf.toString());
		node.setString("publisher", "");
		node.setString("type", "0");
		node.setString("desc", ident.getName() + ","
				+ ident.getAttributes().toString());
		node.set("object", ident);
	}

	private void setMetadataNodeValue(Node node, Metadata meta) {
		node.setString("name", meta.getName());
		node.setString("value",
				meta.getName().substring(meta.getName().indexOf(':') + 1));
		node.setString("type", "1");
		node.setString("publisher", meta.getPublisher());
		node.setString("desc", meta.toString());
		node.setString("image", "ball_red.png");
		node.set("object", meta);
	}

	public void clearTable() {
		if (mTable != null) {
			DefaultTableModel model = new DefaultTableModel();
			for (String s : mMetatableColumns) {
				model.addColumn(s);
			}
			mTable.setModel(model);
		}
	}

	private void showMetaInTable(VisualItem item) {
		if (mTable != null) {
			if (item.canGet("object", Object.class)) {
				DefaultTableModel model = new DefaultTableModel();
				for (String s : mMetatableColumns) {
					model.addColumn(s);
				}
				if (item.get("object") instanceof Metadata) {
					Metadata meta = (Metadata) item.get("object");
					ArrayList<MetadataEntry> values = meta.getElements();
					if (meta.hasAttributes()) {
						for (String key : meta.getAttributes().keySet()) {
							String[] row = {
									"<html><strong>" + key + "</strong></html>",
									meta.getAttribute(key), "" };
							model.addRow(row);
						}
					}
					for (MetadataEntry entry : values) {
						if (!entry.hasAttributes()) {
							String[] row = {
									"<html><strong>" + entry.getName()
											+ "</strong></html>",
									entry.getValue(), "" };
							model.addRow(row);
						} else {
							String[] head = {
									"<html><strong>" + entry.getName()
											+ "</strong></html>", "", "" };
							model.addRow(head);
							HashMap<String, String> attr = entry
									.getAttributes();
							for (String key : attr.keySet()) {
								String[] row = {"", key, attr.get(key) };
								model.addRow(row);
							}
						}
					}
				} else if (item.get("object") instanceof IdentifierData) {
					IdentifierData ident = (IdentifierData) item.get("object");
					Map<String, String> attributes = ident.getAttributes();
					if (attributes != null) {
						Set<String> keys = attributes.keySet();
						if (keys != null) {
							for (String key : keys) {
								String[] vrow = {
										"<html><strong>" + key
												+ "</strong></html>",
										attributes.get(key), "" };
								model.addRow(vrow);
							}
						}
					}
				}
				mTable.setModel(model);
			}
		}
	}

	public void clear() {
		try {
			mSemaphore.acquire();
			this.stopActions();
			mIdentifier2node.clear();
			mLink2node.clear();
			mGraph.clear();
			mSemaphore.release();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void panToNode(IdentifierData ident) {
		ArrayList<Node> nodes = mIdentifier2node.get(ident);
		if (nodes != null) {
			Node n = nodes.get(0);
			panAndSelectToNode(n);
		}
	}

	public void panToNode(Metadata meta, IdentifierData ident) {
		Node node = getNodeFromMetadata(meta, ident);
		if (node != null) {
			panAndSelectToNode(node);
		}
	}

	private void panAndSelectToNode(Node node) {
		try {
			mSemaphore2.acquire();
			VisualItem item = mVis.getVisualItem("graph.nodes", node);
			if (mLastSelectedItem != -1) {
				mVis.getVisualItem("graph.nodes",
						mGraph.getNode(mLastSelectedItem)).setFillColor(
						mLastSelectedColor);
				mVis.getVisualItem("graph.nodes",
						mGraph.getNode(mLastSelectedItem)).setFont(
						mLastSelectedFont);
			}
			mLastSelectedItem = ((Node) item.getSourceTuple()).getRow();
			mLastSelectedColor = item.getFillColor();
			mLastSelectedFont = item.getFont();

			item.setFillColor(ColorLib.color(Color.red));
			item.setFont(item.getFont().deriveFont(Font.BOLD));
			showMetaInTable(item);
			notifyNodeSelectedReceiver(item);
			Point2D point = new Point2D.Double(item.getX(), item.getY());
			animatePanToAbs(point, 1000);
			mSemaphore2.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void centerGraph() {
		synchronized (mVis) {
			if (!isTranformInProgress()) {
				Rectangle2D bounds = mVis.getBounds(Visualization.ALL_ITEMS);
				GraphicsLib.expand(bounds, 50 + (int) (1 / getScale()));
				DisplayLib.fitViewToBounds(this, bounds, 1000);
			}
		}
	}

	private Node getNodeFromMetadata(Metadata meta, IdentifierData parent) {
		Node node = null;
		for (Link link : mLink2node.keys()) {
			ArrayList<Metadata> list = link.getMetadata();
			int i = 0;
			for (Metadata m : list) {
				if (m.equals(meta)) {
					if (link.getIdentifier1().equals(parent)) {
						ArrayList<Node> nodes = mLink2node.get(link);
						node = nodes.get(i);
					} else if (link.getIdentifier2() != null
							&& link.getIdentifier2().equals(parent)) {
						ArrayList<Node> nodes = mLink2node.get(link);
						node = nodes.get(i);
					}
				}
				i++;
			}
		}
		for (IdentifierData ident : mIdentifier2node.keys()) {
			ArrayList<Metadata> list = ident.getMetadata();
			int i = 1;
			for (Metadata m : list) {
				if (m.equals(meta)) {
					ArrayList<Node> nodes = mIdentifier2node.get(ident);
					Node n = nodes.get(i);
					Object o = n.get("object");
					if (o instanceof Metadata) {
						Metadata me = (Metadata) o;
						if (me.equals(m)) {
							if (ident.equals(parent)) {
								node = n;
							}
						}
					}
				}
				i++;
			}
		}
		return node;
	}

	public synchronized void startActions() {
		if (mGraph.getTupleCount() > 0) {
			mVis.run("fill");
			mVis.run("color");
			mVis.run("stroke");
			mVis.run("layout");
		}
		// if (!mPublishers.isEmpty()) {
		// for (String key : mPublishers) {
		// mVis.run(key);
		// }
		// }

	}

	public synchronized void stopActions() {
		mVis.cancel("layout");
		mVis.cancel("stroke");
		mVis.cancel("color");
		mVis.cancel("fill");
		if (!mPublishers.isEmpty()) {
			for (String key : mPublishers) {
				mVis.cancel(key);
			}
		}
	}
}
