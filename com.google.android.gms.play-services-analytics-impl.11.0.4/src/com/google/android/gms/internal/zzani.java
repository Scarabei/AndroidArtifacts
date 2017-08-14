package com.google.android.gms.internal;

import android.content.res.XmlResourceParser;
import android.content.res.Resources.NotFoundException;
import android.text.TextUtils;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

class zzani extends zzamg {
   private zzanj zzagT;

   public zzani(zzamj var1, zzanj var2) {
      super(var1);
      this.zzagT = var2;
   }

   public final zzanh zzP(int var1) {
      try {
         return this.zza(this.zzkp().zzkE().getResources().getXml(var1));
      } catch (NotFoundException var3) {
         this.zzd("inflate() called with unknown resourceId", var3);
         return null;
      }
   }

   private final zzanh zza(XmlResourceParser var1) {
      try {
         var1.next();

         for(int var2 = var1.getEventType(); var2 != 1; var2 = var1.next()) {
            if (var1.getEventType() == 2) {
               String var3;
               String var4;
               String var5;
               if ((var3 = var1.getName().toLowerCase()).equals("screenname")) {
                  var4 = var1.getAttributeValue((String)null, "name");
                  var5 = var1.nextText().trim();
                  if (!TextUtils.isEmpty(var4) && !TextUtils.isEmpty(var5)) {
                     this.zzagT.zzl(var4, var5);
                  }
               } else if (var3.equals("string")) {
                  var4 = var1.getAttributeValue((String)null, "name");
                  var5 = var1.nextText().trim();
                  if (!TextUtils.isEmpty(var4) && var5 != null) {
                     this.zzagT.zzm(var4, var5);
                  }
               } else if (var3.equals("bool")) {
                  var4 = var1.getAttributeValue((String)null, "name");
                  var5 = var1.nextText().trim();
                  if (!TextUtils.isEmpty(var4) && !TextUtils.isEmpty(var5)) {
                     try {
                        boolean var6 = Boolean.parseBoolean(var5);
                        this.zzagT.zze(var4, var6);
                     } catch (NumberFormatException var8) {
                        this.zzc("Error parsing bool configuration value", var5, var8);
                     }
                  }
               } else if (var3.equals("integer")) {
                  var4 = var1.getAttributeValue((String)null, "name");
                  var5 = var1.nextText().trim();
                  if (!TextUtils.isEmpty(var4) && !TextUtils.isEmpty(var5)) {
                     try {
                        int var11 = Integer.parseInt(var5);
                        this.zzagT.zzd(var4, var11);
                     } catch (NumberFormatException var7) {
                        this.zzc("Error parsing int configuration value", var5, var7);
                     }
                  }
               }
            }
         }
      } catch (XmlPullParserException var9) {
         this.zze("Error parsing tracker configuration file", var9);
      } catch (IOException var10) {
         this.zze("Error parsing tracker configuration file", var10);
      }

      return this.zzagT.zzlm();
   }
}
