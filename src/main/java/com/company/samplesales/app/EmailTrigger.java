package com.company.samplesales.app;

import com.company.samplesales.job.EmailSendingJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component("sales_EmailTrigger")
public class EmailTrigger {

    @Bean
    JobDetail emailSendingJob() {
        return JobBuilder.newJob()
                .ofType(EmailSendingJob.class)
                .storeDurably()
                .withIdentity("emailSending")
                .build();
    }

    @Bean
    Trigger emailSendingTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(emailSendingJob())
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("* 0 0 ? * * *"))
                .build();
    }
}