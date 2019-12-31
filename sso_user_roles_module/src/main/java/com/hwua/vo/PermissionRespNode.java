package com.hwua.vo;


import lombok.Data;

import java.util.List;


@Data
public class PermissionRespNode {
   //"id"
    private String id;
   //菜单权限名称"
    private String title;

    //菜单权限标识，shiro 适配restful"
    private String perms;

    //接口地址
    private String url;

    //请求方式 和url 配合使用 (我们用 路径匹配的方式做权限管理的时候用到
    private String method;

    //父级id
    private String pid;

    //父级名称
    private String pidName;

    //菜单权限类型(1:目录;2:菜单;3:按钮)")
    private Integer type;

    //编码(前后端分离 前段对按钮显示隐藏控制 btn-permission-search 代表 菜单权限管理的列表查询按钮)")
    private String code;

    //"排序码")
    private Integer orderNum;

   //"是否展开 默认不展开(false)")
    private boolean spread=true;

   //"是否选中 默认false")
    private boolean checked;
    private List<?> children;


}
