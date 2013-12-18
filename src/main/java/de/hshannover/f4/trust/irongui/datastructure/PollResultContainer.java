package de.hshannover.f4.trust.irongui.datastructure;




import java.util.ArrayList;

public class PollResultContainer {

	private ArrayList<IfmapDataType> mNew;
	private ArrayList<IfmapDataType> mUpdate;
	private ArrayList<IfmapDataType> mDelete;
	
	public PollResultContainer(ArrayList<IfmapDataType> lNew,
			ArrayList<IfmapDataType> lUpdate, 
			ArrayList<IfmapDataType> lDelete) {
		mNew = lNew;
		mUpdate = lUpdate;
		mDelete = lDelete;
	}
	
	public ArrayList<IfmapDataType> getNew(){
		return mNew;
	}
	
	public ArrayList<IfmapDataType> getUpdate(){
		return mUpdate;
	}
	
	public ArrayList<IfmapDataType> getDelete(){
		return mDelete;
	}
	
}
