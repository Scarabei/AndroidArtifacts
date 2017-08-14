package android.support.v4.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Handler.Callback;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.util.Pools.SynchronizedPool;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.concurrent.ArrayBlockingQueue;

public final class AsyncLayoutInflater {
   private static final String TAG = "AsyncLayoutInflater";
   LayoutInflater mInflater;
   Handler mHandler;
   AsyncLayoutInflater.InflateThread mInflateThread;
   private Callback mHandlerCallback = new Callback() {
      public boolean handleMessage(Message msg) {
         AsyncLayoutInflater.InflateRequest request = (AsyncLayoutInflater.InflateRequest)msg.obj;
         if (request.view == null) {
            request.view = AsyncLayoutInflater.this.mInflater.inflate(request.resid, request.parent, false);
         }

         request.callback.onInflateFinished(request.view, request.resid, request.parent);
         AsyncLayoutInflater.this.mInflateThread.releaseRequest(request);
         return true;
      }
   };

   public AsyncLayoutInflater(@NonNull Context context) {
      this.mInflater = new AsyncLayoutInflater.BasicInflater(context);
      this.mHandler = new Handler(this.mHandlerCallback);
      this.mInflateThread = AsyncLayoutInflater.InflateThread.sInstance;
   }

   @UiThread
   public void inflate(@LayoutRes int resid, @Nullable ViewGroup parent, @NonNull AsyncLayoutInflater.OnInflateFinishedListener callback) {
      if (callback == null) {
         throw new NullPointerException("callback argument may not be null!");
      } else {
         AsyncLayoutInflater.InflateRequest request = this.mInflateThread.obtainRequest();
         request.inflater = this;
         request.resid = resid;
         request.parent = parent;
         request.callback = callback;
         this.mInflateThread.enqueue(request);
      }
   }

   private static class InflateThread extends Thread {
      private static final AsyncLayoutInflater.InflateThread sInstance = new AsyncLayoutInflater.InflateThread();
      private ArrayBlockingQueue mQueue = new ArrayBlockingQueue(10);
      private SynchronizedPool mRequestPool = new SynchronizedPool(10);

      public static AsyncLayoutInflater.InflateThread getInstance() {
         return sInstance;
      }

      public void runInner() {
         AsyncLayoutInflater.InflateRequest request;
         try {
            request = (AsyncLayoutInflater.InflateRequest)this.mQueue.take();
         } catch (InterruptedException var4) {
            Log.w("AsyncLayoutInflater", var4);
            return;
         }

         try {
            request.view = request.inflater.mInflater.inflate(request.resid, request.parent, false);
         } catch (RuntimeException var3) {
            Log.w("AsyncLayoutInflater", "Failed to inflate resource in the background! Retrying on the UI thread", var3);
         }

         Message.obtain(request.inflater.mHandler, 0, request).sendToTarget();
      }

      public void run() {
         while(true) {
            this.runInner();
         }
      }

      public AsyncLayoutInflater.InflateRequest obtainRequest() {
         AsyncLayoutInflater.InflateRequest obj = (AsyncLayoutInflater.InflateRequest)this.mRequestPool.acquire();
         if (obj == null) {
            obj = new AsyncLayoutInflater.InflateRequest();
         }

         return obj;
      }

      public void releaseRequest(AsyncLayoutInflater.InflateRequest obj) {
         obj.callback = null;
         obj.inflater = null;
         obj.parent = null;
         obj.resid = 0;
         obj.view = null;
         this.mRequestPool.release(obj);
      }

      public void enqueue(AsyncLayoutInflater.InflateRequest request) {
         try {
            this.mQueue.put(request);
         } catch (InterruptedException var3) {
            throw new RuntimeException("Failed to enqueue async inflate request", var3);
         }
      }

      static {
         sInstance.start();
      }
   }

   private static class BasicInflater extends LayoutInflater {
      private static final String[] sClassPrefixList = new String[]{"android.widget.", "android.webkit.", "android.app."};

      BasicInflater(Context context) {
         super(context);
      }

      public LayoutInflater cloneInContext(Context newContext) {
         return new AsyncLayoutInflater.BasicInflater(newContext);
      }

      protected View onCreateView(String name, AttributeSet attrs) throws ClassNotFoundException {
         String[] var3 = sClassPrefixList;
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            String prefix = var3[var5];

            try {
               View view = this.createView(name, prefix, attrs);
               if (view != null) {
                  return view;
               }
            } catch (ClassNotFoundException var8) {
               ;
            }
         }

         return super.onCreateView(name, attrs);
      }
   }

   private static class InflateRequest {
      AsyncLayoutInflater inflater;
      ViewGroup parent;
      int resid;
      View view;
      AsyncLayoutInflater.OnInflateFinishedListener callback;
   }

   public interface OnInflateFinishedListener {
      void onInflateFinished(View var1, int var2, ViewGroup var3);
   }
}
