package com.baiyajin.mapper.pagedata;

import com.baiyajin.entity.pagedata.PageSubscription;
import com.baiyajin.vo.pagedata.SubscriptionVo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

public interface PageSubscriptionMapper extends BaseMapper<PageSubscription> {

    List<SubscriptionVo> findList(SubscriptionVo subscriptionVo);
}
