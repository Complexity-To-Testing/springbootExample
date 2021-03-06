package com.scrapy.service;
import com.common.dao.entity.User;
import com.common.dao.entity.entityJsonBean.SessionUserJsonBean;
import com.scrapy.common.APIResponse;

import java.util.Map;

public interface UserService {
    APIResponse deleteByPrimaryKey(User record);

    APIResponse insert(User record);

    APIResponse selectByPrimaryKey(User record);

    APIResponse updateByPrimaryKeySelective(User record);

    APIResponse updateByPrimaryKey(User record);

    APIResponse selectList(User record);

    APIResponse login(User user);

    void logout(SessionUserJsonBean sessionUserJsonBean);

    APIResponse selectListByFilter(Map<String, String> record);
}