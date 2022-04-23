package com.company.samplesales.job;

import io.jmix.email.Emailer;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailSendingJob implements Job {

    @Autowired
    private Emailer emailer;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        emailer.processQueuedEmails();
    }
}
