//
//  ReactViewControllerWrapper.swift
//  ioshostapp
//
//  SwiftUI wrapper to present ReactViewController as a full-screen cover
//

import SwiftUI

struct ReactViewControllerWrapper: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        UINavigationController(rootViewController: ReactViewController())
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
        // No updates needed
    }
}
