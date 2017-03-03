package cn.easyproject.easyee.auto;

/**
 * EasyEE PageType enum
 * @author Ray
 * @author inthinkcolor@gmail.com
 * @author easyproject.cn
 * @since
 */
public enum PageType {
	/**
	 * 无需直接编辑，默认方式
	 * 自动添加以下类注解
	 * @EasyAddDialog
	 * @EasyEditDialog
	 */
	NONE,
	/**
	 * 行编辑模式
	 */
	ROW_EDIT,
	/**
	 * 列编辑模式
	 */
	CELL_EDIT,
	/**
	 * 表单编辑模式，单行数据
	 */
	FORM_EDIT;
}
