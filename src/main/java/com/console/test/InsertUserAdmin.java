package com.console.test;
import com.thuctap.struts2_crud_mybatis.db.ConnectDB;
import com.thuctap.struts2_crud_mybatis.model.UserAdmin;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import com.google.common.hash.Hashing;

import mybatis.mapper.UserAdminMapper;


public class InsertUserAdmin {
   
    public static void main(String[] args)
    {   
        SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        
        String userName = "lamminhthien";
        String email = "lamminhthien@gmail.com";
        String password = "MinhThien2000$";

        //Tạo userAdminMapper
        UserAdminMapper userAdminMapper = sqlSession.getMapper(UserAdminMapper.class);
    
        //Hash password sang SHA256:
        // password = Hashing.sha256()
        //             .hashString(password, StandardCharsets.UTF_8)
        //             .toString();
    
        //Tạo đối tượng lấy dữ liệu useradmin từ constructor
        UserAdmin userAdmin = new UserAdmin(userName, password, email);

        //Thêm dữ liệu vào database
        userAdminMapper.insert(userAdmin);
        //Flush database connection, batch script and close connection
        sqlSession.commit();
        sqlSession.close(); 
    }

}
