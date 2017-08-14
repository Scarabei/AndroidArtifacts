package com.google.android.gms.ads.formats;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.internal.zzix;
import com.google.android.gms.internal.zzke;
import com.google.android.gms.internal.zzkf;
import com.google.android.gms.internal.zzzn;

@zzzn
public final class PublisherAdViewOptions extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzc();
   private final boolean zzsu;
   @Nullable
   private final zzke zzsv;
   @Nullable
   private AppEventListener zzsw;

   private PublisherAdViewOptions(PublisherAdViewOptions.Builder var1) {
      this.zzsu = var1.zzsu;
      this.zzsw = var1.zzsw;
      this.zzsv = this.zzsw != null ? new zzix(this.zzsw) : null;
   }

   PublisherAdViewOptions(boolean var1, @Nullable IBinder var2) {
      this.zzsu = var1;
      this.zzsv = var2 != null ? zzkf.zzf(var2) : null;
   }

   @Nullable
   public final AppEventListener getAppEventListener() {
      return this.zzsw;
   }

   public final boolean getManualImpressionsEnabled() {
      return this.zzsu;
   }

   @Nullable
   public final zzke zzai() {
      return this.zzsv;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.getManualImpressionsEnabled());
      zzd.zza(var1, 2, this.zzsv == null ? null : this.zzsv.asBinder(), false);
      zzd.zzI(var1, var5);
   }

   // $FF: synthetic method
   PublisherAdViewOptions(PublisherAdViewOptions.Builder var1, zzb var2) {
      this(var1);
   }

   public static final class Builder {
      private boolean zzsu = false;
      private AppEventListener zzsw;

      public final PublisherAdViewOptions.Builder setManualImpressionsEnabled(boolean var1) {
         this.zzsu = var1;
         return this;
      }

      public final PublisherAdViewOptions.Builder setAppEventListener(AppEventListener var1) {
         this.zzsw = var1;
         return this;
      }

      public final PublisherAdViewOptions build() {
         return new PublisherAdViewOptions(this, (zzb)null);
      }
   }
}
