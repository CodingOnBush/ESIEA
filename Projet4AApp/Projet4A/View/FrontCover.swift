//
//  FrontCover.swift
//  Projet4A
//
//  Created by Sogekingu on 24/12/2020.
//

import SwiftUI
import KingfisherSwiftUI

struct FrontCover: View {
    var book: Book
    var height: Int
    
    var body: some View {
        ZStack{
            Rectangle()
                .fill(Color.red)
                .border(Color.gray, width: 10)
                .frame(width: CGFloat(Double(height)/1.5), height: CGFloat(height), alignment: .topLeading)
                .clipped()
            
            Text(book.title)
                .frame(width: CGFloat(Double(height-45)/1.5), height: CGFloat(height-30))
            
            KFImage(URL(string: book.frontCoverImageLink))
                .resizable()
                .scaledToFit()
                .frame(width: CGFloat(Double(height)/1.5), height: CGFloat(height), alignment: .topLeading)
                .clipped()
            
        }.cornerRadius(8.0)
    }
}

struct FrontCover_Previews: PreviewProvider {
    @StateObject static var bookLibrary = BookLibraryManager()
    
    static var previews: some View {
        FrontCover(book: bookLibrary.myBooks[0], height: 200)
        .previewLayout(.sizeThatFits)
        .padding()
    }
}
