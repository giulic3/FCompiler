package testMain;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.yaml.snakeyaml.Yaml;
import utils.Helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

public class TestSuite {
	
	public static void main(String[] args) {
		try {
			Yaml yaml = new Yaml();
			File yamlTestsFile = new File("testSuite.yml");
			InputStream input = new FileInputStream(yamlTestsFile);
			
			Map<String, ArrayList<String>> tests = yaml.load(input);
			
			int testPassed = 0;
			
			StringBuilder testOutput = new StringBuilder();
			for (String curTest : tests.keySet()) {
				ArrayList<String> testContent = tests.get(curTest);
				String code = testContent.get(0);
				String result = testContent.get(1);
				
				String[] titleParts = curTest.split(" - ");
				String testNumber = titleParts[0].replaceFirst("^test", "");
				
				String resString = Helpers.ANSI_YELLOW + "Current Test: " + titleParts[1] + " (" + testNumber + ")" + Helpers.ANSI_RESET + "\n";
				testOutput.append(resString).append(code).append("\n");
				
				CharStream testInput = CharStreams.fromString(code);
				String output = Main.test(testInput, result);
				testOutput.append(output);
				
				if (output.endsWith("Test PASSED!\n")) testPassed++;
				
				System.out.println(testOutput.toString());
				testOutput.setLength(0);
			}
			
			String color = (testPassed == tests.keySet().size()) ? Helpers.ANSI_GREEN : Helpers.ANSI_RED;
			String totalRes = (testPassed == tests.keySet().size()) ? color + "NO ERRORS OCCURRED." : color + "SOME TESTS FAILED.";
			testOutput.append(totalRes);
			String passedStats = " PASSED TESTS: " + testPassed + "/" + tests.keySet().size() + "\n";
			testOutput.append(color).append(passedStats);
			System.out.println(testOutput);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
