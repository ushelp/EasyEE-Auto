package cn.easyproject.easyee.auto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Edit Dialog extension  
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
public @interface EasyEditDialog{
	/**
	 * User multipart form. (enctype="multipart/form-data")
	 * @return
	 */
	boolean multipart() default false;
}
