package assignment2;

@SuppressWarnings("unused")
public class randomTests {
    public static void main(String[] args) {
        String text = "foobar";
        int rounds = 1000000;
        int crounds = 10000;
        
        // Perform some warm-up rounds
        timeStringBuffer(text, rounds);
        timeStringBuilder(text, rounds);
        timeStringBuffer(text, rounds);
        timeStringBuilder(text, rounds);
        
        timeStringBufferReplaceChar(text, crounds);
        timeStringBufferDeleteInsertChar(text, crounds);
        timeStringBuilderReplaceChar(text, crounds);
        timeStringBuilderDeleteInsertChar(text, crounds);
        timeStringBufferReplaceChar(text, crounds);
        timeStringBufferDeleteInsertChar(text, crounds);
        timeStringBuilderReplaceChar(text, crounds);
        timeStringBuilderDeleteInsertChar(text, crounds);

        timeStringBufferSubstring(text, crounds);
        timeStringBufferGetChar(text, crounds);
        timeStringBuilderSubstring(text, crounds);
        timeStringBuilderGetChar(text, crounds);
        timeStringBufferSubstring(text, crounds);
        timeStringBufferGetChar(text, crounds);
        timeStringBuilderSubstring(text, crounds);
        timeStringBuilderGetChar(text, crounds);
        
        // And now the final results
        System.out.println("StringBufferAppendString:  " 
        		+ timeStringBuffer(text, rounds));
        System.out.println("StringBuilderAppendString: " 
        		+ timeStringBuilder(text, rounds));
        
        System.out.println("StringBufferReplaceChar:  " + 
        		timeStringBufferReplaceChar(text, crounds));
        System.out.println("StringBufferDeleteInsertChar: " + 
        		timeStringBufferDeleteInsertChar(text, crounds));
        System.out.println("StringBuilderReplaceChar:  " + 
        		timeStringBuilderReplaceChar(text, crounds));
        System.out.println("StringBuilderDeleteInsertChar: " + 
        		timeStringBuilderDeleteInsertChar(text, crounds));
        
        System.out.println("StringBufferSubstring:  " + 
        		timeStringBufferSubstring(text, rounds));
        System.out.println("StringBufferCharAt: " + 
        		timeStringBufferGetChar(text, rounds));
        System.out.println("StringBuilderSubstring:  " + 
        		timeStringBuilderSubstring(text, rounds));
        System.out.println("StringBuilderCharAt: " + 
        		timeStringBuilderGetChar(text, rounds));
    }
    
    private static long timeStringBuffer(String text, int rounds) {
        long start = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer(text.length() * rounds);
        for (int i = 0; i < rounds; i++) {
            sb.append(text);
        }
        sb.toString();
        return System.currentTimeMillis() - start;
    }
    
    private static long timeStringBuilder(String text, int rounds) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder(text.length() * rounds);
        for (int i = 0; i < rounds; i++) {
            sb.append(text);
        }
        sb.toString();
        return System.currentTimeMillis() - start;
    }
    
    private static long timeStringBufferReplaceChar(String text, int rounds) {
        long start = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer(text.length() * rounds);
        for (int i = 0; i < rounds; i++) {
            sb.append(text);
            sb.replace(i, i+1, "" + (char)97);
        }
        sb.toString();
        return System.currentTimeMillis() - start;
    }
    
    private static long timeStringBufferDeleteInsertChar(String text, int rounds) {
        long start = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer(text.length() * rounds);
        for (int i = 0; i < rounds; i++) {
            sb.append(text);
            sb.deleteCharAt(i).setCharAt(i, (char)97);
        }
        sb.toString();
        return System.currentTimeMillis() - start;
    }
    
    private static long timeStringBuilderReplaceChar(String text, int rounds) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder(text.length() * rounds);
        for (int i = 0; i < rounds; i++) {
            sb.append(text);
            sb.replace(i, i+1, "" + (char)97);
        }
        sb.toString();
        return System.currentTimeMillis() - start;
    }
    
    private static long timeStringBuilderDeleteInsertChar(String text, int rounds) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder(text.length() * rounds);
        for (int i = 0; i < rounds; i++) {
            sb.append(text);
            sb.deleteCharAt(i).setCharAt(i, (char)97);
        }
        sb.toString();
        return System.currentTimeMillis() - start;
    }
    
    private static long timeStringBufferSubstring(String text, int rounds) {
        long start = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer(text.length() * rounds);
        for (int i = 0; i < rounds; i++) {
            sb.append(text);
            sb.substring(i, i+1);
        }
        sb.toString();
        return System.currentTimeMillis() - start;
    }
    
    private static long timeStringBufferGetChar(String text, int rounds) {
        long start = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer(text.length() * rounds);
        for (int i = 0; i < rounds; i++) {
            sb.append(text);
            sb.charAt(i);
        }
        sb.toString();
        return System.currentTimeMillis() - start;
    }
    
    private static long timeStringBuilderSubstring(String text, int rounds) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder(text.length() * rounds);
        for (int i = 0; i < rounds; i++) {
            sb.append(text);
            sb.substring(i, i+1);
        }
        sb.toString();
        return System.currentTimeMillis() - start;
    }
    
    private static long timeStringBuilderGetChar(String text, int rounds) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder(text.length() * rounds);
        for (int i = 0; i < rounds; i++) {
            sb.append(text);
            sb.charAt(i);
        }
        sb.toString();
        return System.currentTimeMillis() - start;
    }

    private static long timeStringBuilderDeleteInsertSwap(String text, int rounds) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder(text.length() * rounds);
        for (int i = 0; i < rounds; i++) {
            sb.append(text);
            char c = sb.charAt(i);
            sb.deleteCharAt(i).insert(i+1, c);
        }
        sb.toString();
        return System.currentTimeMillis() - start;
    }

    private static long timeStringBuilderReplaceSwap(String text, int rounds) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder(text.length() * rounds);
        for (int i = 0; i < rounds; i++) {
            sb.append(text);
            String s = sb.substring(i, i+2);
            sb.replace(i, i+2, (new StringBuilder(s)).reverse().toString());
        }
        sb.toString();
        return System.currentTimeMillis() - start;
    }
}
