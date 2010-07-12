package assignment1;

import java.util.ArrayList;
import java.util.Arrays;

// Test driver for Line class
@SuppressWarnings("unused")
public class LineTest {
	
	public static void main(String[] args) {
		final int NUM = 11;  // the number of tests
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
			System.out.println(s);
		}
	}
	
	// Test 1: toString
	private static String runTest1() {
		// the errors on the line
		ArrayList<Error> errors = new ArrayList<Error>();	
		// the possible corrections for an error on the line
		ArrayList<String> candidates = 
			new ArrayList<String>(Arrays.asList("so","is"));  
		Error e = new Error(5,"si",candidates); // an error on the line
		errors.add(e);
		candidates = new ArrayList<String>(Arrays.asList("second","scone"));
		e = new Error(12,"scond",candidates);	
		errors.add(e);
		Line l = new Line("This si the scond line.",errors); // the line
		// Note white space is important
		if (l.toString().equals("This si the scond line.\n" +
				"Errors: [si(5-6): [so, is], scond(12-16): [second, scone]]"))
			return "Test 1 passed";
		else 
			return "Test 1 failed\n" + "\tOutput from toString:\n\t\t" + 
						l.toString().replace("\n","\n\t\t");
	}
	
	// Test 2: getText
	private static String runTest2() {
		// the errors on the line
		ArrayList<Error> errors = new ArrayList<Error>();	
		// the possible corrections for an error on the line
		ArrayList<String> candidates = 
			new ArrayList<String>(Arrays.asList("so","is"));  
		Error e = new Error(5,"si",candidates); // an error on the line
		errors.add(e);
		candidates = new ArrayList<String>(Arrays.asList("second","scone"));
		e = new Error(12,"scond",candidates);	
		errors.add(e);
		Line l = new Line("This si the scond line.",errors); // the line
		// Note white space is important
		if (l.getText().equals("This si the scond line."))
			return "Test 2 passed";
		else 
			return "Test 2 failed\n" + "\tOutput from getText: " 
					+ l.getText();
	}
	
	// Test 3: getErrors
	private static String runTest3() {
		// the errors on the line
		ArrayList<Error> errors = new ArrayList<Error>();	
		// the possible corrections for an error on the line
		ArrayList<String> candidates = 
			new ArrayList<String>(Arrays.asList("so","is"));  
		Error e = new Error(5,"si",candidates); // an error on the line
		errors.add(e);
		candidates = new ArrayList<String>(Arrays.asList("second","scone"));
		e = new Error(12,"scond",candidates);	
		errors.add(e);
		Line l = new Line("This si the scond line.",errors); // the line
		// Note white space is important
		if (l.getErrors().equals(errors))
			return "Test 3 passed";
		else 
			return "Test 3 failed\n" + "\tOutput from getErrors: " 
					+ l.getErrors();
	}
	
	// Test 4: correctError
	private static String runTest4() {
		// the errors on the line
		ArrayList<Error> errors = new ArrayList<Error>();	
		
		// the possible corrections for an error on the line
		ArrayList<String> candidates = 
			new ArrayList<String>(Arrays.asList("so","is"));  
		Error e = new Error(5,"si",candidates); // an error on the line
		errors.add(e);
		
		candidates = new ArrayList<String>(Arrays.asList("second","scone"));
		e = new Error(12,"scond",candidates);	
		errors.add(e);
		Line l = new Line("This si the scond line.",errors); // the line
		
		//Expected return with which to compare
		ArrayList<Error> errors2 = new ArrayList<Error>();
		candidates = new ArrayList<String>(Arrays.asList("second","scone"));
		e = new Error(15,"scond",candidates);
		errors2.add(e);
		Line l2 = new Line("This isn't the scond line.",errors2);
		
		// Note white space is important
		Line l2b = l.correctError(0, "isn't");
		if (l2b.toString().equals(l2.toString()))
			return "Test 4 passed";
		else 
			return "Test 4 failed\n" + "\tOutput from correctErrors: " 
					+ l2b.toString() + "\nExpected: " + l2.toString();
	}
	
	// Test 5: NullPointerException, if text is null
	private static String runTest5() {
		// the errors on the line
		ArrayList<Error> errors = new ArrayList<Error>();	
		// the possible corrections for an error on the line
		ArrayList<String> candidates = 
			new ArrayList<String>(Arrays.asList("so","is"));  
		Error e = new Error(5,"si",candidates); // an error on the line
		errors.add(e);
		candidates = new ArrayList<String>(Arrays.asList("second","scone"));
		e = new Error(12,"scond",candidates);	
		errors.add(e);
		
		try {
			Line l = new Line(null,errors);
		} catch(NullPointerException err) {
			return "Test 5 passed";
		}
		return "Test 5 failed\n" + "\tText allowed to be null";
	}
	
	// Test 6: NullPointerException, if errors is null
	private static String runTest6() {
		// the errors on the line
		ArrayList<Error> errors = new ArrayList<Error>();	
		// the possible corrections for an error on the line
		ArrayList<String> candidates = 
			new ArrayList<String>(Arrays.asList("so","is"));  
		Error e = new Error(5,"si",candidates); // an error on the line
		errors.add(e);
		candidates = new ArrayList<String>(Arrays.asList("second","scone"));
		e = new Error(12,"scond",candidates);	
		errors.add(e);
		
		try {
			Line l = new Line("This si the scond line.",null);
		} catch(NullPointerException err) {
			return "Test 6 passed";
		}
		return "Test 6 failed\n" + "\tErrors allowed to be null";
	}
	
	// Test 7: InvalidLineException, if any element of errors is null
	private static String runTest7() {
		// the errors on the line
		ArrayList<Error> errors = new ArrayList<Error>();	
		// the possible corrections for an error on the line
		ArrayList<String> candidates = 
			new ArrayList<String>(Arrays.asList("so","is"));  
		Error e = new Error(5,"si",candidates); // an error on the line
		errors.add(e);
		candidates = new ArrayList<String>(Arrays.asList("second","scone"));
		e = new Error(12,"scond",candidates);	
		errors.add(null);
		
		try {
			Line l = new Line("This si the scond line.",errors);
		} catch(InvalidLineException err) {
			return "Test 7 passed";
		}
		return "Test 7 failed\n" + "\tNull infiltration in errors list";
	}
	
	// Test 8: InvalidLineException, if any element of errors is not present in text at the given position
	private static String runTest8() {
		// the errors on the line
		ArrayList<Error> errors = new ArrayList<Error>();	
		// the possible corrections for an error on the line
		ArrayList<String> candidates = 
			new ArrayList<String>(Arrays.asList("so","is"));  
		Error e = new Error(5,"si",candidates); // an error on the line
		errors.add(e);
		candidates = new ArrayList<String>(Arrays.asList("second","scone"));
		e = new Error(13,"scond",candidates);	
		errors.add(e);
		
		try {
			Line l = new Line("This si the scond line.",errors);
		} catch(InvalidLineException err) {
			return "Test 8 passed";
		}
		return "Test 8 failed\n" + "\tAn element of errors DNE at given position in text";
	}
	
	// Test 9: InvalidLineException, if errors does not list the errors in the order in which they appear in text 
	private static String runTest9() {
		// the errors on the line
		ArrayList<Error> errors = new ArrayList<Error>();	
		// the possible corrections for an error on the line
		ArrayList<String> candidates = new ArrayList<String>(Arrays.asList("second","scone"));
		Error e = new Error(12,"scond",candidates);	
		errors.add(e);
		candidates = 
			new ArrayList<String>(Arrays.asList("so","is"));  
		e = new Error(5,"si",candidates);
		errors.add(e);
		
		try {
			Line l = new Line("This si the scond line.",errors);
		} catch(InvalidLineException err) {
			return "Test 9 passed";
		}
		return "Test 9 failed\n" + "\tErrors allowed to be out of order";
	}
	
	// Test 10: Line with no errors
	private static String runTest10() {
		ArrayList<Error> errors = new ArrayList<Error>();	
		Line l = new Line("This is the second line.",errors);
		if (l.toString().equals("This is the second line.\n" +
				"Errors: []"))
			return "Test 10 passed";
		else 
			return "Test 10 failed\n" + "\tOutput from toString:\n\t\t" + 
						l.toString().replace("\n","\n\t\t");
	}
	
	// Test 11: correctError2
	private static String runTest11() {
		// the errors on the line
		ArrayList<Error> errors = new ArrayList<Error>();	
		
		// the possible corrections for an error on the line
		ArrayList<String> candidates = 
			new ArrayList<String>(Arrays.asList("so","is"));  
		Error e = new Error(5,"si",candidates); // an error on the line
		errors.add(e);
		
		candidates = new ArrayList<String>(Arrays.asList("second","scone"));
		e = new Error(12,"scond",candidates);	
		errors.add(e);
		Line l = new Line("This si the scond line.",errors); // the line
		
		//Expected return with which to compare
		ArrayList<Error> errors2 = new ArrayList<Error>();
		candidates = new ArrayList<String>(Arrays.asList("so","is"));
		e = new Error(5,"si",candidates);
		errors2.add(e);
		Line l2 = new Line("This si the second line.",errors2);
		
		// Note white space is important
		Line l2b = l.correctError(1, "second");
		if (l2b.toString().equals(l2.toString()))
			return "Test 11 passed";
		else 
			return "Test 11 failed\n" + "\tOutput from correctErrors: " 
					+ l2b.toString() + "\nExpected: " + l2.toString();
	}
	
}
