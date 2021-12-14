package com.console;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import com.google.gson.Gson;
import com.thuctap.struts2_crud_mybatis.db.ConnectDB;
import com.thuctap.struts2_crud_mybatis.model.Account;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mindrot.jbcrypt.BCrypt;

import mybatis.mapper.AccountMapper;

public class TestCheckDateExpired {
    static SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();
   
   public static void main(String[] args) {

    SqlSession sqlSession = sqlSessionFactory.openSession();
    AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
    Account account = accountMapper.getByUsername("thienlam782");
    System.out.println(account.toString());

    
   }
}
