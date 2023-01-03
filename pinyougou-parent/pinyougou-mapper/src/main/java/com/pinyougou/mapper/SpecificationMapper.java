package com.pinyougou.mapper;

import com.pinyougou.model.Specification;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author atom.hu
 * @version V1.0
 * @Package com.pinyougou.mapper
 * @date 2023/1/1 22:44
 */
public interface SpecificationMapper {
    List<Specification> findAll(Specification specification);


    int addSpecification(Specification specification);

    @Select("SELECT * FROM tb_specification where id = #{id}")
    @Results({
            @Result(column = "id",property = "id",id = true),
            @Result(column = "spec_name",property = "specName"),
            @Result(column = "id",
                    property = "specificationOptionList",
                    javaType = List.class,
                    many = @Many(select = "com.pinyougou.mapper.SpecificationOptionMapper.findSpecificationOptionList")
            )
    })
    Specification getById(Long id);

    @Update("UPDATE tb_specification SET spec_name = #{specName} WHERE id = #{id}")
    int updateSpecification(Specification specification);

    int deleteSpecification(@Param("ids") List<Long> ids);
}
