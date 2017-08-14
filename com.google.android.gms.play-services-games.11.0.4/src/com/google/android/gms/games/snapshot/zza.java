package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.util.zzn;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public final class zza extends com.google.android.gms.games.internal.zzc implements SnapshotContents {
   private static final Object zzbew = new Object();
   public static final Creator CREATOR = new zzb();
   private com.google.android.gms.drive.zzc zzaOg;

   public zza(com.google.android.gms.drive.zzc var1) {
      this.zzaOg = var1;
   }

   public final ParcelFileDescriptor getParcelFileDescriptor() {
      zzbo.zza(!this.isClosed(), "Cannot mutate closed contents!");
      return this.zzaOg.getParcelFileDescriptor();
   }

   public final com.google.android.gms.drive.zzc zzsM() {
      return this.zzaOg;
   }

   public final void close() {
      this.zzaOg = null;
   }

   public final boolean isClosed() {
      return this.zzaOg == null;
   }

   public final byte[] readFully() throws IOException {
      zzbo.zza(!this.isClosed(), "Must provide a previously opened Snapshot");
      Object var1 = zzbew;
      synchronized(zzbew) {
         ParcelFileDescriptor var2 = this.zzaOg.getParcelFileDescriptor();
         FileInputStream var3 = new FileInputStream(var2.getFileDescriptor());
         BufferedInputStream var4 = new BufferedInputStream(var3);

         byte[] var10000;
         try {
            var3.getChannel().position(0L);
            byte[] var5 = zzn.zza(var4, false);
            var3.getChannel().position(0L);
            var10000 = var5;
         } catch (IOException var7) {
            com.google.android.gms.games.internal.zze.zzc("SnapshotContentsEntity", "Failed to read snapshot data", var7);
            throw var7;
         }

         return var10000;
      }
   }

   public final boolean writeBytes(byte[] var1) {
      return this.zza(0, var1, 0, var1.length, true);
   }

   public final boolean modifyBytes(int var1, byte[] var2, int var3, int var4) {
      return this.zza(var1, var2, var3, var2.length, false);
   }

   private final boolean zza(int var1, byte[] var2, int var3, int var4, boolean var5) {
      zzbo.zza(!this.isClosed(), "Must provide a previously opened SnapshotContents");
      Object var6 = zzbew;
      synchronized(zzbew) {
         ParcelFileDescriptor var7 = this.zzaOg.getParcelFileDescriptor();
         FileOutputStream var8 = new FileOutputStream(var7.getFileDescriptor());
         BufferedOutputStream var9 = new BufferedOutputStream(var8);

         try {
            FileChannel var10;
            (var10 = var8.getChannel()).position((long)var1);
            var9.write(var2, var3, var4);
            if (var5) {
               var10.truncate((long)var2.length);
            }

            var9.flush();
         } catch (IOException var12) {
            com.google.android.gms.games.internal.zze.zzb("SnapshotContentsEntity", "Failed to write snapshot data", var12);
            return false;
         }

         return true;
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzaOg, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
