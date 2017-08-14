package com.google.android.gms.gcm;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;

public class PendingCallback implements Parcelable, ReflectedParcelable {
   final IBinder zzaHj;
   public static final Creator CREATOR = new zzg();

   public PendingCallback(Parcel var1) {
      this.zzaHj = var1.readStrongBinder();
   }

   public int describeContents() {
      return 0;
   }

   public void writeToParcel(Parcel var1, int var2) {
      var1.writeStrongBinder(this.zzaHj);
   }
}
