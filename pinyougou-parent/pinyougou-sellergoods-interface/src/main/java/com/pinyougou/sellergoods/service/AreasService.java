package com.pinyougou.sellergoods.service;
import com.github.pagehelper.PageInfo;
import com.pinyougou.model.Areas;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Areas的增删改查
 * @date: 2018年11月7日19:19:07
 *
 ****/
public interface AreasService {

    /***
     * 增加Areas
     * @param areas
     * @return
     */
    int add(Areas areas);

    /***
     * 删除操作
     * @param ids
     * @return
     */
    int delete(List<Long> ids);


    /**
     * 修改Areas
     * @param areas
     * @return
     */
    int modify(Areas areas);

    /***
     * 根据ID查询Areas
     * @param id
     * @return
     */
    Areas getById(Long id);

    /***
     * 查询所有Areas
     * @return
     */
    PageInfo<Areas> getAll(Integer pageNum, Integer size, Areas areas);

}
