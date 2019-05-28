package khoapham.ptp.phamtanphat.xulybatdongbo1903;

import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Dataobject dataobject = new Dataobject(0,0);
    @Override
    protected synchronized void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // main : 1 luồng chính main thread
        //1 : Tạo thằng A
        //2 : Tạo thằng B
        //3 : Tổng a + b
        // for chay 0 -> 50 : A , B in gia trị random trong 0 -> 10
        // Hiển thị giá trị thông qua log
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0 ; i <= 50 ; i++){
                    int a = new Random().nextInt(11);
                    dataobject.setA(a);
                    Log.d("BBB", "A :"+  a + " , i : "+ i);
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0 ; i <= 50 ; i++){
                    int b = new Random().nextInt(11);
                    dataobject.setB(b);
                    Log.d("BBB", "B :"+  b + " , i : "+ i);
                }
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0 ; i <= 50 ; i++){
                    int ketqua = dataobject.tinhTong();
                    Log.d("BBB", "c :"+  ketqua + " , i : "+ i);
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

    }
    public synchronized void getLog(String keyword){
        for (int i = 0 ; i<100 ; i++){
            Log.d("BBB", keyword + " : " + i);
        }
    }
    private void xulybatdongbofunction(){
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
                Log.d("BBB","Trang thái A "+ thread.getState().name());
                Log.d("BBB", "Trang thái b "+thread1.getState().name());
            }
        },3000);
        // nói về đồng bộ :
        // phải có điểm chung
        //1 : function
        // 2; static function
        // 3 ; Object
    }
}
