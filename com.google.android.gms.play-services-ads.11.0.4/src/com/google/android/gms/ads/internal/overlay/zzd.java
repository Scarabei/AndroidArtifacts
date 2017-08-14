package com.google.android.gms.ads.internal.overlay;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.Surface;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View.MeasureSpec;
import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzia;
import com.google.android.gms.internal.zzzn;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@zzzn
@TargetApi(14)
public final class zzd extends zzy implements OnBufferingUpdateListener, OnCompletionListener, OnErrorListener, OnInfoListener, OnPreparedListener, OnVideoSizeChangedListener, SurfaceTextureListener {
   private static final Map zzOk = new HashMap();
   private final zzar zzOl;
   private final boolean zzOm;
   private int zzOn = 0;
   private int zzOo = 0;
   private MediaPlayer zzOp;
   private Uri zzOq;
   private int zzOr;
   private int zzOs;
   private int zzOt;
   private int zzOu;
   private int zzOv;
   private zzap zzOw;
   private boolean zzOx;
   private int zzOy;
   private zzx zzOz;

   public zzd(Context var1, boolean var2, boolean var3, zzaq var4, zzar var5) {
      super(var1);
      this.setSurfaceTextureListener(this);
      this.zzOl = var5;
      this.zzOx = var2;
      this.zzOm = var3;
      this.zzOl.zza(this);
   }

   public final String zzfD() {
      String var10001 = String.valueOf(this.zzOx ? " spherical" : "");
      return var10001.length() != 0 ? "MediaPlayer".concat(var10001) : new String("MediaPlayer");
   }

   public final void zza(zzx var1) {
      this.zzOz = var1;
   }

   public final void setVideoPath(String var1) {
      Uri var3 = Uri.parse(var1);
      zzia var4 = zzia.zze(var3);
      this.zzOq = var4 == null ? var3 : Uri.parse(var4.url);
      this.zzOy = 0;
      this.zzfE();
      this.requestLayout();
      this.invalidate();
   }

   public final void stop() {
      zzafr.v("AdMediaPlayerView stop");
      if (this.zzOp != null) {
         this.zzOp.stop();
         this.zzOp.release();
         this.zzOp = null;
         this.zzq(0);
         this.zzOo = 0;
      }

      this.zzOl.onStop();
   }

   public final void onVideoSizeChanged(MediaPlayer var1, int var2, int var3) {
      zzafr.v((new StringBuilder(57)).append("AdMediaPlayerView size changed: ").append(var2).append(" x ").append(var3).toString());
      this.zzOr = var1.getVideoWidth();
      this.zzOs = var1.getVideoHeight();
      if (this.zzOr != 0 && this.zzOs != 0) {
         this.requestLayout();
      }

   }

   public final void onPrepared(MediaPlayer var1) {
      zzafr.v("AdMediaPlayerView prepared");
      this.zzq(2);
      this.zzOl.zzfT();
      zzagz.zzZr.post(new zze(this));
      this.zzOr = var1.getVideoWidth();
      this.zzOs = var1.getVideoHeight();
      if (this.zzOy != 0) {
         this.seekTo(this.zzOy);
      }

      this.zzfF();
      int var2 = this.zzOr;
      int var3 = this.zzOs;
      zzafr.zzaS((new StringBuilder(62)).append("AdMediaPlayerView stream dimensions: ").append(var2).append(" x ").append(var3).toString());
      if (this.zzOo == 3) {
         this.play();
      }

      this.zzfH();
   }

   public final void onCompletion(MediaPlayer var1) {
      zzafr.v("AdMediaPlayerView completion");
      this.zzq(5);
      this.zzOo = 5;
      zzagz.zzZr.post(new zzf(this));
   }

   public final boolean onInfo(MediaPlayer var1, int var2, int var3) {
      String var4 = (String)zzOk.get(var2);
      String var5 = (String)zzOk.get(var3);
      zzafr.v((new StringBuilder(37 + String.valueOf(var4).length() + String.valueOf(var5).length())).append("AdMediaPlayerView MediaPlayer info: ").append(var4).append(":").append(var5).toString());
      return true;
   }

   public final boolean onError(MediaPlayer var1, int var2, int var3) {
      String var4 = (String)zzOk.get(var2);
      String var5 = (String)zzOk.get(var3);
      zzafr.zzaT((new StringBuilder(38 + String.valueOf(var4).length() + String.valueOf(var5).length())).append("AdMediaPlayerView MediaPlayer error: ").append(var4).append(":").append(var5).toString());
      this.zzq(-1);
      this.zzOo = -1;
      zzagz.zzZr.post(new zzg(this, var4, var5));
      return true;
   }

   public final void onBufferingUpdate(MediaPlayer var1, int var2) {
      this.zzOt = var2;
   }

   public final void onSurfaceTextureAvailable(SurfaceTexture var1, int var2, int var3) {
      zzafr.v("AdMediaPlayerView surface created");
      this.zzfE();
      zzagz.zzZr.post(new zzh(this));
   }

   public final void onSurfaceTextureSizeChanged(SurfaceTexture var1, int var2, int var3) {
      zzafr.v("AdMediaPlayerView surface changed");
      boolean var4 = this.zzOo == 3;
      boolean var5 = this.zzOr == var2 && this.zzOs == var3;
      if (this.zzOp != null && var4 && var5) {
         if (this.zzOy != 0) {
            this.seekTo(this.zzOy);
         }

         this.play();
      }

      if (this.zzOw != null) {
         this.zzOw.zzf(var2, var3);
      }

      zzagz.zzZr.post(new zzi(this, var2, var3));
   }

   public final boolean onSurfaceTextureDestroyed(SurfaceTexture var1) {
      zzafr.v("AdMediaPlayerView surface destroyed");
      if (this.zzOp != null && this.zzOy == 0) {
         this.zzOy = this.zzOp.getCurrentPosition();
      }

      if (this.zzOw != null) {
         this.zzOw.zzgf();
      }

      zzagz.zzZr.post(new zzj(this));
      this.zzq(true);
      return true;
   }

   public final void onSurfaceTextureUpdated(SurfaceTexture var1) {
      this.zzOl.zzb(this);
      this.zzPp.zza(var1, this.zzOz);
   }

   protected final void onMeasure(int var1, int var2) {
      int var3 = getDefaultSize(this.zzOr, var1);
      int var4 = getDefaultSize(this.zzOs, var2);
      if (this.zzOr > 0 && this.zzOs > 0 && this.zzOw == null) {
         int var5 = MeasureSpec.getMode(var1);
         int var6 = MeasureSpec.getSize(var1);
         int var7 = MeasureSpec.getMode(var2);
         int var8 = MeasureSpec.getSize(var2);
         if (var5 == 1073741824 && var7 == 1073741824) {
            var3 = var6;
            var4 = var8;
            if (this.zzOr * var8 < var6 * this.zzOs) {
               var3 = var8 * this.zzOr / this.zzOs;
            } else if (this.zzOr * var8 > var6 * this.zzOs) {
               var4 = var6 * this.zzOs / this.zzOr;
            }
         } else if (var5 == 1073741824) {
            var3 = var6;
            var4 = var6 * this.zzOs / this.zzOr;
            if (var7 == Integer.MIN_VALUE && var4 > var8) {
               var4 = var8;
            }
         } else if (var7 == 1073741824) {
            var4 = var8;
            var3 = var8 * this.zzOr / this.zzOs;
            if (var5 == Integer.MIN_VALUE && var3 > var6) {
               var3 = var6;
            }
         } else {
            var3 = this.zzOr;
            var4 = this.zzOs;
            if (var7 == Integer.MIN_VALUE && var4 > var8) {
               var4 = var8;
               var3 = var8 * this.zzOr / this.zzOs;
            }

            if (var5 == Integer.MIN_VALUE && var3 > var6) {
               var3 = var6;
               var4 = var6 * this.zzOs / this.zzOr;
            }
         }
      }

      this.setMeasuredDimension(var3, var4);
      if (this.zzOw != null) {
         this.zzOw.zzf(var3, var4);
      }

      if (VERSION.SDK_INT == 16) {
         if (this.zzOu > 0 && this.zzOu != var3 || this.zzOv > 0 && this.zzOv != var4) {
            this.zzfF();
         }

         this.zzOu = var3;
         this.zzOv = var4;
      }

   }

   public final String toString() {
      String var1 = String.valueOf(this.getClass().getName());
      String var2 = String.valueOf(Integer.toHexString(this.hashCode()));
      return (new StringBuilder(1 + String.valueOf(var1).length() + String.valueOf(var2).length())).append(var1).append("@").append(var2).toString();
   }

   private final void zzfE() {
      zzafr.v("AdMediaPlayerView init MediaPlayer");
      SurfaceTexture var1 = this.getSurfaceTexture();
      if (this.zzOq != null && var1 != null) {
         this.zzq(false);

         try {
            zzbs.zzbQ();
            this.zzOp = new MediaPlayer();
            this.zzOp.setOnBufferingUpdateListener(this);
            this.zzOp.setOnCompletionListener(this);
            this.zzOp.setOnErrorListener(this);
            this.zzOp.setOnInfoListener(this);
            this.zzOp.setOnPreparedListener(this);
            this.zzOp.setOnVideoSizeChangedListener(this);
            this.zzOt = 0;
            if (this.zzOx) {
               this.zzOw = new zzap(this.getContext());
               this.zzOw.zza(var1, this.getWidth(), this.getHeight());
               this.zzOw.start();
               SurfaceTexture var2;
               if ((var2 = this.zzOw.zzgg()) != null) {
                  var1 = var2;
               } else {
                  this.zzOw.zzgf();
                  this.zzOw = null;
               }
            }

            this.zzOp.setDataSource(this.getContext(), this.zzOq);
            zzbs.zzbR();
            Surface var6 = new Surface(var1);
            this.zzOp.setSurface(var6);
            this.zzOp.setAudioStreamType(3);
            this.zzOp.setScreenOnWhilePlaying(true);
            this.zzOp.prepareAsync();
            this.zzq(1);
         } catch (IllegalArgumentException | IllegalStateException | IOException var5) {
            String var3 = String.valueOf(this.zzOq);
            zzafr.zzc((new StringBuilder(36 + String.valueOf(var3).length())).append("Failed to initialize MediaPlayer at ").append(var3).toString(), var5);
            this.onError(this.zzOp, 1, 0);
         }
      }
   }

   private final void zzfF() {
      if (this.zzOm) {
         if (this.zzfG() && this.zzOp.getCurrentPosition() > 0 && this.zzOo != 3) {
            zzafr.v("AdMediaPlayerView nudging MediaPlayer");
            this.zza(0.0F);
            this.zzOp.start();
            int var1 = this.zzOp.getCurrentPosition();
            long var2 = zzbs.zzbF().currentTimeMillis();

            while(this.zzfG() && this.zzOp.getCurrentPosition() == var1 && zzbs.zzbF().currentTimeMillis() - var2 <= 250L) {
               ;
            }

            this.zzOp.pause();
            this.zzfH();
         }

      }
   }

   private final void zzq(boolean var1) {
      zzafr.v("AdMediaPlayerView release");
      if (this.zzOw != null) {
         this.zzOw.zzgf();
         this.zzOw = null;
      }

      if (this.zzOp != null) {
         this.zzOp.reset();
         this.zzOp.release();
         this.zzOp = null;
         this.zzq(0);
         if (var1) {
            this.zzOo = 0;
            this.zzOo = 0;
         }
      }

   }

   public final void play() {
      zzafr.v("AdMediaPlayerView play");
      if (this.zzfG()) {
         this.zzOp.start();
         this.zzq(3);
         this.zzPp.zzfU();
         zzagz.zzZr.post(new zzk(this));
      }

      this.zzOo = 3;
   }

   public final void pause() {
      zzafr.v("AdMediaPlayerView pause");
      if (this.zzfG() && this.zzOp.isPlaying()) {
         this.zzOp.pause();
         this.zzq(4);
         zzagz.zzZr.post(new zzl(this));
      }

      this.zzOo = 4;
   }

   public final int getDuration() {
      return this.zzfG() ? this.zzOp.getDuration() : -1;
   }

   public final int getCurrentPosition() {
      return this.zzfG() ? this.zzOp.getCurrentPosition() : 0;
   }

   public final void seekTo(int var1) {
      zzafr.v((new StringBuilder(34)).append("AdMediaPlayerView seek ").append(var1).toString());
      if (this.zzfG()) {
         this.zzOp.seekTo(var1);
         this.zzOy = 0;
      } else {
         this.zzOy = var1;
      }
   }

   private final boolean zzfG() {
      return this.zzOp != null && this.zzOn != -1 && this.zzOn != 0 && this.zzOn != 1;
   }

   public final void zza(float var1, float var2) {
      if (this.zzOw != null) {
         this.zzOw.zzb(var1, var2);
      }

   }

   public final int getVideoWidth() {
      return this.zzOp != null ? this.zzOp.getVideoWidth() : 0;
   }

   public final int getVideoHeight() {
      return this.zzOp != null ? this.zzOp.getVideoHeight() : 0;
   }

   public final void zzfH() {
      this.zza(this.zzPq.zzgm());
   }

   private final void zza(float var1) {
      if (this.zzOp != null) {
         try {
            this.zzOp.setVolume(var1, var1);
         } catch (IllegalStateException var2) {
            ;
         }
      } else {
         zzafr.zzaT("AdMediaPlayerView setMediaPlayerVolume() called before onPrepared().");
      }
   }

   private final void zzq(int var1) {
      if (var1 == 3) {
         this.zzOl.zzgj();
         this.zzPq.zzgj();
      } else if (this.zzOn == 3) {
         this.zzOl.zzgk();
         this.zzPq.zzgk();
      }

      this.zzOn = var1;
   }

   // $FF: synthetic method
   static zzx zza(zzd var0) {
      return var0.zzOz;
   }

   static {
      if (VERSION.SDK_INT >= 17) {
         zzOk.put(Integer.valueOf(-1004), "MEDIA_ERROR_IO");
         zzOk.put(Integer.valueOf(-1007), "MEDIA_ERROR_MALFORMED");
         zzOk.put(Integer.valueOf(-1010), "MEDIA_ERROR_UNSUPPORTED");
         zzOk.put(Integer.valueOf(-110), "MEDIA_ERROR_TIMED_OUT");
         zzOk.put(Integer.valueOf(3), "MEDIA_INFO_VIDEO_RENDERING_START");
      }

      zzOk.put(Integer.valueOf(100), "MEDIA_ERROR_SERVER_DIED");
      zzOk.put(Integer.valueOf(1), "MEDIA_ERROR_UNKNOWN");
      zzOk.put(Integer.valueOf(1), "MEDIA_INFO_UNKNOWN");
      zzOk.put(Integer.valueOf(700), "MEDIA_INFO_VIDEO_TRACK_LAGGING");
      zzOk.put(Integer.valueOf(701), "MEDIA_INFO_BUFFERING_START");
      zzOk.put(Integer.valueOf(702), "MEDIA_INFO_BUFFERING_END");
      zzOk.put(Integer.valueOf(800), "MEDIA_INFO_BAD_INTERLEAVING");
      zzOk.put(Integer.valueOf(801), "MEDIA_INFO_NOT_SEEKABLE");
      zzOk.put(Integer.valueOf(802), "MEDIA_INFO_METADATA_UPDATE");
      if (VERSION.SDK_INT >= 19) {
         zzOk.put(Integer.valueOf(901), "MEDIA_INFO_UNSUPPORTED_SUBTITLE");
         zzOk.put(Integer.valueOf(902), "MEDIA_INFO_SUBTITLE_TIMED_OUT");
      }

   }
}
