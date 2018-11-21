package com.usst.demo.repo;

import com.usst.demo.vo.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDataRepository {
    private JdbcTemplate jdbc;
    @Autowired
    public UserDataRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }
    public UserData getUserData(Integer uid){
        List<UserData> list = jdbc.query("select phone_number, email, grade," +
                "university.name,sex,user_basic.username, user_basic.nickname," +
                " register_date from user_basic,user_info left join university on " +
                        "user_info.university_id=university.university_id" +
                    " where user_basic.uid=? and user_info.uid=user_basic.uid;",
                new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1,uid.toString());
            }
        }, new RowMapper<UserData>() {
            @Override
            public UserData mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserData userData = new UserData();
                userData.setPhone_number(rs.getString(1));
                userData.setEmail(rs.getString(2));
                userData.setGrade(rs.getString(3));
                userData.setUniversity(rs.getString(4));
                String sex = rs.getString(5);
                userData.setUid(uid.toString());
                userData.setUsername(rs.getString(6));
                userData.setNickName(rs.getString(7));
                userData.setSex(sex);
                userData.setRegisterDate(rs.getDate(8));
                return userData;
            }
        });
        return list==null||list.size()==0?null:list.get(0);
    }

}
