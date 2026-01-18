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
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}

class ReactNativeDelegate: RCTDefaultReactNativeFactoryDelegate {
    override func sourceURL(for bridge: RCTBridge) -> URL? {
      self.bundleURL()
    }

    override func bundleURL() -> URL? {
      #if DEBUG
      let bundleURLProvider = RCTBundleURLProvider.sharedSettings()
      // Physical device needs the Mac host; update if your IP changes.
      bundleURLProvider.jsLocation = "192.168.1.18:8081"
      return bundleURLProvider.jsBundleURL(forBundleRoot: "index")
      #else
      return Bundle.main.url(forResource: "main", withExtension: "jsbundle")
      #endif
    }

}
