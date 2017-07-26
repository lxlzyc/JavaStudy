package com.lxl.web.service;

import com.lxl.web.dao.AccountDao;
import com.lxl.web.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
  * @Description: TODO
  * @author: lxl
  * @date: 2017/7/26
  */
@Repository
public class AccountService {

    @Autowired
    AccountDao accountDao;

    public Account getAccountByPhone(String phone){
        return  accountDao.getAccountByPhone(phone);
    }
}
