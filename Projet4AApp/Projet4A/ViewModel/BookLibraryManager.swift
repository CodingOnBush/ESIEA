//
//  Books.swift
//  Projet4A
//
//  Created by Sogekingu on 20/12/2020.
//

import Foundation
import SwiftyJSON


class BookLibraryManager: ObservableObject {
    @Published var booksResult = [Book]()
    @Published var myBooks = [Book]()
    let urlString = "https://www.googleapis.com/books/v1/volumes?q="
    
    func removeFromMyBooks(id: String) {
        myBooks = myBooks.filter{$0.id != id}
    }
    
    func addToMyBooks(book: Book) {
        myBooks.append(book)
    }
    
    func research(research: String) {
        var currentUrlString = self.urlString
        var myResearch = research
        
        myResearch = myResearch.folding(options: .diacriticInsensitive, locale: .current)
        myResearch = myResearch.replacingOccurrences(of: "’", with: " ")
        myResearch = myResearch.replacingOccurrences(of: " ", with: "&")
        
        currentUrlString.append(myResearch)

        self.booksResult = loadData(urlString: currentUrlString)
    }
    
    func loadData(urlString: String) -> [Book] {
        let limitBookNumber = 30
        var count = 0
        var booksResult = [Book]()

        URLSession(configuration: .default).dataTask(with: URL(string: urlString)!) { (data, _, err) in
            if err != nil {
                print("ERREUR : \((err?.localizedDescription)!)")
                return
            }
            
            let json = try! JSON(data: data!)
            let items = json["items"].array!
            
            DispatchQueue.main.async {
                for i in items {
                    count += 1
                    
                    let id = i["id"].stringValue
                    
                    let title = i["volumeInfo"]["title"].stringValue
                    print(title)
                    
                    //Add author or authors
                    var author = ""
                    if i["volumeInfo"]["authors"].array != nil {
                        let authors = i["volumeInfo"]["authors"].array!
                        for j in authors {
                            author += "\(j.stringValue)"
                        }
                    }else {
                        author = "Author unknown"
                    }
                    
                    let description = i["volumeInfo"]["description"].stringValue
                    let imurl = i["volumeInfo"]["imageLinks"]["thumbnail"].stringValue
                    let language = i["volumeInfo"]["language"].stringValue
                    let pageNumber = i["volumeInfo"]["pageCount"].stringValue
                    
                    booksResult
                        .append(
                            Book(
                                id: id,
                                title: title,
                                author: author,
                                frontCoverImageLink: imurl,
                                description: description,
                                language: language,
                                pageNumber: pageNumber
                            )
                        )
                    
                    // limit number of books loaded
                    if count >= limitBookNumber {
                        count = 0
                        break
                    }
                }
            }
        }.resume()
        
        return booksResult
    }
}
