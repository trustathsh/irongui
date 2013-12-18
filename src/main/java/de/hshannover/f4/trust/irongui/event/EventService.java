package de.hshannover.f4.trust.irongui.event;




import java.util.ArrayList;

import de.hshannover.f4.trust.irongui.communication.Connection;
import de.hshannover.f4.trust.irongui.datastructure.PollResultContainer;

public class EventService {

	private ArrayList<StatusChangedReceiver> mStatusReceiver = new ArrayList<StatusChangedReceiver>();
	private ArrayList<IdentifierChangedReceiver> mIdentifierReceiver = new ArrayList<IdentifierChangedReceiver>();

	public void addStatusListener(StatusChangedReceiver r) {
		if (!mStatusReceiver.contains(r))
			mStatusReceiver.add(r);
	}

	public void addUpdateListener(IdentifierChangedReceiver r) {
		if (!mIdentifierReceiver.contains(r))
			mIdentifierReceiver.add(r);
	}

	public synchronized void notifyRepositoryChanged(Connection con, PollResultContainer prc) {
		if (prc != null) {
			for (IdentifierChangedReceiver r : mIdentifierReceiver) {
				r.processNewPollResult(con, prc.getNew(), prc.getUpdate(),
						prc.getDelete());
			}
		}
	}

	public synchronized void notifyStatusChangedReceiver(Connection con, String evt) {
		if (evt != null) {
			for (StatusChangedReceiver r : mStatusReceiver) {
				r.processStatusEvent(con, evt);
			}
		}
	}

}
