package com.example.controller;

import com.example.bo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.Arrays;


@RestController
public class TestController {

    @RequestMapping("/")
    public String test(){
        return "hello word";
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //对象序列化
        /*FileOutputStream fos=new FileOutputStream("D://test.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(new User().setId(1L).setUserName("lisi").setPassWord("123456"));
        oos.flush();*/
        //对象反序列化
       /* FileInputStream fis = new FileInputStream("D://test.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        User user = (User)ois.readObject();
        ois.close();
        System.out.println(user);*/
        //User user = new User().setUserName("ZhangSan").setPassWord("123456").setId(1L);
        String a []={"abc","efg","xyz"};
        System.out.println("初始定义a="+ Arrays.toString(a));
        fix(a);
        System.out.println("方法执行结束后a="+ Arrays.toString(a));
    }
    private static void fix(String a []){
         a[2]=  "jkl";
        System.out.println("方法执行时a="+ Arrays.toString(a));
    }

}
