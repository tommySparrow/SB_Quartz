package com.example.demo.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @ Author     ：jmyang
 * @ Date       ：Created in 2018/7/16
 * @ Description：
 * @ throws
 */
public class MyJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.err.println("Hello World!  MyJob is executing.");
    }
}
