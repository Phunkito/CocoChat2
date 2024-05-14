-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-05-2024 a las 02:27:11
-- Versión del servidor: 10.1.21-MariaDB
-- Versión de PHP: 7.0.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `cocobase`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `friends`
--

CREATE TABLE `friends` (
  `Id_friend` int(11) NOT NULL,
  `Id_user` int(11) NOT NULL,
  `Id_user2` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `groups`
--

CREATE TABLE `groups` (
  `Id_group` int(11) NOT NULL,
  `Id_admin` int(11) NOT NULL,
  `title` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `members`
--

CREATE TABLE `members` (
  `Id_members` int(11) NOT NULL,
  `Id_user` int(11) NOT NULL,
  `Id_group` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `message`
--

CREATE TABLE `message` (
  `Id_message` int(11) NOT NULL,
  `Id_user` int(11) NOT NULL,
  `text` varchar(500) NOT NULL,
  `Id_group` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `request`
--

CREATE TABLE `request` (
  `Id_request` int(11) NOT NULL,
  `Id_user_sended` int(11) NOT NULL,
  `Id_user_recieved` int(11) NOT NULL,
  `type_request` tinyint(1) NOT NULL,
  `Id_group` int(11) NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `Id_user` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `security_word` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `friends`
--
ALTER TABLE `friends`
  ADD PRIMARY KEY (`Id_friend`),
  ADD KEY `Id_user` (`Id_user`),
  ADD KEY `Id_user2` (`Id_user2`);

--
-- Indices de la tabla `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`Id_group`),
  ADD KEY `Id_admin` (`Id_admin`);

--
-- Indices de la tabla `members`
--
ALTER TABLE `members`
  ADD PRIMARY KEY (`Id_members`),
  ADD KEY `Id_user` (`Id_user`),
  ADD KEY `Id_group` (`Id_group`);

--
-- Indices de la tabla `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`Id_message`),
  ADD KEY `Id_user` (`Id_user`),
  ADD KEY `Id_group` (`Id_group`);

--
-- Indices de la tabla `request`
--
ALTER TABLE `request`
  ADD PRIMARY KEY (`Id_request`),
  ADD KEY `Id_user_sended` (`Id_user_sended`),
  ADD KEY `Id_user_recieved` (`Id_user_recieved`),
  ADD KEY `Id_group` (`Id_group`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`Id_user`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `friends`
--
ALTER TABLE `friends`
  MODIFY `Id_friend` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `groups`
--
ALTER TABLE `groups`
  MODIFY `Id_group` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `members`
--
ALTER TABLE `members`
  MODIFY `Id_members` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `message`
--
ALTER TABLE `message`
  MODIFY `Id_message` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `request`
--
ALTER TABLE `request`
  MODIFY `Id_request` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `Id_user` int(11) NOT NULL AUTO_INCREMENT;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `friends`
--
ALTER TABLE `friends`
  ADD CONSTRAINT `friends_ibfk_1` FOREIGN KEY (`Id_user`) REFERENCES `users` (`Id_user`),
  ADD CONSTRAINT `friends_ibfk_2` FOREIGN KEY (`Id_user2`) REFERENCES `users` (`Id_user`);

--
-- Filtros para la tabla `groups`
--
ALTER TABLE `groups`
  ADD CONSTRAINT `groups_ibfk_1` FOREIGN KEY (`Id_admin`) REFERENCES `users` (`Id_user`);

--
-- Filtros para la tabla `members`
--
ALTER TABLE `members`
  ADD CONSTRAINT `members_ibfk_1` FOREIGN KEY (`Id_user`) REFERENCES `users` (`Id_user`),
  ADD CONSTRAINT `members_ibfk_2` FOREIGN KEY (`Id_user`) REFERENCES `users` (`Id_user`),
  ADD CONSTRAINT `members_ibfk_3` FOREIGN KEY (`Id_group`) REFERENCES `groups` (`Id_group`);

--
-- Filtros para la tabla `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `message_ibfk_1` FOREIGN KEY (`Id_user`) REFERENCES `users` (`Id_user`),
  ADD CONSTRAINT `message_ibfk_2` FOREIGN KEY (`Id_group`) REFERENCES `groups` (`Id_group`);

--
-- Filtros para la tabla `request`
--
ALTER TABLE `request`
  ADD CONSTRAINT `request_ibfk_1` FOREIGN KEY (`Id_user_sended`) REFERENCES `users` (`Id_user`),
  ADD CONSTRAINT `request_ibfk_2` FOREIGN KEY (`Id_user_recieved`) REFERENCES `users` (`Id_user`),
  ADD CONSTRAINT `request_ibfk_3` FOREIGN KEY (`Id_group`) REFERENCES `groups` (`Id_group`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
