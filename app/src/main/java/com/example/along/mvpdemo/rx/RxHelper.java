package com.example.along.mvpdemo.rx;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * PackageName: com.smartretax.srt.utils <br/>
 * <br/>
 * ClassName: RxHelper <br/>
 * <br/>
 * Directions: <br/>
 * <br/>
 * Create at 2017/9/13-下午2:37
 */
public class RxHelper {

  /** 封装线程 */
  public static <T> ObservableTransformer<T, T> io_main() {
    return new ObservableTransformer<T, T>() {
      @Override
      public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
        return upstream.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
      }
    };
  }
}
