package _start.test.buttonThread;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainClass {

	public static void main(final String[] arg) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(new TestClass());
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
	}
}