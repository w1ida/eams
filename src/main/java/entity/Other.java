package entity;

/**
 * @ Author     ：viete.
 * @ Date       ：Created in 9:21 2018/6/7
 * @ Description：
 */
public class Other<T>{
    public Other(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private T data;

}
