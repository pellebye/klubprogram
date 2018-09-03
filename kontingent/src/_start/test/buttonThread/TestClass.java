package _start.test.buttonThread;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JPanel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;

import common.LocalMethods;

public class TestClass extends JPanel {

	private Shell childShell;
	private AtomicBoolean paused;
	// private JTextArea jta;
	private Button btn;

	private Thread thread;

	public TestClass() {

		childShell = new Shell(LocalMethods.getShell(), SWT.APPLICATION_MODAL | SWT.TITLE | SWT.CLOSE | SWT.ON_TOP);
		childShell.setSize(500, 530);
		childShell.setText("Om Klubprogram");
		childShell.open();

		paused = new AtomicBoolean(false);
		// jta = new JTextArea(100, 100);
		btn = new Button(childShell, SWT.PUSH);

		initialize();
	}

	public void initialize() {

		// jta.setLineWrap(true);
		// jta.setWrapStyleWord(true);
		// add(new JScrollPane(jta));
		btn.setBounds(10, 10, 100, 100);
		btn.setText("Pause");
		btn.addSelectionListener(new ButtonListener());

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				while (true) {
					int i = 0;
					for (i = 0; i < Integer.MAX_VALUE; i++) {
						boolean b = paused.get();
						if (paused.get()) {
							synchronized (thread) {
								try {

									thread.wait();

								} catch (InterruptedException e) {
								}
							}
						}

						// jta.append(Integer.toString(i) + ", ");
					}

					try {

						Thread.sleep(500);
						btn.setText("OK (" + i + ")");

					} catch (InterruptedException e) {
					}
				}
			}
		};
		thread = new Thread(runnable);
		thread.start();
	}

	@Override
	public Dimension getPreferredSize() {

		return new Dimension(100, 30);

	}

	class ButtonListener implements SelectionListener {

		public void actionPerformed(ActionEvent event) {

			if (!paused.get()) {
				btn.setText("Start");
				paused.set(true);
			} else {
				btn.setText("Pause");
				paused.set(false);

				synchronized (thread) {
					thread.notify();
				}
			}
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
			if (!paused.get()) {
				btn.setText("Start");
				paused.set(true);
			} else {
				btn.setText("Pause");
				paused.set(false);

				synchronized (thread) {
					thread.notify();
				}
			}
		}

		@Override
		public void widgetSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub

		}
	}

}