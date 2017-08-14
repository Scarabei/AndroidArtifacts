package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.zzbo;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

@KeepName
public final class DataHolder extends com.google.android.gms.common.internal.safeparcel.zza implements Closeable {
   public static final Creator CREATOR = new zzf();
   private int zzaku;
   private final String[] zzaFB;
   private Bundle zzaFC;
   private final CursorWindow[] zzaFD;
   private final int zzaxu;
   private final Bundle zzaFE;
   private int[] zzaFF;
   int zzaFG;
   private boolean mClosed;
   private boolean zzaFH;
   private static final DataHolder.zza zzaFI = new zze(new String[0], (String)null);

   DataHolder(int var1, String[] var2, CursorWindow[] var3, int var4, Bundle var5) {
      this.mClosed = false;
      this.zzaFH = true;
      this.zzaku = var1;
      this.zzaFB = var2;
      this.zzaFD = var3;
      this.zzaxu = var4;
      this.zzaFE = var5;
   }

   private DataHolder(String[] var1, CursorWindow[] var2, int var3, Bundle var4) {
      this.mClosed = false;
      this.zzaFH = true;
      this.zzaku = 1;
      this.zzaFB = (String[])zzbo.zzu(var1);
      this.zzaFD = (CursorWindow[])zzbo.zzu(var2);
      this.zzaxu = var3;
      this.zzaFE = var4;
      this.zzqR();
   }

   private DataHolder(DataHolder.zza var1, int var2, Bundle var3) {
      this(var1.zzaFB, zza(var1, -1), var2, (Bundle)null);
   }

   public final void zzqR() {
      this.zzaFC = new Bundle();

      int var1;
      for(var1 = 0; var1 < this.zzaFB.length; ++var1) {
         this.zzaFC.putInt(this.zzaFB[var1], var1);
      }

      this.zzaFF = new int[this.zzaFD.length];
      var1 = 0;

      for(int var2 = 0; var2 < this.zzaFD.length; ++var2) {
         this.zzaFF[var2] = var1;
         int var3 = this.zzaFD[var2].getStartPosition();
         int var4 = var1 - var3;
         var1 += this.zzaFD[var2].getNumRows() - var4;
      }

      this.zzaFG = var1;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzaFB, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaFD, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.zzaxu);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzaFE, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final int getStatusCode() {
      return this.zzaxu;
   }

   public final Bundle zzqN() {
      return this.zzaFE;
   }

   private static CursorWindow[] zza(DataHolder.zza var0, int var1) {
      if (var0.zzaFB.length == 0) {
         return new CursorWindow[0];
      } else {
         ArrayList var2;
         int var3 = (var2 = var0.zzaFJ).size();
         CursorWindow var4 = new CursorWindow(false);
         ArrayList var5;
         (var5 = new ArrayList()).add(var4);
         var4.setNumColumns(var0.zzaFB.length);
         boolean var6 = false;

         try {
            for(int var7 = 0; var7 < var3; ++var7) {
               if (!var4.allocRow()) {
                  Log.d("DataHolder", (new StringBuilder(72)).append("Allocating additional cursor window for large data set (row ").append(var7).append(")").toString());
                  (var4 = new CursorWindow(false)).setStartPosition(var7);
                  var4.setNumColumns(var0.zzaFB.length);
                  var5.add(var4);
                  if (!var4.allocRow()) {
                     Log.e("DataHolder", "Unable to allocate row to hold data.");
                     var5.remove(var4);
                     return (CursorWindow[])var5.toArray(new CursorWindow[var5.size()]);
                  }
               }

               Map var15 = (Map)var2.get(var7);
               boolean var16 = true;

               for(int var10 = 0; var10 < var0.zzaFB.length && var16; ++var10) {
                  String var11 = var0.zzaFB[var10];
                  Object var12;
                  if ((var12 = var15.get(var11)) == null) {
                     var16 = var4.putNull(var7, var10);
                  } else if (var12 instanceof String) {
                     var16 = var4.putString((String)var12, var7, var10);
                  } else if (var12 instanceof Long) {
                     var16 = var4.putLong(((Long)var12).longValue(), var7, var10);
                  } else if (var12 instanceof Integer) {
                     var16 = var4.putLong((long)((Integer)var12).intValue(), var7, var10);
                  } else if (var12 instanceof Boolean) {
                     boolean var13 = ((Boolean)var12).booleanValue();
                     var16 = var4.putLong(var13 ? 1L : 0L, var7, var10);
                  } else if (var12 instanceof byte[]) {
                     var16 = var4.putBlob((byte[])var12, var7, var10);
                  } else if (var12 instanceof Double) {
                     var16 = var4.putDouble(((Double)var12).doubleValue(), var7, var10);
                  } else {
                     if (!(var12 instanceof Float)) {
                        String var18 = String.valueOf(var12);
                        throw new IllegalArgumentException((new StringBuilder(32 + String.valueOf(var11).length() + String.valueOf(var18).length())).append("Unsupported object for column ").append(var11).append(": ").append(var18).toString());
                     }

                     var16 = var4.putDouble((double)((Float)var12).floatValue(), var7, var10);
                  }
               }

               if (!var16) {
                  if (var6) {
                     String var17 = "Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.";
                     throw new DataHolder.zzb(var17);
                  }

                  Log.d("DataHolder", (new StringBuilder(74)).append("Couldn't populate window data for row ").append(var7).append(" - allocating new window.").toString());
                  var4.freeLastRow();
                  (var4 = new CursorWindow(false)).setStartPosition(var7);
                  var4.setNumColumns(var0.zzaFB.length);
                  var5.add(var4);
                  --var7;
                  var6 = true;
               } else {
                  var6 = false;
               }
            }
         } catch (RuntimeException var14) {
            int var8 = 0;

            for(int var9 = var5.size(); var8 < var9; ++var8) {
               ((CursorWindow)var5.get(var8)).close();
            }

            throw var14;
         }

         return (CursorWindow[])var5.toArray(new CursorWindow[var5.size()]);
      }
   }

   private final void zzh(String var1, int var2) {
      if (this.zzaFC != null && this.zzaFC.containsKey(var1)) {
         if (this.isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
         } else if (var2 < 0 || var2 >= this.zzaFG) {
            throw new CursorIndexOutOfBoundsException(var2, this.zzaFG);
         }
      } else {
         IllegalArgumentException var10000 = new IllegalArgumentException;
         String var10003 = String.valueOf(var1);
         String var10002;
         if (var10003.length() != 0) {
            var10002 = "No such column: ".concat(var10003);
         } else {
            String var10004 = new String;
            var10002 = var10004;
            var10004.<init>("No such column: ");
         }

         var10000.<init>(var10002);
         throw var10000;
      }
   }

   public final boolean zzcv(String var1) {
      return this.zzaFC.containsKey(var1);
   }

   public final long zzb(String var1, int var2, int var3) {
      this.zzh(var1, var2);
      return this.zzaFD[var3].getLong(var2, this.zzaFC.getInt(var1));
   }

   public final int zzc(String var1, int var2, int var3) {
      this.zzh(var1, var2);
      return this.zzaFD[var3].getInt(var2, this.zzaFC.getInt(var1));
   }

   public final String zzd(String var1, int var2, int var3) {
      this.zzh(var1, var2);
      return this.zzaFD[var3].getString(var2, this.zzaFC.getInt(var1));
   }

   public final boolean zze(String var1, int var2, int var3) {
      this.zzh(var1, var2);
      return this.zzaFD[var3].getLong(var2, this.zzaFC.getInt(var1)).longValue() == 1L;
   }

   public final float zzf(String var1, int var2, int var3) {
      this.zzh(var1, var2);
      return this.zzaFD[var3].getFloat(var2, this.zzaFC.getInt(var1));
   }

   public final byte[] zzg(String var1, int var2, int var3) {
      this.zzh(var1, var2);
      return this.zzaFD[var3].getBlob(var2, this.zzaFC.getInt(var1));
   }

   public final void zza(String var1, int var2, int var3, CharArrayBuffer var4) {
      this.zzh(var1, var2);
      this.zzaFD[var3].copyStringToBuffer(var2, this.zzaFC.getInt(var1), var4);
   }

   public final boolean zzh(String var1, int var2, int var3) {
      this.zzh(var1, var2);
      return this.zzaFD[var3].isNull(var2, this.zzaFC.getInt(var1));
   }

   public final int getCount() {
      return this.zzaFG;
   }

   public final int zzat(int var1) {
      zzbo.zzae(var1 >= 0 && var1 < this.zzaFG);

      int var2;
      for(var2 = 0; var2 < this.zzaFF.length; ++var2) {
         if (var1 < this.zzaFF[var2]) {
            --var2;
            break;
         }
      }

      if (var2 == this.zzaFF.length) {
         --var2;
      }

      return var2;
   }

   public final boolean isClosed() {
      synchronized(this) {
         return this.mClosed;
      }
   }

   public final void close() {
      synchronized(this) {
         if (!this.mClosed) {
            this.mClosed = true;

            for(int var2 = 0; var2 < this.zzaFD.length; ++var2) {
               this.zzaFD[var2].close();
            }
         }

      }
   }

   protected final void finalize() throws Throwable {
      try {
         if (this.zzaFH && this.zzaFD.length > 0 && !this.isClosed()) {
            this.close();
            String var1 = String.valueOf(this.toString());
            Log.e("DataBuffer", (new StringBuilder(178 + String.valueOf(var1).length())).append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: ").append(var1).append(")").toString());
         }
      } finally {
         super.finalize();
      }

   }

   public static DataHolder.zza zza(String[] var0) {
      return new DataHolder.zza(var0, (String)null, (zze)null);
   }

   public static DataHolder zzau(int var0) {
      return new DataHolder(zzaFI, var0, (Bundle)null);
   }

   // $FF: synthetic method
   DataHolder(DataHolder.zza var1, int var2, Bundle var3, zze var4) {
      this(var1, 0, (Bundle)null);
   }

   public static class zzb extends RuntimeException {
      public zzb(String var1) {
         super(var1);
      }
   }

   public static class zza {
      private final String[] zzaFB;
      private final ArrayList zzaFJ;
      private final String zzaFK;
      private final HashMap zzaFL;
      private boolean zzaFM;
      private String zzaFN;

      private zza(String[] var1, String var2) {
         this.zzaFB = (String[])zzbo.zzu(var1);
         this.zzaFJ = new ArrayList();
         this.zzaFK = var2;
         this.zzaFL = new HashMap();
         this.zzaFM = false;
         this.zzaFN = null;
      }

      public DataHolder.zza zza(HashMap var1) {
         com.google.android.gms.common.internal.zzc.zzr(var1);
         int var10000;
         if (this.zzaFK == null) {
            var10000 = -1;
         } else {
            Object var5;
            if ((var5 = var1.get(this.zzaFK)) == null) {
               var10000 = -1;
            } else {
               Integer var6;
               if ((var6 = (Integer)this.zzaFL.get(var5)) == null) {
                  this.zzaFL.put(var5, this.zzaFJ.size());
                  var10000 = -1;
               } else {
                  var10000 = var6.intValue();
               }
            }
         }

         int var2 = var10000;
         if (var10000 == -1) {
            this.zzaFJ.add(var1);
         } else {
            this.zzaFJ.remove(var2);
            this.zzaFJ.add(var2, var1);
         }

         this.zzaFM = false;
         return this;
      }

      public DataHolder.zza zza(ContentValues var1) {
         com.google.android.gms.common.internal.zzc.zzr(var1);
         HashMap var2 = new HashMap(var1.size());
         Iterator var3 = var1.valueSet().iterator();

         while(var3.hasNext()) {
            Entry var4 = (Entry)var3.next();
            var2.put((String)var4.getKey(), var4.getValue());
         }

         return this.zza(var2);
      }

      public final DataHolder zzav(int var1) {
         return new DataHolder(this, 0, (Bundle)null, (zze)null);
      }

      // $FF: synthetic method
      zza(String[] var1, String var2, zze var3) {
         this(var1, (String)null);
      }
   }
}
