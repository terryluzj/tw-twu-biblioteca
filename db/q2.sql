SELECT * FROM member
WHERE NOT EXISTS (
    SELECT * FROM checkout_item
    WHERE checkout_item.member_id = member.id
);
