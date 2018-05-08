package com.example.base.service;

import com.example.base.domain.User;
import com.github.pagehelper.Page;

import java.util.Map;

/**
 * Description: 入住人【业务逻辑接口】
 *
 * @author tangzhongwei tangzw@zjbdos.com
 * @date 2018/4/29
 */
public interface BaseService<T> {


    /**
     * 插入一条记录
     *
     * @param map  用来存放 xxxMapper.xml 中 sql 语句需要用到的参数,如Integer id,String name,List list,等
     * @param user 操作人
     * @return 该次操作影响的数据库记录数
     */
    int insertSelective(Map map, User user);

    /**
     * 查询一条记录
     *
     * @param id 要查询的记录的 id
     * @return 查询出来的记录/对象
     */
    T selectByPrimaryKey(Integer id);

    /**
     * 更新一条记录
     *
     * @param map  用来存放 xxxMapper.xml 中 sql 语句需要用到的参数,如Integer id,String name,List list,等
     * @param user 操作人
     * @return 该次操作影响的数据库记录数
     */
    int updateByPrimaryKeySelective(Map map, User user);

    /**
     * 删除一条记录,逻辑删除
     *
     * @param id   要删除记录的 id
     * @param user 操作人
     * @return 该次操作影响的数据库记录数
     */
    int deleteByPrimaryKey(Integer id, User user);


    /**
     * 列表页,ajax 分页查询
     *
     * @param map 用来存放 xxxMapper.xml 中 sql 语句需要用到的参数,如Integer id,String name,List list,等
     * @return 封装后的列表页对象，是一个容器,里面盛放了多个record对象,record总数,加载到列表页的表格中
     */
    Page<T> selectPageList(Map map);

}
