package de.hshannover.f4.trust.irongui.datastructure;




import java.util.ArrayList;
import java.util.HashMap;

public class Metadata {

	private String mName;
	private String mPublisher;
	private String mCardinality;
	private String mTimestamp;
	private ArrayList<MetadataEntry> mElements;
	private HashMap<String, String> mAttributes;

	public Metadata() {
		mElements = new ArrayList<MetadataEntry>();
		mAttributes = new HashMap<String, String>();
		mPublisher = "";
		mCardinality = "";
		mTimestamp = "";
	}
	public Metadata(String name) {
		this();
		mName = name;
	}

	public Metadata(String name, String publisher, String cardinality, String timestamp){
		mName = name;
		mPublisher = publisher;
		mCardinality = cardinality;
		mTimestamp = timestamp;
	}
	
	public Metadata(String name, String publisher, String cardinality, String timestamp, 
			ArrayList<MetadataEntry> elements){
		mName = name;
		mPublisher = publisher;
		mCardinality = cardinality;
		mTimestamp = timestamp;
		if(elements != null){
			mElements = elements;
		}
		else {
			mElements = new ArrayList<MetadataEntry>();
		}
	}
	
	public boolean hasElements(){
		return !mElements.isEmpty();
	}
	
	public void addElement(MetadataEntry entry){
		mElements.add(entry);
	}
	
	public boolean hasAttributes(){
		return !mAttributes.isEmpty();
	}
	
	public void addAttribute(String key, String value){
		mAttributes.put(key, value);
	}
	
	public String getAttribute(String key){
		return mAttributes.get(key);
	}
	
	public ArrayList<MetadataEntry> getElements(){
		return mElements;
	}		

	public HashMap<String, String> getAttributes() {
		return mAttributes;
	}
	
	public String getName() {
		return mName;
	}

	public void setName(String mName) {
		this.mName = mName;
	}

	public String getPublisher() {
		return mPublisher;
	}

	public void setPublisher(String publisher) {
		mPublisher = publisher;
	}

	public String getCardinality() {
		return mCardinality;
	}

	public void setCardinality(String cardinality) {
		mCardinality = cardinality;
	}

	public boolean isSingleValue(){
		return mCardinality.equalsIgnoreCase("singleValue");
	}
	
	public String getTimestamp() {
		return mTimestamp;
	}

	public void setTimestamp(String timestamp) {
		mTimestamp = timestamp;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(this == o) return true;
		if(!(o instanceof Metadata)) return false;
		
		Metadata meta = (Metadata)o;
		
		if(this.hashCode() == meta.hashCode()){
			return true;
		}
		else return false;
	}
	
	@Override
	public int hashCode(){
		StringBuffer buf = new StringBuffer();
		buf.append(this.mName);
		buf.append(this.mPublisher);
		buf.append(this.mCardinality);
		buf.append(this.mAttributes.toString());
		buf.append(this.mElements.toString());
		return buf.toString().hashCode();
	}	
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("name="+this.mName+", publisher="+this.mPublisher+", timestamp="+this.mTimestamp+" ,cardinality="+
				this.mCardinality+" [ "+this.mElements.toString()+" ] {"+this.hashCode()+"}");
		return buf.toString();
	}
}
