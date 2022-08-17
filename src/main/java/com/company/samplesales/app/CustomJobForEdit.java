package com.company.samplesales.app;

import com.company.samplesales.screen.login.LoginScreen;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomJobForEdit implements Job {
    private final Logger log = LoggerFactory.getLogger(LoginScreen.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.warn("*** The custom job has been changed and triggered ***");
    }
}
