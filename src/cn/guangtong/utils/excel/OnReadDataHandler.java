package cn.guangtong.utils.excel;

import java.util.List;

/**
 * Excel数据读取回调
 * @author wuwz
 */
public interface OnReadDataHandler {

	/**
	 * 处理当前行数据
	 * @param rowData 当前行数据,以rowData.get(cellIndex)的方式获取,如果cell的值为ExceklKit._emptyCellValue,则表示该单元格为空。
	 */
	void handler(List<String> rowData);
}
