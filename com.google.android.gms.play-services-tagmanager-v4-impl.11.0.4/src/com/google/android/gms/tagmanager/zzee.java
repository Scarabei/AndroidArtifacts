package com.google.android.gms.tagmanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Build.VERSION;
import java.util.HashSet;

final class zzee extends SQLiteOpenHelper {
   private boolean zzbFB;
   private long zzbFC;
   // $FF: synthetic field
   private zzec zzbFA;

   zzee(zzec var1, Context var2, String var3) {
      this.zzbFA = var1;
      super(var2, var3, (CursorFactory)null, 1);
      this.zzbFC = 0L;
   }

   private static boolean zza(String var0, SQLiteDatabase var1) {
      Cursor var2 = null;

      try {
         boolean var3 = (var2 = var1.query("SQLITE_MASTER", new String[]{"name"}, "name=?", new String[]{var0}, (String)null, (String)null, (String)null)).moveToFirst();
         return var3;
      } catch (SQLiteException var7) {
         String var10001 = String.valueOf(var0);
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "Error querying for table ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Error querying for table ");
         }

         zzdj.zzaT(var10000);
      } finally {
         if (var2 != null) {
            var2.close();
         }

      }

      return false;
   }

   public final SQLiteDatabase getWritableDatabase() {
      if (this.zzbFB && this.zzbFC + 3600000L > zzec.zza(this.zzbFA).currentTimeMillis()) {
         throw new SQLiteException("Database creation failed");
      } else {
         SQLiteDatabase var1 = null;
         this.zzbFB = true;
         this.zzbFC = zzec.zza(this.zzbFA).currentTimeMillis();

         try {
            var1 = super.getWritableDatabase();
         } catch (SQLiteException var2) {
            zzec.zzc(this.zzbFA).getDatabasePath(zzec.zzb(this.zzbFA)).delete();
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

      if (!zza("gtm_hits", var1)) {
         var1.execSQL(zzec.zzBC());
      } else {
         Cursor var4 = var1.rawQuery("SELECT * FROM gtm_hits WHERE 0", (String[])null);
         HashSet var5 = new HashSet();

         try {
            String[] var6 = var4.getColumnNames();

            for(int var7 = 0; var7 < var6.length; ++var7) {
               var5.add(var6[var7]);
            }
         } finally {
            var4.close();
         }

         if (var5.remove("hit_id") && var5.remove("hit_url") && var5.remove("hit_time") && var5.remove("hit_first_send_time")) {
            if (!var5.isEmpty()) {
               throw new SQLiteException("Database has extra columns");
            }
         } else {
            throw new SQLiteException("Database column missing");
         }
      }
   }

   public final void onCreate(SQLiteDatabase var1) {
      zzbs.zzfk(var1.getPath());
   }

   public final void onUpgrade(SQLiteDatabase var1, int var2, int var3) {
   }

   public final void onDowngrade(SQLiteDatabase var1, int var2, int var3) {
   }
}
