//package kh.Dionysus.Dao;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//import kh.Dionysus.Dto.MypageDto;
//import kh.Dionysus.Dto.ReviewDto;
//import kh.Dionysus.utils.Common;
//
//// 리뷰 조회
//public class ReviewDao {
//    private Connection conn = null;
//    private Statement stmt = null;
//    private ResultSet rs = null;
//    private PreparedStatement pStmt = null;
//
//    public List<MypageDto> reviewSelect(String id) throws SQLException {
//        List<MypageDto> list = new ArrayList<>();
//        String sql = "SELECT  M.USER_ID, R.ALCOHOL_NAME, R.REVIEW " +
//                "FROM MEMBER_TB M " +
//                "JOIN REVIEW_TB R ON M.USER_ID = R.USER_ID " +
//                "LEFT JOIN ALCOHOL_TB A ON A.ALCOHOL_NAME = R.ALCOHOL_NAME " +
//                "WHERE M.USER_ID = ?";
//
//        try {
//            conn = Common.getConnection();
//            pStmt = conn.prepareStatement(sql);
//            pStmt.setString(1, id);
//            rs = pStmt.executeQuery();
//            while (rs.next()) {
//                MypageDto dto = new MypageDto();
//                dto.setAlcohol_name(rs.getString("ALCOHOL_NAME"));
//                dto.setReview(rs.getString("REVIEW"));
//                list.add(dto);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            Common.close(rs);
//            Common.close(pStmt);
//            Common.close(conn);
//        }
//        return list;
//    }
//
//    public List<MypageDto> reviewAlcoholSelect(List<MypageDto> dto) throws SQLException {
//        List<MypageDto> list = new ArrayList<>();
//        try {
//            conn = Common.getConnection();
//            for (int i = 0; i < dto.size(); i++) {
//                String sql = "SELECT M.USER_ID, a.COM, a.ALCOHOL_NAME, a.COUNTRY_OF_ORIGIN, a.ABV, a.VOLUME, a.PRICE, " +
//                        "r.REVIEW, s.SCORE, j.JJIM " +
//                        "FROM ALCOHOL_TB a " +
//                        "JOIN REVIEW_TB r ON a.ALCOHOL_NAME = r.ALCOHOL_NAME " +
//                        "LEFT JOIN SCORE_TB s ON a.ALCOHOL_NAME = s.ALCOHOL_NAME " +
//                        "LEFT JOIN JJIM_TB j ON a.ALCOHOL_NAME = j.ALCOHOL_NAME " +
//                        "LEFT JOIN MEMBER_TB m ON m.USER_ID = j.USER_ID " +
//                        "WHERE a.ALCOHOL_NAME = ?";
//                pStmt = conn.prepareStatement(sql);
//                pStmt.setString(1, dto.get(i).getAlcohol_name());
//                rs = pStmt.executeQuery();
//                while (rs.next()) {
//                    MypageDto dto1 = new MypageDto();
//                    dto1.setUser_id(rs.getString("USER_ID"));
//                    dto1.setCom(rs.getString("COM"));
//                    dto1.setAlcohol_name(rs.getString("ALCOHOL_NAME"));
//                    dto1.setCountry_of_origin(rs.getString("COUNTRY_OF_ORIGIN"));
//                    dto1.setAbv(rs.getInt("ABV"));
//                    dto1.setVolume(rs.getInt("VOLUME"));
//                    dto1.setPrice(rs.getInt("PRICE"));
//                    dto1.setReview(rs.getString("REVIEW"));
//                    dto1.setScore(rs.getString("SCORE"));
//                    dto1.setJjim(rs.getBoolean( "JJIM"));
//                    list.add(dto1);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            Common.close(rs);
//            Common.close(pStmt);
//            Common.close(conn);
//        }
//        return list;
//    }
//
//}
