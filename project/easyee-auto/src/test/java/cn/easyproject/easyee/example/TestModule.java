package cn.easyproject.easyee.example;

import cn.easyproject.easyee.auto.generator.EasyAutoModule;

public class TestModule {
	public static void main(String[] args) {
		
		EasyAutoModule[] modules=new EasyAutoModule[]{
				EasyAutoModule.CONTROLLER_CLASS,
				EasyAutoModule.CRITERIA_CLASS,
				EasyAutoModule.SERVICE_INTERFACE,
				EasyAutoModule.SERVICE_IMPLEMENTS,
				EasyAutoModule.PAGE,
				EasyAutoModule.MYBATIS_DAO_INTERFACE,
				EasyAutoModule.MYBATIS_MAPPER_XML
		};
		
		String mds="#";
		if(modules==null){
			mds="#controller#criteria#dao#mapper#page#service#serviceImpl#";
		}else{
			for (int i = 0; i < modules.length; i++) {
				mds+=modules[i].getValue()+"#";
			}
		}
		System.out.println(mds);
	}
}
