package com.zjtec.travel.controller;

import com.zjtec.travel.domain.*;
import com.zjtec.travel.service.RouteService;
import com.zjtec.travel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/route")
public class RouteController {

  @Autowired
  private RouteService routeService;


  @RequestMapping("/detail")
  @ResponseBody
  public Detail detail(@RequestParam("rid") int rid){
    Detail data=routeService.findSeller(rid);
    System.out.println(data);
    List<RouteImg> img=routeService.findImg(rid);
    data.setRouteImgList(img);
    return data;

  }




  @RequestMapping("/pageQuery")
  @ResponseBody
  public PageBean<Route> pageQuery(@RequestParam("cid") int cid,@RequestParam(value="pageSize",required = false) Integer pageSize,@RequestParam(value="currentPage",required = false) Integer currentPage){
    //TODO:完成pageQuery 功能*                            ////int

    if(currentPage==null){
      return routeService.pageQuery(cid,1,5);
    }else

    return routeService.pageQuery(cid,currentPage,5);
  }



}
