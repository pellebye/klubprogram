package common.widgets;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import common.LocalMethods;
import common.log.CommonLog;

/**
 * This class opens a form which show basic information for all tournament. When
 * form is submitted information is applied for all tournament in this
 * installation.
 */
public class ShellAbout {

	private Shell childShell;

	private final int BASIC_X = 100;
	private int BASIC_Y = 50;
	private final int BASIC_WIDTH = 275;
	private final int BASIC_HIGH = 20;

	private final String[] LABELTEXT_1 = new String[] { "Version", "År", "Af", "Mail", "Telefon" };
	private String labelText = "";

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class opens a form which show basic information.
	 * 
	 * @param firstTimeUse
	 *            True if program is started for the first time.
	 */
	public ShellAbout(boolean firstTimeUse) {
		CommonLog.logger.info("class//");

		// Text to about.
		LABELTEXT_1[0] += ": " + "1.6";
		LABELTEXT_1[1] += ": " + "2017 - 2018";
		LABELTEXT_1[2] += ": " + "Preben Ellebye";
		LABELTEXT_1[3] += ": " + "preben@pellebye.dk";
		LABELTEXT_1[4] += ": " + "30 42 35 35";

		/*
		 * Create a child shell which contains form.
		 */
		childShell = new Shell(LocalMethods.getShell(), SWT.APPLICATION_MODAL | SWT.TITLE | SWT.CLOSE | SWT.ON_TOP);

		if (firstTimeUse)
			childShell.setSize(500, 530);
		else
			childShell.setSize(500, 340);

		childShell.setText("Om Klubprogram");
		childShell.open();

		createWidgets(childShell, firstTimeUse);

		Display display = childShell.getDisplay();
		while (!childShell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Creates widgets on child shell.
	 *
	 * <ol>
	 * <li>Label with info.</li>
	 * <li>11 Rows of one label widget combined with one text widget.</li>
	 * <li>Submit button.</li>
	 * </ol>
	 * 
	 * @param firstTimeUse
	 *
	 * @param basicchildShell
	 *            Basic childShell for all widgets.
	 */
	private void createWidgets(Shell childShell, boolean firstTimeUse) {
		common.log.CommonLog.logger.info("heading//");

		new CreateHeadline(childShell, BASIC_X + 10, 10);

		createLabelpairsVersion();

		if (firstTimeUse)
			createLabelpairsFirsttimeText();
		createSubmitButton(firstTimeUse);
	}

	private void createSubmitButton(boolean firstTimeUse) {
		common.log.CommonLog.logger.info("heading//");

		int y = 0;

		if (firstTimeUse)
			y = BASIC_Y + 360;
		else
			y = BASIC_Y + 160;

		Button submit = new Button(childShell, SWT.PUSH);
		submit.setBounds(BASIC_X, y, BASIC_WIDTH, 65);
		submit.setText("OK");
		selectionForSubmit(submit, firstTimeUse);

		if (!firstTimeUse) {
			for (int i = 10; i > 0; i--) {
				try {
					submit.setText("OK (" + i + ")");
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * Selection listener for submit button.
	 *
	 * @param submit
	 *            Button submit.
	 * @param firstTimeUse
	 */
	private void selectionForSubmit(Button submit, boolean firstTimeUse) {
		submit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CommonLog.logger.info("heading//");
				childShell.close();
			}
		});
	}

	private void createLabelpairsFirsttimeText() {
		common.log.CommonLog.logger.info("heading//");

		labelText = "Denne meddelelse vises første gang programmet bruges." + LocalMethods.getNewline();
		labelText += "Vær opmærksom på følgende:" + LocalMethods.getNewLineDouble();
		labelText += "   1. at programmet skal bruge nogle filer genereret i " + LocalMethods.getNewline();
		labelText += "      BridgeCentral. Læs vejledningen som vises hver gang " + LocalMethods.getNewline();
		labelText += "      programmet startes." + LocalMethods.getNewLineDouble();
		labelText += "   2. at disse filers filnavne skal nævnes i konfigurationsfilen" + LocalMethods.getNewline();
		labelText += "      (config.txt). Se vejledningen i konfigurationsfilen.";

		Label label = new Label(childShell, SWT.NO);
		label.setBounds(30, 210, 480, 200);
		label.setText(labelText);
		label.setFont(LocalMethods.getFont(-2));
		label.setAlignment(SWT.LEFT);
		// label.setBackground(BCcolors.getColor(BCcolors.BluePurpleLight));
	}

	/**
	 * Creates label and text widgets in rows of one label and one text field.
	 */
	private void createLabelpairsVersion() {
		common.log.CommonLog.logger.info("heading//");

		int y = 0;
		// int space = 0;

		for (int i = 0; i < LABELTEXT_1.length; i++) {
			int space = 10;

			y = BASIC_Y + (i * 25) + space;
			int[] boundsL_1 = new int[] { BASIC_X, y, BASIC_WIDTH, BASIC_HIGH };
			new CreateLabels(childShell, SWT.NO, boundsL_1, LABELTEXT_1[i]);
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {

		/*
		 * Create main shell.
		 */
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new RowLayout(SWT.VERTICAL));
		shell.setData("shell");
		shell.setText("Program start");
		LocalMethods.setShell(shell);
		// new MessagesAndChecks("0", ModeChecks.TEST_GENERAL, null);

		LocalMethods.methodShowMessage("Local class run", "You are running in local class", SWT.ICON_INFORMATION);
		new ShellAbout(false);

		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
