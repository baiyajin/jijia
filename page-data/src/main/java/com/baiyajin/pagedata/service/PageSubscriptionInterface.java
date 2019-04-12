package com.baiyajin.pagedata.service;

import com.baiyajin.pagedata.entity.PageSubscription;
import com.baiyajin.pagedata.vo.Page;
import com.baiyajin.pagedata.vo.SubscriptionVo;
import com.baomidou.mybatisplus.service.IService;

public interface PageSubscriptionInterface extends IService<PageSubscription> {

    Page<SubscriptionVo> findList(Page<SubscriptionVo> page,SubscriptionVo subscriptionVo);
}
