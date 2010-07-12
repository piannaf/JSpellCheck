package assignment2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import assignment1.*;

// Test driver for SpellChecker class
public class PabloSpellCheckerTest {
	
	public static void main(String[] args) {
		
		final int NUM = 14;  // the number of tests
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
			System.out.println(s);
		}		
	}
	
	// Test 1: load non existing file for the Dictionary
	private static String runTest1() {
		
		SpellChecker sp = new SpellChecker();
		
		try {
			sp.loadDictionary("test_diczczxczt.txt");
		} catch (IOException e) {
			
			return "Test 1 passed";
		}
		
		return "Test 1 failed. No IOException for unexisting file";
	}
	
	
	// Test 2: load a text file for the Dictionary
	private static String runTest2() {
		
		SpellChecker sp = new SpellChecker();
		
		try {
			sp.loadDictionary("test_dict.txt");
		}
		
		catch (IOException e) {
			
			return "Test 2 failed. IOException";
		}
				
		return "Test 2 passed";
	}
	
	// Test 3: load an empty text file for the Dictionary
	private static String runTest3() {
		
		SpellChecker sp = new SpellChecker();
		
		try {
			sp.loadDictionary("test_dict_blank.txt");
		}
		
		catch (IOException e) {
			
			return "Test 3 failed. IOException";
		}
				
		return "Test 3 passed";
	}
	
	// Test 4: load a text file for the Dictionary with no valid letters
	private static String runTest4() {
		
		SpellChecker sp = new SpellChecker();
		
		try {
			sp.loadDictionary("test_dict_nonletter.txt");
		}
		
		catch (IOException e) {
			
			return "Test 4 failed. IOException";
		}
				
		return "Test 4 passed";
	}
	
	//Test 5. load a Document after loading a Dictionary
	private static String runTest5() {
		
		SpellChecker sp = new SpellChecker();
		
		try {
			sp.loadDictionary("dict.txt");
			sp.loadDocument("doc.txt");
		}
		
		catch (IOException e) {
			
			return "Test 5 failed. IOException";
		}
				
		return "Test 5 passed";
	}
	
	//Test 6. load document before dict
	private static String runTest6() {
		
		SpellChecker sp = new SpellChecker();
		
		try {
			sp.loadDocument("dict.txt");
			
		}
		catch (NoDictionaryException e) {
			
			return "Test 6 passed.";
		}
		
		catch (IOException e) {
			return "Test 6 couldn't be tested, invalid file";
		}
		
		return "Test 6 Failed!";
		
	}
	
	//Test 7. getLine without loaded dictionary
	private static String runTest7() {
		
		SpellChecker sp = new SpellChecker();
		
		try {
			
			sp.getLine();
		}
		catch (NoDocumentException e) {
			
			return "Test 7 passed.";
		}		
		
		return "Test 7 Failed!";		
	}
	
	//Test 8. test getLine
	private static String runTest8() {
		
		SpellChecker sp = new SpellChecker();
		String s = "";
		try {
			
			sp.loadDictionary("test_wikipedia_dict.txt");
			sp.loadDocument("test_wikipedia.txt");
			s=sp.getLine();

		}		
		
		catch (IOException e) {
			return "Test couldn't be tested, invalid file";
		}
		
		if(s.toString().equals("Topical Sorm Barry was a rapidly forming tropical )))((("))
			return "Test 8 passed ";
		
		else
			return "Test 8 failed. Returned text: " + s;		
		

	}
	
	//Test 9. test getError
	private static String runTest9() {
		
		SpellChecker sp = new SpellChecker();
		String s = "";
		try {
			
			sp.loadDictionary("test_wikipedia_dict.txt");
			sp.loadDocument("test_wikipedia.txt");
			s=sp.getError();

		}		
		
		catch (IOException e) {
			return "Test couldn't be tested, invalid file";
		}

		
		if(s.toString().equals("Topical"))
			return "Test 9 passed ";
		
		else
			return "Test 9 failed. Returned text: " + s;

	}
	
	//Test 10. test getCorrections
	private static String runTest10() {
		
		SpellChecker sp = new SpellChecker();
		ArrayList<String> s;
		try {
			
			sp.loadDictionary("test_wikipedia_dict.txt");
			sp.loadDocument("test_wikipedia.txt");
			s=sp.getCorrections();

		}		
		
		catch (IOException e) {
			return "Test couldn't be tested, invalid file";
		}	

		
		if(s.toString().equals("[Toopical, Tropical]"))
			return "Test 10 passed ";
		
		else
			return "Test 10 failed. Returned text: " + s;

	}
	

	//Test 11. test correctError
	private static String runTest11() {
		
		SpellChecker sp = new SpellChecker();
		String s;
		try {
			
			sp.loadDictionary("test_wikipedia_dict.txt");
			sp.loadDocument("test_wikipedia.txt");
			sp.correctError("Tropical");
			sp.correctError("Storm");
			s=sp.correctError("that");

		}		
		
		catch (IOException e) {
			return "Test couldn't be tested, invalid file";
		}
		
		if(s.equals("cyclone that made landfall on Florida in early June."))
			return "Test 11 passed ";
		
		else
			return "Test 11 failed. Returned text: " + s;

	}
	
	//Test 12. test writeDocument after correcting some errors
	private static String runTest12() {
		
		try {
		
			SpellChecker sp = new SpellChecker();
				
			sp.loadDictionary("test_wikipedia_dict.txt");
			sp.loadDocument("test_wikipedia.txt");
			sp.correctError("Tropical");
			sp.correctError("Storm");
			sp.correctError("that");
			sp.writeDocument("write_here1.txt");
			
			//load the generated file
			FileReader genFile = new FileReader("write_here1.txt");
			
			//load manually corrected file
			FileReader manualFile = new FileReader("test_wikipedia_manual.txt");
			
			//compare the files
			if(compareFiles(genFile,manualFile))
				return "Test 12 passed";
			else
				return "Test 12 failed, generated file is not equal to manually corrected one";
			
		}
		
		catch (IOException e) {
			return "Test couldn't be tested, invalid file";
		}
	}
		
	
	//Test 13. test writeDocument after correcting NO errors
	private static String runTest13() {
		
		try {
		
			SpellChecker sp = new SpellChecker();
				
			sp.loadDictionary("test_wikipedia_dict.txt");
			sp.loadDocument("test_wikipedia.txt");
			
			sp.writeDocument("write_here1.txt");
			
			//load the generated file
			FileReader genFile = new FileReader("write_here1.txt");
			
			//load original file
			FileReader originalFile = new FileReader("test_wikipedia.txt");
			
			//compare the files
			if(compareFiles(genFile,originalFile))
				return "Test 13 passed";
			else
				return "Test 13 failed, generated file is not equal to the original one";
			
		}
		
		catch (IOException e) {
			return "Test couldn't be tested, invalid file";
		}
	}
	
	
	//Test 14. test NoSuchErrorException
	private static String runTest14() {
		
		try {
		
			SpellChecker sp = new SpellChecker();
				
			sp.loadDictionary("test_wikipedia_dict.txt");
			sp.loadDocument("test_wikipedia.txt");
			sp.correctError("Tropical");
			sp.correctError("Storm");
			sp.correctError("that");
			sp.correctError("second");
			sp.correctError("hurricane");
			sp.correctError("low");
			sp.correctError("Rough");	
			sp.correctError("extra_word");	
			
		}
		
		catch (IOException e) {
			return "Test couldn't be tested, invalid file";
		}
		
		catch (NoSuchErrorException e) {
			return "Test 14 passed";
		}
		
		return "Test 14 failed, NoSuchErrorException wasnt thrown";
	}
	
	
		//To compare the contents of two text files
		private static boolean compareFiles (FileReader f1, FileReader f2) 
		throws IOException  {
			
			BufferedReader bf1 = new BufferedReader(f1);
			BufferedReader bf2 = new BufferedReader(f2);
			String line1 = bf1.readLine();
			String line2 = bf2.readLine();
						
			while (line1 != null && line2  != null) {
				
				line1 = bf1.readLine();
				line2 = bf2.readLine();
				
				//compare lines
				if(line1 != null && line2  != null)
					if(!line1.equals(line2))
						return false;
			}
			
			//check if sizes are not the same
			if (line1 != null || line2  != null)
				return false;
			
			return true;
		
	}
	
	
}