package com.google.android.gms.common;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;

public final class ConnectionResult extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final int SUCCESS = 0;
   public static final int SERVICE_MISSING = 1;
   public static final int SERVICE_VERSION_UPDATE_REQUIRED = 2;
   public static final int SERVICE_DISABLED = 3;
   public static final int SIGN_IN_REQUIRED = 4;
   public static final int INVALID_ACCOUNT = 5;
   public static final int RESOLUTION_REQUIRED = 6;
   public static final int NETWORK_ERROR = 7;
   public static final int INTERNAL_ERROR = 8;
   public static final int SERVICE_INVALID = 9;
   public static final int DEVELOPER_ERROR = 10;
   public static final int LICENSE_CHECK_FAILED = 11;
   public static final int CANCELED = 13;
   public static final int TIMEOUT = 14;
   public static final int INTERRUPTED = 15;
   public static final int API_UNAVAILABLE = 16;
   public static final int SIGN_IN_FAILED = 17;
   public static final int SERVICE_UPDATING = 18;
   public static final int SERVICE_MISSING_PERMISSION = 19;
   public static final int RESTRICTED_PROFILE = 20;
   /** @deprecated */
   @Deprecated
   public static final int DRIVE_EXTERNAL_STORAGE_REQUIRED = 1500;
   public static final ConnectionResult zzazX = new ConnectionResult(0);
   public static final Creator CREATOR = new zzb();
   private int zzaku;
   private final int zzaxu;
   private final PendingIntent mPendingIntent;
   private final String zzazY;

   ConnectionResult(int var1, int var2, PendingIntent var3, String var4) {
      this.zzaku = var1;
      this.zzaxu = var2;
      this.mPendingIntent = var3;
      this.zzazY = var4;
   }

   public ConnectionResult(int var1) {
      this(var1, (PendingIntent)null, (String)null);
   }

   public ConnectionResult(int var1, PendingIntent var2) {
      this(var1, var2, (String)null);
   }

   public ConnectionResult(int var1, PendingIntent var2, String var3) {
      this(1, var1, var2, var3);
   }

   public final void startResolutionForResult(Activity var1, int var2) throws SendIntentException {
      if (this.hasResolution()) {
         var1.startIntentSenderForResult(this.mPendingIntent.getIntentSender(), var2, (Intent)null, 0, 0, 0);
      }
   }

   public final boolean hasResolution() {
      return this.zzaxu != 0 && this.mPendingIntent != null;
   }

   public final boolean isSuccess() {
      return this.zzaxu == 0;
   }

   public final int getErrorCode() {
      return this.zzaxu;
   }

   @Nullable
   public final PendingIntent getResolution() {
      return this.mPendingIntent;
   }

   @Nullable
   public final String getErrorMessage() {
      return this.zzazY;
   }

   static String getStatusString(int var0) {
      switch(var0) {
      case -1:
         return "UNKNOWN";
      case 0:
         return "SUCCESS";
      case 1:
         return "SERVICE_MISSING";
      case 2:
         return "SERVICE_VERSION_UPDATE_REQUIRED";
      case 3:
         return "SERVICE_DISABLED";
      case 4:
         return "SIGN_IN_REQUIRED";
      case 5:
         return "INVALID_ACCOUNT";
      case 6:
         return "RESOLUTION_REQUIRED";
      case 7:
         return "NETWORK_ERROR";
      case 8:
         return "INTERNAL_ERROR";
      case 9:
         return "SERVICE_INVALID";
      case 10:
         return "DEVELOPER_ERROR";
      case 11:
         return "LICENSE_CHECK_FAILED";
      case 13:
         return "CANCELED";
      case 14:
         return "TIMEOUT";
      case 15:
         return "INTERRUPTED";
      case 16:
         return "API_UNAVAILABLE";
      case 17:
         return "SIGN_IN_FAILED";
      case 18:
         return "SERVICE_UPDATING";
      case 19:
         return "SERVICE_MISSING_PERMISSION";
      case 20:
         return "RESTRICTED_PROFILE";
      case 21:
         return "API_VERSION_UPDATE_REQUIRED";
      case 99:
         return "UNFINISHED";
      case 1500:
         return "DRIVE_EXTERNAL_STORAGE_REQUIRED";
      default:
         return (new StringBuilder(31)).append("UNKNOWN_ERROR_CODE(").append(var0).append(")").toString();
      }
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof ConnectionResult)) {
         return false;
      } else {
         ConnectionResult var2 = (ConnectionResult)var1;
         return this.zzaxu == var2.zzaxu && zzbe.equal(this.mPendingIntent, var2.mPendingIntent) && zzbe.equal(this.zzazY, var2.zzazY);
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaxu, this.mPendingIntent, this.zzazY});
   }

   public final String toString() {
      return zzbe.zzt(this).zzg("statusCode", getStatusString(this.zzaxu)).zzg("resolution", this.mPendingIntent).zzg("message", this.zzazY).toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.getErrorCode());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, (Parcelable)this.getResolution(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, (String)this.getErrorMessage(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
