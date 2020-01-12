package com.none.appFinancas;

import com.none.appFinancas.service.Jobs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class AppFinancasApplication {

	@Autowired
	private Jobs jobs;

	public static void main(String[] args) {
		SpringApplication.run(AppFinancasApplication.class, args);
	}

	@Scheduled(cron = "0 0 1 1 1/1 ?")
	public void turnAllFixedExpensesToPending() {
		jobs.turnExpenseStatusToPending();
	}

}
