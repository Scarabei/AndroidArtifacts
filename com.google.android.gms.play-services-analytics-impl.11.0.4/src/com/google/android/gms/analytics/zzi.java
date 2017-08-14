package com.google.android.gms.analytics;

import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class zzi {
   private final zzk zzadQ;
   private final com.google.android.gms.common.util.zze zzvw;
   private boolean zzadR;
   private long zzadS;
   private long zzadT;
   private long zzadU;
   private long zzadV;
   private long zzadW;
   private boolean zzadX;
   private final Map zzadY;
   private final List zzadZ;

   public final zzi zzjp() {
      return new zzi(this);
   }

   public final void zza(zzj var1) {
      zzbo.zzu(var1);
      Class var2;
      if ((var2 = var1.getClass()).getSuperclass() != zzj.class) {
         throw new IllegalArgumentException();
      } else {
         var1.zzb(this.zzb(var2));
      }
   }

   public final zzj zza(Class var1) {
      return (zzj)this.zzadY.get(var1);
   }

   public final zzj zzb(Class var1) {
      zzj var2;
      if ((var2 = (zzj)this.zzadY.get(var1)) == null) {
         var2 = zzc(var1);
         this.zzadY.put(var1, var2);
      }

      return var2;
   }

   public final Collection zzjq() {
      return this.zzadY.values();
   }

   public final List zzjr() {
      return this.zzadZ;
   }

   public final long zzjs() {
      return this.zzadS;
   }

   public final void zzl(long var1) {
      this.zzadT = var1;
   }

   public final void zzjt() {
      this.zzadQ.zzjz().zze(this);
   }

   zzi(zzk var1, com.google.android.gms.common.util.zze var2) {
      zzbo.zzu(var1);
      zzbo.zzu(var2);
      this.zzadQ = var1;
      this.zzvw = var2;
      this.zzadV = 1800000L;
      this.zzadW = 3024000000L;
      this.zzadY = new HashMap();
      this.zzadZ = new ArrayList();
   }

   private zzi(zzi var1) {
      this.zzadQ = var1.zzadQ;
      this.zzvw = var1.zzvw;
      this.zzadS = var1.zzadS;
      this.zzadT = var1.zzadT;
      this.zzadU = var1.zzadU;
      this.zzadV = var1.zzadV;
      this.zzadW = var1.zzadW;
      this.zzadZ = new ArrayList(var1.zzadZ);
      this.zzadY = new HashMap(var1.zzadY.size());
      Iterator var2 = var1.zzadY.entrySet().iterator();

      while(var2.hasNext()) {
         Entry var3;
         zzj var4 = zzc((Class)(var3 = (Entry)var2.next()).getKey());
         ((zzj)var3.getValue()).zzb(var4);
         this.zzadY.put((Class)var3.getKey(), var4);
      }

   }

   private static zzj zzc(Class var0) {
      try {
         return (zzj)var0.newInstance();
      } catch (InstantiationException var2) {
         throw new IllegalArgumentException("dataType doesn't have default constructor", var2);
      } catch (IllegalAccessException var3) {
         throw new IllegalArgumentException("dataType default constructor is not accessible", var3);
      }
   }

   public final boolean zzju() {
      return this.zzadR;
   }

   final void zzjv() {
      this.zzadU = this.zzvw.elapsedRealtime();
      if (this.zzadT != 0L) {
         this.zzadS = this.zzadT;
      } else {
         this.zzadS = this.zzvw.currentTimeMillis();
      }

      this.zzadR = true;
   }

   final zzk zzjw() {
      return this.zzadQ;
   }

   final boolean zzjx() {
      return this.zzadX;
   }

   final void zzjy() {
      this.zzadX = true;
   }
}
