package com.console.test;

import java.util.List;

import com.google.gson.Gson;
import com.thuctap.struts2_crud_mybatis.db.ConnectDB;
import com.thuctap.struts2_crud_mybatis.model.KhoaStudent;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.mapper.KhoaStudentMapper;

public class TestInnerJoin {
static SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();
   
   public static void main(String[] args) {

    SqlSession sqlSession = sqlSessionFactory.openSession();
    KhoaStudentMapper khoaStudentMapper = sqlSession.getMapper(KhoaStudentMapper.class);
    List<KhoaStudent> khoaStudent = khoaStudentMapper.getAll();
  
    Gson gson = new Gson();
    System.out.println(gson.toJson(khoaStudent));

    
   }
}
