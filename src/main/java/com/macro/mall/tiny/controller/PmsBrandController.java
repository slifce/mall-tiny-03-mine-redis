package com.macro.mall.tiny.controller;

import com.macro.mall.tiny.annotation.CheckSubmitRepeat;
import com.macro.mall.tiny.common.api.CommonPage;
import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.mbg.model.PmsBrand;
import com.macro.mall.tiny.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * 品牌管理Controller
 */
@Api(tags = "PmsBrandController", description = "商品品牌管理")
@Controller
@RequestMapping("/brand")
public class PmsBrandController {
    @Autowired
    private PmsBrandService demoService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    @ApiOperation("模拟秒杀场景")
    @RequestMapping(value = "secondSkill", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult secondSkill() {
        String key = "stock";
        synchronized (this){
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get(key));
            if (stock > 0){
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set(key,realStock+"");
                System.out.println("扣减库存成功，当前库存："+realStock);
            } else {
                System.out.println("当前库存不足！");
            }
        }
        //String(常见的k-v的String类型存值)
        String currentTime = System.currentTimeMillis()+"";
        stringRedisTemplate.opsForValue().set(currentTime,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        String dateTime = stringRedisTemplate.opsForValue().get(currentTime);
        System.out.println("通过get(K key)方法获取变量中的元素值:" + dateTime);

        //list(rightPush向list尾部插如数据，leftPush向list头部插入数据)
        stringRedisTemplate.opsForList().rightPush("userList","zhangsan");
        stringRedisTemplate.opsForList().rightPush("userList","lisi");
        stringRedisTemplate.opsForList().rightPush("userList","wangwu");
        stringRedisTemplate.opsForList().leftPush("userList","zhaoliu");
        List<String> userList = stringRedisTemplate.opsForList().range("userList", 0, -1);
        for(String user : userList){
            System.out.println(user);
            //zhaoliu   zhangsan    lisi    wangwu
        }

        //hash
        stringRedisTemplate.opsForHash().put("hashValue","map1","map1-1");
        stringRedisTemplate.opsForHash().put("hashValue","map2","map2-2");
        Object mapValue = stringRedisTemplate.opsForHash().get("hashValue","map1");
        System.out.println("通过get(H key, Object hashKey)方法获取map键的值:" + mapValue);
        List<Object> hashList = stringRedisTemplate.opsForHash().values("hashValue");
        System.out.println("通过values(H key)方法获取变量中的hashMap值:" + hashList);

        //set
        stringRedisTemplate.opsForSet().add("setValue","A","B","C","B","D","E","F");
        Set set = stringRedisTemplate.opsForSet().members("setValue");
        System.out.println("通过members(K key)方法获取变量中的元素值:" + set);
        long setLength = stringRedisTemplate.opsForSet().size("setValue");
        System.out.println("通过size(K key)方法获取变量中元素值的长度:" + setLength);
        Object randomMember = stringRedisTemplate.opsForSet().randomMember("setValue");
        System.out.println("通过randomMember(K key)方法随机获取变量中的元素:" + randomMember);

        //zset
        stringRedisTemplate.opsForZSet().add("zSetValue","A",1);
        stringRedisTemplate.opsForZSet().add("zSetValue","B",3);
        stringRedisTemplate.opsForZSet().add("zSetValue","C",2);
        stringRedisTemplate.opsForZSet().add("zSetValue","D",5);
        Set zSetValue = stringRedisTemplate.opsForZSet().range("zSetValue",0,-1);
        System.out.println("通过range(K key, long start, long end)方法获取指定区间的元素:" + zSetValue);
        zSetValue = stringRedisTemplate.opsForZSet().rangeByScore("zSetValue",1,2);
        System.out.println("通过rangeByScore(K key, double min, double max)方法根据设置的score获取区间值:" + zSetValue);

        return CommonResult.success("success");
    }

    @ApiOperation("获取所有品牌列表")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:brand:read')")
    public CommonResult<List<PmsBrand>> getBrandList() {
        return CommonResult.success(demoService.listAllBrand());
    }

    @ApiOperation("添加品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand) {
        CommonResult commonResult;
        int count = demoService.createBrand(pmsBrand);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrand);
            LOGGER.debug("createBrand success:{}", pmsBrand);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createBrand failed:{}", pmsBrand);
        }
        return commonResult;
    }

    @ApiOperation("更新指定id品牌信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrandDto, BindingResult result) {
        CommonResult commonResult;
        int count = demoService.updateBrand(id, pmsBrandDto);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrandDto);
            LOGGER.debug("updateBrand success:{}", pmsBrandDto);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("updateBrand failed:{}", pmsBrandDto);
        }
        return commonResult;
    }

    @ApiOperation("删除指定id的品牌")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteBrand(@PathVariable("id") Long id) {
        int count = demoService.deleteBrand(id);
        if (count == 1) {
            LOGGER.debug("deleteBrand success :id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("deleteBrand failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }

    @CheckSubmitRepeat
    @ApiOperation("分页查询品牌列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        List<PmsBrand> brandList = demoService.listBrand(pageNum, pageSize);
        LOGGER.debug("brandList success :size={}", brandList.size());
        return CommonResult.success(CommonPage.restPage(brandList));
    }

    @ApiOperation("获取指定id的品牌详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsBrand> brand(@PathVariable("id") Long id) {
        return CommonResult.success(demoService.getBrand(id));
    }
}
