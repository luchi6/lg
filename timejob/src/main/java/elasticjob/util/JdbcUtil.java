package elasticjob.util;

import java.sql.*;
import java.util.*;

/**
 * @author luch
 * @date 2021/10/27 0:08
 */


public class JdbcUtil {
    //url
    private static String url = "jdbc:mysql://localhost:3306/lg?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false";
    //user
    private static String user = "root";
    //password
    private static String password = "root";
    //驱动程序类
    private static String driver = "com.mysql.cj.jdbc.Driver";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static void close(ResultSet rs, PreparedStatement ps,
                             Connection con) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } finally {
                        if (con != null) {
                            try {
                                con.close();
                            } catch (SQLException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public static void executeUpdate(String sql, Object... obj) {
        Connection con = getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            close(null, ps, con);
        }
    }


    public static List<Map<String, Object>> executeQuery(String sql, Object... obj) {
        Connection con = getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
            rs = ps.executeQuery();
            List<Map<String, Object>> list = new ArrayList<>();
            int count = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<String,
                        Object>();
                for (int i = 0; i < count; i++) {
                    Object ob = rs.getObject(i + 1);
                    String key = rs.getMetaData().getColumnName(i
                            + 1);
                    map.put(key, ob);
                }
                list.add(map);
            }
            return list;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            close(rs, ps, con);
        }
        return null;
    }

    public static void main(String[] args) throws InterruptedException {
//        String sql = "select * from resume";
//        List<Map<String, Object>> maps = executeQuery(sql);
//        maps.forEach(obj-> System.out.println(obj));
        String[] educations = new String[]{"初中", "高中", "大学"};

        Random rd = new Random();
        for (int i = 152; i < 1000; i++) {
            int index = rd.nextInt(3);
            String sql = "insert into resume values (?,?,?,?,?,?,?)";
            executeUpdate(sql,i,"张三" + i,"n","14458978652","北京",educations[index],"未归档");
            Thread.sleep(50);
        }
    }

}
