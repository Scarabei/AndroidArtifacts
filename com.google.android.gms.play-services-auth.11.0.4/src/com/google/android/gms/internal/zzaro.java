package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.support.annotation.Nullable;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzz;

public final class zzaro extends zzz {
   @Nullable
   private final Auth.AuthCredentialsOptions zzalC;

   public zzaro(Context var1, Looper var2, zzq var3, Auth.AuthCredentialsOptions var4, ConnectionCallbacks var5, OnConnectionFailedListener var6) {
      super(var1, var2, 68, var3, var5, var6);
      this.zzalC = var4;
   }

   protected final String zzdb() {
      return "com.google.android.gms.auth.api.credentials.service.START";
   }

   protected final String zzdc() {
      return "com.google.android.gms.auth.api.credentials.internal.ICredentialsService";
   }

   protected final Bundle zzmo() {
      return this.zzalC == null ? new Bundle() : this.zzalC.zzmo();
   }

   final Auth.AuthCredentialsOptions zzmu() {
      return this.zzalC;
   }

   // $FF: synthetic method
   protected final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.auth.api.credentials.internal.ICredentialsService")) instanceof zzart ? (zzart)var3 : new zzaru(var1));
      }
   }
}
