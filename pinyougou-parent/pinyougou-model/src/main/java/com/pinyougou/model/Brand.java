package com.pinyougou.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "tb_brand")
public class Brand implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 品牌名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 品牌首字母
     */
    @Column(name = "first_char")
    private String firstChar;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Long getId() {
		System.out.pritln("你好");
		System.out.pritln("你好1");
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取品牌名称
     *
     * @return name - 品牌名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置品牌名称
     *
     * @param name 品牌名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取品牌首字母
     *
     * @return first_char - 品牌首字母
     */
    public String getFirstChar() {
        return firstChar;
    }

    /**
     * 设置品牌首字母
     *
     * @param firstChar 品牌首字母
     */
    public void setFirstChar(String firstChar) {
        this.firstChar = firstChar == null ? null : firstChar.trim();
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstChar='" + firstChar + '\'' +
                '}';
    }
}