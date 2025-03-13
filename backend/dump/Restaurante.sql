CREATE TABLE Restaurante (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) UNIQUE NOT NULL,
    comida VARCHAR(255),
    tiempoEntrega VARCHAR(10),
    cantidad INT,
    precio DECIMAL(10,2),
    imagen VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='tabla de restaurantes';

INSERT INTO Restaurante (nombre, comida, tiempoEntrega, cantidad, precio, imagen) VALUES
('GialucaPizza', 'Pizza Familiar', '30 min', 1, 15.99, 'https://upload.wikimedia.org/wikipedia/commons/f/f6/Eataly_Las_Vegas_-_Feb_2019_-_Sarah_Stierch_12.jpg'),
('MegaBurger', 'Doble Cheese', '25 min', 2, 8.55, 'https://s7d1.scene7.com/is/image/mcdonalds/DC_202405_25137_5dollar_McDouble_MealDeal_1564x1564-2:nutrition-calculator-tile'),
('Kebab Natural', 'Durum XL', '10 min', 3, 17.50, 'https://i1.wp.com/intoleranciasmil.com/wp-content/uploads/QUEBAB17-01-e1573070142729-1024x743.jpeg?resize=775%2C562&ssl=1'),
('Gran Asador', 'Codillo de Cerdo', '40 min', 4, 128.99, 'https://i.ytimg.com/vi/Y9v9aaXjYgY/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLD-sP5LqvRafOQlCcp3lTC4BUtXzA'),
('Pizzeria Orlando', 'pizza 4 quesos', '25 min', 4, 42.28, 'https://lh5.googleusercontent.com/p/AF1QipMxJdEgjnVpAgvRHbmsH1NiaO8oC4C_D1oaeH08');