package com.base.example.primary.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.example.primary.entity.AclTask;
import com.base.example.primary.mapper.AclTaskMapper;
import com.base.example.primary.service.AclTaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: --
 * @author：Bing
 * @date：2022/8/23 8:31
 * @version：1.0
 */
@Service
public class AclTaskServiceImpl extends ServiceImpl<AclTaskMapper, AclTask> implements AclTaskService {
    @Resource
    private AclTaskMapper aclTaskMapper;

    @Override
    public IPage<AclTask> findBypage(AclTask aclTask) {
        IPage<AclTask> iPage = new Page<>(aclTask.getCurrentPage(), aclTask.getPageSize());
        return this.page(iPage, new QueryWrapper<AclTask>().orderByDesc("create_time"));
    }
}
