package cn.guangtong.utils.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Excel导出项配置
 * @author wuwz
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface ExportConfig {

	
	/**
	 * 表头显示名
	 * @return 默认为字段名
	 */
	String value() default "field";
	
	/**
	 * 单元格宽度
	 * @return 默认300
	 */
	short width() default 300;
	
	/**
	 * 是否导出数据(如果不导出数据,Ĭ将以“******”填充单元格)
	 * @return 默认true
	 */
	boolean isExportData() default true;
}
