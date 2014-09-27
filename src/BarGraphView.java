import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class BarGraphView extends JPanel implements DataModel.Observer {
	private static final int BAR_WIDTH = 20;
	private static final int BAR_UNIT = 5;
	private DataModel model;

	private int getIndex(MouseEvent e) {
		return e.getX() / BAR_WIDTH;
	}

	private int getNewHeight(MouseEvent e) {
		return e.getY() / BAR_UNIT;
	}
	private void processMouse(MouseEvent e){
		int index = getIndex(e);
		int newHeight = getNewHeight(e);
		if (index < model.getLength()&& index>=0) {
			model.set(index, newHeight);
		}
	}
	public BarGraphView() {
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
		return model.get(index) * BAR_UNIT;
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
