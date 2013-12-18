package de.hshannover.f4.trust.irongui.event;




import java.util.ArrayList;

import de.hshannover.f4.trust.irongui.communication.Connection;
import de.hshannover.f4.trust.irongui.datastructure.IfmapDataType;
/**
 * Interface for Metadata changes
 * @author Tobias
 *
 */
public interface IdentifierChangedReceiver {
	public void processNewPollResult(Connection con, ArrayList<IfmapDataType> newData, 
			ArrayList<IfmapDataType>updateData, ArrayList<IfmapDataType>deleteData);
	//public void processNew(IdentifierContainer conti);
	//public void processUpdate(IdentifierContainer conti);
	//public void processDelete(IdentifierContainer conti);
}
