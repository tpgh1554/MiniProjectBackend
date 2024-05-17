package kh.Dionysus.Dao;

import kh.Dionysus.Dto.*;
import kh.Dionysus.utils.Common;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JjimDao {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    public List<MypageDto> jjimSelect(String id) throws SQLException {
        List<MypageDto> list = new ArrayList<>();
        String sql = "SELECT  M.USER_ID, j.ALCOHOL_NAME, j.JJIM " +
                "FROM MEMBER_TB M " +
                "JOIN JJIM_TB j ON M.USER_ID = j.USER_ID " +
                "LEFT JOIN Alcohol_TB a ON a.ALCOHOL_NAME = j.ALCOHOL_NAME " +
                "WHERE M.USER_ID = ?";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, id);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                MypageDto dto = new MypageDto();
                dto.setUser_id(rs.getString("USER_ID"));
                dto.setAlcohol_name(rs.getString("ALCOHOL_NAME"));
                dto.setJjim(rs.getBoolean("JJIM"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Common.close(rs);
            Common.close(pStmt);
            Common.close(conn);
        }
        return list;
    }

    public List<MypageDto> jjimAlcoholSelect(List<MypageDto> dto) throws SQLException {
        List<MypageDto> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            for (int i = 0; i < dto.size(); i++) {
                String sql = "SELECT M.USER_ID, A.COM, A.ALCOHOL_NAME, A.COUNTRY_OF_ORIGIN, A.ABV, A.VOLUME, A.PRICE, " +
                        "R.REVIEW, S.SCORE, J.JJIM " +
                        "FROM MEMBER_TB M " +
                        "JOIN JJIM_TB J ON M.USER_ID = J.USER_ID " +
                        "LEFT JOIN ALCOHOL_TB A ON A.ALCOHOL_NAME = J.ALCOHOL_NAME " +
                        "LEFT JOIN REVIEW_TB R ON A.ALCOHOL_NAME = R.ALCOHOL_NAME " +
                        "LEFT JOIN SCORE_TB S ON J.ALCOHOL_NAME = S.ALCOHOL_NAME " +
                        "WHERE A.ALCOHOL_NAME = ?";

                pStmt = conn.prepareStatement(sql);
                pStmt.setString(1, dto.get(i).getAlcohol_name());
                rs = pStmt.executeQuery();
                while (rs.next()) {
                    MypageDto dto1 = new MypageDto();
                    dto1.setUser_id(rs.getString("USER_ID"));
                    dto1.setCom(rs.getString("COM"));
                    dto1.setAlcohol_name(rs.getString("ALCOHOL_NAME"));
                    dto1.setCountry_of_origin(rs.getString("COUNTRY_OF_ORIGIN"));
                    dto1.setAbv(rs.getInt("ABV"));
                    dto1.setVolume(rs.getInt("VOLUME"));
                    dto1.setPrice(rs.getInt("PRICE"));
                    dto1.setReview(rs.getString("REVIEW"));
                    dto1.setScore(rs.getString("SCORE"));
                    dto1.setJjim(rs.getBoolean("JJIM"));
                    list.add(dto1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Common.close(rs);
            Common.close(pStmt);
            Common.close(conn);
        }
        return list;
    }


}

