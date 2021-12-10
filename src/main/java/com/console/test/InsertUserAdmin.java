package com.console.test;

import java.time.LocalDate;
import java.sql.Date;

import com.thuctap.struts2_crud_mybatis.db.ConnectDB;
import com.thuctap.struts2_crud_mybatis.model.UserAdmin;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mindrot.jbcrypt.BCrypt;

import mybatis.mapper.UserAdminMapper;

public class InsertUserAdmin {

    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        String userName = "lamminhthien";
        String email = "lamminhthien@gmail.com";
        String password = "MinhThien2000$";

        // Lấy ngày hiện tại:
        LocalDate today = LocalDate.now();
        // Đổi ngày tạo tài khoản và ngày hết hạn sang SQL Date
        Date date_created = Date.valueOf(today);
        Date date_expired = Date.valueOf(today.plusMonths(1));

        // Tạo userAdminMapper
        UserAdminMapper userAdminMapper = sqlSession.getMapper(UserAdminMapper.class);

        // String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
        // Hash password sang BCrypt:
        password = BCrypt.hashpw(password, BCrypt.gensalt(12));

        // Tạo đối tượng lấy dữ liệu useradmin từ constructor
        UserAdmin userAdmin = new UserAdmin(userName, password, email, date_created, date_expired);

        // Thêm dữ liệu vào database
        //userAdminMapper.insert(userAdmin);
        // Flush database connection, batch script and close connection

        //Log Exception
        try {
            userAdminMapper.insert(userAdmin);
        }
        catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }
        

        sqlSession.commit();
        sqlSession.close();
    }

}
