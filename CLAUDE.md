# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a **React Native brownfield integration project** demonstrating how to embed a React Native app into existing native Android (Kotlin/Compose) and iOS (SwiftUI) host applications.

**Structure:**
- `react-native-embedded-app/` - The React Native application (embedded component)
- `android-host-app/` - Native Android host (Kotlin + Jetpack Compose)
- `ios-host-app/` - Native iOS host (SwiftUI)

## Development Commands

All React Native commands run from `react-native-embedded-app/`:

```bash
yarn start          # Start Metro bundler
yarn android        # Build and run on Android
yarn ios            # Build and run on iOS
yarn test           # Run Jest tests
yarn lint           # Run ESLint
```

### iOS Setup (first time or after native dependency changes)

```bash
cd ios-host-app
bundle install              # Install Ruby dependencies
bundle exec pod install     # Install CocoaPods dependencies
```

### Running a Single Test

```bash
cd react-native-embedded-app
yarn test -- --testPathPattern="App.test"
```

## Architecture

### Brownfield Integration Pattern

The React Native app is consumed by native host apps rather than the typical React Native-first approach:

- **Android**: `ReactNativeActivity.kt` loads the RN app, configured in `app/build.gradle.kts` with paths pointing to `../../react-native-embedded-app`
- **iOS**: `ReactNativeContainerView.swift` wraps the RN app, configured in `Podfile` with path `../react-native-embedded-app`

### Key Integration Files

**Android:**
- `MainApplication.kt` - React Native initialization
- `ReactNativeActivity.kt` - Activity that hosts the RN view
- `settings.gradle.kts` - React Native Gradle Plugin configuration

**iOS:**
- `ReactNativeContainerView.swift` - SwiftUI wrapper for React Native
- `Podfile` - CocoaPods configuration pointing to RN app

### Configuration

- **New Architecture**: Enabled (`newArchEnabled=true` in `gradle.properties`)
- **Hermes**: Enabled as the JavaScript engine
- **Target SDK**: Android 36, Min SDK 28
- **Node**: >= 20 required
- **Package Manager**: Yarn 4.12.0

## Guidelines

- Don't commit changes on your own - changes will be reviewed and committed manually
- Don't use emojis
