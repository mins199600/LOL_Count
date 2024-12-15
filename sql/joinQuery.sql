SELECT
    c.crafted_id,
    c.crafted_name_ko,
    m.material_id
FROM
    crafted_item_name_ko c
        JOIN
    crafted_material_mapping m ON c.crafted_id = m.crafted_id;