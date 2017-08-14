package com.google.android.gms.cast.framework.media.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.appcompat.R.attr;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;
import com.google.android.gms.R.color;
import com.google.android.gms.R.dimen;
import com.google.android.gms.R.drawable;
import com.google.android.gms.R.id;
import com.google.android.gms.R.layout;
import com.google.android.gms.R.string;
import com.google.android.gms.R.style;
import com.google.android.gms.R.styleable;
import com.google.android.gms.cast.AdBreakClipInfo;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIMediaController;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.internal.zzavc;
import com.google.android.gms.internal.zzavr;
import com.google.android.gms.internal.zzawa;

public class ExpandedControllerActivity extends AppCompatActivity implements ControlButtonsContainer {
   private final SessionManagerListener zzawe = new ExpandedControllerActivity.zzb((zza)null);
   private final RemoteMediaClient.Listener zzavk = new ExpandedControllerActivity.zza((zza)null);
   @DrawableRes
   private int zzawf;
   @ColorRes
   private int zzawg;
   @DrawableRes
   private int zzawh;
   @DrawableRes
   private int zzawi;
   @DrawableRes
   private int zzawj;
   @DrawableRes
   private int zzawk;
   @DrawableRes
   private int zzawl;
   @DrawableRes
   private int zzawm;
   @DrawableRes
   private int zzawn;
   @DrawableRes
   private int zzawo;
   @DrawableRes
   private int zzawp;
   @DrawableRes
   private int zzawq;
   @DrawableRes
   private int zzawr;
   private int zzaws;
   private TextView zzawt;
   private SeekBar zzavB;
   private ImageView zzawu;
   private ImageView zzawv;
   private zzavr zzaww;
   private int[] zzawx;
   private ImageView[] zzawy = new ImageView[4];
   private View zzawz;
   private ImageView zzawA;
   private TextView zzawB;
   private TextView zzawC;
   private zzavc zzawD;
   private UIMediaController zzawE;
   private SessionManager zzarO;

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      CastContext.getSharedInstance(this).registerLifecycleCallbacksBeforeIceCreamSandwich(this, var1);
      this.zzarO = CastContext.getSharedInstance(this).getSessionManager();
      if (this.zzarO.getCurrentCastSession() == null) {
         this.finish();
      }

      this.zzawE = new UIMediaController(this);
      this.zzawE.setPostRemoteMediaClientListener(this.zzavk);
      this.setContentView(layout.cast_expanded_controller_activity);
      TypedArray var3 = this.obtainStyledAttributes(new int[]{attr.selectableItemBackgroundBorderless, attr.colorControlActivated});
      this.zzawf = var3.getResourceId(0, 0);
      this.zzawg = var3.getResourceId(1, 0);
      var3.recycle();
      ExpandedControllerActivity var2 = this;
      var3 = this.obtainStyledAttributes((AttributeSet)null, styleable.CastExpandedController, com.google.android.gms.R.attr.castExpandedControllerStyle, style.CastExpandedController);
      this.zzaws = var3.getResourceId(styleable.CastExpandedController_castButtonColor, 0);
      this.zzawh = var3.getResourceId(styleable.CastExpandedController_castSeekBarProgressDrawable, 0);
      this.zzawi = var3.getResourceId(styleable.CastExpandedController_castSeekBarThumbDrawable, 0);
      this.zzawj = var3.getResourceId(styleable.CastExpandedController_castPlayButtonDrawable, 0);
      this.zzawk = var3.getResourceId(styleable.CastExpandedController_castPauseButtonDrawable, 0);
      this.zzawl = var3.getResourceId(styleable.CastExpandedController_castStopButtonDrawable, 0);
      this.zzawm = var3.getResourceId(styleable.CastExpandedController_castSkipPreviousButtonDrawable, 0);
      this.zzawn = var3.getResourceId(styleable.CastExpandedController_castSkipNextButtonDrawable, 0);
      this.zzawo = var3.getResourceId(styleable.CastExpandedController_castRewind30ButtonDrawable, 0);
      this.zzawp = var3.getResourceId(styleable.CastExpandedController_castForward30ButtonDrawable, 0);
      this.zzawq = var3.getResourceId(styleable.CastExpandedController_castMuteToggleButtonDrawable, 0);
      this.zzawr = var3.getResourceId(styleable.CastExpandedController_castClosedCaptionsButtonDrawable, 0);
      int var4;
      if ((var4 = var3.getResourceId(styleable.CastExpandedController_castControlButtons, 0)) != 0) {
         TypedArray var5;
         zzbo.zzaf((var5 = this.getResources().obtainTypedArray(var4)).length() == 4);
         this.zzawx = new int[var5.length()];

         for(int var6 = 0; var6 < var5.length(); ++var6) {
            var2.zzawx[var6] = var5.getResourceId(var6, 0);
         }

         var5.recycle();
      } else {
         this.zzawx = new int[]{id.cast_button_type_empty, id.cast_button_type_empty, id.cast_button_type_empty, id.cast_button_type_empty};
      }

      var3.recycle();
      View var10001 = this.findViewById(id.expanded_controller_layout);
      UIMediaController var19 = this.zzawE;
      View var17 = var10001;
      this.zzawu = (ImageView)var17.findViewById(id.background_image_view);
      this.zzawv = (ImageView)var17.findViewById(id.blurred_background_image_view);
      View var20 = var17.findViewById(id.background_place_holder_image_view);
      DisplayMetrics var21 = new DisplayMetrics();
      this.getWindowManager().getDefaultDisplay().getMetrics(var21);
      var19.bindImageViewToImageOfCurrentItem(this.zzawu, new ImageHints(4, var21.widthPixels, var21.heightPixels), var20);
      this.zzawt = (TextView)var17.findViewById(id.status_text);
      ProgressBar var7 = (ProgressBar)var17.findViewById(id.loading_indicator);
      var19.bindViewToLoadingIndicator(var7);
      TextView var8 = (TextView)var17.findViewById(id.start_text);
      TextView var9 = (TextView)var17.findViewById(id.end_text);
      ImageView var10 = (ImageView)var17.findViewById(id.live_stream_indicator);
      this.zzavB = (SeekBar)var17.findViewById(id.seek_bar);
      ExpandedControllerActivity var12;
      Drawable var13 = (var12 = this).getResources().getDrawable(var12.zzawh);
      ColorStateList var14 = null;
      if (var13 != null) {
         if (var12.zzawh == drawable.cast_expanded_controller_seekbar_track) {
            var14 = var12.zzop();
            LayerDrawable var15;
            Drawable var16;
            DrawableCompat.setTintList(var16 = DrawableCompat.wrap((var15 = (LayerDrawable)var13).findDrawableByLayerId(16908301)), var14);
            var15.setDrawableByLayerId(16908301, var16);
            var15.findDrawableByLayerId(16908288).setColorFilter(var12.getResources().getColor(color.cast_expanded_controller_seek_bar_progress_background_tint_color), Mode.SRC_IN);
         }

         var12.zzavB.setProgressDrawable(var13);
      }

      Drawable var23;
      if ((var23 = var12.getResources().getDrawable(var12.zzawi)) != null) {
         if (var12.zzawi == drawable.cast_expanded_controller_seekbar_thumb) {
            if (var14 == null) {
               var14 = var12.zzop();
            }

            DrawableCompat.setTintList(var23 = DrawableCompat.wrap(var23), var14);
         }

         var12.zzavB.setThumb(var23);
      }

      if (zzq.zzse()) {
         this.zzavB.setSplitTrack(false);
      }

      SeekBar var11 = (SeekBar)var17.findViewById(id.live_stream_seek_bar);
      var19.bindTextViewToStreamPosition(var8, true);
      var19.bindTextViewToStreamDuration(var9, var10);
      var19.bindSeekBar(this.zzavB);
      var19.bindViewToUIController(var11, new zzawa(var11, this.zzavB));
      this.zzawy[0] = (ImageView)var17.findViewById(id.button_0);
      this.zzawy[1] = (ImageView)var17.findViewById(id.button_1);
      this.zzawy[2] = (ImageView)var17.findViewById(id.button_2);
      this.zzawy[3] = (ImageView)var17.findViewById(id.button_3);
      this.zza(var17, id.button_0, this.zzawx[0], var19);
      this.zza(var17, id.button_1, this.zzawx[1], var19);
      this.zza(var17, id.button_play_pause_toggle, id.cast_button_type_play_pause_toggle, var19);
      this.zza(var17, id.button_2, this.zzawx[2], var19);
      this.zza(var17, id.button_3, this.zzawx[3], var19);
      this.zzawz = this.findViewById(id.ad_container);
      this.zzawA = (ImageView)this.zzawz.findViewById(id.ad_image_view);
      this.zzawC = (TextView)this.zzawz.findViewById(id.ad_label);
      this.zzawB = (TextView)this.zzawz.findViewById(id.ad_in_progress_label);
      RelativeLayout var22 = (RelativeLayout)var17.findViewById(id.seek_bar_controls);
      zzavr var24 = new zzavr(this);
      LayoutParams var25;
      (var25 = new LayoutParams(-1, -2)).addRule(0, id.end_text);
      var25.addRule(1, id.start_text);
      var25.addRule(6, id.seek_bar);
      var25.addRule(7, id.seek_bar);
      var25.addRule(5, id.seek_bar);
      var25.addRule(8, id.seek_bar);
      var24.setLayoutParams(var25);
      if (zzq.zzsa()) {
         var24.setPaddingRelative(this.zzavB.getPaddingStart(), this.zzavB.getPaddingTop(), this.zzavB.getPaddingEnd(), this.zzavB.getPaddingBottom());
      } else {
         var24.setPadding(this.zzavB.getPaddingLeft(), this.zzavB.getPaddingTop(), this.zzavB.getPaddingRight(), this.zzavB.getPaddingBottom());
      }

      var24.setContentDescription(this.getResources().getString(string.cast_seek_bar));
      var24.setBackgroundColor(0);
      var22.addView(var24);
      this.zzaww = var24;
      Toolbar var18 = (Toolbar)this.findViewById(id.toolbar);
      this.setSupportActionBar(var18);
      if (this.getSupportActionBar() != null) {
         this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         this.getSupportActionBar().setHomeAsUpIndicator(drawable.quantum_ic_keyboard_arrow_down_white_36);
      }

      this.zzor();
      this.zzoq();
      this.zzawD = new zzavc(this.getApplicationContext(), new ImageHints(-1, this.zzawA.getWidth(), this.zzawA.getHeight()));
      this.zzawD.zza(new zza(this));
   }

   protected void onResume() {
      CastContext.getSharedInstance(this).getSessionManager().addSessionManagerListener(this.zzawe, CastSession.class);
      CastSession var1;
      if ((var1 = CastContext.getSharedInstance(this).getSessionManager().getCurrentCastSession()) == null || !var1.isConnected() && !var1.isConnecting()) {
         this.finish();
      }

      this.zzor();
      this.zzos();
      super.onResume();
   }

   protected void onPause() {
      CastContext.getSharedInstance(this).getSessionManager().removeSessionManagerListener(this.zzawe, CastSession.class);
      super.onPause();
   }

   protected void onDestroy() {
      this.zzawD.clear();
      if (this.zzawE != null) {
         this.zzawE.setPostRemoteMediaClientListener((RemoteMediaClient.Listener)null);
         this.zzawE.dispose();
      }

      super.onDestroy();
   }

   public boolean onOptionsItemSelected(MenuItem var1) {
      if (var1.getItemId() == 16908332) {
         this.finish();
      }

      return true;
   }

   public void onWindowFocusChanged(boolean var1) {
      super.onWindowFocusChanged(var1);
      if (var1) {
         int var3 = this.getWindow().getDecorView().getSystemUiVisibility() ^ 2;
         if (zzq.zzrZ()) {
            var3 ^= 4;
         }

         if (zzq.zzsc()) {
            var3 ^= 4096;
         }

         this.getWindow().getDecorView().setSystemUiVisibility(var3);
         if (zzq.zzsb()) {
            this.setImmersive(true);
         }
      }

   }

   public TextView getStatusTextView() {
      return this.zzawt;
   }

   public SeekBar getSeekBar() {
      return this.zzavB;
   }

   public final int getButtonSlotCount() {
      return 4;
   }

   public final int getButtonTypeAt(int var1) throws IndexOutOfBoundsException {
      return this.zzawx[var1];
   }

   public final ImageView getButtonImageViewAt(int var1) throws IndexOutOfBoundsException {
      return this.zzawy[var1];
   }

   public UIMediaController getUIMediaController() {
      return this.zzawE;
   }

   private final ColorStateList zzop() {
      int var1 = this.getResources().getColor(this.zzawg);
      TypedValue var2 = new TypedValue();
      this.getResources().getValue(dimen.cast_expanded_controller_seekbar_disabled_alpha, var2, true);
      int var3 = Color.argb((int)(var2.getFloat() * (float)Color.alpha(var1)), Color.red(var1), Color.green(var1), Color.blue(var1));
      int[] var4 = new int[]{var1, var3};
      int[][] var5 = new int[][]{{16842910}, {-16842910}};
      return new ColorStateList(var5, var4);
   }

   private final void zza(View var1, int var2, int var3, UIMediaController var4) {
      ImageView var5 = (ImageView)var1.findViewById(var2);
      if (var3 == id.cast_button_type_empty) {
         var5.setVisibility(4);
      } else {
         if (var3 != id.cast_button_type_custom) {
            if (var3 == id.cast_button_type_play_pause_toggle) {
               var5.setBackgroundResource(this.zzawf);
               Drawable var9 = zzb.zzb(this, this.zzaws, this.zzawk);
               Drawable var10 = zzb.zzb(this, this.zzaws, this.zzawj);
               Drawable var11 = zzb.zzb(this, this.zzaws, this.zzawl);
               var5.setImageDrawable(var10);
               var4.bindImageViewToPlayPauseToggle(var5, var10, var9, var11, (View)null, false);
               return;
            }

            if (var3 == id.cast_button_type_skip_previous) {
               var5.setBackgroundResource(this.zzawf);
               var5.setImageDrawable(zzb.zzb(this, this.zzaws, this.zzawm));
               var5.setContentDescription(this.getResources().getString(string.cast_skip_prev));
               var4.bindViewToSkipPrev(var5, 0);
               return;
            }

            if (var3 == id.cast_button_type_skip_next) {
               var5.setBackgroundResource(this.zzawf);
               var5.setImageDrawable(zzb.zzb(this, this.zzaws, this.zzawn));
               var5.setContentDescription(this.getResources().getString(string.cast_skip_next));
               var4.bindViewToSkipNext(var5, 0);
               return;
            }

            if (var3 == id.cast_button_type_rewind_30_seconds) {
               var5.setBackgroundResource(this.zzawf);
               var5.setImageDrawable(zzb.zzb(this, this.zzaws, this.zzawo));
               var5.setContentDescription(this.getResources().getString(string.cast_rewind_30));
               var4.bindViewToRewind(var5, 30000L);
               return;
            }

            if (var3 == id.cast_button_type_forward_30_seconds) {
               var5.setBackgroundResource(this.zzawf);
               var5.setImageDrawable(zzb.zzb(this, this.zzaws, this.zzawp));
               var5.setContentDescription(this.getResources().getString(string.cast_forward_30));
               var4.bindViewToForward(var5, 30000L);
               return;
            }

            if (var3 == id.cast_button_type_mute_toggle) {
               var5.setBackgroundResource(this.zzawf);
               var5.setImageDrawable(zzb.zzb(this, this.zzaws, this.zzawq));
               var4.bindImageViewToMuteToggle(var5);
               return;
            }

            if (var3 == id.cast_button_type_closed_caption) {
               var5.setBackgroundResource(this.zzawf);
               var5.setImageDrawable(zzb.zzb(this, this.zzaws, this.zzawr));
               var4.bindViewToClosedCaption(var5);
            }
         }

      }
   }

   private final RemoteMediaClient getRemoteMediaClient() {
      CastSession var1;
      return (var1 = this.zzarO.getCurrentCastSession()) != null && var1.isConnected() ? var1.getRemoteMediaClient() : null;
   }

   private final void zzoq() {
      RemoteMediaClient var1;
      MediaInfo var2;
      MediaMetadata var3;
      ActionBar var4;
      if ((var1 = this.getRemoteMediaClient()) != null && var1.hasMediaSession() && (var2 = var1.getMediaInfo()) != null && (var3 = var2.getMetadata()) != null && (var4 = this.getSupportActionBar()) != null) {
         var4.setTitle(var3.getString("com.google.android.gms.cast.metadata.TITLE"));
      }

   }

   private final void zzor() {
      CastSession var1;
      CastDevice var2;
      String var3;
      if ((var1 = this.zzarO.getCurrentCastSession()) != null && (var2 = var1.getCastDevice()) != null && !TextUtils.isEmpty(var3 = var2.getFriendlyName())) {
         this.zzawt.setText(this.getResources().getString(string.cast_casting_to_device, new Object[]{var3}));
      } else {
         this.zzawt.setText("");
      }
   }

   private final void zzos() {
      RemoteMediaClient var1;
      MediaInfo var2 = (var1 = this.getRemoteMediaClient()) == null ? null : var1.getMediaInfo();
      MediaStatus var10000 = var1 == null ? null : var1.getMediaStatus();
      MediaStatus var3 = var10000;
      if (var10000 != null && var3.isPlayingAd()) {
         Drawable var4;
         Bitmap var5;
         Bitmap var6;
         if (zzq.zzsa() && this.zzawv.getVisibility() == 8 && (var4 = this.zzawu.getDrawable()) != null && var4 instanceof BitmapDrawable && (var5 = ((BitmapDrawable)var4).getBitmap()) != null && (var6 = zzb.zza(this, var5, 0.25F, 7.5F)) != null) {
            this.zzawv.setImageBitmap(var6);
            this.zzawv.setVisibility(0);
         }

         String var8 = null;
         String var9 = null;
         AdBreakClipInfo var10;
         if ((var10 = var3.getCurrentAdBreakClip()) != null) {
            var8 = var10.getTitle();
            var9 = var10.getContentUrl();
         }

         this.zzawB.setVisibility(0);
         if (!TextUtils.isEmpty(var9)) {
            Uri var7 = Uri.parse(var9);
            this.zzawD.zzm(var7);
         } else {
            this.zzawA.setVisibility(8);
         }

         this.zzawC.setText(TextUtils.isEmpty(var8) ? this.getResources().getString(string.cast_ad_label) : var8);
         this.zzavB.setEnabled(false);
         this.zzawz.setVisibility(0);
      } else {
         this.zzavB.setEnabled(true);
         this.zzawz.setVisibility(8);
         if (zzq.zzsa()) {
            this.zzawv.setVisibility(8);
            this.zzawv.setImageBitmap((Bitmap)null);
         }
      }

      if (var2 != null) {
         this.zzaww.zzac(this.zzavB.getMax());
         this.zzaww.zzb(var2.getAdBreaks(), -1);
      }

   }

   // $FF: synthetic method
   static TextView zza(ExpandedControllerActivity var0) {
      return var0.zzawB;
   }

   // $FF: synthetic method
   static ImageView zzb(ExpandedControllerActivity var0) {
      return var0.zzawA;
   }

   class zza implements RemoteMediaClient.Listener {
      // $FF: synthetic field
      private ExpandedControllerActivity zzawF;

      private zza() {
         this.zzawF = ExpandedControllerActivity.this;
         super();
      }

      public final void onStatusUpdated() {
         RemoteMediaClient var1;
         if ((var1 = this.zzawF.getRemoteMediaClient()) != null && var1.hasMediaSession()) {
            this.zzawF.zzor();
            this.zzawF.zzos();
         } else {
            this.zzawF.finish();
         }
      }

      public final void onMetadataUpdated() {
         this.zzawF.zzoq();
      }

      public final void onQueueStatusUpdated() {
      }

      public final void onPreloadStatusUpdated() {
      }

      public final void onSendingRemoteMediaRequest() {
         this.zzawF.zzawt.setText(this.zzawF.getResources().getString(string.cast_expanded_controller_loading));
      }

      public final void onAdBreakStatusUpdated() {
         this.zzawF.zzos();
      }

      // $FF: synthetic method
      zza(zza var2) {
         this();
      }
   }

   class zzb implements SessionManagerListener {
      // $FF: synthetic field
      private ExpandedControllerActivity zzawF;

      private zzb() {
         this.zzawF = ExpandedControllerActivity.this;
         super();
      }

      // $FF: synthetic method
      public final void onSessionEnded(Session var1, int var2) {
         this.zzawF.finish();
      }

      // $FF: synthetic method
      zzb(zza var2) {
         this();
      }
   }
}
