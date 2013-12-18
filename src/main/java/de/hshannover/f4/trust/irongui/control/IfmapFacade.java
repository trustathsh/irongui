package de.hshannover.f4.trust.irongui.control;



import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import de.hshannover.f4.trust.irongui.communication.Connection;
import de.hshannover.f4.trust.irongui.datastructure.PollResultContainer;
import de.hshannover.f4.trust.irongui.event.EventService;
import de.hshannover.f4.trust.irongui.event.IdentifierChangedReceiver;
import de.hshannover.f4.trust.irongui.event.StatusChangedReceiver;
import de.hshannover.f4.trust.irongui.exception.ConnectionCreationException;
import de.hshannover.f4.trust.irongui.exception.PropertiesNotFoundException;
import de.hshannover.f4.trust.irongui.properties.SynchronisationService;
import de.hshannover.f4.trust.irongui.view.component.GraphPanel;
import de.hshannover.f4.trust.ifmapj.exception.IfmapErrorResult;
import de.hshannover.f4.trust.ifmapj.exception.IfmapException;
import de.hshannover.f4.trust.ifmapj.identifier.Identifier;

public final class IfmapFacade {

	private static EventService mEventService;
	private SynchronisationService mSync;

	public IfmapFacade(SynchronisationService sync) {
		mEventService = new EventService();
		mSync = sync;
	}	
	
	/*
	 * public void setDumpFilter(String f){ mFilter = f; }
	 */
	public void addStatusListener(StatusChangedReceiver r) {
		mEventService.addStatusListener(r);
	}

	public void addUpdatesListener(IdentifierChangedReceiver r) {
		mEventService.addUpdateListener(r);
	}

	public static void notifyRepositoryChanged(Connection con, PollResultContainer prc) {
		mEventService.notifyRepositoryChanged(con, prc);
	}
	
	public static void notifyConnectionBroken(Connection con, Exception err){
		mEventService.notifyStatusChangedReceiver(con, err.getLocalizedMessage());
	}
	
	public void removeConnection(Connection con) throws ConnectionCreationException{
		if(con != null){
			mSync.remove(con);
		}
		mSync.sync();
	}
	
	public boolean hasConnection(Connection c){
		if(c != null){
			Connection[] connections = mSync.getConnections();
			for(Connection con : connections){
				if(con.equals(c)){
					return true;
				}
			}
		}
		return false;
	}
	
	public void addPublisher(HashMap<String, Color> publisher, Color iColor, Color mColor){
		if(publisher != null && iColor != null && mColor != null){
			mSync.addPublisher(publisher, iColor, mColor);
			mSync.sync();
		}
	}
	
	public void updateConnections(Connection[] connections) {
		mSync.clearConnections();
		if(connections != null){			
			for(Connection con : connections){
				try {
					mSync.add(con);
				} catch (ConnectionCreationException e) {
					e.printStackTrace();
				}
			}			
		}
		mSync.sync();
	}
	
	public Connection[] getConnections() {
		return mSync.getConnections();
	}
	
	public HashMap<String, Color> getPublisherAndColor(){
		return mSync.getPublisherAndColor();
	}
	
	public Color getDefaultIdentifierColor() {
		return mSync.getDefaultIdentifierColor();
	}

	public Color getDefaultMetadataColor() {
		return mSync.getDefaultMetadataColor();
	}
	
	/*
	 * private synchronized void initProperties() throws
	 * PropertiesNotFoundException { if (this.mProperties == null) throw new
	 * PropertiesNotFoundException(
	 * "Properties not set. Use setPropertiesPath() to set path."); try {
	 * PropertiesRepository.loadProperties(mProperties); } catch
	 * (FileNotFoundException e) { throw new PropertiesNotFoundException(
	 * "Properties not initialized. File not found: '" + mProperties + "'"); }
	 * catch (IOException e) { throw new PropertiesNotFoundException(
	 * "Properties not initialized. IOException: '" + mProperties + "'"); } }
	 */

	public void startSession(Connection con) throws FileNotFoundException,
			PropertiesNotFoundException, IOException, IfmapErrorResult,
			IfmapException, InterruptedException {
		if (con != null) {					
				con.connect();
				mEventService.notifyStatusChangedReceiver(con, "Connection "
						+ con.getEndpoint() + " established.");			
		}
	}

	public void stopSession(Connection con) throws IfmapErrorResult,
			IfmapException, InterruptedException {
		if (con != null) {
			if (con.isConnected()) {
				con.disconnect();
				mEventService.notifyStatusChangedReceiver(con, "Connection "
						+ con.getEndpoint() + " dropped.");
			}
		}
	}

	public String subscribe(Connection con, Identifier ident, int depth,
			int size, String filter, String links, String[] terminal) {
		return con.subscribeUpdate(ident, depth, size, filter, links, terminal);
	}
	
	public void unsubscribe(Connection con, String[] keys) {
		if(keys != null && keys.length > 0){
			con.subscribeDelete(keys);
		}		
	}
}
