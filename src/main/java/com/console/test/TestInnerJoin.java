package com.console.test;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.thuctap.struts2_crud_mybatis.db.ConnectDB;
import com.thuctap.struts2_crud_mybatis.model.KhoaStudent;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.mapper.KhoaStudentMapper;

public class TestInnerJoin {
static SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();
   
   public static void main(String[] args) {

      // s.name, k.TenKhoa
    SqlSession sqlSession = sqlSessionFactory.openSession();
    KhoaStudentMapper khoaStudentMapper = sqlSession.getMapper(KhoaStudentMapper.class);
     List<Map<String,Object>> khoaStudent = khoaStudentMapper.getAll("H6bz8mNh4214124241244124","lamminhthien@gmail.com");
     
   //List<KhoaStudent> khoaStudent = khoaStudentMapper.getAll("s.name","k.TenKhoa");
  
   Gson gson = new Gson();
   System.out.println(gson.toJson(khoaStudent));

    
   }
}
