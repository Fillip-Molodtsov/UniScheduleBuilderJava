package com.springproj.schedulebuilder.quartz;

import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
public class QuartzSubmitJobs {
    private static final String CRON_PATTERN = "0/10 * * ? * * *";

    @Bean(name = "simpleJob")
    public JobDetailFactoryBean jobMemberStats() {
        return QuartzConfig.createJobDetail(MyJob.class, "Simple Quartz Job");
    }

    @Bean(name = "simpleTrigger")
    public SimpleTriggerFactoryBean triggerMemberStats(@Qualifier("simpleJob") JobDetail jobDetail) {
        return QuartzConfig.createTrigger(jobDetail, 6000, "Simple Quartz Job Trigger");
    }

    @Bean(name = "cronJob")
    public JobDetailFactoryBean jobMemberClassStats() {
        return QuartzConfig.createJobDetail(MyJob.class, "Cron Quartz Job");
    }

    @Bean(name = "cronTrigger")
    public CronTriggerFactoryBean triggerMemberClassStats(@Qualifier("cronJob") JobDetail jobDetail) {
        return QuartzConfig.createCronTrigger(jobDetail, CRON_PATTERN, "Cron Quartz Job Trigger");
    }
}
