package com.fish.service.model;

import com.fish.dao.PromoDOMapper;
import com.fish.dataobject.PromoDO;
import com.fish.service.PromoService;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PromoServiceImpl implements PromoService {

    @Autowired
    private PromoDOMapper promoDOMapper;

    @Override
    public PromoModel getPromoByItemId(Integer itemId) {
        //获取对应商品的秒杀信息
        PromoDO promoDO = promoDOMapper.selectByItemId(itemId);

        //dataobject -> model
        PromoModel promoModel = convertFromDataObject(promoDO);
        if (promoModel == null) {
            return null;
        }

        //判断当前时间是否秒杀活动即将开始或正在进行
        DateTime now = new DateTime();
        if (promoModel.getStartDate().isAfter(now)) {
            promoModel.setStatus(1);
        }else if(promoModel.getEndDate().isBefore(now)) {
            promoModel.setStatus(3);
        }else {
            promoModel.setStatus(2);
        }

        return promoModel;
    }

    //converter
    private PromoModel convertFromDataObject(PromoDO promoDO) {
        if (promoDO == null) {
            return null;
        }
        PromoModel promoModel = new PromoModel();
        BeanUtils.copyProperties(promoDO, promoModel);
        promoModel.setPromoItemPrice(new BigDecimal(promoDO.getPromoItemPrice()));
        promoModel.setStartDate(new DateTime(promoDO.getStartTime()));
        promoModel.setEndDate(new DateTime(promoDO.getEndTime()));

        return promoModel;
    }
}
