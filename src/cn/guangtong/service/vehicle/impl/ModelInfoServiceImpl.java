package cn.guangtong.service.vehicle.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.guangtong.dao.vehicle.ModelInfoDao;
import cn.guangtong.entity.vehicle.ModelInfo;
import cn.guangtong.service.vehicle.ModelInfoService;
import cn.guangtong.utils.PageBean;

@Service
@Transactional
public class ModelInfoServiceImpl implements  ModelInfoService {
	
	@Autowired
	private ModelInfoDao modelInfoDao;

	//查询所有车辆类型
	public PageBean selectModelInfoAll(PageBean pageBean) {
		if(pageBean.getCurrentPage() == 0){
			pageBean.setCurrentPage(1);
		}
		if(pageBean.getPageCount() == 0){
			pageBean.setPageCount(10);
		}
		
		
		pageBean.setTotalCount(modelInfoDao.getCounts());
		List<Map<String,Object>> list =modelInfoDao.selectModelInfoAll(pageBean);
		pageBean.setBeanList(list);
		return pageBean;
	}

	//根据ID查询车辆类型信息
	@Override
	public Map<String, Object> selectModelInfoById(String id) {
		Map<String, Object> map = new HashMap<String,Object>();
		return modelInfoDao.selectModelInfoById(id);
	}

	//更新车辆类型信息
	@Override
	public int updateModelInfo(ModelInfo modelInfo) {
		String[] lwh = modelInfo.getLwh().split("\\*");
		modelInfo.setLength(lwh[0]);
		modelInfo.setHeight(lwh[1]);
		modelInfo.setWidth(lwh[2]);
		return modelInfoDao.updateModelInfo(modelInfo);
	}


	@Override
	public int getCounts() {
		return modelInfoDao.getCounts();
	}

	@Override
	public int insertModelInfo(ModelInfo modelInfo) {
		String[] lwh = modelInfo.getLwh().split("\\*");
		modelInfo.setLength(lwh[0]);
		modelInfo.setHeight(lwh[1]);
		modelInfo.setWidth(lwh[2]);
		return modelInfoDao.insertModelInfo(modelInfo);
	}

	@Override
	public int deleteModelInfo(Integer id) {
		
		return modelInfoDao.deleteModelInfo(id);
	}

}
