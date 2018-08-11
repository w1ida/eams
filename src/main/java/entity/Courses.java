package entity;

public class Courses {
    private String cosid;
    private String cosname;
    private String cosinfo;

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

    public String getCosinfo() {
        return cosinfo;
    }

    public void setCosinfo(String cosinfo) {
        this.cosinfo = cosinfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Courses courses = (Courses) o;

        if (cosid != null ? !cosid.equals(courses.cosid) : courses.cosid != null) return false;
        if (cosname != null ? !cosname.equals(courses.cosname) : courses.cosname != null) return false;
        if (cosinfo != null ? !cosinfo.equals(courses.cosinfo) : courses.cosinfo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cosid != null ? cosid.hashCode() : 0;
        result = 31 * result + (cosname != null ? cosname.hashCode() : 0);
        result = 31 * result + (cosinfo != null ? cosinfo.hashCode() : 0);
        return result;
    }
}
