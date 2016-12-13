package com.example.sm.problem3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    int rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<CustomerThread> list = new ArrayList<CustomerThread>();
        Manager manager = new Manager();

        for(int i = 0 ; i < 10 ; i++){
            Customer customer = new Customer("Customer" + i);
            CustomerThread ct = new CustomerThread(customer);
            list.add(ct);
            manager.add_customer(customer);
            ct.start();
        }

        for(CustomerThread ct : list){

            try {
                ct.stop();

                // need something here
            } catch (Exception e) { }
        }

        manager.sort();

        MyBaseAdapter adapter = new MyBaseAdapter(this, manager.list);
        ListView listview = (ListView) findViewById(R.id.listView1) ;
        listview.setAdapter(adapter);


    }
}

class CustomerThread extends Thread{

    Customer customer;

    CustomerThread(Customer customer){
        this.customer = customer;
    }
    // need something here


    @Override
    public void run() {
        super.run();
        for(int i=0;i<10;i++)
        {
            customer.work();
        }

    }
}

abstract class Person{

    static int money = 100000;
    int spent_money = 0;
    abstract void work();

}


class Customer extends Person{

    String name;
    Customer(String name){
        this.name = name;
    }

    @Override
    synchronized void work() {
        spent_money = (int) Math.random()*1000;
        money -= spent_money;

    }

    // need something here
}


class Manager extends Person{
    ArrayList <Customer> list = new ArrayList<Customer>();

    int temp;

    void add_customer(Customer customer) {
        list.add(customer);
    }

    void sort(){

        for(int i=0;i<list.size()-1;i++)
        {
            for(int j=0;j<list.size()-1-i;j++)
            {
                if(list.get(j).spent_money <list.get(j+1).spent_money)
                {
                    temp = list.get(j).spent_money;
                    list.get(j).spent_money = list.get(j+1).spent_money;
                    list.get(j+1).spent_money = temp;
                }
            }
        }



        // 직접 소팅 알고리즘을 이용하여 코딩해야함. 자바 기본 정렬 메소드 이용시 감

        // need something here

    }

    @Override
    void work() {
        sort();
    }
}

// need something here

