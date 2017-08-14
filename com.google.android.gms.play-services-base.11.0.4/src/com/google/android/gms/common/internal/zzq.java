package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.view.View;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzctl;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class zzq {
   private final Account zzajb;
   private final Set zzaAT;
   private final Set zzaHk;
   private final Map zzaHl;
   private final int zzaAV;
   private final View zzaAW;
   private final String zzake;
   private final String zzaAX;
   private final zzctl zzaHm;
   private Integer zzaHn;

   public static zzq zzaA(Context var0) {
      return (new GoogleApiClient.Builder(var0)).zzpn();
   }

   public zzq(Account var1, Set var2, Map var3, int var4, View var5, String var6, String var7, zzctl var8) {
      this.zzajb = var1;
      this.zzaAT = var2 == null ? Collections.EMPTY_SET : Collections.unmodifiableSet(var2);
      this.zzaHl = var3 == null ? Collections.EMPTY_MAP : var3;
      this.zzaAW = var5;
      this.zzaAV = var4;
      this.zzake = var6;
      this.zzaAX = var7;
      this.zzaHm = var8;
      HashSet var9 = new HashSet(this.zzaAT);
      Iterator var10 = this.zzaHl.values().iterator();

      while(var10.hasNext()) {
         zzr var11 = (zzr)var10.next();
         var9.addAll(var11.zzame);
      }

      this.zzaHk = Collections.unmodifiableSet(var9);
   }

   /** @deprecated */
   @Deprecated
   public final String getAccountName() {
      return this.zzajb != null ? this.zzajb.name : null;
   }

   public final Account getAccount() {
      return this.zzajb;
   }

   public final Account zzrl() {
      return this.zzajb != null ? this.zzajb : new Account("<<default account>>", "com.google");
   }

   public final int zzrm() {
      return this.zzaAV;
   }

   public final Set zzrn() {
      return this.zzaAT;
   }

   public final Set zzro() {
      return this.zzaHk;
   }

   public final Map zzrp() {
      return this.zzaHl;
   }

   public final String zzrq() {
      return this.zzake;
   }

   public final String zzrr() {
      return this.zzaAX;
   }

   public final View zzrs() {
      return this.zzaAW;
   }

   public final zzctl zzrt() {
      return this.zzaHm;
   }

   public final Integer zzru() {
      return this.zzaHn;
   }

   public final void zzc(Integer var1) {
      this.zzaHn = var1;
   }

   public final Set zzc(Api var1) {
      zzr var2;
      if ((var2 = (zzr)this.zzaHl.get(var1)) != null && !var2.zzame.isEmpty()) {
         HashSet var3;
         (var3 = new HashSet(this.zzaAT)).addAll(var2.zzame);
         return var3;
      } else {
         return this.zzaAT;
      }
   }
}
