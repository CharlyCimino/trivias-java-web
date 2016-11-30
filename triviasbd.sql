-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-06-2016 a las 01:50:16
-- Versión del servidor: 10.1.13-MariaDB
-- Versión de PHP: 5.6.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `triviasbd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `preguntas`
--

CREATE TABLE `preguntas` (
  `ID_Pregunta` int(11) NOT NULL,
  `ID_Trivia` int(11) NOT NULL,
  `Enunciado` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Area` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `Tema` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `Curso` varchar(10) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `preguntas`
--

INSERT INTO `preguntas` (`ID_Pregunta`, `ID_Trivia`, `Enunciado`, `Area`, `Tema`, `Curso`) VALUES
(1, 1, '¿Capital de Argentina?', 'Geografía', 'Capitales', 'A'),
(2, 1, '¿Capital de Brasil?', 'Geografía', 'Capitales', 'A'),
(3, 1, '¿Capital de Paraguay?', 'Geografía', 'Capitales', 'A'),
(4, 1, '¿Capital de Chile?', 'Geografía', 'Capitales', 'A'),
(5, 1, '¿Capital de Perú?', 'Geografía', 'Capitales', 'A'),
(6, 1, '¿Capital de Colombia?', 'Geografía', 'Capitales', 'A'),
(7, 2, '¿Qué equipo nunca descendió?', 'Deportes', 'Futbol', 'A'),
(8, 2, '¿Quién es el clásico de Racing?', 'Deportes', 'Futbol', 'A'),
(9, 2, '¿A qué club apodan "El Canalla"?', 'Deportes', 'Futbol', 'A'),
(10, 3, '20 + 15', 'Matemáticas', 'Operaciones', 'A'),
(11, 3, '1 + 1', 'Matemáticas', 'Operaciones', 'A'),
(12, 3, '15 + 18', 'Matemáticas', 'Operaciones', 'A'),
(13, 3, '100 + 102', 'Matemáticas', 'Operaciones', 'A');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `puntajes`
--

CREATE TABLE `puntajes` (
  `ID_Puntaje` int(11) NOT NULL,
  `ID_Usuario` int(11) NOT NULL,
  `ID_Trivia` int(11) NOT NULL,
  `Puntaje` double NOT NULL,
  `Fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `respuestas`
--

CREATE TABLE `respuestas` (
  `ID_Respuesta` int(11) NOT NULL,
  `ID_Pregunta` int(11) NOT NULL,
  `Contestacion` varchar(30) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `respuestas`
--

INSERT INTO `respuestas` (`ID_Respuesta`, `ID_Pregunta`, `Contestacion`) VALUES
(1, 1, 'Buenos Aires'),
(2, 1, 'Bariloche'),
(3, 1, 'Rosario'),
(4, 2, 'Brasilia'),
(5, 2, 'Rio De Janeiro'),
(6, 2, 'San Pablo'),
(7, 2, 'Porto Alegre'),
(8, 3, 'Asunción'),
(9, 3, 'Luque'),
(10, 3, 'Encarnación'),
(11, 4, 'Santiago'),
(12, 4, 'Viña del Mar'),
(13, 4, 'Valparaíso'),
(14, 4, 'Concepción'),
(15, 5, 'Lima'),
(16, 5, 'Arequipa'),
(17, 5, 'Cuzco'),
(18, 5, 'Trujillo'),
(19, 6, 'Bogotá'),
(20, 6, 'Cali'),
(21, 6, 'Medellín'),
(22, 7, 'Boca Juniors'),
(23, 7, 'RiBer Plate'),
(24, 7, 'Indesingente'),
(25, 8, 'Independiente'),
(26, 8, 'Huracán'),
(27, 8, 'San Lorenzo'),
(28, 9, 'Rosario Central'),
(29, 9, 'Newell''s Old Boys'),
(30, 9, 'Atlético de Rafaela'),
(31, 9, 'Colón de Santa Fe'),
(32, 10, '35'),
(33, 10, '53'),
(34, 10, '-14'),
(35, 10, '98'),
(36, 11, '2'),
(37, 11, 'No sé, soy re burro e.e'),
(38, 12, '33'),
(39, 12, '44'),
(40, 12, '22'),
(41, 13, '202'),
(42, 13, '201'),
(43, 13, '200'),
(44, 13, '203');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trivias`
--

CREATE TABLE `trivias` (
  `ID_Trivia` int(11) NOT NULL,
  `Nombre` varchar(30) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `trivias`
--

INSERT INTO `trivias` (`ID_Trivia`, `Nombre`) VALUES
(1, 'Capitales de Sudamérica'),
(2, 'Equipos de futbol'),
(3, 'Sumas fáciles');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `ID_usuario` int(11) NOT NULL,
  `Nombre` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `Password` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `Tipo` varchar(10) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`ID_usuario`, `Nombre`, `Password`, `Tipo`) VALUES
(1, 'cimino', 'charly', 'alumno'),
(2, 'saracho', 'agustin', 'alumno'),
(3, 'kuhn', 'monica', 'profesor');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `preguntas`
--
ALTER TABLE `preguntas`
  ADD PRIMARY KEY (`ID_Pregunta`);

--
-- Indices de la tabla `puntajes`
--
ALTER TABLE `puntajes`
  ADD PRIMARY KEY (`ID_Puntaje`);

--
-- Indices de la tabla `respuestas`
--
ALTER TABLE `respuestas`
  ADD PRIMARY KEY (`ID_Respuesta`);

--
-- Indices de la tabla `trivias`
--
ALTER TABLE `trivias`
  ADD PRIMARY KEY (`ID_Trivia`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`ID_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `preguntas`
--
ALTER TABLE `preguntas`
  MODIFY `ID_Pregunta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT de la tabla `puntajes`
--
ALTER TABLE `puntajes`
  MODIFY `ID_Puntaje` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `respuestas`
--
ALTER TABLE `respuestas`
  MODIFY `ID_Respuesta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;
--
-- AUTO_INCREMENT de la tabla `trivias`
--
ALTER TABLE `trivias`
  MODIFY `ID_Trivia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `ID_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
