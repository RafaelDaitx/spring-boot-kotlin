package br.com.erudio.unittests.mapper.mocks

import java.util.ArrayList
import br.com.erudio.data.vo.v2.BookVO
import br.com.erudio.model.Book

class MockBook {
    fun mockEntityList(): ArrayList<Book> {
        val books: ArrayList<Book> = ArrayList<Book>()
        for (i in 0..13) {
            books.add(mockEntity(i))
        }
        return books
    }

    fun mockVOList(): ArrayList<BookVO> {
        val books: ArrayList<BookVO> = ArrayList()
        for (i in 0..13) {
            books.add(mockVO(i))
        }
        return books
    }

    //Mockando por inferencia pasadno a instancia
    fun mockEntity(number: Int) = Book (
        id = number.toLong(),
        price = 25.0,
        author = "Author Test$number",
        title = "title Test$number"
    )

    fun mockVO(number: Int) = BookVO (
            key = number.toLong(),
            price = 25.0,
            author = "Author Test$number",
            title = "title Test$number"
    )

}