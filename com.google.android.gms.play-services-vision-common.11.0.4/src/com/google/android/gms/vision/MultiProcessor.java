package com.google.android.gms.vision;

import android.util.SparseArray;
import java.util.HashSet;
import java.util.Iterator;

public class MultiProcessor implements Detector.Processor {
   private MultiProcessor.Factory zzbNd;
   private SparseArray zzbNe;
   private int zzbMR;

   public void release() {
      for(int var1 = 0; var1 < this.zzbNe.size(); ++var1) {
         ((MultiProcessor.zza)this.zzbNe.valueAt(var1)).zzbMQ.onDone();
      }

      this.zzbNe.clear();
   }

   public void receiveDetections(Detector.Detections var1) {
      MultiProcessor var2 = this;
      SparseArray var4 = var1.getDetectedItems();

      int var6;
      MultiProcessor.zza var8;
      for(int var5 = 0; var5 < var4.size(); ++var5) {
         var6 = var4.keyAt(var5);
         Object var7 = var4.valueAt(var5);
         if (var2.zzbNe.get(var6) == null) {
            (var8 = var2.new zza((zze)null)).zzbMQ = var2.zzbNd.create(var7);
            var8.zzbMQ.onNewItem(var6, var7);
            var2.zzbNe.append(var6, var8);
         }
      }

      Detector.Detections var3 = var1;
      var2 = this;
      var4 = var1.getDetectedItems();
      HashSet var9 = new HashSet();

      for(var6 = 0; var6 < var2.zzbNe.size(); ++var6) {
         int var11 = var2.zzbNe.keyAt(var6);
         if (var4.get(var11) == null) {
            (var8 = (MultiProcessor.zza)var2.zzbNe.valueAt(var6)).zzbMU++;
            if (var8.zzbMU >= var2.zzbMR) {
               var8.zzbMQ.onDone();
               var9.add(var11);
            } else {
               var8.zzbMQ.onMissing(var3);
            }
         }
      }

      Iterator var10 = var9.iterator();

      while(var10.hasNext()) {
         Integer var12 = (Integer)var10.next();
         var2.zzbNe.delete(var12.intValue());
      }

      this.zza(var1);
   }

   private MultiProcessor() {
      this.zzbNe = new SparseArray();
      this.zzbMR = 3;
   }

   private final void zza(Detector.Detections var1) {
      SparseArray var2 = var1.getDetectedItems();

      for(int var3 = 0; var3 < var2.size(); ++var3) {
         int var4 = var2.keyAt(var3);
         Object var5 = var2.valueAt(var3);
         MultiProcessor.zza var6;
         MultiProcessor.zza.zza(var6 = (MultiProcessor.zza)this.zzbNe.get(var4), 0);
         var6.zzbMQ.onUpdate(var1, var5);
      }

   }

   // $FF: synthetic method
   MultiProcessor(zze var1) {
      this();
   }

   class zza {
      private Tracker zzbMQ;
      private int zzbMU;

      private zza() {
         this.zzbMU = 0;
      }

      // $FF: synthetic method
      zza(zze var2) {
         this();
      }

      // $FF: synthetic method
      static int zza(MultiProcessor.zza var0, int var1) {
         return var0.zzbMU = 0;
      }
   }

   public static class Builder {
      private MultiProcessor zzbNf = new MultiProcessor((zze)null);

      public Builder(MultiProcessor.Factory var1) {
         if (var1 == null) {
            throw new IllegalArgumentException("No factory supplied.");
         } else {
            this.zzbNf.zzbNd = var1;
         }
      }

      public MultiProcessor.Builder setMaxGapFrames(int var1) {
         if (var1 < 0) {
            throw new IllegalArgumentException((new StringBuilder(28)).append("Invalid max gap: ").append(var1).toString());
         } else {
            this.zzbNf.zzbMR = var1;
            return this;
         }
      }

      public MultiProcessor build() {
         return this.zzbNf;
      }
   }

   public interface Factory {
      Tracker create(Object var1);
   }
}
