package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.Builder;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzz;
import java.util.Iterator;

public final class zzd extends zzz {
   private final GoogleSignInOptions zzamk;

   public zzd(Context var1, Looper var2, com.google.android.gms.common.internal.zzq var3, GoogleSignInOptions var4, ConnectionCallbacks var5, OnConnectionFailedListener var6) {
      super(var1, var2, 91, var3, var5, var6);
      GoogleSignInOptions var7 = var4 != null ? var4 : (new Builder()).build();
      if (!var3.zzro().isEmpty()) {
         Builder var8 = new Builder(var7);
         Iterator var9 = var3.zzro().iterator();

         while(var9.hasNext()) {
            Scope var10 = (Scope)var9.next();
            var8.requestScopes(var10, new Scope[0]);
         }

         var7 = var8.build();
      }

      this.zzamk = var7;
   }

   protected final String zzdb() {
      return "com.google.android.gms.auth.api.signin.service.START";
   }

   protected final String zzdc() {
      return "com.google.android.gms.auth.api.signin.internal.ISignInService";
   }

   public final boolean zzmG() {
      return true;
   }

   public final Intent zzmH() {
      return zze.zza(this.getContext(), this.zzamk);
   }

   public final GoogleSignInOptions zzmI() {
      return this.zzamk;
   }

   // $FF: synthetic method
   protected final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.auth.api.signin.internal.ISignInService")) instanceof zzt ? (zzt)var3 : new zzu(var1));
      }
   }
}
