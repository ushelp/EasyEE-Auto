package cn.easyproject.easyee.smspringboot.module.entity;







import cn.easyproject.easyee.auto.EasyField;
import cn.easyproject.easyee.auto.EasyPage;
import cn.easyproject.easyee.auto.EasyId;
import cn.easyproject.easyee.auto.EasyModule;
import cn.easyproject.easyee.auto.PageType;

@EasyModule(label="网站基本信息",
pagination=false,contextMenu=false,saveButton=false,
removeButton=false, updateButton=false, toolbar=false,
mybatisTable="web_basic")
@EasyPage(PageType.FORM_EDIT) // 行编辑方式；可选，默认为对话框编辑模式
public class Basic {
	
	@EasyId // 主键字段，**必须**
	@EasyField(show = false, inputShow=false) // 查询和输入时不显示字段信息
	private int id;
	
	@EasyField(label = "邮箱")
	private String email;
	@EasyField(label = "QQ")
	private String qq;
	@EasyField(label = "电话")
	private String tel;
	@EasyField(label = "公司地址")
	private String address;
	@EasyField(label = "微博地址")
	private String weibo;
	@EasyField(label = "底部版权")
	private String copyright;
	@EasyField(label = "简介")
	private String description;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getWeibo() {
		return weibo;
	}
	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}
	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
