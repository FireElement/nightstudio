package org.nightstudio.common.source.spi;

import org.nightstudio.common.util.exception.sys.NSException;

import java.io.Serializable;
import java.util.List;

public interface IDao<T> {
	public Serializable save(T entity) throws NSException;
	public void deleteOne(Serializable id) throws NSException;
	public void deleteOneByExample(T entity) throws NSException;
	public T update(T entity) throws NSException;
	public List<T> getAll() throws NSException;
	public List<T> getSomeByExample(T exampleEntity) throws NSException;
	public T getOne(Serializable id) throws NSException;
	public T getOneByExample(final T exampleEntity) throws NSException;
}
