package com.google.android.gms.wearable;

import android.net.Uri;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;

public class Asset extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zze();
   private byte[] zzbdY;
   private String zzbQX;
   private ParcelFileDescriptor zzbQY;
   private Uri uri;

   Asset(byte[] var1, String var2, ParcelFileDescriptor var3, Uri var4) {
      this.zzbdY = var1;
      this.zzbQX = var2;
      this.zzbQY = var3;
      this.uri = var4;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = var2 | 1;
      int var6 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbdY, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getDigest(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbQY, var5, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.uri, var5, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var6);
   }

   public static Asset createFromRef(String var0) {
      if (var0 == null) {
         throw new IllegalArgumentException("Asset digest cannot be null");
      } else {
         return new Asset((byte[])null, var0, (ParcelFileDescriptor)null, (Uri)null);
      }
   }

   public static Asset createFromBytes(byte[] var0) {
      if (var0 == null) {
         throw new IllegalArgumentException("Asset data cannot be null");
      } else {
         return new Asset(var0, (String)null, (ParcelFileDescriptor)null, (Uri)null);
      }
   }

   public static Asset createFromFd(ParcelFileDescriptor var0) {
      if (var0 == null) {
         throw new IllegalArgumentException("Asset fd cannot be null");
      } else {
         return new Asset((byte[])null, (String)null, var0, (Uri)null);
      }
   }

   public static Asset createFromUri(Uri var0) {
      if (var0 == null) {
         throw new IllegalArgumentException("Asset uri cannot be null");
      } else {
         return new Asset((byte[])null, (String)null, (ParcelFileDescriptor)null, var0);
      }
   }

   public final byte[] getData() {
      return this.zzbdY;
   }

   public String getDigest() {
      return this.zzbQX;
   }

   public ParcelFileDescriptor getFd() {
      return this.zzbQY;
   }

   public Uri getUri() {
      return this.uri;
   }

   public int hashCode() {
      return Arrays.deepHashCode(new Object[]{this.zzbdY, this.zzbQX, this.zzbQY, this.uri});
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof Asset)) {
         return false;
      } else {
         Asset var2 = (Asset)var1;
         return Arrays.equals(this.zzbdY, var2.zzbdY) && zzbe.equal(this.zzbQX, var2.zzbQX) && zzbe.equal(this.zzbQY, var2.zzbQY) && zzbe.equal(this.uri, var2.uri);
      }
   }

   public String toString() {
      StringBuilder var1;
      (var1 = new StringBuilder()).append("Asset[@");
      var1.append(Integer.toHexString(this.hashCode()));
      if (this.zzbQX == null) {
         var1.append(", nodigest");
      } else {
         var1.append(", ");
         var1.append(this.zzbQX);
      }

      if (this.zzbdY != null) {
         var1.append(", size=");
         var1.append(this.zzbdY.length);
      }

      if (this.zzbQY != null) {
         var1.append(", fd=");
         var1.append(this.zzbQY);
      }

      if (this.uri != null) {
         var1.append(", uri=");
         var1.append(this.uri);
      }

      var1.append("]");
      return var1.toString();
   }
}
