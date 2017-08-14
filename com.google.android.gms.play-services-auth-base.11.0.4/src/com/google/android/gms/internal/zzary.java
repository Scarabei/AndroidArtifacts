package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.auth.api.zzd;
import com.google.android.gms.auth.api.zzf;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzz;

public final class zzary extends zzz {
   private final Bundle zzakQ;

   public zzary(Context var1, Looper var2, zzq var3, zzf var4, ConnectionCallbacks var5, OnConnectionFailedListener var6) {
      super(var1, var2, 16, var3, var5, var6);
      this.zzakQ = var4 == null ? new Bundle() : var4.zzms();
   }

   public final boolean zzmv() {
      zzq var1;
      return !TextUtils.isEmpty((var1 = this.zzry()).getAccountName()) && !var1.zzc(zzd.API).isEmpty();
   }

   protected final String zzdb() {
      return "com.google.android.gms.auth.service.START";
   }

   protected final String zzdc() {
      return "com.google.android.gms.auth.api.internal.IAuthService";
   }

   protected final Bundle zzmo() {
      return this.zzakQ;
   }

   // $FF: synthetic method
   protected final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.auth.api.internal.IAuthService")) instanceof zzasb ? (zzasb)var3 : new zzasc(var1));
      }
   }
}
