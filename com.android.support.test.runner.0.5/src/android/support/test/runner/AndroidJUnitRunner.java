package android.support.test.runner;

import android.app.Instrumentation;
import android.content.Context;
import android.os.Bundle;
import android.os.Debug;
import android.os.Build.VERSION;
import android.support.test.internal.runner.RunnerArgs;
import android.support.test.internal.runner.TestExecutor;
import android.support.test.internal.runner.TestRequest;
import android.support.test.internal.runner.TestRequestBuilder;
import android.support.test.internal.runner.listener.ActivityFinisherRunListener;
import android.support.test.internal.runner.listener.CoverageListener;
import android.support.test.internal.runner.listener.DelayInjector;
import android.support.test.internal.runner.listener.InstrumentationResultPrinter;
import android.support.test.internal.runner.listener.LogRunListener;
import android.support.test.internal.runner.listener.SuiteAssignmentPrinter;
import android.support.test.internal.runner.tracker.AnalyticsBasedUsageTracker;
import android.support.test.runner.lifecycle.ApplicationLifecycleCallback;
import android.support.test.runner.lifecycle.ApplicationLifecycleMonitorRegistry;
import android.util.Log;
import java.util.Iterator;
import org.junit.runner.notification.RunListener;

public class AndroidJUnitRunner extends MonitoringInstrumentation {
   private static final String LOG_TAG = "AndroidJUnitRunner";
   private Bundle mArguments;
   private InstrumentationResultPrinter mInstrumentationResultPrinter = null;
   private RunnerArgs mRunnerArgs;
   private UsageTrackerFacilitator mUsageTrackerFacilitator;

   public void onCreate(Bundle arguments) {
      this.mArguments = arguments;
      this.parseRunnerArgs(this.mArguments);
      if (this.mRunnerArgs.debug) {
         Log.i("AndroidJUnitRunner", "Waiting for debugger to connect...");
         Debug.waitForDebugger();
         Log.i("AndroidJUnitRunner", "Debugger connected.");
      }

      this.mUsageTrackerFacilitator = new UsageTrackerFacilitator(this.mRunnerArgs);
      super.onCreate(arguments);
      Iterator i$ = this.mRunnerArgs.appListeners.iterator();

      while(i$.hasNext()) {
         ApplicationLifecycleCallback listener = (ApplicationLifecycleCallback)i$.next();
         ApplicationLifecycleMonitorRegistry.getInstance().addLifecycleCallback(listener);
      }

      this.start();
   }

   private void parseRunnerArgs(Bundle arguments) {
      this.mRunnerArgs = (new RunnerArgs.Builder()).fromManifest(this).fromBundle(arguments).build();
   }

   private Bundle getArguments() {
      return this.mArguments;
   }

   InstrumentationResultPrinter getInstrumentationResultPrinter() {
      return this.mInstrumentationResultPrinter;
   }

   public void onStart() {
      super.onStart();
      if (this.mRunnerArgs.idle) {
         Log.i("AndroidJUnitRunner", "Runner is idle...");
      } else {
         Bundle results = new Bundle();

         try {
            TestExecutor.Builder executorBuilder = new TestExecutor.Builder(this);
            this.addListeners(this.mRunnerArgs, executorBuilder);
            TestRequest testRequest = this.buildRequest(this.mRunnerArgs, this.getArguments());
            results = executorBuilder.build().execute(testRequest);
         } catch (RuntimeException var4) {
            String msg = "Fatal exception when running tests";
            Log.e("AndroidJUnitRunner", "Fatal exception when running tests", var4);
            results.putString("stream", "Fatal exception when running tests\n" + Log.getStackTraceString(var4));
         }

         this.finish(-1, results);
      }
   }

   public void finish(int resultCode, Bundle results) {
      try {
         this.mUsageTrackerFacilitator.trackUsage("AndroidJUnitRunner");
         this.mUsageTrackerFacilitator.sendUsages();
      } catch (RuntimeException var4) {
         Log.w("AndroidJUnitRunner", "Failed to send analytics.", var4);
      }

      super.finish(resultCode, results);
   }

   private void addListeners(RunnerArgs args, TestExecutor.Builder builder) {
      if (args.suiteAssignment) {
         builder.addRunListener(new SuiteAssignmentPrinter());
      } else {
         builder.addRunListener(new LogRunListener());
         this.mInstrumentationResultPrinter = new InstrumentationResultPrinter();
         builder.addRunListener(this.mInstrumentationResultPrinter);
         builder.addRunListener(new ActivityFinisherRunListener(this, new MonitoringInstrumentation.ActivityFinisher()));
         this.addDelayListener(args, builder);
         this.addCoverageListener(args, builder);
      }

      this.addListenersFromArg(args, builder);
   }

   private void addCoverageListener(RunnerArgs args, TestExecutor.Builder builder) {
      if (args.codeCoverage) {
         builder.addRunListener(new CoverageListener(args.codeCoveragePath));
      }

   }

   private void addDelayListener(RunnerArgs args, TestExecutor.Builder builder) {
      if (args.delayInMillis > 0) {
         builder.addRunListener(new DelayInjector(args.delayInMillis));
      } else if (args.logOnly && VERSION.SDK_INT < 16) {
         builder.addRunListener(new DelayInjector(15));
      }

   }

   private void addListenersFromArg(RunnerArgs args, TestExecutor.Builder builder) {
      Iterator i$ = args.listeners.iterator();

      while(i$.hasNext()) {
         RunListener listener = (RunListener)i$.next();
         builder.addRunListener(listener);
      }

   }

   public boolean onException(Object obj, Throwable e) {
      InstrumentationResultPrinter instResultPrinter = this.getInstrumentationResultPrinter();
      if (instResultPrinter != null) {
         instResultPrinter.reportProcessCrash(e);
      }

      return super.onException(obj, e);
   }

   TestRequest buildRequest(RunnerArgs runnerArgs, Bundle bundleArgs) {
      TestRequestBuilder builder = this.createTestRequestBuilder(this, bundleArgs);
      builder.addApkToScan(this.getContext().getPackageCodePath());
      builder.addFromRunnerArgs(runnerArgs);
      this.registerUserTracker();
      return builder.build();
   }

   private void registerUserTracker() {
      Context targetContext = this.getTargetContext();
      if (targetContext != null) {
         this.mUsageTrackerFacilitator.registerUsageTracker((new AnalyticsBasedUsageTracker.Builder(targetContext)).buildIfPossible());
      }

   }

   TestRequestBuilder createTestRequestBuilder(Instrumentation instr, Bundle arguments) {
      return new TestRequestBuilder(instr, arguments);
   }
}
