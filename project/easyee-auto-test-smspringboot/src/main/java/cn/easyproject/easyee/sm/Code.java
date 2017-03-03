package cn.easyproject.easyee.sm;

import cn.easyproject.easyee.auto.generator.SMSpringBootCodeGenerator;

public class Code {
	public static void main(String[] args) {
		// Entity Directory or File
		// 生成模块代码的基础包
//		String entityPackage="cn.easyproject.easyee.sh.hr.entity";
		String entityPackage="cn.easyproject.easyee.sm.cmsmoudle.entity";
		new SMSpringBootCodeGenerator().generator(entityPackage);
//		new CodeGenerator().generator(Emp.class,Dept.class);
		System.out.println("生成完成");
	}
}
