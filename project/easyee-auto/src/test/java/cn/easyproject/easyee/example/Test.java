package cn.easyproject.easyee.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	static Pattern p=Pattern.compile("([a-z])([A-Z])");
	public static String escapeToMyBatisColumn(String str){
		Matcher matcher=p.matcher(str);
		return matcher.replaceAll("$1_$2");
	}
	
	public static void main(String[] args) {

		String s="abcName";
		String h="hName";
		String q="QQ";
		
		
		String[] str={
				"abcName",
				"abcNameInfo",
				"aName",
				"varQQ",
				"QQ",
				"qq",
				"heyBABYgo",
				"SysUser"
		};
		
		for (int i = 0; i < str.length; i++) {
			System.out.println(escapeToMyBatisColumn(str[i]));
		}
		
		
		
		
	}

}
