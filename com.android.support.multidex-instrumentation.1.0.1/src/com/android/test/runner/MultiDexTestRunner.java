package com.android.test.runner;

import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.test.InstrumentationTestRunner;

public class MultiDexTestRunner extends InstrumentationTestRunner {
   public void onCreate(Bundle arguments) {
      MultiDex.install(this.getTargetContext());
      super.onCreate(arguments);
   }
}
