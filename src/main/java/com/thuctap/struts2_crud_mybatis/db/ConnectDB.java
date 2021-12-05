package com.thuctap.struts2_crud_mybatis.db;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class ConnectDB {
    private static SqlSessionFactory sqlSessionFactory;
    // tên package chứa các mapper
    private static String packageName = "mybatis.mapper";

    private ConnectDB() {
    }

    // code singleton github copilot tự sinh ra
    public static SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory == null) {
            try {
                //System.out.println("Đang tạo sqlSessionFactory");
                String resource = "SqlMapConfig.xml";
                Reader reader = Resources.getResourceAsReader(resource);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
                sqlSessionFactory.getConfiguration().addMappers(packageName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sqlSessionFactory;
    }
}
