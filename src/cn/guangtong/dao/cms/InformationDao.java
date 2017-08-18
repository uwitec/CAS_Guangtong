package cn.guangtong.dao.cms;

import java.util.List;

import cn.guangtong.entity.cms.Information;
import cn.guangtong.utils.InformationPageBean;

public  interface InformationDao {
	
	public List<Information> getInformations(InformationPageBean pageBean);
	
	public int getCounts();
	
	public int addInformation(Information information);
	
	public void deleteInformation(int id);
	
	public void updateInformation(Information information);
	
	public Information getInformationById(int id);

}
