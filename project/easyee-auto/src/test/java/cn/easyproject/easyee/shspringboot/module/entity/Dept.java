package cn.easyproject.easyee.shspringboot.module.entity;


import cn.easyproject.easyee.auto.EasyCriteria;
import cn.easyproject.easyee.auto.EasyField;
import cn.easyproject.easyee.auto.EasyPage;
import cn.easyproject.easyee.auto.EasyId;
import cn.easyproject.easyee.auto.EasyModule;
import cn.easyproject.easyee.auto.PageType;

//import java.util.HashSet;
//import java.util.Set;

/**
 * 
 * @author easyproject.cn
 * @version 1.0
 *
 */
@EasyModule(label="部门")
@EasyPage(PageType.ROW_EDIT) // 行编辑方式；可选，默认为对话框编辑模式
public class Dept implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;

	@EasyId // 主键字段，**必须**
	@EasyField(label = "部门编号", show = false, inputShow = false)
	private Integer deptno;
	
	@EasyField(label = "部门名称", inputRequired = true)
	@EasyCriteria(label = "部门名称", like = true)
	private String dname;

	@EasyField(label = "部门地址")
	@EasyCriteria(label = "部门地址", like = true)
	private String loc;

	// private Set moduleEmps = new HashSet(0);

	// Constructors

	/** default constructor */
	public Dept() {
	}

	/** full constructor */
	public Dept(String dname, String loc) {
		this.dname = dname;
		this.loc = loc;
		// this.moduleEmps = moduleEmps;
	}

	// Property accessors

	public Integer getDeptno() {
		return this.deptno;
	}

	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return this.dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getLoc() {
		return this.loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	// public Set getModuleEmps() {
	// return this.moduleEmps;
	// }
	//
	// public void setModuleEmps(Set moduleEmps) {
	// this.moduleEmps = moduleEmps;
	// }

}