package kh.Dionysus.Dao;

import kh.Dionysus.utils.Common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MemberDelDao {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;
    public boolean memberDelete(String id) {
        int result = 0;
        String sql1 = "DELETE FROM SCORE_TB WHERE USER_ID = ?";
        String sql2 = "DELETE FROM JJIM_TB WHERE USER_ID = ?";
        String sql3 = "DELETE FROM REVIEW_TB WHERE USER_ID = ?";
        String sql4 = "DELETE FROM MEMBER_TB WHERE USER_ID = ?";
        try {
            conn = Common.getConnection();

            // 첫 번째 DELETE 쿼리 실행
            pStmt = conn.prepareStatement(sql1);
            pStmt.setString(1, id);
            result += pStmt.executeUpdate();
            pStmt.close();

            // 두 번째 DELETE 쿼리 실행
            pStmt = conn.prepareStatement(sql2);
            pStmt.setString(1, id);
            result += pStmt.executeUpdate();
            pStmt.close();

            // 세 번째 DELETE 쿼리 실행
            pStmt = conn.prepareStatement(sql3);
            pStmt.setString(1, id);
            result += pStmt.executeUpdate();
            pStmt.close();

            // 네 번째 DELETE 쿼리 실행
            pStmt = conn.prepareStatement(sql4);
            pStmt.setString(1, id);
            result += pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Common.close(pStmt);
            Common.close(conn);
        }

        return result == 4; // 모든 DELETE 쿼리가 성공했을 경우 true 반환
    }
}
