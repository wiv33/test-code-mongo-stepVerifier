package com.psawesome.testcodeproject.bytebuddyTest;

import com.psawesome.testcodeproject.bytebuddyTest.model.MyChildClass;
import com.psawesome.testcodeproject.bytebuddyTest.model.MyParentClass;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.DynamicType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * package: com.psawesome.testcodeproject.lombokTest.model
 * author: PS
 * DATE: 2020-03-09 월요일 00:26
 */
public class ByteBuddyTest {

    @Test
    void parentClassTest() throws IOException {
        DynamicType.Unloaded<MyParentClass> make = new ByteBuddy()
                .subclass(MyParentClass.class)
                .defineField("psAwesome", String.class, Visibility.PUBLIC)
                .defineField("isBody", String.class, Visibility.PUBLIC)
                .defineField("id", Long.class, Visibility.PRIVATE)
                .make();
//                .saveIn(new File());

        Class<? extends MyParentClass> loaded = make.load(MyParentClass.class.getClassLoader())
                .getLoaded();

        Object[] objects = Arrays.stream(loaded.getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toList())
                .toArray();
        Object[] expected = Arrays.asList("psAwesome", "isBody", "id")
                .toArray();

        Assertions.assertArrayEquals(expected, objects);
    }

    @Test
    void childClassTest() throws ClassNotFoundException {
        Class<?> clazz = Class.forName(MyChildClass.class.getName());

        for (Field declaredField : clazz.getDeclaredFields()) {
            System.out.println("declaredField = " + declaredField.getName());
        }
    }
}
