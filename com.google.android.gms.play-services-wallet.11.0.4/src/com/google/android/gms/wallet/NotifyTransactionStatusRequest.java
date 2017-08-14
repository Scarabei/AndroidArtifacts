package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbo;

/** @deprecated */
@Deprecated
public final class NotifyTransactionStatusRequest extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzu();
   String zzbOq;
   int status;
   String zzbPF;

   public static NotifyTransactionStatusRequest.Builder newBuilder() {
      return new NotifyTransactionStatusRequest().new Builder((zzt)null);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbOq, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.status);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbPF, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   NotifyTransactionStatusRequest() {
   }

   NotifyTransactionStatusRequest(String var1, int var2, String var3) {
      this.zzbOq = var1;
      this.status = var2;
      this.zzbPF = var3;
   }

   public final String getGoogleTransactionId() {
      return this.zzbOq;
   }

   public final int getStatus() {
      return this.status;
   }

   public final String getDetailedReason() {
      return this.zzbPF;
   }

   public final class Builder {
      // $FF: synthetic field
      private NotifyTransactionStatusRequest zzbPG;

      private Builder() {
         this.zzbPG = NotifyTransactionStatusRequest.this;
         super();
      }

      public final NotifyTransactionStatusRequest.Builder setGoogleTransactionId(String var1) {
         this.zzbPG.zzbOq = var1;
         return this;
      }

      public final NotifyTransactionStatusRequest.Builder setStatus(int var1) {
         this.zzbPG.status = var1;
         return this;
      }

      public final NotifyTransactionStatusRequest.Builder setDetailedReason(String var1) {
         this.zzbPG.zzbPF = var1;
         return this;
      }

      public final NotifyTransactionStatusRequest build() {
         zzbo.zzb(!TextUtils.isEmpty(this.zzbPG.zzbOq), "googleTransactionId is required");
         zzbo.zzb(this.zzbPG.status > 0 && this.zzbPG.status <= 8, "status is an unrecognized value");
         return this.zzbPG;
      }

      // $FF: synthetic method
      Builder(zzt var2) {
         this();
      }
   }

   public interface Status {
      int SUCCESS = 1;

      public interface Error {
         int UNKNOWN = 2;
         int BAD_CVC = 3;
         int BAD_CARD = 4;
         int DECLINED = 5;
         int OTHER = 6;
         int AVS_DECLINE = 7;
         int FRAUD_DECLINE = 8;
      }
   }
}
