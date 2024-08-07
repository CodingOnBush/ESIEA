//
//  Book.swift
//  Projet4A
//
//  Created by Sogekingu on 30/12/2020.
//

import Foundation


struct Book: Identifiable {
    let id: String
    let title: String
    let author: String
    let frontCoverImageLink: String
    let description: String
    let language: String
    let pageNumber: String
    
    init(
        id: String,
        title: String,
        author: String,
        frontCoverImageLink: String,
        description: String,
        language: String,
        pageNumber: String
    ) {
        self.id = id
        self.title = title
        self.author = author
        self.frontCoverImageLink = frontCoverImageLink
        self.description = description
        self.language = language
        self.pageNumber = pageNumber
    }
    
    init(bookEntity: BookEntity) {
        self.id = bookEntity.id
        self.title = bookEntity.title
        self.author = bookEntity.author
        self.frontCoverImageLink = bookEntity.frontCoverImageLink
        self.description = bookEntity.myDescription
        self.language = bookEntity.myLanguage
        self.pageNumber = bookEntity.pageNumber
    }
}
