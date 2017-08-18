package cn.guangtong.utils.excel;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 导出Excel设置接口。
 * @author wuwz
 */
public interface OnSettingHanlder {
	/**
	 * 设置表头样式
	 * @param wb 当前Wordbook对象
	 * @return 处理后的样式
	 */
	CellStyle getHeadCellStyle(Workbook wb);

	/**
	 * 设置内容部分的单元格样式
	 * @param wb 当前Wordbook对象
	 * @return 处理后的样式
	 */
	CellStyle getBodyCellStyle(Workbook wb);
	
	/**
	 * 设置导出的文件名（无需处理后缀）
	 * @param sheetName sheetName
	 * @return 处理后的文件名
	 */
	String getExportFileName(String sheetName);
}
