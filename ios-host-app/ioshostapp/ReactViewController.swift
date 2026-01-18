//
//  ReactViewController.swift
//  ioshostapp
//
//  Created by Henrique Cícero on 18/01/2026.
//

import UIKit
import React
import React_RCTAppDelegate
import ReactAppDependencyProvider

class ReactViewController: UIViewController {
    var reactNativeFactory: RCTReactNativeFactory?
    var reactNativeFactoryDelegate: RCTReactNativeFactoryDelegate?

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        navigationItem.leftBarButtonItem = UIBarButtonItem(
            barButtonSystemItem: .close,
            target: self,
            action: #selector(closePressed)
        )
        reactNativeFactoryDelegate = ReactNativeDelegate()
        reactNativeFactoryDelegate!.dependencyProvider = RCTAppDependencyProvider()
        reactNativeFactory = RCTReactNativeFactory(delegate: reactNativeFactoryDelegate!)
        view = reactNativeFactory!.rootViewFactory.view(withModuleName: "reactnativeembeddedapp")
    }

    @objc private func closePressed() {
        dismiss(animated: true)
    }

}

class ReactNativeDelegate: RCTDefaultReactNativeFactoryDelegate {
    override func sourceURL(for bridge: RCTBridge) -> URL? {
      self.bundleURL()
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
