package testMain;

import org.apache.commons.cli.*;

public class CommandLineMain {
	
	public static void main(String[] args) {
		
		CommandLineParser parser = new DefaultParser();
		Options ops = buildJAROptions();
		try {
			CommandLine clInput = parser.parse(ops, args);
			
			if (clInput.hasOption("help"))
				showHelpMessage(ops);
			
			if (!clInput.hasOption("input")) {
				System.err.println("Usage Error: Missing input file");
				showHelpMessage(ops);
			}
			
			String inputFileName = clInput.getOptionValue("input");
			boolean verbose = clInput.hasOption("verbose");
			boolean astOnly = clInput.hasOption("ast");
			
			if (clInput.hasOption("test"))
				TestSuite.startTestSuite(inputFileName);
			else
				Main.execute(inputFileName, verbose, astOnly);
		}
		catch (ParseException e) {
			System.err.println("Usage error: " + e.getMessage());
			showHelpMessage(ops);
		}
	}
	
	private static Options buildJAROptions() {
		Options ops = new Options();
		
		Option help = Option.builder("h").longOpt("help").desc("Show help message").build();
		Option ast = Option.builder("a").longOpt("ast").desc("Show AST after semantic checks (no effects in test mode)").build();
		Option input = Option.builder("i").longOpt("input").hasArg().argName("FOOL-filepath").desc("Specify FOOL code input file (REQUIRED)").build();
		Option test = Option.builder("t").longOpt("test").desc("Use input file as YAML test suite").build();
		Option verbose = Option.builder("v").longOpt("verbose").desc("Use verbose execution showing everything, from AST to assembly (no effect in test mode)").build();
		ops.addOption(help).addOption(ast).addOption(input).addOption(test).addOption(verbose);
		
		return ops;
	}
	
	private static void showHelpMessage(Options ops) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("FCompiler.jar", ops);
		System.exit(0);
	}
}
