INSERT INTO member ("id", "name")
VALUES (43, "Terry Lu");

INSERT INTO book ("id", "title")
VALUES (11, "The Pragmatic Programmer");

INSERT INTO checkout_item ("member_id", "book_id")
VALUES (43, 11);

SELECT * FROM member
WHERE EXISTS (
    SELECT * FROM checkout_item
    WHERE EXISTS (
        SELECT * FROM book
        WHERE book.id = checkout_item.book_id
            AND checkout_item.member_id = member.id
            AND book.title = "The Pragmatic Programmer"
    )
);
