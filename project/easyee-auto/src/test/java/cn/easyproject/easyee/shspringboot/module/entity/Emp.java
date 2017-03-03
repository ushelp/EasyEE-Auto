package cn.easyproject.easyee.shspringboot.module.entity;

import cn.easyproject.easyee.auto.EasyCriteria;
import cn.easyproject.easyee.auto.EasyCriterias;
import cn.easyproject.easyee.auto.EasyField;
import cn.easyproject.easyee.auto.EasyPage;
import cn.easyproject.easyee.auto.EasyId;
import cn.easyproject.easyee.auto.EasyModule;
import cn.easyproject.easyee.auto.PageType;

/**
 * ModuleEmp entity. @author MyEclipse Persistence Tools
 */
/**
 * 
 * @author easyproject.cn
 * @version 1.0
 *
 */

// 自定义条件字段
@EasyCriterias({
	// label 条件显示的名称，name 条件类属性名，queryConditionName hql查询条件名，type
	// 条件属性数据类型，like 是否模糊查询
	@EasyCriteria(label = "部门", filed = "deptno", queryConditionName = "dept.deptno", type = Integer.class) 
})
@EasyModule(label="员工")
@EasyPage(PageType.NONE) // 行编辑方式；可选，默认为对话框编辑模式
public class Emp implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;

	@EasyId // 主键字段，**必须**
	@EasyCriteria(label = "员工编号") // 条件字段信息
	@EasyField(show = false, inputShow=false) // 查询和输入时不显示字段信息
	private Integer empno;

	@EasyCriteria(label = "员工姓名", like = true) // 条件字段，模糊查询
	@EasyField(label = "员工姓名", inputRequired = true)
	private String ename;

	@EasyCriteria(label = "职务") // 条件字段
	@EasyField(label = "职务")
	private String job;

	@EasyField(label = "部门", field = "dept.dname", inputField = "dept.deptno", inputRequired = true)
	private Dept dept;
	
	
	// Constructors

	/** default constructor */
	public Emp() {
	}

	/** full constructor */
	public Emp(Dept dept, String ename, String job) {
		this.dept = dept;
		this.ename = ename;
		this.job = job;
	}

	// Property accessors

	public Integer getEmpno() {
		return this.empno;
	}

	public void setEmpno(Integer empno) {
		this.empno = empno;
	}

	public Dept getDept() {
		return this.dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

}