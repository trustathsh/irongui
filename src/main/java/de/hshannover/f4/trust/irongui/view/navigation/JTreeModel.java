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




import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import de.hshannover.f4.trust.irongui.datastructure.IdentifierConstants.IdentifierType;

public class JTreeModel {
	
	private DefaultTreeModel tree;
	private DefaultMutableTreeNode root;
	public DefaultMutableTreeNode device;
	public DefaultMutableTreeNode ip;
	public DefaultMutableTreeNode identity;
	public DefaultMutableTreeNode mac;
	public DefaultMutableTreeNode ar;
	
	public JTreeModel() {
		initModel();
	}
	/*public JTreeModel(IdentifierContainer container){		
		this.tree = initModel(container);
	}
	*/
	public DefaultTreeModel getDefaultTreeModel(){
		return tree;
	}	
	
	private void initModel(){
		root = new DefaultMutableTreeNode("IdentifierData");
        device = new DefaultMutableTreeNode(IdentifierType.DEVICE);
        ip = new DefaultMutableTreeNode(IdentifierType.IP_ADDRESS);
        identity = new DefaultMutableTreeNode(IdentifierType.IDENTITY);
        mac = new DefaultMutableTreeNode(IdentifierType.MAC_ADDRESS);
        ar = new DefaultMutableTreeNode(IdentifierType.ACCESS_REQUEST);
        
        root.add(ar);
        root.add(device);
        root.add(ip);
        root.add(identity);
        root.add(mac);
        
        tree = new DefaultTreeModel(root);
        /*
        for(IdentifierData ident : conti){
             DefaultMutableTreeNode ident_node = new DefaultMutableTreeNode(ident.getAttributes());
            for(LinkContainer lc : ident.getLinks()){         
                for(Link l : lc.getLinks()){
                   String _n = l.getName();
                   if(l.getIdentifier1().getValue("value") != null){
                	  _n = _n.concat(" ["+ l.getIdentifier1().getValue("value")+"]["+l.getIdentifier2().getValue("value")+"]");
                   }
                   DefaultMutableTreeNode nl = new DefaultMutableTreeNode(_n);                  
                       Hashtable<String, Hashtable<String, String>> tab = l.getMetadata();                       
                       Enumeration<String> enumera = tab.keys();
                          while(enumera.hasMoreElements()){
                              String name = enumera.nextElement();
                            //  System.out.println("name: "+name);
                                DefaultMutableTreeNode tmp = new DefaultMutableTreeNode(name);
                                Hashtable<String, String> hash = tab.get(name);
                                Enumeration<String> e_hash = hash.keys();                                
                                while(e_hash.hasMoreElements()){
                                    String key = e_hash.nextElement();
                                    String value = hash.get(key);
                                    DefaultMutableTreeNode k = new DefaultMutableTreeNode(key);
                                    k.add(new DefaultMutableTreeNode(value));
                                    tmp.add(k);
                                }
                            nl.add(tmp);
                          }
                 ident_node.add(nl);
                }
                   
            }
            if(n1.getUserObject().toString().equals(ident.getName())){
                n1.add(ident_node);
            }
            else if(n2.getUserObject().toString().equals(ident.getName())){
                n2.add(ident_node);
            }
             else if(n3.getUserObject().toString().equals(ident.getName())){
                n3.add(ident_node);
            }
             else if(n4.getUserObject().toString().equals(ident.getName())){
                n4.add(ident_node);
            }
             else if(n5.getUserObject().toString().equals(ident.getName())){
                n5.add(ident_node);
            }
        }*/        
	}
}
