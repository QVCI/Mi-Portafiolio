drop database if exists Anuncios;
create database Anuncios;
use Anuncios;
show databases;

CREATE TABLE Anuncios (
    IDMensaje INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    ESTACION NVARCHAR(30),
    CAUSA NVARCHAR(62),
    HORAPUBLICACION NVARCHAR(12),
    UID TINYINT
);
SELECT * FROM Anuncios;
