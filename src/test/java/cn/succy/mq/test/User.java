package cn.succy.mq.test;

import java.io.Serializable;

/**
 * @author Succy
 * @date 2017-11-02 16:21
 **/

public class User implements Serializable {

    private static final long serialVersionUID = 6787864738816891344L;
    private String name;
    private Integer age;
    private String addr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", addr='" + addr + '\'' +
                '}';
    }
}
