package org.nightstudio.common.util.lang;

import net.sf.cglib.beans.BeanCopier;
import org.apache.log4j.Logger;
import org.nightstudio.common.util.exception.sys.NSException;
import org.nightstudio.common.util.exception.sys.SystemInternalException;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * <p>Title: ObjectUtil</p>
 * <p>Description: 对象操作支持类，提供各种java reflect操作方法</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: SI-TECH </p>
 * @author CAOXZ
 * @version 1.0
 * @time 2010-2-4 下午01:22:15
 *
 */
public class ObjectUtil {
	private static Logger logger = Logger.getLogger(ObjectUtil.class);
	
	@SuppressWarnings("unchecked")
	public static List<Field> getAllFields(Class clazz) {
		List<Field> result = new ArrayList<Field>();
		if (clazz != null) {
			List<Class> classList = new LinkedList<Class>();
			classList.add(clazz);
			Class tmpClazz;
			while (classList.size() > 0) {
				tmpClazz = classList.remove(0);
				if (!Object.class.equals(tmpClazz)) {
					for (Field field : tmpClazz.getDeclaredFields()) {
						result.add(field);
					}
					if (tmpClazz.getSuperclass() != null && !Object.class.equals(tmpClazz.getSuperclass())) {
						classList.add(tmpClazz.getSuperclass());
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: getAllFields
	 * @Description: 获取对象的所有Field，因为用ObjectUtil的都是DAO类，所以只关心当前类和父类即可
	 * @param
	 * @return List<Field>
	 * @throws
	 * @author CAOXZ
	 * @version 1.0
	 * @time 2010-2-4 下午02:40:47
	 */
	public static List<Field> getAllFields(Object obj) {
		if (obj != null) {
			return getAllFields(obj.getClass());
		}
		return null;
	}
	
	/**
	 * 
	 * @Title: getFields
	 * @Description: 获取对象中指定类型的Field
	 * @param
	 * @return List<Field>
	 * @throws
	 * @author CAOXZ
	 * @version 1.0
	 * @time 2010-2-4 下午02:43:27
	 */
	@SuppressWarnings("unchecked")
	public static List<Field> getFields(Object obj, Class fieldType) {
		if (obj != null) {
			return getFields(obj.getClass(), fieldType);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Field> getFields(Class clazz, Class fieldType) {
		List<Field> fields = getAllFields(clazz);
		List<Field> result = new ArrayList<Field>(fields.size());
		if (fields != null && fieldType != null) {
			for (Field field : fields) {
				if (field.getType().equals(fieldType)) {
					result.add(field);
				}
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: getField
	 * @Description: 根据Field的名称获取对象中的Field
	 * @param
	 * @return Field
	 * @throws
	 * @author CAOXZ
	 * @version 1.0
	 * @time 2010-2-4 下午02:57:33
	 */
	public static Field getField(Object obj, String fieldName) {
		if (obj != null) {
			return getField(obj.getClass(), fieldName);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static Field getField(Class clazz, String fieldName) {
		List<Field> fields = getAllFields(clazz);
		if (fields != null) {
			for (Field field : fields) {
				if (field.getName().equals(fieldName)) {
					return field;
				}
			}
		}
		return null;
	}
	
	public static List<Field> getFields(Object obj, FieldTypeFilter filter) {
		if (obj != null) {
			return getFields(obj.getClass(), filter);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Field> getFields(Class clazz, FieldTypeFilter filter) {
		List<Field> fields = getAllFields(clazz);
		List<Field> result = new ArrayList<Field>(fields.size());
		if (fields != null) {
			for (Field field : fields) {
				if (filter.doFilter(field)) {
					result.add(field);
				}
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: setFieldValue
	 * @Description: 设置对象中Field的值
	 * @param
	 * @return void
	 * @throws
	 * @author CAOXZ
	 * @version 1.0
	 * @createtime 2010-4-9 下午03:51:36
	 */
	public static void setFieldValue(Object obj, String fieldName, Object value) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		ObjectUtil.setFieldValue(obj, ObjectUtil.getField(obj, fieldName), value);
	}
	
	/**
	 * 
	 * @Title: setFieldValue
	 * @Description: 设置对象中Field的值
	 * @param
	 * @return void
	 * @throws
	 * @author CAOXZ
	 * @version 1.0
	 * @time 2010-2-4 下午03:17:16
	 */
	public static void setFieldValue(Object obj, Field field, Object value) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		if (obj == null) {
			logger.error("obj is null");
			return;
		}
		if (field == null) {
			logger.error("field is null");
			return;
		}
		try {
			field.set(obj, value);
		} catch (IllegalAccessException e) {
			try {
				String methodName = getSetMethodName(field);
				if (methodName == null) {
					logger.error("method name is null");
					return;
				}
				Method m = obj.getClass().getMethod(methodName, field.getType());
				if (m == null) {
					logger.error("method is null");
					return;
				}
				m.invoke(obj, value);
			} catch (SecurityException e1) {
				logger.error("", e1);
				throw e1;
			} catch (NoSuchMethodException e1) {
				logger.error("", e1);
				throw e1;
			} catch (IllegalArgumentException e1) {
				logger.error("", e1);
				throw e1;
			} catch (IllegalAccessException e1) {
				logger.error("", e1);
				throw e1;
			} catch (InvocationTargetException e1) {
				logger.error("", e1);
				throw e1;
			}
		}
	}
	
	/**
	 * 
	 * @Title: getFieldValue
	 * @Description: 获取对象中名称为fieldName的Field的值
	 * @param
	 * @return Object
	 * @throws
	 * @author CAOXZ
	 * @version 1.0
	 * @time 2010-2-4 下午03:33:20
	 */
	public static Object getFieldValue(Object obj, String fieldName) throws IllegalArgumentException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		return ObjectUtil.getFieldValue(obj, ObjectUtil.getField(obj, fieldName));
	}
	
	/**
	 * 
	 * @Title: getFieldValue
	 * @Description: 获取对象中Field的值
	 * @param
	 * @return Object
	 * @throws
	 * @author CAOXZ
	 * @version 1.0
	 * @time 2010-2-4 下午03:35:35
	 */
	public static Object getFieldValue(Object obj, Field field) throws IllegalArgumentException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		if (obj == null) {
			logger.error("obj is null");
			return null;
		}
		if (field == null) {
			logger.error("field is null");
			return null;
		}
		try {
			return field.get(obj);
		} catch (IllegalAccessException e) {
			try {
				String methodName = getGetMethodName(field);
				if (methodName == null) {
					logger.error("method name is null");
					return null;
				}
				Method m = obj.getClass().getMethod(methodName);
				if (m == null) {
					logger.error("method is null");
					return null;
				}
				return m.invoke(obj);
			} catch (SecurityException e1) {
				logger.error("", e1);
				throw e1;
			} catch (NoSuchMethodException e1) {
				logger.error("", e1);
				throw e1;
			} catch (InvocationTargetException e1) {
				logger.error("", e1);
				throw e1;
			} catch (IllegalAccessException e1) {
				logger.error("", e1);
				throw e1;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Object getFieldValue(Class clazz, String fieldName) throws IllegalArgumentException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		return ObjectUtil.getFieldValue(clazz, ObjectUtil.getField(clazz, fieldName));
	}
	
	@SuppressWarnings("unchecked")
	public static Object getFieldValue(Class clazz, Field field) throws IllegalArgumentException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		if (field == null) {
			logger.error("field is null");
			return null;
		}
		try {
			return field.get(null);
		} catch (IllegalAccessException e) {
			try {
				String methodName = getGetMethodName(field);
				if (methodName == null) {
					logger.error("method name is null");
					return null;
				}
				Method m = clazz.getMethod(methodName);
				if (m == null) {
					logger.error("method is null");
					return null;
				}
				return m.invoke(null);
			} catch (SecurityException e1) {
				logger.error("", e1);
				throw e1;
			} catch (NoSuchMethodException e1) {
				logger.error("", e1);
				throw e1;
			} catch (InvocationTargetException e1) {
				logger.error("", e1);
				throw e1;
			} catch (IllegalAccessException e1) {
				logger.error("", e1);
				throw e1;
			}
		}
	}
	
	/**
	 * 
	 * @Title: getGetMethodName
	 * @Description: 获取Field的getter方法名
	 * @param
	 * @return String
	 * @throws
	 * @author CAOXZ
	 * @version 1.0
	 * @time 2010-2-4 下午03:44:17
	 */
	public static String getGetMethodName(Field field) {
		if (field == null) {
			logger.error("field is null");
			return null;
		}
		if (field.getType().equals(Boolean.class)) {
			if (field.getName().length() > 1) {
				return "is" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
			} else {
				return "is" + field.getName().toUpperCase();
			}
		} else {
			if (field.getName().length() > 1) {
				return "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
			} else {
				return "get" + field.getName().toUpperCase();
			}
		}
	}
	
	/**
	 * 
	 * @Title: getSetMethodName
	 * @Description: 获取Field的setter方法名
	 * @param
	 * @return String
	 * @throws
	 * @author CAOXZ
	 * @version 1.0
	 * @time 2010-2-4 下午03:44:33
	 */
	public static String getSetMethodName(Field field) {
		if (field == null) {
			logger.error("field is null");
			return null;
		}
		if (field.getName().length() > 1) {
			return "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
		} else {
			return "set" + field.getName().toUpperCase();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Class getActualTypeArguments(Class clazz, int index) {
		Type[] types = ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments();
		if (types != null && index >= 0 && index < types.length) {
			return (Class) types[index];
		}
		return null;
	}
	
	/**
	 * 
	 * @Title: copyObject
	 * @Description: 拷贝对象
	 * @param
	 * @return void
	 * @throws
	 * @author CAOXZ
	 * @version 1.0
	 * @time 2010-3-5 下午05:11:00
	 */
	@SuppressWarnings("unchecked")
	public static void copyObject(Class clazz, Object obj, Object newObj) {
		BeanCopier copier = BeanCopier.create(clazz, clazz, false);
		copier.copy(obj, newObj, null);
	}
	
	/**
	 * 
	 * @Title: copyObject
	 * @Description: 拷贝对象
	 * @param
	 * @return void
	 * @throws
	 * @author CAOXZ
	 * @version 1.0
	 * @time 2010-3-5 下午05:14:00
	 */
	@SuppressWarnings("unchecked")
	public static void copyObject(Class fromClazz, Class toClazz, Object obj, Object newObj) {
		BeanCopier copier = BeanCopier.create(fromClazz, toClazz, false);
		copier.copy(obj, newObj, null);
	}
	
	public static <T> T newInstance(Class<T> clazz) throws NSException {
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			logger.error("", e);
			throw new SystemInternalException();
		} catch (IllegalAccessException e) {
			logger.error("", e);
			throw new SystemInternalException();
		}
	}
}
