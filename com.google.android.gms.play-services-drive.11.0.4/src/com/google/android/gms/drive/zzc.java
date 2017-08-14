package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public final class zzc extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzd();
   private ParcelFileDescriptor zzaGc;
   final int zzaLT;
   private int zzaLU;
   private DriveId zzaLV;
   private boolean zzaLW;
   private String zzyi;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaGc, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.zzaLT);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.zzaLU);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzaLV, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzaLW);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzyi, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public zzc(ParcelFileDescriptor var1, int var2, int var3, DriveId var4, boolean var5, String var6) {
      this.zzaGc = var1;
      this.zzaLT = var2;
      this.zzaLU = var3;
      this.zzaLV = var4;
      this.zzaLW = var5;
      this.zzyi = var6;
   }

   public final ParcelFileDescriptor getParcelFileDescriptor() {
      return this.zzaGc;
   }

   public final DriveId getDriveId() {
      return this.zzaLV;
   }

   public final InputStream getInputStream() {
      return new FileInputStream(this.zzaGc.getFileDescriptor());
   }

   public final OutputStream getOutputStream() {
      return new FileOutputStream(this.zzaGc.getFileDescriptor());
   }

   public final int getMode() {
      return this.zzaLU;
   }

   public final int getRequestId() {
      return this.zzaLT;
   }

   public final boolean zzsK() {
      return this.zzaLW;
   }
}
