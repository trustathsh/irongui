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
