//
//  ContentView.swift
//  ioshostapp
//
//  Created by Henrique Cícero on 17/01/2026.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        NavigationStack {
            VStack(spacing: 24) {
                Text("Hello, Host App!")
                    .font(.title)

                NavigationLink("Open Embedded App") {
                    ReactNativeContainerView()
                }
                .buttonStyle(.borderedProminent)
            }
            .padding()
        }
    }
}

#Preview {
    ContentView()
}
