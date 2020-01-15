package com.suruns.runsSpringBoot.exception;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: suruns
 * @Date: 2020年01月15日 14:41
 * @Desc:
 */
public class ExceptionInheritUtil {

    public static final String EXCEPTION_CLASS_NAME = "java.lang.Exception";

    public static String getLastInherit(Collection<String> names, Exception e) {
        List<String> inheritNames = getInherits(e);
        for (String name : inheritNames) {
            if (names.contains(name)) {
                return name;
            }
        }
        return EXCEPTION_CLASS_NAME;
    }

    private static List<String> getInherits(Exception e) {
        List<String> inheritNames = new ArrayList<>();
        getInherits(e.getClass(), inheritNames);
        return inheritNames;
    }
    private static void getInherits(Class<?> clazz, List<String> inheritNames) {
        final String name = clazz.getName();
        if (!EXCEPTION_CLASS_NAME.equals(name)){
            inheritNames.add(clazz.getName());
            getInherits(clazz.getSuperclass(), inheritNames);
        }
    }
}
