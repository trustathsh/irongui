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



package de.hshannover.f4.trust.irongui.datastructure;




import de.hshannover.f4.trust.irongui.exception.LinkConstructionException;

/**
 * Represents a Link
 * @author Tobias
 *
 */
public class Link extends IfmapDataType{

	private String name;
	private IdentifierData mIdentifier1;	
	private IdentifierData mIdentifier2;	

	public Link(String name, IdentifierData i1, IdentifierData i2) 
	throws LinkConstructionException{
		super();
		if(i1 == null || i2 == null){
			throw new LinkConstructionException("One or two Identifiers are null!");
		}
		this.name = name;
		this.mIdentifier1 = i1;
		this.mIdentifier2 = i2;		
	}
	
	public String getName(){
		return name;
	}
	
	public IdentifierData getIdentifier1(){
		return mIdentifier1;
	}
	
	public IdentifierData getIdentifier2(){
		return mIdentifier2;
	}

	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(this == o) return true;
		if(!(o instanceof Link)) return false;
		
		Link l2 = (Link)o;
		
		if(this.hashCode() == l2.hashCode()){
			return true;
		}
		else return false;
	}

	@Override
	public int hashCode() {
		StringBuffer buf = new StringBuffer();
		buf.append(this.getName());
		buf.append(this.mIdentifier1.hashCode()+this.mIdentifier2.hashCode());		
		//buf.append(this.getMetadata().toString());
		return buf.toString().hashCode();
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(this.getName()+" [");
		buf.append(this.mIdentifier1.getName()+", "+ this.mIdentifier2.getName()+
				" ] {"+this.hashCode()+"}\r\n     |--[M] "+this.getMetadata().toString());
		return buf.toString();
	}	
}
