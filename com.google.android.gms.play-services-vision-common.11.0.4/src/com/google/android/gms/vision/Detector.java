package com.google.android.gms.vision;

import android.util.SparseArray;

public abstract class Detector {
   private final Object zzbML = new Object();
   private Detector.Processor zzbMM;

   public void release() {
      Object var1 = this.zzbML;
      synchronized(this.zzbML) {
         if (this.zzbMM != null) {
            this.zzbMM.release();
            this.zzbMM = null;
         }

      }
   }

   public abstract SparseArray detect(Frame var1);

   public boolean setFocus(int var1) {
      return true;
   }

   public void receiveFrame(Frame var1) {
      Object var2 = this.zzbML;
      synchronized(this.zzbML) {
         if (this.zzbMM == null) {
            throw new IllegalStateException("Detector processor must first be set with setProcessor in order to receive detection results.");
         } else {
            Frame.Metadata var3;
            (var3 = new Frame.Metadata(var1.getMetadata())).zzDN();
            SparseArray var4 = this.detect(var1);
            boolean var5 = this.isOperational();
            Detector.Detections var6 = new Detector.Detections(var4, var3, var5);
            this.zzbMM.receiveDetections(var6);
         }
      }
   }

   public void setProcessor(Detector.Processor var1) {
      this.zzbMM = var1;
   }

   public boolean isOperational() {
      return true;
   }

   public interface Processor {
      void release();

      void receiveDetections(Detector.Detections var1);
   }

   public static class Detections {
      private SparseArray zzbMN;
      private Frame.Metadata zzbMO;
      private boolean zzbMP;

      public Detections(SparseArray var1, Frame.Metadata var2, boolean var3) {
         this.zzbMN = var1;
         this.zzbMO = var2;
         this.zzbMP = var3;
      }

      public SparseArray getDetectedItems() {
         return this.zzbMN;
      }

      public Frame.Metadata getFrameMetadata() {
         return this.zzbMO;
      }

      public boolean detectorIsOperational() {
         return this.zzbMP;
      }
   }
}
