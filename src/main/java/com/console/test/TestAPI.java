package com.console.test;


import com.tmdt.db.ConnectDB;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;



public class TestAPI {

    static SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();
    public static void main(String[] args) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //BaoCaoNguoiDungMapper baoCaoNguoiDungMapper = sqlSession.getMapper(BaoCaoNguoiDungMapper.class);

        //List<Map<String, Object>> chiTietBaoCao = baoCaoNguoiDungMapper.detaiBaoCao(1);
       // Map<String, Object> jsonRes = new HashMap<String, Object>();
      //  jsonRes.put("chitiet_baocao", chiTietBaoCao);
        sqlSession.close();
        //Gson gson = new Gson();
        //System.out.println(gson.toJson(chiTietBaoCao));
       // return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }
}
