package entity;

import java.io.Serializable;

public class CurriculaPK implements Serializable {
    private String cosid;
    private int tid;

    public String getCosid() {
        return cosid;
    }

    public void setCosid(String cosid) {
        this.cosid = cosid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurriculaPK that = (CurriculaPK) o;

        if (tid != that.tid) return false;
        if (cosid != null ? !cosid.equals(that.cosid) : that.cosid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cosid != null ? cosid.hashCode() : 0;
        result = 31 * result + tid;
        return result;
    }
}
