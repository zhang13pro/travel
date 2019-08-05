package cn.itcast.demo.reflect;

import cn.itcast.demo.po.Person;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @Author: bobobo
 * @Date: 2019/8/5 14:54
 * @Version：1.0
 * 自定义“框架”类
 * 在不能改变该类任何代码的前提下 而是通过 修改配置文件 可以创建任意类的对象 可以执行任意方法
 * 			* 实现：
 * 				1. 配置文件
 * 				2. 反射
 * 			* 步骤：
 * 				1. 将需要创建的对象的全类名和需要执行的方法定义在配置文件中
 * 				2. 在程序中加载读取配置文件
 * 				3. 使用反射技术来加载类文件进内存
 * 				4. 创建对象
 * 				5. 执行方法
 */
public class ReflectDemo {
    public static void main(String[] args) throws Exception {
        /*Person person = new Person();
        person.setName("ss");*/
        //1加载配置文件
        Properties properties = new Properties();
        //1.1在程序中加载配置文件
        ClassLoader loader = ReflectDemo.class.getClassLoader();
        InputStream is = loader.getResourceAsStream("pro.properties");
        properties.load(is);

        //2在程序中读取配置文件
        String className = properties.getProperty("className");
        String methodName = properties.getProperty("methodName");
        //3使用反射技术来加载类文件进内存
        Class cls = Class.forName(className);
        //4创建对象
        Object obj = cls.newInstance();
        //5获取方法对象
        Method method = cls.getMethod(methodName);
        //6执行方法
        method.invoke(obj);
    }
}
