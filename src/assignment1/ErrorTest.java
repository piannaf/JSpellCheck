package assignment1;

import java.util.ArrayList;
import java.util.Arrays;

// Test driver for Error class
@SuppressWarnings("unused")
public class ErrorTest {

	public static void main(String[] args) {
		final int NUM = 12;  // the number of tests
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
			System.out.println(s);
		}		
	}
	
	// Test 1: toString
	private static String runTest1() {
		// the possible corrections for the error
		ArrayList<String> candidates = 
			new ArrayList<String>(Arrays.asList("ten","tee","tea","the"));
		Error e = new Error(5,"teh",candidates);  // the error
		e.checkInv();
		// Note white space is important
		if (e.toString().equals("teh(5-7): [ten, tee, tea, the]"))
			return "Test 1 passed";
		else 
			return "Test 1 failed\n" + "\tOutput from toString: " + e;
	}

	// Test 2: getCandidates
	private static String runTest2() {
		// the possible corrections for the error
		ArrayList<String> candidates = 
			new ArrayList<String>(Arrays.asList("ten","tea","tee","the"));
		Error e = new Error(5,"the",candidates);  // the error
		if (e.getCandidates().equals(candidates) && e.checkInv()) 
			return "Test 2 passed";
		else 
			return "Test 2 failed\n" + "\tOutput from getCandidates: " 
						+ e.getCandidates();
	}
	
	// Test 3: getStart
	private static String runTest3() {
		// the possible corrections for the error
		ArrayList<String> candidates = 
			new ArrayList<String>(Arrays.asList("ten","tea","tee","the"));
		Error e = new Error(5,"the",candidates);  // the error
		if (e.getStart() == 5 && e.checkInv()) 
			return "Test 3 passed";
		else 
			return "Test 3 failed\n" + "\tOutput from getStart: " 
					+ e.getStart();
	}
	
	// Test 4: getEnd
	private static String runTest4() {
		// the possible corrections for the error
		ArrayList<String> candidates = 
			new ArrayList<String>(Arrays.asList("ten","tea","tee","the"));
		Error e = new Error(5,"the",candidates);  // the error
		if (e.getEnd() == 7 && e.checkInv()) 
			return "Test 4 passed";
		else 
			return "Test 4 failed\n" + "\tOutput from getEnd: " 
						+ e.getEnd();
	}
	
	// Test 5: getWord
	private static String runTest5() {
		// the possible corrections for the error
		ArrayList<String> candidates = 
			new ArrayList<String>(Arrays.asList("ten","tea","tee","the"));
		Error e = new Error(5,"the",candidates);  // the error
		if (e.getWord().equals("the") && e.checkInv()) 
			return "Test 5 passed";
		else 
			return "Test 5 failed\n" + "\tOutput from getWord: " 
						+ e.getWord();
	}
	
	// Test 6: NullPointerException, if word is null
	private static String runTest6() {
		// the possible corrections for the error
		ArrayList<String> candidates = 
			new ArrayList<String>(Arrays.asList("ten","tea","tee","the"));
		try {
			Error e = new Error(5,null,candidates);
		} catch(NullPointerException err) {
			return "Test 6 passed";
		}
		return "Test 6 failed\n" + "\tWord allowed to be null";
	}
	
	// Test 7: NullPointerException, if candidates is null
	private static String runTest7() {
		// the possible corrections for the error
		ArrayList<String> candidates = null;
		try {
			Error e = new Error(5,"teh",candidates);
		} catch(NullPointerException err) {
			return "Test 7 passed";
		}
		return "Test 7 failed\n" + "\tCandidates allowed to be null";
	}
	
	// Test 8: InvalidErrorException, if word is the empty String
	private static String runTest8() {
		// the possible corrections for the error
		ArrayList<String> candidates = 
			new ArrayList<String>(Arrays.asList("ten","the","tee","the"));
		try {
			Error e = new Error(5,"",candidates);
		} catch(InvalidErrorException err) {
			return "Test 8 passed";
		}
		return "Test 8 failed\n" + "\tWord allowed to be empty";
	}
	
	// Test 9: InvalidErrorException, if any element of candidates is null
	private static String runTest9() {
		// the possible corrections for the error
		ArrayList<String> candidates = 
			new ArrayList<String>(Arrays.asList("ten",null,"tee","the"));
		try {
			Error e = new Error(5,"teh",candidates);
		} catch(InvalidErrorException err) {
			return "Test 9 passed";
		}
		return "Test 9 failed\n" + "\tNull infiltration for list";
	}
	
	// Test 10: InvalidErrorException, if any element of candidates is the empty String
	private static String runTest10() {
		// the possible corrections for the error
		ArrayList<String> candidates = 
			new ArrayList<String>(Arrays.asList("ten","","tee","the"));
		try {
			Error e = new Error(5,"teh",candidates);
		} catch(InvalidErrorException err) {
			return "Test 10 passed";
		}
		return "Test 10 failed\n" + "\tEmpty string infiltration for list";
	}
	
	// Test 11: No duplicates allowed
	private static String runTest11() {
		// the possible corrections for the error
		ArrayList<String> candidates = 
			new ArrayList<String>(Arrays.asList("ten","the","tee","the"));
		try {
			Error e = new Error(5,"the",candidates);
		} catch(InvalidErrorException err) {
			return "Test 11 passed";
		}
		return "Test 11 failed\n" + "\tDuplicate infiltration for list";
	}
	
	
	// Test 12: equals
	private static String runTest12() {
		ArrayList<String> candidates = 
			new ArrayList<String>(Arrays.asList("ten","tea","tee","the"));
		Error e = new Error(5,"the",candidates);
		Error e2 = new Error(5,"the",candidates);
		Error e3 = new Error(5,"the",candidates);

		ArrayList<String> candidates2 = 
			new ArrayList<String>(Arrays.asList("tea","the","ten","tee"));
		Error e4 = new Error(5,"the",candidates2);  // the error
		if (e.equals(e2) && e2.equals(e) && 
				e2.equals(e3) && e.equals(e3) &&
				!e.equals(e4) && !e4.equals(e)) {
			return "Test 12 passed";
		}
		else 
			return "Test 12 failed\n" + "\tEquals not working..." 
						+ e.getWord();
	}
}
