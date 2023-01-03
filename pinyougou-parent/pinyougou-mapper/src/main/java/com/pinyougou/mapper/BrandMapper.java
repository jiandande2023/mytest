package com.pinyougou.mapper;

import com.pinyougou.model.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author atom.hu
 * @version V1.0
 * @Package com.pinyougou.mapper
 * @date 2023/1/1 18:32
 */
public interface BrandMapper {

    List<Brand> list(Brand brand);

    @Insert("INSERT INTO tb_brand VALUES(NULL,#{name},#{firstChar})")
    int addBrand(Brand brand);

    @Select("select * from tb_brand where id = #{id}")
    Brand getById(Long id);

    @Update("UPDATE tb_brand SET NAME = #{name},first_char = #{firstChar} WHERE id = #{id}")
    int updateBrand(Brand brand);

    int deleteBrand(@Param("ids") List<Long> ids);
}
