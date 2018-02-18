package com.dil.nit.sch;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(immediate=true, metatype=true, label="Test Scheduler")
@Service(value = Runnable.class)
@Property( name = "scheduler.expression", value = "0 0 14 * * SUN")

public class TestScheduler implements Runnable {
	
	Logger log = LoggerFactory.getLogger(TestScheduler.class);

	public void run() {
		// TODO Auto-generated method stub
		log.info("The scheduler is logging this");
	}
	
}
