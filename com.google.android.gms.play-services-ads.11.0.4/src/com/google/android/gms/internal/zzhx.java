package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.io.InputStream;

@zzzn
public final class zzhx extends zza {
   public static final Creator CREATOR = new zzhy();
   @Nullable
   private ParcelFileDescriptor zzzt;

   public zzhx() {
      this((ParcelFileDescriptor)null);
   }

   public zzhx(@Nullable ParcelFileDescriptor var1) {
      this.zzzt = var1;
   }

   public final synchronized boolean zzcY() {
      return this.zzzt != null;
   }

   @Nullable
   public final synchronized InputStream zzcZ() {
      if (this.zzzt == null) {
         return null;
      } else {
         AutoCloseInputStream var1 = new AutoCloseInputStream(this.zzzt);
         this.zzzt = null;
         return var1;
      }
   }

   private synchronized ParcelFileDescriptor zzda() {
      return this.zzzt;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzda(), var2, false);
      zzd.zzI(var1, var5);
   }
}
