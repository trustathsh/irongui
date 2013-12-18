package de.hshannover.f4.trust.irongui.communication;



import de.hshannover.f4.trust.ifmapj.messages.Request;

/**
 * Tobias Ruhe's request for the dump operation.
 * 
 * @author 
 *
 */
public interface DumpRequest extends Request {
	
	public void setIdentifierFilter(String filter);
	
	public String getIdentifierFilter();
}

