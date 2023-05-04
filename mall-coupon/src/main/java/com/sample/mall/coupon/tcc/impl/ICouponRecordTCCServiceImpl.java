package com.sample.mall.coupon.tcc.impl;

import com.sample.mall.common.dto.CouponRecordDTO;
import com.sample.mall.common.util.Assert;
import com.sample.mall.common.util.ObjectTransformer;
import com.sample.mall.coupon.mapper.CouponRecordMapper;
import com.sample.mall.coupon.model.CouponRecordDO;
import com.sample.mall.coupon.service.ICouponRecordService;
import com.sample.mall.coupon.tcc.ICouponRecordTCCService;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 该场景下 优惠券状态为 0-未使用 1-已冻结 2-已使用
 * Created by LuoboGan
 * Date:2023-03-30
 */
@Service
public class ICouponRecordTCCServiceImpl implements ICouponRecordTCCService {

    @Resource
    CouponRecordMapper couponRecordMapper;

    @Resource
    ICouponRecordService iCouponRecordService;

    @Override
    public boolean freezeCoupon(BusinessActionContext context, CouponRecordDTO couponRecordDTO) {
        CouponRecordDO couponRecordDO = ObjectTransformer.transform(couponRecordDTO, CouponRecordDO.class);
        couponRecordDO = couponRecordMapper.selectCouponRecord(couponRecordDO);
//        iCouponRecordService.checkCouponRecordStatus(couponRecordDO);
        // 冻结状态
        couponRecordDO.setStatus(1);
        int result = couponRecordMapper.updateCouponRecordStatus(couponRecordDO);
        return Assert.singleRowAffected(result);
    }

    @Override
    public boolean deductCoupon(BusinessActionContext context) {
        // 扣减优惠券，状态为已使用
        return false;
    }

    @Override
    public boolean unfreezeCoupon(BusinessActionContext context) {
        // 解冻优惠券，状态为未使用
        return false;
    }
}

