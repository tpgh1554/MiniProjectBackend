package kh.Dionysus.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kh.Dionysus.Dto.ReviewDto;
import kh.Dionysus.utils.Common;

public class ReviewDao {
    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement pSmt = null;

    public ReviewDto reviewSelect(String userid) {
        ReviewDto dto = new ReviewDto();
        String sql = null;
        sql = "SELECT REVIEW FROM REVIEW_TB WHERE USER_ID = ?";
        try {
            conn = Common.getConnection();
            pSmt = conn.prepareStatement(sql);
            pSmt.setString(1, userid);
            rs = pSmt.executeQuery();

            while(rs.next()) {
                String review = rs.getString("REVIEW");

                dto.setReview(review);

            }
            Common.close(rs);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

}
