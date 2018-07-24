package testMain;

import java.io.File;
import java.util.logging.*;


class MyFormatter extends Formatter {
	// Create a DateFormat to format the logger timestamp.
	//private static final DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
	
	public String format(LogRecord record) {
		StringBuilder builder = new StringBuilder(1000);
		//builder.append(df.format(new Date(record.getMillis()))).append(" - ");
		//builder.append("[").append(record.getSourceClassName()).append(".");
		//builder.append(record.getSourceMethodName()).append("] - ");
		//builder.append("[").append(record.getLevel()).append("] - ");
		builder.append(formatMessage(record));
		builder.append("\n");
		return builder.toString();
	}
	
	public String getHead(Handler h) {
		return super.getHead(h);
	}
	
	public String getTail(Handler h) {
		return super.getTail(h);
	}
}

public class TestThread implements Runnable {

	/* TODO move elsewhere, these constants can be useful in other files too */
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	private File file;

	TestThread(File file) {
		this.file = file;
	}
	@Override
	public void run()  {
		
		Thread t = Thread.currentThread();
		
		/*Logger logger = Logger.getLogger(TestThread.class.getName());
		logger.setUseParentHandlers(false);
		
		MyFormatter formatter = new MyFormatter();
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(formatter);
		
		logger.addHandler(handler);
		
		logger.log(Level.INFO, t.getName());
		logger.log(Level.INFO, ANSI_BLUE + file.getName() + ANSI_RESET);*/
		
		System.out.println(t.getName());
		//checks if this thread is alive
		//System.out.println(ANSI_GREEN + ", status = " + t.isAlive() + ANSI_RESET);
		System.out.println(ANSI_BLUE + file.getName() + ANSI_RESET);

		Main.testWithThreads(file);

	}

	public static void main(String args[]) throws Exception {

		File codeDirectory = new File("code/");

		for (File inputFile : codeDirectory.listFiles()) {

			try {
				Thread t = new Thread(new TestThread(inputFile));
				// this will call run() function
				t.start();
				// waits for this thread to die
				t.join();

				System.out.println("\n");
			}
			catch (Exception E) {

			}
		}
	}

}