package com.google.android.gms.internal;

import android.content.Context;
import java.util.WeakHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@zzzn
public final class zzacd {
   private WeakHashMap zzWe = new WeakHashMap();

   public final Future zzn(Context var1) {
      return zzagt.zza((Callable)(new zzace(this, var1)));
   }

   // $FF: synthetic method
   static WeakHashMap zza(zzacd var0) {
      return var0.zzWe;
   }
}
