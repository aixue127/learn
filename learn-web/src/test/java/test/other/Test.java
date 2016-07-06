package test.other;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String args[]) {
		createFolder();
	}
	
	public static void createFolder() {
		String str = "/a1/b1/c1/d1/";
		Pattern t = Pattern.compile("\\w+\\/");
		Matcher m = t.matcher(str);
		String t12 = "";
	    while(m.find()) {
	    	t12 = m.group();
	    }
	    System.out.println(t12);
	  
	}
}
