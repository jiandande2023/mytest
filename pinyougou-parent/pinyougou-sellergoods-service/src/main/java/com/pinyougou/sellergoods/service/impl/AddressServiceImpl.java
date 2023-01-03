package com.pinyougou.sellergoods.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.AddressMapper;
import com.pinyougou.model.Address;
import com.pinyougou.sellergoods.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import com.alibaba.dubbo.config.annotation.Service;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Address的增删改查
 * @date: ${date}
 *
 ****/
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    /**
     * 增加Address
     * @param address
     * @return
     */
    @Override
    public int add(Address address) {
        return addressMapper.insertSelective(address);
    }


    /***
     * 删除Address
     * @param ids
     * @return
     */
    @Override
    public int delete(List<Long> ids) {
        Example example = new Example(Address.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id",ids);
        return addressMapper.deleteByExample(example);
    }


    /***
     * 根据ID查询Address
     * @param address
     * @return
     */
    @Override
    public int modify(Address address) {
        return addressMapper.updateByPrimaryKeySelective(address);
    }


    /***
     * 根据ID查询Address
     * @param id
     * @return
     */
    @Override
    public Address getById(Long id) {
        return addressMapper.selectByPrimaryKey(id);
    }

    /***
     * 使用通用Mapper查询所有Address
     * @return
     */
    @Override
    public PageInfo<Address> getAll(Integer pageNum,Integer size,Address address) {
        //分页实现
        PageHelper.startPage(pageNum,size);


		/****
		如果使用模糊搜索，则去掉注释，根据需要修改
        //构建条件
        Example example = new Example(Address.class);
        Example.Criteria criteria = example.createCriteria();

        if(brand!=null){
            //根据品牌名字模糊查询 根据Address需要更改
            if(!StringUtils.isEmpty(address.getName())){
                criteria.andLike("name","%"+address.getName()+"%");
            }

            //首字母   根据Address需要更改
            if(!StringUtils.isEmpty(address.getFirstChar())){
                criteria.andEqualTo("firstChar",address.getFirstChar());
            }
        }

        //模糊搜索
        List<Address> list = addressMapper.selectByExample(example);
		****/

        //查询数据
        List<Address> list = addressMapper.selectAll();
        return  new PageInfo<Address>(list);
    }


}
