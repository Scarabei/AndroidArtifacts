package com.google.android.gms.tagmanager;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Keep;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepName;

@KeepName
public class TagManagerService extends Service {
   public IBinder onBind(Intent var1) {
      return zzbf.zzbn(this);
   }

   @Keep
   @KeepName
   @WorkerThread
   public static void initialize(Context var0) {
      zzbf.zzbo(var0);
   }
}
