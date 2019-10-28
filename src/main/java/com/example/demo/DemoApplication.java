package com.example.demo;

import com.example.demo.job.MyJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.JobKey.jobKey;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DemoApplication.class, args);

		// Grab the Scheduler instance from the Factory//从Factory中获取Scheduler实例
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		// and start it off
		scheduler.start();

		// define the job and tie it to our MyJob class
		JobDetail jobDetail = newJob(MyJob.class).withIdentity("job1", "group1").storeDurably().build();
		// Trigger the job to run now, and then repeat every 10 seconds forever
		Trigger trigger = newTrigger().withIdentity("trigger1", "group1")
				.startNow()
				.withSchedule(simpleSchedule().withIntervalInSeconds(10).repeatForever())
				.build();
		Trigger trigger2 = newTrigger().withIdentity("trigger2", "group1")
				.forJob(jobKey("job1", "group1"))
				.startNow()
				.withSchedule(simpleSchedule().withIntervalInSeconds(10).repeatForever())
				.build();
		// Tell quartz to schedule the job using our trigger
		scheduler.scheduleJob(jobDetail, trigger);
		scheduler.scheduleJob(trigger2);
	}
}
