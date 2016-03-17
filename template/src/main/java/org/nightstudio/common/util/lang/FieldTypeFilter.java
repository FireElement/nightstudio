package org.nightstudio.common.util.lang;

import java.lang.reflect.Field;

public interface FieldTypeFilter {
	boolean doFilter(Field field);
}
