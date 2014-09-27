import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class BarGraphView extends JPanel implements DataModel.Observer {
	public static int BAR_WIDTH = 20;
	public static int BAR_UNIT = 5;
	
	public static final int BAR_MIN_UNIT=1;
	public static final int BAR_MAX_UNIT=10;
	
	private DataModel model;
	private BarGraphDemo mainApp;

	private int getIndex(MouseEvent e) {
		return e.getX() / BAR_WIDTH;
	}

	private int getNewValue(MouseEvent e) {
		return mainApp.getStrategy().heightToValue(e.getY());
	}
	private void processMouse(MouseEvent e){
		int index = getIndex(e);
		int newHeight = getNewValue(e);
		if (index < model.getLength()&& index>=0) {
			model.set(index, newHeight);
		}
	}
	public BarGraphView(BarGraphDemo mainApp) {
		this.mainApp=mainApp;
		this.setBorder(BorderFactory.createDashedBorder(Color.black));
		this.addMouseMotionListener(new MouseMotionListener() {
			
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseDragged(MouseEvent e) {
				processMouse(e);
			}
		});
		this.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mousePressed(MouseEvent e) {
				processMouse(e);
				
			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			

			public void mouseClicked(MouseEvent e) {
				processMouse(e);
			}
		});
	}

	private int getColumnX(int index) {
		return index * BAR_WIDTH;
	}

	public int getColumnY(int index) {
		return mainApp.getStrategy().valueToHeight(model.get(index));
	}

	@Override
	public Dimension getPreferredSize() {
		if (model == null)
			return new Dimension(200, 100);
		else
			return new Dimension(model.getLength() * BAR_WIDTH, 100);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (model == null)
			return;

		for (int i = 0; i < model.getLength(); i++) {
			g.setColor(new Color((int) (Math.random() * 0xFFFFFF)));
			g.fillRect(getColumnX(i), 0, BAR_WIDTH, getColumnY(i));
		}
	}

	public void onDataChange(DataModel model) {
		this.model = model;

		repaint();
	}

	public void onAmountOfDataPointsChange(DataModel model) {
		this.model = model;

		repaint();
	}
}
