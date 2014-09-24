import javax.swing.JFrame;
import javax.swing.JLabel;

public class BarGraphDemo {

	public static void main(String[] args) {

		JFrame frame = new JFrame("BarGraphDemo");
		DataModel model = DataModel.getDataModel();

		Controller controller = new Controller(model);
		BarGraphView barView = new BarGraphView();
		controller.addObserver((DataModel.Observer) barView);
		frame.getContentPane().add(new JLabel("TextView"));

		frame.getContentPane().add(barView);
		barView.setSize(200,100);
		Editor editor = new Editor(DataModel.getDataModel());
		frame.getContentPane().add(editor);
		frame.setVisible(true);
		frame.pack();

	}

}
