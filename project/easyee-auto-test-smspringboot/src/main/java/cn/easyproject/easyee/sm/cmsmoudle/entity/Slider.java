package cn.easyproject.easyee.sm.cmsmoudle.entity;







import cn.easyproject.easyee.auto.EasyAddDialog;
import cn.easyproject.easyee.auto.EasyEditDialog;
import cn.easyproject.easyee.auto.EasyField;
import cn.easyproject.easyee.auto.EasyPage;
import cn.easyproject.easyee.auto.EasyId;
import cn.easyproject.easyee.auto.EasyModule;
import cn.easyproject.easyee.auto.PageType;

@EasyModule(
		label="首页轮播图片",
//		pagination=false,
//		contextMenu=false, 
//		updateButton=false, 
//		searchButton=false, 
		toolbar=true,
		mutipleDelete=true,
		mybatisTable="web_slider"
)
@EasyPage(PageType.NONE) // 行编辑方式；可选，默认为对话框编辑模式
@EasyAddDialog(multipart=true)
//@EasyEditDialog(multipart=true)
public class Slider {

	@EasyId // 主键字段，**必须**
	@EasyField(show = false, inputShow=false) // 查询和输入时不显示字段信息
	private int id;
	
	@EasyField(label = "图片",updateAble=false,inputClass="easyui-filebox")
	private String url;
	
	@EasyField(label = "排序序号（由大到小）", inputClass="numberbox")
	private int sort;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
	
	
	
}
