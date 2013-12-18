package de.hshannover.f4.trust.irongui.event;




import de.hshannover.f4.trust.irongui.communication.Connection;

/**
 * Interface for status changes
 * @author Tobias
 *
 */
public interface StatusChangedReceiver {
	public void processStatusEvent(Connection con, String event);
}
