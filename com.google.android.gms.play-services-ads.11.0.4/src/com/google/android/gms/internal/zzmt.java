package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.text.TextUtils;

@zzzn
public final class zzmt {
   @Nullable
   public static zzmr zza(@Nullable zzmq var0) {
      if (!var0.zzdL()) {
         zzafr.v("CsiReporterFactory: CSI is not enabled. No CSI reporter created.");
         return null;
      } else if (var0.getContext() == null) {
         throw new IllegalArgumentException("Context can't be null. Please set up context in CsiConfiguration.");
      } else if (TextUtils.isEmpty(var0.zzck())) {
         throw new IllegalArgumentException("AfmaVersion can't be null or empty. Please set up afmaVersion in CsiConfiguration.");
      } else {
         return new zzmr(var0.getContext(), var0.zzck(), var0.zzdM(), var0.zzdN());
      }
   }
}
