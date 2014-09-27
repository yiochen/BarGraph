import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class BarGraphDemo extends JFrame {
	
	private DataModel.Strategy mStrategy;
	
	public DataModel.Strategy getStrategy() {
		return mStrategy;
	}

	public void setStrategy(DataModel.Strategy mStrategy) {
		this.mStrategy = mStrategy;
	}

	public BarGraphDemo() {
		setTitle("BarGraphDemo");
		this.mStrategy=new DataModel.LinearStrategy();
	}

	public Dimension getPreferredSize() { // TODO Auto-generated method stub
		return new Dimension(600, 400);
	}

	public static void main(String[] args) {

		BarGraphDemo frame = new BarGraphDemo();
		frame.setSize(600, 400);
		frame.getContentPane().setLayout(new GridLayout(1, 2));
		DataModel model = DataModel.getDataModel();

		Controller controller = new Controller(model);
		BarGraphView barView = new BarGraphView(frame);
		controller.addObserver((DataModel.Observer) barView);

		Editor editor = new Editor(DataModel.getDataModel(),frame);
		controller.addObserver(editor);
		frame.getContentPane().add(editor);
		frame.getContentPane().add(barView);

		frame.setVisible(true);
		frame.pack();

	}

}
