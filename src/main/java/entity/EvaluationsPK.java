package entity;

import java.io.Serializable;

public class EvaluationsPK implements Serializable {
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

        EvaluationsPK that = (EvaluationsPK) o;

        if (sid != that.sid) return false;
        if (tid != that.tid) return false;
        if (cosid != null ? !cosid.equals(that.cosid) : that.cosid != null) return false;

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
