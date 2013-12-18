package de.hshannover.f4.trust.irongui.datastructure;




import java.util.ArrayList;

public abstract class IfmapDataType {

	private ArrayList<Metadata> mMetadata;
	private String mSubscriptionName;

	public IfmapDataType() {
		mMetadata = new ArrayList<Metadata>();
	}

	public void addOrReplaceMetadata(Metadata m) {
		if (mMetadata.contains(m)) {
			Metadata oldMeta = mMetadata.get(mMetadata.indexOf(m));
			if (oldMeta.getTimestamp().equals(m.getTimestamp())) {
				return;
			}
		}
		for (int i = 0; i < mMetadata.size(); i++) {
			Metadata meta = mMetadata.get(i);
			if (meta.getName().equalsIgnoreCase(m.getName())) {
				if (m.isSingleValue()) {
					mMetadata.remove(meta);
				}
			}
		}

		mMetadata.add(m);
	}

	public boolean remove(Metadata meta) {
		return mMetadata.remove(meta);
	}

	public ArrayList<Metadata> getMetadataAsCopy() {
		// TODO make a real copy here
		return this.getMetadata();
	}

	public ArrayList<Metadata> getMetadata() {
		return mMetadata;
	}
	
	public String getSubscriptionName() {
		return mSubscriptionName;
	}

	public void setSubscriptionName(String mSubscriptionName) {
		this.mSubscriptionName = mSubscriptionName;
	}

	public abstract boolean equals(Object o);

	public abstract int hashCode();
	// public abstract E clone();

}
