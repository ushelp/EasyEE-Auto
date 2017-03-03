package cn.easyproject.easyee.auto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Criteria annotation
 * 
 * Set for Class or Field
 * 
 * @author Ray
 * @author inthinkcolor@gmail.com
 * @author easyproject.cn
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE,ElementType.FIELD})
public @interface EasyCriteria {
	
	/**
	 * 显示的条件说明，默认为属性名
	 * @return 条件说明
	 */
	String label() default "";
	
	/**
	 * 显示的条件说明的国际化键，默认为空
	 * @return 条件说明国际化键
	 */
	String labelKey() default "";
	
	/**
	 * Criteria 条件类属性名，默认为属性名
	 * @return 条件属性名
	 */
	String filed() default "";
	
	/**
	 * 查询时，使用的 SQL/HQL 条件名称
	 * @return 查询条件名称
	 */
	String queryConditionName() default "";
	
	/**
	 * 查询对象的数据类型
	 * @return 数据类型
	 */
	@SuppressWarnings("rawtypes")
	Class type() default Object.class;
	
	/**
	 * 进行模糊查询
	 * @return 是否模糊查询
	 */
	boolean like() default false;
	
}
