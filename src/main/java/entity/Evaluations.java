package entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Evaluations implements Serializable {
    private int sid;
    private int tid;
    private String cosid;
    private Timestamp time;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private int score;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getCosid() {
        return cosid;
    }

    public void setCosid(String cosid) {
        this.cosid = cosid;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Evaluations that = (Evaluations) o;

        if (sid != that.sid) return false;
        if (tid != that.tid) return false;
        if (cosid != null ? !cosid.equals(that.cosid) : that.cosid != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (score != that.score ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sid;
        result = 31 * result + tid;
        result = 31 * result + (cosid != null ? cosid.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + score;
        return result;
    }
}
