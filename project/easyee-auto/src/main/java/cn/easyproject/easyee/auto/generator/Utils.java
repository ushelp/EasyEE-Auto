package cn.easyproject.easyee.auto.generator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	static Pattern p=Pattern.compile("([a-z])([A-Z])");
	public static String escapeToMyBatisName(String str){
		Matcher matcher=p.matcher(str);
		return matcher.replaceAll("$1_$2");
	}
	
}
