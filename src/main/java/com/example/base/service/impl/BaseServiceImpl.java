package com.example.base.service.impl;

import com.example.base.dao.BaseMapper;
import com.example.base.domain.User;
import com.example.base.service.BaseService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Description: 【业务逻辑实现类】基类
 *
 * @author tangzhongwei tangzw@zjbdos.com
 * @date 2018/4/29
 */
@Service
public class BaseServiceImpl<T> implements BaseService<T> {

    private BaseMapper mapper;

    /**
     * 基于构造函数的自动注入(自动注入有:字段注入,构造函数注入,设值函数注入)
     *
     * @param mapper
     */
    @Autowired
    public BaseServiceImpl(BaseMapper mapper) {
        this.mapper = mapper;
    }


    /**
     * @param map 用来存放 xxxMapper.xml 中 sql 语句需要用到的参数,如Integer id,String name,List list,等
     * @return
     */
    @Override
    public Page<T> selectPageList(Map map) {

        int offset = map.get("offset") != null ? (Integer.parseInt(map.get("offset").toString())) : 0;
        int pageSize = map.get("limit") != null ? Integer.parseInt(map.get("limit").toString()) : 10;

        PageHelper.offsetPage(offset, pageSize);

        return mapper.selectPageList(map);
    }


    @Override
    public int insertSelective(Map map, User user) {
        return 0;
    }

    @Override
    public T selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Map map, User user) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id, User user) {
        return 0;
    }
}
