SELECT title, type FROM
(
    SELECT *, 'book' AS type FROM book
    UNION
    SELECT *, 'movie' AS type FROM movie
) AS rental
WHERE NOT EXISTS (
    SELECT * FROM checkout_item
    WHERE (checkout_item.book_id = rental.id AND rental.type = 'book')
        OR (checkout_item.movie_id = rental.id AND rental.type = 'movie')
);
