package com.test.jdk8;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

/**
 * 类JDK8Collect的实现描述：TODO 类实现描述 
 * @author Administrator 2018/6/25 17:57
 */
public class JDK8Collect {

    public static List<User> userList = new ArrayList<>();

    static{
        User user1 = new User();
        user1.setUserName("hello");
        user1.setAccountNo("11111");
        user1.setAge(11);

        User user2 = new User();
        user2.setUserName("good");
        user2.setAccountNo("22222");
        user2.setAge(22);

        User user3 = new User();
        user3.setUserName("test");
        user3.setAccountNo("3333");

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
    }

    public static void main(String[] args) {
//        listforMap();
//        getValueList();
//        listforListMap();
//        listForSet();
//        distinct();
//        sum();
//        forEach();
       for(int i=0;i<5;i++){
           SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
           System.out.println(sf.format(new Date()));
       }
        System.out.println(String.join(";","a","b"));
    }

    private static void forEach(){
        List<String> list=new ArrayList<String>();
        for(int i=0;i<100000;i++){
            list.add("a");
        }
        long a=System.currentTimeMillis();
        for(String action:list){
            if(action==null){
                //什么都不干，就判断一下而已
            }
        }
        long b=System.currentTimeMillis();
        System.out.println(b-a);//传统的foreach时间1
        list.forEach(action->{
            if(action==null){
                //什么都不干，就判断一下而已
            };
        });

        long c=System.currentTimeMillis();
        System.out.println(c-b);//JDK8的时间2
    }


    /**
     * 获取list某个属性的list
     */
    private static void getValueList(){
        List<String> userNameList = userList.stream().map(User::getUserName).collect(toList());
        System.out.println(userNameList);
    }

    /**
     * list转换map
     */
    private static void listforMap(){
        Map<String,User> map = userList.stream().collect(toMap(user->user.getAccountNo(), user->user));

        Map<String,User> map3 = userList.stream().collect(toMap(User::getUserName, user->user));

        Map<String,User> map1 = userList.stream().collect(toMap(user->user.getUserName(), User->User));

        Map<String,User> map2 = userList.stream().collect(toMap(User::getUserName,
                Function.identity()));

        System.out.println(map);

    }

    /**
     * list转换Map<String, List>
     */
    private static void listforListMap(){
        Map<String,List<User>> map = userList.stream().collect(groupingBy(user->user.getUserName()));
        System.out.println(map.get("hello").get(0).getAccountNo());
    }

    /**
     * list转换set
     */
    private static void listForSet(){
        Set<String> setUser = userList.stream().map(User::getUserName).collect(toSet());
        System.out.println(setUser);
    }

    private static void distinct(){
        List<String> userNameList = userList.stream().map(User::getUserName).collect(toList());
        List<String> list = userNameList.stream().distinct().collect(toList());
        System.out.println(list);
    }

    private static void sum(){
        int sum = userList.stream().filter(user->user.getAge()!=null).mapToInt(user->user.getAge()).sum();
        System.out.println(sum);
    }


    //jdk8冒号用法
    //::相当于类名调用方法
    //->相当于匿名方法
    public static class AcceptMethod {

        public static void  printValur(String str){
            System.out.println("print value : "+str);
        }

        public static void main(String[] args) {
            List<String> al = Arrays.asList("a","b","c","d");
            for (String a: al) {
                AcceptMethod.printValur(a);
            }
            //下面的for each循环和上面的循环是等价的
            al.forEach(x->{;
                AcceptMethod.printValur(x);
            });

            al.forEach(AcceptMethod::printValur);

            //下面的方法和上面等价的
            Consumer<String> methodParam = AcceptMethod::printValur; //方法参数
            al.forEach(x -> methodParam.accept(x));//方法执行accept
        }
    }
}
