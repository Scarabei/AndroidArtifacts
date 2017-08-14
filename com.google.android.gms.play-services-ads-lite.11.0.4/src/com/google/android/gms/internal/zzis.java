package com.google.android.gms.internal;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@zzzn
public final class zzis {
   private long zzAd;
   private Bundle mExtras;
   private int zzAe;
   private List zzAf;
   private boolean zzAg;
   private int zzAh;
   private boolean zzsu;
   private String zzAi;
   private zzlt zzAj;
   private Location zzde;
   private String zzAk;
   private Bundle zzAl;
   private Bundle zzAm;
   private List zzAn;
   private String zzAo;
   private String zzAp;
   private boolean zzAq;

   public zzis() {
      this.zzAd = -1L;
      this.mExtras = new Bundle();
      this.zzAe = -1;
      this.zzAf = new ArrayList();
      this.zzAg = false;
      this.zzAh = -1;
      this.zzsu = false;
      this.zzAi = null;
      this.zzAj = null;
      this.zzde = null;
      this.zzAk = null;
      this.zzAl = new Bundle();
      this.zzAm = new Bundle();
      this.zzAn = new ArrayList();
      this.zzAo = null;
      this.zzAp = null;
      this.zzAq = false;
   }

   public zzis(zzir var1) {
      this.zzAd = var1.zzzN;
      this.mExtras = var1.extras;
      this.zzAe = var1.zzzO;
      this.zzAf = var1.zzzP;
      this.zzAg = var1.zzzQ;
      this.zzAh = var1.zzzR;
      this.zzsu = var1.zzzS;
      this.zzAi = var1.zzzT;
      this.zzAj = var1.zzzU;
      this.zzde = var1.zzzV;
      this.zzAk = var1.zzzW;
      this.zzAl = var1.zzzX;
      this.zzAm = var1.zzzY;
      this.zzAn = var1.zzzZ;
      this.zzAo = var1.zzAa;
      this.zzAp = var1.zzAb;
   }

   public final zzir zzdj() {
      return new zzir(7, this.zzAd, this.mExtras, this.zzAe, this.zzAf, this.zzAg, this.zzAh, this.zzsu, this.zzAi, this.zzAj, this.zzde, this.zzAk, this.zzAl, this.zzAm, this.zzAn, this.zzAo, this.zzAp, false);
   }

   public final zzis zza(@Nullable Location var1) {
      this.zzde = null;
      return this;
   }
}
