
 
package com.rischan.appclient;
import com.rischan.sqlite.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
 
public class AppClientActivity extends Activity {
 
 private Socket client;
 private PrintWriter printwriter;
 private EditText textField;
 private Button button;
 private String messsage;


 
 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_app_client);
 
  textField = (EditText) findViewById(R.id.pesan); //reference to the text field
  button = (Button) findViewById(R.id.kirim);   //reference to the send button
  final DBSqlite db = new DBSqlite(this);
  
  //Button press event listener
  button.setOnClickListener(new View.OnClickListener() {
 
   public void onClick(View v) {
 
    messsage = textField.getText().toString(); //get the text message on the text field
    textField.setText("");      //Reset the text field to blank
 
    try {
     db.addPesan(messsage);
     TulisDiSdcard(messsage);
     client = new Socket("10.0.2.2", 4444);  //connect to server
     printwriter = new PrintWriter(client.getOutputStream(),true);
     printwriter.write(messsage);  //write the message to output stream
     printwriter.flush();
     printwriter.close();
     client.close();   //closing the connection
 
    } catch (UnknownHostException e) {
     e.printStackTrace();
    } catch (IOException e) {
     e.printStackTrace();
    }
   }
  });
 
 }
 
 public void TulisDiSdcard(String pesan)
 {       
	 File root = android.os.Environment.getExternalStorageDirectory(); 
	File dir = new File (root.getAbsolutePath() + "/");
    dir.mkdirs();
    File logFile = new File(dir,"pesan.txt");
    if (!logFile.exists())
    {
       try
       {
          logFile.createNewFile();
       } 
       catch (IOException e)
       {
          // TODO Auto-generated catch block
          e.printStackTrace();
       }
    }
    try
    {
       //BufferedWriter for performance, true to set append to file flag
       BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true)); 
       buf.append(pesan);
       buf.newLine();
       buf.close();
    }
    catch (IOException e)
    {
       // TODO Auto-generated catch block
       e.printStackTrace();
    }
 }
 
 
}