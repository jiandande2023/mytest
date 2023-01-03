package com.pinyougou.sellergoods.service;

import com.github.pagehelper.PageInfo;
import com.pinyougou.model.Specification;

import java.util.List;

/**
 * @author atom.hu
 * @version V1.0
 * @Package com.pinyougou.sellergoods.service
 * @date 2023/1/1 22:40
 */
public interface SpecificationService {
    PageInfo<Specification> list(Integer pageNum, Integer pageSize, Specification specification);

    int add(Specification specification);

    Specification getById(Long id);

    int update(Specification specification);

    int delete(List<Long> ids);
}
