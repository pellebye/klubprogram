package common.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import common.LocalMethods;

public class CreateHeadline {

	private Label labelMessage;

	public Label getLabelBoardNumber() {
		return labelMessage;
	}

	public CreateHeadline(Composite composite, int PARx, int PARy) {

		labelMessage = new Label(composite, SWT.LEFT | SWT.WRAP);
		labelMessage.setBounds(PARx, PARy, 275, 40);
		labelMessage.setFont(LocalMethods.getFont(0));
		labelMessage.setText("Klubprogram");
		labelMessage.setAlignment(SWT.CENTER);
		labelMessage.setFont(LocalMethods.getFont(+5));
		labelMessage.setVisible(true);
		// labelMessage.setBackground(BCcolors.getColor(BCcolors.Red));
	}
}
