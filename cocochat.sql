-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-05-2024 a las 14:42:38
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `cocochat`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `security_word` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `username`, `password`, `security_word`) VALUES
(1, 'usuario_prueba', 'contrase?a_prueba', NULL),
(2, 'penillamylove', 'sexo', NULL),
(3, 'penilla', '1234', NULL),
(4, 'penillas', '12345678', NULL),
(5, 'penillasexo', '17756315ebd47b7110359fc7b168179bf6f2df3646fcc888bc8aa05c78b38ac1', NULL),
(6, 'penillalove', '15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225', '59195c6c541c8307f1da2d1e768d6f2280c984df217ad5f4c64c3542b04111a4'),
(7, 'anette', '17756315ebd47b7110359fc7b168179bf6f2df3646fcc888bc8aa05c78b38ac1', '17756315ebd47b7110359fc7b168179bf6f2df3646fcc888bc8aa05c78b38ac1'),
(8, 'emma', '15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225', '5667ef73dac709effcfdc518a98ea9238365ec62b0eb5f478392e28818a2efd5'),
(9, 'emmas', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', '18ac3e7343f016890c510e93f935261169d9e3f565436429830faf0934f4f8e4'),
(10, 'a', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', 'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb'),
(11, 'as', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', 'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb'),
(12, 'antonio', 'jUeN3lT30JKOUJSsLbeXUkCYLHZYFAUce8KBWB9Xu1VX4jUuGObAxDCMKvbZl/Ac', '3Df3b05ClsIr3GjNIyLjh49uo11AdO17kBo35R3GbVPxluzeUbqHi+gaz8AABaQs');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
