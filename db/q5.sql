SELECT * FROM member
WHERE EXISTS (
    SELECT * FROM checkout_item
    WHERE checkout_item.member_id = member.id
    GROUP BY checkout_item.member_id
    HAVING COUNT(checkout_item.book_id) > 1 OR COUNT(checkout_item.movie_id) > 1
);
