package com.lxl.dataStructures;

import java.lang.reflect.Field;

public class TestForAdd {

    /**
     * 2+2 = 5 You need to change it even deeper than you can typically access.
     * Note that this is designed for Java 6 with no funky parameters passed in
     * on the JVM that would otherwise change the IntegerCache.
     * <p>
     * Deep within the Integer class is a Flyweight of Integers. This is an
     * array of Integers from −128 to +127. cache[132] is the spot where 4 would
     * normally be. Set it to 5.
     * <p>
     * 利用缓存的读写接口，将4这个实例的缓存对象的指针改为指向5的实例对象了，这样，当应用程序取出4时，实际上返回的是5的引用，打印出来的也就是5了。
     */
    public static void errorAdd() {
        Class cache = Integer.class.getDeclaredClasses()[0];

        Field c;
        try {
            c = cache.getDeclaredField("cache");

            c.setAccessible(true);

            Integer[] array = (Integer[]) c.get(cache);

            array[132] = array[133];

            System.out.printf("%d", 2 + 2);
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
