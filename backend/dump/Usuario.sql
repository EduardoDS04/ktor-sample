-- Crear la tabla de Usuarios
CREATE TABLE Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(20) UNIQUE NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    token VARCHAR(255) DEFAULT ''  -- Token vacío por defecto
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Tabla de usuarios';

-- Insertar usuarios de ejemplo (passwords hasheadas con SHA-256)
INSERT INTO Usuario (dni, nombre, email, password, token) VALUES
('12345678A', 'Juan Perez', 'juan.perez@example.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', ''),  -- Contraseña: '123456' (hash)
('23456789B', 'Maria Lopez', 'maria.lopez@example.com', '2c6ee24b09816a6f14f95d1698b24ead', ''),  -- Contraseña: 'abcdef' (hash)
('34567890C', 'Carlos Garcia', 'carlos.garcia@example.com', '81dc9bdb52d04dc20036dbd8313ed055', ''),  -- Contraseña: '123123' (hash)
('45678901D', 'Ana Torres', 'ana.torres@example.com', '6d7fce9b0c9bcaa8f1d3c89fcd1f45f7', ''); -- Contraseña: 'password' (hash)
