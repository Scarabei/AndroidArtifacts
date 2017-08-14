package android.support.test.rule.logging;

import android.app.Instrumentation;
import android.app.UiAutomation;
import android.content.Context;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.support.annotation.Nullable;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.internal.util.Checks;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RuleLoggingUtils {
   private static final String TAG = "RuleLoggingUtils";
   public static final String LOGGING_SUB_DIR_NAME = "testdata";

   private static void assertFileContent(@Nullable String message, File file, String contentString, boolean contains) throws AssertionError, IOException {
      StringBuilder fileContents = new StringBuilder();
      boolean shouldContain = contains;
      boolean didContain = false;

      try {
         FileReader fileReader = new FileReader(file);
         Throwable var8 = null;

         try {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Throwable var10 = null;

            try {
               String readLine;
               while((readLine = bufferedReader.readLine()) != null) {
                  fileContents.append(readLine);
                  fileContents.append(System.lineSeparator());
                  if (fileContents.length() >= contentString.length()) {
                     long index = (long)fileContents.indexOf(contentString);
                     if (!shouldContain && index >= 0L) {
                        throw new AssertionError("File content found that shouldn't have been present, contentString=" + contentString);
                     }

                     if (contains && index >= 0L) {
                        didContain = true;
                        break;
                     }

                     fileContents.delete(0, fileContents.length() - contentString.length());
                  }
               }

               if (shouldContain && !didContain) {
                  throw new AssertionError("File content not found that should have been present, contentString=" + contentString);
               }
            } catch (Throwable var37) {
               var10 = var37;
               throw var37;
            } finally {
               if (bufferedReader != null) {
                  if (var10 != null) {
                     try {
                        bufferedReader.close();
                     } catch (Throwable var36) {
                        var10.addSuppressed(var36);
                     }
                  } else {
                     bufferedReader.close();
                  }
               }

            }
         } catch (Throwable var39) {
            var8 = var39;
            throw var39;
         } finally {
            if (fileReader != null) {
               if (var8 != null) {
                  try {
                     fileReader.close();
                  } catch (Throwable var35) {
                     var8.addSuppressed(var35);
                  }
               } else {
                  fileReader.close();
               }
            }

         }

      } catch (AssertionError var41) {
         if (message != null) {
            throw new AssertionError(message, var41);
         } else {
            throw var41;
         }
      }
   }

   private static String getCommandFromParts(String[] commandParts) {
      StringBuilder commandBuilder = new StringBuilder();
      String[] arr$ = commandParts;
      int len$ = commandParts.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         String commandPart = arr$[i$];
         commandBuilder.append(commandPart);
         commandBuilder.append(" ");
      }

      return commandBuilder.toString();
   }

   private static File getTestDirectory(String className, String testName, @Nullable Integer testRunNumber) {
      Context context = InstrumentationRegistry.getTargetContext();
      File rootDir = context.getExternalFilesDir((String)null);
      File testFileDir = new File(rootDir, "testdata");
      if (getTranslatedTestName(className, testName) != null) {
         testFileDir = new File(testFileDir, getTranslatedTestName(className, testName));
         if (testRunNumber != null) {
            testFileDir = new File(testFileDir, testRunNumber + "");
         }
      }

      if (!testFileDir.exists() && !testFileDir.mkdirs()) {
         throw new RuntimeException("Unable to create logging rules log directory.");
      } else {
         return testFileDir;
      }
   }

   private static String getTranslatedTestName(String className, String testName) {
      if (className != null && testName != null) {
         String base = className + "_" + testName;
         base = base.replace("com", "c").replace("google", "g").replace("android", "a").replace("perfmatters", "pm").replace("automatingperformancetesting", "apt");
         return base;
      } else {
         return null;
      }
   }

   private static void writeProcessOutputToLogcat(Process process, String logTag) throws IOException {
      InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
      Throwable var3 = null;

      try {
         BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
         Throwable var5 = null;

         try {
            String line;
            try {
               while(null != (line = bufferedReader.readLine())) {
                  Log.w(logTag, line);
               }
            } catch (Throwable var28) {
               var5 = var28;
               throw var28;
            }
         } finally {
            if (bufferedReader != null) {
               if (var5 != null) {
                  try {
                     bufferedReader.close();
                  } catch (Throwable var27) {
                     var5.addSuppressed(var27);
                  }
               } else {
                  bufferedReader.close();
               }
            }

         }
      } catch (Throwable var30) {
         var3 = var30;
         throw var30;
      } finally {
         if (inputStreamReader != null) {
            if (var3 != null) {
               try {
                  inputStreamReader.close();
               } catch (Throwable var26) {
                  var3.addSuppressed(var26);
               }
            } else {
               inputStreamReader.close();
            }
         }

      }

   }

   public static void assertEmptyFile(@Nullable String message, File file) throws AssertionError, IOException {
      try {
         if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            Throwable var3 = null;

            try {
               InputStreamReader isr = new InputStreamReader(fis);
               Throwable var5 = null;

               try {
                  BufferedReader br = new BufferedReader(isr);
                  Throwable var7 = null;

                  try {
                     String firstLine = br.readLine();
                     if (firstLine != null && !TextUtils.isEmpty(firstLine)) {
                        throw new AssertionError("Expected file to be empty, but was able to read data: " + firstLine);
                     }
                  } catch (Throwable var54) {
                     var7 = var54;
                     throw var54;
                  } finally {
                     if (br != null) {
                        if (var7 != null) {
                           try {
                              br.close();
                           } catch (Throwable var53) {
                              var7.addSuppressed(var53);
                           }
                        } else {
                           br.close();
                        }
                     }

                  }
               } catch (Throwable var56) {
                  var5 = var56;
                  throw var56;
               } finally {
                  if (isr != null) {
                     if (var5 != null) {
                        try {
                           isr.close();
                        } catch (Throwable var52) {
                           var5.addSuppressed(var52);
                        }
                     } else {
                        isr.close();
                     }
                  }

               }
            } catch (Throwable var58) {
               var3 = var58;
               throw var58;
            } finally {
               if (fis != null) {
                  if (var3 != null) {
                     try {
                        fis.close();
                     } catch (Throwable var51) {
                        var3.addSuppressed(var51);
                     }
                  } else {
                     fis.close();
                  }
               }

            }

         } else {
            throw new IOException("Expected file did not exist: " + file.getAbsolutePath());
         }
      } catch (AssertionError | IOException var60) {
         if (message != null) {
            throw new AssertionError(message, var60);
         } else {
            throw var60;
         }
      }
   }

   public static void assertFileContentContains(@Nullable String message, File file, String contentString) throws AssertionError, IOException {
      assertFileContent(message, file, contentString, true);
   }

   public static void assertFileContentDoesNotContain(@Nullable String message, File file, String contentString) throws AssertionError, IOException {
      assertFileContent(message, file, contentString, false);
   }

   public static void assertFileContentStartsWith(@Nullable String message, File file, String contentString) throws AssertionError, IOException {
      StringBuilder fileContents = new StringBuilder();
      FileReader fileReader = new FileReader(file);
      Throwable var5 = null;

      try {
         BufferedReader bufferedReader = new BufferedReader(fileReader);
         Throwable var7 = null;

         try {
            String readLine;
            try {
               while((readLine = bufferedReader.readLine()) != null && fileContents.length() < contentString.length()) {
                  fileContents.append(readLine);
                  fileContents.append(System.lineSeparator());
               }
            } catch (Throwable var33) {
               var7 = var33;
               throw var33;
            }
         } finally {
            if (bufferedReader != null) {
               if (var7 != null) {
                  try {
                     bufferedReader.close();
                  } catch (Throwable var31) {
                     var7.addSuppressed(var31);
                  }
               } else {
                  bufferedReader.close();
               }
            }

         }
      } catch (Throwable var35) {
         var5 = var35;
         throw var35;
      } finally {
         if (fileReader != null) {
            if (var5 != null) {
               try {
                  fileReader.close();
               } catch (Throwable var30) {
                  var5.addSuppressed(var30);
               }
            } else {
               fileReader.close();
            }
         }

      }

      try {
         if (fileContents.length() < contentString.length()) {
            throw new AssertionError("File content wasn't long enough to match, expected=" + contentString + ", minimalStartingFileContent=" + fileContents);
         } else if (!fileContents.substring(0, contentString.length()).equals(contentString)) {
            throw new AssertionError("File content did not match, expected=" + contentString + ", minimalStartingFileContent=" + fileContents);
         }
      } catch (AssertionError var32) {
         if (message != null) {
            throw new AssertionError(message, var32);
         } else {
            throw var32;
         }
      }
   }

   public static File getTestDir(String className, String testName, int testRunNumber) {
      Checks.checkState(testRunNumber >= 0, "Invalid test run number (" + testRunNumber + ")");
      return getTestDirectory(className, testName, testRunNumber);
   }

   public static File getTestFile(String className, String testName, String filename, int testRunNumber) {
      return new File(getTestDir(className, testName, testRunNumber), filename);
   }

   public static File getTestRunDir() {
      return getTestDirectory((String)null, (String)null, (Integer)null);
   }

   public static File getTestRunFile(String filename) {
      return new File(getTestDirectory((String)null, (String)null, (Integer)null), filename);
   }

   public static void printFileToLogcat(File logFile, String logcatTag) throws IOException {
      FileReader fileReader = new FileReader(logFile);
      Throwable var3 = null;

      try {
         BufferedReader bufferedReader = new BufferedReader(fileReader);
         Throwable var5 = null;

         try {
            Log.w(logcatTag, "Logging file located at " + logFile.getAbsolutePath());

            String line;
            while(null != (line = bufferedReader.readLine())) {
               Log.w(logcatTag, line);
            }
         } catch (Throwable var28) {
            var5 = var28;
            throw var28;
         } finally {
            if (bufferedReader != null) {
               if (var5 != null) {
                  try {
                     bufferedReader.close();
                  } catch (Throwable var27) {
                     var5.addSuppressed(var27);
                  }
               } else {
                  bufferedReader.close();
               }
            }

         }
      } catch (Throwable var30) {
         var3 = var30;
         throw var30;
      } finally {
         if (fileReader != null) {
            if (var3 != null) {
               try {
                  fileReader.close();
               } catch (Throwable var26) {
                  var3.addSuppressed(var26);
               }
            } else {
               fileReader.close();
            }
         }

      }

   }

   @SdkSuppress(
      minSdkVersion = 21
   )
   public static void startCmdAndLogOutputPostL(String[] commandParts, File logFile) {
      try {
         Instrumentation testingInstrumentation = InstrumentationRegistry.getInstrumentation();
         UiAutomation uiAutomation = testingInstrumentation.getUiAutomation();
         String command = getCommandFromParts(commandParts);
         InputStream inputStream = new AutoCloseInputStream(uiAutomation.executeShellCommand(command));
         Throwable var6 = null;

         try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            Throwable var8 = null;

            try {
               BufferedReader reader = new BufferedReader(inputStreamReader);
               Throwable var10 = null;

               try {
                  FileWriter fileWriter = new FileWriter(logFile);
                  Throwable var12 = null;

                  try {
                     BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                     Throwable var14 = null;

                     try {
                        String line;
                        try {
                           while(null != (line = reader.readLine())) {
                              bufferedWriter.write(line);
                              bufferedWriter.write(System.lineSeparator());
                           }
                        } catch (Throwable var126) {
                           var14 = var126;
                           throw var126;
                        }
                     } finally {
                        if (bufferedWriter != null) {
                           if (var14 != null) {
                              try {
                                 bufferedWriter.close();
                              } catch (Throwable var125) {
                                 var14.addSuppressed(var125);
                              }
                           } else {
                              bufferedWriter.close();
                           }
                        }

                     }
                  } catch (Throwable var128) {
                     var12 = var128;
                     throw var128;
                  } finally {
                     if (fileWriter != null) {
                        if (var12 != null) {
                           try {
                              fileWriter.close();
                           } catch (Throwable var124) {
                              var12.addSuppressed(var124);
                           }
                        } else {
                           fileWriter.close();
                        }
                     }

                  }
               } catch (Throwable var130) {
                  var10 = var130;
                  throw var130;
               } finally {
                  if (reader != null) {
                     if (var10 != null) {
                        try {
                           reader.close();
                        } catch (Throwable var123) {
                           var10.addSuppressed(var123);
                        }
                     } else {
                        reader.close();
                     }
                  }

               }
            } catch (Throwable var132) {
               var8 = var132;
               throw var132;
            } finally {
               if (inputStreamReader != null) {
                  if (var8 != null) {
                     try {
                        inputStreamReader.close();
                     } catch (Throwable var122) {
                        var8.addSuppressed(var122);
                     }
                  } else {
                     inputStreamReader.close();
                  }
               }

            }
         } catch (Throwable var134) {
            var6 = var134;
            throw var134;
         } finally {
            if (inputStream != null) {
               if (var6 != null) {
                  try {
                     inputStream.close();
                  } catch (Throwable var121) {
                     var6.addSuppressed(var121);
                  }
               } else {
                  inputStream.close();
               }
            }

         }
      } catch (Exception var136) {
         writeErrorToFileAndLogcat(logFile, "RuleLoggingUtils", "Couldn't start and write process output", var136);
      }

   }

   public static Process startProcess(String[] commandParts) throws IOException {
      ProcessBuilder processBuilder = new ProcessBuilder(new String[0]);
      processBuilder.command(commandParts);
      processBuilder.redirectErrorStream();
      return processBuilder.start();
   }

   public static void startProcessAndLogToFile(String[] commandParts, File logFile, int androidVersion) {
      if (androidVersion > 21) {
         startCmdAndLogOutputPostL(commandParts, logFile);
      } else {
         startProcessAndWriteOutputToFilePreL(commandParts, logFile);
      }

   }

   public static void startProcessAndWriteOutputToFilePreL(String[] commandParts, File logFile) {
      Process process = null;

      try {
         process = startProcess(commandParts);
         process.waitFor();
         writeProcessOutputToFile(process, logFile);
      } catch (IOException | InterruptedException var7) {
         writeErrorToFileAndLogcat(logFile, "RuleLoggingUtils", "Couldn't start and write process output", var7);
      } finally {
         if (process != null) {
            process.destroy();
         }

      }

   }

   public static void writeErrorToFileAndLogcat(File file, String logTag, String errorMessage, @Nullable Exception exception) {
      if (exception != null) {
         Log.e(logTag, errorMessage, exception);
      } else {
         Log.e(logTag, errorMessage);
      }

      try {
         FileWriter fileWriter = new FileWriter(file);
         Throwable var5 = null;

         try {
            fileWriter.append(errorMessage);
            fileWriter.append(System.lineSeparator());
            if (exception != null) {
               fileWriter.append(exception.toString());
            }
         } catch (Throwable var15) {
            var5 = var15;
            throw var15;
         } finally {
            if (fileWriter != null) {
               if (var5 != null) {
                  try {
                     fileWriter.close();
                  } catch (Throwable var14) {
                     var5.addSuppressed(var14);
                  }
               } else {
                  fileWriter.close();
               }
            }

         }
      } catch (IOException var17) {
         Log.e(logTag, "Unable to log error to file " + file.getAbsolutePath(), var17);
      }

   }

   public static void writeProcessOutputToFile(Process process, File logFile) throws IOException {
      FileWriter fileWriter = new FileWriter(logFile);
      Throwable var3 = null;

      try {
         BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
         Throwable var5 = null;

         try {
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
            Throwable var7 = null;

            try {
               BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
               Throwable var9 = null;

               try {
                  String line;
                  try {
                     while(null != (line = bufferedReader.readLine())) {
                        bufferedWriter.append(line);
                        bufferedWriter.append(System.lineSeparator());
                     }
                  } catch (Throwable var81) {
                     var9 = var81;
                     throw var81;
                  }
               } finally {
                  if (bufferedReader != null) {
                     if (var9 != null) {
                        try {
                           bufferedReader.close();
                        } catch (Throwable var80) {
                           var9.addSuppressed(var80);
                        }
                     } else {
                        bufferedReader.close();
                     }
                  }

               }
            } catch (Throwable var83) {
               var7 = var83;
               throw var83;
            } finally {
               if (inputStreamReader != null) {
                  if (var7 != null) {
                     try {
                        inputStreamReader.close();
                     } catch (Throwable var79) {
                        var7.addSuppressed(var79);
                     }
                  } else {
                     inputStreamReader.close();
                  }
               }

            }
         } catch (Throwable var85) {
            var5 = var85;
            throw var85;
         } finally {
            if (bufferedWriter != null) {
               if (var5 != null) {
                  try {
                     bufferedWriter.close();
                  } catch (Throwable var78) {
                     var5.addSuppressed(var78);
                  }
               } else {
                  bufferedWriter.close();
               }
            }

         }
      } catch (Throwable var87) {
         var3 = var87;
         throw var87;
      } finally {
         if (fileWriter != null) {
            if (var3 != null) {
               try {
                  fileWriter.close();
               } catch (Throwable var77) {
                  var3.addSuppressed(var77);
               }
            } else {
               fileWriter.close();
            }
         }

      }

   }
}
