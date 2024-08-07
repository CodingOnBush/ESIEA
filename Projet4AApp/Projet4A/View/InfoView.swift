//
//  InfoView.swift
//  Projet4A
//
//  Created by Sogekingu on 30/12/2020.
//

import SwiftUI

struct InfoView: View {
    var body: some View {
        VStack {
            HStack{
                Text("Hi!")
                    .font(.system(size: 90))
                    .foregroundColor(Color.purple)
                    .bold()
                    .padding()
            }
            Spacer()
            
            HStack{
                Text("I'm SupaMustang,\nand I made this little app")
                    .font(.largeTitle)
                    .foregroundColor(Color.purple)
                    .padding()
                Spacer()
            }
            Spacer()
            
            Link(destination: URL(string: "https://linktr.ee/MostafaOmrane")!){
                Text("My social links")
                .fontWeight(.bold)
                .font(.title)
                .foregroundColor(.white)
                .padding(10)
                .background(Color.purple)
                .cornerRadius(18.0)
            }
            
            Spacer()
            
            Text("App realized\nwith 💜")
                .bold()
                .foregroundColor(.gray)
                .multilineTextAlignment(.center)
                .padding()
        }
    }
}

struct InfoView_Previews: PreviewProvider {
    static var previews: some View {
        InfoView()
    }
}
