package com.fish.controller;

import com.fish.controller.viewobject.ItemVO;
import com.fish.error.BusinessException;
import com.fish.response.CommonReturnType;
import com.fish.service.ItemService;
import com.fish.service.model.ItemModel;
import com.sun.tools.javac.util.List;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/item")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class ItemController extends BaseController{

    @Autowired
    private ItemService itemService;

    //创建商品的controller
    @RequestMapping(value = "/create", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType createItem(@RequestParam(name = "title")String title,
                                       @RequestParam(name = "description")String description,
                                       @RequestParam(name = "price")BigDecimal price,
                                       @RequestParam(name = "stock")Integer stock,
                                       @RequestParam(name = "imgUrl")String imgUrl) throws BusinessException {
        //封装service请求用来创建商品
        ItemModel itemModel = new ItemModel();
        itemModel.setStock(stock);
        itemModel.setTitle(title);
        itemModel.setDescription(description);
        itemModel.setImgUrl(imgUrl);
        itemModel.setPrice(price);

        ItemModel result = itemService.createItem(itemModel);
        ItemVO itemVO = convertVOFromItemModel(result);

        return CommonReturnType.create(itemVO);
    }

    //商品详情页浏览
    @RequestMapping(value = "/get", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getItem(@RequestParam(name = "id")Integer id) {
        ItemModel itemModel = itemService.getItemById(id);
        ItemVO itemVO = convertVOFromItemModel(itemModel);

        CommonReturnType type = CommonReturnType.create(itemVO);
        return type;
    }

    //商品列表页面浏览
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType listItem() {
        java.util.List<ItemModel> itemModelList = itemService.listItem();

        //使用stream api将list内的itemModel转换为itemVO
        java.util.List<ItemVO> itemVOList = itemModelList.stream().map(itemModel -> {
            ItemVO itemVO = this.convertVOFromItemModel(itemModel);
            return itemVO;
        }).collect(Collectors.toList());

        return CommonReturnType .create(itemVOList);
    }

    //converter
    private ItemVO convertVOFromItemModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemModel, itemVO);
        if (itemModel.getPromoModel() != null) {
            //有正在进行或即将开始的秒杀活动
            itemVO.setPromoStatus(itemModel.getPromoModel().getStatus());
            itemVO.setPromoId(itemModel.getPromoModel().getId());
            itemVO.setPromoPrice(itemModel.getPromoModel().getPromoItemPrice());
            itemVO.setStartDate(itemModel.getPromoModel().getStartDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
        }else {
            itemVO.setPromoStatus(0);
        }
        return itemVO;
    }
}
