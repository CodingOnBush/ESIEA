//
//  Projet4AApp.swift
//  Projet4A
//
//  Created by Sogekingu on 23/11/2020.
//

import SwiftUI

@main
// The @main attribute identifies the app’s entry point.
struct Projet4AApp: App {
    @StateObject var bookLibrary = BookLibraryManager()
    let persistenceController = PersistenceController.shared
    
    var body: some Scene {
        WindowGroup {
            ListBookView(bookLibrary: bookLibrary)
                .environmentObject(BookLibraryManager())
                .environment(\.managedObjectContext, persistenceController.container.viewContext)
        }
    }
}
