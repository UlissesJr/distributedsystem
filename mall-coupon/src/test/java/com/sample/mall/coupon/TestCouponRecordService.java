package com.sample.mall.coupon;

import com.sample.mall.common.dto.CouponRecordDTO;
import com.sample.mall.coupon.service.ICouponRecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * Created by LuoboGan
 * Date:2023/3/16
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestCouponRecordService {

    private ICouponRecordService couponRecordService;

    private static final int concurrency = 100;
    private CountDownLatch countDownLatch = new CountDownLatch(concurrency);

    @Test
    public void testReceiveCoupon(){
        for(int i = 0; i < concurrency ; i++ ){
            long userID = i;
            new Thread(() -> {
                        countDownLatch.countDown();
                        try{
                            countDownLatch.await();
                            CouponRecordDTO couponRecordDTO = this.initCouponRecordDTO(userID);
                            couponRecordService.receiveCoupon(couponRecordDTO);
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
            ).start();
        }
    }

    private CouponRecordDTO initCouponRecordDTO(Long userId){
        CouponRecordDTO couponRecordDTO = new CouponRecordDTO();
        couponRecordDTO.setCouponId(1L);
        couponRecordDTO.setUserId(userId);
        return couponRecordDTO;
    }

}
