package com.example.lyl.myapplication.rxandroid;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;

import com.example.lyl.myapplication.BaseActivity;
import com.example.lyl.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author lyl
 * @date 2017/11/16.
 */

public class RxAndroidActivity extends BaseActivity {

    private RecyclerView recyclerView;

    private SwipeRefreshLayout swipe;

    private List<Appinfo> appinfoslist = new ArrayList<>();

    private MyAdapter myAdapter;
    private LinearLayoutManager ll;
    private boolean isLoadMore = false;
    private ProgressDialog p;
    private Subscriber<Appinfo> sub;
    private Subscriber<Long> subInt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxandroid);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swiper);

        initView();


        // lunxun();
        for (int i = 0; i <10 ; i++) {
            appinfoslist.add(new Appinfo("app" + i, i, i + 100));
        }
        appinfoslist.add(new Appinfo("cnn" + 10, 10, 10 + 100));
        justAppinfos();
//        getApps();
       // refreshList();
        //loadList();


        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.e(TAG, "onRefresh: ");
                appinfoslist.clear();
                refreshList();
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if ((!isLoadMore) && (dy > 0)) {//没有在加载，向上滑动
                    int visible = ll.getChildCount();//可见
                    int first = ll.findFirstCompletelyVisibleItemPosition();//第一个完整可见的
                    int total = ll.getItemCount();//总数
                    int nowLast = visible + first + 2;
                    if (nowLast > total) {
                        isLoadMore = true;
                        refreshList();
                    }
                }
            }
        });
    }

    private void lunxun() {
        subInt = new Subscriber<Long>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long s) {
                Log.e(TAG, "onNext: " + s);

            }
        };
        Observable.interval(2, 2, TimeUnit.SECONDS).subscribe(subInt);
    }

    private void initView() {
        ll = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(ll);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        myAdapter = new MyAdapter(appinfoslist);
        recyclerView.setAdapter(myAdapter);
    }

    private List<Appinfo> lists = new ArrayList<>();

    public void loadList() {
        Observable.from(appinfoslist).subscribe(new Observer<Appinfo>() {
            @Override
            public void onCompleted() {
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Appinfo appinfo) {
                appinfoslist.add(appinfo);
            }
        });

    }


    public void justAppinfos() {
//        Observable.just(new Appinfo("2",2,2),new Appinfo("3",3,3),
//                new Appinfo("4",4,4),new Appinfo("5",5,5)).repeat(3)
//                .subscribe(new Action1<Appinfo>() {
//            @Override
//            public void call(Appinfo appinfo) {
//                appinfoslist.add(appinfo);
//            }
//        });

//        Observable.interval(2, 2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io()).subscribe(new Action1<Long>() {
//            @Override
//            public void call(Long aLong) {
//                refreshList();
//            }
//        });

        Observable.from(appinfoslist).filter(new Func1<Appinfo, Boolean>() {
            @Override
            public Boolean call(Appinfo appinfo) {
                return appinfo.getAppName().startsWith("a");
            }
        }).subscribe(new Observer<Appinfo>() {
            @Override
            public void onCompleted() {

                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Appinfo appinfo) {
                //appinfoslist.clear();
                appinfoslist.add(appinfo);
            }
        });
    }

    //刷新列表
    public void refreshList() {
//        getApps()
//                .subscribe(new Observer<Appinfo>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.e(TAG, "onCompleted: ");
//                        appinfoslist.add(new Appinfo("新增的", 1, 2));
//                        myAdapter.notifyDataSetChanged();
//                        swipe.setRefreshing(false);
//                        isLoadMore=false;
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        swipe.setRefreshing(false);
//                    }
//
//                    @Override
//                    public void onNext(Appinfo appinfo) {
//                        Log.e(TAG, "onNext: ");
//                        appinfoslist.add(appinfo);
//                    }
//                });


        if (sub == null) {
            sub = new Subscriber<Appinfo>() {
                @Override
                public void onStart() {
                    super.onStart();
                    Log.e(TAG, "onStart: ");
                    //  initDialog();
                }

                @Override
                public void onCompleted() {
                    Log.e(TAG, "onCompleted: ");
                    appinfoslist.add(new Appinfo("新增的", 1, 2));
                    myAdapter.notifyDataSetChanged();
                    swipe.setRefreshing(false);
                    isLoadMore = false;
                    dismis();
                }

                @Override
                public void onError(Throwable e) {
                    swipe.setRefreshing(false);
                }

                @Override
                public void onNext(Appinfo appinfo) {
                    Log.e(TAG, "onNext: ");
                    appinfoslist.add(appinfo);
                }
            };
        }

        getApps().filter(new Func1<Appinfo, Boolean>() {
            @Override
            public Boolean call(Appinfo appinfo) {
                if (appinfo.getAppName().startsWith("a")) {
                    return true;
                }
                return false;
            }
        }).subscribe(sub);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sub != null) {
            Log.e(TAG, "onDestroy:sub ");
            sub.unsubscribe();
            sub = null;
        }
        if (subInt != null) {
            subInt.unsubscribe();
        }
    }

    private void dismis() {
        if (p != null) {
            p.dismiss();
        }
    }
//
//    private void initDialog() {
//        p = new ProgressDialog(this);
//        p.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        p.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        p.setMessage("加载中");
//        p.show();
//    }


    private Observable<Appinfo> getApps() {

        return Observable.create(new Observable.OnSubscribe<Appinfo>() {
            @Override
            public void call(Subscriber<? super Appinfo> subscriber) {
                for (int i = 0; i < 5; i++) {
                    subscriber.onNext(new Appinfo("app" + i, i, i + 100));
                }
                subscriber.onCompleted();
                if (!subscriber.isUnsubscribed()) {
                    Log.e(TAG, "isUnsubscribed: ");
                }
            }
        });
    }
}
