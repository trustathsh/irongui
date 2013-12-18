package de.hshannover.f4.trust.irongui.datastructure;




import java.util.HashMap;

public class MetadataEntry {	
	private HashMap<String, String> mAttributes;
	private String mName;
	private String mValue;
	
	
	public MetadataEntry(String name, String value) {
		mName = name;
		mValue = value;
	}
	
	public void addAttribute(String key, String value){
		if(mAttributes == null)
			mAttributes = new HashMap<String, String>();
		mAttributes.put(key, value);
	}
		
	public HashMap<String, String> getAttributes(){
		return mAttributes;
	}

	
	public boolean hasAttributes(){
		return (mAttributes == null || mAttributes.size() == 0) ? false : true;
	}
	
	public String getName() {
		return mName;
	}

	public String getValue() {
		return mValue;
	}
	
	@Override
	public String toString(){
		return mName+":"+mValue;
	}
	
}
