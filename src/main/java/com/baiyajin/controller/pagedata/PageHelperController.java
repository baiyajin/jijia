package com.baiyajin.controller.pagedata;

import com.baiyajin.entity.pagedata.PageHelper;
import com.baiyajin.service.pagedata.PageHelperInterface;
import com.baiyajin.util.PageUtils;
import com.baiyajin.util.Results;
import com.baiyajin.vo.pagedata.HelperVo;
import com.baiyajin.vo.pagedata.Page;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baiyajin.util.IdGenerate;

import java.sql.Timestamp;

@Api("帮助")
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
    @ApiOperation(value = "帮助中心文章增加" ,notes = "publishState默认为1,0代表已发布，1代表未发布，传入0以后自动生成发布时间")
    @ApiImplicitParams({@ApiImplicitParam(name = "title（必填）,content(非必填),publishState(非必填)",value =  "title:123465,content:789463,publishState:0",dataType = "String",paramType = "body")})
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Object addHelper(PageHelper pageHelper){

        String publishState =((pageHelper == null ||StringUtils.isBlank(pageHelper.getPublishState())) ? "1":pageHelper.getPublishState()) ;
        if ("0".equals(publishState)){
            pageHelper.setPublishTime(new Timestamp(System.currentTimeMillis()));
        }
        pageHelper.setPublishState(publishState);
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
    @ApiOperation(value = "帮助中心文章删除" ,notes = "逻辑删除，statusId值为jy代表已删除，数据库依然存在，但是页面不显示")
    @ApiImplicitParams({@ApiImplicitParam(name = "id（必填)",value =  "id:123465",dataType = "String",paramType = "body")})
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Object delete(String id){
        PageHelper pageHelper = pageHelperInterface.selectById(id);
        if (pageHelper == null){
            return new Results(1,"该文章不存在");
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
    @ApiOperation(value = "帮助中心文章修改" ,notes = "需要修改什么那些字段就传入那些字段")
    @ApiImplicitParams({@ApiImplicitParam(name = "id（必填),title(非必填),content(非必填),publishState(非必填)",value =  "id:123465",dataType = "String",paramType = "body")})
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Object update(PageHelper pageHelper){
        if (pageHelper != null){
            if ("0".equals(pageHelper.getPublishState())){
                pageHelper.setPublishTime(new Timestamp(System.currentTimeMillis()));
            }
        }


        pageHelper.setUpdateTime(new Timestamp(System.currentTimeMillis()));
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
    @ApiOperation(value = "帮助中心文章发布" ,notes = "publishState，0代表已发布，1代表未发布，默认新增文章时为未发布")
    @ApiImplicitParams({@ApiImplicitParam(name = "id（必填)",value =  "id:123465",dataType = "String",paramType = "body")})
    @RequestMapping(value = "/publish",method = RequestMethod.POST)
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
    @ApiOperation(value = "查询帮助中心文章列表" ,notes = "分页查询，未传入pageNum和pageSize默认从第1页查，每页十条数据,num为非必填，填入以后只查询该编号的文章，num为数字")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum（非必填),pageSize(非必填)",value =  "pageNum:1,pageNum:5",dataType = "String",paramType = "body")})
    @RequestMapping(value = "/findHelperByPage",method = RequestMethod.POST)
    @ResponseBody
    public Object findHelperByPage(HelperVo helperVo,String pageNum,String pageSize){
        Page<HelperVo> p = new Page();
        if (StringUtils.isNotBlank(pageNum)&&StringUtils.isNotBlank(pageSize)){
            if ("0".equals(pageNum)){
                pageNum = "1";
            }
            helperVo.setPageCurrent(PageUtils.pageNoRecord(pageNum,pageSize));
            helperVo.setPSize(Integer.valueOf(pageSize));
            p.setPageSize(Integer.valueOf(pageSize));
            p.setPageNo(Integer.valueOf(pageNum));
        }else {
            helperVo.setPSize(10);
            helperVo.setPageCurrent(PageUtils.pageNoRecord("1","10"));
            p.setPageSize(10);
            p.setPageNo(1);
        }
        PageHelper pageHelper = new PageHelper();
        Integer code  = helperVo.getCode();
        if (code != null){
            pageHelper.setArtCode(code);
        }
        String publishState = helperVo.getPublishState();
        if (StringUtils.isBlank(publishState)){
            pageHelper.setPublishState("0");
            helperVo.setPublishState("0");
        }
        pageHelper.setStatusID("qy");
        int count = pageHelperInterface.selectCount(new EntityWrapper<>(pageHelper));
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
    @ApiOperation(value = "查询文章详情" ,notes = "ID查询，只要ID能获取到就能查到文章，无论是否被删除")
    @ApiImplicitParams({@ApiImplicitParam(name = "id（必填)",value =  "id:123465",dataType = "String",paramType = "body")})
    @RequestMapping(value = "/getArtInfo",method = RequestMethod.POST)
    @ResponseBody
    public Object getArtInfo(String id){
        PageHelper pageHelper = pageHelperInterface.selectById(id);
        if (pageHelper !=null){
            pageHelper.setContent(StringEscapeUtils.escapeHtml(pageHelper.getContent()));
        }else {
            return new Results(1,"没有该文章");
        }
        return pageHelper;
    }

}
