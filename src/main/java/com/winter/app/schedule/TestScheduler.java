package com.winter.app.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.winter.app.member.MemberDAO;
//@Component
public class TestScheduler {
	//@Autowired
	private MemberDAO memberDAO;
	
	//@Scheduled(fixedRate = 1000, initialDelay =2000)
	//@Scheduled(fixedRateString = "1000", initialDelayString = "2000")
	public void useFixRate() {
		System.out.println("Fix Rate");
	}
	
	//@Scheduled(fixedDelay = 1000, initialDelay =2000)
	//@Scheduled(fixedDelayString = "2000", initialDelayString = "2000")
	public void useFixDelay() {
		System.out.println("Fix Delay");
	}
	
	//@Scheduled(cron = "30 * * * * *")
	public void userCron() {
		System.out.println("Cron");
		//memberDAO.getDetail(null);
	}
	
	

}
