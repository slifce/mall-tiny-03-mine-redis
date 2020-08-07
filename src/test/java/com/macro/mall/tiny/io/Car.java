package com.macro.mall.tiny.io;

/**
 * Created by Administrator on 2020/8/7.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Car implements Comparable<Car>, Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String company;
    private String brand;

    private int maxSpeed;
    private float price;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Car(String company, String brand, float price) {
        super();
        this.company = company;
        this.brand = brand;
        this.price = price;
    }

    public Car(String company, String brand, int maxSpeed) {
        super();
        this.company = company;
        this.brand = brand;
        this.maxSpeed = maxSpeed;
    }

    public Car(String company, String brand, int maxSpeed, float price) {
        super();
        this.company = company;
        this.brand = brand;
        this.maxSpeed = maxSpeed;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car [company=" + company + ", brand=" + brand + ", maxSpeed="
                + maxSpeed + ", price=" + price + "]";
    }

    @Override
    public int compareTo(Car o) {
        return this.maxSpeed-o.getMaxSpeed();
    }

    @SuppressWarnings("resource")
    public static void main(String[] args) throws IOException {
        List<Car> al = new ArrayList<>();
        Car car1 = new Car("1111", "1111", 10);
        Car car2 = new Car("3333", "3333", 30);
        Car car3 = new Car("22222", "22222", 20);
        al.add(car1);
        al.add(car2);
        al.add(car3);
        System.out.println(al.get(2).toString());
        Collections.sort(al);
        System.out.println(al.get(2).toString());
        Properties pro=new Properties();
        String filePath = Car.class.getResource("/generator.properties").getPath();
        System.out.println(filePath);
        FileInputStream fis=new FileInputStream(filePath);
        pro.load(fis);
        String ip=pro.getProperty("jdbc.password");
        System.out.println(ip);
        File file = new File("112233.txt");
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(new String("123").getBytes());
        outputStream.close();

        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] c = new byte[1024];
        int n = 0;
        while((n=fileInputStream.read(c))>-1){
            String str = new String(c,0,n,"UTF-8");//利用GBK/UTF-8字符集，对FileInputStream读取的字节数组进行解码
            System.out.print(str);
        }

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("car.txt"));
        objectOutputStream.writeObject(car1);
        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("car.txt"));
        try {
            Car car = (Car)objectInputStream.readObject();
            System.out.println(car.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Map<Object, Integer> map = new HashMap<Object, Integer>();
        map.put(null, 213);
        Map<Object, Integer> map2 = new Hashtable<Object, Integer>();
        map2.put(null, 213);
    }
}

