package org.fkjava.example.s2s3m3.db.entity;

/**
 *
 * @author rodney
 */
public class User {

    /**
     * 主键
     */
    private int id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private Gender gender;
    /**
     * 年龄
     */
    private Integer age;

    /**
     * 主键
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * 主键
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 姓名
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * 姓名
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 性别
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * 性别
     * @param gender the gender to set
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * 年龄
     * @return the age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 年龄
     * @param age the age to set
     */
    public void setAge(Integer age) {
        this.age = age;
    }
}
