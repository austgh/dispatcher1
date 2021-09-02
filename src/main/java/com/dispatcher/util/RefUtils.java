package com.dispatcher.util;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;

public class RefUtils {
	public static Field getInheritedField(Class<?> clazz, String fieldName) {
		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				return clazz.getDeclaredField(fieldName);
			} catch (NoSuchFieldException | SecurityException e) {
			}
		}

		return null;
	}

	public static Field[] getInheritedFields(Class<?> clazz) {
		Field[] fields = new Field[0];
		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			fields = ArrayUtils.addAll(fields, clazz.getDeclaredFields());
		}
		return fields;
	}

	public static Object newInstance(Object obj) {
		try {
			return obj.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException("动态创建对象[" + obj.toString() + "]报错!");
		}
	}

	public static <T> T newInstance(Class<T> cl) {
		try {
			return cl.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException("动态创建对象[" + cl.getName() + "]报错!");
		}
	}

	public static Object get(Field field, Object obj) {
		try {
			field.setAccessible(true);
			return field.get(obj);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException("动态获得对象[" + obj.toString() + "]的属性["
					+ field.getName() + "]的值报错!");
		}
	}

	public static void set(Field field, Object obj, Object value) {
		try {
			field.setAccessible(true);
			field.set(obj, value);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException("动态设置对象[" + obj.toString() + "]的属性["
					+ field.getName() + "]的值为[" + value.toString() + "报错!");
		}
	}

	public static boolean equalType(Field field, Class<?> type) {
		return field.getType() == type;
	}
}
