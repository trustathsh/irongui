package de.hshannover.f4.trust.irongui.util;




import javax.swing.ImageIcon;

public class ResourceHelper {

	private static final String APPICON = "esukom.png";
	
	public static ImageIcon getImage(String res){
		return new ImageIcon(ResourceHelper.class.getClassLoader().getResource(res));
	}
	
	public static ImageIcon getAppIconImage(){
		return new ImageIcon(ResourceHelper.class.getClassLoader().getResource(APPICON));
	}
}
