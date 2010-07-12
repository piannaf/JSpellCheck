package assignment1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

// Test driver for Document class
@SuppressWarnings("unused")
public class DocumentTest {
	
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
	
	// Test 1: toString
	private static String runTest1() {
		Document d = new Document();  // the document
		if (d.toString().equals(""))
			return "Test 1 passed";
		else 
			return "Test 1 failed\n" + "\tOutput from toString: " + 
						d.toString().replace("\n","\n\t\t");
	}
	
	// Test 2: add to empty document
	private static String runTest2() {
		Document d = new Document();  // the document
		// the errors on the line
		ArrayList<Error> errors = new ArrayList<Error>(); 
		// the line
		Line l = new Line("This is a three line document.",errors);  
		d.add(l);
		if (d.toString().equals("0 This is a three line document.\n" +
				"Errors: []"))
			return "Test 2 passed";
		else 
			return "Test 2 failed\n" + "\tOutput from toString:\n\t\t" +
						d.toString().replace("\n","\n\t\t");
	}
	
	// Test 3: iterator
	private static String runTest3() {
		Document d = new Document();  // the document
		// the errors on the line
		ArrayList<Error> errors = new ArrayList<Error>(); 
		// the lines
		Line l = new Line("This is a three line document.",errors);  
		d.add(l);

		// the errors on the line
		errors = new ArrayList<Error>();	
		// the possible corrections for an error on the line
		ArrayList<String> candidates = 
			new ArrayList<String>(Arrays.asList("so","is"));  
		Error e = new Error(5,"si",candidates); // an error on the line
		errors.add(e);
		candidates = new ArrayList<String>(Arrays.asList("second","scone"));
		e = new Error(12,"scond",candidates);	
		errors.add(e);
		l = new Line("This si the scond line.",errors); // the line
		d.add(l);
		
		String doc = "";
		Iterator<Line> iter = d.iterator();
		while(iter.hasNext())
		{
			doc += iter.next().getText() + "\n";
		}
		
		if (doc.equals("This is a three line document.\nThis si the scond line.\n"))
			return "Test 3 passed";
		else 
			return "Test 3 failed\n" + "\tOutput from iteration:\n\t" + doc;
	}
	
	// Test 4: correctError
	private static String runTest4() {
		Document d = new Document();  // the document
		// the errors on the line
		ArrayList<Error> errors = new ArrayList<Error>(); 
		// the lines
		Line l = new Line("This is a three line document.",errors);  
		d.add(l);

		// the errors on the line
		errors = new ArrayList<Error>();	
		// the possible corrections for an error on the line
		ArrayList<String> candidates = 
			new ArrayList<String>(Arrays.asList("so","is"));  
		Error e = new Error(5,"si",candidates); // an error on the line
		errors.add(e);
		candidates = new ArrayList<String>(Arrays.asList("second","scone"));
		e = new Error(12,"scond",candidates);	
		errors.add(e);
		l = new Line("This si the scond line.",errors); // the line
		d.add(l);
		d.correctError(1, 0, "is");
		
		if (d.toString().equals("0 This is a three line document.\n" +
				"Errors: []\n" + "1 This is the scond line.\n" + 
				"Errors: [scond(12-16): [second, scone]]"))
			return "Test 4 passed";
		else 
			return "Test 4 failed\n" + "\tOutput from toString:\n\t\t" +
						d.toString().replace("\n","\n\t\t");
	}
	
	// Test 5: getText
	private static String runTest5() {
		Document d = new Document();  // the document
		// the errors on the line
		ArrayList<Error> errors = new ArrayList<Error>(); 
		// the line
		Line l = new Line("This is a three line document.",errors);  
		d.add(l);
		if (d.getText(0).equals("This is a three line document."))
			return "Test 5 passed";
		else 
			return "Test 5 failed\n" + "\tOutput from getText: " + d.getText(0);
	}
	
	// Test 6: NullPointerException, if line is null
	private static String runTest6() {
		Document d = new Document();  // the document
		// the errors on the line
		ArrayList<Error> errors = new ArrayList<Error>(); 
		// the line
		Line l = null;  
		try {
			d.add(l);
		} catch(NullPointerException err) {
			return "Test 6 passed";
		}
		return "Test 6 failed\n" + "\tLine allowed to be null";
	}
	
	// Test 7: NullPointerException, if correction is null
	private static String runTest7() {
		Document d = new Document();  // the document
		// the errors on the line
		ArrayList<Error> errors = new ArrayList<Error>(); 
		// the lines
		Line l = new Line("This is a three line document.",errors);  
		d.add(l);

		// the errors on the line
		errors = new ArrayList<Error>();	
		// the possible corrections for an error on the line
		ArrayList<String> candidates = 
			new ArrayList<String>(Arrays.asList("so","is"));  
		Error e = new Error(5,"si",candidates); // an error on the line
		errors.add(e);
		candidates = new ArrayList<String>(Arrays.asList("second","scone"));
		e = new Error(12,"scond",candidates);	
		errors.add(e);
		l = new Line("This si the scond line.",errors); // the line
		d.add(l);
		
		try {
			d.correctError(1, 0, null);
		} catch(NullPointerException err) {
			return "Test 7 passed";
		}
		return "Test 7 failed\n" + "\tCorrection allowed to be null";
	}
	
	// Test 8: NoSuchLineException, if index does not index a line in this Document
	private static String runTest8() {
		Document d = new Document();  // the document
		// the errors on the line
		ArrayList<Error> errors = new ArrayList<Error>(); 
		// the lines
		Line l = new Line("This is a three line document.",errors);  
		d.add(l);

		// the errors on the line
		errors = new ArrayList<Error>();	
		// the possible corrections for an error on the line
		ArrayList<String> candidates = 
			new ArrayList<String>(Arrays.asList("so","is"));  
		Error e = new Error(5,"si",candidates); // an error on the line
		errors.add(e);
		candidates = new ArrayList<String>(Arrays.asList("second","scone"));
		e = new Error(12,"scond",candidates);	
		errors.add(e);
		l = new Line("This si the scond line.",errors); // the line
		d.add(l);
		
		try {
			d.correctError(10, 0, "is");
		} catch(NoSuchLineException err) {
			return "Test 8 passed";
		}
		return "Test 8 failed\n" + "\tLine DNE but no exception thrown";
	}
	
	// Test 9: NoSuchErrorException, if pos is not a position in the list of errors in the line indexed by index
	private static String runTest9() {
		Document d = new Document();  // the document
		// the errors on the line
		ArrayList<Error> errors = new ArrayList<Error>(); 
		// the lines
		Line l = new Line("This is a three line document.",errors);  
		d.add(l);

		// the errors on the line
		errors = new ArrayList<Error>();	
		// the possible corrections for an error on the line
		ArrayList<String> candidates = 
			new ArrayList<String>(Arrays.asList("so","is"));  
		Error e = new Error(5,"si",candidates); // an error on the line
		errors.add(e);
		candidates = new ArrayList<String>(Arrays.asList("second","scone"));
		e = new Error(12,"scond",candidates);	
		errors.add(e);
		l = new Line("This si the scond line.",errors); // the line
		d.add(l);
		
		try {
			d.correctError(1, 10, "is");
		} catch(NoSuchErrorException err) {
			return "Test 9 passed";
		}
		return "Test 9 failed\n" + "\tError (object) DNE but no exception thrown";
	}
	
	// Test 10: NoSuchLineException, if index does not index a line in this Document
	private static String runTest10() {
		Document d = new Document();  // the document
		// the errors on the line
		ArrayList<Error> errors = new ArrayList<Error>(); 
		// the line
		Line l = new Line("This is a three line document.",errors);  
		d.add(l);	
		
		try {
			d.getText(10);
		} catch(NoSuchLineException err) {
			return "Test 10 passed";
		}
		return "Test 10 failed\n" + "\tLine DNE but no exception thrown";
	}

}
