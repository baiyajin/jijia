package com.baiyajin.service.pagedata;

import com.baiyajin.entity.pagedata.PageSubscription;
import com.baiyajin.vo.pagedata.Page;
import com.baiyajin.vo.pagedata.SubscriptionVo;
import com.baomidou.mybatisplus.service.IService;

public interface PageSubscriptionInterface extends IService<PageSubscription> {

    Page<SubscriptionVo> findList(Page<SubscriptionVo> page,SubscriptionVo subscriptionVo);
}
