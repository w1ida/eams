package entity;

public class Classes {
    private int cid;
    private String cname;
    private String cinfo;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCinfo() {
        return cinfo;
    }

    public void setCinfo(String cinfo) {
        this.cinfo = cinfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Classes classes = (Classes) o;

        if (cid != classes.cid) return false;
        if (cname != null ? !cname.equals(classes.cname) : classes.cname != null) return false;
        if (cinfo != null ? !cinfo.equals(classes.cinfo) : classes.cinfo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cid;
        result = 31 * result + (cname != null ? cname.hashCode() : 0);
        result = 31 * result + (cinfo != null ? cinfo.hashCode() : 0);
        return result;
    }
}
