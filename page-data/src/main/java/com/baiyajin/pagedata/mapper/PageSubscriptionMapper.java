package com.baiyajin.pagedata.mapper;

import com.baiyajin.pagedata.entity.PageSubscription;
import com.baiyajin.pagedata.vo.SubscriptionVo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

public interface PageSubscriptionMapper extends BaseMapper<PageSubscription> {

    List<SubscriptionVo> findList(SubscriptionVo subscriptionVo);
}
