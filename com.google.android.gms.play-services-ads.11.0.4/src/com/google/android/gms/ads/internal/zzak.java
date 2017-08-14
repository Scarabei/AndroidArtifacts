package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.internal.zzaje;
import com.google.android.gms.internal.zziv;
import com.google.android.gms.internal.zzjo;
import com.google.android.gms.internal.zzjr;
import com.google.android.gms.internal.zzjv;
import com.google.android.gms.internal.zzkk;
import com.google.android.gms.internal.zzon;
import com.google.android.gms.internal.zzpn;
import com.google.android.gms.internal.zzpq;
import com.google.android.gms.internal.zzpt;
import com.google.android.gms.internal.zzpw;
import com.google.android.gms.internal.zzpz;
import com.google.android.gms.internal.zzuq;
import com.google.android.gms.internal.zzzn;

@zzzn
public final class zzak extends zzjv {
   private zzjo zztK;
   private zzpn zzua;
   private zzpq zzub;
   private SimpleArrayMap zzuc;
   private SimpleArrayMap zzud;
   private zzpz zzue;
   private zziv zztO;
   private PublisherAdViewOptions zztP;
   private zzon zztS;
   private zzkk zztU;
   private final Context mContext;
   private final zzuq zzsX;
   private final String zztV;
   private final zzaje zztW;
   private final zzv zzsS;

   public zzak(Context var1, String var2, zzuq var3, zzaje var4, zzv var5) {
      this.mContext = var1;
      this.zztV = var2;
      this.zzsX = var3;
      this.zztW = var4;
      this.zzud = new SimpleArrayMap();
      this.zzuc = new SimpleArrayMap();
      this.zzsS = var5;
   }

   public final zzjr zzaZ() {
      return new zzai(this.mContext, this.zztV, this.zzsX, this.zztW, this.zztK, this.zzua, this.zzub, this.zzud, this.zzuc, this.zztS, this.zztU, this.zzsS, this.zzue, this.zztO, this.zztP);
   }

   public final void zzb(zzjo var1) {
      this.zztK = var1;
   }

   public final void zza(zzon var1) {
      this.zztS = var1;
   }

   public final void zza(PublisherAdViewOptions var1) {
      this.zztP = var1;
   }

   public final void zzb(zzkk var1) {
      this.zztU = var1;
   }

   public final void zza(zzpq var1) {
      this.zzub = var1;
   }

   public final void zza(zzpn var1) {
      this.zzua = var1;
   }

   public final void zza(String var1, zzpw var2, zzpt var3) {
      if (TextUtils.isEmpty(var1)) {
         throw new IllegalArgumentException("Custom template ID for native custom template ad is empty. Please provide a valid template id.");
      } else {
         this.zzud.put(var1, var2);
         this.zzuc.put(var1, var3);
      }
   }

   public final void zza(zzpz var1, zziv var2) {
      this.zzue = var1;
      this.zztO = var2;
   }
}
