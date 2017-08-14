package com.google.android.gms.location.places.internal;

import android.support.annotation.Nullable;
import android.text.style.CharacterStyle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.location.places.AutocompletePrediction;
import java.util.Collections;
import java.util.List;

public final class zzd extends zzav implements AutocompletePrediction {
   public zzd(DataHolder var1, int var2) {
      super(var1, var2);
   }

   public final String getPlaceId() {
      return this.zzD("ap_place_id", (String)null);
   }

   public final List getPlaceTypes() {
      return this.zza("ap_place_types", Collections.emptyList());
   }

   public final CharSequence getFullText(@Nullable CharacterStyle var1) {
      return zzg.zza(this.zzvT(), this.zzvW(), var1);
   }

   public final CharSequence getPrimaryText(@Nullable CharacterStyle var1) {
      return zzg.zza(this.zzvU(), this.zzvX(), var1);
   }

   public final CharSequence getSecondaryText(@Nullable CharacterStyle var1) {
      return zzg.zza(this.zzvV(), this.zzvY(), var1);
   }

   private final String zzvT() {
      return this.zzD("ap_description", "");
   }

   private final String zzvU() {
      return this.zzD("ap_primary_text", "");
   }

   private final String zzvV() {
      return this.zzD("ap_secondary_text", "");
   }

   private final List zzvW() {
      return this.zza("ap_matched_subscriptions", zzb.CREATOR, Collections.emptyList());
   }

   private final List zzvX() {
      return this.zza("ap_primary_text_matched", zzb.CREATOR, Collections.emptyList());
   }

   private final List zzvY() {
      return this.zza("ap_secondary_text_matched", zzb.CREATOR, Collections.emptyList());
   }

   // $FF: synthetic method
   public final Object freeze() {
      String var10000 = this.getPlaceId();
      List var10001 = this.getPlaceTypes();
      int var10002 = this.zzu("ap_personalization_type", 6);
      String var10003 = this.zzvT();
      List var10004 = this.zzvW();
      String var10005 = this.zzvU();
      List var10006 = this.zzvX();
      String var10007 = this.zzvV();
      List var10 = this.zzvY();
      String var9 = var10007;
      List var8 = var10006;
      String var7 = var10005;
      List var6 = var10004;
      String var5 = var10003;
      int var4 = var10002;
      List var3 = var10001;
      String var2 = var10000;
      return new zza(var2, var3, var4, (String)zzbo.zzu(var5), var6, var7, var8, var9, var10);
   }
}
