import java.util.ArrayList;


public class DataModel {
	private ArrayList<Integer> data;

	private Controller controller;
	
	private static DataModel mModel;
	
	public static DataModel getDataModel(){
		if (mModel==null){
			mModel=new DataModel(1,0);
			return mModel;
			}
		else return mModel;
	}
	
	private DataModel(int n,int initialValue) {
		this.controller=null;
		data=new ArrayList<Integer>();
		for (int i=0; i<n;i++ ){
			data.add(initialValue);
		}
	}
	public void setController(Controller controller){
		this.controller=controller;
	}
	public int get(int index){
		return data.get(index);
	}
	public int getLength(){
		return data.size();
	}
	public void change(int index, int value){
		data.set(index,value);
		controller.onDataChange();
	}
	public void add(int value){
		data.add(value);
		controller.onAmountOfDataPointsChange();
	}
	public void set(int index, int value){
		data.set(index, value);
		controller.onDataChange();
	}
	public void remove(int index){
		data.remove(index);
		controller.onAmountOfDataPointsChange();
	}
	public static interface Observer{
		void onDataChange(DataModel model);
		void onAmountOfDataPointsChange(DataModel model);
	}
	
}
