package entity;

import java.io.Serializable;

public class ScoresPK implements Serializable {
    private int sid;
    private int tid;
    private String cosid;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScoresPK scoresPK = (ScoresPK) o;

        if (sid != scoresPK.sid) return false;
        if (tid != scoresPK.tid) return false;
        if (cosid != null ? !cosid.equals(scoresPK.cosid) : scoresPK.cosid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sid;
        result = 31 * result + tid;
        result = 31 * result + (cosid != null ? cosid.hashCode() : 0);
        return result;
    }
}
