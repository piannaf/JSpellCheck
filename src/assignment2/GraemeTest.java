package assignment2;

import java.io.IOException;

public class GraemeTest {

	public static void main(String[] args) throws IOException {
		SpellChecker s = new SpellChecker();
		s.loadDictionary("dict.txt");
		s.loadDocument("doc.txt");
		s.correctError("second");
		s.correctError("line");
		s.correctError("the");
		s.writeDocument("doc1.txt");
	}
	
}
