package cn.easyproject.easyee.auto.generator;

public enum EasyAutoModule {
	CONTROLLER_CLASS("controller"),
	CRITERIA_CLASS("criteria"),
	SERVICE_INTERFACE("service"),
	SERVICE_IMPLEMENTS("serviceImpl"),
	PAGE("page"),
	MYBATIS_DAO_INTERFACE("dao"),
	MYBATIS_MAPPER_XML("mapper");
	
	String value;
	private EasyAutoModule(String value) {
		this.value=value;
	}
	public String getValue() {
		return value;
	}
	
	
}
