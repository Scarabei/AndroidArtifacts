package com.google.android.gms.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.google.android.gms.analytics.zzl;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.util.zzm;
import java.io.Closeable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class zzams extends zzamh implements Closeable {
   private static final String zzagp = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' TEXT NOT NULL, '%s' INTEGER);", "hits2", "hit_id", "hit_time", "hit_url", "hit_string", "hit_app_id");
   private static final String zzagq = String.format("SELECT MAX(%s) FROM %s WHERE 1;", "hit_time", "hits2");
   private final zzamt zzagr;
   private final zzaoo zzags = new zzaoo(this.zzkq());
   private final zzaoo zzagt = new zzaoo(this.zzkq());

   zzams(zzamj var1) {
      super(var1);
      String var2 = "google_analytics_v4.db";
      this.zzagr = new zzamt(this, var1.getContext(), var2);
   }

   protected final void zzjD() {
   }

   public final void beginTransaction() {
      this.zzkD();
      this.getWritableDatabase().beginTransaction();
   }

   public final void setTransactionSuccessful() {
      this.zzkD();
      this.getWritableDatabase().setTransactionSuccessful();
   }

   public final void endTransaction() {
      this.zzkD();
      this.getWritableDatabase().endTransaction();
   }

   public final void zzc(zzanx var1) {
      zzbo.zzu(var1);
      zzl.zzjC();
      this.zzkD();
      zzbo.zzu(var1);
      Builder var8 = new Builder();
      Iterator var9 = var1.zzdV().entrySet().iterator();

      while(var9.hasNext()) {
         Entry var10;
         String var11 = (String)(var10 = (Entry)var9.next()).getKey();
         if (!"ht".equals(var11) && !"qt".equals(var11) && !"AppUID".equals(var11)) {
            var8.appendQueryParameter(var11, (String)var10.getValue());
         }
      }

      String var17;
      String var10000 = (var17 = var8.build().getEncodedQuery()) == null ? "" : var17;
      String var2 = var10000;
      if (var10000.length() > 8192) {
         this.zzkr().zza(var1, "Hit length exceeds the maximum allowed size");
      } else {
         int var15 = ((Integer)zzans.zzahj.get()).intValue();
         long var16;
         if ((var16 = this.zzkN()) > (long)(var15 - 1)) {
            long var18 = var16 - (long)var15 + 1L;
            List var13 = this.zzn(var18);
            this.zzd("Store full, deleting hits to make room, count", var13.size());
            this.zzs(var13);
         }

         SQLiteDatabase var3 = this.getWritableDatabase();
         ContentValues var4;
         (var4 = new ContentValues()).put("hit_string", var2);
         var4.put("hit_time", var1.zzlG());
         var4.put("hit_app_id", var1.zzlE());
         var4.put("hit_url", var1.zzlI() ? zzank.zzlu() : zzank.zzlv());

         try {
            long var5;
            if ((var5 = var3.insert("hits2", (String)null, var4)) == -1L) {
               this.zzbs("Failed to insert a hit (got -1)");
            } else {
               this.zzb("Hit saved to database. db-id, hit", var5, var1);
            }
         } catch (SQLiteException var14) {
            this.zze("Error storing a hit", var14);
         }
      }
   }

   private final long zzkN() {
      zzl.zzjC();
      this.zzkD();
      return this.zzb("SELECT COUNT(*) FROM hits2", (String[])null);
   }

   final boolean isEmpty() {
      return this.zzkN() == 0L;
   }

   private final List zzn(long var1) {
      zzl.zzjC();
      this.zzkD();
      if (var1 <= 0L) {
         return Collections.emptyList();
      } else {
         SQLiteDatabase var3 = this.getWritableDatabase();
         ArrayList var4 = new ArrayList();
         Cursor var5 = null;

         try {
            if ((var5 = var3.query("hits2", new String[]{"hit_id"}, (String)null, (String[])null, (String)null, (String)null, String.format("%s ASC", "hit_id"), Long.toString(var1))).moveToFirst()) {
               do {
                  var4.add(var5.getLong(0));
               } while(var5.moveToNext());
            }
         } catch (SQLiteException var10) {
            this.zzd("Error selecting hit ids", var10);
         } finally {
            if (var5 != null) {
               var5.close();
            }

         }

         return var4;
      }
   }

   public final List zzo(long var1) {
      zzbo.zzaf(var1 >= 0L);
      zzl.zzjC();
      this.zzkD();
      SQLiteDatabase var3 = this.getWritableDatabase();
      Cursor var4 = null;

      ArrayList var20;
      try {
         var4 = var3.query("hits2", new String[]{"hit_id", "hit_time", "hit_string", "hit_url", "hit_app_id"}, (String)null, (String[])null, (String)null, (String)null, String.format("%s ASC", "hit_id"), Long.toString(var1));
         ArrayList var5 = new ArrayList();
         if (var4.moveToFirst()) {
            do {
               long var6 = var4.getLong(0);
               long var8 = var4.getLong(1);
               String var10 = var4.getString(2);
               String var11 = var4.getString(3);
               int var12 = var4.getInt(4);
               Map var13 = this.zzbt(var10);
               boolean var14 = zzaos.zzbF(var11);
               var5.add(new zzanx(this, var13, var8, var14, var6, var12));
            } while(var4.moveToNext());
         }

         var20 = var5;
      } catch (SQLiteException var18) {
         this.zze("Error loading hits from the database", var18);
         throw var18;
      } finally {
         if (var4 != null) {
            var4.close();
         }

      }

      return var20;
   }

   public final void zzs(List var1) {
      zzbo.zzu(var1);
      zzl.zzjC();
      this.zzkD();
      if (!var1.isEmpty()) {
         StringBuilder var2;
         (var2 = new StringBuilder("hit_id")).append(" in (");

         for(int var3 = 0; var3 < var1.size(); ++var3) {
            Long var4;
            if ((var4 = (Long)var1.get(var3)) == null || var4.longValue() == 0L) {
               throw new SQLiteException("Invalid hit id");
            }

            if (var3 > 0) {
               var2.append(",");
            }

            var2.append(var4);
         }

         var2.append(")");
         String var7 = var2.toString();

         try {
            SQLiteDatabase var8 = this.getWritableDatabase();
            this.zza("Deleting dispatched hits. count", var1.size());
            int var5;
            if ((var5 = var8.delete("hits2", var7, (String[])null)) != var1.size()) {
               this.zzb("Deleted fewer hits then expected", var1.size(), var5, var7);
            }

         } catch (SQLiteException var6) {
            this.zze("Error deleting hits", var6);
            throw var6;
         }
      }
   }

   public final void zzp(long var1) {
      zzl.zzjC();
      this.zzkD();
      ArrayList var3;
      (var3 = new ArrayList(1)).add(var1);
      this.zza("Deleting hit, id", var1);
      this.zzs(var3);
   }

   public final int zzkS() {
      zzl.zzjC();
      this.zzkD();
      if (!this.zzags.zzu(86400000L)) {
         return 0;
      } else {
         this.zzags.start();
         this.zzbo("Deleting stale hits (if any)");
         SQLiteDatabase var1 = this.getWritableDatabase();
         long var2 = this.zzkq().currentTimeMillis() - 2592000000L;
         int var4 = var1.delete("hits2", "hit_time < ?", new String[]{Long.toString(var2)});
         this.zza("Deleted stale hits, count", var4);
         return var4;
      }
   }

   public final long zzkT() {
      zzl.zzjC();
      this.zzkD();
      return this.zza(zzagq, (String[])null, 0L);
   }

   public final long zza(long var1, String var3, String var4) {
      zzbo.zzcF(var3);
      zzbo.zzcF(var4);
      this.zzkD();
      zzl.zzjC();
      return this.zza("SELECT hits_count FROM properties WHERE app_uid=? AND cid=? AND tid=?", new String[]{String.valueOf(var1), var3, var4}, 0L);
   }

   public final List zzq(long var1) {
      this.zzkD();
      zzl.zzjC();
      SQLiteDatabase var3 = this.getWritableDatabase();
      Cursor var4 = null;

      ArrayList var24;
      try {
         String[] var5 = new String[]{"cid", "tid", "adid", "hits_count", "params"};
         int var6;
         String var7 = String.valueOf(var6 = ((Integer)zzans.zzahl.get()).intValue());
         String var8 = "app_uid=?";
         String[] var9 = new String[]{"0"};
         var4 = var3.query("properties", var5, var8, var9, (String)null, (String)null, (String)null, var7);
         ArrayList var10 = new ArrayList();
         if (var4.moveToFirst()) {
            do {
               String var11 = var4.getString(0);
               String var12 = var4.getString(1);
               boolean var13 = var4.getInt(2) != 0;
               long var14 = (long)var4.getInt(3);
               String var16 = var4.getString(4);
               Map var17 = this.zzbu(var16);
               if (!TextUtils.isEmpty(var11) && !TextUtils.isEmpty(var12)) {
                  zzamm var18 = new zzamm(0L, var11, var12, var13, var14, var17);
                  var10.add(var18);
               } else {
                  this.zzc("Read property with empty client id or tracker id", var11, var12);
               }
            } while(var4.moveToNext());
         }

         if (var10.size() >= var6) {
            this.zzbr("Sending hits to too many properties. Campaign report might be incorrect");
         }

         var24 = var10;
      } catch (SQLiteException var22) {
         this.zze("Error loading hits from the database", var22);
         throw var22;
      } finally {
         if (var4 != null) {
            var4.close();
         }

      }

      return var24;
   }

   public final void close() {
      try {
         this.zzagr.close();
      } catch (SQLiteException var2) {
         this.zze("Sql error closing database", var2);
      } catch (IllegalStateException var3) {
         this.zze("Error closing database", var3);
      }
   }

   private final long zzb(String var1, String[] var2) {
      SQLiteDatabase var3 = this.getWritableDatabase();
      Cursor var4 = null;

      long var5;
      try {
         if (!(var4 = var3.rawQuery(var1, (String[])null)).moveToFirst()) {
            throw new SQLiteException("Database returned empty set");
         }

         var5 = var4.getLong(0);
      } catch (SQLiteException var10) {
         this.zzd("Database error", var1, var10);
         throw var10;
      } finally {
         if (var4 != null) {
            var4.close();
         }

      }

      return var5;
   }

   private final long zza(String var1, String[] var2, long var3) {
      SQLiteDatabase var5 = this.getWritableDatabase();
      Cursor var6 = null;

      long var7;
      try {
         if (!(var6 = var5.rawQuery(var1, var2)).moveToFirst()) {
            return 0L;
         }

         var7 = var6.getLong(0);
      } catch (SQLiteException var12) {
         this.zzd("Database error", var1, var12);
         throw var12;
      } finally {
         if (var6 != null) {
            var6.close();
         }

      }

      return var7;
   }

   private final Map zzbt(String var1) {
      if (TextUtils.isEmpty(var1)) {
         return new HashMap(0);
      } else {
         try {
            String var10000;
            if (var1.startsWith("?")) {
               var10000 = var1;
            } else {
               String var10001 = String.valueOf(var1);
               if (var10001.length() != 0) {
                  var10000 = "?".concat(var10001);
               } else {
                  String var10002 = new String;
                  var10000 = var10002;
                  var10002.<init>("?");
               }
            }

            String var2 = var10000;
            return zzm.zza(new URI(var2), "UTF-8");
         } catch (URISyntaxException var3) {
            this.zze("Error parsing hit parameters", var3);
            return new HashMap(0);
         }
      }
   }

   private final Map zzbu(String var1) {
      if (TextUtils.isEmpty(var1)) {
         return new HashMap(0);
      } else {
         try {
            String var10001 = String.valueOf(var1);
            String var10000;
            if (var10001.length() != 0) {
               var10000 = "?".concat(var10001);
            } else {
               String var10002 = new String;
               var10000 = var10002;
               var10002.<init>("?");
            }

            String var2 = var10000;
            return zzm.zza(new URI(var2), "UTF-8");
         } catch (URISyntaxException var3) {
            this.zze("Error parsing property parameters", var3);
            return new HashMap(0);
         }
      }
   }

   final SQLiteDatabase getWritableDatabase() {
      try {
         return this.zzagr.getWritableDatabase();
      } catch (SQLiteException var2) {
         this.zzd("Error opening database", var2);
         throw var2;
      }
   }

   private static String zzkU() {
      return "google_analytics_v4.db";
   }

   // $FF: synthetic method
   static zzaoo zza(zzams var0) {
      return var0.zzagt;
   }

   // $FF: synthetic method
   static String zzb(zzams var0) {
      return zzkU();
   }

   // $FF: synthetic method
   static String zzkV() {
      return zzagp;
   }
}
