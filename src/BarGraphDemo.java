import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BarGraphDemo extends JFrame{
	
	public BarGraphDemo() {
		setTitle("BarGraphDemo");
	}
	@Override
	public Dimension getPreferredSize() {
		// TODO Auto-generated method stub
		return new Dimension(600,400);
	}

	public static void main(String[] args) {

		BarGraphDemo frame = new BarGraphDemo();
		frame.setSize(600, 400);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		DataModel model = DataModel.getDataModel();

		Controller controller = new Controller(model);
		BarGraphView barView = new BarGraphView();
		controller.addObserver((DataModel.Observer) barView);

		
		Editor editor = new Editor(DataModel.getDataModel());
		frame.getContentPane().add(editor);
		frame.getContentPane().add(barView);
		
		frame.setVisible(true);
		frame.pack();

	}

}
