package cn.easyproject.easyee.auto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Page Type annotation
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
public @interface EasyPage{
	/**
	 * 页面类型，默认为  PageType.NONE
	 * @return 
	 */
	PageType value() default PageType.NONE;
}
