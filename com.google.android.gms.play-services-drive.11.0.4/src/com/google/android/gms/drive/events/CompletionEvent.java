package com.google.android.gms.drive.events;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.zzbng;
import com.google.android.gms.internal.zzbot;
import com.google.android.gms.internal.zzbrc;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class CompletionEvent extends com.google.android.gms.common.internal.safeparcel.zza implements ResourceEvent {
   public static final int STATUS_SUCCESS = 0;
   public static final int STATUS_FAILURE = 1;
   public static final int STATUS_CONFLICT = 2;
   public static final int STATUS_CANCELED = 3;
   public static final Creator CREATOR = new zzg();
   private DriveId zzaLV;
   private String zzakh;
   private ParcelFileDescriptor zzaMW;
   private ParcelFileDescriptor zzaMX;
   private MetadataBundle zzaMY;
   private List zzaMZ;
   private int zzLe;
   private IBinder zzaNa;
   private boolean zzaNb = false;
   private boolean zzaNc = false;
   private boolean zzaNd = false;

   CompletionEvent(DriveId var1, String var2, ParcelFileDescriptor var3, ParcelFileDescriptor var4, MetadataBundle var5, List var6, int var7, IBinder var8) {
      this.zzaLV = var1;
      this.zzakh = var2;
      this.zzaMW = var3;
      this.zzaMX = var4;
      this.zzaMY = var5;
      this.zzaMZ = var6;
      this.zzLe = var7;
      this.zzaNa = var8;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = var2 | 1;
      int var6 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaLV, var5, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzakh, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzaMW, var5, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzaMX, var5, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzaMY, var5, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzb(var1, 7, this.zzaMZ, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 8, this.zzLe);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.zzaNa, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var6);
   }

   public final int getType() {
      return 2;
   }

   public final DriveId getDriveId() {
      this.zzsY();
      return this.zzaLV;
   }

   public final String getAccountName() {
      this.zzsY();
      return this.zzakh;
   }

   public final InputStream getBaseContentsInputStream() {
      this.zzsY();
      if (this.zzaMW == null) {
         return null;
      } else if (this.zzaNb) {
         throw new IllegalStateException("getBaseInputStream() can only be called once per CompletionEvent instance.");
      } else {
         this.zzaNb = true;
         return new FileInputStream(this.zzaMW.getFileDescriptor());
      }
   }

   public final InputStream getModifiedContentsInputStream() {
      this.zzsY();
      if (this.zzaMX == null) {
         return null;
      } else if (this.zzaNc) {
         throw new IllegalStateException("getModifiedInputStream() can only be called once per CompletionEvent instance.");
      } else {
         this.zzaNc = true;
         return new FileInputStream(this.zzaMX.getFileDescriptor());
      }
   }

   public final MetadataChangeSet getModifiedMetadataChangeSet() {
      this.zzsY();
      return this.zzaMY != null ? new MetadataChangeSet(this.zzaMY) : null;
   }

   public final List getTrackingTags() {
      this.zzsY();
      return new ArrayList(this.zzaMZ);
   }

   public final int getStatus() {
      this.zzsY();
      return this.zzLe;
   }

   public final void dismiss() {
      this.zzq(false);
   }

   public final void snooze() {
      this.zzq(true);
   }

   private final void zzq(boolean var1) {
      this.zzsY();
      this.zzaNd = true;
      com.google.android.gms.common.util.zzn.zza(this.zzaMW);
      com.google.android.gms.common.util.zzn.zza(this.zzaMX);
      if (this.zzaMY != null && this.zzaMY.zzc(zzbrc.zzaQv)) {
         ((BitmapTeleporter)this.zzaMY.zza(zzbrc.zzaQv)).release();
      }

      if (this.zzaNa == null) {
         String var10002 = String.valueOf(var1 ? "snooze" : "dismiss");
         String var10001;
         if (var10002.length() != 0) {
            var10001 = "No callback on ".concat(var10002);
         } else {
            String var10003 = new String;
            var10001 = var10003;
            var10003.<init>("No callback on ");
         }

         zzbng.zzz("CompletionEvent", var10001);
      } else {
         try {
            zzbot.zzK(this.zzaNa).zzq(var1);
         } catch (RemoteException var5) {
            String var3 = var1 ? "snooze" : "dismiss";
            String var4 = String.valueOf(var5);
            zzbng.zzz("CompletionEvent", (new StringBuilder(21 + String.valueOf(var3).length() + String.valueOf(var4).length())).append("RemoteException on ").append(var3).append(": ").append(var4).toString());
         }
      }
   }

   private final void zzsY() {
      if (this.zzaNd) {
         throw new IllegalStateException("Event has already been dismissed or snoozed.");
      }
   }

   public final String toString() {
      String var10000;
      if (this.zzaMZ == null) {
         var10000 = "<null>";
      } else {
         String var2 = String.valueOf(TextUtils.join("','", this.zzaMZ));
         var10000 = (new StringBuilder(2 + String.valueOf(var2).length())).append("'").append(var2).append("'").toString();
      }

      String var1 = var10000;
      return String.format(Locale.US, "CompletionEvent [id=%s, status=%s, trackingTag=%s]", this.zzaLV, this.zzLe, var1);
   }
}
