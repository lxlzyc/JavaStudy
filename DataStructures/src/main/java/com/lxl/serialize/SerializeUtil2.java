package com.lxl.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * try-with-resources，是个语法糖。实际上就是自动调用资源的close()函数
 *
 * @ClassName: SerializeUtil2
 * @Description: TODO
 * @author: lxl
 * @date: 2016年5月18日 下午4:16:03
 */
public class SerializeUtil2 {

    public byte[] serialize(Object object) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos);) {
            oos.writeObject(object);
            return baos.toByteArray();
        } catch (IOException e) {
        }
        return null;
    }

    public Object unserialize(byte[] bytes) {
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));) {
            return ois.readObject();
        } catch (Exception e) {
        }
        return null;
    }

}
