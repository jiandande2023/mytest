package com.pinyougou.sellergoods.service;
import com.github.pagehelper.PageInfo;
import com.pinyougou.model.Address;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Address的增删改查
 * @date: 2018年11月7日19:19:07
 *
 ****/
public interface AddressService {

    /***
     * 增加Address
     * @param address
     * @return
     */
    int add(Address address);

    /***
     * 删除操作
     * @param ids
     * @return
     */
    int delete(List<Long> ids);


    /**
     * 修改Address
     * @param address
     * @return
     */
    int modify(Address address);

    /***
     * 根据ID查询Address
     * @param id
     * @return
     */
    Address getById(Long id);

    /***
     * 查询所有Address
     * @return
     */
    PageInfo<Address> getAll(Integer pageNum, Integer size, Address address);

}
