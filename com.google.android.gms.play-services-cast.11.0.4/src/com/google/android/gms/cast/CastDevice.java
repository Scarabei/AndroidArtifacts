package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzaye;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CastDevice extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final int CAPABILITY_VIDEO_OUT = 1;
   public static final int CAPABILITY_VIDEO_IN = 2;
   public static final int CAPABILITY_AUDIO_OUT = 4;
   public static final int CAPABILITY_AUDIO_IN = 8;
   public static final int CAPABILITY_MULTIZONE_GROUP = 32;
   public static final Creator CREATOR = new zzn();
   private String zzapa;
   private String zzapb;
   private Inet4Address zzapc;
   private String zzapd;
   private String zzape;
   private String zzapf;
   private int zzapg;
   private List zzaph;
   private int zzapi;
   private int zzLe;
   private String zzapj;
   private String zzapk;
   private int zzapl;

   CastDevice(String var1, String var2, String var3, String var4, String var5, int var6, List var7, int var8, int var9, String var10, String var11, int var12) {
      this.zzapa = zzbX(var1);
      this.zzapb = zzbX(var2);
      if (!TextUtils.isEmpty(this.zzapb)) {
         try {
            InetAddress var13;
            if ((var13 = InetAddress.getByName(this.zzapb)) instanceof Inet4Address) {
               this.zzapc = (Inet4Address)var13;
            }
         } catch (UnknownHostException var16) {
            String var14 = this.zzapb;
            String var15 = String.valueOf(var16.getMessage());
            Log.i("CastDevice", (new StringBuilder(48 + String.valueOf(var14).length() + String.valueOf(var15).length())).append("Unable to convert host address (").append(var14).append(") to ipaddress: ").append(var15).toString());
         }
      }

      this.zzapd = zzbX(var3);
      this.zzape = zzbX(var4);
      this.zzapf = zzbX(var5);
      this.zzapg = var6;
      this.zzaph = (List)(var7 != null ? var7 : new ArrayList());
      this.zzapi = var8;
      this.zzLe = var9;
      this.zzapj = zzbX(var10);
      this.zzapk = var11;
      this.zzapl = var12;
   }

   public String getDeviceId() {
      return this.zzapa.startsWith("__cast_nearby__") ? this.zzapa.substring(16) : this.zzapa;
   }

   public Inet4Address getIpAddress() {
      return this.zzapc;
   }

   public String getFriendlyName() {
      return this.zzapd;
   }

   public String getModelName() {
      return this.zzape;
   }

   public String getDeviceVersion() {
      return this.zzapf;
   }

   public int getServicePort() {
      return this.zzapg;
   }

   public List getIcons() {
      return Collections.unmodifiableList(this.zzaph);
   }

   public WebImage getIcon(int var1, int var2) {
      if (this.zzaph.isEmpty()) {
         return null;
      } else if (var1 > 0 && var2 > 0) {
         WebImage var3 = null;
         WebImage var4 = null;
         Iterator var5 = this.zzaph.iterator();

         while(true) {
            WebImage var6;
            int var7;
            int var8;
            label57:
            do {
               while(var5.hasNext()) {
                  var7 = (var6 = (WebImage)var5.next()).getWidth();
                  var8 = var6.getHeight();
                  if (var7 >= var1 && var8 >= var2) {
                     continue label57;
                  }

                  if (var7 < var1 && var8 < var2 && (var4 == null || var4.getWidth() < var7 && var4.getHeight() < var8)) {
                     var4 = var6;
                  }
               }

               WebImage var9;
               if (var3 != null) {
                  var9 = var3;
               } else if (var4 != null) {
                  var9 = var4;
               } else {
                  var9 = (WebImage)this.zzaph.get(0);
               }

               return var9;
            } while(var3 != null && (var3.getWidth() <= var7 || var3.getHeight() <= var8));

            var3 = var6;
         }
      } else {
         return (WebImage)this.zzaph.get(0);
      }
   }

   public boolean hasIcons() {
      return !this.zzaph.isEmpty();
   }

   public boolean hasCapability(int var1) {
      return (this.zzapi & var1) == var1;
   }

   public boolean hasCapabilities(int[] var1) {
      if (var1 == null) {
         return false;
      } else {
         int[] var2 = var1;
         int var3 = var1.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            int var5 = var2[var4];
            if (!this.hasCapability(var5)) {
               return false;
            }
         }

         return true;
      }
   }

   public String toString() {
      return String.format("\"%s\" (%s)", this.zzapd, this.zzapa);
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzapa, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzapb, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getFriendlyName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.getModelName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.getDeviceVersion(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 7, this.getServicePort());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 8, this.getIcons(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 9, this.zzapi);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 10, this.zzLe);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 11, this.zzapj, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 12, this.zzapk, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 13, this.zzapl);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof CastDevice)) {
         return false;
      } else {
         CastDevice var2 = (CastDevice)var1;
         if (this.zzapa == null) {
            return var2.zzapa == null;
         } else {
            return zzaye.zza(this.zzapa, var2.zzapa) && zzaye.zza(this.zzapc, var2.zzapc) && zzaye.zza(this.zzape, var2.zzape) && zzaye.zza(this.zzapd, var2.zzapd) && zzaye.zza(this.zzapf, var2.zzapf) && this.zzapg == var2.zzapg && zzaye.zza(this.zzaph, var2.zzaph) && this.zzapi == var2.zzapi && this.zzLe == var2.zzLe && zzaye.zza(this.zzapj, var2.zzapj) && zzaye.zza(this.zzapl, var2.zzapl);
         }
      }
   }

   public boolean isSameDevice(CastDevice var1) {
      if (var1 == null) {
         return false;
      } else if (this.zzapa == null) {
         return var1.zzapa == null;
      } else {
         return zzaye.zza(this.zzapa, var1.zzapa);
      }
   }

   public int hashCode() {
      return this.zzapa == null ? 0 : this.zzapa.hashCode();
   }

   public void putInBundle(Bundle var1) {
      if (var1 != null) {
         var1.putParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE", this);
      }
   }

   public static CastDevice getFromBundle(Bundle var0) {
      if (var0 == null) {
         return null;
      } else {
         var0.setClassLoader(CastDevice.class.getClassLoader());
         return (CastDevice)var0.getParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE");
      }
   }

   public boolean isOnLocalNetwork() {
      return !this.zzapa.startsWith("__cast_nearby__");
   }

   private static String zzbX(String var0) {
      return var0 == null ? "" : var0;
   }
}
