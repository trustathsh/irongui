package de.hshannover.f4.trust.irongui.communication;



import java.util.Collection;

import de.hshannover.f4.trust.ifmapj.identifier.Identifier;
import de.hshannover.f4.trust.ifmapj.messages.Result;

/**
 * Tobias Ruhe's result for the dump operation.
 * 
 * @author aw
 *
 */
public interface DumpResult extends Result {
	
	/**
	 * @return value of the last-update string
	 */
	public String getLastUpdate();
	
	/**
	 * @return a list of identifiers which represent identifiers
	 *         having metadata attached to them.
	 */
	public Collection<Identifier> getIdentifiers();

}
