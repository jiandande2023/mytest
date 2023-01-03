package com.pinyougou.sellergoods.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.AreasMapper;
import com.pinyougou.model.Areas;
import com.pinyougou.sellergoods.service.AreasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import com.alibaba.dubbo.config.annotation.Service;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Areas的增删改查
 * @date: ${date}
 *
 ****/
@Service
public class AreasServiceImpl implements AreasService {

    @Autowired
    private AreasMapper areasMapper;

    /**
     * 增加Areas
     * @param areas
     * @return
     */
    @Override
    public int add(Areas areas) {
        return areasMapper.insertSelective(areas);
    }


    /***
     * 删除Areas
     * @param ids
     * @return
     */
    @Override
    public int delete(List<Long> ids) {
        Example example = new Example(Areas.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id",ids);
        return areasMapper.deleteByExample(example);
    }


    /***
     * 根据ID查询Areas
     * @param areas
     * @return
     */
    @Override
    public int modify(Areas areas) {
        return areasMapper.updateByPrimaryKeySelective(areas);
    }


    /***
     * 根据ID查询Areas
     * @param id
     * @return
     */
    @Override
    public Areas getById(Long id) {
        return areasMapper.selectByPrimaryKey(id);
    }

    /***
     * 使用通用Mapper查询所有Areas
     * @return
     */
    @Override
    public PageInfo<Areas> getAll(Integer pageNum,Integer size,Areas areas) {
        //分页实现
        PageHelper.startPage(pageNum,size);


		/****
		如果使用模糊搜索，则去掉注释，根据需要修改
        //构建条件
        Example example = new Example(Areas.class);
        Example.Criteria criteria = example.createCriteria();

        if(brand!=null){
            //根据品牌名字模糊查询 根据Areas需要更改
            if(!StringUtils.isEmpty(areas.getName())){
                criteria.andLike("name","%"+areas.getName()+"%");
            }

            //首字母   根据Areas需要更改
            if(!StringUtils.isEmpty(areas.getFirstChar())){
                criteria.andEqualTo("firstChar",areas.getFirstChar());
            }
        }

        //模糊搜索
        List<Areas> list = areasMapper.selectByExample(example);
		****/

        //查询数据
        List<Areas> list = areasMapper.selectAll();
        return  new PageInfo<Areas>(list);
    }


}
