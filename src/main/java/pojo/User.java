package pojo;

/**
 * Created by Daihangtao on 2017/9/9.
 */
public class User {
    String uid;

    public String getUid() {

        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                '}';
    }
}
