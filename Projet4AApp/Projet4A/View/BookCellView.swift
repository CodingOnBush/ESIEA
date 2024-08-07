//
//  BookCellView.swift
//  Projet4A
//
//  Created by Sogekingu on 20/12/2020.
//

import SwiftUI

struct BookCellView: View {
    let book: Book
    
    var body: some View {
        ZStack {
            RoundedRectangle(cornerRadius: 8, style: .continuous)
                .fill(Color.blue)
                .shadow(color: Color(Color.RGBColorSpace.sRGB, white: 0, opacity: 0.2), radius: 4)
                .frame(width: .none, height: CGFloat(170), alignment: .center)
                .opacity(0.6)
            
            VStack{
                HStack{
                    FrontCover(book: book, height: 100)
                    
                    VStack(alignment: .leading){
                        Group{
                            Text(book.title)
                                .font(.custom("BookTitle", size: 30.0))
                                .foregroundColor(Color.white)
                                .lineLimit(1)
                            Divider()
                            Text(book.author)
                                .font(.custom("Author", size: 20.0))
                                .foregroundColor(Color.white)
                                .lineLimit(1)
                        }
                    }
                }.padding(.top, 10)
                
                HStack {
                    Spacer()
                    Text("\(book.pageNumber) pages")
                        .foregroundColor(Color.white)
                        .font(.system(size: CGFloat(20)))
                        .padding(.bottom, 10)
                    Spacer()
                    Text("\(book.language.capitalized) ")
                        .foregroundColor(Color.white)
                        .font(.system(size: CGFloat(20)))
                        .padding(.bottom, 10)
                    Spacer()
                }
            }
            .padding(.horizontal, 10)
        }
    }
}

struct BookCellView_Previews: PreviewProvider {
    static var previews: some View {
        BookCellView(book: Book(id: "id", title: "Title", author: "Author", frontCoverImageLink: "link", description: "description", language: "fr", pageNumber: "23"))
            .previewLayout(.sizeThatFits)
            .padding()
    }
}
