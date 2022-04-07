create table shopping_cart (
                          id          bigserial primary key,
                          product_id  bigint,
                          CONSTRAINT fk_product
                              FOREIGN KEY(product_id)
                                  REFERENCES products(id)
);

insert into shopping_cart (product_id) values
                                        (1);