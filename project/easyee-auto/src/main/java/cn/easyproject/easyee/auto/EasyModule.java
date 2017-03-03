package cn.easyproject.easyee.auto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Module annotation
 * 
 * Set for Class
 * 
 * @author Ray
 * @author inthinkcolor@gmail.com
 * @author easyproject.cn
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE})
public @interface EasyModule {
	
	/**
	 * 显示的模块名称，默认为类名
	 * @return 生成的显示名
	 */
	String label() default "";
	
	/**
	 * 显示模块名称的国际化键，默认为类名
	 * @return 生成显示名的国际化键
	 */
	String labelKey() default "";
	
	/**
	 * 是否显示分页，默认为true
	 * @return 是否
	 */
	boolean pagination() default true;
	
	/**
	 * 是否显示保存，默认为true
	 * @return 是否
	 */
	boolean saveButton() default true;
	
	/**
	 * 是否显示修改，默认为true
	 * @return 是否
	 */
	boolean updateButton() default true;
	
	/**
	 * 是否显示删除，默认为true
	 * @return 是否
	 */
	boolean removeButton() default true;
	
	/**
	 * 是否搜索，默认为true
	 * @return 是否
	 */
	boolean searchButton() default true;
	
	/**
	 * 是否显示工具栏，默认为true
	 * @return 是否
	 */
	boolean toolbar() default true;
	
	/**
	 * 是否显示右键菜单，默认为true
	 * @return 是否
	 */
	boolean contextMenu() default true;
	
	/**
	 * 是否批量删除，默认为true
	 * @return 是否
	 */
	boolean mutipleDelete() default true;
	
	/**
	 * MyBatis 表名，默认为类名(MyBatis 专有)
	 * @return 表名
	 */
	String mybatisTable() default "";
	
	
}
