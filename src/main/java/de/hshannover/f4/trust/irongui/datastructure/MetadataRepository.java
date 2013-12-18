package de.hshannover.f4.trust.irongui.datastructure;




import java.util.ArrayList;

public class MetadataRepository {

	private MultiMap<IdentifierData, Link> mMapIdentifier;
	private MultiMap<Link, IdentifierData> mMapLinks;

	public MetadataRepository() {
		mMapIdentifier = new MultiHashMap<IdentifierData, Link>();
		mMapLinks = new MultiHashMap<Link, IdentifierData>();
	}

	private void removeLink(Link link) {
		if (link != null) {
			IdentifierData i1 = link.getIdentifier1();
			IdentifierData i2 = link.getIdentifier2();
			if (i1 != null) {
				ArrayList<Link> links = mMapIdentifier.get(i1);
				if (links == null) {
					mMapIdentifier.remove(i1);
				} else {
					links.remove(link);
					if (links.isEmpty()) {
						mMapIdentifier.remove(i1);
					}
				}
			}
			if (i2 != null) {
				ArrayList<Link> links = mMapIdentifier.get(i2);
				if (links == null) {
					mMapIdentifier.remove(i2);
				} else {
					links.remove(link);
					if (links.isEmpty()) {
						mMapIdentifier.remove(i2);
					}
				}
			}
			mMapLinks.remove(link);
		}
	}

	private void addLink(Link link) {
		if (link != null) {
			// IdentifierData already exists?
			IdentifierData i1 = link.getIdentifier1();
			IdentifierData i2 = link.getIdentifier2();
			ArrayList<Link> links1 = mMapIdentifier.get(i1);
			ArrayList<Link> links2 = mMapIdentifier.get(i2);

			if (links1 == null || !links1.contains(link)) {
				mMapIdentifier.put(i1, link);
			} else {
				int index = links1.indexOf(link);
				Link l1 = links1.get(index);
				for (Metadata m : link.getMetadataAsCopy()) {
					l1.addOrReplaceMetadata(m);
				}
			}

			if (links2 == null || !links2.contains(link)) {
				mMapIdentifier.put(i2, link);
			} else {
				int index = links2.indexOf(link);
				Link l2 = links2.get(index);
				for (Metadata m : link.getMetadataAsCopy()) {
					l2.addOrReplaceMetadata(m);
				}
			}
			if (mMapLinks.hasKey(link)) {
				Link key = mMapLinks.getKey(link);
				for (Metadata m : link.getMetadataAsCopy()) {
					key.addOrReplaceMetadata(m);
				}
			}

			mMapLinks.put(link, i1);
			mMapLinks.put(link, i2);
		}		
	}

	public synchronized void addEntity(IfmapDataType entity){
		if(entity instanceof IdentifierData){
			addIdentifier((IdentifierData)entity);
		}
		else if (entity instanceof Link){
			addLink((Link)entity);
		}
	}
	
	public synchronized void removeEntity(IfmapDataType entity){
		if(entity instanceof IdentifierData){
			removeIdentifier((IdentifierData)entity);
		}
		else if (entity instanceof Link){
			removeLink((Link)entity);
		}
	}
	
	private void addIdentifier(IdentifierData ident) {
		if (ident != null) {
			if (!mMapIdentifier.hasKey(ident)) {
				mMapIdentifier.put(ident, null);
			} else {
				IdentifierData identRef = mMapIdentifier.getKey(ident);
				for (Metadata meta : ident.getMetadata()) {
					identRef.addOrReplaceMetadata(meta);
				}
			}			
		}		
	}
	
	private void removeIdentifier(IdentifierData ident){
		if(ident != null && this.mMapIdentifier.hasKey(ident)){
			IdentifierData ident_old = this.mMapIdentifier.getKey(ident);
			for(Metadata meta : ident.getMetadata()){
				ident_old.remove(meta);
			}
			if(ident_old.getMetadata().isEmpty()){
				mMapIdentifier.remove(ident_old);
			}			
		}
	}	
	public void printMaps(){
		System.err.println("MultiMap<IdentifierData, Link>\r\n"+this.mMapIdentifier.toString());
		System.err.println("MultiMap<Link, IdentifierData>\r\n"+this.mMapLinks.toString());
	}
}
