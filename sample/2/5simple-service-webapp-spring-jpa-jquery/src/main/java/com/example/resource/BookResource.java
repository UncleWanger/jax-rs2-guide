package com.example.resource;

import com.example.domain.Book;
import com.example.domain.Books;
import com.example.service.BookService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * <p>BookResource class.</p>
 *
 * @author hanl
 * @version $Id: $Id
 */
@Path("books")
public class BookResource {
    private static final Logger LOGGER = Logger.getLogger(BookResource.class);
    @Autowired
    private BookService bookService;

    /**
     * <p>getBooks.</p>
     *
     * @return a {@link com.example.domain.Books} object.
     */
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Books getBooks() {
        final Books books = bookService.getBooks();
        BookResource.LOGGER.debug(books);
        return books;
    }

    /**
     * <p>getBookByPath.</p>
     *
     * @param bookId a {@link java.lang.Integer} object.
     * @return a {@link com.example.domain.Book} object.
     */
    @Path("{bookId:[0-9]*}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Book getBookByPath(@PathParam("bookId") final Long bookId) {
        final Book book = bookService.getBook(bookId);
        BookResource.LOGGER.debug(book);
        return book;
    }


    /**
     * this is a git test
     * @param abookId
     * @return
     */
    @Path("{bookId:[a-z]*}/{abookId:[0-9]*}")
    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Book getBookPath(@PathParam("abookId") final Long abookId) {
        final Book book = bookService.getBook(abookId);
        BookResource.LOGGER.debug(book);
        return book;

    }

    /**
     * <p>getBookByQuery.</p>
     *
     * @param bookId a {@link java.lang.Integer} object.
     * @return a {@link com.example.domain.Book} object.
     */
    @Path("/book")
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Book getBookByQuery(@QueryParam("id") final Long bookId) {
        final Book book = bookService.getBook(bookId);
        BookResource.LOGGER.debug(book);
        return book;
    }

    /**
     * <p>saveBook.</p>
     *
     * @param book a {@link com.example.domain.Book} object.
     * @return a {@link com.example.domain.Book} object.
     */
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
    public Book saveBook(final Book book) {
        return bookService.saveBook(book);
    }

    /**
     * <p>updateBook.</p>
     *
     * @param bookId a {@link java.lang.Integer} object.
     * @param book a {@link com.example.domain.Book} object.
     * @return a {@link com.example.domain.Book} object.
     */
    @Path("{bookId:[0-9]*}")
    @PUT
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
    public Book updateBook(@PathParam("bookId") final Long bookId, final Book book) {
        if (book == null) {
            return null;
        }
        return bookService.updateBook(bookId, book);
    }

    /**
     * <p>deleteBook.</p>
     *
     * @param bookId a {@link java.lang.Integer} object.
     * @return a {@link java.lang.String} object.
     */
    @Path("/{bookId:[0-9]*}")
    @DELETE
    public String deleteBook(@PathParam("bookId") final Long bookId) {
        if (bookService.deleteBook(bookId)) {
            return "Deleted book id=" + bookId;
        } else {
            return "Deleted book failed id=" + bookId;
        }
    }
}
