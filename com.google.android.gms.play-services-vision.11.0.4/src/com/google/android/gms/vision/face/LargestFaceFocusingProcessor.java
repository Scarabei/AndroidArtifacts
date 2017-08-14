package com.google.android.gms.vision.face;

import android.util.SparseArray;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.FocusingProcessor;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.Detector.Detections;

public class LargestFaceFocusingProcessor extends FocusingProcessor {
   public LargestFaceFocusingProcessor(Detector var1, Tracker var2) {
      super(var1, var2);
   }

   public int selectFocus(Detections var1) {
      SparseArray var2;
      if ((var2 = var1.getDetectedItems()).size() == 0) {
         throw new IllegalArgumentException("No faces for selectFocus.");
      } else {
         int var3 = var2.keyAt(0);
         float var4 = ((Face)var2.valueAt(0)).getWidth();

         for(int var5 = 1; var5 < var2.size(); ++var5) {
            int var6 = var2.keyAt(var5);
            float var7;
            if ((var7 = ((Face)var2.valueAt(var5)).getWidth()) > var4) {
               var3 = var6;
               var4 = var7;
            }
         }

         return var3;
      }
   }

   public static class Builder {
      private LargestFaceFocusingProcessor zzbNy;

      public Builder(Detector var1, Tracker var2) {
         this.zzbNy = new LargestFaceFocusingProcessor(var1, var2);
      }

      public LargestFaceFocusingProcessor.Builder setMaxGapFrames(int var1) {
         this.zzbNy.zzbK(var1);
         return this;
      }

      public LargestFaceFocusingProcessor build() {
         return this.zzbNy;
      }
   }
}
