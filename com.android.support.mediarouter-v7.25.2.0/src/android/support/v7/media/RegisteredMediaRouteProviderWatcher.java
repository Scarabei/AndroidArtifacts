package android.support.v7.media;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Handler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

final class RegisteredMediaRouteProviderWatcher {
   private final Context mContext;
   private final RegisteredMediaRouteProviderWatcher.Callback mCallback;
   private final Handler mHandler;
   private final PackageManager mPackageManager;
   private final ArrayList mProviders = new ArrayList();
   private boolean mRunning;
   private final BroadcastReceiver mScanPackagesReceiver = new BroadcastReceiver() {
      public void onReceive(Context context, Intent intent) {
         RegisteredMediaRouteProviderWatcher.this.scanPackages();
      }
   };
   private final Runnable mScanPackagesRunnable = new Runnable() {
      public void run() {
         RegisteredMediaRouteProviderWatcher.this.scanPackages();
      }
   };

   public RegisteredMediaRouteProviderWatcher(Context context, RegisteredMediaRouteProviderWatcher.Callback callback) {
      this.mContext = context;
      this.mCallback = callback;
      this.mHandler = new Handler();
      this.mPackageManager = context.getPackageManager();
   }

   public void start() {
      if (!this.mRunning) {
         this.mRunning = true;
         IntentFilter filter = new IntentFilter();
         filter.addAction("android.intent.action.PACKAGE_ADDED");
         filter.addAction("android.intent.action.PACKAGE_REMOVED");
         filter.addAction("android.intent.action.PACKAGE_CHANGED");
         filter.addAction("android.intent.action.PACKAGE_REPLACED");
         filter.addAction("android.intent.action.PACKAGE_RESTARTED");
         filter.addDataScheme("package");
         this.mContext.registerReceiver(this.mScanPackagesReceiver, filter, (String)null, this.mHandler);
         this.mHandler.post(this.mScanPackagesRunnable);
      }

   }

   public void stop() {
      if (this.mRunning) {
         this.mRunning = false;
         this.mContext.unregisterReceiver(this.mScanPackagesReceiver);
         this.mHandler.removeCallbacks(this.mScanPackagesRunnable);

         for(int i = this.mProviders.size() - 1; i >= 0; --i) {
            ((RegisteredMediaRouteProvider)this.mProviders.get(i)).stop();
         }
      }

   }

   void scanPackages() {
      if (this.mRunning) {
         int targetIndex = 0;
         Intent intent = new Intent("android.media.MediaRouteProviderService");
         Iterator var3 = this.mPackageManager.queryIntentServices(intent, 0).iterator();

         while(var3.hasNext()) {
            ResolveInfo resolveInfo = (ResolveInfo)var3.next();
            ServiceInfo serviceInfo = resolveInfo.serviceInfo;
            if (serviceInfo != null) {
               int sourceIndex = this.findProvider(serviceInfo.packageName, serviceInfo.name);
               RegisteredMediaRouteProvider provider;
               if (sourceIndex < 0) {
                  provider = new RegisteredMediaRouteProvider(this.mContext, new ComponentName(serviceInfo.packageName, serviceInfo.name));
                  provider.start();
                  this.mProviders.add(targetIndex++, provider);
                  this.mCallback.addProvider(provider);
               } else if (sourceIndex >= targetIndex) {
                  provider = (RegisteredMediaRouteProvider)this.mProviders.get(sourceIndex);
                  provider.start();
                  provider.rebindIfDisconnected();
                  Collections.swap(this.mProviders, sourceIndex, targetIndex++);
               }
            }
         }

         if (targetIndex < this.mProviders.size()) {
            for(int i = this.mProviders.size() - 1; i >= targetIndex; --i) {
               RegisteredMediaRouteProvider provider = (RegisteredMediaRouteProvider)this.mProviders.get(i);
               this.mCallback.removeProvider(provider);
               this.mProviders.remove(provider);
               provider.stop();
            }
         }

      }
   }

   private int findProvider(String packageName, String className) {
      int count = this.mProviders.size();

      for(int i = 0; i < count; ++i) {
         RegisteredMediaRouteProvider provider = (RegisteredMediaRouteProvider)this.mProviders.get(i);
         if (provider.hasComponentName(packageName, className)) {
            return i;
         }
      }

      return -1;
   }

   public interface Callback {
      void addProvider(MediaRouteProvider var1);

      void removeProvider(MediaRouteProvider var1);
   }
}
