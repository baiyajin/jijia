package com.baiyajin.controller.pagedata;

import com.baiyajin.entity.pagedata.PageHelper;
import com.baiyajin.service.pagedata.PageHelperInterface;
import com.baiyajin.util.Results;
import com.baiyajin.vo.pagedata.HelperVo;
import com.baiyajin.vo.pagedata.Page;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baiyajin.util.IdGenerate;

import java.sql.Timestamp;

@Controller
@RequestMapping(value = "/pageHelperController")
public class PageHelperController {
    @Autowired
    private PageHelperInterface pageHelperInterface;


    /**
     * 新增帮助文章
     * @param pageHelper
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Object addHelper(PageHelper pageHelper){
        pageHelper.setId(IdGenerate.uuid());
        pageHelper.setStatusID("qy");
        pageHelper.setCreateTime(new Timestamp(System.currentTimeMillis()));
        pageHelper.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        try {
            pageHelperInterface.insert(pageHelper);
        } catch (Exception e) {
            e.printStackTrace();
            return new Results(1,"fail");
        }
        return new Results(0,"success");
    };

    /**
     * 删除文章
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.PUT)
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Object delete(String id){
        PageHelper pageHelper = pageHelperInterface.selectById(id);
        if (pageHelper == null){
            return new Results(1,"无法找到该文章");
        }
        pageHelper.setStatusID("jy");
        try {
            pageHelperInterface.updateById(pageHelper);
        } catch (Exception e) {
            e.printStackTrace();
            return new Results(1,"fail");
        }
            return new Results(0,"success");
    };

    /**
     * 修改文章
     * @param pageHelper
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Object update(PageHelper pageHelper){
        try {
            pageHelperInterface.updateById(pageHelper);
        } catch (Exception e) {
            e.printStackTrace();
            return new Results(1,"fail");
        }
        return new Results(0,"success");
    };

    /**
     * 发布文章
     * @param id
     * @return
     */
    @RequestMapping(value = "/publish",method = RequestMethod.PUT)
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Object publish(String id){
        PageHelper pageHelper = pageHelperInterface.selectById(id);
        if (pageHelper == null){
            return new Results(1,"无法找到该文章");
        }
        pageHelper.setPublishState("0");
        pageHelper.setPublishTime(new Timestamp(System.currentTimeMillis()));
        try {
            pageHelperInterface.updateById(pageHelper);
        } catch (Exception e) {
            e.printStackTrace();
            return new Results(1,"fail");
        }
        return new Results(0,"success");
    };


    /**
     * 分页查询帮助文章
     * @param helperVo
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/findHelperByPage",method = RequestMethod.POST)
    @ResponseBody
    public Object findHelperByPage(HelperVo helperVo,String pageNum,String pageSize){
        Page<HelperVo> p = new Page();
        if (StringUtils.isNotBlank(pageNum)&&StringUtils.isNotBlank(pageSize)){
            p.setPageNo(Integer.valueOf(pageNum));
            p.setPageSize(Integer.valueOf(pageSize));
        }else {
            p.setPageSize(10);
            p.setPageNo(0);
        }
        int count = pageHelperInterface.selectCount(new EntityWrapper<>());
        Page<HelperVo> page = pageHelperInterface.findList(p,helperVo);
        if (page == null || page.getList() == null ||page.getList().size() == 0){
            return new Results(1,"暂无数据");
        }
        page.setCount(count);
        return page;
    };

    /**
     * 查询文章详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/getArtInfo",method = RequestMethod.GET)
    @ResponseBody
    public Object getArtInfo(String id){
        PageHelper pageHelper = pageHelperInterface.selectById(id);
        pageHelper.setContent(StringEscapeUtils.escapeHtml(pageHelper.getContent()));
        if (pageHelper == null){
            return new Results(1,"没有该文章");
        }
        return pageHelper;
    }

}
