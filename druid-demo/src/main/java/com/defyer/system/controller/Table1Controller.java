package com.defyer.system.controller;


import com.defyer.system.entity.Table1;
import com.defyer.system.mapper.Table1Mapper;
import com.defyer.system.service.impl.Table1ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author defyer
 * @since 2021-11-13
 */
@RestController
@RequestMapping("/system/table1")
public class Table1Controller {

    @Autowired
    private Table1ServiceImpl table1Service;

    @GetMapping
    public Table1 index(){
        Table1 table1=table1Service.getById(7);
        return  table1;
    }


}
