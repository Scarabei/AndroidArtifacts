package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.annotation.KeepName;

@KeepName
public final class BinderWrapper implements Parcelable {
   private IBinder zzaHj;
   public static final Creator CREATOR = new zzp();

   public BinderWrapper() {
      this.zzaHj = null;
   }

   public BinderWrapper(IBinder var1) {
      this.zzaHj = null;
      this.zzaHj = var1;
   }

   private BinderWrapper(Parcel var1) {
      this.zzaHj = null;
      this.zzaHj = var1.readStrongBinder();
   }

   public final int describeContents() {
      return 0;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      var1.writeStrongBinder(this.zzaHj);
   }

   // $FF: synthetic method
   BinderWrapper(Parcel var1, zzp var2) {
      this(var1);
   }
}
