package com.lxl.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author lxl
 */
public class TestObjSerializeAndDeserialize {
    /**
     * 序列化
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void SerializePerson() throws FileNotFoundException, IOException {
        Person person = new Person();
        person.setName("gacl");
        person.setAge(25);
        person.setSex("男");
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File("E:/person.txt")));
        oo.writeObject(person);
        System.out.println("person对象序列化成功");
        oo.close();
    }

    /**
     * 反序列化
     */
    private static Person DeserializePerson() throws Exception, IOException {
        ObjectInputStream oo = new ObjectInputStream(new FileInputStream(new File("E:/person.txt")));
        Person person = (Person) oo.readObject();
        System.out.println("Person对象反序列化成功");
        return person;
    }

}
