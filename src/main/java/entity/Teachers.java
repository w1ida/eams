package entity;

public class Teachers {
    private int tid;
    private String tname;
    private String tinfo;
    private String pwd;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTinfo() {
        return tinfo;
    }

    public void setTinfo(String tinfo) {
        this.tinfo = tinfo;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teachers teachers = (Teachers) o;

        if (tid != teachers.tid) return false;
        if (tname != null ? !tname.equals(teachers.tname) : teachers.tname != null) return false;
        if (tinfo != null ? !tinfo.equals(teachers.tinfo) : teachers.tinfo != null) return false;
        if (pwd != null ? !pwd.equals(teachers.pwd) : teachers.pwd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tid;
        result = 31 * result + (tname != null ? tname.hashCode() : 0);
        result = 31 * result + (tinfo != null ? tinfo.hashCode() : 0);
        result = 31 * result + (pwd != null ? pwd.hashCode() : 0);
        return result;
    }
}
