// React Native Gradle Plugin configuration for brownfield integration
pluginManagement {
    includeBuild("../react-native-embedded-app/node_modules/@react-native/gradle-plugin")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("com.facebook.react.settings")
}

extensions.configure<com.facebook.react.ReactSettingsExtension> {
    autolinkLibrariesFromCommand(
        command = listOf("/Users/rick/.nvm/versions/node/v20.19.4/bin/npx", "@react-native-community/cli", "config"),
        workingDirectory = settings.layout.rootDirectory.dir("../react-native-embedded-app").asFile,
        lockFiles = settings.layout.rootDirectory.dir("../react-native-embedded-app")
            .files("yarn.lock", "package-lock.json", "package.json", "react-native.config.js")
    )
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
    }
}

// Include React Native Gradle Plugin build
includeBuild("../react-native-embedded-app/node_modules/@react-native/gradle-plugin")

rootProject.name = "androidhostapp"
include(":app")
