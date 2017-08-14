package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class zzec implements zzcc {
   private static final String zzagp = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL,'%s' INTEGER NOT NULL);", "gtm_hits", "hit_id", "hit_time", "hit_url", "hit_first_send_time");
   private final zzee zzbFu;
   private volatile zzbe zzbFv;
   private final zzcd zzbFw;
   private final Context mContext;
   private final String zzbFx;
   private long zzbFy;
   private com.google.android.gms.common.util.zze zzvw;
   private final int zzbFz;

   zzec(zzcd var1, Context var2) {
      this(var1, var2, "gtm_urls.db", 2000);
   }

   private zzec(zzcd var1, Context var2, String var3, int var4) {
      this.mContext = var2.getApplicationContext();
      this.zzbFx = var3;
      this.zzbFw = var1;
      this.zzvw = com.google.android.gms.common.util.zzi.zzrY();
      this.zzbFu = new zzee(this, this.mContext, this.zzbFx);
      this.zzbFv = new zzfv(this.mContext, new zzed(this));
      this.zzbFy = 0L;
      this.zzbFz = 2000;
   }

   public final void zzb(long var1, String var3) {
      long var5;
      if ((var5 = this.zzvw.currentTimeMillis()) > this.zzbFy + 86400000L) {
         this.zzbFy = var5;
         SQLiteDatabase var7;
         if ((var7 = this.zzfg("Error opening database for deleteStaleHits.")) != null) {
            long var8 = this.zzvw.currentTimeMillis() - 2592000000L;
            var7.delete("gtm_hits", "HIT_TIME < ?", new String[]{Long.toString(var8)});
            this.zzbFw.zzar(this.zzBA() == 0);
         }
      }

      int var11;
      if ((var11 = this.zzBA() - this.zzbFz + 1) > 0) {
         List var6;
         int var12 = (var6 = this.zzbD(var11)).size();
         zzdj.v((new StringBuilder(51)).append("Store full, deleting ").append(var12).append(" hits to make room.").toString());
         this.zzd((String[])var6.toArray(new String[0]));
      }

      zzec var4 = this;
      SQLiteDatabase var13;
      if ((var13 = this.zzfg("Error opening database for putHit")) != null) {
         ContentValues var9;
         (var9 = new ContentValues()).put("hit_time", var1);
         var9.put("hit_url", var3);
         var9.put("hit_first_send_time", Integer.valueOf(0));

         try {
            var13.insert("gtm_hits", (String)null, var9);
            var4.zzbFw.zzar(false);
            return;
         } catch (SQLiteException var10) {
            zzdj.zzaT("Error storing hit");
         }
      }

   }

   private final List zzbD(int var1) {
      ArrayList var2 = new ArrayList();
      if (var1 <= 0) {
         zzdj.zzaT("Invalid maxHits specified. Skipping");
         return var2;
      } else {
         SQLiteDatabase var3;
         if ((var3 = this.zzfg("Error opening database for peekHitIds.")) == null) {
            return var2;
         } else {
            Cursor var4 = null;

            try {
               if ((var4 = var3.query("gtm_hits", new String[]{"hit_id"}, (String)null, (String[])null, (String)null, (String)null, String.format("%s ASC", "hit_id"), Integer.toString(var1))).moveToFirst()) {
                  do {
                     var2.add(String.valueOf(var4.getLong(0)));
                  } while(var4.moveToNext());
               }
            } catch (SQLiteException var9) {
               String var10001 = String.valueOf(var9.getMessage());
               String var10000;
               if (var10001.length() != 0) {
                  var10000 = "Error in peekHits fetching hitIds: ".concat(var10001);
               } else {
                  String var10002 = new String;
                  var10000 = var10002;
                  var10002.<init>("Error in peekHits fetching hitIds: ");
               }

               zzdj.zzaT(var10000);
            } finally {
               if (var4 != null) {
                  var4.close();
               }

            }

            return var2;
         }
      }
   }

   private final List zzbE(int var1) {
      ArrayList var2 = new ArrayList();
      SQLiteDatabase var3;
      if ((var3 = this.zzfg("Error opening database for peekHits")) == null) {
         return var2;
      } else {
         Cursor var4 = null;

         ArrayList var6;
         String var10000;
         String var10001;
         String var10002;
         label312: {
            try {
               var4 = var3.query("gtm_hits", new String[]{"hit_id", "hit_time", "hit_first_send_time"}, (String)null, (String[])null, (String)null, (String)null, String.format("%s ASC", "hit_id"), Integer.toString(40));
               var2 = new ArrayList();
               if (var4.moveToFirst()) {
                  do {
                     zzbx var5 = new zzbx(var4.getLong(0), var4.getLong(1), var4.getLong(2));
                     var2.add(var5);
                  } while(var4.moveToNext());
               }
               break label312;
            } catch (SQLiteException var25) {
               var10001 = String.valueOf(var25.getMessage());
               if (var10001.length() != 0) {
                  var10000 = "Error in peekHits fetching hitIds: ".concat(var10001);
               } else {
                  var10002 = new String;
                  var10000 = var10002;
                  var10002.<init>("Error in peekHits fetching hitIds: ");
               }

               zzdj.zzaT(var10000);
               var6 = var2;
            } finally {
               if (var4 != null) {
                  var4.close();
               }

            }

            return var6;
         }

         int var27 = 0;

         try {
            try {
               if ((var4 = var3.query("gtm_hits", new String[]{"hit_id", "hit_url"}, (String)null, (String[])null, (String)null, (String)null, String.format("%s ASC", "hit_id"), Integer.toString(40))).moveToFirst()) {
                  do {
                     if (((SQLiteCursor)var4).getWindow().getNumRows() > 0) {
                        ((zzbx)var2.get(var27)).zzfl(var4.getString(1));
                     } else {
                        zzdj.zzaT(String.format("HitString for hitId %d too large.  Hit will be deleted.", ((zzbx)var2.get(var27)).zzBm()));
                     }

                     ++var27;
                  } while(var4.moveToNext());
               }

               var6 = var2;
               return var6;
            } catch (SQLiteException var23) {
               var10001 = String.valueOf(var23.getMessage());
               if (var10001.length() != 0) {
                  var10000 = "Error in peekHits fetching hit url: ".concat(var10001);
               } else {
                  var10002 = new String;
                  var10000 = var10002;
                  var10002.<init>("Error in peekHits fetching hit url: ");
               }
            }

            zzdj.zzaT(var10000);
            ArrayList var7 = new ArrayList();
            boolean var8 = false;
            ArrayList var12;
            int var13 = (var12 = (ArrayList)var2).size();
            int var14 = 0;

            while(true) {
               zzbx var10;
               label334: {
                  if (var14 < var13) {
                     Object var28 = var12.get(var14);
                     ++var14;
                     if (!TextUtils.isEmpty((var10 = (zzbx)var28).zzBo())) {
                        break label334;
                     }

                     if (!var8) {
                        var8 = true;
                        break label334;
                     }
                  }

                  ArrayList var9 = var7;
                  return var9;
               }

               var7.add(var10);
            }
         } finally {
            if (var4 != null) {
               var4.close();
            }

         }
      }
   }

   private final void zzd(String[] var1) {
      if (var1 != null && var1.length != 0) {
         SQLiteDatabase var2;
         if ((var2 = this.zzfg("Error opening database for deleteHits.")) != null) {
            String var3 = String.format("HIT_ID in (%s)", TextUtils.join(",", Collections.nCopies(var1.length, "?")));

            try {
               var2.delete("gtm_hits", var3, var1);
               this.zzbFw.zzar(this.zzBA() == 0);
            } catch (SQLiteException var4) {
               zzdj.zzaT("Error deleting hits");
            }
         }
      }
   }

   private final void zzp(long var1) {
      this.zzd(new String[]{String.valueOf(var1)});
   }

   private final void zzh(long var1, long var3) {
      SQLiteDatabase var5;
      if ((var5 = this.zzfg("Error opening database for getNumStoredHits.")) != null) {
         ContentValues var6;
         (var6 = new ContentValues()).put("hit_first_send_time", var3);

         try {
            var5.update("gtm_hits", var6, "hit_id=?", new String[]{String.valueOf(var1)});
         } catch (SQLiteException var7) {
            zzdj.zzaT((new StringBuilder(69)).append("Error setting HIT_FIRST_DISPATCH_TIME for hitId: ").append(var1).toString());
            this.zzp(var1);
         }
      }
   }

   private final int zzBA() {
      int var1 = 0;
      SQLiteDatabase var2;
      if ((var2 = this.zzfg("Error opening database for getNumStoredHits.")) == null) {
         return 0;
      } else {
         Cursor var3 = null;

         try {
            if ((var3 = var2.rawQuery("SELECT COUNT(*) from gtm_hits", (String[])null)).moveToFirst()) {
               var1 = (int)var3.getLong(0);
            }
         } catch (SQLiteException var7) {
            zzdj.zzaT("Error getting numStoredHits");
         } finally {
            if (var3 != null) {
               var3.close();
            }

         }

         return var1;
      }
   }

   private final int zzBB() {
      int var1 = 0;
      SQLiteDatabase var2;
      if ((var2 = this.zzfg("Error opening database for getNumStoredHits.")) == null) {
         return 0;
      } else {
         Cursor var3 = null;

         try {
            var1 = (var3 = var2.query("gtm_hits", new String[]{"hit_id", "hit_first_send_time"}, "hit_first_send_time=0", (String[])null, (String)null, (String)null, (String)null)).getCount();
         } catch (SQLiteException var7) {
            zzdj.zzaT("Error getting num untried hits");
         } finally {
            if (var3 != null) {
               var3.close();
            }

         }

         return var1;
      }
   }

   public final void dispatch() {
      zzdj.v("GTM Dispatch running...");
      if (this.zzbFv.zzBf()) {
         List var1;
         if ((var1 = this.zzbE(40)).isEmpty()) {
            zzdj.v("...nothing to dispatch");
            this.zzbFw.zzar(true);
         } else {
            this.zzbFv.zzK(var1);
            if (this.zzBB() > 0) {
               zzfo.zzBV().dispatch();
            }

         }
      }
   }

   private final SQLiteDatabase zzfg(String var1) {
      try {
         SQLiteDatabase var2 = this.zzbFu.getWritableDatabase();
         return var2;
      } catch (SQLiteException var3) {
         zzdj.zzaT(var1);
         return null;
      }
   }

   // $FF: synthetic method
   static void zza(zzec var0, long var1) {
      var0.zzp(var1);
   }

   // $FF: synthetic method
   static com.google.android.gms.common.util.zze zza(zzec var0) {
      return var0.zzvw;
   }

   // $FF: synthetic method
   static void zza(zzec var0, long var1, long var3) {
      var0.zzh(var1, var3);
   }

   // $FF: synthetic method
   static String zzb(zzec var0) {
      return var0.zzbFx;
   }

   // $FF: synthetic method
   static Context zzc(zzec var0) {
      return var0.mContext;
   }

   // $FF: synthetic method
   static String zzBC() {
      return zzagp;
   }
}
