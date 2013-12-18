package de.hshannover.f4.trust.irongui.event;




import java.awt.Color;
import java.util.HashMap;

public interface PublisherColorChangedReceiver {
	public void setNewColorSelection(HashMap<String, Color> map, Color defaultIdentifier,
			Color defaultMetadata);
}
