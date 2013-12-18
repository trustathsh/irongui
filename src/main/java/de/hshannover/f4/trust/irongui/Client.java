package de.hshannover.f4.trust.irongui;




import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import de.hshannover.f4.trust.irongui.communication.ConnectionRepository;
import de.hshannover.f4.trust.irongui.control.IfmapFacade;
import de.hshannover.f4.trust.irongui.properties.PropertiesRepository;
import de.hshannover.f4.trust.irongui.properties.PublisherRepository;
import de.hshannover.f4.trust.irongui.properties.SynchronisationService;
import de.hshannover.f4.trust.irongui.view.ViewController;

public class Client {

	private final ViewController mViewController;
	private final IfmapFacade mIfmapFacade;
	
	public Client() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.put("PopupMenu.consumeEventOnClose", Boolean.TRUE);
			//UIManager.put("Button.showMnemonics", Boolean.TRUE);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		PropertiesRepository mPropertiesRep = new PropertiesRepository();
		ConnectionRepository mConnectionRep = new ConnectionRepository();
		PublisherRepository mPublisherRep = new PublisherRepository();
		SynchronisationService mSyncService = new SynchronisationService(mPropertiesRep, 
				mConnectionRep, mPublisherRep);
		mSyncService.init();
		mIfmapFacade = new IfmapFacade(mSyncService);
		mViewController = new ViewController(mIfmapFacade);		
	}
	
	public void start(){		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				mViewController.showMainFrame();
			}
		});
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		client.start();
	}
}
