package com.example.Utpatur;

public class Member {
    private Integer memberId;
    private String email;
    private String memberName;
    private String password;

    public Member(){

    }

    public Member(Integer memberId, String email, String memberName, String password) {
        this.memberId = memberId;
        this.email = email;
        this.memberName = memberName;
        this.password = password;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
