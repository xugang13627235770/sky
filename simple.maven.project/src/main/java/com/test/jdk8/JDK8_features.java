package com.test.jdk8;

import com.google.common.collect.Lists;

import java.nio.charset.StandardCharsets;
import java.time.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/**
 * 类JDK8_features的实现描述：TODO 类实现描述 
 * @author Administrator 2018/6/13 10:40
 */
public class JDK8_features {

    public static List<Integer> list = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10);

    /**
     * 1.Lambda表达式
     */
    public static void testLambda(){
        list.forEach(System.out::println);
        list.forEach(e -> System.out.println("方式二："+e));
    }

    public static void main(String[] args) {
        testStream();
    }

    public static void testStream(){
        List<Integer> nums = Lists.newArrayList(1,1,null,2,3,4,null,5,6,7,8,9,10);
//        System.out.println("求和："+nums
//                .stream()//转成Stream
//                .filter(team -> team!=null)//过滤
//                .distinct()//去重
//                .mapToInt(num->num*2)//map操作
//                .skip(2)//跳过前2个元素
//                .limit(4)//限制取前4个元素
//                .peek(System.out::println)//流式处理对象函数
//                .sum());//

//        nums.stream().filter(team -> team!=null).forEach(System.out::println);
//        nums.stream().distinct().forEach(System.out::println);
//        Integer sum1 = nums.stream().filter(team -> team!=null).mapToInt(num->num*2).sum();
        System.out.println(nums.stream().filter(team -> team!=null).reduce(0,Integer::sum));
//        nums.stream().skip(2).forEach(System.out::println);
//        nums.stream().limit(4).forEach(System.out::println);
    }

    public static void testDefaultFunctionInterface(){
        //可以直接使用接口名.静态方法来访问接口中的静态方法
        JDK8Interface1.staticMethod();
        //接口中的默认方法必须通过它的实现类来调用
        new JDK8InterfaceImpl1().defaultMethod();
        //多实现类，默认方法重名时必须复写
        new JDK8InterfaceImpl2().defaultMethod();
    }

    /**
     * 4.方法引用,与Lambda表达式联合使用
     */
    public static void testMethodReference(){
        //构造器引用。语法是Class::new，或者更一般的Class< T >::new，要求构造器方法是没有参数；
        final Car car = Car.create( Car::new );
        final List< Car > cars = Arrays.asList( car );
        //静态方法引用。语法是Class::static_method，要求接受一个Class类型的参数；
        cars.forEach( Car::collide );
        //任意对象的方法引用。它的语法是Class::method。无参，所有元素调用；
        cars.forEach( Car::repair );
        //特定对象的方法引用，它的语法是instance::method。有参，在某个对象上调用方法，将列表元素作为参数传入；
        final Car police = Car.create( Car::new );
        cars.forEach( police::follow );
    }

    public static class Car {
        public static Car create( final Supplier< Car > supplier ) {
            return supplier.get();
        }

        public static void collide( final Car car ) {
            System.out.println( "静态方法引用 " + car.toString() );
        }

        public void repair() {
            System.out.println( "任意对象的方法引用 " + this.toString() );
        }

        public void follow( final Car car ) {
            System.out.println( "特定对象的方法引用 " + car.toString() );
        }
    }

    /**
     * 5.引入重复注解
     * 1.@Repeatable
     * 2.可以不用以前的“注解容器”写法，直接写2次相同注解即可
     *
     * Java 8在编译器层做了优化，相同注解会以集合的方式保存，因此底层的原理并没有变化。
     */
    public static void RepeatingAnnotations(){
        RepeatingAnnotations.main(null);
    }

    /**
     * 6.类型注解
     * 新增类型注解:ElementType.TYPE_USE 和ElementType.TYPE_PARAMETER（在Target上）
     *
     */
    public static void ElementType(){
        Annotations.main(null);
    }

    /**
     * 7.最新的Date/Time API (JSR 310)
     */
    public static void DateTime(){
        //1.Clock
        final Clock clock = Clock.systemUTC();
        System.out.println( clock.instant() );
        System.out.println( clock.millis() );

        //2. ISO-8601格式且无时区信息的日期部分
        final LocalDate date = LocalDate.now();
        final LocalDate dateFromClock = LocalDate.now( clock );

        System.out.println( date );
        System.out.println( dateFromClock );

        // ISO-8601格式且无时区信息的时间部分
        final LocalTime time = LocalTime.now();
        final LocalTime timeFromClock = LocalTime.now( clock );

        System.out.println( time );
        System.out.println( timeFromClock );

        // 3.ISO-8601格式无时区信息的日期与时间
        final LocalDateTime datetime = LocalDateTime.now();
        final LocalDateTime datetimeFromClock = LocalDateTime.now( clock );

        System.out.println( datetime );
        System.out.println( datetimeFromClock );

        // 4.特定时区的日期/时间，
        final ZonedDateTime zonedDatetime = ZonedDateTime.now();
        final ZonedDateTime zonedDatetimeFromClock = ZonedDateTime.now( clock );
        final ZonedDateTime zonedDatetimeFromZone = ZonedDateTime.now( ZoneId.of( "America/Los_Angeles" ) );

        System.out.println( zonedDatetime );
        System.out.println( zonedDatetimeFromClock );
        System.out.println( zonedDatetimeFromZone );

        //5.在秒与纳秒级别上的一段时间
        final LocalDateTime from = LocalDateTime.of( 2014, Month.APRIL, 16, 0, 0, 0 );
        final LocalDateTime to = LocalDateTime.of( 2015, Month.APRIL, 16, 23, 59, 59 );

        final Duration duration = Duration.between( from, to );
        System.out.println( "Duration in days: " + duration.toDays() );
        System.out.println( "Duration in hours: " + duration.toHours() );
    }

    /**
     * 8.新增base64加解密API
     */
    public static  void testBase64(){
        final String text = "就是要测试加解密！！abjdkhdkuasu!!@@@@";
        String encoded = Base64.getEncoder()
                .encodeToString( text.getBytes( StandardCharsets.UTF_8 ) );
        System.out.println("加密后="+ encoded );

        final String decoded = new String(
                Base64.getDecoder().decode( encoded ),
                StandardCharsets.UTF_8 );
        System.out.println( "解密后="+decoded );
    }

    /**
     * 9.数组并行（parallel）操作
     */
    public static void testParallel(){
        long[] arrayOfLong = new long [ 20000 ];
        //1.给数组随机赋值
        Arrays.parallelSetAll( arrayOfLong,
                index -> ThreadLocalRandom.current().nextInt( 1000000 ) );
        //2.打印出前10个元素
        Arrays.stream( arrayOfLong ).limit( 10 ).forEach(
                i -> System.out.print( i + " " ) );
        System.out.println();
        //3.数组排序
        Arrays.parallelSort( arrayOfLong );
        //4.打印排序后的前10个元素
        Arrays.stream( arrayOfLong ).limit( 10 ).forEach(
                i -> System.out.print( i + " " ) );
        System.out.println();
    }
}
