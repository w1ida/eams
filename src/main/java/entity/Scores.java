package entity;

import java.io.Serializable;

public class Scores implements Serializable {
    private int sid;



    private int cid;
    private String sname;
    private String tname;

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    private int tid;
    private String cosid;
    private String cosname;
    private Double score;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
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

    public String getCosname() {
        return cosname;
    }

    public void setCosname(String cosname) {
        this.cosname = cosname;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Scores scores = (Scores) o;

        if (sid != scores.sid) return false;
        if (tid != scores.tid) return false;
        if (sname != null ? !sname.equals(scores.sname) : scores.sname != null) return false;
        if (cosid != null ? !cosid.equals(scores.cosid) : scores.cosid != null) return false;
        if (cosname != null ? !cosname.equals(scores.cosname) : scores.cosname != null) return false;
        if (score != null ? !score.equals(scores.score) : scores.score != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sid;
        result = 31 * result + (sname != null ? sname.hashCode() : 0);
        result = 31 * result + tid;
        result = 31 * result + (cosid != null ? cosid.hashCode() : 0);
        result = 31 * result + (cosname != null ? cosname.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        return result;
    }
}
