package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.eg;
import com.google.android.gms.internal.ei;
import com.google.android.gms.internal.ek;
import com.google.android.gms.internal.em;
import com.google.android.gms.internal.zzbg;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

final class zzfc {
   private static final zzea zzbGe = new zzea(zzgk.zzCh(), true);
   private final ek zzbGf;
   private final zzbo zzbGg;
   private final Map zzbGh;
   private final Map zzbGi;
   private final Map zzbGj;
   private final zzp zzbGk;
   private final zzp zzbGl;
   private final Set zzbGm;
   private final DataLayer zzbDx;
   private final Map zzbGn;
   private volatile String zzbGo;
   private int zzbGp;

   public zzfc(Context var1, ek var2, DataLayer var3, zzan var4, zzan var5, zzbo var6) {
      if (var2 == null) {
         throw new NullPointerException("resource cannot be null");
      } else {
         this.zzbGf = var2;
         this.zzbGm = new HashSet(var2.zzCX());
         this.zzbDx = var3;
         this.zzbGg = var6;
         zzfd var7 = new zzfd(this);
         new zzq();
         this.zzbGk = zzq.zza(1048576, var7);
         zzfe var8 = new zzfe(this);
         new zzq();
         this.zzbGl = zzq.zza(1048576, var8);
         this.zzbGh = new HashMap();
         this.zzb(new zzm(var1));
         this.zzb(new zzam(var5));
         this.zzb(new zzaz(var3));
         this.zzb(new zzgl(var1, var3));
         this.zzbGi = new HashMap();
         this.zzc(new zzak());
         this.zzc(new zzbl());
         this.zzc(new zzbm());
         this.zzc(new zzbt());
         this.zzc(new zzbu());
         this.zzc(new zzdf());
         this.zzc(new zzdg());
         this.zzc(new zzem());
         this.zzc(new zzfz());
         this.zzbGj = new HashMap();
         this.zza((zzbr)(new zze(var1)));
         this.zza((zzbr)(new zzf(var1)));
         this.zza((zzbr)(new zzh(var1)));
         this.zza((zzbr)(new zzi(var1)));
         this.zza((zzbr)(new zzj(var1)));
         this.zza((zzbr)(new zzk(var1)));
         this.zza((zzbr)(new zzl(var1)));
         this.zza((zzbr)(new zzt()));
         this.zza((zzbr)(new zzaj(this.zzbGf.getVersion())));
         this.zza((zzbr)(new zzam(var4)));
         this.zza((zzbr)(new zzas(var3)));
         this.zza((zzbr)(new zzbc(var1)));
         this.zza((zzbr)(new zzbd()));
         this.zza((zzbr)(new zzbk()));
         this.zza((zzbr)(new zzbp(this)));
         this.zza((zzbr)(new zzbv()));
         this.zza((zzbr)(new zzbw()));
         this.zza((zzbr)(new zzcw(var1)));
         this.zza((zzbr)(new zzcy()));
         this.zza((zzbr)(new zzde()));
         this.zza((zzbr)(new zzdl()));
         this.zza((zzbr)(new zzdn(var1)));
         this.zza((zzbr)(new zzeb()));
         this.zza((zzbr)(new zzef()));
         this.zza((zzbr)(new zzej()));
         this.zza((zzbr)(new zzel()));
         this.zza((zzbr)(new zzen(var1)));
         this.zza((zzbr)(new zzfk()));
         this.zza((zzbr)(new zzfl()));
         this.zza((zzbr)(new zzgf()));
         this.zza((zzbr)(new zzgm()));
         this.zzbGn = new HashMap();
         Iterator var9 = this.zzbGm.iterator();

         ei var12;
         while(var9.hasNext()) {
            em var10 = (em)var9.next();

            int var11;
            String var13;
            zzfj var14;
            for(var11 = 0; var11 < var10.zzDC().size(); ++var11) {
               var12 = (ei)var10.zzDC().get(var11);
               var13 = "Unknown";
               (var14 = zzf(this.zzbGn, zza(var12))).zza(var10);
               var14.zza(var10, var12);
               var14.zza(var10, var13);
            }

            for(var11 = 0; var11 < var10.zzDD().size(); ++var11) {
               var12 = (ei)var10.zzDD().get(var11);
               var13 = "Unknown";
               (var14 = zzf(this.zzbGn, zza(var12))).zza(var10);
               var14.zzb(var10, var12);
               var14.zzb(var10, var13);
            }
         }

         var9 = this.zzbGf.zzDA().entrySet().iterator();

         while(var9.hasNext()) {
            Entry var15;
            Iterator var16 = ((List)(var15 = (Entry)var9.next()).getValue()).iterator();

            while(var16.hasNext()) {
               if (!zzgk.zzf((com.google.android.gms.internal.zzbr)(var12 = (ei)var16.next()).zzCZ().get(zzbg.zzix.toString())).booleanValue()) {
                  zzf(this.zzbGn, (String)var15.getKey()).zzb(var12);
               }
            }
         }

      }
   }

   public final synchronized void zzL(List var1) {
      Iterator var2 = var1.iterator();

      while(true) {
         while(var2.hasNext()) {
            com.google.android.gms.internal.zzbp var3;
            if ((var3 = (com.google.android.gms.internal.zzbp)var2.next()).name != null && var3.name.startsWith("gaExperiment:")) {
               zzbq.zza(this.zzbDx, var3);
            } else {
               String var4 = String.valueOf(var3);
               zzdj.v((new StringBuilder(22 + String.valueOf(var4).length())).append("Ignored supplemental: ").append(var4).toString());
            }
         }

         return;
      }
   }

   public final synchronized void zzeZ(String var1) {
      this.zzft(var1);
      zzar var2 = this.zzbGg.zzfj(var1).zzBj();
      Set var10001 = this.zzbGm;
      zzfb var7 = var2.zzAZ();
      Set var6 = var10001;
      Iterator var3 = ((Set)this.zza((Set)var6, (Set)(new HashSet()), (zzfh)(new zzfg(this)), (zzfb)var7).getObject()).iterator();

      while(var3.hasNext()) {
         ei var4 = (ei)var3.next();
         this.zza((Map)this.zzbGh, (ei)var4, (Set)(new HashSet()), (zzeo)var2.zzAY());
      }

      this.zzft((String)null);
   }

   public final zzea zzfs(String var1) {
      this.zzbGp = 0;
      zzbn var2 = this.zzbGg.zzfi(var1);
      return this.zza((String)var1, new HashSet(), (zzdm)var2.zzBi());
   }

   private final synchronized void zzft(String var1) {
      this.zzbGo = var1;
   }

   final synchronized String zzBK() {
      return this.zzbGo;
   }

   private final zzea zza(String var1, Set var2, Map var3, Map var4, Map var5, Map var6, Set var7, zzfb var8) {
      return this.zza((Set)var2, (Set)var7, (zzfh)(new zzff(this, var3, var4, var5, var6)), (zzfb)var8);
   }

   private static zzfj zzf(Map var0, String var1) {
      zzfj var2;
      if ((var2 = (zzfj)var0.get(var1)) == null) {
         var2 = new zzfj();
         var0.put(var1, var2);
      }

      return var2;
   }

   private final zzea zza(Set var1, Set var2, zzfh var3, zzfb var4) {
      HashSet var5 = new HashSet();
      HashSet var6 = new HashSet();
      boolean var7 = true;

      zzea var11;
      for(Iterator var8 = var1.iterator(); var8.hasNext(); var7 = var7 && var11.zzBz()) {
         em var9 = (em)var8.next();
         zzer var10 = var4.zzBx();
         if (((Boolean)(var11 = this.zza(var9, var2, var10)).getObject()).booleanValue()) {
            var3.zza(var9, var5, var6, var10);
         }
      }

      var5.removeAll(var6);
      return new zzea(var5, var7);
   }

   private static String zza(ei var0) {
      return zzgk.zzb((com.google.android.gms.internal.zzbr)var0.zzCZ().get(zzbg.zzhS.toString()));
   }

   private static void zza(Map var0, zzbr var1) {
      if (var0.containsKey(var1.zzBk())) {
         IllegalArgumentException var10000 = new IllegalArgumentException;
         String var10003 = String.valueOf(var1.zzBk());
         String var10002;
         if (var10003.length() != 0) {
            var10002 = "Duplicate function type name: ".concat(var10003);
         } else {
            String var10004 = new String;
            var10002 = var10004;
            var10004.<init>("Duplicate function type name: ");
         }

         var10000.<init>(var10002);
         throw var10000;
      } else {
         var0.put(var1.zzBk(), var1);
      }
   }

   private final void zza(zzbr var1) {
      zza(this.zzbGj, var1);
   }

   private final void zzb(zzbr var1) {
      zza(this.zzbGh, var1);
   }

   private final void zzc(zzbr var1) {
      zza(this.zzbGi, var1);
   }

   private final zzea zza(ei var1, Set var2, zzeo var3) {
      zzea var4;
      Boolean var5;
      zzgk.zzI(var5 = zzgk.zzf((com.google.android.gms.internal.zzbr)(var4 = this.zza(this.zzbGi, var1, var2, var3)).getObject()));
      return new zzea(var5, var4.zzBz());
   }

   private final zzea zza(em var1, Set var2, zzer var3) {
      boolean var4 = true;

      Iterator var5;
      ei var6;
      zzea var7;
      for(var5 = var1.zzDc().iterator(); var5.hasNext(); var4 = var4 && var7.zzBz()) {
         var6 = (ei)var5.next();
         if (((Boolean)(var7 = this.zza(var6, var2, var3.zzBr())).getObject()).booleanValue()) {
            zzgk.zzI(false);
            return new zzea(false, var7.zzBz());
         }
      }

      for(var5 = var1.zzDb().iterator(); var5.hasNext(); var4 = var4 && var7.zzBz()) {
         var6 = (ei)var5.next();
         if (!((Boolean)(var7 = this.zza(var6, var2, var3.zzBs())).getObject()).booleanValue()) {
            zzgk.zzI(false);
            return new zzea(false, var7.zzBz());
         }
      }

      zzgk.zzI(true);
      return new zzea(true, var4);
   }

   private final String zzBL() {
      if (this.zzbGp <= 1) {
         return "";
      } else {
         StringBuilder var1;
         (var1 = new StringBuilder()).append(Integer.toString(this.zzbGp));

         for(int var2 = 2; var2 < this.zzbGp; ++var2) {
            var1.append(' ');
         }

         var1.append(": ");
         return var1.toString();
      }
   }

   private final zzea zza(String var1, Set var2, zzdm var3) {
      ++this.zzbGp;
      zzfi var4;
      if ((var4 = (zzfi)this.zzbGl.get(var1)) != null) {
         this.zza(var4.zzBN(), var2);
         --this.zzbGp;
         return var4.zzBM();
      } else {
         zzfj var5;
         if ((var5 = (zzfj)this.zzbGn.get(var1)) == null) {
            String var12 = String.valueOf(this.zzBL());
            zzdj.e((new StringBuilder(15 + String.valueOf(var12).length() + String.valueOf(var1).length())).append(var12).append("Invalid macro: ").append(var1).toString());
            --this.zzbGp;
            return zzbGe;
         } else {
            zzea var6;
            ei var7;
            if (((Set)(var6 = this.zza(var1, var5.zzBO(), var5.zzBP(), var5.zzBQ(), var5.zzBS(), var5.zzBR(), var2, var3.zzAZ())).getObject()).isEmpty()) {
               var7 = var5.zzBT();
            } else {
               if (((Set)var6.getObject()).size() > 1) {
                  String var8 = String.valueOf(this.zzBL());
                  zzdj.zzaT((new StringBuilder(37 + String.valueOf(var8).length() + String.valueOf(var1).length())).append(var8).append("Multiple macros active for macroName ").append(var1).toString());
               }

               var7 = (ei)((Set)var6.getObject()).iterator().next();
            }

            if (var7 == null) {
               --this.zzbGp;
               return zzbGe;
            } else {
               zzea var13 = this.zza(this.zzbGj, var7, var2, var3.zzBq());
               boolean var9 = var6.zzBz() && var13.zzBz();
               zzea var10 = var13 == zzbGe ? zzbGe : new zzea((com.google.android.gms.internal.zzbr)var13.getObject(), var9);
               com.google.android.gms.internal.zzbr var11 = var7.zzBN();
               if (var10.zzBz()) {
                  this.zzbGl.zzf(var1, new zzfi(var10, var11));
               }

               this.zza(var11, var2);
               --this.zzbGp;
               return var10;
            }
         }
      }
   }

   private final void zza(com.google.android.gms.internal.zzbr var1, Set var2) {
      if (var1 != null) {
         zzea var3;
         if ((var3 = this.zza((com.google.android.gms.internal.zzbr)var1, var2, (zzgn)(new zzdy()))) != zzbGe) {
            Object var4;
            if ((var4 = zzgk.zzg((com.google.android.gms.internal.zzbr)var3.getObject())) instanceof Map) {
               Map var5 = (Map)var4;
               this.zzbDx.push(var5);
            } else if (var4 instanceof List) {
               Iterator var6 = ((List)var4).iterator();

               while(var6.hasNext()) {
                  Object var7;
                  if ((var7 = var6.next()) instanceof Map) {
                     Map var8 = (Map)var7;
                     this.zzbDx.push(var8);
                  } else {
                     zzdj.zzaT("pushAfterEvaluate: value not a Map");
                  }
               }

            } else {
               zzdj.zzaT("pushAfterEvaluate: value not a Map or List");
            }
         }
      }
   }

   private final zzea zza(com.google.android.gms.internal.zzbr var1, Set var2, zzgn var3) {
      if (!var1.zzlN) {
         return new zzea(var1, true);
      } else {
         com.google.android.gms.internal.zzbr var4;
         int var5;
         zzea var6;
         switch(var1.type) {
         case 2:
            (var4 = eg.zzj(var1)).zzlE = new com.google.android.gms.internal.zzbr[var1.zzlE.length];

            for(var5 = 0; var5 < var1.zzlE.length; ++var5) {
               if ((var6 = this.zza(var1.zzlE[var5], var2, var3.zzbz(var5))) == zzbGe) {
                  return zzbGe;
               }

               var4.zzlE[var5] = (com.google.android.gms.internal.zzbr)var6.getObject();
            }

            return new zzea(var4, false);
         case 3:
            var4 = eg.zzj(var1);
            if (var1.zzlF.length != var1.zzlG.length) {
               String var10001 = String.valueOf(var1.toString());
               String var10000;
               if (var10001.length() != 0) {
                  var10000 = "Invalid serving value: ".concat(var10001);
               } else {
                  String var10002 = new String;
                  var10000 = var10002;
                  var10002.<init>("Invalid serving value: ");
               }

               zzdj.e(var10000);
               return zzbGe;
            } else {
               var4.zzlF = new com.google.android.gms.internal.zzbr[var1.zzlF.length];
               var4.zzlG = new com.google.android.gms.internal.zzbr[var1.zzlF.length];

               for(var5 = 0; var5 < var1.zzlF.length; ++var5) {
                  var6 = this.zza(var1.zzlF[var5], var2, var3.zzbA(var5));
                  zzea var7 = this.zza(var1.zzlG[var5], var2, var3.zzbB(var5));
                  if (var6 == zzbGe || var7 == zzbGe) {
                     return zzbGe;
                  }

                  var4.zzlF[var5] = (com.google.android.gms.internal.zzbr)var6.getObject();
                  var4.zzlG[var5] = (com.google.android.gms.internal.zzbr)var7.getObject();
               }

               return new zzea(var4, false);
            }
         case 4:
            if (var2.contains(var1.zzlH)) {
               String var9 = String.valueOf(var1.zzlH);
               String var11 = String.valueOf(var2.toString());
               zzdj.e((new StringBuilder(79 + String.valueOf(var9).length() + String.valueOf(var11).length())).append("Macro cycle detected.  Current macro reference: ").append(var9).append(".  Previous macro references: ").append(var11).append(".").toString());
               return zzbGe;
            }

            var2.add(var1.zzlH);
            zzea var8 = zzgo.zza(this.zza(var1.zzlH, var2, var3.zzBy()), var1.zzlM);
            var2.remove(var1.zzlH);
            return var8;
         case 5:
         case 6:
         default:
            int var10 = var1.type;
            zzdj.e((new StringBuilder(25)).append("Unknown type: ").append(var10).toString());
            return zzbGe;
         case 7:
            (var4 = eg.zzj(var1)).zzlL = new com.google.android.gms.internal.zzbr[var1.zzlL.length];

            for(var5 = 0; var5 < var1.zzlL.length; ++var5) {
               if ((var6 = this.zza(var1.zzlL[var5], var2, var3.zzbC(var5))) == zzbGe) {
                  return zzbGe;
               }

               var4.zzlL[var5] = (com.google.android.gms.internal.zzbr)var6.getObject();
            }

            return new zzea(var4, false);
         }
      }
   }

   private final zzea zza(Map var1, ei var2, Set var3, zzeo var4) {
      com.google.android.gms.internal.zzbr var5;
      if ((var5 = (com.google.android.gms.internal.zzbr)var2.zzCZ().get(zzbg.zzhG.toString())) == null) {
         zzdj.e("No function id in properties");
         return zzbGe;
      } else {
         String var6 = var5.zzlI;
         zzbr var7;
         if ((var7 = (zzbr)var1.get(var6)) == null) {
            zzdj.e(String.valueOf(var6).concat(" has no backing implementation."));
            return zzbGe;
         } else {
            zzea var8;
            if ((var8 = (zzea)this.zzbGk.get(var2)) != null) {
               return var8;
            } else {
               HashMap var9 = new HashMap();
               boolean var10 = true;

               Entry var12;
               zzea var14;
               for(Iterator var11 = var2.zzCZ().entrySet().iterator(); var11.hasNext(); var9.put((String)var12.getKey(), (com.google.android.gms.internal.zzbr)var14.getObject())) {
                  var12 = (Entry)var11.next();
                  zzeq var13 = var4.zzfp((String)var12.getKey());
                  if ((var14 = this.zza((com.google.android.gms.internal.zzbr)var12.getValue(), var3, var13.zza((com.google.android.gms.internal.zzbr)var12.getValue()))) == zzbGe) {
                     return zzbGe;
                  }

                  if (var14.zzBz()) {
                     var2.zza((String)var12.getKey(), (com.google.android.gms.internal.zzbr)var14.getObject());
                  } else {
                     var10 = false;
                  }
               }

               if (!var7.zzd(var9.keySet())) {
                  String var16 = String.valueOf(var7.zzBl());
                  String var18 = String.valueOf(var9.keySet());
                  zzdj.e((new StringBuilder(43 + String.valueOf(var6).length() + String.valueOf(var16).length() + String.valueOf(var18).length())).append("Incorrect keys for function ").append(var6).append(" required ").append(var16).append(" had ").append(var18).toString());
                  return zzbGe;
               } else {
                  boolean var15 = var10 && var7.zzAE();
                  zzea var17 = new zzea(var7.zzo(var9), var15);
                  if (var15) {
                     this.zzbGk.zzf(var2, var17);
                  }

                  return var17;
               }
            }
         }
      }
   }
}
