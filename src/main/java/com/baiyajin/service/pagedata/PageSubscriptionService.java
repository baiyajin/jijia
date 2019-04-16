package com.baiyajin.service.pagedata;

import com.baiyajin.entity.pagedata.PageSubscription;
import com.baiyajin.mapper.pagedata.PageSubscriptionMapper;
import com.baiyajin.vo.pagedata.Page;
import com.baiyajin.vo.pagedata.SubscriptionVo;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageSubscriptionService extends ServiceImpl<PageSubscriptionMapper,PageSubscription> implements PageSubscriptionInterface {


    @Override
    public Page<SubscriptionVo> findList(Page<SubscriptionVo> page, SubscriptionVo subscriptionVo) {
        List<SubscriptionVo> subscriptionVoList = baseMapper.findList(subscriptionVo);
        // 设置分页参数
        subscriptionVo.setPage(page);
        // 执行分页查询
        page.setList(subscriptionVoList);
        return page;
    }
}
