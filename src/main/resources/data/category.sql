-- Удаляем все текущие записи из таблицы categories
DELETE FROM categories;

-- Вставляем обновленные записи согласно вашему списку
INSERT INTO categories (id, name, image) VALUES
                                             (1, 'Games', 'https://th.bing.com/th/id/R.68bdc14b12eb891b8fedbbd862cf15b4?rik=6D5gDBOy6sLKbg&pid=ImgRaw&r=0'),
                                             (2, 'Electronics', 'https://m.media-amazon.com/images/I/31XIqfc70vL._SY300_SX300_QL70_ML2_.jpg'),
                                             (3, 'Computers', 'https://m.media-amazon.com/images/I/714sa4fUFNL._AC_UF894,1000_QL80_.jpg'),
                                             (4, 'Gadgets', 'https://m.media-amazon.com/images/I/71ZRus2YNcL._AC_UF894,1000_QL80_.jpg'),
                                             (5, 'Gaming Consoles', 'https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_141517940?x=1800&y=1800&format=jpg&quality=80&sp=yes&strip=yes&ex=1800&ey=1800&align=center&resizesource&unsharp=1.5x1+0.7+0.02'),
                                             (6, 'Audio', 'https://media.cnn.com/api/v1/images/stellar/prod/airpods-max.jpg?c=16x9&q=h_720,w_1280,c_fill'),
                                             (7, 'Computer Accessories', 'https://m.media-amazon.com/images/I/51KmxQjXBlL._AC_UF1000,1000_QL80_.jpg'),
                                             (8, 'Sales', 'https://clipart-library.com/img1/845447.jpg');  -- Замените пустую строку на URL изображения для категории Sales, если такой есть

-- Здесь вы можете добавить оставшиеся категории, которые у вас есть