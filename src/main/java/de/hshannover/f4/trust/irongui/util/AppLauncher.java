package de.hshannover.f4.trust.irongui.util;




import java.io.IOException;
import java.net.URISyntaxException;

public class AppLauncher {
	public static boolean openWebpageInBrowser(String url){
		if(java.awt.Desktop.isDesktopSupported()) {
			java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
			if(desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
				java.net.URI uri;
				try {
					uri = new java.net.URI(url);				
					desktop.browse(uri);
					return true;
				} catch (IOException e) {
					e.printStackTrace();
					return false;					
				} catch (URISyntaxException e) {
					e.printStackTrace();
			}
			
		}
	}
	return false;
 }
}
