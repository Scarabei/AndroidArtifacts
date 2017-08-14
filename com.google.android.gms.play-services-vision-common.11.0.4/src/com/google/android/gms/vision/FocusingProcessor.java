package com.google.android.gms.vision;

import android.util.Log;
import android.util.SparseArray;

public abstract class FocusingProcessor implements Detector.Processor {
   private Detector zzbMB;
   private Tracker zzbMQ;
   private int zzbMR = 3;
   private boolean zzbMS = false;
   private int zzbMT;
   private int zzbMU = 0;

   public FocusingProcessor(Detector var1, Tracker var2) {
      this.zzbMB = var1;
      this.zzbMQ = var2;
   }

   public void release() {
      this.zzbMQ.onDone();
   }

   public void receiveDetections(Detector.Detections var1) {
      SparseArray var2;
      if ((var2 = var1.getDetectedItems()).size() == 0) {
         if (this.zzbMU == this.zzbMR) {
            this.zzbMQ.onDone();
            this.zzbMS = false;
         } else {
            this.zzbMQ.onMissing(var1);
         }

         ++this.zzbMU;
      } else {
         this.zzbMU = 0;
         if (this.zzbMS) {
            Object var3;
            if ((var3 = var2.get(this.zzbMT)) != null) {
               this.zzbMQ.onUpdate(var1, var3);
               return;
            }

            this.zzbMQ.onDone();
            this.zzbMS = false;
         }

         int var5 = this.selectFocus(var1);
         Object var4;
         if ((var4 = var2.get(var5)) == null) {
            Log.w("FocusingProcessor", (new StringBuilder(35)).append("Invalid focus selected: ").append(var5).toString());
         } else {
            this.zzbMS = true;
            this.zzbMT = var5;
            this.zzbMB.setFocus(this.zzbMT);
            this.zzbMQ.onNewItem(this.zzbMT, var4);
            this.zzbMQ.onUpdate(var1, var4);
         }
      }
   }

   public abstract int selectFocus(Detector.Detections var1);

   protected final void zzbK(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException((new StringBuilder(28)).append("Invalid max gap: ").append(var1).toString());
      } else {
         this.zzbMR = var1;
      }
   }
}
