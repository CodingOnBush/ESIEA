//
//  BookDetailView.swift
//  Projet4A
//
//  Created by Sogekingu on 27/12/2020.
//

import SwiftUI

struct BookDetailView: View {
    @Environment(\.presentationMode) var presentationMode
    @State var currentBook: Book
    let deleteAction: () -> Void
    
    var body: some View {
        VStack
        {
            ScrollView
            {
                FrontCover(book: currentBook, height: 300)
                    .padding(.vertical, 30)

                HStack {
                    Text("Title :")
                    Text(currentBook.title).lineLimit(1)
                    Spacer()
                }.padding()
                
                HStack {
                    Text("Author :")
                    Text(currentBook.author).lineLimit(1)
                    Spacer()
                }.padding()

                HStack {
                    Text("Language :")
                    Text(currentBook.language).lineLimit(1)
                    Spacer()
                }.padding()

                HStack {
                    Text("Page number :")
                    Text("\(currentBook.pageNumber)").lineLimit(1)
                    Spacer()
                }.padding()
                
                VStack {
                    Text("Description :")
                    Text(currentBook.description)
                }.padding()
                
                Spacer()
                
                Button(action: {
                    deleteAction()
                    presentationMode.wrappedValue.dismiss()
                }, label: {
                    Text("Delete")
                        .foregroundColor(Color.white)
                        .bold()
                        .padding()
                })
                .background(Color.red)
                .cornerRadius(10.0)
                
            }
        }
    }
}

struct BookDetailView_Previews: PreviewProvider {
    @StateObject static var bookLibrary = BookLibraryManager()
    
    static var previews: some View {
        BookDetailView(currentBook: bookLibrary.myBooks[0], deleteAction: {})
    }
}
