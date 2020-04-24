SELECT * FROM member
WHERE EXISTS (
    SELECT * FROM checkout_item
    WHERE EXISTS (
        SELECT * FROM book
        WHERE book.id = checkout_item.book_id
            AND checkout_item.member_id = member.id
            AND book.title = "The Hobbit"
    )
);
