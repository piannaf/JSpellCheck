package assignment2;

import java.io.*;

public class JustinTest {

	public static void main(String[] args) throws IOException {
		SpellChecker s = new SpellChecker();
		
		/*
		 * Lorem Ipsum test. Misspelled words appear first on a line
		 * Some words contain leading and trailing garbage (ignored)
		 * Some words have garbage in the middle (not ignored)
		 */
		s.loadDictionary("lipsum.txt");
		s.loadDocument("lipsumErr.txt");
		
		s.correctError("Lorem-ipsum");
		s.correctError("Donec1vulputate");
		s.correctError("Mauris%^nec");
		s.correctError("Vivamus*0)consectetur");
		s.correctError("Curabitur");
		s.correctError("Donec");
		s.correctError("Aliquam");
		s.correctError("Duis");
		s.correctError("Ut");
		s.correctError("Donec");
		s.correctError("Cras");
		s.correctError("Pellentesque");
		s.correctError("Ut");
		s.correctError("Praesent");
		s.correctError("Etiam2734892ullamcorper");
		s.correctError("Praesent2q3&*(accumsan");
		s.correctError("Duis98-95commodo");
		s.correctError("Phasellus");
		s.correctError("Cras");
		s.correctError("Mauris");
		s.correctError("Mauris");
		s.correctError("Curabitur");
		s.correctError("Nullam");
		s.writeDocument("lipsumCorrect.txt");
		
		BufferedReader br1 = new BufferedReader(new FileReader("lipsum.txt"));
		BufferedReader br2 = new BufferedReader(new FileReader("lipsumCorrect.txt"));
		String line1;
		String line2;
		while((line1 = br1.readLine()) != null) {
			line2 = br2.readLine();
			if(!line1.equals(line2)) {
				System.out.println("Lipsum Failed at line: ");
				System.out.println("\tLine1:\t" + line1);
				System.out.println("\tLine2:\t" + line2);
				return;
			}
		}
		System.out.println("Lipsum Passed");
		
		/*
		 * Commonly misspelled words test. Misspelled words occur at
		 * regular intervals following a pattern.
		 * All words are normal English words (no garbage characters).
		 */
		s.loadDictionary("simpleTestDict.txt");
		s.loadDocument("simpleTestDocErr.txt");
		s.correctError("address");
		s.correctError("advice");
		s.correctError("argument");
		s.correctError("athlete");
		s.correctError("awful");
		s.correctError("becoming");
		s.correctError("beginning");
		s.correctError("believe");
		s.correctError("benefit");
		s.correctError("brilliant");
		s.correctError("business");
		s.correctError("calendar");
		s.correctError("chief");
		s.correctError("coming");
		s.correctError("criticize");
		s.correctError("decide");
		s.correctError("definite");
		s.correctError("desperate");
		s.correctError("difference");
		s.correctError("dilemma");
		s.correctError("disappear");
		s.correctError("discipline");
		s.correctError("does");
		s.correctError("during");
		s.correctError("equipped");
		s.correctError("excellent");
		s.correctError("existence");
		s.correctError("expect");
		s.correctError("experience");
		s.correctError("familiar");
		s.correctError("finally");
		s.correctError("foreign");
		s.correctError("forty");
		s.correctError("friend");
		s.correctError("fundamental");
		s.correctError("generally");
		s.correctError("heroes");
		s.correctError("identity");
		s.correctError("immediately");
		s.correctError("incidentally");
		s.correctError("independent");
		s.correctError("interfere");
		s.correctError("interruption");
		s.correctError("invitation");
		s.correctError("irrelevant");
		s.correctError("island");
		s.correctError("jealous");
		s.correctError("judgment");
		s.writeDocument("simpleTestDocCorrected.txt");
		
		br1 = new BufferedReader(new FileReader("simpleTestDict.txt"));
		br2 = new BufferedReader(new FileReader("simpleTestDocCorrected.txt"));
		while((line1 = br1.readLine()) != null) {
			/*
			 * tabs will get messed up so ignore them by replacing any
			 * non-alpha characters occurring at the end of the line with "".
			 */
			line1 = line1.replaceAll("[^\\p{Alpha}]*$", "");
			line2 = br2.readLine().replaceAll("[^\\p{Alpha}]*$", "");
			if(!line1.equals(line2)) {
				System.out.println("Simple Failed at line: ");
				System.out.println("\tLine1:\t" + line1);
				System.out.println("\tLine2:\t" + line2);
				return;
			}
		}
		System.out.println("Simple Passed");
	}
	
}
