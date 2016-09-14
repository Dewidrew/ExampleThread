package ayp.aug.examplethread;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

/**
 * Created by Hattapong on 9/14/2016.
 */
public class TestLooper extends HandlerThread {

    private static final int MESSAGE_1 = 7;
    private static final int MESSAGE_2 = 9;

    protected Handler handlerInThread1;
    protected Handler handlerInThread2;
    protected Handler handlerMainThread;
    private static final String TAG = "TestLooper";
    private CallBacks callBacks;

    public TestLooper(String threadName, Context ctx) {
        super(threadName);
        callBacks = (CallBacks) ctx;
    }

    interface CallBacks {
        void setText(String s);
    }

    public void setHandlerMainThread(Handler handlerMainThread) {
        this.handlerMainThread = handlerMainThread;
    }

    @Override
    protected void onLooperPrepared() {
        handlerInThread1 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == MESSAGE_1) {
                    handlerMainThread.post(new Runnable() {
                        @Override
                        public void run() {
                            callBacks.setText("This is Thread I");
                            Log.d(TAG, "I" + getLooper().toString());
                        }
                    });
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        handlerInThread2 = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                if (msg.what == MESSAGE_2) {
                    handlerMainThread.post(new Runnable() {
                        @Override
                        public void run() {
                            callBacks.setText("This is Thread II");
                            Log.d(TAG, "II" + getLooper().toString());
                        }
                    });


                }

            }
        };
    }

    public void sendValueInQueue() {
        Message message1 = handlerInThread1.obtainMessage(MESSAGE_1);
        Message message2 = handlerInThread2.obtainMessage(MESSAGE_2);
        message1.sendToTarget();
        message2.sendToTarget();
    }

    public void clearQueue() {
        handlerInThread1.removeMessages(MESSAGE_1);
        handlerInThread2.removeMessages(MESSAGE_2);
    }


}
