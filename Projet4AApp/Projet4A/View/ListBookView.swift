//
//  ListView.swift
//  Projet4A
//
//  Created by Sogekingu on 20/12/2020.
//

import SwiftUI

struct ListBookView: View {
    @State private var isAddBookShowed: Bool = false
    @State private var isInfoShowed: Bool = false
    @ObservedObject var bookLibrary: BookLibraryManager
    @Environment(\.managedObjectContext) private var viewContext
    
    @FetchRequest(
        entity: BookEntity.entity(),
        sortDescriptors: [
            NSSortDescriptor(keyPath: \BookEntity.id, ascending: true)
        ]
    ) var myBooks: FetchedResults<BookEntity>
    
    var body: some View {
        NavigationView {
            ZStack(alignment: .bottom) {
                
                //My list of books with navigation
                List(myBooks) { myBookEntity in
                    NavigationLink(
                        destination: BookDetailView(
                            currentBook: Book(
                                bookEntity: myBookEntity
                            ),
                            deleteAction: {
                                viewContext.delete(myBookEntity)
                                do {
                                    try viewContext.save()
                                } catch {
                                    print("Erreur de suppression")
                                }
                            }
                        ),
                        label: {
                            BookCellView(
                                book: Book(
                                    bookEntity: myBookEntity
                                )
                            )
                        }
                    )
                }
                .listStyle(GroupedListStyle())
                
                // Button
                AddBookButtonView(
                    size: 25,
                    action: {
                        isAddBookShowed.toggle()
                    }
                )
                .padding(10)
                .sheet(
                    isPresented: $isAddBookShowed,
                    content: {
                        AddBookView(bookLibrary: bookLibrary)
                    }
                )
            }
            .navigationTitle(Text("My Books"))
            .navigationBarItems(trailing: InfoButton(isInfoShowed: $isInfoShowed))
        }
    }
}

struct InfoButton: View {
    @Binding var isInfoShowed: Bool
    
    var body: some View {
        Button(
            action: { isInfoShowed.toggle()},
            label: {
                Image(systemName: "info.circle")
                    .font(.system(size: 25))
                    .padding(5)
            }
        )
        .sheet(
            isPresented:$isInfoShowed,
            content: {
                InfoView()
            }
        )
    }
}


struct MyBooksView_Previews: PreviewProvider {
    @StateObject static var bookLibrary = BookLibraryManager()
    
    static var previews: some View {
        ListBookView(bookLibrary: bookLibrary)
    }
}
