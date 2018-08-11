package entity;

import java.io.Serializable;

public class Curricula implements Serializable {
    private String cosid;
    private int tid;
    private String cospwd;
    private Teachers teacher;
    private Courses courses;

    public Teachers getTeacher() {
        return teacher;
    }

    public void setTeacher(Teachers teacher) {
        this.teacher = teacher;
    }

    public Courses getCourses() {
        return courses;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }

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

    public String getCospwd() {
        return cospwd;
    }

    public void setCospwd(String cospwd) {
        this.cospwd = cospwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Curricula curricula = (Curricula) o;

        if (tid != curricula.tid) return false;
        if (cosid != null ? !cosid.equals(curricula.cosid) : curricula.cosid != null) return false;
        if (cospwd != null ? !cospwd.equals(curricula.cospwd) : curricula.cospwd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cosid != null ? cosid.hashCode() : 0;
        result = 31 * result + tid;
        result = 31 * result + (cospwd != null ? cospwd.hashCode() : 0);
        return result;
    }

}
