package log;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDKLogger {

	private static final Logger  logger = Logger.getLogger(JDKLogger.class.getName());
	
	public static void main(String[] args) {
		logger.info("JDK自带的INFO日志");
		logger.fine("JDK自带的FINE日志");
		logger.finer("JDK自带的FINER日志");
		logger.finest("JDK自带的FINEST日志");
		logger.log(Level.WARNING, "JDK自带的LOF日志");
	}
}
