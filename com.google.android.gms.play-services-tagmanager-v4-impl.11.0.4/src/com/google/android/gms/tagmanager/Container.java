package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.eg;
import com.google.android.gms.internal.ek;
import com.google.android.gms.internal.eo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Container {
   private final Context mContext;
   private final String zzbDw;
   private final DataLayer zzbDx;
   private zzfc zzbDy;
   private Map zzbDz = new HashMap();
   private Map zzbDA = new HashMap();
   private volatile long zzbDB;
   private volatile String zzbDC = "";

   Container(Context var1, DataLayer var2, String var3, long var4, ek var6) {
      this.mContext = var1;
      this.zzbDx = var2;
      this.zzbDw = var3;
      this.zzbDB = 0L;
      this.zza(var6);
   }

   Container(Context var1, DataLayer var2, String var3, long var4, com.google.android.gms.internal.zzbq var6) {
      this.mContext = var1;
      this.zzbDx = var2;
      this.zzbDw = var3;
      this.zzbDB = var4;
      com.google.android.gms.internal.zzbn var8 = var6.zzlB;
      if (var8 == null) {
         throw new NullPointerException();
      } else {
         label19: {
            ek var9;
            try {
               var9 = eg.zza(var8);
            } catch (eo var13) {
               String var11 = String.valueOf(var8);
               String var12 = String.valueOf(var13.toString());
               zzdj.e((new StringBuilder(46 + String.valueOf(var11).length() + String.valueOf(var12).length())).append("Not loading resource: ").append(var11).append(" because it is invalid: ").append(var12).toString());
               break label19;
            }

            this.zza(var9);
         }

         if (var6.zzlA != null) {
            this.zza(var6.zzlA);
         }

      }
   }

   public String getContainerId() {
      return this.zzbDw;
   }

   public boolean getBoolean(String var1) {
      zzfc var2;
      if ((var2 = this.zzAJ()) == null) {
         zzdj.e("getBoolean called for closed container.");
         return zzgk.zzCe().booleanValue();
      } else {
         try {
            return zzgk.zzf((com.google.android.gms.internal.zzbr)var2.zzfs(var1).getObject()).booleanValue();
         } catch (Exception var4) {
            String var3 = String.valueOf(var4.getMessage());
            zzdj.e((new StringBuilder(66 + String.valueOf(var3).length())).append("Calling getBoolean() threw an exception: ").append(var3).append(" Returning default value.").toString());
            return zzgk.zzCe().booleanValue();
         }
      }
   }

   public double getDouble(String var1) {
      zzfc var2;
      if ((var2 = this.zzAJ()) == null) {
         zzdj.e("getDouble called for closed container.");
         return zzgk.zzCd().doubleValue();
      } else {
         try {
            return zzgk.zze((com.google.android.gms.internal.zzbr)var2.zzfs(var1).getObject()).doubleValue();
         } catch (Exception var4) {
            String var3 = String.valueOf(var4.getMessage());
            zzdj.e((new StringBuilder(65 + String.valueOf(var3).length())).append("Calling getDouble() threw an exception: ").append(var3).append(" Returning default value.").toString());
            return zzgk.zzCd().doubleValue();
         }
      }
   }

   public long getLong(String var1) {
      zzfc var2;
      if ((var2 = this.zzAJ()) == null) {
         zzdj.e("getLong called for closed container.");
         return zzgk.zzCc().longValue();
      } else {
         try {
            return zzgk.zzd((com.google.android.gms.internal.zzbr)var2.zzfs(var1).getObject()).longValue();
         } catch (Exception var4) {
            String var3 = String.valueOf(var4.getMessage());
            zzdj.e((new StringBuilder(63 + String.valueOf(var3).length())).append("Calling getLong() threw an exception: ").append(var3).append(" Returning default value.").toString());
            return zzgk.zzCc().longValue();
         }
      }
   }

   public String getString(String var1) {
      zzfc var2;
      if ((var2 = this.zzAJ()) == null) {
         zzdj.e("getString called for closed container.");
         return zzgk.zzCg();
      } else {
         try {
            return zzgk.zzb((com.google.android.gms.internal.zzbr)var2.zzfs(var1).getObject());
         } catch (Exception var4) {
            String var3 = String.valueOf(var4.getMessage());
            zzdj.e((new StringBuilder(65 + String.valueOf(var3).length())).append("Calling getString() threw an exception: ").append(var3).append(" Returning default value.").toString());
            return zzgk.zzCg();
         }
      }
   }

   public long getLastRefreshTime() {
      return this.zzbDB;
   }

   public boolean isDefault() {
      return this.getLastRefreshTime() == 0L;
   }

   public void registerFunctionCallMacroCallback(String var1, Container.FunctionCallMacroCallback var2) {
      if (var2 == null) {
         throw new NullPointerException("Macro handler must be non-null");
      } else {
         Map var3 = this.zzbDz;
         synchronized(this.zzbDz) {
            this.zzbDz.put(var1, var2);
         }
      }
   }

   public void unregisterFunctionCallMacroCallback(String var1) {
      Map var2 = this.zzbDz;
      synchronized(this.zzbDz) {
         this.zzbDz.remove(var1);
      }
   }

   final Container.FunctionCallMacroCallback zzeX(String var1) {
      Map var2 = this.zzbDz;
      synchronized(this.zzbDz) {
         return (Container.FunctionCallMacroCallback)this.zzbDz.get(var1);
      }
   }

   public void registerFunctionCallTagCallback(String var1, Container.FunctionCallTagCallback var2) {
      if (var2 == null) {
         throw new NullPointerException("Tag callback must be non-null");
      } else {
         Map var3 = this.zzbDA;
         synchronized(this.zzbDA) {
            this.zzbDA.put(var1, var2);
         }
      }
   }

   public void unregisterFunctionCallTagCallback(String var1) {
      Map var2 = this.zzbDA;
      synchronized(this.zzbDA) {
         this.zzbDA.remove(var1);
      }
   }

   public final Container.FunctionCallTagCallback zzeY(String var1) {
      Map var2 = this.zzbDA;
      synchronized(this.zzbDA) {
         return (Container.FunctionCallTagCallback)this.zzbDA.get(var1);
      }
   }

   public final void zzeZ(String var1) {
      this.zzAJ().zzeZ(var1);
   }

   public final String zzAI() {
      return this.zzbDC;
   }

   private final void zza(com.google.android.gms.internal.zzbp[] var1) {
      ArrayList var2 = new ArrayList();
      com.google.android.gms.internal.zzbp[] var3 = var1;
      int var4 = var1.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         com.google.android.gms.internal.zzbp var6 = var3[var5];
         var2.add(var6);
      }

      this.zzAJ().zzL(var2);
   }

   private final void zza(ek var1) {
      this.zzbDC = var1.getVersion();
      String var10000 = this.zzbDC;
      zzei.zzBD().zzBE().equals(zzei.zza.zzbFL);
      zzdr var2 = new zzdr();
      zzfc var3 = new zzfc(this.mContext, var1, this.zzbDx, new Container.zza((zzu)null), new Container.zzb((zzu)null), var2);
      this.zza(var3);
      if (this.getBoolean("_gtm.loadEventEnabled")) {
         this.zzbDx.pushEvent("gtm.load", DataLayer.mapOf("gtm.id", this.zzbDw));
      }

   }

   private final synchronized void zza(zzfc var1) {
      this.zzbDy = var1;
   }

   private final synchronized zzfc zzAJ() {
      return this.zzbDy;
   }

   final void release() {
      this.zzbDy = null;
   }

   class zzb implements zzan {
      // $FF: synthetic field
      private Container zzbDD;

      private zzb() {
         this.zzbDD = Container.this;
         super();
      }

      public final Object zzd(String var1, Map var2) {
         Container.FunctionCallTagCallback var3;
         if ((var3 = this.zzbDD.zzeY(var1)) != null) {
            var3.execute(var1, var2);
         }

         return zzgk.zzCg();
      }

      // $FF: synthetic method
      zzb(zzu var2) {
         this();
      }
   }

   class zza implements zzan {
      // $FF: synthetic field
      private Container zzbDD;

      private zza() {
         this.zzbDD = Container.this;
         super();
      }

      public final Object zzd(String var1, Map var2) {
         Container.FunctionCallMacroCallback var3;
         return (var3 = this.zzbDD.zzeX(var1)) == null ? null : var3.getValue(var1, var2);
      }

      // $FF: synthetic method
      zza(zzu var2) {
         this();
      }
   }

   public interface FunctionCallTagCallback {
      void execute(String var1, Map var2);
   }

   public interface FunctionCallMacroCallback {
      Object getValue(String var1, Map var2);
   }
}
