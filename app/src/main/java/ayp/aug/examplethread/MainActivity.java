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


    private static final int CODE_HANDLER = 1234;
    public TextView txtView;

    public Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         handler = new Handler(){
             @Override
             public void handleMessage(Message msg) {
                 if(msg.what == CODE_HANDLER){
                     txtView.setText(msg.obj.toString());
                 }
             }
         };


        txtView = (TextView)findViewById(R.id.text_1);
        txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Message msg = handler.obtainMessage(CODE_HANDLER,new Object());
                msg.sendToTarget();

            }
        });

    }
}
