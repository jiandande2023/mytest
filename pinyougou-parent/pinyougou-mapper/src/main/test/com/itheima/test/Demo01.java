package com.itheima.test;

import com.pinyougou.model.Brand;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author atom.hu
 * @version V1.0
 * @Package com.itheima.test
 * @date 2022/3/30 16:36
 */
public class Demo01 {

    //BrandMapper接口代理对象注入
    private BrandMapper brandMapper;

    @Before
    public void init(){
        //创建ApplicationContext 容器对象
        ApplicationContext act = new ClassPathXmlApplicationContext("classpath:/spring/spring-mybatis.xml");
        //获取BrandMapper接口代理对象
        brandMapper = act.getBean(BrandMapper.class);
    }

    /***
     * 查询所有
     */
    @Test
    public void testSelectAll(){
        //调用接口方法查询数据
        List<Brand> brands = brandMapper.selectAll();
        //输出结果
        for (Brand brand : brands) {
            System.out.println(brand);
        }
    }

    @Test
    public void testInsert(){
        Brand brand = new Brand();
        brand.setName("深圳黑马训练营");
        brand.setFirstChar("C");
        int acount = brandMapper.insert(brand);
        System.out.println(acount);
    }

    @Test
    public void testInsertSelective(){
        Brand brand = new Brand();
        brand.setName("传智播客-黑马训练营");
        //brand.setFirstChar("C");
        int acount = brandMapper.insertSelective(brand);
        System.out.println(acount);
    }

    @Test
    public void testUpdateByPrimaryKeySelective(){
        Brand brand = new Brand();
        brand.setId(25L);
        brand.setName("深圳黑马训练营");
        //brand.setFirstChar("S");
        //根据主键修改数据
        int mcount = brandMapper.updateByPrimaryKeySelective(brand);
        System.out.println(mcount);
    }

    @Test
    public void testUpdateByExample(){
        //firstChar=S
        Brand brand = new Brand();
        brand.setName("深圳市黑马训练");

        //创建Example对象
        Example example = new Example(Brand.class);

        //Criteria 用来构造约束条件
        Example.Criteria criteria = example.createCriteria();

        //第一个参数是Brand对应的属性，第二个参数是属性约束值   相当于 where firstChar=S
        criteria.andEqualTo("firstChar","C");

        //条件修改数据
        int mcount = brandMapper.updateByExampleSelective(brand,example);
        System.out.println(mcount);
    }

    @Test
    public void testExample(){
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        //id IN(1,2,5,6)
        List<Long> ids = new ArrayList<Long>();
        ids.add(1L);
        ids.add(2L);
        ids.add(5L);
        ids.add(6L);

        //第二个参数是个集合对象即可，注意集合对象这里对应的类型虽然是Object，不过要和你数据库对应的类型保持一致
        criteria.andIn("id",ids);

        //执行查询
        List<Brand> brands = brandMapper.selectByExample(example);

        for (Brand brand : brands) {
            System.out.println(brand);
        }
    }
}
