package cn.easyproject.easyee.auto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Field annotation
 * 
 * Set for Fields
 * 
 * @author Ray
 * @author inthinkcolor@gmail.com
 * @author easyproject.cn
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.FIELD})
public @interface EasyField {
	
	/**
	 * 显示的说明名称，默认为属性名
	 * @return 生成的显示名
	 */
	String label() default "";
	
	/**
	 * 显示的说明名称的国际化键，默认为属性名
	 * @return 生成显示名的国际化键
	 */
	String labelKey() default "";
	
	/**
	 * 是否显示在列表，默认显示
	 * @return 是否在数据网格中显示该字段
	 */
	boolean show() default true;
	
	/**
	 * 获取数据的字段名称，默认为属性名
	 * @return 生成的显示名
	 */
	String field() default "";
	
	/**
	 * 在添加或编辑时是否显示该字段，默认显示, 仅  DIALOG 编辑模式有效
	 * @return 是否在数据网格中显示该字段
	 */
	boolean inputShow() default true;
	
	/**
	 * 添加或编辑时使用的字段名称，默认为属性名, 仅 DIALOG 编辑模式有效
	 * @return 输入信息的字段名
	 */
	String inputField() default "";
	
	/**
	 * 添加或编辑时，是否必填，默认为false
	 * @return 是否必填
	 */
	boolean inputRequired() default false;
	
	/**
	 * 编辑模式时（Dialog, ROW, CELL, FORM），是否可编辑，默认为true
	 * @return 是否必填
	 */
	boolean updateAble() default true;
	
	/**
	 * 编辑模式时，表单元素类型(easyui-textbox, easyui-filebox, easyui-numberbox...)。默认为easyui-textbox。
	 * @return 是否必填
	 */
	String inputClass() default "easyui-textbox";
	
	/**
	 * MyBatis 列名，默认为属性名(MyBatis专有)
	 * @return 表名
	 */
	String mybatisColumn() default "";
	
}
