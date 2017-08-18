package org.apache.shiro.session.mgt.quartz;

import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuartzSessionValidationJob implements Job{

    public QuartzSessionValidationJob(){
    }

    public void execute(JobExecutionContext context) throws JobExecutionException{
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        ValidatingSessionManager sessionManager = (ValidatingSessionManager)jobDataMap.get("sessionManager");
        if(log.isDebugEnabled())
            log.debug("Executing session validation Quartz job...");
        sessionManager.validateSessions();
        if(log.isDebugEnabled())
            log.debug("Session validation Quartz job complete.");
    }

    static final String SESSION_MANAGER_KEY = "sessionManager";
    private static final Logger log = LoggerFactory.getLogger(QuartzSessionValidationJob.class);

}