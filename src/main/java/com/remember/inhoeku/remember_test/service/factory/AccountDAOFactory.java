package com.remember.inhoeku.remember_test.service.factory;

import com.remember.inhoeku.remember_test.dao.AccountDAO;
import com.remember.inhoeku.remember_test.dao.ClientAccountDAO;
import com.remember.inhoeku.remember_test.dao.DriverAccountDAO;
import com.remember.inhoeku.remember_test.domain.enumeration.ACCOUNT_TYPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountDAOFactory {
	@Autowired
	private ClientAccountDAO clientAccountDAO;

	@Autowired
	private DriverAccountDAO driverAccountDAO;

	public AccountDAO getDAO(ACCOUNT_TYPE account_type){
		if(account_type == ACCOUNT_TYPE.PASSENGER){ return clientAccountDAO;}
		else if(account_type == ACCOUNT_TYPE.DRIVER) { return driverAccountDAO;}
		else{ return null;}
	}
}
