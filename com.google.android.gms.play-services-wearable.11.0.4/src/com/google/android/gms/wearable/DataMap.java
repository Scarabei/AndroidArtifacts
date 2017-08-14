package com.google.android.gms.wearable;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.internal.ado;
import com.google.android.gms.internal.adp;
import com.google.android.gms.internal.hc;
import com.google.android.gms.internal.hd;
import com.google.android.gms.internal.he;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class DataMap {
   public static final String TAG = "DataMap";
   private final HashMap zzrO = new HashMap();

   public static DataMap fromBundle(Bundle var0) {
      var0.setClassLoader(Asset.class.getClassLoader());
      DataMap var1 = new DataMap();
      Iterator var2 = var0.keySet().iterator();

      while(var2.hasNext()) {
         String var3 = (String)var2.next();
         Object var6 = var0.get(var3);
         if (var6 instanceof String) {
            var1.putString(var3, (String)var6);
         } else if (var6 instanceof Integer) {
            var1.putInt(var3, ((Integer)var6).intValue());
         } else if (var6 instanceof Long) {
            var1.putLong(var3, ((Long)var6).longValue());
         } else if (var6 instanceof Double) {
            var1.putDouble(var3, ((Double)var6).doubleValue());
         } else if (var6 instanceof Float) {
            var1.putFloat(var3, ((Float)var6).floatValue());
         } else if (var6 instanceof Boolean) {
            var1.putBoolean(var3, ((Boolean)var6).booleanValue());
         } else if (var6 instanceof Byte) {
            var1.putByte(var3, ((Byte)var6).byteValue());
         } else if (var6 instanceof byte[]) {
            var1.putByteArray(var3, (byte[])var6);
         } else if (var6 instanceof String[]) {
            var1.putStringArray(var3, (String[])var6);
         } else if (var6 instanceof long[]) {
            var1.putLongArray(var3, (long[])var6);
         } else if (var6 instanceof float[]) {
            var1.putFloatArray(var3, (float[])var6);
         } else if (var6 instanceof Asset) {
            var1.putAsset(var3, (Asset)var6);
         } else if (var6 instanceof Bundle) {
            var1.putDataMap(var3, fromBundle((Bundle)var6));
         } else if (var6 instanceof ArrayList) {
            switch(zze((ArrayList)var6)) {
            case 0:
               var1.putStringArrayList(var3, (ArrayList)var6);
               break;
            case 1:
               var1.putStringArrayList(var3, (ArrayList)var6);
               break;
            case 2:
               var1.putIntegerArrayList(var3, (ArrayList)var6);
               break;
            case 3:
               var1.putStringArrayList(var3, (ArrayList)var6);
            case 4:
            default:
               break;
            case 5:
               var1.putDataMapArrayList(var3, arrayListFromBundleArrayList((ArrayList)var6));
            }
         }
      }

      return var1;
   }

   public static DataMap fromByteArray(byte[] var0) {
      try {
         return hc.zza(new hd(he.zzy(var0), new ArrayList()));
      } catch (ado var2) {
         throw new IllegalArgumentException("Unable to convert data", var2);
      }
   }

   public static ArrayList arrayListFromBundleArrayList(ArrayList var0) {
      ArrayList var1 = new ArrayList();
      ArrayList var3;
      int var4 = (var3 = (ArrayList)var0).size();
      int var5 = 0;

      while(var5 < var4) {
         Object var10000 = var3.get(var5);
         ++var5;
         Bundle var2 = (Bundle)var10000;
         var1.add(fromBundle(var2));
      }

      return var1;
   }

   public Bundle toBundle() {
      Bundle var1 = new Bundle();
      Iterator var2 = this.zzrO.keySet().iterator();

      while(var2.hasNext()) {
         String var3 = (String)var2.next();
         zzb(var1, var3, this.zzrO.get(var3));
      }

      return var1;
   }

   public byte[] toByteArray() {
      return adp.zzc(hc.zza(this).zzbTF);
   }

   public int size() {
      return this.zzrO.size();
   }

   public boolean isEmpty() {
      return this.zzrO.isEmpty();
   }

   public void clear() {
      this.zzrO.clear();
   }

   public boolean containsKey(String var1) {
      return this.zzrO.containsKey(var1);
   }

   public Object get(String var1) {
      return this.zzrO.get(var1);
   }

   public Object remove(String var1) {
      return this.zzrO.remove(var1);
   }

   public void putAll(DataMap var1) {
      Iterator var2 = var1.keySet().iterator();

      while(var2.hasNext()) {
         String var3 = (String)var2.next();
         this.zzrO.put(var3, var1.get(var3));
      }

   }

   public Set keySet() {
      return this.zzrO.keySet();
   }

   public void putBoolean(String var1, boolean var2) {
      this.zzrO.put(var1, var2);
   }

   public void putByte(String var1, byte var2) {
      this.zzrO.put(var1, var2);
   }

   public void putInt(String var1, int var2) {
      this.zzrO.put(var1, var2);
   }

   public void putLong(String var1, long var2) {
      this.zzrO.put(var1, var2);
   }

   public void putFloat(String var1, float var2) {
      this.zzrO.put(var1, var2);
   }

   public void putDouble(String var1, double var2) {
      this.zzrO.put(var1, var2);
   }

   public void putString(String var1, String var2) {
      this.zzrO.put(var1, var2);
   }

   public void putAsset(String var1, Asset var2) {
      this.zzrO.put(var1, var2);
   }

   public void putDataMap(String var1, DataMap var2) {
      this.zzrO.put(var1, var2);
   }

   public void putDataMapArrayList(String var1, ArrayList var2) {
      this.zzrO.put(var1, var2);
   }

   public void putIntegerArrayList(String var1, ArrayList var2) {
      this.zzrO.put(var1, var2);
   }

   public void putStringArrayList(String var1, ArrayList var2) {
      this.zzrO.put(var1, var2);
   }

   public void putByteArray(String var1, byte[] var2) {
      this.zzrO.put(var1, var2);
   }

   public void putLongArray(String var1, long[] var2) {
      this.zzrO.put(var1, var2);
   }

   public void putFloatArray(String var1, float[] var2) {
      this.zzrO.put(var1, var2);
   }

   public void putStringArray(String var1, String[] var2) {
      this.zzrO.put(var1, var2);
   }

   public boolean getBoolean(String var1) {
      return this.getBoolean(var1, false);
   }

   public boolean getBoolean(String var1, boolean var2) {
      Object var3;
      if ((var3 = this.zzrO.get(var1)) == null) {
         return var2;
      } else {
         try {
            return ((Boolean)var3).booleanValue();
         } catch (ClassCastException var5) {
            zza(var1, var3, "Boolean", var2, var5);
            return var2;
         }
      }
   }

   public byte getByte(String var1) {
      return this.getByte(var1, (byte)0);
   }

   public byte getByte(String var1, byte var2) {
      Object var3;
      if ((var3 = this.zzrO.get(var1)) == null) {
         return var2;
      } else {
         try {
            return ((Byte)var3).byteValue();
         } catch (ClassCastException var5) {
            zza(var1, var3, "Byte", var2, var5);
            return var2;
         }
      }
   }

   public int getInt(String var1) {
      return this.getInt(var1, 0);
   }

   public int getInt(String var1, int var2) {
      Object var3;
      if ((var3 = this.zzrO.get(var1)) == null) {
         return var2;
      } else {
         try {
            return ((Integer)var3).intValue();
         } catch (ClassCastException var5) {
            zza(var1, var3, "Integer", var5);
            return var2;
         }
      }
   }

   public long getLong(String var1) {
      return this.getLong(var1, 0L);
   }

   public long getLong(String var1, long var2) {
      Object var4;
      if ((var4 = this.zzrO.get(var1)) == null) {
         return var2;
      } else {
         try {
            return ((Long)var4).longValue();
         } catch (ClassCastException var6) {
            zza(var1, var4, "long", var6);
            return var2;
         }
      }
   }

   public float getFloat(String var1) {
      return this.getFloat(var1, 0.0F);
   }

   public float getFloat(String var1, float var2) {
      Object var3;
      if ((var3 = this.zzrO.get(var1)) == null) {
         return var2;
      } else {
         try {
            return ((Float)var3).floatValue();
         } catch (ClassCastException var5) {
            zza(var1, var3, "Float", var2, var5);
            return var2;
         }
      }
   }

   public double getDouble(String var1) {
      return this.getDouble(var1, 0.0D);
   }

   public double getDouble(String var1, double var2) {
      Object var4;
      if ((var4 = this.zzrO.get(var1)) == null) {
         return var2;
      } else {
         try {
            return ((Double)var4).doubleValue();
         } catch (ClassCastException var6) {
            zza(var1, var4, "Double", var2, var6);
            return var2;
         }
      }
   }

   public String getString(String var1, String var2) {
      String var3;
      return (var3 = this.getString(var1)) == null ? var2 : var3;
   }

   public String getString(String var1) {
      Object var2;
      if ((var2 = this.zzrO.get(var1)) == null) {
         return null;
      } else {
         try {
            return (String)var2;
         } catch (ClassCastException var4) {
            zza(var1, var2, "String", var4);
            return null;
         }
      }
   }

   public Asset getAsset(String var1) {
      Object var2;
      if ((var2 = this.zzrO.get(var1)) == null) {
         return null;
      } else {
         try {
            return (Asset)var2;
         } catch (ClassCastException var4) {
            zza(var1, var2, "Asset", var4);
            return null;
         }
      }
   }

   public DataMap getDataMap(String var1) {
      Object var2;
      if ((var2 = this.zzrO.get(var1)) == null) {
         return null;
      } else {
         try {
            return (DataMap)var2;
         } catch (ClassCastException var4) {
            zza(var1, var2, "DataMap", var4);
            return null;
         }
      }
   }

   public ArrayList getIntegerArrayList(String var1) {
      Object var2;
      if ((var2 = this.zzrO.get(var1)) == null) {
         return null;
      } else {
         try {
            return (ArrayList)var2;
         } catch (ClassCastException var4) {
            zza(var1, var2, "ArrayList<Integer>", var4);
            return null;
         }
      }
   }

   public ArrayList getStringArrayList(String var1) {
      Object var2;
      if ((var2 = this.zzrO.get(var1)) == null) {
         return null;
      } else {
         try {
            return (ArrayList)var2;
         } catch (ClassCastException var4) {
            zza(var1, var2, "ArrayList<String>", var4);
            return null;
         }
      }
   }

   public ArrayList getDataMapArrayList(String var1) {
      Object var2;
      if ((var2 = this.zzrO.get(var1)) == null) {
         return null;
      } else {
         try {
            return (ArrayList)var2;
         } catch (ClassCastException var4) {
            zza(var1, var2, "ArrayList<DataMap>", var4);
            return null;
         }
      }
   }

   public byte[] getByteArray(String var1) {
      Object var2;
      if ((var2 = this.zzrO.get(var1)) == null) {
         return null;
      } else {
         try {
            return (byte[])var2;
         } catch (ClassCastException var4) {
            zza(var1, var2, "byte[]", var4);
            return null;
         }
      }
   }

   public long[] getLongArray(String var1) {
      Object var2;
      if ((var2 = this.zzrO.get(var1)) == null) {
         return null;
      } else {
         try {
            return (long[])var2;
         } catch (ClassCastException var4) {
            zza(var1, var2, "long[]", var4);
            return null;
         }
      }
   }

   public float[] getFloatArray(String var1) {
      Object var2;
      if ((var2 = this.zzrO.get(var1)) == null) {
         return null;
      } else {
         try {
            return (float[])var2;
         } catch (ClassCastException var4) {
            zza(var1, var2, "float[]", var4);
            return null;
         }
      }
   }

   public String[] getStringArray(String var1) {
      Object var2;
      if ((var2 = this.zzrO.get(var1)) == null) {
         return null;
      } else {
         try {
            return (String[])var2;
         } catch (ClassCastException var4) {
            zza(var1, var2, "String[]", var4);
            return null;
         }
      }
   }

   public boolean equals(Object var1) {
      if (!(var1 instanceof DataMap)) {
         return false;
      } else {
         DataMap var3 = (DataMap)var1;
         DataMap var2 = this;
         if (this.size() != var3.size()) {
            return false;
         } else {
            Iterator var4 = this.keySet().iterator();

            while(var4.hasNext()) {
               String var5 = (String)var4.next();
               Object var6 = var2.get(var5);
               Object var7 = var3.get(var5);
               if (var6 instanceof Asset) {
                  if (!(var7 instanceof Asset)) {
                     return false;
                  }

                  Asset var10000 = (Asset)var6;
                  Asset var9 = (Asset)var7;
                  Asset var8 = var10000;
                  if (!(var10000 != null && var9 != null ? (!TextUtils.isEmpty(var8.getDigest()) ? var8.getDigest().equals(var9.getDigest()) : Arrays.equals(var8.getData(), var9.getData())) : var8 == var9)) {
                     return false;
                  }
               } else if (var6 instanceof String[]) {
                  if (!(var7 instanceof String[])) {
                     return false;
                  }

                  if (!Arrays.equals((String[])var6, (String[])var7)) {
                     return false;
                  }
               } else if (var6 instanceof long[]) {
                  if (!(var7 instanceof long[])) {
                     return false;
                  }

                  if (!Arrays.equals((long[])var6, (long[])var7)) {
                     return false;
                  }
               } else if (var6 instanceof float[]) {
                  if (!(var7 instanceof float[])) {
                     return false;
                  }

                  if (!Arrays.equals((float[])var6, (float[])var7)) {
                     return false;
                  }
               } else if (var6 instanceof byte[]) {
                  if (!(var7 instanceof byte[])) {
                     return false;
                  }

                  if (!Arrays.equals((byte[])var6, (byte[])var7)) {
                     return false;
                  }
               } else {
                  if (var6 == null || var7 == null) {
                     if (var6 != var7) {
                        return false;
                     }
                     break;
                  }

                  if (!var6.equals(var7)) {
                     return false;
                  }
               }
            }

            return true;
         }
      }
   }

   public int hashCode() {
      return 29 * this.zzrO.hashCode();
   }

   public String toString() {
      return this.zzrO.toString();
   }

   private static void zza(String var0, Object var1, String var2, ClassCastException var3) {
      zza(var0, var1, var2, "<null>", var3);
   }

   private static void zza(String var0, Object var1, String var2, Object var3, ClassCastException var4) {
      StringBuilder var5;
      (var5 = new StringBuilder()).append("Key ");
      var5.append(var0);
      var5.append(" expected ");
      var5.append(var2);
      var5.append(" but value was a ");
      var5.append(var1.getClass().getName());
      var5.append(".  The default value ");
      var5.append(var3);
      var5.append(" was returned.");
      Log.w("DataMap", var5.toString());
      Log.w("DataMap", "Attempt to cast generated internal exception:", var4);
   }

   private static void zzb(Bundle var0, String var1, Object var2) {
      if (var2 instanceof String) {
         var0.putString(var1, (String)var2);
      } else if (var2 instanceof Integer) {
         var0.putInt(var1, ((Integer)var2).intValue());
      } else if (var2 instanceof Long) {
         var0.putLong(var1, ((Long)var2).longValue());
      } else if (var2 instanceof Double) {
         var0.putDouble(var1, ((Double)var2).doubleValue());
      } else if (var2 instanceof Float) {
         var0.putFloat(var1, ((Float)var2).floatValue());
      } else if (var2 instanceof Boolean) {
         var0.putBoolean(var1, ((Boolean)var2).booleanValue());
      } else if (var2 instanceof Byte) {
         var0.putByte(var1, ((Byte)var2).byteValue());
      } else if (var2 instanceof byte[]) {
         var0.putByteArray(var1, (byte[])var2);
      } else if (var2 instanceof String[]) {
         var0.putStringArray(var1, (String[])var2);
      } else if (var2 instanceof long[]) {
         var0.putLongArray(var1, (long[])var2);
      } else if (var2 instanceof float[]) {
         var0.putFloatArray(var1, (float[])var2);
      } else if (var2 instanceof Asset) {
         var0.putParcelable(var1, (Asset)var2);
      } else if (var2 instanceof DataMap) {
         var0.putParcelable(var1, ((DataMap)var2).toBundle());
      } else {
         if (var2 instanceof ArrayList) {
            switch(zze((ArrayList)var2)) {
            case 0:
               var0.putStringArrayList(var1, (ArrayList)var2);
               return;
            case 1:
               var0.putStringArrayList(var1, (ArrayList)var2);
               return;
            case 2:
               var0.putIntegerArrayList(var1, (ArrayList)var2);
               return;
            case 3:
               var0.putStringArrayList(var1, (ArrayList)var2);
               return;
            case 4:
               ArrayList var3 = new ArrayList();
               ArrayList var5;
               int var6 = (var5 = (ArrayList)var2).size();
               int var7 = 0;

               while(var7 < var6) {
                  Object var10000 = var5.get(var7);
                  ++var7;
                  DataMap var4 = (DataMap)var10000;
                  var3.add(var4.toBundle());
               }

               var0.putParcelableArrayList(var1, var3);
            }
         }

      }
   }

   private static int zze(ArrayList var0) {
      if (var0.isEmpty()) {
         return 0;
      } else {
         ArrayList var2;
         int var3 = (var2 = (ArrayList)var0).size();
         int var4 = 0;

         while(var4 < var3) {
            Object var10000 = var2.get(var4);
            ++var4;
            Object var1 = var10000;
            if (var10000 != null) {
               if (var1 instanceof Integer) {
                  return 2;
               }

               if (var1 instanceof String) {
                  return 3;
               }

               if (var1 instanceof DataMap) {
                  return 4;
               }

               if (var1 instanceof Bundle) {
                  return 5;
               }
            }
         }

         return 1;
      }
   }
}
