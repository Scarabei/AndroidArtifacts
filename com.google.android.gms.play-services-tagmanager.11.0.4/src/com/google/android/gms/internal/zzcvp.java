package com.google.android.gms.internal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Build.VERSION;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

final class zzcvp extends SQLiteOpenHelper {
   private boolean zzbFB;
   private long zzbFC;
   // $FF: synthetic field
   private zzcvn zzbIB;

   zzcvp(zzcvn var1, Context var2, String var3) {
      this.zzbIB = var1;
      super(var2, var3, (CursorFactory)null, 1);
      this.zzbFC = 0L;
   }

   private static boolean zza(String var0, SQLiteDatabase var1) {
      Cursor var2 = null;

      try {
         boolean var3 = (var2 = var1.query("SQLITE_MASTER", new String[]{"name"}, "name=?", new String[]{var0}, (String)null, (String)null, (String)null)).moveToFirst();
         return var3;
      } catch (SQLiteException var7) {
         ;
      } finally {
         if (var2 != null) {
            var2.close();
         }

      }

      return false;
   }

   public final SQLiteDatabase getWritableDatabase() {
      if (this.zzbFB && this.zzbFC + 3600000L > zzcvn.zza(this.zzbIB).currentTimeMillis()) {
         throw new SQLiteException("Database creation failed");
      } else {
         SQLiteDatabase var1 = null;
         this.zzbFB = true;
         this.zzbFC = zzcvn.zza(this.zzbIB).currentTimeMillis();

         try {
            var1 = super.getWritableDatabase();
         } catch (SQLiteException var2) {
            zzcvn.zzc(this.zzbIB).getDatabasePath(zzcvn.zzb(this.zzbIB)).delete();
         }

         if (var1 == null) {
            var1 = super.getWritableDatabase();
         }

         this.zzbFB = false;
         return var1;
      }
   }

   public final void onOpen(SQLiteDatabase var1) {
      if (VERSION.SDK_INT < 15) {
         Cursor var2 = var1.rawQuery("PRAGMA journal_mode=memory", (String[])null);

         try {
            var2.moveToFirst();
         } finally {
            var2.close();
         }
      }

      List var6;
      if (!zza("gtm_hit_unique_ids", var1)) {
         var1.execSQL(zzcvn.zzBC());
      } else {
         var6 = Arrays.asList("hit_unique_id");
         zza(var1, "gtm_hit_unique_ids", var6);
      }

      if (!zza("gtm_hits", var1)) {
         var1.execSQL(zzcvn.zzCs());
      } else {
         var6 = Arrays.asList("hit_id", "hit_url", "hit_time", "hit_first_send_time", "hit_method", "hit_unique_id", "hit_headers", "hit_body");
         zza(var1, "gtm_hits", var6);
      }

      var1.execSQL(zzcvn.zzCt());
      var1.execSQL(zzcvn.zzCu());
   }

   private static void zza(SQLiteDatabase var0, String var1, List var2) {
      Cursor var3 = var0.rawQuery((new StringBuilder(22 + String.valueOf(var1).length())).append("SELECT * FROM ").append(var1).append(" WHERE 0").toString(), (String[])null);
      HashSet var4 = new HashSet();

      try {
         String[] var5 = var3.getColumnNames();

         for(int var6 = 0; var6 < var5.length; ++var6) {
            var4.add(var5[var6]);
         }
      } finally {
         var3.close();
      }

      Iterator var10 = var2.iterator();

      String var11;
      do {
         if (!var10.hasNext()) {
            if (!var4.isEmpty()) {
               throw new SQLiteException(String.format("Database has extra columns in table %s.", var1));
            }

            return;
         }

         var11 = (String)var10.next();
      } while(var4.remove(var11));

      throw new SQLiteException(String.format("Database column %s missing in table %s.", var11, var1));
   }

   public final void onCreate(SQLiteDatabase var1) {
      String var2 = var1.getPath();
      if (zzcuv.version() >= 9) {
         File var3;
         (var3 = new File(var2)).setReadable(false, false);
         var3.setWritable(false, false);
         var3.setReadable(true, true);
         var3.setWritable(true, true);
      }

   }

   public final void onUpgrade(SQLiteDatabase var1, int var2, int var3) {
   }

   public final void onDowngrade(SQLiteDatabase var1, int var2, int var3) {
   }
}
