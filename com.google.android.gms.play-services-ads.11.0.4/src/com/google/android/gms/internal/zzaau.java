package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.ParcelFileDescriptor.AutoCloseOutputStream;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.common.util.zzn;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.IOException;

@zzzn
public final class zzaau extends zza {
   public static final Creator CREATOR = new zzaaw();
   private ParcelFileDescriptor zzTP;
   private Parcelable zzTQ;
   private boolean zzTR;

   zzaau(ParcelFileDescriptor var1) {
      this.zzTP = var1;
      this.zzTQ = null;
      this.zzTR = true;
   }

   public zzaau(SafeParcelable var1) {
      this.zzTP = null;
      this.zzTQ = var1;
      this.zzTR = false;
   }

   public final SafeParcelable zza(Creator var1) {
      if (this.zzTR) {
         if (this.zzTP == null) {
            zzafr.e("File descriptor is empty, returning null.");
            return null;
         }

         DataInputStream var2 = new DataInputStream(new AutoCloseInputStream(this.zzTP));

         byte[] var3;
         try {
            var3 = new byte[var2.readInt()];
            var2.readFully(var3, 0, var3.length);
         } catch (IOException var14) {
            throw new IllegalStateException("Could not read from parcel file descriptor", var14);
         } finally {
            zzn.closeQuietly(var2);
         }

         Parcel var4 = Parcel.obtain();

         try {
            var4.unmarshall(var3, 0, var3.length);
            var4.setDataPosition(0);
            this.zzTQ = (SafeParcelable)var1.createFromParcel(var4);
         } finally {
            var4.recycle();
         }

         this.zzTR = false;
      }

      return (SafeParcelable)this.zzTQ;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      if (this.zzTP == null) {
         Parcel var3 = Parcel.obtain();

         byte[] var4;
         try {
            this.zzTQ.writeToParcel(var3, 0);
            var4 = var3.marshall();
         } finally {
            var3.recycle();
         }

         this.zzTP = this.zzc(var4);
      }

      int var8 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzTP, var2, false);
      zzd.zzI(var1, var8);
   }

   private final ParcelFileDescriptor zzc(byte[] var1) {
      Object var2 = null;

      try {
         ParcelFileDescriptor[] var3;
         ParcelFileDescriptor var4 = (var3 = ParcelFileDescriptor.createPipe())[1];
         AutoCloseOutputStream var5 = new AutoCloseOutputStream(var4);
         zzaav var6 = new zzaav(this, var5, var1);
         (new Thread(var6)).start();
         return var3[0];
      } catch (IOException var7) {
         zzafr.zzb("Error transporting the ad response", var7);
         com.google.android.gms.ads.internal.zzbs.zzbD().zza((Throwable)var7, (String)"LargeParcelTeleporter.pipeData.2");
         zzn.closeQuietly((Closeable)var2);
         return null;
      }
   }
}
