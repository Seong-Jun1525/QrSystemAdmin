package kr.ac.yuhan.cs.qradmin.data;

import java.util.Date;

public class MemberData {
    // Member Data Field
    private int number;
    private String memberId;
    private Date joinDate;
    private int point;

    // MemberData Constructor
    public MemberData(int number, String memberId, Date joinDate, int point) {
        this.number = number;
        this.memberId = memberId;
        this.joinDate = joinDate;
        this.point = point;
    }

    // Getter & Setter
    public int getNumber() {
        return number;
    }

    public String getMemberId() {
        return memberId;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public int getPoint() {
        return point;
    }
}
