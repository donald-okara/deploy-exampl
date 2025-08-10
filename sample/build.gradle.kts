plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("com.vanniktech.maven.publish") version "0.34.0"
}

val versionProp = findProperty("version") as String?
if (versionProp.isNullOrBlank()) {
    throw GradleException("❌ Version property is required. Pass it via -Pversion=YOUR_VERSION")
}

android {
    namespace = "ke.don.sample"
    compileSdk = 36

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

mavenPublishing {
    publishToMavenCentral() // or publishToMavenCentral(automaticRelease = true)
    signAllPublications()

    coordinates("io.github.donald-okara", "sample", versionProp!!)

    pom {
        name.set("Sample Library")
        description.set("A description of what my library does.")
        inceptionYear.set("2025")
        url.set("https://github.com/donald-okara/deploy-exampl/")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("repo")
            }
        }
        developers {
            developer {
                id.set("donald-okara")
                name.set("Donald Okara")
                url.set("https://github.com/donald-okara/")
            }
        }
        scm {
            url.set("https://github.com/donald-okara/deploy-exampl/")
            connection.set("scm:git:git://github.com/donald-okara/deploy-exampl.git")
            developerConnection.set("scm:git:ssh://git@github.com/donald-okara/deploy-exampl.git")
        }
    }
}

