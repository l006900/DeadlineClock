plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.deadlineclock"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.deadlineclock"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }


}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //recyclerview
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("com.android.support:recyclerview-v7:28.0.0")
    //Material库
    implementation("com.google.android.material:material:1.12.0")
    //图片圆形化
    implementation("de.hdodenhof:circleimageview:3.0.1")

    implementation("androidx.fragment:fragment:1.8.2")

    //Design Support库
    implementation("com.android.support:appcompat-v7:28.0.0")

    //viewmodel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.navigation:navigation-fragment:2.5.3")
    implementation("androidx.navigation:navigation-ui:2.5.3")

    implementation ("com.github.bumptech.glide:glide:4.12.0")
    implementation ("androidx.palette:palette:1.0.0")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))


    //glide图片框架

    implementation ("com.github.bumptech.glide:glide:4.12.0")
    implementation ("com.github.bumptech.glide:annotations:4.12.0")
    implementation ("com.github.bumptech.glide:okhttp3-integration:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
    implementation ("jp.wasabeef:glide-transformations:4.3.0")

    //放大图片
    implementation("com.github.FlyJingFish.OpenImage:OpenImageGlideLib:v1.3.2")

    //底部导航栏
    implementation ("com.google.android.material:material:1.12.0")

    //banner
    implementation("androidx.viewpager2:viewpager2:1.1.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

    //OKHttp
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    //mmkv
    implementation("com.tencent:mmkv:1.3.9")



}