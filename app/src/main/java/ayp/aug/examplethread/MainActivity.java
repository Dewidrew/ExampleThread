package ayp.aug.examplethread;

import android.os.AsyncTask;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text_1);
        calendar = Calendar.getInstance();

        textView.setText(String.format("%1$tI:%1$tM:%1tS %1$Tp", calendar));

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExampleAsyncTask exampleAsyncTask = new ExampleAsyncTask();
                exampleAsyncTask.execute();
            }
        });

    }

    private class ExampleAsyncTask extends AsyncTask<Void, Void, Calendar> {
        @Override
        protected Calendar doInBackground(Void... voids) {
            calendar = Calendar.getInstance();
            return calendar;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(Calendar calendar) {
            super.onPostExecute(calendar);
            textView.setText(String.format("%1$tI:%1$tM:%1tS %1$Tp", calendar));
        }
    }
}
