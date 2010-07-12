package assignment2;

import java.util.ArrayList;

import assignment2.Dictionary;

public class PabloDictionaryTest {

	/**
	 * @param args
	 */
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
			//if (i==10) s = runTest10();
			//if (i==2) s = runTest2();
			//if (i==2) s = runTest2();
			//if (i==2) s = runTest2();
			//if (i==2) s = runTest2();
			//if (i==2) s = runTest2();
			
			System.out.println(s);
			s="";
		}

	}
	
	/** TESTS	
	 *  Test 1: lookup a word that is in the Dictionary
	 *  Test 2: lookup a word that isn't in the Dictionary
	 *  Test 3: insertionCorrections, long strings
	 *  Test 4: insertionCorrections, 3-char strings
	 *  Test 5: insertionCorrections, 1-char strings
	 *  Test 6: deletionCorrections, long strings
	 *  Test 7: deletionCorrections, short strings and empty string
	 * 
	 * @return
	 */
	
	
	// Test 1: lookup a word that is in the Dictionary
	private static String runTest1() {
		Dictionary d = new Dictionary();
		d.add("hello");
		
		if (d.lookup("hello"))
			return "Test 1 passed";
		else 
			return "Test 1 failed\n"+ "\tOutput:"+d.lookup("hello");
	}
	
	// Test 2: lookup a word that isn't in the Dictionary
	private static String runTest2() {
		Dictionary d = new Dictionary();
		d.add("hello");
		
		if (!d.lookup("hessllo"))
			return "Test 2 passed";
		else 
			return "Test 2 failed\n"+ "\tOutput:"+d.lookup("hessllo");
	}
	
	// Test 3: insertionCorrections, long strings
	private static String runTest3() {
		Dictionary d = new Dictionary();
		d.add("ello");
		d.add("hllo");
		d.add("hell");
		d.add("helloo");
		d.add("helloo");
		
		ArrayList<String> result= new
			ArrayList<String>(d.getCorrections("hello"));
		
		boolean c1 = result.contains("ello");
		boolean c2 = result.contains("hllo");
		boolean c3 = result.contains("hell");
		
		
		if((c1 && c2) && c3)
			return "Test 3 passed";
		else 
			return "Test 3 failed\n"+ "\tOutput:"+result;
	}
	

	// Test 4: insertionCorrections, 3-char strings
	private static String runTest4() {
		Dictionary d = new Dictionary();
		d.add("is");
		d.add("si");
		d.add("ii");
		
		ArrayList<String> result= new
			ArrayList<String>(d.getCorrections("iSi"));
		
		boolean c1 = result.contains("iS");
		boolean c2 = result.contains("Si");
		boolean c3 = result.contains("ii");
		
		if(c1 && c2 && c3)
			return "Test 4 passed";
		else 
			return "Test 4 failed\n"+ "\tOutput:"+result;
	}
	
	// Test 5: insertionCorrections, 1-char strings
	private static String runTest5() {
		Dictionary d = new Dictionary();
		d.add("i");
		
		ArrayList<String> result= new
			ArrayList<String>(d.getCorrections("a"));
				
		if(result.size() == 1)
			return "Test 5 passed";
		else 
			return "Test 5 failed\n"+ "\tOutput:"+result;
	}
	
	// Test 6: deletionCorrections, long strings
	private static String runTest6() {
		Dictionary d = new Dictionary();
		d.add("ello");
		d.add("hllo");
		d.add("hhello");
		d.add("helloo");
		d.add("helllo");
		
		ArrayList<String> result= new
			ArrayList<String>(d.getCorrections("hello"));
		
		boolean c1 = result.contains("hhello");
		boolean c2 = result.contains("helloo");
		boolean c3 = result.contains("helllo");
		
		
		if((c1 && c2) && c3)
			return "Test 6 passed ";
		else 
			return "Test 6 failed\n"+ "\tOutput:"+result;
	}
	
	// Test 7: deletionCorrections, short strings and empty string
	private static String runTest7() {
		Dictionary d = new Dictionary();
		d.add("ello");
		d.add("hllo");
		d.add("hhello");
		d.add("helloo");
		d.add("e");
		d.add("");
		
		ArrayList<String> result= new
			ArrayList<String>(d.getCorrections("h"));
		
		boolean c1 = result.contains("");
		
		if(c1)
			return "Test 7 passed ";
		else 
			return "Test 7 failed\n"+ "\tOutput:"+result;
	}
	
	// Test 8: substitutionCorrections, long strings
	private static String runTest8() {
		Dictionary d = new Dictionary();
		d.add("lalalalala");
		d.add("laleliloll");
		d.add("laellilolu");
		d.add("aalelilolu");
		d.add("lllelilolu");
		
		ArrayList<String> result= new
			ArrayList<String>(d.getCorrections("lalelilolu"));
		
		boolean c1 = result.contains("laleliloll");
		boolean c2 = result.contains("aalelilolu");
		boolean c3 = result.contains("lllelilolu");
		
		
		if((c1 && c2) && c3)
			return "Test 8 passed ";
		else 
			return "Test 8 failed\n"+ "\tOutput:"+result;
	}
	
	// Test 9: transpositionCorrections, long strings
	private static String runTest9() {
		Dictionary d = new Dictionary();
		d.add("lalalalala");
		d.add("laleliloul");
		d.add("laellilolu");
		d.add("allelilolu");
		d.add("lllelilolu");
		
		ArrayList<String> result= new
			ArrayList<String>(d.getCorrections("lalelilolu"));
		
		boolean c1 = result.contains("laleliloul");
		boolean c2 = result.contains("allelilolu");
		boolean c3 = result.contains("laellilolu");
		
		
		if((c1 && c2) && c3)
			return "Test 9 passed ";
		else 
			return "Test 9 failed\n"+ "\tOutput:"+result;
	}
	
	
	
}
