package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Base64;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.ado;
import com.google.android.gms.internal.adp;
import com.google.android.gms.internal.zzbms;
import com.google.android.gms.internal.zzbmx;
import com.google.android.gms.internal.zzbng;
import com.google.android.gms.internal.zzbnn;
import com.google.android.gms.internal.zzbra;
import com.google.android.gms.internal.zzbrb;

public class DriveId extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final int RESOURCE_TYPE_UNKNOWN = -1;
   public static final int RESOURCE_TYPE_FILE = 0;
   public static final int RESOURCE_TYPE_FOLDER = 1;
   public static final Creator CREATOR = new zzj();
   private String zzaMh;
   private long zzaMi;
   private long zzaLQ;
   private int zzaMj;
   private volatile String zzaLS = null;
   private volatile String zzaMk = null;

   public static DriveId zzcO(String var0) {
      zzbo.zzu(var0);
      return new DriveId(var0, -1L, -1L, -1);
   }

   public String getResourceId() {
      return this.zzaMh;
   }

   public int getResourceType() {
      return this.zzaMj;
   }

   public DriveFile asDriveFile() {
      if (this.zzaMj == 1) {
         throw new IllegalStateException("This DriveId corresponds to a folder. Call asDriveFolder instead.");
      } else {
         return new zzbms(this);
      }
   }

   public DriveFolder asDriveFolder() {
      if (this.zzaMj == 0) {
         throw new IllegalStateException("This DriveId corresponds to a file. Call asDriveFile instead.");
      } else {
         return new zzbmx(this);
      }
   }

   public DriveResource asDriveResource() {
      if (this.zzaMj == 1) {
         return this.asDriveFolder();
      } else {
         return (DriveResource)(this.zzaMj == 0 ? this.asDriveFile() : new zzbnn(this));
      }
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaMh, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzaMi);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzaLQ);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, this.zzaMj);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public DriveId(String var1, long var2, long var4, int var6) {
      this.zzaMh = var1;
      zzbo.zzaf(!"".equals(var1));
      zzbo.zzaf(var1 != null || var2 != -1L);
      this.zzaMi = var2;
      this.zzaLQ = var4;
      this.zzaMj = var6;
   }

   public int hashCode() {
      if (this.zzaMi == -1L) {
         return this.zzaMh.hashCode();
      } else {
         String var10000 = String.valueOf(String.valueOf(this.zzaLQ));
         String var10001 = String.valueOf(String.valueOf(this.zzaMi));
         if (var10001.length() != 0) {
            var10000 = var10000.concat(var10001);
         } else {
            String var10002 = new String;
            var10001 = var10000;
            var10000 = var10002;
            var10002.<init>(var10001);
         }

         return var10000.hashCode();
      }
   }

   public boolean equals(Object var1) {
      if (!(var1 instanceof DriveId)) {
         return false;
      } else {
         DriveId var2;
         if ((var2 = (DriveId)var1).zzaLQ != this.zzaLQ) {
            return false;
         } else if (var2.zzaMi == -1L && this.zzaMi == -1L) {
            return var2.zzaMh.equals(this.zzaMh);
         } else if (this.zzaMh != null && var2.zzaMh != null) {
            if (var2.zzaMi == this.zzaMi) {
               if (var2.zzaMh.equals(this.zzaMh)) {
                  return true;
               }

               zzbng.zzy("DriveId", "Unexpected unequal resourceId for same DriveId object.");
            }

            return false;
         } else {
            return var2.zzaMi == this.zzaMi;
         }
      }
   }

   public final String encodeToString() {
      if (this.zzaLS == null) {
         zzbra var3;
         (var3 = new zzbra()).versionCode = 1;
         var3.zzaPy = this.zzaMh == null ? "" : this.zzaMh;
         var3.zzaPz = this.zzaMi;
         var3.zzaPw = this.zzaLQ;
         var3.zzaPA = this.zzaMj;
         String var1 = Base64.encodeToString(adp.zzc(var3), 10);
         String var10001 = String.valueOf("DriveId:");
         String var10002 = String.valueOf(var1);
         if (var10002.length() != 0) {
            var10001 = var10001.concat(var10002);
         } else {
            String var10003 = new String;
            var10002 = var10001;
            var10001 = var10003;
            var10003.<init>(var10002);
         }

         this.zzaLS = var10001;
      }

      return this.zzaLS;
   }

   public final String toInvariantString() {
      if (this.zzaMk == null) {
         zzbrb var3;
         (var3 = new zzbrb()).zzaPz = this.zzaMi;
         var3.zzaPw = this.zzaLQ;
         byte[] var1 = adp.zzc(var3);
         this.zzaMk = Base64.encodeToString(var1, 10);
      }

      return this.zzaMk;
   }

   public String toString() {
      return this.encodeToString();
   }

   public static DriveId decodeFromString(String var0) {
      boolean var10000 = var0.startsWith("DriveId:");
      String var10002 = String.valueOf(var0);
      String var10001;
      if (var10002.length() != 0) {
         var10001 = "Invalid DriveId: ".concat(var10002);
      } else {
         String var10003 = new String;
         var10001 = var10003;
         var10003.<init>("Invalid DriveId: ");
      }

      zzbo.zzb(var10000, var10001);
      return zzi(Base64.decode(var0.substring(8), 10));
   }

   private static DriveId zzi(byte[] var0) {
      zzbra var1;
      try {
         var1 = (zzbra)adp.zza(new zzbra(), var0);
      } catch (ado var4) {
         throw new IllegalArgumentException();
      }

      String var2 = "".equals(var1.zzaPy) ? null : var1.zzaPy;
      return new DriveId(var2, var1.zzaPz, var1.zzaPw, var1.zzaPA);
   }
}
