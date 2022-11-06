package com.devsuperior.hrpayroll.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.devsuperior.hrpayroll.entities.Payment;
import com.devsuperior.hrpayroll.entities.Worker;
import com.devsuperior.hrpayroll.feignclients.WorkerFeignClient;

@Service // Faz com que essa classe se torne componente do spring
public class PaymentService {

	/*
	@Value("${hr-worker.host}")
	private String workerHost;
	*/
	
	/*@Autowired
	private RestTemplate restTemplate;
	*/
	
	
	@Autowired
	private WorkerFeignClient workerFeignClient;
	

	public Payment getPayment(long workerId, int days) {
		Worker worker = workerFeignClient.findById(workerId).getBody();
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}
}
