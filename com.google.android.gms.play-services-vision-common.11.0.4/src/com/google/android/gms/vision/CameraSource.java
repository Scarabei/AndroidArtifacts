package com.google.android.gms.vision;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.os.SystemClock;
import android.support.annotation.RequiresPermission;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import com.google.android.gms.common.images.Size;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CameraSource {
   @SuppressLint({"InlinedApi"})
   public static final int CAMERA_FACING_BACK = 0;
   @SuppressLint({"InlinedApi"})
   public static final int CAMERA_FACING_FRONT = 1;
   private Context mContext;
   private final Object zzbMo;
   private Camera zzbMp;
   private int zzbMq;
   private int zzOa;
   private Size zzbMr;
   private float zzbMs;
   private int zzbMt;
   private int zzbMu;
   private boolean zzbMv;
   private SurfaceTexture zzbMw;
   private boolean zzbMx;
   private Thread zzbMy;
   private CameraSource.zzb zzbMz;
   private Map zzbMA;

   public void release() {
      Object var1 = this.zzbMo;
      synchronized(this.zzbMo) {
         this.stop();
         this.zzbMz.release();
      }
   }

   @RequiresPermission("android.permission.CAMERA")
   public CameraSource start() throws IOException {
      Object var1 = this.zzbMo;
      synchronized(this.zzbMo) {
         if (this.zzbMp != null) {
            return this;
         } else {
            this.zzbMp = this.zzDK();
            this.zzbMw = new SurfaceTexture(100);
            this.zzbMp.setPreviewTexture(this.zzbMw);
            this.zzbMx = true;
            this.zzbMp.startPreview();
            this.zzbMy = new Thread(this.zzbMz);
            this.zzbMz.setActive(true);
            this.zzbMy.start();
            return this;
         }
      }
   }

   @RequiresPermission("android.permission.CAMERA")
   public CameraSource start(SurfaceHolder var1) throws IOException {
      Object var2 = this.zzbMo;
      synchronized(this.zzbMo) {
         if (this.zzbMp != null) {
            return this;
         } else {
            this.zzbMp = this.zzDK();
            this.zzbMp.setPreviewDisplay(var1);
            this.zzbMp.startPreview();
            this.zzbMy = new Thread(this.zzbMz);
            this.zzbMz.setActive(true);
            this.zzbMy.start();
            this.zzbMx = false;
            return this;
         }
      }
   }

   public void stop() {
      Object var1 = this.zzbMo;
      synchronized(this.zzbMo) {
         this.zzbMz.setActive(false);
         if (this.zzbMy != null) {
            try {
               this.zzbMy.join();
            } catch (InterruptedException var6) {
               Log.d("CameraSource", "Frame processing thread interrupted on release.");
            }

            this.zzbMy = null;
         }

         if (this.zzbMp != null) {
            this.zzbMp.stopPreview();
            this.zzbMp.setPreviewCallbackWithBuffer((PreviewCallback)null);

            try {
               if (this.zzbMx) {
                  this.zzbMp.setPreviewTexture((SurfaceTexture)null);
               } else {
                  this.zzbMp.setPreviewDisplay((SurfaceHolder)null);
               }
            } catch (Exception var5) {
               String var3 = String.valueOf(var5);
               Log.e("CameraSource", (new StringBuilder(32 + String.valueOf(var3).length())).append("Failed to clear camera preview: ").append(var3).toString());
            }

            this.zzbMp.release();
            this.zzbMp = null;
         }

         this.zzbMA.clear();
      }
   }

   public Size getPreviewSize() {
      return this.zzbMr;
   }

   public int getCameraFacing() {
      return this.zzbMq;
   }

   public void takePicture(CameraSource.ShutterCallback var1, CameraSource.PictureCallback var2) {
      Object var3 = this.zzbMo;
      synchronized(this.zzbMo) {
         if (this.zzbMp != null) {
            CameraSource.zzd var4;
            (var4 = new CameraSource.zzd((zza)null)).zzbMI = var1;
            CameraSource.zzc var5;
            (var5 = new CameraSource.zzc((zza)null)).zzbMH = var2;
            this.zzbMp.takePicture(var4, (android.hardware.Camera.PictureCallback)null, (android.hardware.Camera.PictureCallback)null, var5);
         }

      }
   }

   private CameraSource() {
      this.zzbMo = new Object();
      this.zzbMq = 0;
      this.zzbMs = 30.0F;
      this.zzbMt = 1024;
      this.zzbMu = 768;
      this.zzbMv = false;
      this.zzbMA = new HashMap();
   }

   @SuppressLint({"InlinedApi"})
   private final Camera zzDK() throws IOException {
      int var7 = this.zzbMq;
      CameraInfo var8 = new CameraInfo();
      int var9 = 0;

      int var10000;
      while(true) {
         if (var9 >= Camera.getNumberOfCameras()) {
            var10000 = -1;
            break;
         }

         Camera.getCameraInfo(var9, var8);
         if (var8.facing == var7) {
            var10000 = var9;
            break;
         }

         ++var9;
      }

      int var1 = var10000;
      if (var10000 == -1) {
         throw new IOException("Could not find requested camera.");
      } else {
         Camera var2;
         CameraSource.zze var3;
         if ((var3 = zza(var2 = Camera.open(var1), this.zzbMt, this.zzbMu)) == null) {
            throw new IOException("Could not find suitable preview size.");
         } else {
            Size var4 = var3.zzDM();
            this.zzbMr = var3.zzDL();
            int[] var5;
            if ((var5 = zza(var2, this.zzbMs)) == null) {
               throw new IOException("Could not find suitable preview frames per second range.");
            } else {
               Parameters var6 = var2.getParameters();
               if (var4 != null) {
                  var6.setPictureSize(var4.getWidth(), var4.getHeight());
               }

               var6.setPreviewSize(this.zzbMr.getWidth(), this.zzbMr.getHeight());
               var6.setPreviewFpsRange(var5[0], var5[1]);
               var6.setPreviewFormat(17);
               WindowManager var11 = (WindowManager)this.mContext.getSystemService("window");
               short var12 = 0;
               int var13;
               switch(var13 = var11.getDefaultDisplay().getRotation()) {
               case 0:
                  var12 = 0;
                  break;
               case 1:
                  var12 = 90;
                  break;
               case 2:
                  var12 = 180;
                  break;
               case 3:
                  var12 = 270;
                  break;
               default:
                  Log.e("CameraSource", (new StringBuilder(31)).append("Bad rotation value: ").append(var13).toString());
               }

               CameraInfo var14 = new CameraInfo();
               Camera.getCameraInfo(var1, var14);
               int var15;
               int var16;
               if (var14.facing == 1) {
                  var15 = (var14.orientation + var12) % 360;
                  var16 = (360 - var15) % 360;
               } else {
                  var16 = var15 = (var14.orientation - var12 + 360) % 360;
               }

               this.zzOa = var15 / 90;
               var2.setDisplayOrientation(var16);
               var6.setRotation(var15);
               if (this.zzbMv) {
                  if (var6.getSupportedFocusModes().contains("continuous-video")) {
                     var6.setFocusMode("continuous-video");
                  } else {
                     Log.i("CameraSource", "Camera auto focus is not supported on this device.");
                  }
               }

               var2.setParameters(var6);
               var2.setPreviewCallbackWithBuffer(new CameraSource.zza((zza)null));
               var2.addCallbackBuffer(this.zza(this.zzbMr));
               var2.addCallbackBuffer(this.zza(this.zzbMr));
               var2.addCallbackBuffer(this.zza(this.zzbMr));
               var2.addCallbackBuffer(this.zza(this.zzbMr));
               return var2;
            }
         }
      }
   }

   private static CameraSource.zze zza(Camera var0, int var1, int var2) {
      Parameters var9;
      List var10 = (var9 = var0.getParameters()).getSupportedPreviewSizes();
      List var11 = var9.getSupportedPictureSizes();
      ArrayList var12 = new ArrayList();
      Iterator var13 = var10.iterator();

      while(true) {
         android.hardware.Camera.Size var14;
         while(var13.hasNext()) {
            float var15 = (float)(var14 = (android.hardware.Camera.Size)var13.next()).width / (float)var14.height;
            Iterator var16 = var11.iterator();

            while(var16.hasNext()) {
               android.hardware.Camera.Size var17;
               float var18 = (float)(var17 = (android.hardware.Camera.Size)var16.next()).width / (float)var17.height;
               if (Math.abs(var15 - var18) < 0.01F) {
                  var12.add(new CameraSource.zze(var14, var17));
                  break;
               }
            }
         }

         if (var12.size() == 0) {
            Log.w("CameraSource", "No preview sizes have a corresponding same-aspect-ratio picture size");
            var13 = var10.iterator();

            while(var13.hasNext()) {
               var14 = (android.hardware.Camera.Size)var13.next();
               var12.add(new CameraSource.zze(var14, (android.hardware.Camera.Size)null));
            }
         }

         CameraSource.zze var4 = null;
         int var5 = Integer.MAX_VALUE;
         ArrayList var19;
         int var20 = (var19 = (ArrayList)var12).size();
         int var21 = 0;

         while(var21 < var20) {
            Object var10000 = var19.get(var21);
            ++var21;
            CameraSource.zze var6;
            Size var7;
            int var8;
            if ((var8 = Math.abs((var7 = (var6 = (CameraSource.zze)var10000).zzDL()).getWidth() - var1) + Math.abs(var7.getHeight() - var2)) < var5) {
               var4 = var6;
               var5 = var8;
            }
         }

         return var4;
      }
   }

   @SuppressLint({"InlinedApi"})
   private static int[] zza(Camera var0, float var1) {
      int var2 = (int)(var1 * 1000.0F);
      int[] var3 = null;
      int var4 = Integer.MAX_VALUE;
      Iterator var5 = var0.getParameters().getSupportedPreviewFpsRange().iterator();

      while(var5.hasNext()) {
         int[] var6 = (int[])var5.next();
         int var7 = var2 - var6[0];
         int var8 = var2 - var6[1];
         int var9;
         if ((var9 = Math.abs(var7) + Math.abs(var8)) < var4) {
            var3 = var6;
            var4 = var9;
         }
      }

      return var3;
   }

   @SuppressLint({"InlinedApi"})
   private final byte[] zza(Size var1) {
      int var2 = ImageFormat.getBitsPerPixel(17);
      byte[] var3;
      ByteBuffer var4;
      if ((var4 = ByteBuffer.wrap(var3 = new byte[(int)Math.ceil((double)((long)(var1.getHeight() * var1.getWidth() * var2)) / 8.0D) + 1])).hasArray() && var4.array() == var3) {
         this.zzbMA.put(var3, var4);
         return var3;
      } else {
         throw new IllegalStateException("Failed to create valid buffer for camera source.");
      }
   }

   // $FF: synthetic method
   CameraSource(zza var1) {
      this();
   }

   class zzb implements Runnable {
      private Detector zzbMB;
      private long zzagZ;
      private final Object mLock;
      private boolean mActive;
      private long zzbME;
      private int zzbMF;
      private ByteBuffer zzbMG;
      // $FF: synthetic field
      private CameraSource zzbMD;

      zzb(Detector var2) {
         this.zzbMD = CameraSource.this;
         super();
         this.zzagZ = SystemClock.elapsedRealtime();
         this.mLock = new Object();
         this.mActive = true;
         this.zzbMF = 0;
         this.zzbMB = var2;
      }

      @SuppressLint({"Assert"})
      final void release() {
         this.zzbMB.release();
         this.zzbMB = null;
      }

      final void setActive(boolean var1) {
         Object var2 = this.mLock;
         synchronized(this.mLock) {
            this.mActive = var1;
            this.mLock.notifyAll();
         }
      }

      final void zza(byte[] var1, Camera var2) {
         Object var3 = this.mLock;
         synchronized(this.mLock) {
            if (this.zzbMG != null) {
               var2.addCallbackBuffer(this.zzbMG.array());
               this.zzbMG = null;
            }

            if (!this.zzbMD.zzbMA.containsKey(var1)) {
               Log.d("CameraSource", "Skipping frame. Could not find ByteBuffer associated with the image data from the camera.");
            } else {
               this.zzbME = SystemClock.elapsedRealtime() - this.zzagZ;
               ++this.zzbMF;
               this.zzbMG = (ByteBuffer)this.zzbMD.zzbMA.get(var1);
               this.mLock.notifyAll();
            }
         }
      }

      @SuppressLint({"InlinedApi"})
      public final void run() {
         while(true) {
            Object var3 = this.mLock;
            Frame var1;
            ByteBuffer var2;
            synchronized(this.mLock) {
               while(this.mActive && this.zzbMG == null) {
                  try {
                     this.mLock.wait();
                  } catch (InterruptedException var13) {
                     Log.d("CameraSource", "Frame processing loop terminated.", var13);
                     return;
                  }
               }

               if (!this.mActive) {
                  return;
               }

               var1 = (new Frame.Builder()).setImageData(this.zzbMG, this.zzbMD.zzbMr.getWidth(), this.zzbMD.zzbMr.getHeight(), 17).setId(this.zzbMF).setTimestampMillis(this.zzbME).setRotation(this.zzbMD.zzOa).build();
               var2 = this.zzbMG;
               this.zzbMG = null;
            }

            try {
               this.zzbMB.receiveFrame(var1);
            } catch (Throwable var11) {
               Log.e("CameraSource", "Exception thrown from receiver.", var11);
            } finally {
               this.zzbMD.zzbMp.addCallbackBuffer(var2.array());
            }
         }
      }
   }

   class zza implements PreviewCallback {
      // $FF: synthetic field
      private CameraSource zzbMD;

      private zza() {
         this.zzbMD = CameraSource.this;
         super();
      }

      public final void onPreviewFrame(byte[] var1, Camera var2) {
         this.zzbMD.zzbMz.zza(var1, var2);
      }

      // $FF: synthetic method
      zza(zza var2) {
         this();
      }
   }

   static class zze {
      private Size zzbMJ;
      private Size zzbMK;

      public zze(android.hardware.Camera.Size var1, android.hardware.Camera.Size var2) {
         this.zzbMJ = new Size(var1.width, var1.height);
         if (var2 != null) {
            this.zzbMK = new Size(var2.width, var2.height);
         }

      }

      public final Size zzDL() {
         return this.zzbMJ;
      }

      public final Size zzDM() {
         return this.zzbMK;
      }
   }

   class zzc implements android.hardware.Camera.PictureCallback {
      private CameraSource.PictureCallback zzbMH;
      // $FF: synthetic field
      private CameraSource zzbMD;

      private zzc() {
         this.zzbMD = CameraSource.this;
         super();
      }

      public final void onPictureTaken(byte[] var1, Camera var2) {
         if (this.zzbMH != null) {
            this.zzbMH.onPictureTaken(var1);
         }

         synchronized(this.zzbMD.zzbMo) {
            if (this.zzbMD.zzbMp != null) {
               this.zzbMD.zzbMp.startPreview();
            }

         }
      }

      // $FF: synthetic method
      zzc(zza var2) {
         this();
      }
   }

   static class zzd implements android.hardware.Camera.ShutterCallback {
      private CameraSource.ShutterCallback zzbMI;

      private zzd() {
      }

      public final void onShutter() {
         if (this.zzbMI != null) {
            this.zzbMI.onShutter();
         }

      }

      // $FF: synthetic method
      zzd(zza var1) {
         this();
      }
   }

   public interface PictureCallback {
      void onPictureTaken(byte[] var1);
   }

   public interface ShutterCallback {
      void onShutter();
   }

   public static class Builder {
      private final Detector zzbMB;
      private CameraSource zzbMC = new CameraSource((zza)null);

      public Builder(Context var1, Detector var2) {
         if (var1 == null) {
            throw new IllegalArgumentException("No context supplied.");
         } else if (var2 == null) {
            throw new IllegalArgumentException("No detector supplied.");
         } else {
            this.zzbMB = var2;
            this.zzbMC.mContext = var1;
         }
      }

      public CameraSource.Builder setRequestedFps(float var1) {
         if (var1 <= 0.0F) {
            throw new IllegalArgumentException((new StringBuilder(28)).append("Invalid fps: ").append(var1).toString());
         } else {
            this.zzbMC.zzbMs = var1;
            return this;
         }
      }

      public CameraSource.Builder setRequestedPreviewSize(int var1, int var2) {
         if (var1 > 0 && var1 <= 1000000 && var2 > 0 && var2 <= 1000000) {
            this.zzbMC.zzbMt = var1;
            this.zzbMC.zzbMu = var2;
            return this;
         } else {
            throw new IllegalArgumentException((new StringBuilder(45)).append("Invalid preview size: ").append(var1).append("x").append(var2).toString());
         }
      }

      public CameraSource.Builder setFacing(int var1) {
         if (var1 != 0 && var1 != 1) {
            throw new IllegalArgumentException((new StringBuilder(27)).append("Invalid camera: ").append(var1).toString());
         } else {
            this.zzbMC.zzbMq = var1;
            return this;
         }
      }

      public CameraSource.Builder setAutoFocusEnabled(boolean var1) {
         this.zzbMC.zzbMv = var1;
         return this;
      }

      public CameraSource build() {
         CameraSource var10000 = this.zzbMC;
         CameraSource var10003 = this.zzbMC;
         this.zzbMC.getClass();
         var10000.zzbMz = var10003.new zzb(this.zzbMB);
         return this.zzbMC;
      }
   }
}
