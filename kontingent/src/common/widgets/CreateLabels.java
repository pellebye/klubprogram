package common.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class CreateLabels {

	private Label label;

	public Label getLabel() {
		return label;
	}

	private Label label_2;

	public Label getLabel_2() {
		return label_2;
	}

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * Create a label and a text field.
	 *
	 * @param composite
	 *            Common composite.
	 * @param swt_1
	 *            SWT for label (e.g. SWT.NO).
	 * @param boundsL_1
	 *            Bounds array for label (x, y, width, and height).
	 * @param swt_2
	 *            SWT for text field (e.g. SWT.NO).
	 * @param boundsL_2
	 *            Bounds array for text field (x, y, width, and height).
	 * @param swt_2
	 *            Default text for text field.
	 */
	public CreateLabels(
			// Common variables
			Composite composite,
			// Concerning label #1.
			int swt_1, int[] boundsL_1, String text_1) {

		label = new Label(composite, swt_1);
		label.setBounds(boundsL_1[0], boundsL_1[1] + 3, boundsL_1[2], boundsL_1[3]);
		label.setText(text_1);
		label.setAlignment(SWT.CENTER);
		// label.setBackground(BCcolors.getColor(BCcolors.BluePurpleLight));
	}
}
