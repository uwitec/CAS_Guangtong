package cn.guangtong.service.cms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.guangtong.dao.cms.FeedbackDao;
import cn.guangtong.entity.cms.Feedback;
import cn.guangtong.service.cms.FeedbackService;
import cn.guangtong.utils.FeedbackPageBean;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService{
	
	@Autowired
	private FeedbackDao feedbackDao;

	@Override
	public List<Feedback> getFeedbacks(FeedbackPageBean pageBean) {
		return feedbackDao.getFeedbacks(pageBean);
	}

	@Override
	public int getCounts() {
		return feedbackDao.getCounts();
	}

}
