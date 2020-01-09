package com.example.Utpatur;

public class Member {
    private Integer memberId;
    private String fullName;
    private String memberName;
    private String password;

    public Member(){

    }

    public Member(Integer memberId, String fullName, String memberName, String password) {
        this.memberId = memberId;
        this.fullName = fullName;
        this.memberName = memberName;
        this.password = password;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
