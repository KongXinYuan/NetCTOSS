package org.tarena.netctoss.util;

import org.apache.log4j.Logger;
public class LoggerException {
	public void execute(Exception ex){
		Logger logger = Logger.getLogger(this.getClass());
		logger.error(ex.getClass().getName());
		logger.error(ex);
				
	}
}
