package com.google.android.gms.cast.framework.media.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;
import com.google.android.gms.R.attr;
import com.google.android.gms.R.dimen;
import com.google.android.gms.R.drawable;
import com.google.android.gms.R.id;
import com.google.android.gms.R.layout;
import com.google.android.gms.R.string;
import com.google.android.gms.R.style;
import com.google.android.gms.R.styleable;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.uicontroller.UIMediaController;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzayo;

public class MiniControllerFragment extends Fragment implements ControlButtonsContainer {
   private static final zzayo zzarK = new zzayo("MiniControllerFragment");
   private boolean zzawG;
   private int zzawH;
   private int zzawI;
   private TextView zzawJ;
   private int zzawK;
   private int zzawL;
   private int zzaws;
   private int[] zzawx;
   private ImageView[] zzawy = new ImageView[3];
   private int zzawM;
   @DrawableRes
   private int zzawj;
   @DrawableRes
   private int zzawk;
   @DrawableRes
   private int zzawl;
   @DrawableRes
   private int zzawN;
   @DrawableRes
   private int zzawO;
   @DrawableRes
   private int zzawP;
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
   private UIMediaController zzawE;

   public void onInflate(Context var1, AttributeSet var2, Bundle var3) {
      super.onInflate(var1, var2, var3);
      MiniControllerFragment var4 = this;
      if (this.zzawx == null) {
         TypedArray var7 = var1.obtainStyledAttributes(var2, styleable.CastMiniController, attr.castMiniControllerStyle, style.CastMiniController);
         this.zzawG = var7.getBoolean(styleable.CastMiniController_castShowImageThumbnail, true);
         this.zzawH = var7.getResourceId(styleable.CastMiniController_castTitleTextAppearance, 0);
         this.zzawI = var7.getResourceId(styleable.CastMiniController_castSubtitleTextAppearance, 0);
         this.zzawK = var7.getResourceId(styleable.CastMiniController_castBackground, 0);
         this.zzawL = var7.getColor(styleable.CastMiniController_castProgressBarColor, 0);
         this.zzaws = var7.getResourceId(styleable.CastMiniController_castButtonColor, 0);
         this.zzawj = var7.getResourceId(styleable.CastMiniController_castPlayButtonDrawable, 0);
         this.zzawk = var7.getResourceId(styleable.CastMiniController_castPauseButtonDrawable, 0);
         this.zzawl = var7.getResourceId(styleable.CastMiniController_castStopButtonDrawable, 0);
         this.zzawN = var7.getResourceId(styleable.CastMiniController_castPlayButtonDrawable, 0);
         this.zzawO = var7.getResourceId(styleable.CastMiniController_castPauseButtonDrawable, 0);
         this.zzawP = var7.getResourceId(styleable.CastMiniController_castStopButtonDrawable, 0);
         this.zzawm = var7.getResourceId(styleable.CastMiniController_castSkipPreviousButtonDrawable, 0);
         this.zzawn = var7.getResourceId(styleable.CastMiniController_castSkipNextButtonDrawable, 0);
         this.zzawo = var7.getResourceId(styleable.CastMiniController_castRewind30ButtonDrawable, 0);
         this.zzawp = var7.getResourceId(styleable.CastMiniController_castForward30ButtonDrawable, 0);
         this.zzawq = var7.getResourceId(styleable.CastMiniController_castMuteToggleButtonDrawable, 0);
         this.zzawr = var7.getResourceId(styleable.CastMiniController_castClosedCaptionsButtonDrawable, 0);
         int var8;
         if ((var8 = var7.getResourceId(styleable.CastMiniController_castControlButtons, 0)) == 0) {
            zzarK.zzf("Unable to read attribute castControlButtons.", new Object[0]);
            this.zzawx = new int[]{id.cast_button_type_empty, id.cast_button_type_empty, id.cast_button_type_empty};
         } else {
            TypedArray var9;
            zzbo.zzaf((var9 = var1.getResources().obtainTypedArray(var8)).length() == 3);
            this.zzawx = new int[var9.length()];

            for(int var10 = 0; var10 < var9.length(); ++var10) {
               var4.zzawx[var10] = var9.getResourceId(var10, 0);
            }

            var9.recycle();
            if (var4.zzawG) {
               var4.zzawx[0] = id.cast_button_type_empty;
            }

            var4.zzawM = 0;
            int[] var13 = var4.zzawx;
            int var11 = var4.zzawx.length;

            for(int var12 = 0; var12 < var11; ++var12) {
               if (var13[var12] != id.cast_button_type_empty) {
                  ++var4.zzawM;
               }
            }
         }

         var7.recycle();
      }

   }

   public View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
      this.zzawE = new UIMediaController(this.getActivity());
      View var4;
      (var4 = var1.inflate(layout.cast_mini_controller, var2)).setVisibility(8);
      this.zzawE.bindViewVisibilityToMediaSession(var4, 8);
      RelativeLayout var5 = (RelativeLayout)var4.findViewById(id.container_current);
      if (this.zzawK != 0) {
         var5.setBackgroundResource(this.zzawK);
      }

      ImageView var6 = (ImageView)var4.findViewById(id.icon_view);
      TextView var7 = (TextView)var4.findViewById(id.title_view);
      if (this.zzawH != 0) {
         var7.setTextAppearance(this.getActivity(), this.zzawH);
      }

      this.zzawJ = (TextView)var4.findViewById(id.subtitle_view);
      if (this.zzawI != 0) {
         this.zzawJ.setTextAppearance(this.getActivity(), this.zzawI);
      }

      ProgressBar var8 = (ProgressBar)var4.findViewById(id.progressBar);
      if (this.zzawL != 0) {
         ((LayerDrawable)var8.getProgressDrawable()).setColorFilter(this.zzawL, Mode.SRC_IN);
      }

      this.zzawE.bindTextViewToMetadataOfCurrentItem(var7, "com.google.android.gms.cast.metadata.TITLE");
      this.zzawE.bindTextViewToSmartSubtitle(this.zzawJ);
      this.zzawE.bindProgressBar(var8);
      this.zzawE.bindViewToLaunchExpandedController(var5);
      if (this.zzawG) {
         int var9 = this.getResources().getDimensionPixelSize(dimen.cast_mini_controller_icon_width);
         int var10 = this.getResources().getDimensionPixelSize(dimen.cast_mini_controller_icon_height);
         this.zzawE.bindImageViewToImageOfCurrentItem(var6, new ImageHints(2, var9, var10), drawable.cast_album_art_placeholder);
      } else {
         var6.setVisibility(8);
      }

      this.zzawy[0] = (ImageView)var5.findViewById(id.button_0);
      this.zzawy[1] = (ImageView)var5.findViewById(id.button_1);
      this.zzawy[2] = (ImageView)var5.findViewById(id.button_2);
      this.zza(var5, id.button_0, 0);
      this.zza(var5, id.button_1, 1);
      this.zza(var5, id.button_2, 2);
      return var4;
   }

   public void onDestroy() {
      if (this.zzawE != null) {
         this.zzawE.dispose();
         this.zzawE = null;
      }

      super.onDestroy();
   }

   public final int getButtonSlotCount() {
      return 3;
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

   private final void zza(RelativeLayout var1, int var2, int var3) {
      ImageView var4 = (ImageView)var1.findViewById(var2);
      int var5;
      if ((var5 = this.zzawx[var3]) == id.cast_button_type_empty) {
         var4.setVisibility(4);
      } else {
         if (var5 != id.cast_button_type_custom) {
            if (var5 == id.cast_button_type_play_pause_toggle) {
               int var6 = this.zzawj;
               int var7 = this.zzawk;
               int var8 = this.zzawl;
               if (this.zzawM == 1) {
                  var6 = this.zzawN;
                  var7 = this.zzawO;
                  var8 = this.zzawP;
               }

               Drawable var9 = zzb.zza(this.getContext(), this.zzaws, var6);
               Drawable var10 = zzb.zza(this.getContext(), this.zzaws, var7);
               Drawable var11 = zzb.zza(this.getContext(), this.zzaws, var8);
               var4.setImageDrawable(var10);
               ProgressBar var15 = new ProgressBar(this.getContext());
               LayoutParams var16;
               (var16 = new LayoutParams(-2, -2)).addRule(8, var2);
               var16.addRule(6, var2);
               var16.addRule(5, var2);
               var16.addRule(7, var2);
               var16.addRule(15);
               var15.setLayoutParams(var16);
               var15.setVisibility(8);
               Drawable var17 = var15.getIndeterminateDrawable();
               if (this.zzawL != 0 && var17 != null) {
                  var17.setColorFilter(this.zzawL, Mode.SRC_IN);
               }

               var1.addView(var15);
               this.zzawE.bindImageViewToPlayPauseToggle(var4, var9, var10, var11, var15, true);
               return;
            }

            if (var5 == id.cast_button_type_skip_previous) {
               var4.setImageDrawable(zzb.zza(this.getContext(), this.zzaws, this.zzawm));
               var4.setContentDescription(this.getResources().getString(string.cast_skip_prev));
               this.zzawE.bindViewToSkipPrev(var4, 0);
               return;
            }

            if (var5 == id.cast_button_type_skip_next) {
               var4.setImageDrawable(zzb.zza(this.getContext(), this.zzaws, this.zzawn));
               var4.setContentDescription(this.getResources().getString(string.cast_skip_next));
               this.zzawE.bindViewToSkipNext(var4, 0);
               return;
            }

            if (var5 == id.cast_button_type_rewind_30_seconds) {
               var4.setImageDrawable(zzb.zza(this.getContext(), this.zzaws, this.zzawo));
               var4.setContentDescription(this.getResources().getString(string.cast_rewind_30));
               this.zzawE.bindViewToRewind(var4, 30000L);
               return;
            }

            if (var5 == id.cast_button_type_forward_30_seconds) {
               var4.setImageDrawable(zzb.zza(this.getContext(), this.zzaws, this.zzawp));
               var4.setContentDescription(this.getResources().getString(string.cast_forward_30));
               this.zzawE.bindViewToForward(var4, 30000L);
               return;
            }

            if (var5 == id.cast_button_type_mute_toggle) {
               var4.setImageDrawable(zzb.zza(this.getContext(), this.zzaws, this.zzawq));
               this.zzawE.bindImageViewToMuteToggle(var4);
               return;
            }

            if (var5 == id.cast_button_type_closed_caption) {
               var4.setImageDrawable(zzb.zza(this.getContext(), this.zzaws, this.zzawr));
               this.zzawE.bindViewToClosedCaption(var4);
            }
         }

      }
   }
}
