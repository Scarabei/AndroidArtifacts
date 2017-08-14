package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import com.google.android.gms.ads.internal.zzbl;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public class zzoc implements zzny {
   private final Object mLock = new Object();
   private final zznz zzHX;
   private final Context mContext;
   @Nullable
   private final JSONObject zzIa;
   @Nullable
   private zzyh zzuP;
   @Nullable
   private final zzoa zzIb;
   private final zzcu zzIc;
   @Nullable
   private final zzaje zztW;
   boolean zzId;
   @Nullable
   private String zzIe;
   @Nullable
   private zzaev zzuk;
   private WeakReference zzIf = null;

   public zzoc(Context var1, zznz var2, @Nullable zzyh var3, zzcu var4, @Nullable JSONObject var5, @Nullable zzoa var6, @Nullable zzaje var7, @Nullable String var8) {
      this.mContext = var1;
      this.zzHX = var2;
      this.zzuP = var3;
      this.zzIc = var4;
      this.zzIa = var5;
      this.zzIb = var6;
      this.zztW = var7;
      this.zzIe = var8;
   }

   @Nullable
   public View zza(OnClickListener var1, boolean var2) {
      zznn var3;
      if ((var3 = this.zzIb.zzek()) == null) {
         return null;
      } else {
         LayoutParams var4 = new LayoutParams(-2, -2);
         if (!var2) {
            switch(var3.zzee()) {
            case 0:
               var4.addRule(10);
               var4.addRule(9);
               break;
            case 1:
            default:
               var4.addRule(10);
               var4.addRule(11);
               break;
            case 2:
               var4.addRule(12);
               var4.addRule(11);
               break;
            case 3:
               var4.addRule(12);
               var4.addRule(9);
            }
         }

         zzno var5;
         (var5 = new zzno(this.mContext, var3, var4)).setOnClickListener(var1);
         zzme var6 = zzmo.zzFA;
         var5.setContentDescription((CharSequence)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var6));
         return var5;
      }
   }

   public boolean zzep() {
      zznn var1;
      return (var1 = this.zzIb.zzek()) != null && var1.zzef();
   }

   public final void zza(View var1, String var2, @Nullable Bundle var3, Map var4, View var5) {
      JSONObject var6 = this.zza(var4, var5);
      JSONObject var7 = this.zzi(var5);
      JSONObject var8 = zzj(var5);
      JSONObject var9 = null;

      try {
         JSONObject var10 = com.google.android.gms.ads.internal.zzbs.zzbz().zza((Bundle)var3, (JSONObject)null);
         (var9 = new JSONObject()).put("click_point", var10);
         var9.put("asset_id", var2);
      } catch (Exception var11) {
         zzafr.zzb("Error occured while grabbing click signals.", var11);
      }

      this.zza(var1, var7, var6, var8, var2, var9, (JSONObject)null);
   }

   public void zza(View var1, Map var2, Bundle var3, View var4) {
      zzbo.zzcz("performClick must be called on the main UI thread.");
      if (var2 != null) {
         Iterator var5 = var2.entrySet().iterator();

         while(var5.hasNext()) {
            Entry var6;
            View var7 = (View)((WeakReference)(var6 = (Entry)var5.next()).getValue()).get();
            if (var1.equals(var7)) {
               this.zza(var1, (String)var6.getKey(), var3, var2, var4);
               return;
            }
         }
      }

      if ("2".equals(this.zzIb.zzej())) {
         this.zza(var1, "2099", var3, var2, var4);
      } else {
         if ("1".equals(this.zzIb.zzej())) {
            this.zza(var1, "1099", var3, var2, var4);
         }

      }
   }

   public final void zzc(Bundle var1) {
      if (var1 == null) {
         zzafr.zzaC("Click data is null. No click is reported.");
      } else {
         String var2 = var1.getBundle("click_signal").getString("asset_id");
         JSONObject var3 = com.google.android.gms.ads.internal.zzbs.zzbz().zza((Bundle)var1, (JSONObject)null);
         this.zza((View)null, (JSONObject)null, (JSONObject)null, (JSONObject)null, var2, (JSONObject)null, var3);
      }
   }

   private final void zza(View var1, JSONObject var2, JSONObject var3, JSONObject var4, String var5, JSONObject var6, JSONObject var7) {
      zzbo.zzcz("performClick must be called on the main UI thread.");

      try {
         JSONObject var8;
         (var8 = new JSONObject()).put("ad", this.zzIa);
         if (var3 != null) {
            var8.put("asset_view_signal", var3);
         }

         if (var2 != null) {
            var8.put("ad_view_signal", var2);
         }

         if (var6 != null) {
            var8.put("click_signal", var6);
         }

         if (var4 != null) {
            var8.put("scroll_view_signal", var4);
         }

         JSONObject var9;
         (var9 = new JSONObject()).put("asset_id", var5);
         var9.put("template", this.zzIb.zzej());
         var9.put("has_custom_click_handler", this.zzHX.zzs(this.zzIb.getCustomTemplateId()) != null);
         var8.put("has_custom_click_handler", this.zzHX.zzs(this.zzIb.getCustomTemplateId()) != null);

         try {
            JSONObject var10;
            if ((var10 = this.zzIa.optJSONObject("tracking_urls_and_actions")) == null) {
               var10 = new JSONObject();
            }

            String var11 = var10.optString("click_string");
            String var12 = this.zzIc.zzB().zza(this.mContext, var11, var1);
            var9.put("click_signals", var12);
         } catch (Exception var13) {
            zzafr.zzb("Exception obtaining click signals", var13);
         }

         var8.put("click", var9);
         if (var7 != null) {
            var8.put("provided_signals", var7);
         }

         var8.put("ads_id", this.zzIe);
         this.zzuP.zza((zzym)(new zzod(this, var8)));
      } catch (JSONException var14) {
         zzafr.zzb("Unable to create click JSON.", var14);
      }
   }

   public final void zzd(MotionEvent var1) {
      this.zzIc.zza(var1);
   }

   public final void zze(Bundle var1) {
      if (var1 == null) {
         zzafr.zzaC("Touch event data is null. No touch event is reported.");
      } else {
         int var2 = (int)var1.getFloat("x");
         int var3 = (int)var1.getFloat("y");
         int var4 = var1.getInt("duration_ms");
         this.zzIc.zzB().zza(var2, var3, var4);
      }
   }

   public final void zzc(View var1, Map var2) {
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         if (!this.zzId) {
            if (var1.isShown()) {
               if (var1.getGlobalVisibleRect(new Rect(), (Point)null)) {
                  this.zza(var1, var2);
               }
            }
         }
      }
   }

   public void zza(View var1, Map var2) {
      JSONObject var3 = this.zzi(var1);
      JSONObject var4 = this.zza(var2, var1);
      JSONObject var5 = zzj(var1);
      this.zza((JSONObject)var3, (JSONObject)var4, (JSONObject)var5, (JSONObject)null);
   }

   public final boolean zzd(Bundle var1) {
      JSONObject var2 = com.google.android.gms.ads.internal.zzbs.zzbz().zza((Bundle)var1, (JSONObject)null);
      return this.zza((JSONObject)null, (JSONObject)null, (JSONObject)null, (JSONObject)var2);
   }

   private final boolean zza(JSONObject var1, JSONObject var2, JSONObject var3, JSONObject var4) {
      zzbo.zzcz("recordImpression must be called on the main UI thread.");
      this.zzId = true;

      try {
         JSONObject var5;
         (var5 = new JSONObject()).put("ad", this.zzIa);
         var5.put("ads_id", this.zzIe);
         if (var2 != null) {
            var5.put("asset_view_signal", var2);
         }

         if (var1 != null) {
            var5.put("ad_view_signal", var1);
         }

         if (var3 != null) {
            var5.put("scroll_view_signal", var3);
         }

         if (var4 != null) {
            var5.put("provided_signals", var4);
         }

         this.zzuP.zza((zzym)(new zzoe(this, var5)));
         this.zzuP.zza((zzym)(new zzof(this.zzHX, this.zzIe)));
      } catch (JSONException var6) {
         zzafr.zzb("Unable to create impression JSON.", var6);
         return false;
      }

      this.zzHX.zza((zzny)this);
      this.zzHX.zzaw();
      return true;
   }

   public final View zzeu() {
      return this.zzIf != null ? (View)this.zzIf.get() : null;
   }

   public final void zzg(View var1) {
      this.zzIf = new WeakReference(var1);
   }

   public void zza(View var1, Map var2, OnTouchListener var3, OnClickListener var4) {
      zzme var7 = zzmo.zzFy;
      if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var7)).booleanValue()) {
         var1.setOnTouchListener(var3);
         var1.setClickable(true);
         var1.setOnClickListener(var4);
         if (var2 != null) {
            Iterator var5 = var2.entrySet().iterator();

            while(var5.hasNext()) {
               View var6;
               if ((var6 = (View)((WeakReference)((Entry)var5.next()).getValue()).get()) != null) {
                  var6.setOnTouchListener(var3);
                  var6.setClickable(true);
                  var6.setOnClickListener(var4);
               }
            }

         }
      }
   }

   public void zzb(View var1, Map var2) {
      zzme var5 = zzmo.zzFx;
      if (!((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var5)).booleanValue()) {
         var1.setOnTouchListener((OnTouchListener)null);
         var1.setClickable(false);
         var1.setOnClickListener((OnClickListener)null);
         if (var2 != null) {
            Iterator var3 = var2.entrySet().iterator();

            while(var3.hasNext()) {
               View var4;
               if ((var4 = (View)((WeakReference)((Entry)var3.next()).getValue()).get()) != null) {
                  var4.setOnTouchListener((OnTouchListener)null);
                  var4.setClickable(false);
                  var4.setOnClickListener((OnClickListener)null);
               }
            }

         }
      }
   }

   public zzaka zzes() throws zzakm {
      if (this.zzIa != null && this.zzIa.optJSONObject("overlay") != null) {
         zzakk var10000 = com.google.android.gms.ads.internal.zzbs.zzbA();
         Context var10001 = this.mContext;
         zziv var10002 = zziv.zzg(this.mContext);
         zzaje var6 = this.zztW;
         zzcu var5 = this.zzIc;
         zziv var4 = var10002;
         zzaka var1;
         (var1 = var10000.zza(var10001, var4, false, false, var5, var6, (zznb)null, (zzbl)null, (zzv)null, zzig.zzde())).getView().setVisibility(8);
         zzyh var7 = this.zzuP;
         zzog var2 = new zzog(var1);
         var7.zza((zzym)(new zzom(var2)));
         return var1;
      } else {
         return null;
      }
   }

   public final void zzd(Map var1) {
      if (this.zzIb.zzel() != null) {
         if ("2".equals(this.zzIb.zzej())) {
            com.google.android.gms.ads.internal.zzbs.zzbD().zzb(this.mContext, this.zzHX.getAdUnitId(), this.zzIb.zzej(), var1.containsKey("2011"));
            return;
         }

         if ("1".equals(this.zzIb.zzej())) {
            com.google.android.gms.ads.internal.zzbs.zzbD().zzb(this.mContext, this.zzHX.getAdUnitId(), this.zzIb.zzej(), var1.containsKey("1009"));
         }
      }

   }

   public final void zza(View var1, zznw var2) {
      android.widget.FrameLayout.LayoutParams var3 = new android.widget.FrameLayout.LayoutParams(-1, -1);
      View var4;
      if ((var4 = this.zzIb.zzel()) != null) {
         ViewParent var12;
         if ((var12 = var4.getParent()) instanceof ViewGroup) {
            ((ViewGroup)var12).removeView(var4);
         }

         ((FrameLayout)var1).addView(var4, var3);
         this.zzHX.zza(var2);
      } else if (this.zzIb instanceof zzob) {
         zzob var5;
         if ((var5 = (zzob)this.zzIb).getImages() != null && var5.getImages().size() > 0) {
            Object var10;
            zzos var10000 = (var10 = var5.getImages().get(0)) instanceof IBinder ? zzot.zzi((IBinder)var10) : null;
            zzos var6 = var10000;
            if (var10000 != null) {
               try {
                  IObjectWrapper var7;
                  if ((var7 = var6.zzeg()) != null) {
                     Drawable var8 = (Drawable)zzn.zzE(var7);
                     ImageView var9;
                     (var9 = new ImageView(this.mContext)).setImageDrawable(var8);
                     var9.setScaleType(ScaleType.CENTER_INSIDE);
                     ((FrameLayout)var1).addView(var9, var3);
                  }

                  return;
               } catch (RemoteException var11) {
                  zzafr.zzaT("Could not get drawable from image");
               }
            }
         }

      }
   }

   public final void zzev() {
      this.zzHX.zzaO();
   }

   public void zzet() {
      this.zzuP.zzfd();
   }

   public final Context getContext() {
      return this.mContext;
   }

   private static int[] zzh(View var0) {
      int[] var1 = new int[2];
      var0.getLocationOnScreen(var1);
      return var1;
   }

   private final int zzm(int var1) {
      zzji.zzds();
      return zzaiy.zzd(this.mContext, var1);
   }

   private final JSONObject zzb(Rect var1) throws JSONException {
      JSONObject var2;
      (var2 = new JSONObject()).put("width", this.zzm(var1.right - var1.left));
      var2.put("height", this.zzm(var1.bottom - var1.top));
      var2.put("x", this.zzm(var1.left));
      var2.put("y", this.zzm(var1.top));
      var2.put("relative_to", "self");
      return var2;
   }

   private final JSONObject zzi(View var1) {
      JSONObject var2 = new JSONObject();
      if (var1 == null) {
         return var2;
      } else {
         try {
            int[] var3 = zzh(var1);
            JSONObject var4;
            (var4 = new JSONObject()).put("width", this.zzm(var1.getMeasuredWidth()));
            var4.put("height", this.zzm(var1.getMeasuredHeight()));
            var4.put("x", this.zzm(var3[0]));
            var4.put("y", this.zzm(var3[1]));
            var4.put("relative_to", "window");
            var2.put("frame", var4);
            Rect var6 = new Rect();
            JSONObject var5;
            if (var1.getGlobalVisibleRect(var6)) {
               var5 = this.zzb(var6);
            } else {
               (var5 = new JSONObject()).put("width", 0);
               var5.put("height", 0);
               var5.put("x", this.zzm(var3[0]));
               var5.put("y", this.zzm(var3[1]));
               var5.put("relative_to", "window");
            }

            var2.put("visible_bounds", var5);
         } catch (Exception var7) {
            zzafr.zzaT("Unable to get native ad view bounding box");
         }

         return var2;
      }
   }

   private static JSONObject zzj(View var0) {
      JSONObject var1 = new JSONObject();
      if (var0 == null) {
         return var1;
      } else {
         try {
            com.google.android.gms.ads.internal.zzbs.zzbz();
            int var2 = zzagz.zzp(var0);
            var1.put("contained_in_scroll_view", var2 != -1);
         } catch (Exception var3) {
            ;
         }

         return var1;
      }
   }

   private final JSONObject zza(Map var1, View var2) {
      JSONObject var3 = new JSONObject();
      if (var1 != null && var2 != null) {
         int[] var4 = zzh(var2);
         Iterator var5 = var1.entrySet().iterator();

         while(var5.hasNext()) {
            Entry var6;
            View var7;
            if ((var7 = (View)((WeakReference)(var6 = (Entry)var5.next()).getValue()).get()) != null) {
               int[] var8 = zzh(var7);
               JSONObject var9 = new JSONObject();
               JSONObject var10 = new JSONObject();

               try {
                  var10.put("width", this.zzm(var7.getMeasuredWidth()));
                  var10.put("height", this.zzm(var7.getMeasuredHeight()));
                  var10.put("x", this.zzm(var8[0] - var4[0]));
                  var10.put("y", this.zzm(var8[1] - var4[1]));
                  var10.put("relative_to", "ad_view");
                  var9.put("frame", var10);
                  Rect var12 = new Rect();
                  JSONObject var11;
                  if (var7.getLocalVisibleRect(var12)) {
                     var11 = this.zzb(var12);
                  } else {
                     (var11 = new JSONObject()).put("width", 0);
                     var11.put("height", 0);
                     var11.put("x", this.zzm(var8[0] - var4[0]));
                     var11.put("y", this.zzm(var8[1] - var4[1]));
                     var11.put("relative_to", "ad_view");
                  }

                  var9.put("visible_bounds", var11);
                  if (var7 instanceof TextView) {
                     TextView var13 = (TextView)var7;
                     var9.put("text_color", var13.getCurrentTextColor());
                     var9.put("font_size", (double)var13.getTextSize());
                     var9.put("text", var13.getText());
                  }

                  var3.put((String)var6.getKey(), var9);
               } catch (JSONException var14) {
                  zzafr.zzaT("Unable to get asset views information");
               }
            }
         }

         return var3;
      } else {
         return var3;
      }
   }

   @Nullable
   public final zzaev zzew() {
      if (com.google.android.gms.ads.internal.zzbs.zzbY().zzr(this.mContext)) {
         if (this.zzuk == null) {
            this.zzuk = new zzaev(this.mContext, this.zzHX.getAdUnitId());
         }

         return this.zzuk;
      } else {
         return null;
      }
   }
}
