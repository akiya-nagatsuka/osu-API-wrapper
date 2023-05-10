import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel

    var body: some View {
        Text(viewModel.text)
    }
}

extension ContentView {
    class ViewModel: ObservableObject {
        @Published var text = "Loading..."
        init() {
            if (DIKt.tokenProvider.isAuthenticated) {
                self.text = "Authenticated"
            } else {
                DIKt.tokenProvider.authorise(isForce: false, completionHandler: { _ in })
            }
        }
    }
}