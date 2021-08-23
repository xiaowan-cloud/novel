package com.example.demo.util;

import com.example.demo.entity.PageInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wan
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021年08月19日 15:10:39
 */
public class FileUtil{

    /**
     * 得到文件
     * @param path
     * @return
     */
    public static List<String> getFile(String path) {
        List<String> list = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))))){
            String text = null;
            while ((text = br.readLine()) != null) {
                list.add(text);
            }
        }catch (Exception e){
            System.out.println("产生异常"+e.getMessage());
        }
        return list;
    }

    /**
     * 获取分页小说
     * @param name
     * @param pageNum
     * @return
     */
    public static List<String> getNovel(String name, int pageNum) {
        List<String> novels = new ArrayList<>();
        int num = 60;
        //获取小说路径
        String path = getPath(name);
        //根据路径读取小说
        List<String> file = getFile(path);
        //集合筛选需要的页数

        if (file.size() > 10) {
            novels.addAll(file.subList(num*(pageNum-1), num*pageNum));
        }else {
            for (int i = file.size(); i < num; i++) {
                file.add(" ");
            }
        }
        novels.forEach(System.out::println);
        return novels;
    }
    /**
     * 获取小说路径
     * @return
     */
    public static String getPath(String name) {
        try {
            JDBCUtil.stat = JDBCUtil.getConn();
            String sql = "select path from t_novel where novel_name = "+"\""+name+"\"";
            JDBCUtil.result = JDBCUtil.stat.executeQuery(sql);
            String path = null;
            while (JDBCUtil.result.next()) {
                path = JDBCUtil.result.getString("path");
            }
            JDBCUtil.closeSource();;
            return path;
        } catch (SQLException e) {
            System.out.println("数据库连接异常："+e.getMessage());
        }
        return null;
    }
    public static void main(String[] args) {
        List<String> list = getNovel("遮天", 1);
    }
}

class JDBCUtil {

    static Connection conn =null;
    static Statement stat = null;
    static ResultSet result = null;

    /**
     * 获取连接
     * @return
     */
    public static Statement getConn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://139.224.22.145:3306/wan?characterEncoding=utf8&useUnicode=true&useSSL=false&serverTimezone=GMT%2B8", "root", "123456");
            stat = conn.createStatement();
            return stat;
        } catch (Exception e) {
            System.out.println("出错了:"+e.getMessage());
        }
        return null;
    }

    public static void closeSource() throws SQLException {
        if (null != conn) {
            conn.close();
        }
        if (null != stat) {
            stat.close();
        }
        if (null != result) {
            result.close();
        }
    }

}

