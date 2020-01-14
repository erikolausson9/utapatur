package com.example.Utpatur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



@Repository
public class MemberRepository {
    @Autowired
     DataSource dataSource;
/*
    @Autowired
    PasswordEncoder encoder;*/

    //instance variables
    private List<Member> members;

    public MemberRepository() {
    }


    //helper function to map field names to variable memberName
    public Member rsMember(ResultSet rs) throws SQLException {
        Member member = new Member();
        member.setMemberId(rs.getInt("memberId"));
        member.setEmail(rs.getString("email"));
        member.setMemberName(rs.getString("memberName"));
        member.setPassword(rs.getString("password"));

        return member;
    }

    //add member to database
    public void addMember(Member memberToAdd) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO Member (memberName, password, email) VALUES(?, ?, ?)")) {
             ps.setString(1, memberToAdd.getMemberName());
             ps.setString(2,memberToAdd.getPassword());
             ps.setString(3, memberToAdd.getEmail());
             ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //get members from database
    public List<Member> getMembers(){
        members.clear();

        try (Connection conn = dataSource.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Member")){
            while(rs.next()){
                members.add(rsMember(rs));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return members;

    }

    //Check email for login
    public Member getMemberByEmail(String email) {
        Member member = null;
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Member WHERE Email ='" + email +"'")) {     //Email el email? påverkar det?
            if (rs.next()) {
                member = rsMember(rs);

            }
        }
        catch (SQLException e) {

            e.printStackTrace();
        }
        return member;
    }

    //Get memberId
    public Member getMemberById(int memberId){ //TODO: testa om denna funktion gör vad den ska!

        Member member = new Member();
        try(Connection conn = dataSource.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Member WHERE MemberID=" + memberId)){
            if(rs.next()){
                member = rsMember(rs);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return member;
    }

    public Member getMemberByMemberName(String memberName) {
        Member member = new Member();
        try(Connection conn = dataSource.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Member WHERE MemberName='" + memberName + "'")){
            if(rs.next()){
                member = rsMember(rs);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return member;
    }
}
