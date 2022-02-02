-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-05-2020 a las 19:58:11
-- Versión del servidor: 10.4.6-MariaDB
-- Versión de PHP: 7.3.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `alpes_eventos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asistencia_invitado`
--

CREATE TABLE `asistencia_invitado` (
  `idAsistencia_Invitado` int(11) NOT NULL,
  `folio` varchar(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `genero` varchar(45) NOT NULL,
  `grupo` varchar(45) NOT NULL,
  `nota` text NOT NULL,
  `invitacion` varchar(45) NOT NULL,
  `relacion` varchar(45) NOT NULL,
  `mesa` varchar(45) NOT NULL,
  `telcel` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `asistencia` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `buzon`
--

CREATE TABLE `buzon` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `asunto` varchar(50) NOT NULL,
  `mensaje` text NOT NULL,
  `fecha` date NOT NULL,
  `leido` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `buzon`
--

INSERT INTO `buzon` (`id`, `nombre`, `asunto`, `mensaje`, `fecha`, `leido`) VALUES
(1, 'CARLOS DANIEL ANGEL PADILLA', 'QUEJA', 'EL SISTEMA FALLA EN CADA VENTANA', '2020-04-15', 1),
(2, 'CARLOS DANIEL ANGEL PADILLA', 'REPORTE DE FALLOS', 'EL SISTEMA SE CUELGA DESPUES DE MULTIPLES VENTANAS', '2020-04-26', 1),
(3, 'YESENIA DIAZ HERNANDEZ', 'FALLA SECUENCIA', 'ESTO NO FUNCIONA POR ALGUNA ESTUPIDA RAZON', '2020-04-27', 1),
(4, 'YESENIA DIAZ HERNANDEZ', 'FALLA AL PASAR LISTA', 'SOLICITO POR FAVOR LA DEVOLUCION DE LA LISTA', '2020-04-27', 1),
(5, 'YESENIA DIAZ HERNANDEZ', 'PROBAR EL CORREO', 'HOLA BUENOS DIAS RESULTA QUE ESTA ES UNA PRUEBA', '2020-04-29', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `eventos`
--

CREATE TABLE `eventos` (
  `idEventos` int(11) NOT NULL,
  `folio` varchar(11) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `cliente` varchar(45) NOT NULL,
  `no_invitado` varchar(45) NOT NULL,
  `mesas` varchar(25) NOT NULL,
  `fecha` varchar(45) NOT NULL,
  `hora` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagos`
--

CREATE TABLE `pagos` (
  `idPagos` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `cantidad` varchar(45) NOT NULL,
  `estado` varchar(45) NOT NULL,
  `expiracion` varchar(45) NOT NULL,
  `folio` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `pagos`
--

INSERT INTO `pagos` (`idPagos`, `nombre`, `cantidad`, `estado`, `expiracion`, `folio`) VALUES
(17, 'las flores', '300', 'Pagado', '12-04-2020', '00000002'),
(18, 'las flores ', '400', 'Pendiente', '24-04-2020', '00000002');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `presupuesto`
--

CREATE TABLE `presupuesto` (
  `idPresupuesto` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `categoria` varchar(45) DEFAULT NULL,
  `nota` varchar(45) DEFAULT NULL,
  `estimada` varchar(45) DEFAULT NULL,
  `folio` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `idProveedores` int(11) NOT NULL,
  `folio` varchar(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `categoria` varchar(45) NOT NULL,
  `nota` varchar(45) NOT NULL,
  `estimada` varchar(45) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `sitioweb` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `proveedores`
--

INSERT INTO `proveedores` (`idProveedores`, `folio`, `nombre`, `categoria`, `nota`, `estimada`, `telefono`, `correo`, `sitioweb`, `direccion`) VALUES
(3, '00000001', 'yo', 'Accesorios', 'lo', '834', '3843247', 'jsdfsdb', 'fsdsfh', 'dbksd');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `subtareas`
--

CREATE TABLE `subtareas` (
  `idSubtareas` int(11) NOT NULL,
  `folio` varchar(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `nota` varchar(45) NOT NULL,
  `estado` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tablero`
--

CREATE TABLE `tablero` (
  `idTablero` int(11) NOT NULL,
  `semilla` varchar(45) DEFAULT NULL,
  `pendientes` varchar(45) DEFAULT NULL,
  `completadas` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tareas`
--

CREATE TABLE `tareas` (
  `idtareas` int(11) NOT NULL,
  `folio` varchar(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `categoria` varchar(45) NOT NULL,
  `nota` varchar(45) NOT NULL,
  `estado` varchar(45) NOT NULL,
  `fecha` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `personal` varchar(45) NOT NULL,
  `fechanac` varchar(45) NOT NULL,
  `usuario` varchar(45) NOT NULL,
  `contrasena` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idUsuario`, `nombre`, `personal`, `fechanac`, `usuario`, `contrasena`) VALUES
(5, 'CARLOS DANIEL ANGEL PADILLA', 'Administrador', '24-04-1998', 'ADMIN', 'ADMIN'),
(7, 'YESENIA DIAZ HERNANDEZ', 'Cliente', '19-08-1997', 'yesi', 'yesi'),
(8, 'Admin', 'Administrador', '24-04-1998', 'admin', 'admin');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `asistencia_invitado`
--
ALTER TABLE `asistencia_invitado`
  ADD PRIMARY KEY (`idAsistencia_Invitado`);

--
-- Indices de la tabla `buzon`
--
ALTER TABLE `buzon`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `eventos`
--
ALTER TABLE `eventos`
  ADD PRIMARY KEY (`idEventos`);

--
-- Indices de la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD PRIMARY KEY (`idPagos`);

--
-- Indices de la tabla `presupuesto`
--
ALTER TABLE `presupuesto`
  ADD PRIMARY KEY (`idPresupuesto`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`idProveedores`);

--
-- Indices de la tabla `subtareas`
--
ALTER TABLE `subtareas`
  ADD PRIMARY KEY (`idSubtareas`);

--
-- Indices de la tabla `tablero`
--
ALTER TABLE `tablero`
  ADD PRIMARY KEY (`idTablero`);

--
-- Indices de la tabla `tareas`
--
ALTER TABLE `tareas`
  ADD PRIMARY KEY (`idtareas`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idUsuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `asistencia_invitado`
--
ALTER TABLE `asistencia_invitado`
  MODIFY `idAsistencia_Invitado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `buzon`
--
ALTER TABLE `buzon`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `eventos`
--
ALTER TABLE `eventos`
  MODIFY `idEventos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `pagos`
--
ALTER TABLE `pagos`
  MODIFY `idPagos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `presupuesto`
--
ALTER TABLE `presupuesto`
  MODIFY `idPresupuesto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `idProveedores` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `subtareas`
--
ALTER TABLE `subtareas`
  MODIFY `idSubtareas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `tablero`
--
ALTER TABLE `tablero`
  MODIFY `idTablero` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tareas`
--
ALTER TABLE `tareas`
  MODIFY `idtareas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
