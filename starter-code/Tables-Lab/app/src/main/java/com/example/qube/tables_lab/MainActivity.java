package com.example.qube.tables_lab;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qube.tables_lab.setup.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // TODO: Reminder fill up the global variables
    Button addData;
    Button employeesWorkingAtTheSameCompany;
    Button companiesInBoston;
    Button companyWithTheHighestSalary;
    TextView textView;
    ListView listView;
    DatabaseHelper databaseHelper;
    ArrayAdapter<String> arrayAdapter;
    List<String> arrayList;
    LinearLayout linearLayout;

    Employee emp1, emp2, emp3, emp4, emp5, emp6, emp7, emp8;
    Job job1, job2, job3, job4, job5, job6, job7, job8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        linearLayout = (LinearLayout) findViewById(R.id.linearlayout);
        listView = (ListView) findViewById(R.id.listView);

        arrayList = new ArrayList<String>();
        arrayList = new ArrayList<String>();
        arrayList = databaseHelper.getEmployee();

        arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout
                .simple_expandable_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        addData = (Button) findViewById(R.id.addData_button);
        employeesWorkingAtTheSameCompany = (Button) findViewById(R.id.sameCompany_button);
        companiesInBoston = (Button) findViewById(R.id.bostonCompany_button);
        companyWithTheHighestSalary = (Button) findViewById(R.id.highestSalary_button);
        textView = (TextView) findViewById(R.id.textView);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        emp1 = new Employee("123-04-5678", "John", "Smith", 1973, "NY");
        emp2 = new Employee("123-04-5679", "David", "McWill", 1982, "Seattle");
        emp3 = new Employee("123-04-5680", "Katerina", "Wise", 1973, "Boston");
        emp4 = new Employee("123-04-5681", "Donald", "Lee", 1992, "London");
        emp5 = new Employee("123-04-5682", "Gary", "Henwood", 1987, "Las Vegas");
        emp6 = new Employee("123-04-5683", "Anthony", "Bright", 1963, "Seattle");
        emp7 = new Employee("123-04-5684", "William", "Newey", 1995, "Boston");
        emp8 = new Employee("123-04-5685", "Melony", "Smith", 1970, "Chicago");

        job1 = new Job("123-04-5678", "Fuzz", 60, 1);
        job2 = new Job("123-04-5679", "GA", 70, 2);
        job3 = new Job("123-04-5680", "Little Place", 120, 5);
        job4 = new Job("123-04-5681", "Macy's", 78, 3);
        job6 = new Job("123-04-5683", "Believe", 158, 6);
        job7 = new Job("123-04-5684", "Macy's", 200, 8);
        job8 = new Job("123-04-5685", "Stop", 299, 12);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(MainActivity.this, "Pika Pika", Toast.LENGTH_LONG);
                toast.show();
                createNewEmployeeAlertDialog();
            }
        });

        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    databaseHelper.insertRowEmployee(emp1);
                    databaseHelper.insertRowEmployee(emp2);
                    databaseHelper.insertRowEmployee(emp3);
                    databaseHelper.insertRowEmployee(emp4);
                    databaseHelper.insertRowEmployee(emp5);
                    databaseHelper.insertRowEmployee(emp6);
                    databaseHelper.insertRowEmployee(emp7);
                    databaseHelper.insertRowEmployee(emp8);

                    databaseHelper.insertRowJob(job1);
                    databaseHelper.insertRowJob(job2);
                    databaseHelper.insertRowJob(job3);
                    databaseHelper.insertRowJob(job4);
                    databaseHelper.insertRowJob(job5);
                    databaseHelper.insertRowJob(job6);
                    databaseHelper.insertRowJob(job7);
                    databaseHelper.insertRowJob(job8);
                } catch (SQLiteConstraintException exception) {
                }
            }
        });

        companiesInBoston.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList = databaseHelper.getBostonCompanies();
                for (String string : arrayList) {
                    Log.i("employee", string);
                }
                arrayAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_expandable_list_item_1, arrayList);
                listView.setAdapter(arrayAdapter);
            }
        });

        employeesWorkingAtTheSameCompany.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                arrayList = databaseHelper.getEmployeeInfo();
                arrayAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_expandable_list_item_1, arrayList);
                for (String string : arrayList) {
                    Log.i("employee", string);
                }
                listView.setAdapter(arrayAdapter);
            }
        });

        companyWithTheHighestSalary.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                textView.setText("Company with the highest salary: " + databaseHelper.getHigestSalary() + " ");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void createNewEmployeeAlertDialog() {
        // Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
        // Get the layout inflater (inflater is a LayoutInflater data type assigned to inflating the main activity
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        // dialogView is a View data type that inflates the view with the data from the add_data xml
        final View dialogView = inflater.inflate(R.layout.add_data, null);
        // Inflate and set the layout for the dialog, pass null as the parent view because its going in the dialog layout
        dialogBuilder.setView(dialogView);

        final EditText ssn = (EditText) dialogView.findViewById(R.id.ssn_editText);
        final EditText firstName = (EditText) dialogView.findViewById(R.id.firstName_editText);
        final EditText lastName = (EditText) dialogView.findViewById(R.id.lastName_editText);
        final EditText yearOfBirth = (EditText) dialogView.findViewById(R.id.yearOfBirth_editText);
        final EditText city = (EditText) dialogView.findViewById(R.id.city_editText);

        // Create buttons for the dialog view
        dialogBuilder.setPositiveButton("Add Data", null);
        dialogBuilder.setNegativeButton("Cancel", null);

        final AlertDialog b = dialogBuilder.create();
        b.show();

        // get the add data button and cancel button
        b.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //TODO: Fill this button up
            }
        });
        b.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                b.dismiss();
            }
        });
    }

    public boolean checkInput(EditText editText) {
        if (editText.getText().toString().length() == 0) {
            Snackbar.make(linearLayout, "Enter something please!", Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (editText.getText().toString().length() > 100) {
            Snackbar.make(linearLayout, "Something is way too long.", Snackbar.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}
