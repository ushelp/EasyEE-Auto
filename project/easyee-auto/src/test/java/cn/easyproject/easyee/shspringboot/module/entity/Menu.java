package cn.easyproject.easyee.shspringboot.module.entity;







import cn.easyproject.easyee.auto.EasyField;
import cn.easyproject.easyee.auto.EasyPage;
import cn.easyproject.easyee.auto.EasyId;
import cn.easyproject.easyee.auto.EasyModule;
import cn.easyproject.easyee.auto.PageType;

@EasyModule(label="网站菜单",pagination=false,contextMenu=false,saveButton=false, removeButton=false, updateButton=false, toolbar=false)
@EasyPage(PageType.FORM_EDIT) // 行编辑方式；可选，默认为对话框编辑模式
public class Menu {
	
	@EasyId // 主键字段，**必须**
	@EasyField(show = false, inputShow=false) // 查询和输入时不显示字段信息
	private int id;
	
	@EasyField(label = "菜单1")
	private String m1;
	@EasyField(label = "菜单2")
	private String m2;
	@EasyField(label = "菜单3")
	private String m3;
	@EasyField(label = "菜单4")
	private String m4;
	@EasyField(label = "菜单5")
	private String m5;
	@EasyField(label = "菜单6")
	private String m6;
	@EasyField(label = "菜单7")
	private String m7;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getM1() {
		return m1;
	}
	public void setM1(String m1) {
		this.m1 = m1;
	}
	public String getM2() {
		return m2;
	}
	public void setM2(String m2) {
		this.m2 = m2;
	}
	public String getM3() {
		return m3;
	}
	public void setM3(String m3) {
		this.m3 = m3;
	}
	public String getM4() {
		return m4;
	}
	public void setM4(String m4) {
		this.m4 = m4;
	}
	public String getM5() {
		return m5;
	}
	public void setM5(String m5) {
		this.m5 = m5;
	}
	public String getM6() {
		return m6;
	}
	public void setM6(String m6) {
		this.m6 = m6;
	}
	public String getM7() {
		return m7;
	}
	public void setM7(String m7) {
		this.m7 = m7;
	}
	
	
}
