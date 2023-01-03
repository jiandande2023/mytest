package com.pinyougou.mapper;

import com.pinyougou.model.SpecificationOption;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author atom.hu
 * @version V1.0
 * @Package com.pinyougou.mapper
 * @date 2023/1/1 23:08
 */
public interface SpecificationOptionMapper {
    void addSpecificationOption(@Param("specificationOptionList") List<SpecificationOption> specificationOptionList);

    @Select("SELECT * FROM tb_specification_option WHERE spec_id = #{id}")
    @Results(
            {
             @Result(column = "id",property = "id",id = true),
             @Result(column = "option_name",property = "optionName"),
             @Result(column = "spec_id",property = "specId"),
             @Result(column = "orders",property = "orders")
            }
    )
    List<SpecificationOption> findSpecificationOptionList(Long id);


    /*void updateSpecificationOption(@Param("specificationOptionList") List<SpecificationOption> specificationOptionList);*/

    @Delete("DELETE FROM tb_specification_option WHERE spec_id = #{id}")
    void deleteBySpecId(Long id);

    void deleteSpecificationOption(@Param("ids") List<Long> ids);
}
