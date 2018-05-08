package com.example.base.dao;

import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Description: DAO基类,其它DAO可以直接继承这个DAO,不但可以复用共用的方法,还可以获得[泛型]的好处。
 *
 * @author yangzhaoyunfei yangzhaoyunfei@qq.com
 * @date 2018/5/8
 */
@SuppressWarnings({"ALL", "AlibabaClassMustHaveAuthor"})
@Repository
public interface BaseMapper<T> {

    int deleteByPrimaryKey(Integer id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    /**
     * 列表页,ajax 分页查询
     *
     * @param map 用来存放 xxxMapper.xml 中 sql 语句需要用到的参数,如Integer id,String name,List list,等
     * @return 封装后的列表页对象，是一个容器,里面盛放了多个record对象,record总数,加载到列表页的表格中
     */
    Page<T> selectPageList(Map map);
}