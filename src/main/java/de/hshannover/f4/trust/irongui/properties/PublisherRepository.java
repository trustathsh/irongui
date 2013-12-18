package de.hshannover.f4.trust.irongui.properties;




import java.awt.Color;
import java.util.HashMap;

public class PublisherRepository {

	private HashMap<String, Color> mPublishers = new HashMap<String, Color>();
	private Color mIdentifierColor = new Color(153, 153, 255);
	private Color mMetadataColor = new Color(255, 153, 102);
	
	public synchronized void add(String publisher, Color color){
		if(publisher != null && color != null){
			mPublishers.put(publisher, color);
		}
	}
	
	public synchronized void add(HashMap<String, Color> publisher){
		if(publisher != null){
			for(String key : publisher.keySet()){
				mPublishers.put(key, publisher.get(key));
			}			
		}
	}
	
	public synchronized void clear(){
		mPublishers.clear();
	}
	
	public synchronized void remove(String publisher){
		mPublishers.remove(publisher);
	}
	
	public synchronized Color getColor(String publisher){
		return mPublishers.get(publisher);
	}
	
	public synchronized HashMap<String, Color> getPublisherAndColor(){
		return this.mPublishers;
	}
	
	public synchronized String[] getPublisher(){
		int length = mPublishers.size();
		String[] pubs = null;
		if(length > 0){
			pubs = new String[length];
			int i = 0;
			for(String p : mPublishers.keySet()){
				pubs[i++] = p;
			}
		}
		return pubs;
	}

	public synchronized Color getIdentifierColor() {
		return mIdentifierColor;
	}

	public synchronized void setIdentifierColor(Color mIdentifierColor) {
		this.mIdentifierColor = mIdentifierColor;
	}

	public synchronized Color getMetadataColor() {
		return mMetadataColor;
	}

	public synchronized void setMetadataColor(Color mMetadataColor) {
		this.mMetadataColor = mMetadataColor;
	}
}
