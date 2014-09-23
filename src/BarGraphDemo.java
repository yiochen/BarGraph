import javax.swing.JFrame;
import javax.swing.JLabel;


public class BarGraphDemo {
	
	public static void main(String[] args) {
		DataModel model=DataModel.getDataModel();
		JFrame frame=new JFrame("BarGraphDemo");
		
		frame.getContentPane().add(new JLabel("TextView"));
		Controller controller=new Controller(model);
		BarGraphView barView=new BarGraphView();
		frame.getContentPane().add(new BarGraphView());
		Editor editor=new Editor(DataModel.getDataModel());
		frame.getContentPane().add(editor);
		frame.setVisible(true);
		frame.pack();
		
		

	}
	

}
