package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public final class zzbbg extends Handler {
   public zzbbg() {
      this(Looper.getMainLooper());
   }

   public zzbbg(Looper var1) {
      super(var1);
   }

   public final void zza(ResultCallback var1, Result var2) {
      this.sendMessage(this.obtainMessage(1, new Pair(var1, var2)));
   }

   public final void handleMessage(Message var1) {
      switch(var1.what) {
      case 1:
         Pair var2;
         ResultCallback var10000 = (ResultCallback)(var2 = (Pair)var1.obj).first;
         Result var4 = (Result)var2.second;
         ResultCallback var3 = var10000;

         try {
            var3.onResult(var4);
            return;
         } catch (RuntimeException var6) {
            zzbbe.zzc(var4);
            throw var6;
         }
      case 2:
         ((zzbbe)var1.obj).zzs(Status.zzaBp);
         return;
      default:
         int var7 = var1.what;
         Log.wtf("BasePendingResult", (new StringBuilder(45)).append("Don't know how to handle message: ").append(var7).toString(), new Exception());
      }
   }
}
