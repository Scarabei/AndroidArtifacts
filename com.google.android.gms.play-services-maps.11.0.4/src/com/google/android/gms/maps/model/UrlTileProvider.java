package com.google.android.gms.maps.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public abstract class UrlTileProvider implements TileProvider {
   private final int zzrW;
   private final int zzrX;

   public UrlTileProvider(int var1, int var2) {
      this.zzrW = var1;
      this.zzrX = var2;
   }

   public abstract URL getTileUrl(int var1, int var2, int var3);

   public final Tile getTile(int var1, int var2, int var3) {
      URL var4;
      if ((var4 = this.getTileUrl(var1, var2, var3)) == null) {
         return NO_TILE;
      } else {
         Tile var5;
         try {
            int var10002 = this.zzrW;
            int var10003 = this.zzrX;
            InputStream var6 = var4.openStream();
            ByteArrayOutputStream var7 = new ByteArrayOutputStream();
            zza(var6, var7);
            var5 = new Tile(var10002, var10003, var7.toByteArray());
         } catch (IOException var8) {
            var5 = null;
         }

         return var5;
      }
   }

   private static long zza(InputStream var0, OutputStream var1) throws IOException {
      byte[] var2 = new byte[4096];

      long var3;
      int var5;
      for(var3 = 0L; (var5 = var0.read(var2)) != -1; var3 += (long)var5) {
         var1.write(var2, 0, var5);
      }

      return var3;
   }
}
