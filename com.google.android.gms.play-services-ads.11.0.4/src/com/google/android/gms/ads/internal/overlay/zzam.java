package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzzn;

@zzzn
final class zzam implements SensorEventListener {
   private final SensorManager zzPM;
   private final Object zzPN;
   private final Display zzPO;
   private final float[] zzPP;
   private final float[] zzPQ;
   private float[] zzPR;
   private Handler zzPS;
   private zzao zzPT;

   zzam(Context var1) {
      this.zzPM = (SensorManager)var1.getSystemService("sensor");
      WindowManager var2 = (WindowManager)var1.getSystemService("window");
      this.zzPO = var2.getDefaultDisplay();
      this.zzPP = new float[9];
      this.zzPQ = new float[9];
      this.zzPN = new Object();
   }

   final void start() {
      if (this.zzPS == null) {
         Sensor var1;
         if ((var1 = this.zzPM.getDefaultSensor(11)) == null) {
            zzafr.e("No Sensor of TYPE_ROTATION_VECTOR");
         } else {
            HandlerThread var2;
            (var2 = new HandlerThread("OrientationMonitor")).start();
            this.zzPS = new Handler(var2.getLooper());
            if (!this.zzPM.registerListener(this, var1, 0, this.zzPS)) {
               zzafr.e("SensorManager.registerListener failed.");
               this.stop();
            }

         }
      }
   }

   final void stop() {
      if (this.zzPS != null) {
         this.zzPM.unregisterListener(this);
         this.zzPS.post(new zzan(this));
         this.zzPS = null;
      }
   }

   final void zza(zzao var1) {
      this.zzPT = var1;
   }

   public final void onSensorChanged(SensorEvent var1) {
      float[] var3 = var1.values;
      zzam var2 = this;
      if (var3[0] != 0.0F || var3[1] != 0.0F || var3[2] != 0.0F) {
         Object var4 = this.zzPN;
         synchronized(this.zzPN) {
            if (var2.zzPR == null) {
               var2.zzPR = new float[9];
            }
         }

         SensorManager.getRotationMatrixFromVector(this.zzPP, var3);
         switch(this.zzPO.getRotation()) {
         case 1:
            SensorManager.remapCoordinateSystem(this.zzPP, 2, 129, this.zzPQ);
            break;
         case 2:
            SensorManager.remapCoordinateSystem(this.zzPP, 129, 130, this.zzPQ);
            break;
         case 3:
            SensorManager.remapCoordinateSystem(this.zzPP, 130, 1, this.zzPQ);
            break;
         default:
            System.arraycopy(this.zzPP, 0, this.zzPQ, 0, 9);
         }

         this.zze(1, 3);
         this.zze(2, 6);
         this.zze(5, 7);
         Object var5 = this.zzPN;
         synchronized(this.zzPN) {
            System.arraycopy(var2.zzPQ, 0, var2.zzPR, 0, 9);
         }

         if (this.zzPT != null) {
            this.zzPT.zzfO();
         }
      }

   }

   public final void onAccuracyChanged(Sensor var1, int var2) {
   }

   final boolean zza(float[] var1) {
      Object var2 = this.zzPN;
      synchronized(this.zzPN) {
         if (this.zzPR == null) {
            return false;
         } else {
            System.arraycopy(this.zzPR, 0, var1, 0, this.zzPR.length);
            return true;
         }
      }
   }

   private final void zze(int var1, int var2) {
      float var3 = this.zzPQ[var1];
      this.zzPQ[var1] = this.zzPQ[var2];
      this.zzPQ[var2] = var3;
   }
}
