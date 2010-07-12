package assignment2;

import java.util.ArrayList;
import java.io.IOException;
import assignment1.*;
import assignment1.Error;

// Test driver for Dictionary class
public class SpellCheckerTest {
	
	public static void main(String[] args) {
		final int NUM = 25;  // the number of tests
		String s = "";  // the result of a test
		for (int i=1; i <= NUM; i++) {
			if (i==1) s = runTest1();
			if (i==2) s = runTest2();
			if (i==3) s = runTest3();
			if (i==4) s = runTest4();
			if (i==5) s = runTest5();
			if (i==6) s = runTest6();
			if (i==7) s = runTest7();
			if (i==8) s = runTest8();
			if (i==9) s = runTest9();
			if (i==10) s = runTest10();
			if (i==11) s = runTest11();
			if (i==12) s = runTest12();
			if (i==13) s = runTest13();
			if (i==14) s = runTest14();
			if (i==15) s = runTest15();
			if (i==16) s = runTest16();
			if (i==17) s = runTest17();
			if (i==18) s = runTest18();
			if (i==19) s = runTest19();
			if (i==20) s = runTest20();
			if (i==21) s = runTest21();
			if (i==22) s = runTest22();
			if (i==23) s = runTest23();
			if (i==24) s = runTest24();
			if (i==25) s = runTest25();
			System.out.println(s);
		}
	}
	
	/*
	 * Functionality Tests
	 */
	
	// Test 1: Load an Empty dictionary
	private static String runTest1() {
		SpellChecker spellCheck = new SpellChecker();
		try {
			spellCheck.loadDictionary("emptyDict.txt");
		} catch (IOException err) { return err.toString(); }
		
		if(spellCheck.dictionary.toString().equals("") && 
				spellCheck.invCheck()) {
			return "Test 1 Passed";
		}
		return "Test 1 Failed\n" + "\tDictionary Contains: " + 
			spellCheck.dictionary.toString().replace("\n","\n\t\t");
	}
	
	// Test 2: Load a small dictionary
	private static String runTest2() {
		SpellChecker spellCheck = new SpellChecker();
		try {
			spellCheck.loadDictionary("smallDict.txt");
		} catch (IOException err) { return err.toString(); }
		
		Dictionary checkDict = new Dictionary();
		checkDict.add("I'm");
		checkDict.add("It's");
		checkDict.add("a");
		checkDict.add("a8b");
		checkDict.add("afraid");
		checkDict.add("b");
		checkDict.add("c");
		checkDict.add("hit-and-miss");
		checkDict.add("little");

		if(spellCheck.dictionary.toString().equals(checkDict.toString()) && 
				spellCheck.invCheck()) {
			return "Test 2 Passed";
		}
		return "Test 2 Failed\n" + "\tDictionary Contains: " + 
			spellCheck.dictionary.toString().replace("\n","\n\t\t");
	}
	
	// Test 3: Load an empty document
	private static String runTest3() {
		SpellChecker spellCheck = new SpellChecker();
		try {
			spellCheck.loadDictionary("mediumDict.txt");
		} catch (IOException err) { return err.toString(); }
		
		try {
			spellCheck.loadDocument("emptyDoc.txt");
		} catch (IOException err) { return err.toString(); }

		if(spellCheck.document.toString().equals((new Document().toString())) && 
				spellCheck.invCheck()) {
			return "Test 3 Passed";
		}
		return "Test 3 Failed\n" + "\tDocument Contains: " + 
			spellCheck.document.toString().replace("\n","\n\t\t");
	}
	
	// Test 4: Load a small document (no errors)
	private static String runTest4() {
		SpellChecker spellCheck = new SpellChecker();
		try {
			spellCheck.loadDictionary("smallDoc.txt");
		} catch (IOException err) { return err.toString(); }
		
		try {
			spellCheck.loadDocument("smallDoc.txt");
		} catch (IOException err) { return err.toString(); }

		Document docCheck = new Document();
		docCheck.add(new Line("Halt! This is a very small document.", 
				new ArrayList<Error>()));
		docCheck.add(new Line("", new ArrayList<Error>()));
		docCheck.add(new Line("There are very few sentences, not much punctuation, and I'm not going to worry about crazy errors.",
				new ArrayList<Error>()));
		docCheck.add(new Line("", new ArrayList<Error>()));
		docCheck.add(new Line("Cats don't like water. They also don't like dogs. A wet dog is the worst!", 
				new ArrayList<Error>()));
		
		if(spellCheck.document.toString().equals(docCheck.toString()) && 
				spellCheck.invCheck()) {
			return "Test 4 Passed";
		}
		return "Test 4 Failed\n" + "\tDocument Contains: " + 
			spellCheck.document.toString().replace("\n","\n\t\t") + 
			"\n\tShould be: " + docCheck.toString().replace("\n","\n\t\t");
	}
	
	// Test 5: Load a small document (with errors)
	private static String runTest5() {
		SpellChecker spellCheck = new SpellChecker();
		try {
			spellCheck.loadDictionary("smallDoc.txt");
		} catch (IOException err) { return err.toString(); }
		
		try {
			spellCheck.loadDocument("smallDocErrors.txt");
		} catch (IOException err) { return err.toString(); }

		Document docCheck = new Document();
		ArrayList<Error> e;
		ArrayList<String> c;
		
		c = new ArrayList<String>();
		c.add("Halt");
		e = new ArrayList<Error>();
		e.add(new Error(0, "Hlt", c));
		
		c = new ArrayList<String>();
		c.add("This");
		e.add(new Error(5, "Thiis", c));
		
		docCheck.add(new Line("Hlt! Thiis is a very small document.", e));
		docCheck.add(new Line("", new ArrayList<Error>()));
		
		c = new ArrayList<String>();
		c.add("very");
		e = new ArrayList<Error>();
		e.add(new Error(10, "vry", c));
		docCheck.add(new Line("There are vry few sentences, not much " +
				"punctuation, and I'm not going to worry about crazy errors.", 
				e));
		docCheck.add(new Line("", new ArrayList<Error>()));
		
		c = new ArrayList<String>();
		c.add("like");
		e = new ArrayList<Error>();
		e.add(new Error(11, "liek", c));
		c = new ArrayList<String>();
		c.add("dog");
		e.add(new Error(56, "dag", c));
		docCheck.add(new Line("Cats don't liek water. They also don't like dogs. A wet dag is the worst!", 
				e));
		
		if(spellCheck.document.toString().equals(docCheck.toString()) && 
				spellCheck.invCheck()) {
			return "Test 5 Passed";
		}
		return "Test 5 Failed\n" + "\tDocument Contains: " + 
			spellCheck.document.toString().replace("\n","\n\t\t") + 
			"\n\tShould be: " + docCheck.toString().replace("\n","\n\t\t");
	}

	// Test 6: getLine (with errors)
	private static String runTest6() {
		SpellChecker spellCheck = new SpellChecker();
		try {
			spellCheck.loadDictionary("smallDoc.txt");
		} catch (IOException err) { return err.toString(); }
		
		try {
			spellCheck.loadDocument("smallDocErrors.txt");
		} catch (IOException err) { return err.toString(); }
				
		if(spellCheck.getLine().equals("Hlt! Thiis is a very small document.") && 
				spellCheck.invCheck()) {
			return "Test 6 Passed";
		}
		return "Test 6 Failed\n" + "\tgetLine Returns: " + 
			spellCheck.getLine().replace("\n","\n\t\t") + 
			"\n\tShould be: " + "\n\t\tThiis is a very small document.";
	}
	
	// Test 7: getError (with errors)
	private static String runTest7() {
		SpellChecker spellCheck = new SpellChecker();
		try {
			spellCheck.loadDictionary("smallDoc.txt");
		} catch (IOException err) { return err.toString(); }
		
		try {
			spellCheck.loadDocument("smallDocErrors.txt");
		} catch (IOException err) { return err.toString(); }
				
		if(spellCheck.getError().equals("Hlt") && 
				spellCheck.invCheck()) {
			return "Test 7 Passed";
		}
		return "Test 7 Failed\n" + "\tgetError Returns: " + 
			spellCheck.getError().replace("\n","\n\t\t") + 
			"\n\tShould be: " + "\n\t\tThiis";
	}
	
	// Test 8: getCorrections (with errors)
	private static String runTest8() {
		SpellChecker spellCheck = new SpellChecker();
		try {
			spellCheck.loadDictionary("smallDoc.txt");
		} catch (IOException err) { return err.toString(); }
		
		try {
			spellCheck.loadDocument("smallDocErrors.txt");
		} catch (IOException err) { return err.toString(); }
				
		ArrayList<String> c = new ArrayList<String>();
		c.add("Halt");
		
		if(spellCheck.getCorrections().equals(c) && 
				spellCheck.invCheck()) {
			return "Test 8 Passed";
		}
		return "Test 8 Failed\n" + "\tgetCorrections Returns: " + 
			spellCheck.getCorrections().toString().replace("\n","\n\t\t") + 
			"\n\tShould be: " + "\n\t\t" + c;
	}
	
	// Test 9: correctError (with errors)
	private static String runTest9() {
		SpellChecker spellCheck = new SpellChecker();
		try {
			spellCheck.loadDictionary("smallDoc.txt");
		} catch (IOException err) { return err.toString(); }
		
		try {
			spellCheck.loadDocument("smallDocErrors.txt");
		} catch (IOException err) { return err.toString(); }
				
		ArrayList<String> c = new ArrayList<String>();
		c.add("this");
		
		String correctedText = spellCheck.correctError("This");
		
		
		if(correctedText.equals("This! Thiis is a very small document.") && 
				spellCheck.getError().equals("Thiis") && 
				spellCheck.invCheck()) {
			return "Test 9 Passed";
		}
		return "Test 9 Failed\n" + "\tcorrectError Returns: " + 
			correctedText.replace("\n","\n\t\t") + 
			"\n\tShould be: " + "\n\t\tthis is a very small document.";
	}

	/*
	 * Error Handling Tests
	 */
	
	// Test 10: Load a dictionary that DNE
	private static String runTest10() {
		SpellChecker spellCheck = new SpellChecker();
		try {
			spellCheck.loadDictionary("DNE");
		} catch (IOException err) { 
			return "Test 10 Passed"; 
		}
		return "Test 10 Failed";
	}
	
	// Test 11: Load a document when dictionary DNE
	private static String runTest11() {
		SpellChecker spellCheck = new SpellChecker();
		try {
			spellCheck.loadDocument("emptyDoc.txt");
		} 	catch (IOException err) { return err.toString(); }
			catch (NoDictionaryException err) { return "Test 11 Passed"; }
		return "Test 11 Failed";
	}
	
	// Test 12: Load a document that DNE
	private static String runTest12() {
		SpellChecker spellCheck = new SpellChecker();
		try {
			spellCheck.loadDictionary("mediumDict.txt");
		} catch (IOException err) { return err.toString(); }
		
		try {
			spellCheck.loadDocument("DNE");
		} 	catch (IOException err) { return "Test 12 Passed"; }
		
		return "Test 12 Failed";
	}
	
	// Test 13: getLine (no document loaded)
	private static String runTest13() {
		SpellChecker spellCheck = new SpellChecker();
		try {
			spellCheck.loadDictionary("smallDoc.txt");
		} catch (IOException err) { return err.toString(); }
		
		try {
			spellCheck.getLine();
		} catch (NoDocumentException err) { return "Test 13 Passed"; }
		return "Test 13 Failed";
	}
	
	// Test 14: getLine (no errors)
	private static String runTest14() {
		SpellChecker spellCheck = new SpellChecker();
		try {
			spellCheck.loadDictionary("smallDoc.txt");
		} catch (IOException err) { return err.toString(); }
		
		try {
			spellCheck.loadDocument("smallDoc.txt");
		} catch (IOException err) { return err.toString(); }
		
		try {
			spellCheck.getLine();
		} catch (NoSuchLineException err) { return "Test 14 Passed"; }
		return "Test 14 Failed";
	}
	
	// Test 15: getError (no document loaded)
	private static String runTest15() {
		SpellChecker spellCheck = new SpellChecker();
		try {
			spellCheck.loadDictionary("smallDoc.txt");
		} catch (IOException err) { return err.toString(); }
		
		try {
			spellCheck.getError();
		} catch (NoDocumentException err) { return "Test 15 Passed"; }
		return "Test 15 Failed";
	}
	
	// Test 16: getError (no errors)
	private static String runTest16() {
		SpellChecker spellCheck = new SpellChecker();
		try {
			spellCheck.loadDictionary("smallDoc.txt");
		} catch (IOException err) { return err.toString(); }
		
		try {
			spellCheck.loadDocument("smallDoc.txt");
		} catch (IOException err) { return err.toString(); }
		
		try {
			spellCheck.getError();
		} catch (NoSuchErrorException err) { return "Test 16 Passed"; }
		return "Test 16 Failed";
	}

	// Test 17: getCorrections (no document loaded)
	private static String runTest17() {
		SpellChecker spellCheck = new SpellChecker();
		try {
			spellCheck.loadDictionary("smallDoc.txt");
		} catch (IOException err) { return err.toString(); }
		
		try {
			spellCheck.getCorrections();
		} catch (NoDocumentException err) { return "Test 17 Passed"; }
		return "Test 17 Failed";
	}
	
	// Test 18: getCorrections (no errors)
	private static String runTest18() {
		SpellChecker spellCheck = new SpellChecker();
		try {
			spellCheck.loadDictionary("smallDoc.txt");
		} catch (IOException err) { return err.toString(); }
		
		try {
			spellCheck.loadDocument("smallDoc.txt");
		} catch (IOException err) { return err.toString(); }
		
		try {
			spellCheck.getCorrections();
		} catch (NoSuchErrorException err) { return "Test 18 Passed"; }
		return "Test 18 Failed";
	}

	// Test 19: correctError (no document loaded)
	private static String runTest19() {
		SpellChecker spellCheck = new SpellChecker();
		try {
			spellCheck.loadDictionary("smallDoc.txt");
		} catch (IOException err) { return err.toString(); }
		
		try {
			spellCheck.correctError("foobar");
		} catch (NoDocumentException err) { return "Test 19 Passed"; }
		return "Test 19 Failed";
	}
	
	// Test 20: correctError (no errors)
	private static String runTest20() {
		SpellChecker spellCheck = new SpellChecker();
		try {
			spellCheck.loadDictionary("smallDoc.txt");
		} catch (IOException err) { return err.toString(); }
		
		try {
			spellCheck.loadDocument("smallDoc.txt");
		} catch (IOException err) { return err.toString(); }
		
		try {
			spellCheck.correctError("foobar");
		} catch (NoSuchErrorException err) { return "Test 20 Passed"; }
		return "Test 20 Failed";
	}

	// Test 21: writeDocument (no document loaded)
	private static String runTest21() {
		SpellChecker spellCheck = new SpellChecker();
		try {
			spellCheck.loadDictionary("smallDoc.txt");
		} catch (IOException err) { return err.toString(); }
		
		try {
			spellCheck.writeDocument("readonly.txt");
		} catch (NoDocumentException err) { return "Test 21 Passed"; }
		catch (IOException err) { return err.toString(); }
		return "Test 21 Failed";
	}
	
	// Test 22: writeDocument (file for writing is readonly)
	private static String runTest22() {
		SpellChecker spellCheck = new SpellChecker();
		try {
			spellCheck.loadDictionary("smallDoc.txt");
		} catch (IOException err) { return err.toString(); }
		
		try {
			spellCheck.loadDocument("smallDoc.txt");
		} catch (IOException err) { return err.toString(); }
		
		try {
			spellCheck.writeDocument("readonly.txt");
		} catch (IOException err) { return "Test 22 Passed"; }
		return "Test 22 Failed";
	}

	/*
	 * toString tests
	 */
	
	// Test 23: toString (no dictionary and no document)
	private static String runTest23() {
		SpellChecker spellCheck = new SpellChecker();
		String expected = "Current State of SpellChecker:" +
		"\n\tDictionary: Not Loaded" +
		"\n\tDocument: Not Loaded";
		
		if(spellCheck.toString().equals(expected)) {
			return "Test 23 Passed";
		}
		return "Test 23 Failed\n" + "\tOutput from toString: " + 
			spellCheck.toString().replace("\n","\n\t\t") + 
			"\n\tExpected: " + expected.toString().replace("\n","\n\t\t");
	}

	// Test 24: toString (no document)
	private static String runTest24() {
		SpellChecker spellCheck = new SpellChecker();
		try {
			spellCheck.loadDictionary("smallDict.txt");
		} catch (IOException err) { return err.toString(); }
		
		String expected = "Current State of SpellChecker:" +
		"\n\tDictionary: \n\t\t" + spellCheck.dictionary.toString().replace("\n","\n\t\t") +
		"\n\tDocument: Not Loaded";
		
		if(spellCheck.toString().equals(expected)) {
			return "Test 24 Passed";
		}
		return "Test 24 Failed\n" + "\tOutput from toString: " + 
			spellCheck.toString().replace("\n","\n\t\t") + 
			"\n\tExpected: " + expected.toString().replace("\n","\n\t\t");
	}

	// Test 25: toString (no document)
	private static String runTest25() {
		SpellChecker spellCheck = new SpellChecker();
		try {
			spellCheck.loadDictionary("smallDict.txt");
		} catch (IOException err) { return err.toString(); }

		try {
			spellCheck.loadDocument("smallDoc.txt");
		} catch (IOException err) { return err.toString(); }
		
		String expected = "Current State of SpellChecker:" +
		"\n\tDictionary: \n\t\t" + spellCheck.dictionary.toString().replace("\n","\n\t\t") +
		"\n\tDocument: \n\t\t" + spellCheck.document.toString().replace("\n", "\n\t\t");
		
		if(spellCheck.toString().equals(expected)) {
			return "Test 25 Passed";
		}
		return "Test 25 Failed\n" + "\tOutput from toString: " + 
			spellCheck.toString().replace("\n","\n\t\t") + 
			"\n\tExpected: " + expected.toString().replace("\n","\n\t\t");
	}
}

