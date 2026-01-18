//
//  ReactNativeContainerView.swift
//  ioshostapp
//
//  SwiftUI wrapper to present React Native without a custom UIViewController.
//

import SwiftUI
import UIKit
import React
import React_RCTAppDelegate
import ReactAppDependencyProvider

struct ReactNativeContainerView: View {
    var body: some View {
        ReactNativeView()
            .ignoresSafeArea()
    }
}

struct ReactNativeView: UIViewRepresentable {
    func makeCoordinator() -> Coordinator {
        Coordinator()
    }

    func makeUIView(context: Context) -> UIView {
        context.coordinator.prepareFactoryIfNeeded()
        return context.coordinator.factory!.rootViewFactory.view(withModuleName: "reactnativeembeddedapp")
    }

    func updateUIView(_ uiView: UIView, context: Context) {
        // No updates needed
    }

    final class Coordinator {
        var factory: RCTReactNativeFactory?
        private var factoryDelegate: RCTReactNativeFactoryDelegate?

        func prepareFactoryIfNeeded() {
            guard factory == nil else { return }
            let delegate = ReactNativeDelegate()
            delegate.dependencyProvider = RCTAppDependencyProvider()
            factoryDelegate = delegate
            factory = RCTReactNativeFactory(delegate: delegate)
        }
    }
}

final class ReactNativeDelegate: RCTDefaultReactNativeFactoryDelegate {
    override func sourceURL(for bridge: RCTBridge) -> URL? {
        bundleURL()
    }

    override func bundleURL() -> URL? {
        #if DEBUG
        let bundleURLProvider = RCTBundleURLProvider.sharedSettings()
        if let metroHost = Bundle.main.object(forInfoDictionaryKey: "RNMetroHost") as? String,
           !metroHost.isEmpty {
            bundleURLProvider.jsLocation = metroHost
        }
        return bundleURLProvider.jsBundleURL(forBundleRoot: "index")
        #else
        return Bundle.main.url(forResource: "main", withExtension: "jsbundle")
        #endif
    }
}
