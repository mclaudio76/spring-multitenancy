package mclaudio76.application.core.services;

import java.util.logging.Logger;

public class BaseService {
	private Logger log;
	
	public BaseService() {
		log =  Logger.getLogger(getClass().getName());
	}
	
	protected void logInfo(String message) {
		log.info(message);
	}
}
