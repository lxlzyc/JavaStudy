package com.lxl.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

/**
 * 序列化实践  同SerializeUtil2
 *
 * @ClassName: SerializeUtil
 * @Description: TODO
 * @author: lxl
 * @date: 2016年5月18日 下午3:18:52
 */
public class SerializeUtil {

    public byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            //序列化--- null可以被序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != oos) {
                    //ObjectOutputStream为null时关闭报错
                    oos.close();
                }
            } catch (Exception e) {
                oos = null;
                e.printStackTrace();
            }
        }
        return baos.toByteArray();
    }

    public Object unserialize(byte[] bytes) {
        ObjectInputStream ois = null;
        Object returnObj = null;
        try {
            //反序列化--bytes为null时抛出空指针异常
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            returnObj = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != ois) {
                    ois.close();
                }
            } catch (Exception e) {
                ois = null;
                e.printStackTrace();
            }
        }
        return returnObj;
    }


    public static void main(String[] args) {
        SerializeUtil su = new SerializeUtil();
        String i = null;
        byte[] b = su.serialize(i);
        System.out.println(Arrays.toString(b));
        b = null;
        Object o = su.unserialize(b);
        if (o == null) {
            System.out.println("o is null");
        } else {
            System.out.println(o.toString());
        }
    }


}
