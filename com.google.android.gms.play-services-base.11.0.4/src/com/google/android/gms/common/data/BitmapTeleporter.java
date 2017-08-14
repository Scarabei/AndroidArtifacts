package com.google.android.gms.common.data;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.ReflectedParcelable;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class BitmapTeleporter extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zza();
   private int zzaku;
   private ParcelFileDescriptor zzTP;
   private int zzamr;
   private Bitmap zzaFr;
   private boolean zzaFs;
   private File zzaFt;

   BitmapTeleporter(int var1, ParcelFileDescriptor var2, int var3) {
      this.zzaku = var1;
      this.zzTP = var2;
      this.zzamr = var3;
      this.zzaFr = null;
      this.zzaFs = false;
   }

   public BitmapTeleporter(Bitmap var1) {
      this.zzaku = 1;
      this.zzTP = null;
      this.zzamr = 0;
      this.zzaFr = var1;
      this.zzaFs = true;
   }

   public final Bitmap zzqO() {
      if (!this.zzaFs) {
         DataInputStream var1 = new DataInputStream(new AutoCloseInputStream(this.zzTP));

         byte[] var2;
         int var3;
         int var4;
         Config var5;
         try {
            var2 = new byte[var1.readInt()];
            var3 = var1.readInt();
            var4 = var1.readInt();
            var5 = Config.valueOf(var1.readUTF());
            var1.read(var2);
         } catch (IOException var10) {
            throw new IllegalStateException("Could not read from parcel file descriptor", var10);
         } finally {
            zza(var1);
         }

         ByteBuffer var6 = ByteBuffer.wrap(var2);
         Bitmap var7;
         (var7 = Bitmap.createBitmap(var3, var4, var5)).copyPixelsFromBuffer(var6);
         this.zzaFr = var7;
         this.zzaFs = true;
      }

      return this.zzaFr;
   }

   public void writeToParcel(Parcel var1, int var2) {
      if (this.zzTP == null) {
         Bitmap var3 = this.zzaFr;
         ByteBuffer var4 = ByteBuffer.allocate(this.zzaFr.getRowBytes() * var3.getHeight());
         var3.copyPixelsToBuffer(var4);
         byte[] var5 = var4.array();
         FileOutputStream var6 = this.zzqP();
         DataOutputStream var7 = new DataOutputStream(var6);

         try {
            var7.writeInt(var5.length);
            var7.writeInt(var3.getWidth());
            var7.writeInt(var3.getHeight());
            var7.writeUTF(var3.getConfig().toString());
            var7.write(var5);
         } catch (IOException var16) {
            throw new IllegalStateException("Could not write into unlinked file", var16);
         } finally {
            zza(var7);
         }
      }

      int var12 = var2 | 1;
      int var13 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzTP, var12, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.zzamr);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var13);
      this.zzTP = null;
   }

   public final void release() {
      if (!this.zzaFs) {
         try {
            this.zzTP.close();
            return;
         } catch (IOException var2) {
            Log.w("BitmapTeleporter", "Could not close PFD", var2);
         }
      }

   }

   public final void zzc(File var1) {
      if (var1 == null) {
         throw new NullPointerException("Cannot set null temp directory");
      } else {
         this.zzaFt = var1;
      }
   }

   private final FileOutputStream zzqP() {
      if (this.zzaFt == null) {
         throw new IllegalStateException("setTempDir() must be called before writing this object to a parcel");
      } else {
         File var1;
         try {
            var1 = File.createTempFile("teleporter", ".tmp", this.zzaFt);
         } catch (IOException var4) {
            throw new IllegalStateException("Could not create temporary file", var4);
         }

         FileOutputStream var2;
         try {
            var2 = new FileOutputStream(var1);
            this.zzTP = ParcelFileDescriptor.open(var1, 268435456);
         } catch (FileNotFoundException var3) {
            throw new IllegalStateException("Temporary file is somehow already deleted");
         }

         var1.delete();
         return var2;
      }
   }

   private static void zza(Closeable var0) {
      try {
         var0.close();
      } catch (IOException var2) {
         Log.w("BitmapTeleporter", "Could not close stream", var2);
      }
   }
}
