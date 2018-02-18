package com.dil.nit.schedular;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// */5 * * * * Every 5 minutes. 
// */30 * * * * Every 30 minutes. 
// 0 */12 * * * Every 12 hours, on the hour.
// */20 * * * 1-5 Every 20 minutes, Monday through Friday. 
// 0 9 1-7 * 1 First Monday of each month, at 9am.

@Component
@Service(value = Runnable.class)
@Property(name = "scheduler.period", value = "0 */2 * * * ?")
// longValue=some value this will be in seconds
public class ScheduledPeriodicJob implements Runnable {

	/** Default log. */
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	public void run() {
		log.info("Executing a perodic job (job#2) through the whiteboard pattern");
	}
	//
}