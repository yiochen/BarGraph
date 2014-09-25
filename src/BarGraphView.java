import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;


public class BarGraphView extends JPanel implements DataModel.Observer {
	private static final int BAR_WIDTH=20;
	private static final int BAR_INTERVAL=5;
	private static final int BAR_UNIT=5;
	private DataModel model;
	public BarGraphView() {
		
	}
	@Override
	public Dimension getPreferredSize() {
		if (model==null) return new Dimension(200,100);
		else return new Dimension(model.getLength()*(BAR_WIDTH+BAR_INTERVAL), 100);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (model==null) return;
		
		g.setColor(Color.black);
		for (int i=0; i<model.getLength();i++){
			g.fillRect(i*(BAR_WIDTH+BAR_INTERVAL),0,BAR_WIDTH,BAR_UNIT*model.get(i));
		}
	}
	public void onDataChange(DataModel model) {
		this.model=model;
		
		repaint();
	}
	public void onAmountOfDataPointsChange(DataModel model) {
		this.model=model;
		
		repaint();
	}
}
