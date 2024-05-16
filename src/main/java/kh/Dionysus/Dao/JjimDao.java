package kh.Dionysus.Dao;

import kh.Dionysus.Dto.JjimDto;
import kh.Dionysus.utils.Common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

    public class JjimDao {
        private Connection conn = null;
        private ResultSet rs = null;
        private PreparedStatement pSmt = null;

        public JjimDto jjimSelect(String userid) {
            JjimDto dto = new JjimDto();
            String sql = null;
            sql = "SELECT JJIM FROM JJIM_TB WHERE USER_ID = ?";
            try {
                conn = Common.getConnection();
                pSmt = conn.prepareStatement(sql);
                pSmt.setString(1, userid);
                rs = pSmt.executeQuery();

                while(rs.next()) {
                    boolean jjim = rs.getBoolean("JJIM");

                    dto.setJjim(jjim);

                }
                Common.close(rs);
                Common.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return dto;
        }

    }
