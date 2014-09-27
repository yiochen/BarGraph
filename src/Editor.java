import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Editor extends JPanel implements DataModel.Observer {
	private ArrayList<JTextField> inputArray;
	private final Editor mEditor = this;
	private final DataModel model;
	private BarGraphDemo mainApp;

	private static final int WIDTH = 100;
	private static final int HEIGHT = 30;

	private static final String LINEAR = "linear";
	private static final String LOG = "logarithmic";

	private JTextField newText(int index, String initialText) {
		JTextField textInput = new JTextField();
		textInput.setText(initialText);
		textInput.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		textInput.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		textInput.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		return textInput;
	}

	private JPanel createControllerPane() {
		JPanel newPane = new JPanel();
		newPane.setLayout(new BoxLayout(newPane, BoxLayout.Y_AXIS));
		final JSlider slider = new JSlider(JSlider.HORIZONTAL,
				BarGraphView.BAR_MIN_UNIT, BarGraphView.BAR_MAX_UNIT,
				BarGraphView.BAR_UNIT);
		newPane.add(slider);
		slider.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {
				BarGraphView.BAR_UNIT = slider.getValue();
				model.refresh();
			}
		});

		JRadioButton linearButton = new JRadioButton(LINEAR);
		linearButton.setActionCommand(LINEAR);
		linearButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				mainApp.setStrategy(new DataModel.LinearStrategy());

			}
		});
		linearButton.setSelected(true);
		JRadioButton logButton = new JRadioButton(LOG);
		logButton.setActionCommand(LOG);
		logButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				mainApp.setStrategy(new DataModel.LogStrategy());
			}
		});

		ButtonGroup group = new ButtonGroup();
		group.add(linearButton);
		group.add(logButton);
		newPane.add(linearButton);
		newPane.add(logButton);

		return newPane;
	}

	private JPanel createButtonPane() {
		JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));

		JButton refreshButton = new JButton("refresh");
		pane.add(refreshButton);
		refreshButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < inputArray.size(); i++) {
					if (inputArray.get(i).getText().isEmpty())
						model.set(i, 0);
					else
						model.set(i,
								Integer.parseInt(inputArray.get(i).getText()));
				}
			}
		});

		JButton addButton = new JButton("add field");

		pane.add(addButton);
		addButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				final int index = model.add(0);
				final JTextField newText = newText(index, "");

				newText.addFocusListener(new FocusListener() {

					public void focusLost(FocusEvent e) {
						int attemptInterpret = Integer.parseInt(newText
								.getText().toString());
						model.set(index, attemptInterpret);
					}

					public void focusGained(FocusEvent e) {
						// TODO Auto-generated method stub

					}
				});

				inputArray.add(newText);
				mEditor.add(newText);
				mEditor.revalidate();
				mEditor.repaint();

			}
		});

		JButton removeButton = new JButton("remove");
		// removeButton.setPreferredSize(new Dimension(200, 30));
		pane.add(removeButton);
		removeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (inputArray.size() <= 0)
					return;
				model.remove(inputArray.size() - 1);

				JTextField removedText = inputArray.remove(inputArray.size() - 1);
				mEditor.remove(removedText);
				mEditor.repaint();
			}
		});
		return pane;

	}

	public Editor(final DataModel model, BarGraphDemo mainApp) {
		this.model = model;
		this.mainApp = mainApp;
		inputArray = new ArrayList<JTextField>();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(createButtonPane());
		this.add(createControllerPane());

	}

	public void onDataChange(DataModel model) {
		for (int i = 0; i < inputArray.size(); i++) {
			inputArray.get(i).setText("" + model.get(i));
		}
	}

	public void onAmountOfDataPointsChange(DataModel model) {
		// TODO Auto-generated method stub

	}

}
