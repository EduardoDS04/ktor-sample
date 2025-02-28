package com.data.inmemory.models

import com.domain.models.Restaurante

object RestauranteData {
    val listRestaurante = mutableListOf(
        Restaurante(
            id = 1,
            nombre = "GialucaPizza",
            comida = "Pizza Familiar",
            tiempoEntrega = "30 min",
            cantidad = 1,
            precio = 15.99,
            imagen = "https://upload.wikimedia.org/wikipedia/commons/f/f6/Eataly_Las_Vegas_-_Feb_2019_-_Sarah_Stierch_12.jpg"
        ),
        Restaurante(
            id = 2,
            nombre = "MegaBurger",
            comida = "Doble Cheese",
            tiempoEntrega = "25 min",
            cantidad = 2,
            precio = 8.55,
            imagen = "https://s7d1.scene7.com/is/image/mcdonalds/DC_202405_25137_5dollar_McDouble_MealDeal_1564x1564-2:nutrition-calculator-tile"
        ),
        Restaurante(
            id = 3,
            nombre = "Pasta Andalucia",
            comida = "Macarrones queso",
            tiempoEntrega = "20 min",
            cantidad = 5,
            precio = 60.0,
            imagen = "https://www.vvsupremo.com/wp-content/uploads/2015/11/Macarrones-con-Queso-1.jpg"
        ),
        Restaurante(
            id = 4,
            nombre = "Kebab Natural",
            comida = "Durum XL",
            tiempoEntrega = "10 min",
            cantidad = 3,
            precio = 17.50,
            imagen = "https://i1.wp.com/intoleranciasmil.com/wp-content/uploads/QUEBAB17-01-e1573070142729-1024x743.jpeg?resize=775%2C562&ssl=1"
        ),
        Restaurante(
            id = 5,
            nombre = "Gran Asador",
            comida = "Codillo de Cerdo",
            tiempoEntrega = "40 min",
            cantidad = 4,
            precio = 128.99,
            imagen = "https://i.ytimg.com/vi/Y9v9aaXjYgY/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLD-sP5LqvRafOQlCcp3lTC4BUtXzA"
        ),
        Restaurante(
            id = 6,
            nombre = "Pizzeria Orlando",
            comida = "pizza 4 quesos",
            tiempoEntrega = "25 min",
            cantidad = 4,
            precio = 42.28,
            imagen = "https://lh5.googleusercontent.com/p/AF1QipMxJdEgjnVpAgvRHbmsH1NiaO8oC4C_D1oaeH08"
        )
    )
}