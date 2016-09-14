package ayp.aug.examplethread;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

import javax.net.ssl.HandshakeCompletedListener;

public class MainActivity extends AppCompatActivity {

    public TextView txtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView = (TextView)findViewById(R.id.text_1);
        txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExampleTask exampleTask = new ExampleTask();
                exampleTask.execute(1);
            }
        });

    }

    public class ExampleTask extends AsyncTask<Integer,Integer,String>{

        @Override
        protected String doInBackground(Integer... args) {
            int i = args[0]; // i = 1;
            while(true){
                i++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i); //invoke UI Thread to onProgressUpdate
                if(i == 100){
                    break;
                }
            }

            return "Hello";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            txtView.setText(values[0].toString());
        }

        @Override
        protected void onPostExecute(String s) {
            txtView.setText(s);
        }
    }
}
