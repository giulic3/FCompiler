
import java.io.File;


public class TestThread implements Runnable {

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
		System.out.println(t.getName());
		//checks if this thread is alive
		//System.out.println(ANSI_GREEN + ", status = " + t.isAlive() + ANSI_RESET);
		System.out.println(ANSI_BLUE + file.getName() + ANSI_RESET);

		Main.test(file);

	}

	public static void main(String args[]) {

		File codeDirectory = new File("code/");

		for (File inputFile : codeDirectory.listFiles()) {

			try {
				Thread t = new Thread(new TestThread(inputFile));
				// this will call run() function
				t.start();
				// waits for this thread to die
				t.join();
				//System.out.print(t.getName());
				//checks if this thread is alive
				//System.out.println(ANSI_RED + ", status = " + t.isAlive() + ANSI_RESET);
				System.out.println("\n");
			}
			catch (Exception E) {

			}
		}
	}

}