//
//  ContentView.swift
//  ioshostapp
//
//  Created by Henrique Cícero on 17/01/2026.
//

import SwiftUI

struct ContentView: View {
    @State private var showReactNative = false
    
    var body: some View {
        VStack(spacing: 20) {
            Text("Hello, iOS!")
                .font(.title)
            
            Button("Open React Native") {
                showReactNative = true
            }
            .buttonStyle(.borderedProminent)
        }
        .padding()
        .fullScreenCover(isPresented: $showReactNative) {
            ReactNativeContainerView()
        }
    }
}

#Preview {
    ContentView()
}
