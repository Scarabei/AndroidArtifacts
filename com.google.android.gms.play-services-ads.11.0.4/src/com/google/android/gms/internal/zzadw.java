package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzadw extends zzafp implements zzadv {
   private final zzafg zzQQ;
   private final Context mContext;
   private final ArrayList zzWN;
   private final ArrayList zzWO;
   private final HashMap zzWP;
   private final List zzWQ;
   private final HashSet zzWR;
   private final Object mLock;
   private final zzacs zzWS;
   private final long zzWC;

   public zzadw(Context var1, zzafg var2, zzacs var3) {
      zzme var4 = zzmo.zzDI;
      this(var1, var2, var3, ((Long)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var4)).longValue());
   }

   private zzadw(Context var1, zzafg var2, zzacs var3, long var4) {
      this.zzWN = new ArrayList();
      this.zzWO = new ArrayList();
      this.zzWP = new HashMap();
      this.zzWQ = new ArrayList();
      this.zzWR = new HashSet();
      this.mLock = new Object();
      this.mContext = var1;
      this.zzQQ = var2;
      this.zzWS = var3;
      this.zzWC = var4;
   }

   public final void zzbd() {
      Iterator var1 = this.zzQQ.zzXN.zzLY.iterator();

      String var3;
      label301:
      while(var1.hasNext()) {
         zzua var2;
         var3 = (var2 = (zzua)var1.next()).zzLP;
         Iterator var4 = var2.zzLJ.iterator();

         while(true) {
            String var5;
            while(true) {
               if (!var4.hasNext()) {
                  continue label301;
               }

               var5 = (String)var4.next();
               if (!"com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(var5) && !"com.google.ads.mediation.customevent.CustomEventAdapter".equals(var5)) {
                  break;
               }

               try {
                  var5 = (new JSONObject(var3)).getString("class_name");
                  break;
               } catch (JSONException var48) {
                  zzafr.zzb("Unable to determine custom event class name, skipping...", var48);
               }
            }

            zzua var19 = var2;
            String var18 = var3;
            String var17 = var5;
            zzadw var16 = this;
            synchronized(this.mLock) {
               zzadz var21;
               if ((var21 = var16.zzWS.zzav(var17)) != null && var21.zzgX() != null && var21.zzgW() != null) {
                  zzadm var22 = new zzadm(var16.mContext, var17, var18, var19, var16.zzQQ, var21, var16, var16.zzWC);
                  var16.zzWN.add((Future)var22.zzgp());
                  var16.zzWO.add(var17);
                  var16.zzWP.put(var17, var22);
               } else {
                  var16.zzWQ.add((new zzadr()).zzay(var19.zzLK).zzax(var17).zzg(0L).zzw(7).zzgU());
               }
            }
         }
      }

      for(int var50 = 0; var50 < this.zzWN.size(); ++var50) {
         boolean var39 = false;

         label307: {
            Object var53;
            String var54;
            zzadm var57;
            label308: {
               try {
                  var39 = true;
                  ((Future)this.zzWN.get(var50)).get();
                  var39 = false;
                  break label307;
               } catch (InterruptedException var45) {
                  Thread.currentThread().interrupt();
                  var39 = false;
                  break label308;
               } catch (Exception var46) {
                  zzafr.zzc("Unable to resolve rewarded adapter.", var46);
                  var39 = false;
               } finally {
                  if (var39) {
                     Object var11 = this.mLock;
                     synchronized(this.mLock) {
                        String var12;
                        zzadm var13;
                        if (!TextUtils.isEmpty(var12 = (String)this.zzWO.get(var50)) && (var13 = (zzadm)this.zzWP.get(var12)) != null) {
                           this.zzWQ.add(var13.zzgR());
                        }

                     }
                  }
               }

               var53 = this.mLock;
               synchronized(this.mLock) {
                  if (!TextUtils.isEmpty(var54 = (String)this.zzWO.get(var50)) && (var57 = (zzadm)this.zzWP.get(var54)) != null) {
                     this.zzWQ.add(var57.zzgR());
                  }
                  continue;
               }
            }

            var53 = this.mLock;
            synchronized(this.mLock) {
               if (!TextUtils.isEmpty(var54 = (String)this.zzWO.get(var50)) && (var57 = (zzadm)this.zzWP.get(var54)) != null) {
                  this.zzWQ.add(var57.zzgR());
               }
               break;
            }
         }

         Object var52 = this.mLock;
         synchronized(this.mLock) {
            zzadm var55;
            if (!TextUtils.isEmpty(var3 = (String)this.zzWO.get(var50)) && (var55 = (zzadm)this.zzWP.get(var3)) != null) {
               this.zzWQ.add(var55.zzgR());
            }
         }

         var52 = this.mLock;
         synchronized(this.mLock) {
            if (this.zzWR.contains(this.zzWO.get(var50))) {
               var3 = (String)this.zzWO.get(var50);
               zzua var58 = this.zzWP.get(var3) != null ? ((zzadm)this.zzWP.get(var3)).zzgS() : null;
               zzaff var56 = this.zza(-2, var3, var58);
               zzaiy.zzaaH.post(new zzadx(this, var56));
               return;
            }
         }
      }

      zzaff var51 = this.zza(3, (String)null, (zzua)null);
      zzaiy.zzaaH.post(new zzady(this, var51));
   }

   public final void onStop() {
   }

   public final void zzaw(String var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzWR.add(var1);
      }
   }

   public final void zza(String var1, int var2) {
   }

   private final zzaff zza(int var1, @Nullable String var2, @Nullable zzua var3) {
      return new zzaff(this.zzQQ.zzUj.zzSz, (zzaka)null, this.zzQQ.zzXY.zzMa, var1, this.zzQQ.zzXY.zzMb, this.zzQQ.zzXY.zzTq, this.zzQQ.zzXY.orientation, this.zzQQ.zzXY.zzMg, this.zzQQ.zzUj.zzSC, this.zzQQ.zzXY.zzTo, var3, (zzut)null, var2, this.zzQQ.zzXN, (zzud)null, this.zzQQ.zzXY.zzTp, this.zzQQ.zzvX, this.zzQQ.zzXY.zzTn, this.zzQQ.zzXR, this.zzQQ.zzXY.zzTs, this.zzQQ.zzXY.zzTt, this.zzQQ.zzXL, (zzoa)null, this.zzQQ.zzXY.zzTD, this.zzQQ.zzXY.zzTE, this.zzQQ.zzXY.zzTF, this.zzQQ.zzXY.zzTG, this.zzQQ.zzXY.zzTH, this.zzgV(), this.zzQQ.zzXY.zzMd, this.zzQQ.zzXY.zzTK, this.zzQQ.zzXX);
   }

   private final String zzgV() {
      StringBuilder var1 = new StringBuilder("");
      if (this.zzWQ == null) {
         return var1.toString();
      } else {
         Iterator var2 = this.zzWQ.iterator();

         while(var2.hasNext()) {
            zzadp var3;
            if ((var3 = (zzadp)var2.next()) != null && !TextUtils.isEmpty(var3.zzLK)) {
               String var5 = var3.zzLK;
               byte var10001;
               switch(var3.errorCode) {
               case 3:
                  var10001 = 1;
                  break;
               case 4:
                  var10001 = 2;
                  break;
               case 5:
                  var10001 = 4;
                  break;
               case 6:
                  var10001 = 0;
                  break;
               case 7:
                  var10001 = 3;
                  break;
               default:
                  var10001 = 6;
               }

               byte var6 = var10001;
               long var7 = var3.zzML;
               var1.append(String.valueOf((new StringBuilder(33 + String.valueOf(var5).length())).append(var5).append(".").append(var6).append(".").append(var7).toString()).concat("_"));
            }
         }

         return var1.substring(0, Math.max(0, var1.length() - 1));
      }
   }

   // $FF: synthetic method
   static zzacs zza(zzadw var0) {
      return var0.zzWS;
   }
}
