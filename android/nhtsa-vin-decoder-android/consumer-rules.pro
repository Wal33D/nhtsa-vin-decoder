# Add project specific ProGuard rules here.
# These rules are applied to code consumed by this library.

# Keep VIN decoder public API
-keep class io.github.vindecoder.android.VINDecoderAndroid { *; }
-keep class io.github.vindecoder.android.VINDecoderAndroid$* { *; }
-keep class io.github.vindecoder.nhtsa.** { *; }
-keep class io.github.vindecoder.offline.** { *; }

# Keep Retrofit and Gson classes
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.google.gson.** { *; }
-keep class retrofit2.** { *; }
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}
