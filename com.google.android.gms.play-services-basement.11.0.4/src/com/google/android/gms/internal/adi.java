package com.google.android.gms.internal;

import java.io.IOException;

public final class adi extends IOException {
   adi(int var1, int var2) {
      super((new StringBuilder(108)).append("CodedOutputStream was writing to a flat byte array and ran out of space (pos ").append(var1).append(" limit ").append(var2).append(").").toString());
   }
}
