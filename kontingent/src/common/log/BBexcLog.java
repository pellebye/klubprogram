package common.log;

public class BBexcLog {

	private static String keyWord = "";

	/**
	 *
	 * @param keyWord
	 * @param e
	 */
	public static void log(String keyWord, Exception e) {
		BBexcLog.keyWord = keyWord;
		CommonLog.logger.info("message//---> Exception - " + e.getMessage() + " - " + BBexcLog.keyWord);

		StackTraceElement[] stes = e.getStackTrace();

		for (int i = 0; i < stes.length; i++) {
			StackTraceElement ste = stes[i];
			ste.getMethodName();
			int lineNo = ste.getLineNumber();
			String space = "";
			if (lineNo < 999)
				space += " ";
			if (lineNo < 99)
				space += " ";
			String lineNumber = (lineNo == -1) ? "       " : space + "(" + ste.getLineNumber() + ") ";

			CommonLog.logger.info("message//       " + lineNumber + ste.getMethodName() + " - " + ste.getClassName());
		}
		CommonLog.logger.info("message//------------------------------ Exception end");
		CommonLog.logger.info("message// ");
	}
}
