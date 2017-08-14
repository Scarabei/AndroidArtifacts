package com.google.android.gms.fitness.data;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public final class Value extends com.google.android.gms.common.internal.safeparcel.zza {
   private final int versionCode;
   private final int format;
   private boolean zzaVl;
   private float value;
   private String zzaIF;
   private Map zzaVm;
   private int[] zzaVn;
   private float[] zzaVo;
   private byte[] zzaVp;
   public static final Creator CREATOR = new zzai();

   public Value(int var1) {
      this(3, var1, false, 0.0F, (String)null, (Bundle)null, (int[])null, (float[])null, (byte[])null);
   }

   Value(int var1, int var2, boolean var3, float var4, String var5, Bundle var6, int[] var7, float[] var8, byte[] var9) {
      this.versionCode = var1;
      this.format = var2;
      this.zzaVl = var3;
      this.value = var4;
      this.zzaIF = var5;
      Bundle var10 = var6;
      ArrayMap var10001;
      if (var6 == null) {
         var10001 = null;
      } else {
         var6.setClassLoader(MapValue.class.getClassLoader());
         ArrayMap var11 = new ArrayMap(var6.size());
         Iterator var12 = var6.keySet().iterator();

         while(var12.hasNext()) {
            String var13 = (String)var12.next();
            var11.put(var13, (MapValue)var10.getParcelable(var13));
         }

         var10001 = var11;
      }

      this.zzaVm = var10001;
      this.zzaVn = var7;
      this.zzaVo = var8;
      this.zzaVp = var9;
   }

   public final void setInt(int var1) {
      zzbo.zza(this.format == 1, "Attempting to set an int value to a field that is not in INT32 format.  Please check the data type definition and use the right format.");
      this.zzaVl = true;
      this.value = Float.intBitsToFloat(var1);
   }

   public final void setFloat(float var1) {
      zzbo.zza(this.format == 2, "Attempting to set an float value to a field that is not in FLOAT format.  Please check the data type definition and use the right format.");
      this.zzaVl = true;
      this.value = var1;
   }

   public final void setString(String var1) {
      zzbo.zza(this.format == 3, "Attempting to set a string value to a field that is not in STRING format.  Please check the data type definition and use the right format.");
      this.zzaVl = true;
      this.zzaIF = var1;
   }

   public final void setKeyValue(String var1, float var2) {
      zzbo.zza(this.format == 4, "Attempting to set a key's value to a field that is not in FLOAT_MAP format.  Please check the data type definition and use the right format.");
      this.zzaVl = true;
      if (this.zzaVm == null) {
         this.zzaVm = new HashMap();
      }

      this.zzaVm.put(var1, new MapValue(2, var2));
   }

   public final void clearKey(String var1) {
      zzbo.zza(this.format == 4, "Attempting to set a key's value to a field that is not in FLOAT_MAP format.  Please check the data type definition and use the right format.");
      if (this.zzaVm != null) {
         this.zzaVm.remove(var1);
      }

   }

   public final void setActivity(String var1) {
      this.setInt(com.google.android.gms.fitness.zza.zzcW(var1));
   }

   public final boolean isSet() {
      return this.zzaVl;
   }

   public final int getFormat() {
      return this.format;
   }

   public final int asInt() {
      zzbo.zza(this.format == 1, "Value is not in int format");
      return Float.floatToRawIntBits(this.value);
   }

   public final float asFloat() {
      zzbo.zza(this.format == 2, "Value is not in float format");
      return this.value;
   }

   public final String asString() {
      zzbo.zza(this.format == 3, "Value is not in string format");
      return this.zzaIF;
   }

   @Nullable
   public final Float getKeyValue(String var1) {
      zzbo.zza(this.format == 4, "Value is not in float map format");
      return this.zzaVm != null && this.zzaVm.containsKey(var1) ? ((MapValue)this.zzaVm.get(var1)).asFloat() : null;
   }

   public final String asActivity() {
      return com.google.android.gms.fitness.zza.getName(this.asInt());
   }

   public final boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof Value) {
            Value var3 = (Value)var1;
            boolean var10000;
            if (this.format == var3.format && this.zzaVl == var3.zzaVl) {
               switch(this.format) {
               case 1:
                  var10000 = this.asInt() == var3.asInt();
                  break;
               case 2:
                  var10000 = this.value == var3.value;
                  break;
               case 3:
                  var10000 = zzbe.equal(this.zzaIF, var3.zzaIF);
                  break;
               case 4:
                  var10000 = zzbe.equal(this.zzaVm, var3.zzaVm);
                  break;
               case 5:
                  var10000 = Arrays.equals(this.zzaVn, var3.zzaVn);
                  break;
               case 6:
                  var10000 = Arrays.equals(this.zzaVo, var3.zzaVo);
                  break;
               case 7:
                  var10000 = Arrays.equals(this.zzaVp, var3.zzaVp);
                  break;
               default:
                  var10000 = this.value == var3.value;
               }
            } else {
               var10000 = false;
            }

            if (var10000) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.value, this.zzaIF, this.zzaVm, this.zzaVn, this.zzaVo, this.zzaVp});
   }

   public final String toString() {
      if (!this.zzaVl) {
         return "unset";
      } else {
         switch(this.format) {
         case 1:
            return Integer.toString(this.asInt());
         case 2:
            return Float.toString(this.value);
         case 3:
            return this.zzaIF;
         case 4:
            return (new TreeMap(this.zzaVm)).toString();
         case 5:
            return Arrays.toString(this.zzaVn);
         case 6:
            return Arrays.toString(this.zzaVo);
         case 7:
            return com.google.android.gms.common.util.zzl.zza(this.zzaVp, 0, this.zzaVp.length, false);
         default:
            return "unknown";
         }
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.getFormat());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.isSet());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.value);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzaIF, false);
      Bundle var10002;
      if (this.zzaVm == null) {
         var10002 = null;
      } else {
         Bundle var7 = new Bundle(this.zzaVm.size());
         Iterator var8 = this.zzaVm.entrySet().iterator();

         while(var8.hasNext()) {
            Entry var9 = (Entry)var8.next();
            var7.putParcelable((String)var9.getKey(), (Parcelable)var9.getValue());
         }

         var10002 = var7;
      }

      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, var10002, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzaVn, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzaVo, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.versionCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzaVp, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
