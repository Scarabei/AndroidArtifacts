package com.google.android.gms.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzg;
import com.google.android.gms.common.util.zzi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class zzcvn implements zzcvb {
   private static final String zzbIv = String.format("CREATE TABLE IF NOT EXISTS %s ('%s' TEXT UNIQUE);", "gtm_hit_unique_ids", "hit_unique_id");
   private static final String zzagp = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' TEXT UNIQUE, '%s' TEXT, '%s' TEXT);", "gtm_hits", "hit_id", "hit_time", "hit_url", "hit_first_send_time", "hit_method", "hit_unique_id", "hit_headers", "hit_body");
   private static final String zzbIw = String.format("CREATE TRIGGER IF NOT EXISTS %s DELETE ON %s FOR EACH ROW WHEN OLD.%s NOTNULL BEGIN     INSERT OR IGNORE INTO %s (%s) VALUES (OLD.%s); END;", "save_unique_on_delete", "gtm_hits", "hit_unique_id", "gtm_hit_unique_ids", "hit_unique_id", "hit_unique_id");
   private static final String zzbIx = String.format("CREATE TRIGGER IF NOT EXISTS %s BEFORE INSERT ON %s FOR EACH ROW WHEN NEW.%s NOT NULL BEGIN     SELECT RAISE(ABORT, 'Duplicate unique ID.')     WHERE EXISTS (SELECT 1 FROM %s WHERE %s = NEW.%s); END;", "check_unique_on_insert", "gtm_hits", "hit_unique_id", "gtm_hit_unique_ids", "hit_unique_id", "hit_unique_id");
   private final zzcvp zzbIy;
   private volatile zzcus zzbIz;
   private final zzcvc zzbIA;
   private final Context mContext;
   private final String zzbFx;
   private long zzbFy;
   private zze zzvw;
   private final int zzbFz;

   zzcvn(zzcvc var1, Context var2) {
      this(var1, var2, "gtm_urls.db", 2000);
   }

   private zzcvn(zzcvc var1, Context var2, String var3, int var4) {
      this.mContext = var2.getApplicationContext();
      this.zzbFx = var3;
      this.zzbIA = var1;
      this.zzvw = zzi.zzrY();
      this.zzbIy = new zzcvp(this, this.mContext, this.zzbFx);
      this.zzbIz = new zzcwj(this.mContext, new zzcvo(this));
      this.zzbFy = 0L;
      this.zzbFz = 2000;
   }

   public final void zza(long var1, String var3, @Nullable String var4, @Nullable String var5, @Nullable Map var6, @Nullable String var7) {
      long var9;
      if ((var9 = this.zzvw.currentTimeMillis()) > this.zzbFy + 86400000L) {
         this.zzbFy = var9;
         SQLiteDatabase var11;
         if ((var11 = this.zzfg("Error opening database for deleteStaleHits.")) != null) {
            long var12 = this.zzvw.currentTimeMillis() - 2592000000L;
            int var14 = var11.delete("gtm_hits", "HIT_TIME < ?", new String[]{Long.toString(var12)});
            zzcvk.v((new StringBuilder(31)).append("Removed ").append(var14).append(" stale hits.").toString());
            this.zzbIA.zzar(this.zzfF("gtm_hits") == 0);
         }
      }

      int var21;
      if ((var21 = this.zzfF("gtm_hits") - this.zzbFz + 1) > 0) {
         List var10;
         int var22 = (var10 = this.zzbD(var21)).size();
         zzcvk.v((new StringBuilder(51)).append("Store full, deleting ").append(var22).append(" hits to make room.").toString());
         this.zzd((String[])var10.toArray(new String[0]));
      }

      String var23 = var3;
      zzcvn var8 = this;
      SQLiteDatabase var16;
      if ((var16 = this.zzfg("Error opening database for putHit")) != null) {
         ContentValues var17;
         (var17 = new ContentValues()).put("hit_time", var1);
         var17.put("hit_url", var3);
         var17.put("hit_first_send_time", Integer.valueOf(0));
         var17.put("hit_method", var4 == null ? "GET" : var4);
         var17.put("hit_unique_id", var5);
         var17.put("hit_headers", var6 == null ? null : (new JSONObject(var6)).toString());
         var17.put("hit_body", var7);

         String var10000;
         String var10001;
         String var10002;
         try {
            var16.insertOrThrow("gtm_hits", (String)null, var17);
            zzcvk.v((new StringBuilder(19 + String.valueOf(var23).length())).append("Hit stored (url = ").append(var23).append(")").toString());
            var8.zzbIA.zzar(false);
         } catch (SQLiteConstraintException var19) {
            var10001 = String.valueOf(var3);
            if (var10001.length() != 0) {
               var10000 = "Hit has already been sent: ".concat(var10001);
            } else {
               var10002 = new String;
               var10000 = var10002;
               var10002.<init>("Hit has already been sent: ");
            }

            zzcvk.v(var10000);
         } catch (SQLiteException var20) {
            var10001 = String.valueOf(var20.getMessage());
            if (var10001.length() != 0) {
               var10000 = "Error storing hit: ".concat(var10001);
            } else {
               var10002 = new String;
               var10000 = var10002;
               var10002.<init>("Error storing hit: ");
            }

            zzcvk.zzaT(var10000);
         }
      }

      if (zzcvs.zzCw().isPreview()) {
         zzcvk.v("Sending hits immediately under preview.");
         this.dispatch();
      }

   }

   private final List zzbD(int var1) {
      ArrayList var2 = new ArrayList();
      if (var1 <= 0) {
         zzcvk.zzaT("Invalid maxHits specified. Skipping");
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

               zzcvk.zzaT(var10000);
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
         label372: {
            try {
               var4 = var3.query("gtm_hits", new String[]{"hit_id", "hit_time", "hit_first_send_time"}, (String)null, (String[])null, (String)null, (String)null, String.format("%s ASC", "hit_id"), Integer.toString(40));
               var2 = new ArrayList();
               if (var4.moveToFirst()) {
                  do {
                     zzcuw var5 = new zzcuw(var4.getLong(0), var4.getLong(1), var4.getLong(2));
                     var2.add(var5);
                  } while(var4.moveToNext());
               }
               break label372;
            } catch (SQLiteException var30) {
               var10001 = String.valueOf(var30.getMessage());
               if (var10001.length() != 0) {
                  var10000 = "Error in peekHits fetching hitIds: ".concat(var10001);
               } else {
                  var10002 = new String;
                  var10000 = var10002;
                  var10002.<init>("Error in peekHits fetching hitIds: ");
               }

               zzcvk.zzaT(var10000);
               var6 = var2;
            } finally {
               if (var4 != null) {
                  var4.close();
               }

            }

            return var6;
         }

         int var32 = 0;

         try {
            try {
               if ((var4 = var3.query("gtm_hits", new String[]{"hit_id", "hit_url", "hit_method", "hit_headers", "hit_body"}, (String)null, (String[])null, (String)null, (String)null, String.format("%s ASC", "hit_id"), Integer.toString(40))).moveToFirst()) {
                  do {
                     if (((SQLiteCursor)var4).getWindow().getNumRows() > 0) {
                        ((zzcuw)var2.get(var32)).zzfl(var4.getString(1));
                        ((zzcuw)var2.get(var32)).zzfD(var4.getString(2));
                        ((zzcuw)var2.get(var32)).zzfE(var4.getString(4));
                        HashMap var33 = null;

                        try {
                           String var34;
                           if ((var34 = var4.getString(3)) != null) {
                              JSONObject var35;
                              JSONArray var36 = (var35 = new JSONObject(var34)).names();
                              var33 = new HashMap();

                              for(int var11 = 0; var11 < var36.length(); ++var11) {
                                 String var12 = var36.getString(var11);
                                 var33.put(var12, (String)var35.opt(var12));
                              }
                           }
                        } catch (JSONException var27) {
                           zzcvk.zzaT(String.format("Failed to read headers for hitId %d: %s", ((zzcuw)var2.get(var32)).zzBm(), var27.getMessage()));
                           continue;
                        }

                        ((zzcuw)var2.get(var32)).zzu(var33);
                     } else {
                        zzcvk.zzaT(String.format("HitString for hitId %d too large. Hit will be deleted.", ((zzcuw)var2.get(var32)).zzBm()));
                     }

                     ++var32;
                  } while(var4.moveToNext());
               }

               var6 = var2;
               return var6;
            } catch (SQLiteException var28) {
               var10001 = String.valueOf(var28.getMessage());
               if (var10001.length() != 0) {
                  var10000 = "Error in peekHits fetching hit url: ".concat(var10001);
               } else {
                  var10002 = new String;
                  var10000 = var10002;
                  var10002.<init>("Error in peekHits fetching hit url: ");
               }
            }

            zzcvk.zzaT(var10000);
            ArrayList var7 = new ArrayList();
            boolean var8 = false;
            ArrayList var14;
            int var15 = (var14 = (ArrayList)var2).size();
            int var16 = 0;

            while(true) {
               zzcuw var10;
               label396: {
                  if (var16 < var15) {
                     Object var37 = var14.get(var16);
                     ++var16;
                     if (!TextUtils.isEmpty((var10 = (zzcuw)var37).zzBo())) {
                        break label396;
                     }

                     if (!var8) {
                        var8 = true;
                        break label396;
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
               this.zzbIA.zzar(this.zzfF("gtm_hits") == 0);
            } catch (SQLiteException var5) {
               String var10001 = String.valueOf(var5.getMessage());
               String var10000;
               if (var10001.length() != 0) {
                  var10000 = "Error deleting hits: ".concat(var10001);
               } else {
                  String var10002 = new String;
                  var10000 = var10002;
                  var10002.<init>("Error deleting hits: ");
               }

               zzcvk.zzaT(var10000);
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
         } catch (SQLiteException var8) {
            String var7 = String.valueOf(var8.getMessage());
            zzcvk.zzaT((new StringBuilder(70 + String.valueOf(var7).length())).append("Error setting HIT_FIRST_DISPATCH_TIME for hitId ").append(var1).append(": ").append(var7).toString());
            this.zzp(var1);
         }
      }
   }

   private final int zzfF(String var1) {
      int var2 = 0;
      SQLiteDatabase var3;
      if ((var3 = this.zzfg("Error opening database for getNumRecords.")) == null) {
         return 0;
      } else {
         Cursor var4 = null;

         try {
            String var10001;
            String var10002;
            try {
               var10002 = String.valueOf(var1);
               if (var10002.length() != 0) {
                  var10001 = "SELECT COUNT(*) from ".concat(var10002);
               } else {
                  String var10003 = new String;
                  var10001 = var10003;
                  var10003.<init>("SELECT COUNT(*) from ");
               }

               if ((var4 = var3.rawQuery(var10001, (String[])null)).moveToFirst()) {
                  var2 = (int)var4.getLong(0);
               }
            } catch (SQLiteException var9) {
               var10001 = String.valueOf(var9.getMessage());
               String var10000;
               if (var10001.length() != 0) {
                  var10000 = "Error getting numStoredRecords: ".concat(var10001);
               } else {
                  var10002 = new String;
                  var10000 = var10002;
                  var10002.<init>("Error getting numStoredRecords: ");
               }

               zzcvk.zzaT(var10000);
            }
         } finally {
            if (var4 != null) {
               var4.close();
            }

         }

         return var2;
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
         } catch (SQLiteException var8) {
            String var10001 = String.valueOf(var8.getMessage());
            String var10000;
            if (var10001.length() != 0) {
               var10000 = "Error getting num untried hits: ".concat(var10001);
            } else {
               String var10002 = new String;
               var10000 = var10002;
               var10002.<init>("Error getting num untried hits: ");
            }

            zzcvk.zzaT(var10000);
         } finally {
            if (var3 != null) {
               var3.close();
            }

         }

         return var1;
      }
   }

   public final void dispatch() {
      zzcvk.v("GTM Dispatch running...");
      if (this.zzbIz.zzBf()) {
         List var1;
         if ((var1 = this.zzbE(40)).isEmpty()) {
            zzcvk.v("...nothing to dispatch");
            this.zzbIA.zzar(true);
         } else {
            this.zzbIz.zzK(var1);
            if (this.zzBB() > 0) {
               zzcwd.zzCA().dispatch();
            }

         }
      }
   }

   private final SQLiteDatabase zzfg(String var1) {
      try {
         SQLiteDatabase var2 = this.zzbIy.getWritableDatabase();
         return var2;
      } catch (SQLiteException var6) {
         Context var5 = this.mContext;
         zzcvk.zzc(var1, var6);
         if (zzg.zza(var5, var6)) {
            zzcvk.v("Crash reported successfully.");
         } else {
            zzcvk.v("Failed to report crash");
         }

         return null;
      }
   }

   // $FF: synthetic method
   static void zza(zzcvn var0, long var1) {
      var0.zzp(var1);
   }

   // $FF: synthetic method
   static zze zza(zzcvn var0) {
      return var0.zzvw;
   }

   // $FF: synthetic method
   static void zza(zzcvn var0, long var1, long var3) {
      var0.zzh(var1, var3);
   }

   // $FF: synthetic method
   static String zzb(zzcvn var0) {
      return var0.zzbFx;
   }

   // $FF: synthetic method
   static Context zzc(zzcvn var0) {
      return var0.mContext;
   }

   // $FF: synthetic method
   static String zzBC() {
      return zzbIv;
   }

   // $FF: synthetic method
   static String zzCs() {
      return zzagp;
   }

   // $FF: synthetic method
   static String zzCt() {
      return zzbIw;
   }

   // $FF: synthetic method
   static String zzCu() {
      return zzbIx;
   }
}
