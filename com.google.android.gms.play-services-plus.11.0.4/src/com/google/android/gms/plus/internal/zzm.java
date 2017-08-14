package com.google.android.gms.plus.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.view.View;
import com.google.android.gms.dynamic.zzp;
import com.google.android.gms.plus.PlusOneDummyView;

public final class zzm extends zzp {
   private static final zzm zzbAG = new zzm();

   public static View zza(Context var0, int var1, int var2, String var3, int var4) {
      Object var5;
      try {
         if (var3 == null) {
            throw new NullPointerException();
         }

         var5 = (View)com.google.android.gms.dynamic.zzn.zzE(((zzd)zzbAG.zzaS(var0)).zza(com.google.android.gms.dynamic.zzn.zzw(var0), var1, var2, var3, var4));
      } catch (Exception var6) {
         var5 = new PlusOneDummyView(var0, var1);
      }

      return (View)var5;
   }

   private zzm() {
      super("com.google.android.gms.plus.plusone.PlusOneButtonCreatorImpl");
   }

   // $FF: synthetic method
   protected final Object zzb(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (var3 = var1.queryLocalInterface("com.google.android.gms.plus.internal.IPlusOneButtonCreator")) instanceof zzd ? (zzd)var3 : new zze(var1);
      }
   }
}
