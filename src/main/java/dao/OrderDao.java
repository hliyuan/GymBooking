package dao;

import pojo.Order;

import java.util.Collection;


/**
 * Created by liyuan on 2017/9/10.
 */
public interface OrderDao {

    void insertOrder(Order o) throws Exception;

    void deleteOrder(Order o) throws Exception;

    int alreadyHasIndex (Order o) throws Exception;

    Collection<Order> selectAllOrder(String areaName) throws Exception;

}