package ayp.aug.examplethread;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HandshakeCompletedListener;

public class MainActivity extends AppCompatActivity {

    public TextView txtView;
    public Button btnView;
    public EditText editView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnView = (Button)findViewById(R.id.btn_1);
        editView = (EditText)findViewById(R.id.edittxt_1);
        txtView = (TextView)findViewById(R.id.text_1);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExampleTask exampleTask = new ExampleTask();
                exampleTask.execute(Integer.parseInt(editView.getText().toString()));
            }
        });

    }

    public class ExampleTask extends AsyncTask<Integer,Integer,String>{

        @Override
        protected String doInBackground(Integer... args) {
            int i = args[0]; // i = 1;
            while(true){

                try {
//                    Thread.sleep(1000);
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i); //invoke UI Thread to onProgressUpdate
                i--;
                if(i == 0){
                    break;
                }
            }

            return "Finish";
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
