package com.example.sm.problem2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MyBaseAdapter adapter;
    ListView listview;
    ArrayList<Employee> emp_list;
    int position;
    int ag_,sa_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emp_list = new ArrayList<Employee>();

        // need something here

        adapter = new MyBaseAdapter(this, emp_list);
        listview = (ListView) findViewById(R.id.listView1) ;
        listview.setAdapter(adapter);
        listview.setOnItemClickListener((AdapterView.OnItemClickListener)adapter);
        Employee test = new Employee("나연",22,50000);
        adapter.add(test);


    }
    @Override
    public void onClick(View v){
        EditText edit_name = (EditText) findViewById(R.id.edit_name);
        EditText edit_age = (EditText) findViewById(R.id.edit_age);
        EditText edit_salary = (EditText) findViewById(R.id.edit_salary);

        Employee employee;

        switch (v.getId()){
            case R.id.btn_inc:
              position = listview.getSelectedItemPosition();
                emp_list.get(position).increase();
                // need something here
                break;

            case R.id.btn_dec:
                position = listview.getSelectedItemPosition();
                emp_list.get(position).decrease();
                // need something hereee
                break;

            case R.id.btn_store:
                String na = edit_name.getText().toString();
                String ag = edit_age.getText().toString();
                String sa = edit_salary.getText().toString();

                ag_ = Integer.parseInt(ag);
                sa_ = Integer.parseInt(sa);

                employee = new Employee(na,ag_,sa_);
                adapter.add(employee);
                // need something here
                break;

            case R.id.btn_modify:
                // need something here
                position = listview.getSelectedItemPosition();


                break;

            case R.id.btn_delete:
                // need something here
                position = listview.getSelectedItemPosition();
                adapter.delete(position);
                break;
        }
    }
}

interface Payment {
    void increase();
    void decrease();
}
