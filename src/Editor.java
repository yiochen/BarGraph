import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Editor extends JPanel {
	private ArrayList<JTextField> inputArray;
	private final Editor mEditor = this;
	private final DataModel model;

	public Editor(final DataModel model) {
		this.model = model;
		inputArray = new ArrayList<JTextField>();
		JTextField textInput = new JTextField();
		textInput.setText("0");
		inputArray.add(textInput);

		JButton refreshButton = new JButton("refresh");
		this.add(refreshButton);
		refreshButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < inputArray.size(); i++) {
					model.set(i, Integer.parseInt(inputArray.get(i).getText()));
				}
			}
		});
		JButton addButton = new JButton("add field");
		this.add(addButton);
		addButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				model.add(0);
				JTextField newText = new JTextField("0");
				inputArray.add(newText);
				mEditor.add(newText);
				mEditor.revalidate();
				mEditor.repaint();

			}
		});

		JButton removeButton = new JButton("remove field");
		this.add(removeButton);
		removeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (inputArray.size() <= 0)
					return;
				model.remove(inputArray.size() - 1);

				JTextField removedText = inputArray.remove(inputArray.size() - 1);
				mEditor.remove(removedText);
				mEditor.revalidate();
				mEditor.repaint();
			}
		});
		this.add(textInput);
	}

}
