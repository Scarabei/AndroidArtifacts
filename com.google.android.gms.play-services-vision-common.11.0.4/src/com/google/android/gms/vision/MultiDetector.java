package com.google.android.gms.vision;

import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MultiDetector extends Detector {
   private List zzbNb;

   public void release() {
      Iterator var1 = this.zzbNb.iterator();

      while(var1.hasNext()) {
         ((Detector)var1.next()).release();
      }

      this.zzbNb.clear();
   }

   public SparseArray detect(Frame var1) {
      SparseArray var2 = new SparseArray();
      Iterator var3 = this.zzbNb.iterator();

      while(var3.hasNext()) {
         SparseArray var4 = ((Detector)var3.next()).detect(var1);

         for(int var5 = 0; var5 < var4.size(); ++var5) {
            int var6 = var4.keyAt(var5);
            if (var2.get(var6) != null) {
               throw new IllegalStateException((new StringBuilder(104)).append("Detection ID overlap for id = ").append(var6).append("  This means that one of the detectors is not using global IDs.").toString());
            }

            Object var7 = var4.valueAt(var5);
            var2.append(var6, var7);
         }
      }

      return var2;
   }

   public void receiveFrame(Frame var1) {
      Iterator var2 = this.zzbNb.iterator();

      while(var2.hasNext()) {
         ((Detector)var2.next()).receiveFrame(var1);
      }

   }

   public void setProcessor(Detector.Processor var1) {
      throw new UnsupportedOperationException("MultiDetector.setProcessor is not supported.  You should set a processor instance on each underlying detector instead.");
   }

   public boolean isOperational() {
      Iterator var1 = this.zzbNb.iterator();

      do {
         if (!var1.hasNext()) {
            return true;
         }
      } while(((Detector)var1.next()).isOperational());

      return false;
   }

   private MultiDetector() {
      this.zzbNb = new ArrayList();
   }

   // $FF: synthetic method
   MultiDetector(zzd var1) {
      this();
   }

   public static class Builder {
      private MultiDetector zzbNc = new MultiDetector((zzd)null);

      public MultiDetector.Builder add(Detector var1) {
         this.zzbNc.zzbNb.add(var1);
         return this;
      }

      public MultiDetector build() {
         if (this.zzbNc.zzbNb.size() == 0) {
            throw new RuntimeException("No underlying detectors added to MultiDetector.");
         } else {
            return this.zzbNc;
         }
      }
   }
}
