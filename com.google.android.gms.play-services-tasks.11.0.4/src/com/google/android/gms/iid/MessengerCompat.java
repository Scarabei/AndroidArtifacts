package com.google.android.gms.iid;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.Build.VERSION;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;

public class MessengerCompat implements ReflectedParcelable {
   private Messenger zzbgX;
   private zzb zzbgY;
   public static final Creator CREATOR = new zzd();

   public MessengerCompat(IBinder var1) {
      if (VERSION.SDK_INT >= 21) {
         this.zzbgX = new Messenger(var1);
      } else {
         IInterface var3;
         this.zzbgY = (zzb)(var1 == null ? null : ((var3 = var1.queryLocalInterface("com.google.android.gms.iid.IMessengerCompat")) instanceof zzb ? (zzb)var3 : new zzc(var1)));
      }
   }

   public final void send(Message var1) throws RemoteException {
      if (this.zzbgX != null) {
         this.zzbgX.send(var1);
      } else {
         this.zzbgY.send(var1);
      }
   }

   private final IBinder getBinder() {
      return this.zzbgX != null ? this.zzbgX.getBinder() : this.zzbgY.asBinder();
   }

   public boolean equals(Object var1) {
      if (var1 == null) {
         return false;
      } else {
         try {
            return this.getBinder().equals(((MessengerCompat)var1).getBinder());
         } catch (ClassCastException var2) {
            return false;
         }
      }
   }

   public int hashCode() {
      return this.getBinder().hashCode();
   }

   public int describeContents() {
      return 0;
   }

   public void writeToParcel(Parcel var1, int var2) {
      if (this.zzbgX != null) {
         var1.writeStrongBinder(this.zzbgX.getBinder());
      } else {
         var1.writeStrongBinder(this.zzbgY.asBinder());
      }
   }
}
