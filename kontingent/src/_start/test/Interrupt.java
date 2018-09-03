package _start.test;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import common.LocalMethods;
import common.log.CommonLog;

public class Interrupt {

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	public Interrupt() {
		CommonLog.logger.info("heading//");

		for (int i = 0; i < 10; i++) {
			// Pause for 4 seconds
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// We've been interrupted: no more messages.
				return;
			}
			// Print a message
			System.out.println(i);
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

		new Interrupt();

		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
