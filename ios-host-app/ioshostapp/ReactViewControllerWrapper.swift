//
//  ReactViewControllerWrapper.swift
//  ioshostapp
//
//  SwiftUI wrapper to present ReactViewController as a full-screen cover
//

import SwiftUI

struct ReactViewControllerWrapper: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> ReactViewController {
        return ReactViewController()
    }
    
    func updateUIViewController(_ uiViewController: ReactViewController, context: Context) {
        // No updates needed
    }
}
