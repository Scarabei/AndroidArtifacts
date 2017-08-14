package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.auth.account.zze;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzz;

public final class zzaqx extends zzz {
   public zzaqx(Context var1, Looper var2, zzq var3, ConnectionCallbacks var4, OnConnectionFailedListener var5) {
      super(var1, var2, 120, var3, var4, var5);
   }

   protected final String zzdb() {
      return "com.google.android.gms.auth.account.workaccount.START";
   }

   protected final String zzdc() {
      return "com.google.android.gms.auth.account.IWorkAccountService";
   }

   // $FF: synthetic method
   protected final IInterface zzd(IBinder var1) {
      return zze.zzz(var1);
   }
}
