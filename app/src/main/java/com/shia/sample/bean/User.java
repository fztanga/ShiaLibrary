package com.shia.sample.bean;

import android.databinding.Bindable;
import com.shia.sample.BR;
import org.parceler.Parcel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by administrator on 2017/3/31. 1、市局级领导：登录系统  完善个人信息、关联企业信息 
 * 上报企业问题（输入企业问题描述、选择问题解决方式、可选择图片、单选牵头单位、多选责任单位）  提交 2、市考评办：登录系统 
 * 多条件查询企业问题（行政区域、市直单位、领导姓名、企业名称）  查看问题详细信息、问题处理流程  编辑企业问题分类  通过或驳回问题 
 * 通过后进入到市考评单位处理（通过或驳回问题，企业，责任领导可接收相应提示消息）  可选择公开部分大众化问题 3、市考评单位：登录系统 
 * 查看由考评办审核通过后的企业问题  查看问题记录  处理问题（可上传附件）  处理完成（处理完成后的问题，企业，责任领导可接收相应提示消息）
 * 4、联系企业：登录系统  查看本企业问题处理进度  查看其它企业遇到问题
 */
@Parcel
public class User extends WHKPBResponse {
    String groupId;// 6-千名干部领导组（角色1） 2-考评办工作组 1-考评工作组 7-千名干部企业组
    String username;// 账号
    String status;
    String userId;
    String realname;
    String region;// 所属区域
    String mobile;// 手机
    String phone;// 固定电话
    String intro;// 职务
    String orgname;// 部门

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Bindable
    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
        notifyPropertyChanged(BR.realname);
    }

    @Bindable
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
        notifyPropertyChanged(BR.intro);
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("realname", notNull(realname));
        params.put("region", notNull(region));
        params.put("mobile", notNull(mobile));
        params.put("phone", notNull(phone));
        params.put("intro", notNull(intro));

        return params;
    }

    private String notNull(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }
}
