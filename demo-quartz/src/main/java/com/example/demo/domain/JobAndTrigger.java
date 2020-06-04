package com.example.demo.domain;

import lombok.Data;

import java.math.BigInteger;

/**
 * @author admin
 */
@Data
public class JobAndTrigger {
	private String JOB_NAME;
	private String JOB_GROUP;
	private String JOB_CLASS_NAME;
	private String TRIGGER_NAME;
	private String TRIGGER_GROUP;
	private BigInteger REPEAT_INTERVAL;
	private BigInteger TIMES_TRIGGERED;
	private String CRON_EXPRESSION;
	private String TIME_ZONE_ID;
}
