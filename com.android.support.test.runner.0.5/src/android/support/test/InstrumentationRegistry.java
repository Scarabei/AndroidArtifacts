package android.support.test;

import android.app.Instrumentation;
import android.content.Context;
import android.os.Bundle;
import java.util.concurrent.atomic.AtomicReference;

public final class InstrumentationRegistry {
   private static final AtomicReference sInstrumentationRef = new AtomicReference((Object)null);
   private static final AtomicReference sArguments = new AtomicReference((Object)null);

   public static Instrumentation getInstrumentation() {
      Instrumentation instance = (Instrumentation)sInstrumentationRef.get();
      if (null == instance) {
         throw new IllegalStateException("No instrumentation registered! Must run under a registering instrumentation.");
      } else {
         return instance;
      }
   }

   public static Bundle getArguments() {
      Bundle instance = (Bundle)sArguments.get();
      if (null == instance) {
         throw new IllegalStateException("No instrumentation arguments registered! Are you running under an Instrumentation which registers arguments?");
      } else {
         return new Bundle(instance);
      }
   }

   public static Context getContext() {
      return getInstrumentation().getContext();
   }

   public static Context getTargetContext() {
      return getInstrumentation().getTargetContext();
   }

   public static void registerInstance(Instrumentation instrumentation, Bundle arguments) {
      sInstrumentationRef.set(instrumentation);
      sArguments.set(new Bundle(arguments));
   }
}
