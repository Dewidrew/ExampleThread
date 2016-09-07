package ayp.aug.examplethread;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    Calendar calendar;
    TextView textView;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                    textView.setText(String.format("%1$tI:%1$tM:%1tS %1$Tp", calendar));
            }
        };
        textView = (TextView) findViewById(R.id.text_1);
        calendar = Calendar.getInstance();

        textView.setText(String.format("%1$tI:%1$tM:%1tS %1$Tp", calendar));

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        calendar = Calendar.getInstance();
                        handler.sendEmptyMessage(0);
                        handler.postDelayed(this,1000);
                    }
                }).start();

            }
        });

    }
}
