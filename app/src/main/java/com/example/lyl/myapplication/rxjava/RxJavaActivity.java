package com.example.lyl.myapplication.rxjava;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.example.lyl.myapplication.BaseActivity;
import com.example.lyl.myapplication.R;
import com.example.lyl.myapplication.api.LoginSuccess;
import com.example.lyl.myapplication.api.NetWorkService;
import com.example.lyl.myapplication.api.Utils;
import com.example.lyl.myapplication.bean.Liebiao;
import com.example.lyl.myapplication.bean.LoginSucess;
import com.example.lyl.myapplication.rxjava.login.JsonUtils;
import com.example.lyl.myapplication.rxjava.login.LoginUtils;
import com.example.lyl.myapplication.rxjava.login.entity.Login;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.GroupedObservable;
import rx.schedulers.Schedulers;

/**
 * @author lyl
 * @date 2017/11/14.
 */

@SuppressWarnings({"AlibabaRemoveCommentedCode", "AlibabaAvoidManuallyCreateThread", "AlibabaAvoidCommentBehindStatement"})
public class RxJavaActivity extends BaseActivity {
    private Button button, btn_drawable, btn_flatMap, btn_interval, btn_rxbus, btn_filter, btn_download, btn_login, btn_defer;
    private Button btn_map, btn_send, btn_take, btn_from, btn_just, btn_repet, btn_range, btn_timer, btn_takeLast, btn_distinct,
            btn_distinctUntilChanged, btn_first, btn_last, btn_skip, btn_skipLast, btn_ElementAt, btn_sample,
            btn_timeout, btn_debounce, btn_flatmap, btn_concatmap, btn_flatMapIterable, btn_scan, btn_groupby, btn_buffer,
            btn_window, btn_cast, btn_merge, btn_login2;
    private EditText ed_debounce;
    private ImageView imageview;
    private Subscription subscr;
    private Subscriber<byte[]> subscriber;
    private Map<String, String> params = new HashMap<>();
    private Subscription subscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjava);
        /**
         * 测试OkGo网络请求框架
         */
        okGoRequest();

        ed_debounce = (EditText) findViewById(R.id.ed_debounce);
        button = (Button) findViewById(R.id.btn_rxbindding);
        btn_drawable = (Button) findViewById(R.id.btn_drawable);
        imageview = (ImageView) findViewById(R.id.imageview);
        btn_flatMap = (Button) findViewById(R.id.btn_flatMap);
        btn_interval = (Button) findViewById(R.id.btn_interval);
        btn_rxbus = (Button) findViewById(R.id.btn_rxbus);
        btn_filter = (Button) findViewById(R.id.btn_filter);
        btn_download = (Button) findViewById(R.id.btn_download);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_defer = (Button) findViewById(R.id.btn_defer);
        btn_map = (Button) findViewById(R.id.btn_map);
        btn_send = (Button) findViewById(R.id.btn_send);
        btn_take = (Button) findViewById(R.id.btn_take);
        btn_from = (Button) findViewById(R.id.btn_from);
        btn_just = (Button) findViewById(R.id.btn_just);
        btn_repet = (Button) findViewById(R.id.btn_repet);
        btn_range = (Button) findViewById(R.id.btn_range);
        btn_timer = (Button) findViewById(R.id.btn_timer);
        btn_takeLast = (Button) findViewById(R.id.btn_takeLast);
        btn_distinct = (Button) findViewById(R.id.btn_distinct);
        btn_distinctUntilChanged = (Button) findViewById(R.id.btn_distinctUntilChanged);
        btn_first = (Button) findViewById(R.id.btn_first);
        btn_last = (Button) findViewById(R.id.btn_last);
        btn_skip = (Button) findViewById(R.id.btn_skip);
        btn_skipLast = (Button) findViewById(R.id.btn_skipLast);
        btn_ElementAt = (Button) findViewById(R.id.btn_ElementAt);
        btn_sample = (Button) findViewById(R.id.btn_sample);
        btn_timeout = (Button) findViewById(R.id.btn_timeout);
        btn_debounce = (Button) findViewById(R.id.btn_debounce);
        btn_flatmap = (Button) findViewById(R.id.btn_flatmap);
        btn_concatmap = (Button) findViewById(R.id.btn_concatmap);
        btn_flatMapIterable = (Button) findViewById(R.id.btn_flatMapIterable);
        btn_scan = (Button) findViewById(R.id.btn_scan);
        btn_groupby = (Button) findViewById(R.id.btn_groupby);
        btn_buffer = (Button) findViewById(R.id.btn_buffer);
        btn_window = (Button) findViewById(R.id.btn_window);
        btn_cast = (Button) findViewById(R.id.btn_cast);
        btn_merge = (Button) findViewById(R.id.btn_merge);
        btn_login2 = (Button) findViewById(R.id.btn_login2);
        btn_login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        btn_merge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                merge();
            }
        });
        btn_cast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cast();
            }
        });
        btn_window.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window();
            }
        });
        btn_buffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buffer();
            }
        });
        btn_groupby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupBy();
            }
        });
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scan();
            }
        });
        btn_flatMapIterable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flatMapIterable();
            }
        });
        btn_concatmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                concatMap();
            }
        });
        btn_flatmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flatMap();
            }
        });
        //debounce可用于延时搜索的功能
        RxTextView.textChanges(ed_debounce)
                .debounce(400, TimeUnit.MILLISECONDS)//ms
                .subscribe(new Subscriber<CharSequence>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CharSequence charSequence) {
                        Log.e(TAG, "onNext: " + charSequence);
                    }
                });

        btn_debounce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                debounce();
            }
        });
        btn_timeout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeout();
            }
        });
        btn_sample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sample();
            }
        });
        btn_ElementAt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elementAt();
            }
        });
        btn_skipLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxSkipLast();
            }
        });
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skip();
            }
        });
        btn_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxLast();
            }
        });
        btn_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxFirst();
            }
        });
        btn_distinctUntilChanged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDistinctUntilChanged();
            }
        });
        btn_distinct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDistinct();
            }
        });

        btn_takeLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxTakeLast();
            }
        });
        btn_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxTimer();
            }
        });
        btn_range.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxRange();
            }
        });
        btn_repet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxRepet();
            }
        });
        btn_just.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxJust();
            }
        });
        btn_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxFrom();
            }
        });
        btn_take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxTake();
            }
        });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCheckCode();
            }
        });
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map();
            }
        });
        btn_defer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defer();
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                params.put("userName", "zlq");
                params.put("password", "888888");
                new LoginUtils().getObserString("http://36.7.144.130/web/entrance/phoneLogin.do", params)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<JSONObject>() {
                            @Override
                            public void onCompleted() {
                                Log.e(TAG, "onCompleted ");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, "onError: ");
                            }

                            @Override
                            public void onNext(JSONObject s) {

                                Login login = new Login();
                                JsonUtils.analyseJson(s, login);
                                Log.e(TAG, "onNext: " + login.getHeadCompany());
                                Log.e(TAG, "onNext: " + s.toString());

                            }
                        });
            }
        });
        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscriber = new Subscriber<byte[]>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "download onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "download onError: ");
                    }

                    @Override
                    public void onNext(byte[] bytes) {
                        Log.e(TAG, "download onNext: ");
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        imageview.setImageBitmap(bitmap);
                    }
                };

                new DownLoadUtils()
                        .getBytes("http://i.pbase.com/o6/92/229792/1/80199697.uAs58yHk.50pxCross_of_the_Knights_Templar_svg.png")
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(subscriber);
            }
        });
        btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter();
            }
        });
        btn_rxbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RxJavaActivity.this, RxBusActivity.class));
            }
        });
        btn_interval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createObservablebtn_interval();
            }
        });

        btn_flatMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createObservablebtn_flatMap();
            }
        });
        btn_drawable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createObservableDrawable();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createObservable();
            }
        });

        RxView.clicks(button).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {

            }
        });

        /**
         * 获取RxBus传输的消息
         */
        getRxBus();

//        RxView.clicks(button)
//                .throttleFirst(2, TimeUnit.SECONDS)
//                .subscribe(new Consumer<Object>() {
//                    @Override
//                    public void accept(Object o) throws Exception {
//
//                        Toast.makeText(RxJavaActivity.this, "4444", Toast.LENGTH_SHORT).show();
//                    }
//                });

        final int[] i = {1};
        RxView.clicks(button).throttleFirst(3000, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                i[0]++;
                Toast.makeText(RxJavaActivity.this, "点击了button" + i[0], Toast.LENGTH_SHORT).show();
            }
        });


        //被观察者订阅观察者
        new Thread(new Runnable() {
            @Override
            public void run() {
//                Log.e(TAG, "run: "+Thread.currentThread().getName() );
//                Toast.makeText(RxJavaActivity.this, "111", Toast.LENGTH_SHORT).show();

                obserable.subscribe(sub);
            }
        }).start();

        //解决订阅关系
        //  sub.unsubscribe();
    }

    private void okGoRequest() {
        OkGo.<LoginSucess>get("http://36.7.144.130:6020/api/phone/logins")
                .params("loginName", "222222")
                .params("password", "888888")
                .execute(new MyCallBack<LoginSucess>(this) {

                    @Override
                    public void onError(Response<LoginSucess> response) {
                        super.onError(response);
                    }

                    @Override
                    public void onSuccess(Response<LoginSucess> response) {
                        LogUtils.eTag("lyl1", "登录成功");
                    }

                    @Override
                    public LoginSucess convertResponse(okhttp3.Response response) throws Throwable {
                        return null;
                    }
                });
    }

    /**
     * retrofit测试登录
     */
    private void login() {

        NetWorkService.getLogin()
                .getLogin("111111", Utils.getMD5("888888"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginSuccess>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(LoginSuccess s) {
                        Log.e(TAG, "onNext: " + s.getMessage());
                    }
                });
    }

    /**
     * merge
     * 组合
     */
    private void merge() {
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        List<String> list2 = new ArrayList<>();
        list2.add("4");
        list2.add("5");
        list2.add("6");

        Observable<String> obser1 = Observable.from(list1);
        Observable<String> obser2 = Observable.from(list2);

        Observable.merge(obser1, obser2).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s);
            }
        });
    }

    /**
     * cast
     * 类型转换异常，cast操作符就是将不同数据类型转换成指定类型.
     */
    private void cast() {
        Observable.range(0, 10).cast(String.class).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s);
            }
        });
    }

    /**
     * window
     */
    private void window() {
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(10)
                .window(3, TimeUnit.SECONDS)
                .subscribe(new Subscriber<Observable<Long>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Observable<Long> integerObservable) {
                        Log.e(TAG, "onNext: ");
                        integerObservable.subscribe(new Action1<Long>() {
                            @Override
                            public void call(Long integer) {
                                Log.e(TAG, "call: " + integer);
                            }
                        });
                    }
                });
    }

    /**
     * buffer
     * RxJava的buffer()方法将Observable转换成一个新的Observable，它以列表的形式发射值，而不是单个的发射。
     */
    private void buffer() {
        Observable.range(0, 10).buffer(3, 3).subscribe(new Subscriber<List<Integer>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Integer> integers) {

                Log.e(TAG, "onNext: " + integers);
            }
        });
    }

    /**
     * groupby
     * 分组
     */
    private void groupBy() {
        Observable.range(0, 10).groupBy(new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                return 2;
            }
        }).subscribe(new Subscriber<GroupedObservable<Integer, Integer>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(GroupedObservable<Integer, Integer> integerIntegerGroupedObservable) {
                integerIntegerGroupedObservable.subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e(TAG, integerIntegerGroupedObservable.getKey() + "onNext: " + integer);
                    }
                });
            }
        });
    }

    /**
     * 拼接上一个结果
     * call 回掉第一个参数是上次的结算结果，第二个参数是当此的源observable的输入值
     */
    private void scan() {
        Observable.just("1", "2", "3", "4", "5").scan(new Func2<String, String, String>() {
            @Override
            public String call(String s, String s2) {
                //s：上一个结果  s2：这一次发射的值
                return s + s2;
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s);
            }
        });
    }

    /**
     * flatMapIterable
     * 和flatmap很像
     * 唯一不同是它把源平铺成可迭代的值(Iterables)，而不是Observable。
     */
    private void flatMapIterable() {
        Observable.just("1", "2", "3", "4", "5").flatMapIterable(new Func1<String, Iterable<Student>>() {
            @Override
            public Iterable<Student> call(String s) {
                return getStudentList(s);
            }
        }).subscribe(new Subscriber<Student>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Student s) {
                Log.e(TAG, "onNext: " + s.getName());
            }
        });
    }

    // 针对不同的s发射不同的Observable序列
    private ArrayList<Student> getStudentList(final String s) {
        ArrayList<Student> lists_0 = new ArrayList<>();
        lists_0.add(new Student(s));
        lists_0.add(new Student(s));
        lists_0.add(new Student(s));

        return lists_0;
    }

    /**
     * concatmap
     * concatMap和flatMap最大的区别是concatMap发射的数据集是有序的，flatMap发射的数据集是无序的。
     * 延时500ms就会发现区别
     */
    private void concatMap() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("num:" + i);
        }
        Observable.from(list).concatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                return Observable.just(s).delay(500, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s);
            }
        });
    }

    /**
     * flatMap
     */
    private void flatMap() {
        /**
         * //map和flatmap进行对比
         * map返回的是结果集，flatmap返回的是包含结果集的Observable（返回结果不同）
         * map适用于一对一转换，当然也可以配合flatmap进行适用
         * flatmap适用于一对多，多对多的场景
         */
//        Observable.just("1", "2", "3", "4", "5").map(new Func1<String, Observable<String>>() {
//            @Override
//            public Observable<String> call(String s) {
//                return Observable.just(s);
//            }
//        }).subscribe(new Subscriber<Observable<String>>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(Observable<String> stringObservable) {
//                Log.e(TAG, "onNext: " + stringObservable);
//            }
//        });

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("num:" + i);
        }
        Observable.from(list).flatMap(new Func1<String, Observable<List<String>>>() {
            @Override
            public Observable<List<String>> call(String s) {
                List<String> list = new ArrayList<String>();
                list.add(s);
                return Observable.just(list)
                        .delay(500, TimeUnit.MILLISECONDS);//延时500ms发送
            }
        }).subscribe(new Subscriber<List<String>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<String> stringList) {
                // Log.e(TAG, "onNext size: " + stringList.size());
                for (int i = 0; i < stringList.size(); i++) {
                    Log.e(TAG, "onNext: " + stringList.get(i));
                }
            }
        });
    }

    /**
     * 这个元素在一个我们指定的时间间隔后面没有跟着其它元素
     */
    private void debounce() {

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                for (int i = 0; i < 10; i++) {
                    subscriber.onNext("第" + i + "个");
                    try {
                        Thread.sleep(i * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).debounce(2, TimeUnit.SECONDS).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s);
            }
        });

//        Observable.just("1", "2", "3", "4", "5")
//                .debounce(2, TimeUnit.SECONDS)
//                .subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.e(TAG, "onCompleted: ");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        Log.e(TAG, "onNext: " + s);
//                    }
//                });
    }

    /**
     * 它的作用是在我们设定的时间间隔内没有发射值时发射一个错误
     */
    private void timeout() {


        Observable.interval(3, TimeUnit.SECONDS)
                .timeout(2, TimeUnit.SECONDS)
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: 指定时间内没有发射任何值");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.e(TAG, "onNext: " + aLong);
                    }
                });
    }

    /**
     * 这个新序列在固定的间隔发射序列中的最近的一个元素
     */
    private void sample() {
        Observable.interval(1, TimeUnit.SECONDS).sample(3, TimeUnit.SECONDS).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                Log.e(TAG, "onNext: " + aLong);
            }
        });

//        Observable.just("1","2","3","4","5")
//                .sample(30,TimeUnit.SECONDS)
//                .subscribe(new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.e(TAG, "onNext: "+s);
//            }
//        });
    }

    /**
     * 用elementAt()发射序列的第n个元素，然后结束。
     */
    private void elementAt() {
        Observable.just("1", "2", "3", "4", "5")
                .elementAt(2)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, "onNext: " + s);
                    }
                });
    }

    private void skip() {
        Observable.just("1", "2", "3", "4", "5").skip(2).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s);
            }
        });
    }

    private void rxSkipLast() {
        Observable.just("1", "2", "3", "4", "5").skipLast(2).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s);
            }
        });
    }

    /**
     * last操作符 获取最后一个数据   或者    满足指定条件的最后一个数据:
     */
    private void rxLast() {
        Observable.just("1", "1", "3", "1", "2").lastOrDefault("1").subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s);
            }
        });
    }

    /**
     * 创建只发射第一个的操作符
     * first操作符是获取源Observable产生的   第一个数据   或者  满足指定条件的第一个数据
     */
    private void rxFirst() {
        Observable.range(1, 10).first(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer > 5;
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG, "onNext: " + integer);
            }
        });

//        Observable.just("1","1","3","1","2").first(new Func1<String, Boolean>() {
//            @Override
//            public Boolean call(String s) {
//                return s.equals("3");
//            }
//        }).subscribe(new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.e(TAG, "onNext: "+s );
//            }
//        });
    }

    /**
     * DistinctUntilChanged
     * 它忽略所有重复值 ，直到有新的值出现，但是不排除后面的重复值
     * 输出结果 1,3,1,2
     */
    private void rxDistinctUntilChanged() {
        Observable.just("1", "1", "3", "1", "2")
                .distinctUntilChanged(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s;
                    }
                }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s);
            }
        });
    }

    /**
     * 我们可以对序列使用distinct()方法来去除重复元素
     * 重复的数据不发射
     * distinct()位置不同，处理的结果不同
     */
    private void rxDistinct() {
        Observable.just("1", "2", "3", "1", "2")
                .repeat(3)
                .distinct(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s;
                    }
                }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s);
            }
        });
    }

    /**
     * 如果我们只想要最后的N个元素，使用takeLast()。
     * <p>
     * 1、发射just里面的字符串
     * 2、取最后的三位数字
     * 3、过滤掉等于4的数字
     */
    private void rxTakeLast() {
        Observable.just("1", "2", "3", "4", "5", "6")
                .takeLast(3)
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return Integer.parseInt(s) != 4;
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, "onNext: " + s);
                    }
                });
    }

    /**
     * timer操作符
     * 如果你想在一定的时间间隔后发射一个item
     */
    private void rxTimer() {

        Observable.timer(2, TimeUnit.SECONDS).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                Log.e(TAG, "onNext: " + aLong);
                Observable.just("111")
                        .filter(new Func1<String, Boolean>() {
                            @Override
                            public Boolean call(String s) {
                                return s.contains("1");
                            }
                        }).map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + "转化后的s";
                    }
                }).subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, "onNext: " + s);
                    }
                });
            }
        });
    }

    /**
     * range()方法需要两个参数，第一个是开始的点，第二个是我们想发射的元素的数量。
     * range 操作符的使用方法
     */
    private void rxRange() {

        Observable.range(10, 3).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG, "onNext: " + integer);
            }
        });
    }

    /**
     * repet
     * 操作符的使用方法
     */
    private void rxRepet() {
        Observable.just("1", "2").repeat(2).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s);
            }
        });

    }

    /**
     * just 操作符
     * just()创建器能接受1到9个参数
     */
    private void rxJust() {
        String[] strings1 = {"1", "2"};
        String[] strings2 = {"3", "4"};


        //发射数组
        Observable.just(strings1, strings2).subscribe(new Action1<String[]>() {
            @Override
            public void call(String[] strings) {
                for (int i = 0; i < strings.length; i++) {
                    Log.e(TAG, "call: " + strings[i]);
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e(TAG, "throwable: ");
            }
        }, new Action0() {
            @Override
            public void call() {
                Log.e(TAG, "onComplete ");
            }
        });
        //发射字符串
        Observable.just("hello world").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e(TAG, "call: " + s);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e(TAG, "throwable: ");
            }
        }, new Action0() {
            @Override
            public void call() {
                Log.e(TAG, "onComplete ");
            }
        });
    }

    /**
     * from操作符的使用
     * <p>
     * from和just的区别：
     * 生成的Observable不一样from是把数组或者列表展开,just就是原来的数据。
     */
    private void rxFrom() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        Observable.from(list)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, "onNext: " + s);
                    }
                });

    }

    /**
     * take操作符，指定发送几次事件
     * 如果我们只需要序列的前三个元素该怎么做
     */
    private void rxTake() {
        Observable.from(new String[]{"1", "2", "3", "4"})
                .take(3)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, "onNext: " + s);
                    }
                });
    }

    private void sendCheckCode() {
        final int count = 3;
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(count + 1)//take()方法使用一个N整型变量，使序列只发射最开始的N个元素，然后就结束。
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return count - aLong;
                    }
                }).doOnSubscribe(new Action0() {//doOnSubscribe 运行在subscribeOn的指定的线程中
            @Override
            public void call() {
                btn_send.setEnabled(false);
                btn_send.setTextColor(Color.BLACK);
                Log.e(TAG, "doOnSubscribe: " + Thread.currentThread().getName());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())// 指定 subscribe() 发生在io线程
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {
                        btn_send.setEnabled(true);
                        btn_send.setTextColor(Color.RED);
                        btn_send.setText("发送验证码");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + Thread.currentThread().getName());
                        Log.e(TAG, "onError: ");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.e(TAG, "onNext: " + Thread.currentThread().getName());
                        btn_send.setText("剩余" + aLong + "秒");

                    }
                });
    }


    /**
     * map
     * 转化  将string类型的path转化为bitmap类型
     */
    public void map() {
        //imageview.setImageBitmap(BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/1.jpg"));
//        Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                ///storage/emulated/0/1.jpg
//                Log.e(TAG, "call: " + Environment.getExternalStorageDirectory() + "/1.jpg");
//                subscriber.onNext(Environment.getExternalStorageDirectory() + "/1.jpg");
//            }
//        }).map(new Func1<String, Bitmap>() {
//            @Override
//            public Bitmap call(String s) {
//                return BitmapFactory.decodeFile(s);
//            }
//        }).subscribe(new Action1<Bitmap>() {
//            @Override
//            public void call(Bitmap bitmap) {
//                imageview.setImageBitmap(bitmap);
//            }
//        });

        Observable.just("1", "2", "3", "4").map(new Func1<String, List<String>>() {
            @Override
            public List<String> call(String s) {
                List<String> list = new ArrayList<String>();
                list.add(s);
                return list;
            }
        }).subscribe(new Subscriber<List<String>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<String> stringList) {
                Log.e(TAG, "onNext: size  " + stringList.size());
                for (int i = 0; i < stringList.size(); i++) {
                    Log.e(TAG, "onNext: " + stringList.get(i));
                }

            }
        });

    }


    //操作符defer的使用方法
    private void defer() {

        String finalA = "1";
        String finalA1 = finalA;
        Observable<String> obser = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.just(finalA1);
            }
        });
        finalA = "2";
        obser.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

                Log.e(TAG, "onNext: " + s);
            }
        });


    }

    private void filter() {
        String[] strings = {"1", "2", "3", "4"};

//        Observable.from(strings).filter(new Func1<String, Boolean>() {
//            @Override
//            public Boolean call(String s) {
//
//                return !s.startsWith("1");
//            }
//        }).subscribe(new Observer<String>() {
//            @Override
//            public void onCompleted() {
//                Log.e(TAG, "onCompleted: ");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.e(TAG, "onError: ");
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.e(TAG, "onNext: " + s);
//            }
//        });

        //一旦调用了subscribe()方法，就会自动触发call方法
//        Integer[] int1 = {1, 2, 3, 4, 5, 6};
//        Integer[] int2 = {789, 456, 444};
//        String[] strings1 = {"1", "2", "3"};
//        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//                .filter(new Func1<Integer, Boolean>() {
//                    @Override
//                    public Boolean call(Integer integer) {
//                        return integer > 2;
//                    }
//                }).subscribe(new Observer<Integer>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                Log.e(TAG, "onNext: " + integer);
//            }
//        });


        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("11");
            }
        }).filter(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                Log.e(TAG, "filter: ");
                return true;
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: ");
            }
        });


//    Observable.from(int1).filter(new Func1<Integer, Boolean>() {
//        @Override
//        public Boolean call(Integer integer) {
//            return integer>1;
//        }
//    }).subscribe(new Action1<Integer>() {
//        @Override
//        public void call(Integer integer) {
//            Log.e(TAG, "call: "+integer );
//        }
//    });

//        Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                String[] in = {"1", "2", "3"};
//                for (int i = 0; i < in.length; i++) {
//                    if (!in[i].startsWith("1")) {
//                        subscriber.onNext(in[i]);
//                    }
//                }
//            }
//        }).subscribe(new Observer<String>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.e(TAG, "onNext: " + s);
//            }
//        });

    }

    private void getRxBus() {
        subscr = RxBus.getInstance().toObserverable(String.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        if (s.equals("rxbus")) {
                            Log.e(TAG, "传过来的消息" + s);
                            Toast.makeText(RxJavaActivity.this, "传过来的消息" + s, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RxJavaActivity.this, "传过来的是别的消息" + s, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    Action1<Long> timerTisker = new Action1<Long>() {
        @Override
        public void call(Long aLong) {

        }
    };

    Subscriber<Long> timer = new Subscriber<Long>() {
        @Override
        public void onCompleted() {
            Log.e(TAG, "onCompleted: ");
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError: ");

        }

        @Override
        public void onNext(Long aLong) {
            Toast.makeText(RxJavaActivity.this, "每隔2s弹出一次", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * interval 操作符的使用
     */
    private void createObservablebtn_interval() {
//        RxTextView.textChanges(new EditText(this))
//                .debounce(400, TimeUnit.MILLISECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .filter(new Func1<CharSequence, Boolean>() {
//                    @Override
//                    public Boolean call(CharSequence charSequence) {
//                        return charSequence.toString().length() > 0;
//                    }
//                })
//                .flatMap(new Func1<CharSequence, Observable<List<String>>>() {
//
//                    @Override
//                    public Observable<List<String>> call(CharSequence charSequence) {
//                        return null;
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Action1<List<String>>() {
//                    @Override
//                    public void call(List<String> stringList) {
//
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//
//                    }
//                });


        Observable.interval(0, 2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(timer);
//        PublishSubject<String> stringPublishSubject = PublishSubject.create();
//        Subscription subscriptionPrint = stringPublishSubject.subscribe(new Observer<String>() {
//            @Override
//            public void onCompleted() {
//                System.out.println("Observable completed");
//            }
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("Oh no! Something wrong happened!");
//            }
//            @Override
//            public void onNext(String message) {
//                System.out.println(message); }
//        });
//
//        PublishSubject<String> stringpublish = PublishSubject.create();
//        stringpublish.subscribe(new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//                Log.e(TAG, "onCompleted: ");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.e(TAG, "onError: ");
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.e(TAG, "onNext: " + s);
//            }
//        });
//
//        //使用publishSubject
//        PublishSubject.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("ttt");
//                subscriber.onCompleted();
//                // stringpublish.onNext("tttt");
//                //  stringpublish.onCompleted();
//
//            }
//        }).doOnCompleted(new Action0() {
//            @Override
//            public void call() {
//                stringpublish.onNext("sssss");
//            }
//        }).subscribe();

//        Observable.empty();
//        Observable.never();
//        Observable.just("1", "2", "3").subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                Toast.makeText(RxJavaActivity.this, s, Toast.LENGTH_SHORT).show();
//            }
//        });


//        Observable.from(new String[]{"1", "2", "3"})
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                Toast.makeText(RxJavaActivity.this, s, Toast.LENGTH_SHORT).show();
//            }
//        });

    }


    //打印每个学生的姓名  每个学生选择的课程
    private void createObservablebtn_flatMap() {

        ArrayList<Course> co = new ArrayList<>();
        co.add(new Course("语文"));
        co.add(new Course("数学"));
        co.add(new Course("英语"));
        co.add(new Course("历史"));
        final Student stu = new Student("11", 11, co);
        Student stu1 = new Student("22", 22, co);
        Student stu2 = new Student("33", 33, co);
        final Student[] studets = new Student[]{stu, stu1, stu2};

//获取每个学生选择的课程  使用from的方法
//        Observable.from(studets).subscribe(new Action1<Student>() {
//            @Override
//            public void call(Student student) {
//                for (int i = 0; i < student.getCourses().size(); i++) {
//                    Course co = student.getCourses().get(i);
//                    Log.e(TAG, "call: "+ co.getLesson());
//                }
//            }
//        });

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < studets.length; i++) {
//                    final List<Course> c = studets[i].getCourses();
//                    for (int j = 0; j < c.size(); j++) {
//                        final int finalJ = j;
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(RxJavaActivity.this, "createObservablebtn_flatMap: " + c.get(finalJ).getLesson(), Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                        //Log.e(TAG, "createObservablebtn_flatMap: " + c.get(j).getLesson());
//                    }
//                }
//            }
//        }).start();


        //获取每个学生选择的课程  不用for循环  使用flatMap
        Observable.from(studets).flatMap(new Func1<Student, Observable<Course>>() {
            @Override
            public Observable<Course> call(Student student) {
                return Observable.from(student.getCourses());
            }
        }).subscribe(new Action1<Course>() {
            @Override
            public void call(Course course) {
                Log.e(TAG, "course: " + course.getLesson());
            }
        });
//获取每个学生的姓名  使用map的方法
//        Observable.from(studets).map(new Func1<Student, String>() {
//            @Override
//            public String call(Student student) {
//                return student.getName();
//            }
//        }).subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                Log.e(TAG, "call: " + s);
//            }
//        });

    }

    @SuppressWarnings("AlibabaAvoidCommentBehindStatement")
    private void createObservableDrawable() {
        Observable observable = Observable.interval(1, 1, TimeUnit.MILLISECONDS);
        observable.subscribe(new Action1() {
            @Override
            public void call(Object o) {

            }
        });

//        Observable
//                .just(getResources().getDrawable(R.mipmap.ic_launcher))
//                .subscribeOn(Schedulers.io())// 指定 subscribe() 发生在 IO 线程
//                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
//                .subscribe(new Subscriber<Drawable>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Drawable drawable) {
//                        imageview.setImageDrawable(drawable);
//                    }
//                });

        //可以看到，map() 方法将参数中的 String 对象转换成一个 Bitmap 对象后返回，
        Observable
                .just(Environment.getExternalStorageDirectory() + "/1.jpg")
                .map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String s) {
                        return getBitmap(s);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        imageview.setImageBitmap(bitmap);
                    }
                });

    }

    public Bitmap getBitmap(String path) {
        return BitmapFactory.decodeFile(path);
    }


    public void createObservable() {

        //实现了Observer的抽象类
        Subscriber<String> sub = new Subscriber<String>() {

            @Override
            public void onStart() {
                super.onStart();
                Log.e(TAG, "onStart: ");
            }

            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

                Log.e(TAG, "onNext: " + s);
            }
        };

        //onNext
        Action1<String> ac = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e(TAG, "onNext: " + s);
            }
        };

        //onError
        Action1<Throwable> action = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e(TAG, "Throwable: ");
            }
        };

        //onComplete
        Action0 comac = new Action0() {
            @Override
            public void call() {
                Log.e(TAG, "onCompleted: ");
            }
        };

        //多个时间以数组的形式触发
        Observable.from(new String[]{"1", "2", "3", "4"}).observeOn(AndroidSchedulers.mainThread()).subscribe(ac, action, comac);
        //create单个事件触发
//        Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("111");
//            }
//        }).subscribe(sub);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sub != null) {
            sub.unsubscribe();
        }
        //解除订阅
        if (timer != null) {
            timer.unsubscribe();
        }
        if (subscr != null) {
            subscr.unsubscribe();
        }
        if (subscriber != null) {
            subscriber.unsubscribe();
        }
    }

    /**
     * Subscriber是实现了Observer的抽象类
     */
    Subscriber<String> sub = new Subscriber<String>() {
        //1. onStart(): 这是 Subscriber 增加的方法,可以用来进行初始化操作、比如网络请求时显示进度条
        @Override
        public void onStart() {
            super.onStart();
            Log.e(TAG, "onStart: ");
        }

        @Override
        public void onCompleted() {
            Log.e(TAG, "onCompleted: ");
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError: " + e.getMessage());
        }

        @Override
        public void onNext(String s) {
//            Toast.makeText(RxJavaActivity.this, "222", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "onNext: " + s);
            Log.e(TAG, "运行在: " + Thread.currentThread().getName());
        }
    };
    //rxJava 2.0
//    io.reactivex.Observer<String> obser = new io.reactivex.Observer<String>() {
//        @Override
//        public void onSubscribe(Disposable d) {
//            Log.e(TAG, "onSubscribe: ");
//        }
//
//        @Override
//        public void onNext(String value) {
//            Log.e(TAG, "onNext: ");
//        }
//
//        @Override
//        public void onError(Throwable e) {
//            Log.e(TAG, "onError: ");
//        }
//
//        @Override
//        public void onComplete() {
//            Log.e(TAG, "onComplete: ");
//        }
//    };

    //rxjava 1.5
    rx.Observer<String> obser1 = new rx.Observer<String>() {


        @Override
        public void onCompleted() {
            Log.e(TAG, "onCompleted: ");
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError: ");
        }

        @Override
        public void onNext(String s) {
            Log.e(TAG, "onNext: " + s);
            Toast.makeText(RxJavaActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    };

    //快速创建被观察者
    Observable ob = Observable.just("111", "222", "333");

    Action1<String> action = new Action1<String>() {
        @Override
        public void call(String s) {

            Log.e(TAG, "call: " + s);
        }
    };


    Observable.OnSubscribe<String> onsub = new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            if (!subscriber.isUnsubscribed()) {
                Log.e(TAG, "call: ");
                subscriber.onNext("ssss");
                subscriber.onNext("111");
                subscriber.onNext("222");
                subscriber.onNext("333");
                subscriber.onCompleted();
            }

        }
    };


    //rxjava 1.5
    rx.Observable obserable = Observable.create(onsub).observeOn(AndroidSchedulers.mainThread());//指定运行在主线程中


}
