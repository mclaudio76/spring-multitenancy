package mclaudio76.application.jpa;

import java.util.logging.Logger;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jta.atomikos.AtomikosXADataSourceWrapper;

public class ApplicationJtaPlatform extends AbstractJtaPlatform {

	private static final long serialVersionUID = 1L;
	
	private static TransactionManager txManager;
	private static UserTransaction    userTx;
	
	@Autowired
	public ApplicationJtaPlatform(TransactionManager txManager, UserTransaction userTx) {
		ApplicationJtaPlatform.txManager = txManager;
		ApplicationJtaPlatform.userTx 	 = userTx;
		Logger.getLogger(this.getClass().getCanonicalName()).info("ApplicationJtaPlatform wired up");
	}
	
	@Override
	protected TransactionManager locateTransactionManager() {
		return txManager;
	}

	@Override
	protected UserTransaction locateUserTransaction() {
		return userTx;
	}
}