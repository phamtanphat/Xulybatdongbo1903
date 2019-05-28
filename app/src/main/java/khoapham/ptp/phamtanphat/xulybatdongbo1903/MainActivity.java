package khoapham.ptp.phamtanphat.xulybatdongbo1903;

import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Dataobject dataobject = new Dataobject(0, 0);
    int index = 0;
    @Override
    protected synchronized void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataobject.setLaco(0);
        // main : 1 luồng chính main thread
        //1 : Tạo thằng A
        //2 : Tạo thằng B
        //3 : Tổng a + b
        // for chay 0 -> 50 : A , B in gia trị random trong 0 -> 10
        // Hiển thị giá trị thông qua log
        //for 1 - 50
        // A : so chan
        // B : so le
        // C : so chia 3 du 1
        // D : ((sochia 3 + so le) / 2 ) + chẳn0
        //bai 2 :
        // A : tạo chiều dài
        // B : tạo chiều rộng
        //c : diện tích hình chữ nhật
        //d : chu vi hình chữ nhật
        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (dataobject) {
                    for (int i = 0; i <= 5000; ) {
                        if (dataobject.getLaco() == 0) {
                            index = i++;
                            int a = new Random().nextInt(11);
                            dataobject.setA(a);
                            dataobject.setLaco(2);
                            dataobject.notifyAll();
                            Log.d("BBB", "A :" + a + " , i : " + index );
                        } else {
                            try {
                                dataobject.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    dataobject.setLaco(4);
                    dataobject.notifyAll();

                }

            }
        });
        final Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (dataobject) {
                    for (; true; ) {
                        if (dataobject.getLaco() == 2 || dataobject.getLaco()== 4){
                            int b = new Random().nextInt(11);
                            if (dataobject.getLaco() == 4){
                                dataobject.setB(b);
                                dataobject.setLaco(5);
                                dataobject.notifyAll();
                                return;
                            }
                            dataobject.setB(b);
                            dataobject.setLaco(3);
                            dataobject.notifyAll();

                            Log.d("BBB", "B :" + b + " , i : " + index);
                        }else {
                            try {
                                dataobject.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }

            }
        });

        final Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (dataobject){
                    for (; true;) {
                        if (dataobject.getLaco() == 3 || dataobject.getLaco() == 5){
                            int ketqua = dataobject.tinhTong();
                            if (dataobject.getLaco() == 5){
                                Log.d("BBB", "c :" + ketqua + " , i : " + index);
                                return;
                            }
                            dataobject.setLaco(0);
                            dataobject.notifyAll();
                            Log.d("BBB", "c :" + ketqua + " , i : " + index);
                        }else{
                            try {
                                dataobject.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }

            }
        });
        thread2.start();
        thread3.start();
        thread1.start();


        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("CCC","Thread A "+ thread1.getState().name());
                Log.d("CCC","Thread B "+ thread2.getState().name());
                Log.d("CCC","Thread C "+ thread3.getState().name());
            }
        },3000);
    }

    public synchronized void getLog(String keyword) {
        for (int i = 0; i < 100; i++) {
            Log.d("BBB", keyword + " : " + i);
        }
    }

    private void xulybatdongbofunction() {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getLog("A");
            }
        });
        thread.start();
        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                getLog("B");
            }
        });
        thread1.start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("BBB", "Trang thái A " + thread.getState().name());
                Log.d("BBB", "Trang thái b " + thread1.getState().name());
            }
        }, 3000);
        // nói về đồng bộ :
        // phải có điểm chung
        //1 : function
        // 2; static function
        // 3 ; Object
    }
}
