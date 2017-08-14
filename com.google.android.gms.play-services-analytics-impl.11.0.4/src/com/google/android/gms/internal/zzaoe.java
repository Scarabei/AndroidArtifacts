package com.google.android.gms.internal;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import com.google.android.gms.analytics.zzl;
import com.google.android.gms.common.internal.zzbo;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.zip.GZIPOutputStream;

final class zzaoe extends zzamh {
   private final String zzJP;
   private final zzaoo zzais;
   private static final byte[] zzait = "\n".getBytes();

   zzaoe(zzamj var1) {
      super(var1);
      String var10002 = zzami.VERSION;
      String var10003 = VERSION.RELEASE;
      String var10004 = zzaos.zza(Locale.getDefault());
      String var7 = Build.ID;
      String var6 = Build.MODEL;
      String var5 = var10004;
      String var4 = var10003;
      String var3 = var10002;
      String var2 = "GoogleAnalytics";
      this.zzJP = String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", var2, var3, var4, var5, var6, var7);
      this.zzais = new zzaoo(var1.zzkq());
   }

   protected final void zzjD() {
      this.zza("Network initialized. User agent", this.zzJP);
   }

   public final boolean zzlQ() {
      zzl.zzjC();
      this.zzkD();
      ConnectivityManager var1 = (ConnectivityManager)this.getContext().getSystemService("connectivity");
      NetworkInfo var2 = null;

      try {
         var2 = var1.getActiveNetworkInfo();
      } catch (SecurityException var3) {
         ;
      }

      if (var2 != null && var2.isConnected()) {
         return true;
      } else {
         this.zzbo("No network connectivity");
         return false;
      }
   }

   public final List zzu(List var1) {
      boolean var10000;
      boolean var2;
      label74: {
         zzl.zzjC();
         this.zzkD();
         zzbo.zzu(var1);
         if (!this.zzks().zzlx().isEmpty() && this.zzais.zzu((long)((Integer)zzans.zzahG.get()).intValue() * 1000L)) {
            var2 = zzana.zzbx((String)zzans.zzahz.get()) != zzana.zzagG;
            if (zzang.zzby((String)zzans.zzahA.get()) == zzang.zzagR) {
               var10000 = true;
               break label74;
            }
         } else {
            var2 = false;
         }

         var10000 = false;
      }

      boolean var3 = var10000;
      if (!var2) {
         return this.zzv(var1);
      } else {
         zzbo.zzaf(!var1.isEmpty());
         this.zza("Uploading batched hits. compression, count", var3, var1.size());
         zzaof var7 = new zzaof(this);
         ArrayList var8 = new ArrayList();
         Iterator var9 = var1.iterator();

         while(var9.hasNext()) {
            zzanx var10 = (zzanx)var9.next();
            if (!var7.zze(var10)) {
               break;
            }

            var8.add(var10.zzlF());
         }

         if (var7.zzlT() == 0) {
            return var8;
         } else {
            URL var11;
            if ((var11 = this.zzlR()) == null) {
               this.zzbs("Failed to build batching endpoint url");
            } else {
               int var12;
               if (var3) {
                  var12 = this.zzb(var11, var7.getPayload());
               } else {
                  var12 = this.zza(var11, var7.getPayload());
               }

               if (200 == var12) {
                  this.zza("Batched upload completed. Hits batched", var7.zzlT());
                  return var8;
               }

               this.zza("Network error uploading hits. status code", var12);
               if (this.zzks().zzlx().contains(var12)) {
                  this.zzbr("Server instructed the client to stop batching");
                  this.zzais.start();
               }
            }

            return Collections.emptyList();
         }
      }
   }

   private final List zzv(List var1) {
      ArrayList var2 = new ArrayList(var1.size());
      Iterator var3 = var1.iterator();

      while(var3.hasNext()) {
         zzanx var4 = (zzanx)var3.next();
         zzbo.zzu(var4);
         boolean var10000;
         String var7;
         if ((var7 = this.zza(var4, !var4.zzlI())) == null) {
            this.zzkr().zza(var4, "Error formatting hit for upload");
            var10000 = true;
         } else {
            label47: {
               if (var7.length() <= ((Integer)zzans.zzahy.get()).intValue()) {
                  URL var8;
                  if ((var8 = this.zzb(var4, var7)) != null) {
                     var10000 = this.zza(var8) == 200;
                     break label47;
                  }

                  this.zzbs("Failed to build collect GET endpoint url");
               } else {
                  if ((var7 = this.zza(var4, false)) == null) {
                     this.zzkr().zza(var4, "Error formatting hit for POST upload");
                     var10000 = true;
                     break label47;
                  }

                  byte[] var10;
                  if ((var10 = var7.getBytes()).length > ((Integer)zzans.zzahD.get()).intValue()) {
                     this.zzkr().zza(var4, "Hit payload exceeds size limit");
                     var10000 = true;
                     break label47;
                  }

                  URL var9;
                  if ((var9 = this.zzd(var4)) == null) {
                     this.zzbs("Failed to build collect POST endpoint url");
                  } else if (this.zza(var9, var10) == 200) {
                     var10000 = true;
                     break label47;
                  }
               }

               var10000 = false;
            }
         }

         if (!var10000) {
            break;
         }

         var2.add(var4.zzlF());
         if (var2.size() >= zzank.zzls()) {
            break;
         }
      }

      return var2;
   }

   private final int zza(URL var1) {
      zzbo.zzu(var1);
      this.zzb((String)"GET request", (Object)var1);
      HttpURLConnection var2 = null;

      try {
         (var2 = this.zzb(var1)).connect();
         this.zzb(var2);
         int var3;
         if ((var3 = var2.getResponseCode()) == 200) {
            this.zzkv().zzko();
         }

         this.zzb((String)"GET status", (Object)var3);
         return var3;
      } catch (IOException var7) {
         this.zzd("Network GET connection error", var7);
      } finally {
         if (var2 != null) {
            var2.disconnect();
         }

      }

      return 0;
   }

   private final int zza(URL var1, byte[] var2) {
      zzbo.zzu(var1);
      zzbo.zzu(var2);
      this.zzb("POST bytes, url", var2.length, var1);
      if (zzhM()) {
         this.zza("Post payload\n", new String(var2));
      }

      HttpURLConnection var3 = null;
      OutputStream var4 = null;

      try {
         this.getContext().getPackageName();
         (var3 = this.zzb(var1)).setDoOutput(true);
         var3.setFixedLengthStreamingMode(var2.length);
         var3.connect();
         (var4 = var3.getOutputStream()).write(var2);
         this.zzb(var3);
         int var5;
         if ((var5 = var3.getResponseCode()) == 200) {
            this.zzkv().zzko();
         }

         this.zzb((String)"POST status", (Object)var5);
         return var5;
      } catch (IOException var15) {
         this.zzd("Network POST connection error", var15);
      } finally {
         if (var4 != null) {
            try {
               var4.close();
            } catch (IOException var14) {
               this.zze("Error closing http post connection output stream", var14);
            }
         }

         if (var3 != null) {
            var3.disconnect();
         }

      }

      return 0;
   }

   private final int zzb(URL var1, byte[] var2) {
      zzbo.zzu(var1);
      zzbo.zzu(var2);
      HttpURLConnection var3 = null;
      OutputStream var4 = null;
      boolean var17 = false;

      int var6;
      label138: {
         try {
            var17 = true;
            this.getContext().getPackageName();
            ByteArrayOutputStream var11 = new ByteArrayOutputStream();
            GZIPOutputStream var12;
            (var12 = new GZIPOutputStream(var11)).write(var2);
            var12.close();
            var11.close();
            byte[] var5 = var11.toByteArray();
            this.zza("POST compressed size, ratio %, url", var5.length, 100L * (long)var5.length / (long)var2.length, var1);
            if (var5.length > var2.length) {
               this.zzc("Compressed payload is larger then uncompressed. compressed, uncompressed", var5.length, var2.length);
            }

            if (zzhM()) {
               String var10003 = String.valueOf(new String(var2));
               String var10002;
               if (var10003.length() != 0) {
                  var10002 = "\n".concat(var10003);
               } else {
                  String var10004 = new String;
                  var10002 = var10004;
                  var10004.<init>("\n");
               }

               this.zza("Post payload", var10002);
            }

            (var3 = this.zzb(var1)).setDoOutput(true);
            var3.addRequestProperty("Content-Encoding", "gzip");
            var3.setFixedLengthStreamingMode(var5.length);
            var3.connect();
            (var4 = var3.getOutputStream()).write(var5);
            var4.close();
            var4 = null;
            this.zzb(var3);
            if ((var6 = var3.getResponseCode()) == 200) {
               this.zzkv().zzko();
            }

            this.zzb((String)"POST status", (Object)var6);
            var17 = false;
            break label138;
         } catch (IOException var20) {
            this.zzd("Network compressed POST connection error", var20);
            var17 = false;
         } finally {
            if (var17) {
               if (var4 != null) {
                  try {
                     var4.close();
                  } catch (IOException var19) {
                     this.zze("Error closing http compressed post connection output stream", var19);
                  }
               }

               if (var3 != null) {
                  var3.disconnect();
               }

            }
         }

         if (var4 != null) {
            try {
               var4.close();
            } catch (IOException var18) {
               this.zze("Error closing http compressed post connection output stream", var18);
            }
         }

         if (var3 != null) {
            var3.disconnect();
         }

         return 0;
      }

      if (var3 != null) {
         var3.disconnect();
      }

      return var6;
   }

   private final void zzb(HttpURLConnection var1) throws IOException {
      InputStream var2 = null;

      try {
         var2 = var1.getInputStream();
         byte[] var3 = new byte[1024];

         while(var2.read(var3) > 0) {
            ;
         }
      } finally {
         if (var2 != null) {
            try {
               var2.close();
            } catch (IOException var9) {
               this.zze("Error closing http connection input stream", var9);
            }
         }

      }

   }

   private final HttpURLConnection zzb(URL var1) throws IOException {
      URLConnection var2;
      if (!((var2 = var1.openConnection()) instanceof HttpURLConnection)) {
         throw new IOException("Failed to obtain http connection");
      } else {
         HttpURLConnection var3;
         (var3 = (HttpURLConnection)var2).setDefaultUseCaches(false);
         var3.setConnectTimeout(((Integer)zzans.zzahI.get()).intValue());
         var3.setReadTimeout(((Integer)zzans.zzahJ.get()).intValue());
         var3.setInstanceFollowRedirects(false);
         var3.setRequestProperty("User-Agent", this.zzJP);
         var3.setDoInput(true);
         return var3;
      }
   }

   private final URL zzd(zzanx var1) {
      String var10000;
      String var10001;
      String var10002;
      String var2;
      if (var1.zzlI()) {
         var10000 = String.valueOf(zzank.zzlu());
         var10001 = String.valueOf(zzank.zzlw());
         if (var10001.length() != 0) {
            var10000 = var10000.concat(var10001);
         } else {
            var10002 = new String;
            var10001 = var10000;
            var10000 = var10002;
            var10002.<init>(var10001);
         }

         var2 = var10000;
      } else {
         var10000 = String.valueOf(zzank.zzlv());
         var10001 = String.valueOf(zzank.zzlw());
         if (var10001.length() != 0) {
            var10000 = var10000.concat(var10001);
         } else {
            var10002 = new String;
            var10001 = var10000;
            var10000 = var10002;
            var10002.<init>(var10001);
         }

         var2 = var10000;
      }

      try {
         return new URL(var2);
      } catch (MalformedURLException var4) {
         this.zze("Error trying to parse the hardcoded host url", var4);
         return null;
      }
   }

   private final URL zzb(zzanx var1, String var2) {
      String var3;
      String var4;
      String var5;
      if (var1.zzlI()) {
         var4 = String.valueOf(zzank.zzlu());
         var5 = String.valueOf(zzank.zzlw());
         var3 = (new StringBuilder(1 + String.valueOf(var4).length() + String.valueOf(var5).length() + String.valueOf(var2).length())).append(var4).append(var5).append("?").append(var2).toString();
      } else {
         var4 = String.valueOf(zzank.zzlv());
         var5 = String.valueOf(zzank.zzlw());
         var3 = (new StringBuilder(1 + String.valueOf(var4).length() + String.valueOf(var5).length() + String.valueOf(var2).length())).append(var4).append(var5).append("?").append(var2).toString();
      }

      try {
         return new URL(var3);
      } catch (MalformedURLException var6) {
         this.zze("Error trying to parse the hardcoded host url", var6);
         return null;
      }
   }

   private final URL zzlR() {
      String var10000 = String.valueOf(zzank.zzlu());
      String var10001 = String.valueOf((String)zzans.zzahx.get());
      if (var10001.length() != 0) {
         var10000 = var10000.concat(var10001);
      } else {
         String var10002 = new String;
         var10001 = var10000;
         var10000 = var10002;
         var10002.<init>(var10001);
      }

      String var1 = var10000;

      try {
         return new URL(var1);
      } catch (MalformedURLException var3) {
         this.zze("Error trying to parse the hardcoded host url", var3);
         return null;
      }
   }

   final String zza(zzanx var1, boolean var2) {
      zzbo.zzu(var1);
      StringBuilder var3 = new StringBuilder();

      try {
         Iterator var4 = var1.zzdV().entrySet().iterator();

         while(var4.hasNext()) {
            Entry var5;
            String var6 = (String)(var5 = (Entry)var4.next()).getKey();
            if (!"ht".equals(var6) && !"qt".equals(var6) && !"AppUID".equals(var6) && !"z".equals(var6) && !"_gmsv".equals(var6)) {
               zza(var3, var6, (String)var5.getValue());
            }
         }

         zza(var3, "ht", String.valueOf(var1.zzlG()));
         long var10 = this.zzkq().currentTimeMillis() - var1.zzlG();
         zza(var3, "qt", String.valueOf(var10));
         if (var2) {
            String var8;
            long var11;
            if ((var11 = var1.zzlJ()) != 0L) {
               var8 = String.valueOf(var11);
            } else {
               var8 = String.valueOf(var1.zzlF());
            }

            zza(var3, "z", var8);
         }

         return var3.toString();
      } catch (UnsupportedEncodingException var9) {
         this.zze("Failed to encode name or value", var9);
         return null;
      }
   }

   private static void zza(StringBuilder var0, String var1, String var2) throws UnsupportedEncodingException {
      if (var0.length() != 0) {
         var0.append('&');
      }

      var0.append(URLEncoder.encode(var1, "UTF-8"));
      var0.append('=');
      var0.append(URLEncoder.encode(var2, "UTF-8"));
   }

   // $FF: synthetic method
   static byte[] zzlS() {
      return zzait;
   }
}
