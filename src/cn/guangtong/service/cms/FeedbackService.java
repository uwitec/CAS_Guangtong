package cn.guangtong.service.cms;

import java.util.List;

import cn.guangtong.entity.cms.Feedback;
import cn.guangtong.utils.FeedbackPageBean;

public interface FeedbackService {

	public List<Feedback> getFeedbacks(FeedbackPageBean pageBean);

	public int getCounts();
	
}
