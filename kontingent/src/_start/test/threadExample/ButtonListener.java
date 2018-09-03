package _start.test.threadExample;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

class ButtonListener implements SelectionListener {

	private CreateThreadRunnableExample runnableExample;

	public ButtonListener(CreateThreadRunnableExample runnableExample) {
		this.runnableExample = runnableExample;
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// if (!paused.get()) {
		// runnableExample.getBtn().setText("Start");
		// paused.set(true);
		// } else {
		// runnableExample.getBtn().setText("Pause");
		// paused.set(false);
		// }
	}

	@Override
	public void widgetSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		if (!runnableExample.getPaused().get()) {
			runnableExample.getBtn().setText("Start");
			runnableExample.getPaused().set(true);
		} else {
			runnableExample.getBtn().setText("Pause");
			runnableExample.getPaused().set(false);
		}
	}
}
