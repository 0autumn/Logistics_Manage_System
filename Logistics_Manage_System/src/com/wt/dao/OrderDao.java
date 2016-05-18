package com.wt.dao;

import java.util.List;

import org.hibernate.Query;

import com.wt.entity.Book;
import com.wt.entity.Order;

public class OrderDao extends BaseDao{
	
	// ���µĲ���Ϊ��ȡ�������
	public Book findBook(String bookname) {
		Book book = null;
		
		String hql = "FROM Book b LEFT OUTER JOIN FETCH b.orders WHERE b.book_name = ?";
		
		Query query = getSession().createQuery(hql);
		
		query.setString(0, bookname);
		
		book = (Book) query.uniqueResult();
		
		return book;
	}
	
	// ���µĲ���Ϊ��ȡ�ֿ��е���
	public Book findWarehouseBook(String bookname) {
		Book book = null;
		
		String hql = "FROM Warehouse w WHERE w.book_name = ?";
		
		Query query = getSession().createQuery(hql);
		
		query.setString(0, bookname);
		
		book = (Book) query.uniqueResult();
		
		return book;
	}
	
	// ����Ϊ��ѯĳ���û��Ķ����б�
	@SuppressWarnings("unchecked")
	public List<Order> findOrder(String userName) {
		List<Order> orders = null;
		
		String hql = "FROM Order o LEFT OUTER JOIN FETCH o.books WHERE o.user_name = ?";
		
		Query query = getSession().createQuery(hql);

		orders = query.setString(0, userName).list();
		
		return orders;
	}
	
	public void saveOrUpdateOrder(Order order){
		getSession().saveOrUpdate(order);
	}
	
	public void saveOrUpdateBook(Book book){
		getSession().saveOrUpdate(book);
	}
	
}
