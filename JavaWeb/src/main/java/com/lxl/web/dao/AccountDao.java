package com.lxl.web.dao;

import com.lxl.web.model.Account;
import org.apache.ibatis.annotations.Param;

 /*
   * @Description: TODO
   * @author: lxl
   * @date: 2017/7/26
   */
public interface AccountDao {
    Account getAccountByPhone(@Param("phone") String phone);
}
