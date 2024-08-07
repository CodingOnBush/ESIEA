//
//  ButtonAddBookView.swift
//  Projet4A
//
//  Created by Sogekingu on 24/12/2020.
//

import SwiftUI

struct AddBookButtonView: View {
    var size: CGFloat
    let action: () -> Void
    
    var body: some View {
        Button(action: action, label: {
            Spacer()
            
            Text("Add Book")
                .font(.system(size: size))
                .foregroundColor(Color.white)
                .fontWeight(.bold)
                .padding()
            
            Spacer()
        })
            .background(Color.pink)
            .opacity(0.8)
            .cornerRadius(10.0)
            .shadow(radius: 10)
    }
}

struct AddBookButtonView_Previews: PreviewProvider {
    static var previews: some View {
        AddBookButtonView(size: 20, action: {})
            .previewLayout(.sizeThatFits)
            .padding()
    }
}

