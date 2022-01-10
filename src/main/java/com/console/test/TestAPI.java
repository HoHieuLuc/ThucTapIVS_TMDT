package com.console.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.tmdt.db.ConnectDB;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.mapper.BaoCaoNguoiDungMapper;

public class TestAPI {

    static SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();
    public static void main(String[] args) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        BaoCaoNguoiDungMapper baoCaoNguoiDungMapper = sqlSession.getMapper(BaoCaoNguoiDungMapper.class);

        List<Map<String, Object>> chiTietBaoCao = baoCaoNguoiDungMapper.detaiBaoCao(1);
       // Map<String, Object> jsonRes = new HashMap<String, Object>();
      //  jsonRes.put("chitiet_baocao", chiTietBaoCao);
        sqlSession.close();
        Gson gson = new Gson();
        System.out.println(gson.toJson(chiTietBaoCao));
       // return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }
}
