package assignment2;

import java.util.ArrayList;

// Test driver for Dictionary class
public class DictionaryTest {
	
	public static void main(String[] args) {
		final int NUM = 10;  // the number of tests
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
			System.out.println(s);
		}
	}
	
	// Test 1: Create new empty dictionary
	private static String runTest1() {
		Dictionary d = new Dictionary();
		if(d.toString().equals("") && d.invCheck()) {
			return "Test 1 Passed";
		}
		return "Test 1 Failed\n" + "\tOutput from toString: " + 
				d.toString().replace("\n","\n\t\t");
	}
	
	// Test 2: Add to empty dictionary
	private static String runTest2() {
		Dictionary d = new Dictionary();
		d.add("foobar");
		if(d.toString().equals("0 foobar\n") && d.invCheck()) {
			return "Test 2 Passed";
		}
		return "Test 2 Failed\n" + "\tOutput from toString: " + 
				d.toString().replace("\n","\n\t\t");
	}
	
	// Test 3: word not found in dictionary
	private static String runTest3() {
		Dictionary d = new Dictionary();
		d.add("foobar");
		if(!d.lookup("qwerty") && d.invCheck()) {
			return "Test 3 Passed";
		}
		return "Test 3 Failed\n";
	}
	
	// Test 4: word is found in dictionary
	private static String runTest4() {
		Dictionary d = new Dictionary();
		d.add("FooBar");
		if(d.lookup("foobar") && d.lookup("FOOBAR")&& d.invCheck()) {
			return "Test 4 Passed";
		}
		return "Test 4 Failed\n";
	}
	
	// Test 5: getCorrections (word spelled correctly)
	private static String runTest5() {
		Dictionary d = new Dictionary();
		d.add("foobar");
		d.add("querty");
		ArrayList<String> corrections = d.getCorrections("foobar");	
		
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("foobar");
		if(corrections.equals(expected) && d.invCheck()) {
			return "Test 5 Passed";
		}
		return "Test 5 Failed\n" + "\tOutput from getCorrections: \n\t\t" + 
			corrections;
	}
	
	// Test 6: getCorrections (insertionErrors)
	private static String runTest6() {
		Dictionary d = new Dictionary();
		d.add("foobar");
		d.add("querty");
		ArrayList<String> corrections = d.getCorrections("footbar");
		
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("foobar");
		if(containsExactly(corrections, expected) && d.invCheck()) {
			return "Test 6 Passed";
		}
		return "Test 6 Failed\n" + "\tOutput from getCorrections: \n\t\t" + 
			corrections;
	}
	
	// Test 7: getCorrections (deletionErrors)
	private static String runTest7() {
		Dictionary d = new Dictionary();
		d.add("foobar");
		d.add("fobarz");
		d.add("afobar");
		ArrayList<String> corrections = d.getCorrections("fobar");
		
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("foobar");
		expected.add("fobarz");
		expected.add("afobar");
		if(containsExactly(corrections, expected) && d.invCheck()) {
			return "Test 7 Passed";
		}
		
		return "Test 7 Failed\n" + "\tOutput from getCorrections: \n\t\t" + 
			corrections;
	}
	
	// Test 8: getCorrections (substitutionErrors)
	private static String runTest8() {
		Dictionary d = new Dictionary();
		d.add("foobar");
		d.add("fobarz");
		d.add("afobar");
		ArrayList<String> corrections = d.getCorrections("fooban");
		
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("foobar");
		if(containsExactly(corrections, expected) && d.invCheck()) {
			return "Test 8 Passed";
		}
		
		return "Test 8 Failed\n" + "\tOutput from getCorrections: \n\t\t" + 
			corrections;
	}
	
	// Test 9: getCorrections (transpositionErrors)
	private static String runTest9() {
		Dictionary d = new Dictionary();
		d.add("foobar");
		d.add("fobarz");
		d.add("afobar");
		ArrayList<String> corrections = d.getCorrections("fooabr");
		
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("foobar");
		if(containsExactly(corrections, expected) && d.invCheck()) {
			return "Test 9 Passed";
		}
		
		return "Test 9 Failed\n" + "\tOutput from getCorrections: \n\t\t" + 
			corrections;
	}
	
	// Test 10: getCorrections (all four errors)
	private static String runTest10() {
		Dictionary d = new Dictionary();
		d.add("oobar");
		d.add("foobaar");
		d.add("foocar");
		d.add("foobra");
		ArrayList<String> corrections = d.getCorrections("foobar");
		
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("oobar");
		expected.add("foobaar");
		expected.add("foocar");
		expected.add("foobra");
		if(containsExactly(corrections, expected) && d.invCheck()) {
			return "Test 10 Passed";
		}
		
		return "Test 10 Failed\n" + "\tOutput from getCorrections: \n\t\t" + 
			corrections;
	}
	
	private static Boolean containsExactly (ArrayList<String> a, 
			ArrayList<String> b) {
		if(a.containsAll(b) && b.containsAll(a)) return true;
		return false;
	}
}
