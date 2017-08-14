package com.google.android.gms.ads.internal.overlay;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;
import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmo;
import com.google.android.gms.internal.zzzn;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.concurrent.CountDownLatch;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

@zzzn
@TargetApi(14)
public final class zzap extends Thread implements OnFrameAvailableListener, zzao {
   private static final float[] zzPU = new float[]{-1.0F, -1.0F, -1.0F, 1.0F, -1.0F, -1.0F, -1.0F, 1.0F, -1.0F, 1.0F, 1.0F, -1.0F};
   private final zzam zzPV;
   private final float[] zzPR;
   private final float[] zzPW;
   private final float[] zzPX;
   private final float[] zzPY;
   private final float[] zzPZ;
   private final float[] zzQa;
   private final float[] zzQb;
   private float zzQc;
   private float zzQd;
   private float zzQe;
   private int zzrX;
   private int zzrW;
   private SurfaceTexture zzQf;
   private SurfaceTexture zzQg;
   private int zzQh;
   private int zzQi;
   private int zzQj;
   private FloatBuffer zzQk;
   private final CountDownLatch zzQl;
   private final Object zzQm;
   private EGL10 zzQn;
   private EGLDisplay zzQo;
   private EGLContext zzQp;
   private EGLSurface zzQq;
   private volatile boolean zzQr;
   private volatile boolean zzQs;

   public zzap(Context var1) {
      super("SphericalVideoProcessor");
      this.zzQk = ByteBuffer.allocateDirect(zzPU.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
      this.zzQk.put(zzPU).position(0);
      this.zzPR = new float[9];
      this.zzPW = new float[9];
      this.zzPX = new float[9];
      this.zzPY = new float[9];
      this.zzPZ = new float[9];
      this.zzQa = new float[9];
      this.zzQb = new float[9];
      this.zzQc = Float.NaN;
      this.zzPV = new zzam(var1);
      this.zzPV.zza((zzao)this);
      this.zzQl = new CountDownLatch(1);
      this.zzQm = new Object();
   }

   public final void zza(SurfaceTexture var1, int var2, int var3) {
      this.zzrW = var2;
      this.zzrX = var3;
      this.zzQg = var1;
   }

   public final void zzf(int var1, int var2) {
      Object var3 = this.zzQm;
      synchronized(this.zzQm) {
         this.zzrW = var1;
         this.zzrX = var2;
         this.zzQr = true;
         this.zzQm.notifyAll();
      }
   }

   public final void zzgf() {
      Object var1 = this.zzQm;
      synchronized(this.zzQm) {
         this.zzQs = true;
         this.zzQg = null;
         this.zzQm.notifyAll();
      }
   }

   public final SurfaceTexture zzgg() {
      if (this.zzQg == null) {
         return null;
      } else {
         try {
            this.zzQl.await();
         } catch (InterruptedException var1) {
            ;
         }

         return this.zzQf;
      }
   }

   public final void onFrameAvailable(SurfaceTexture var1) {
      ++this.zzQj;
      Object var2 = this.zzQm;
      synchronized(this.zzQm) {
         this.zzQm.notifyAll();
      }
   }

   public final void zzfO() {
      Object var1 = this.zzQm;
      synchronized(this.zzQm) {
         this.zzQm.notifyAll();
      }
   }

   public final void run() {
      if (this.zzQg == null) {
         zzafr.e("SphericalVideoProcessor started with no output texture.");
         this.zzQl.countDown();
      } else {
         this.zzQn = (EGL10)EGLContext.getEGL();
         this.zzQo = this.zzQn.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
         boolean var10000;
         if (this.zzQo == EGL10.EGL_NO_DISPLAY) {
            var10000 = false;
         } else {
            int[] var9 = new int[2];
            if (!this.zzQn.eglInitialize(this.zzQo, var9)) {
               var10000 = false;
            } else {
               int[] var13 = new int[1];
               EGLConfig[] var14 = new EGLConfig[1];
               int[] var15 = new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12325, 16, 12344};
               EGLConfig var37 = this.zzQn.eglChooseConfig(this.zzQo, var15, var14, 1, var13) && var13[0] > 0 ? var14[0] : null;
               EGLConfig var10 = var37;
               if (var37 == null) {
                  var10000 = false;
               } else {
                  int[] var11 = new int[]{12440, 2, 12344};
                  this.zzQp = this.zzQn.eglCreateContext(this.zzQo, var10, EGL10.EGL_NO_CONTEXT, var11);
                  if (this.zzQp != null && this.zzQp != EGL10.EGL_NO_CONTEXT) {
                     this.zzQq = this.zzQn.eglCreateWindowSurface(this.zzQo, var10, this.zzQg, (int[])null);
                     var10000 = this.zzQq != null && this.zzQq != EGL10.EGL_NO_SURFACE ? this.zzQn.eglMakeCurrent(this.zzQo, this.zzQq, this.zzQq, this.zzQp) : false;
                  } else {
                     var10000 = false;
                  }
               }
            }
         }

         boolean var1 = var10000;
         zzme var18 = zzmo.zzEn;
         zzme var19 = zzmo.zzEn;
         int var10001;
         int var35;
         if ((var35 = zzc(35633, !((String)zzbs.zzbL().zzd(var19)).equals(var18.zzdI()) ? (String)zzbs.zzbL().zzd(var18) : "attribute highp vec3 aPosition;varying vec3 pos;void main() {  gl_Position = vec4(aPosition, 1.0);  pos = aPosition;}")) == 0) {
            var10001 = 0;
         } else {
            var18 = zzmo.zzEo;
            var19 = zzmo.zzEo;
            int var36;
            if ((var36 = zzc(35632, !((String)zzbs.zzbL().zzd(var19)).equals(var18.zzdI()) ? (String)zzbs.zzbL().zzd(var18) : "#extension GL_OES_EGL_image_external : require\n#define INV_PI 0.3183\nprecision highp float;varying vec3 pos;uniform samplerExternalOES uSplr;uniform mat3 uVMat;uniform float uFOVx;uniform float uFOVy;void main() {  vec3 ray = vec3(pos.x * tan(uFOVx), pos.y * tan(uFOVy), -1);  ray = (uVMat * ray).xyz;  ray = normalize(ray);  vec2 texCrd = vec2(    0.5 + atan(ray.x, - ray.z) * INV_PI * 0.5, acos(ray.y) * INV_PI);  gl_FragColor = vec4(texture2D(uSplr, texCrd).xyz, 1.0);}")) == 0) {
               var10001 = 0;
            } else {
               int var16 = GLES20.glCreateProgram();
               zzar("createProgram");
               if (var16 != 0) {
                  GLES20.glAttachShader(var16, var35);
                  zzar("attachShader");
                  GLES20.glAttachShader(var16, var36);
                  zzar("attachShader");
                  GLES20.glLinkProgram(var16);
                  zzar("linkProgram");
                  int[] var17 = new int[1];
                  GLES20.glGetProgramiv(var16, 35714, var17, 0);
                  zzar("getProgramiv");
                  if (var17[0] != 1) {
                     Log.e("SphericalVideoRenderer", "Could not link program: ");
                     Log.e("SphericalVideoRenderer", GLES20.glGetProgramInfoLog(var16));
                     GLES20.glDeleteProgram(var16);
                     zzar("deleteProgram");
                     var16 = 0;
                  } else {
                     GLES20.glValidateProgram(var16);
                     zzar("validateProgram");
                  }
               }

               var10001 = var16;
            }
         }

         this.zzQh = var10001;
         GLES20.glUseProgram(this.zzQh);
         zzar("useProgram");
         int var31;
         GLES20.glVertexAttribPointer(var31 = GLES20.glGetAttribLocation(this.zzQh, "aPosition"), 3, 5126, false, 12, this.zzQk);
         zzar("vertexAttribPointer");
         GLES20.glEnableVertexAttribArray(var31);
         zzar("enableVertexAttribArray");
         int[] var32 = new int[1];
         GLES20.glGenTextures(1, var32, 0);
         zzar("genTextures");
         int var34 = var32[0];
         GLES20.glBindTexture(36197, var34);
         zzar("bindTextures");
         GLES20.glTexParameteri(36197, 10240, 9729);
         zzar("texParameteri");
         GLES20.glTexParameteri(36197, 10241, 9729);
         zzar("texParameteri");
         GLES20.glTexParameteri(36197, 10242, 33071);
         zzar("texParameteri");
         GLES20.glTexParameteri(36197, 10243, 33071);
         zzar("texParameteri");
         this.zzQi = GLES20.glGetUniformLocation(this.zzQh, "uVMat");
         float[] var12 = new float[]{1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F};
         GLES20.glUniformMatrix3fv(this.zzQi, 1, false, var12, 0);
         boolean var3 = this.zzQh != 0;
         if (var1 && var3) {
            this.zzQf = new SurfaceTexture(var34);
            this.zzQf.setOnFrameAvailableListener(this);
            this.zzQl.countDown();
            this.zzPV.start();

            try {
               this.zzQr = true;

               while(!this.zzQs) {
                  this.zzgh();
                  if (this.zzQr) {
                     GLES20.glViewport(0, 0, this.zzrW, this.zzrX);
                     zzar("viewport");
                     var31 = GLES20.glGetUniformLocation(this.zzQh, "uFOVx");
                     int var33 = GLES20.glGetUniformLocation(this.zzQh, "uFOVy");
                     if (this.zzrW > this.zzrX) {
                        GLES20.glUniform1f(var31, 0.87266463F);
                        GLES20.glUniform1f(var33, 0.87266463F * (float)this.zzrX / (float)this.zzrW);
                     } else {
                        GLES20.glUniform1f(var31, 0.87266463F * (float)this.zzrW / (float)this.zzrX);
                        GLES20.glUniform1f(var33, 0.87266463F);
                     }

                     this.zzQr = false;
                  }

                  try {
                     Object var30 = this.zzQm;
                     synchronized(this.zzQm) {
                        if (!this.zzQs && !this.zzQr && this.zzQj == 0) {
                           this.zzQm.wait();
                        }
                     }
                  } catch (InterruptedException var26) {
                     ;
                  }
               }

               return;
            } catch (IllegalStateException var27) {
               zzafr.zzaT("SphericalVideoProcessor halted unexpectedly.");
               return;
            } catch (Throwable var28) {
               zzafr.zzb("SphericalVideoProcessor died.", var28);
               zzbs.zzbD().zza(var28, "SphericalVideoProcessor.run.2");
            } finally {
               this.zzPV.stop();
               this.zzQf.setOnFrameAvailableListener((OnFrameAvailableListener)null);
               this.zzQf = null;
               this.zzgi();
            }

         } else {
            String var4 = GLUtils.getEGLErrorString(this.zzQn.eglGetError());
            String var38 = String.valueOf(var4);
            String var39;
            if (var38.length() != 0) {
               var39 = "EGL initialization failed: ".concat(var38);
            } else {
               String var10002 = new String;
               var39 = var10002;
               var10002.<init>("EGL initialization failed: ");
            }

            String var5 = var39;
            zzafr.e(var39);
            zzbs.zzbD().zza(new Throwable(var5), "SphericalVideoProcessor.run.1");
            this.zzgi();
            this.zzQl.countDown();
         }
      }
   }

   private final void zzgh() {
      while(this.zzQj > 0) {
         this.zzQf.updateTexImage();
         --this.zzQj;
      }

      if (this.zzPV.zza(this.zzPR)) {
         if (Float.isNaN(this.zzQc)) {
            float[] var1 = this.zzPR;
            float[] var4 = new float[]{0.0F, 1.0F, 0.0F};
            float[] var3 = this.zzPR;
            float[] var2;
            this.zzQc = -((float)Math.atan2((double)(var2 = new float[]{var3[0] * var4[0] + var3[1] * var4[1] + var3[2] * var4[2], var3[3] * var4[0] + var3[4] * var4[1] + var3[5] * var4[2], var3[6] * var4[0] + var3[7] * var4[1] + var3[8] * var4[2]})[1], (double)var2[0]) - 1.5707964F);
         }

         zzb(this.zzQa, this.zzQc + this.zzQd);
      } else {
         zza(this.zzPR, -1.5707964F);
         zzb(this.zzQa, this.zzQd);
      }

      zza(this.zzPW, 1.5707964F);
      zza(this.zzPX, this.zzQa, this.zzPW);
      zza(this.zzPY, this.zzPR, this.zzPX);
      zza(this.zzPZ, this.zzQe);
      zza(this.zzQb, this.zzPZ, this.zzPY);
      GLES20.glUniformMatrix3fv(this.zzQi, 1, false, this.zzQb, 0);
      GLES20.glDrawArrays(5, 0, 4);
      zzar("drawArrays");
      GLES20.glFinish();
      this.zzQn.eglSwapBuffers(this.zzQo, this.zzQq);
   }

   public final void zzb(float var1, float var2) {
      float var3;
      float var4;
      if (this.zzrW > this.zzrX) {
         var3 = 1.7453293F * var1 / (float)this.zzrW;
         var4 = 1.7453293F * var2 / (float)this.zzrW;
      } else {
         var3 = 1.7453293F * var1 / (float)this.zzrX;
         var4 = 1.7453293F * var2 / (float)this.zzrX;
      }

      this.zzQd -= var3;
      this.zzQe -= var4;
      if (this.zzQe < -1.5707964F) {
         this.zzQe = -1.5707964F;
      }

      if (this.zzQe > 1.5707964F) {
         this.zzQe = 1.5707964F;
      }

   }

   private static void zza(float[] var0, float[] var1, float[] var2) {
      var0[0] = var1[0] * var2[0] + var1[1] * var2[3] + var1[2] * var2[6];
      var0[1] = var1[0] * var2[1] + var1[1] * var2[4] + var1[2] * var2[7];
      var0[2] = var1[0] * var2[2] + var1[1] * var2[5] + var1[2] * var2[8];
      var0[3] = var1[3] * var2[0] + var1[4] * var2[3] + var1[5] * var2[6];
      var0[4] = var1[3] * var2[1] + var1[4] * var2[4] + var1[5] * var2[7];
      var0[5] = var1[3] * var2[2] + var1[4] * var2[5] + var1[5] * var2[8];
      var0[6] = var1[6] * var2[0] + var1[7] * var2[3] + var1[8] * var2[6];
      var0[7] = var1[6] * var2[1] + var1[7] * var2[4] + var1[8] * var2[7];
      var0[8] = var1[6] * var2[2] + var1[7] * var2[5] + var1[8] * var2[8];
   }

   private static void zza(float[] var0, float var1) {
      var0[0] = 1.0F;
      var0[1] = 0.0F;
      var0[2] = 0.0F;
      var0[3] = 0.0F;
      var0[4] = (float)Math.cos((double)var1);
      var0[5] = (float)(-Math.sin((double)var1));
      var0[6] = 0.0F;
      var0[7] = (float)Math.sin((double)var1);
      var0[8] = (float)Math.cos((double)var1);
   }

   private static void zzb(float[] var0, float var1) {
      var0[0] = (float)Math.cos((double)var1);
      var0[1] = (float)(-Math.sin((double)var1));
      var0[2] = 0.0F;
      var0[3] = (float)Math.sin((double)var1);
      var0[4] = (float)Math.cos((double)var1);
      var0[5] = 0.0F;
      var0[6] = 0.0F;
      var0[7] = 0.0F;
      var0[8] = 1.0F;
   }

   private static int zzc(int var0, String var1) {
      int var2 = GLES20.glCreateShader(var0);
      zzar("createShader");
      if (var2 != 0) {
         GLES20.glShaderSource(var2, var1);
         zzar("shaderSource");
         GLES20.glCompileShader(var2);
         zzar("compileShader");
         int[] var3 = new int[1];
         GLES20.glGetShaderiv(var2, 35713, var3, 0);
         zzar("getShaderiv");
         if (var3[0] == 0) {
            Log.e("SphericalVideoRenderer", (new StringBuilder(37)).append("Could not compile shader ").append(var0).append(":").toString());
            Log.e("SphericalVideoRenderer", GLES20.glGetShaderInfoLog(var2));
            GLES20.glDeleteShader(var2);
            zzar("deleteShader");
            var2 = 0;
         }
      }

      return var2;
   }

   private final boolean zzgi() {
      boolean var1 = false;
      if (this.zzQq != null && this.zzQq != EGL10.EGL_NO_SURFACE) {
         var1 = false | this.zzQn.eglMakeCurrent(this.zzQo, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT) | this.zzQn.eglDestroySurface(this.zzQo, this.zzQq);
         this.zzQq = null;
      }

      if (this.zzQp != null) {
         var1 |= this.zzQn.eglDestroyContext(this.zzQo, this.zzQp);
         this.zzQp = null;
      }

      if (this.zzQo != null) {
         var1 |= this.zzQn.eglTerminate(this.zzQo);
         this.zzQo = null;
      }

      return var1;
   }

   private static void zzar(String var0) {
      int var1;
      if ((var1 = GLES20.glGetError()) != 0) {
         Log.e("SphericalVideoRenderer", (new StringBuilder(21 + String.valueOf(var0).length())).append(var0).append(": glError ").append(var1).toString());
      }

   }
}
