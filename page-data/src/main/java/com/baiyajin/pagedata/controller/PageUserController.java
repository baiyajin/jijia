package com.baiyajin.pagedata.controller;

import com.baiyajin.pagedata.entity.PageUser;
import com.baiyajin.pagedata.service.PageUserInterface;
import com.baiyajin.pagedata.util.HashSalt;
import com.baiyajin.pagedata.util.PhoneUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/PageUserController")
public class PageUserController {

    @Autowired
    private PageUserInterface pageUserInterface;










}



