package learnJava.rz20200207;

import org.apache.commons.cli.*;

public class ToolCmd {
	public static void main(String[] args) {
		
		CommandLineParser parser = new BasicParser( );
		Options options = new Options( );
		options.addOption("h", "help", false, "Print this usage information");
		options.addOption("c", "cfg", true, "config Absolute Path");
		options.addOption("l", "log", true, "log configuration");
//		System.out.println("OK");

		// Parse the program arguments

		try {
			CommandLine commandLine = parser.parse( options, args );
			if( commandLine.hasOption('h') ) {
				printHelpMessage();
				System.exit(0);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// Set the appropriate variables based on supplied options
		
		
//		if( commandLine.hasOption('c') ) {
//			cfg = new File(commandLine.getOptionValue('c'));
//		} else {
//			printHelpMessage();
//			System.exit(0);
//		}
//		if( commandLine.hasOption('l') ) {
//			log = new File(commandLine.getOptionValue('l'));
//		} else {
//			printHelpMessage();
//			System.exit(0);
//		}
 
	}
	
	public static void printHelpMessage() {
		System.out.println( "Change the xml File and Log.XML Path to the right Absolute Path base on your project Location in your computor");
		System.out.println("Usage example: ");
		System.out.println( "java -cfg D:\\MyProject\\face2face\\logic\\src\\main\\resources\\logic.xml  -log D:\\MyProject\\face2face\\logic\\src\\main\\resources\\log.xml");
		System.exit(0);
	}
 
}
