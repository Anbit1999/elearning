package com.myclass.kat.elearning.service;

import java.util.List;

public interface BaseService<T,K> {

	List<T> findAll();
	T finById(K id);
	boolean insert(T dto);
	boolean update(K id, T dto);
	boolean delete(K id);
}
