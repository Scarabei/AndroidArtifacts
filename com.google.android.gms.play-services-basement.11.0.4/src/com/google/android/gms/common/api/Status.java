package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Arrays;

public final class Status extends zza implements Result, ReflectedParcelable {
   public static final Status zzaBm = new Status(0);
   public static final Status zzaBn = new Status(14);
   public static final Status zzaBo = new Status(8);
   public static final Status zzaBp = new Status(15);
   public static final Status zzaBq = new Status(16);
   private static Status zzaBr = new Status(17);
   private static Status zzaBs = new Status(18);
   public static final Creator CREATOR = new zzf();
   private int zzaku;
   private final int zzaxu;
   private final String zzazY;
   private final PendingIntent mPendingIntent;

   Status(int var1, int var2, String var3, PendingIntent var4) {
      this.zzaku = var1;
      this.zzaxu = var2;
      this.zzazY = var3;
      this.mPendingIntent = var4;
   }

   public Status(int var1) {
      this(var1, (String)null);
   }

   public Status(int var1, String var2) {
      this(1, var1, var2, (PendingIntent)null);
   }

   public Status(int var1, String var2, PendingIntent var3) {
      this(1, var1, var2, var3);
   }

   public final void startResolutionForResult(Activity var1, int var2) throws SendIntentException {
      if (this.hasResolution()) {
         var1.startIntentSenderForResult(this.mPendingIntent.getIntentSender(), var2, (Intent)null, 0, 0, 0);
      }
   }

   @Nullable
   public final String getStatusMessage() {
      return this.zzazY;
   }

   public final boolean hasResolution() {
      return this.mPendingIntent != null;
   }

   public final boolean isSuccess() {
      return this.zzaxu <= 0;
   }

   public final boolean isCanceled() {
      return this.zzaxu == 16;
   }

   public final boolean isInterrupted() {
      return this.zzaxu == 14;
   }

   public final int getStatusCode() {
      return this.zzaxu;
   }

   public final PendingIntent getResolution() {
      return this.mPendingIntent;
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaku, this.zzaxu, this.zzazY, this.mPendingIntent});
   }

   public final boolean equals(Object var1) {
      if (!(var1 instanceof Status)) {
         return false;
      } else {
         Status var2 = (Status)var1;
         return this.zzaku == var2.zzaku && this.zzaxu == var2.zzaxu && zzbe.equal(this.zzazY, var2.zzazY) && zzbe.equal(this.mPendingIntent, var2.mPendingIntent);
      }
   }

   public final String zzpq() {
      return this.zzazY != null ? this.zzazY : CommonStatusCodes.getStatusCodeString(this.zzaxu);
   }

   public final String toString() {
      return zzbe.zzt(this).zzg("statusCode", this.zzpq()).zzg("resolution", this.mPendingIntent).toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.getStatusCode());
      zzd.zza(var1, 2, (String)this.getStatusMessage(), false);
      zzd.zza(var1, 3, (Parcelable)this.mPendingIntent, var2, false);
      zzd.zzc(var1, 1000, this.zzaku);
      zzd.zzI(var1, var5);
   }

   public final Status getStatus() {
      return this;
   }
}
