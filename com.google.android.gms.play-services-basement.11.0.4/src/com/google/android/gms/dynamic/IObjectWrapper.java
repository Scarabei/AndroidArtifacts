package com.google.android.gms.dynamic;

import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.internal.zzee;

public interface IObjectWrapper extends IInterface {
   public abstract static class zza extends zzee implements IObjectWrapper {
      public zza() {
         this.attachInterface(this, "com.google.android.gms.dynamic.IObjectWrapper");
      }

      public static IObjectWrapper zzM(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1;
            return (IObjectWrapper)((var1 = var0.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper")) instanceof IObjectWrapper ? (IObjectWrapper)var1 : new zzm(var0));
         }
      }
   }
}
