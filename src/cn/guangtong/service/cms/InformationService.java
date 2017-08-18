package cn.guangtong.service.cms;

import java.util.List;

import cn.guangtong.entity.cms.Information;
import cn.guangtong.utils.InformationPageBean;

public interface InformationService {
	
	public List<Information> getInformations(InformationPageBean pageBean);
	
	public int getCounts();
	
	public void addInformation(Information information);
	
	public void deleteInformation(int id);
	
	public void updateInformation(Information information);
	
	public Information getInformationById(int id);

}
