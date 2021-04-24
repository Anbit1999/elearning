package com.myclass.kat.elearning.transfer;

public interface BaseTransfer<E,D> {
	D entityToDto(E entity);
	E dtoToEntity(D dto);
}
