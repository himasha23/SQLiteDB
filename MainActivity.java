package com.example.student.datab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import Database.DBhelper;

public class MainActivity extends AppCompatActivity {

    EditText txt_username,txt_password;
    String username,password;
    DBhelper  db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_username = findViewById(R.id.txt_username);
        txt_password = findViewById(R.id.txt_password);

        db = new DBhelper(this);

    }

    public void addInfro(View view){

        username = txt_username.getText().toString();
        password = txt_password.getText().toString();

        boolean result = db.addUser(username,password);

        if(result == true){

            Toast.makeText(getApplicationContext(),"Adding success",Toast.LENGTH_LONG).show();
        }else{

            Toast.makeText(getApplicationContext(),"Error in adding ", Toast.LENGTH_LONG).show();
        }
    }

    public void getAllUsers(View view){

        List resultList = db.readAllInfor();

        for(int i = 0; i < resultList.size();i++){


            Log.i("getAllUsers",resultList.get(i).toString());
        }
    }

    public void updatepwd(View view)
    {
        boolean result = db.updatePassword(txt_username.getText().toString(),txt_password.getText().toString());

        if(result == true){

            Toast.makeText(getApplicationContext(),"Update success",Toast.LENGTH_LONG).show();
        }else

            Toast.makeText(getApplicationContext(),"ERROR IN UPDATE",Toast.LENGTH_LONG).show();
    }








    public void deleteUser(View view)
    {
        int result = db.deleteData(txt_username.getText().toString());

        if(result == 1){

            Toast.makeText(getApplicationContext(),"delete success",Toast.LENGTH_LONG).show();
        }else

            Toast.makeText(getApplicationContext(),"ERROR IN deleting",Toast.LENGTH_LONG).show();
    }



}
