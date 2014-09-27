import java.util.ArrayList;

public class Controller {
	private ArrayList<DataModel.Observer> views;
	private DataModel model;

	public Controller(DataModel model) {
		this.model = model;
		model.setController(this);
		views = new ArrayList<DataModel.Observer>();
		// TODO Auto-generated constructor stub
	}

	public void addObserver(DataModel.Observer view) {
		views.add(view);
	}

	public void removeObserver(DataModel.Observer view) {
		views.remove(view);
	}

	public void onDataChange() {
		for (DataModel.Observer view : views) {
			view.onDataChange(model);
		}

	}

	public void onAmountOfDataPointsChange() {
		for (DataModel.Observer view : views) {
			view.onAmountOfDataPointsChange(model);
		}

	}

}