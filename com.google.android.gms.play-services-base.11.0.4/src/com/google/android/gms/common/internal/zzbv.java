package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.view.View;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamic.zzp;

public final class zzbv extends zzp {
   private static final zzbv zzaIv = new zzbv();

   private zzbv() {
      super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
   }

   public static View zzc(Context var0, int var1, int var2) throws com.google.android.gms.dynamic.zzq {
      return zzaIv.zzd(var0, var1, var2);
   }

   private final View zzd(Context var1, int var2, int var3) throws com.google.android.gms.dynamic.zzq {
      try {
         zzbt var4 = new zzbt(var2, var3, (Scope[])null);
         IObjectWrapper var5 = zzn.zzw(var1);
         return (View)zzn.zzE(((zzbb)this.zzaS(var1)).zza(var5, var4));
      } catch (Exception var6) {
         throw new com.google.android.gms.dynamic.zzq((new StringBuilder(64)).append("Could not get button with size ").append(var2).append(" and color ").append(var3).toString(), var6);
      }
   }

   // $FF: synthetic method
   public final Object zzb(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (var3 = var1.queryLocalInterface("com.google.android.gms.common.internal.ISignInButtonCreator")) instanceof zzbb ? (zzbb)var3 : new zzbc(var1);
      }
   }
}
