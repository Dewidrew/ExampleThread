package ayp.aug.examplethread;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TestLooper.CallBacks {
    private static final String TAG = "MainActivity";
    public TextView txtView;
    public Button btnView;
    public EditText editView;
    private Handler handlerMainThread;
    private Context ctx;
    TestLooper testLooper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handlerMainThread = new Handler();

        testLooper = new TestLooper("ThreadName", this);
        testLooper.setHandlerMainThread(handlerMainThread);
        testLooper.start();
        testLooper.onLooperPrepared();

        btnView = (Button) findViewById(R.id.btn_1);
        editView = (EditText) findViewById(R.id.edittxt_1);
        txtView = (TextView) findViewById(R.id.text_1);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testLooper.sendValueInQueue();
            }
        });

    }

    @Override
    public void setText(String s) {
        txtView.setText(s);
        Log.d(TAG, "SET TEXT " + s);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        testLooper.clearQueue();
    }
}
