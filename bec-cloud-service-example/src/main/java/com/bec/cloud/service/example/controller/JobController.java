package com.bec.cloud.service.example.controller;

import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bec.cloud.auth.core.support.Result;
import com.bec.cloud.service.example.mapper.JobAndTriggerMapper;
import com.bec.cloud.service.example.model.JobAndTrigger;
import com.github.pagehelper.PageHelper;

/**
 * 定时任务管理
 * @author suruiliang
 * @version 创建时间：2018年5月23日 下午1:44:52
 * @ClassName 类名称
 * @Description 类描述
 */
@RestController
@RequestMapping(value="/job")
public class JobController 
{
	@Autowired
	private JobAndTriggerMapper jobAndTriggerMapper;

	@Autowired
	private Scheduler scheduler;

	@PostMapping(value="/addjob")
	public Result<?> addjob(@RequestParam(value="jobClassName")String jobClassName, 
			@RequestParam(value="jobGroupName")String jobGroupName, 
			@RequestParam(value="cronExpression")String cronExpression) throws Exception
	{           
		addJob(jobClassName, jobGroupName, cronExpression);
		return Result.success(null);
	}

	/**
	 * 添加定时任务
	 * @param jobClassName
	 * @param jobGroupName
	 * @param cronExpression
	 * @throws Exception
	 */
	private void addJob(String jobClassName, String jobGroupName, String cronExpression)throws Exception{

		// 启动调度器  
		scheduler.start(); 

		//构建job信息
		JobDetail jobDetail = JobBuilder.newJob(getClazz(jobClassName)).withIdentity(jobClassName, jobGroupName).build();

		//表达式调度构建器(即任务执行的时间)
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

		//按新的cronExpression表达式构建一个新的trigger
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
				.withSchedule(scheduleBuilder).build();

		try {
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			System.out.println("创建定时任务失败"+e);
			throw new Exception("创建定时任务失败");
		}
	}

	/**
	 * 暂停定时任务
	 * @param jobClassName
	 * @param jobGroupName
	 * @throws Exception
	 */
	@PostMapping(value="/pausejob")
	public Result<?> pausejob(@RequestParam(value="jobClassName")String jobClassName, @RequestParam(value="jobGroupName")String jobGroupName) throws Exception
	{           
		jobPause(jobClassName, jobGroupName);
		return Result.success(null);
	}

	private void jobPause(String jobClassName, String jobGroupName) throws Exception
	{   
		scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
	}


	/**
	 * 恢复定时任务
	 * @param jobClassName
	 * @param jobGroupName
	 * @throws Exception
	 */
	@PostMapping(value="/resumejob")
	public Result<?> resumejob(@RequestParam(value="jobClassName")String jobClassName, @RequestParam(value="jobGroupName")String jobGroupName) throws Exception
	{           
		jobresume(jobClassName, jobGroupName);
		return Result.success(null);
	}

	private void jobresume(String jobClassName, String jobGroupName) throws Exception
	{
		scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
	}


	/**
	 * 重设定时任务
	 * @param jobClassName
	 * @param jobGroupName
	 * @param cronExpression
	 * @throws Exception
	 */
	@PostMapping(value="/reschedulejob")
	public Result<?> rescheduleJob(@RequestParam(value="jobClassName")String jobClassName, 
			@RequestParam(value="jobGroupName")String jobGroupName,
			@RequestParam(value="cronExpression")String cronExpression) throws Exception
	{           
		jobreschedule(jobClassName, jobGroupName, cronExpression);
		return Result.success(null);
	}

	private void jobreschedule(String jobClassName, String jobGroupName, String cronExpression) throws Exception
	{               
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		} catch (SchedulerException e) {
			System.out.println("更新定时任务失败"+e);
			throw new Exception("更新定时任务失败");
		}
	}


	/**
	 * 删除定时任务
	 * @param jobClassName
	 * @param jobGroupName
	 * @throws Exception
	 */
	@PostMapping(value="/deletejob")
	public Result<?> deletejob(@RequestParam(value="jobClassName")String jobClassName, @RequestParam(value="jobGroupName")String jobGroupName) throws Exception
	{           
		jobdelete(jobClassName, jobGroupName);
		return Result.success(null);
	}

	public void jobdelete(String jobClassName, String jobGroupName) throws Exception
	{       
		scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
		scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
		scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));             
	}


	/**
	 * 查询定时任务列表
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@GetMapping(value="/queryjob")
	public Result<?> queryjob(@RequestParam(value="pageNum",defaultValue="1")Integer pageNum, @RequestParam(value="pageSize",defaultValue="10")Integer pageSize) 
	{           
		PageHelper.startPage(pageNum, pageSize);
		List<JobAndTrigger> jobAndTriggers = jobAndTriggerMapper.getJobAndTriggerDetails();
		return Result.success(jobAndTriggers);
	}

	@SuppressWarnings("unchecked")
	private static Class<Job> getClazz(String classname) throws Exception 
	{
		return (Class<Job>) Class.forName(classname);
	}



}

