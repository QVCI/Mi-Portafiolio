import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NodosPrueba {
    private static final double INF = Double.POSITIVE_INFINITY;
    private static List<Nodos> nodosUsados = new ArrayList<>();
    private static int nodoFinal; // Variable para almacenar el nodo final
  
    public static void main(String[] args) {
        
        List<Nodos> nodos = new ArrayList<>();
        int numVertices = 0;

        try {
            BufferedReader lector = new BufferedReader(new FileReader("distancias.txt"));
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split("\t");
                if (partes.length == 3) {
                    int nodo1 = Integer.parseInt(partes[0]);
                    int nodo2 = Integer.parseInt(partes[1]);
                    double distancia = Double.parseDouble(partes[2]);
                    nodos.add(new Nodos(nodo1, nodo2, distancia));
                    numVertices = Math.max(numVertices, Math.max(nodo1, nodo2) + 1);
                }
            }
            lector.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int nodoInicio = 1;                                        
        nodoFinal = 490; // Almacenar el nodo final
        Nodos(nodos, nodoInicio, nodoFinal, numVertices);
    }

    public static void Nodos(List<Nodos> nodos, int inicio, int fin, int numvertices) {
        double[] distancia = new double[numvertices];
        boolean[] calculado = new boolean[numvertices];
        Arrays.fill(distancia, INF);
        distancia[inicio] = 0.0;

        while (!calculado[fin]) {
            
            int mindis = distanciaminima(distancia, calculado);
            calculado[mindis] = true;
              System.out.println("Explorando nodo: " + mindis);
        System.out.println("Distancia actual: " + distancia[mindis]);

            if (mindis == fin) {
        break;
    }

            for (Nodos nodo : nodos) {
                int nodo1 = nodo.nodo1;
                int nodo2 = nodo.nodo2;
                double pesonodo = nodo.peso;

                if (!calculado[nodo2] && nodo1 == mindis && distancia[mindis] != INF) {
                    double nuevadistancia = distancia[mindis] + pesonodo;
                    if (nuevadistancia < distancia[nodo2]) {
                        distancia[nodo2] = nuevadistancia;
                    }
                }
            }
        }

        List<Nodos> caminoMasCorto = new ArrayList<>();
        int nodoActual = fin;
        while (nodoActual != inicio) {
            int nodoAnterior = -1;
            for (Nodos nodo : nodos) {
                if (nodo.nodo2 == nodoActual && distancia[nodo.nodo1] + nodo.peso == distancia[nodo.nodo2]) {
                    caminoMasCorto.add(nodo);
                    nodoAnterior = nodo.nodo1;
                    break;
                }
            }
            nodoActual = nodoAnterior;
        }

        Collections.reverse(caminoMasCorto);
        nodosUsados.addAll(caminoMasCorto);

        muestraresultado(distancia, fin);
        muestraNodosUsados();

        int total = calcularPrecio(nodosUsados);
        System.out.println("Total: $" + total + " MXN.");
      
    }

    private static int distanciaminima(double[] distancia, boolean[] calculado) {
        double distanciaminima = INF;
        int minIndex = -1;
        for (int i = 0; i < distancia.length; i++) {
            if (!calculado[i] && distancia[i] < distanciaminima) {
                distanciaminima = distancia[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
    private static void muestraresultado(double[] distancia, int fin) {
        
        System.out.println("La distancia aproximada es: " + distancia[fin] + " metros");
    }

    private static void muestraNodosUsados() {
        System.out.println("Estaciones:");
        ArrayList<String> estaciones = new ArrayList<>();
        estaciones.add(0, "Observatorio");
        estaciones.add(1, "Tacubaya");
        estaciones.add(2, "Juanacatlán");
        estaciones.add(3, "Chapultepec");
        estaciones.add(4, "Sevilla");
        estaciones.add(5, "Insurgentes");
        estaciones.add(6, "Cuauhtémoc");
        estaciones.add(7, "Balderas ");
        estaciones.add(8, "Salto del Agua ");
        estaciones.add(9, "Isabel la Católica ");
        estaciones.add(10, "Pino Suárez ");
        estaciones.add(11, "Merced ");
        estaciones.add(12, "Candelaria ");
        estaciones.add(13, "San Lázaro");
        estaciones.add(14, "Moctezuma ");
        estaciones.add(15, "Balbuela");
        estaciones.add(16, "Boulevard Puerto Aéreo ");
        estaciones.add(17, "Gómez Farias");
        estaciones.add(18, "Zaragoza ");
        estaciones.add(19, "Pantitlán ");
        //L2
        estaciones.add(20, "Cuatro Caminos ");
        estaciones.add(21, "Panteones");
        estaciones.add(22, "Tacuba ");
        estaciones.add(23, "Cuitláhuac");
        estaciones.add(24, "Popotla ");
        estaciones.add(25, "Colegio Militar ");
        estaciones.add(26, "Normal ");
        estaciones.add(27, "San Cosme ");
        estaciones.add(28, "Revolucion ");
        estaciones.add(29, "Hidalgo");
        estaciones.add(30, "Bellas Artes");
        estaciones.add(31, "Allende");
        estaciones.add(32, "Zócalo");
        estaciones.add(33, "Pino Suárez ");
        estaciones.add(34, "San Antonio Abad");
        estaciones.add(35, "Chabacano");
        estaciones.add(36, "Viaducto ");
        estaciones.add(37, "Xola");
        estaciones.add(38, "Villa de Cortes");
        estaciones.add(39, "Nativitas");
        estaciones.add(40, "Portales");
        estaciones.add(41, "Ermita");
        estaciones.add(42, "General Anaya");
        estaciones.add(43, "Tasqueña");
        //L3
        estaciones.add(44, "Indios Verdes ");
        estaciones.add(45, "Deportivo 18 de Marzo");
        estaciones.add(46, "Potrero");
        estaciones.add(47, "La Raza");
        estaciones.add(48, "Tlatelolco");
        estaciones.add(49, "Guerrero");
        estaciones.add(50, "Hidalgo");
        estaciones.add(51, "Juárez");
        estaciones.add(52, "Balderas ");
        estaciones.add(53, "Niños Héroes/ Poder Judicial CDMX");
        estaciones.add(54, "Hospital General");
        estaciones.add(55, "Centro Médico");
        estaciones.add(56, "Etiopía");
        estaciones.add(57, "Eugenia ");
        estaciones.add(58, "División del Norte ");
        estaciones.add(59, "Zapata");
        estaciones.add(60, "Coyoacán ");
        estaciones.add(61, "Viveros / Derechos Humanos");
        estaciones.add(62, "Miguel Ángel De Quevedo");
        estaciones.add(63, "Copilco");
        estaciones.add(64, "Universidad");
        //L4
        estaciones.add(65, "Martín Carrera");
        estaciones.add(66, "Talismán");
        estaciones.add(67, "Bondojito");
        estaciones.add(68, "Consulado");
        estaciones.add(69, "Canal del Norte");
        estaciones.add(70, "Morelos");
        estaciones.add(71, "Candelaria");
        estaciones.add(72, "Fray Servando");
        estaciones.add(73, "Jamaica");
        estaciones.add(74, "Santa Anita");
        //L5
        estaciones.add(75, "Pantitlán ");
        estaciones.add(76, "Hangares");
        estaciones.add(77, "Terminal Aérea ");
        estaciones.add(78, "Oceanía ");
        estaciones.add(79, "Aragón ");
        estaciones.add(80, "Eduardo Molina ");
        estaciones.add(81, "Consulado");
        estaciones.add(82, "Valle Gómez ");
        estaciones.add(83, "Misterios");
        estaciones.add(84, "La Raza");
        estaciones.add(85, "Autobuses del Norte");
        estaciones.add(86, "Instituto del Petróleo ");
        estaciones.add(87, "Politécnico ");
        //L6
        estaciones.add(88, "El Rosario ");
        estaciones.add(89, "Tezozómoc ");
        estaciones.add(90, "UAM Azcapotzalco");
        estaciones.add(91, "Ferrería / Arena CDMX");
        estaciones.add(92, "Norte 45");
        estaciones.add(93, "Vallejo");
        estaciones.add(94, "Instituto del Petróleo ");
        estaciones.add(95, "Lindavista ");
        estaciones.add(96, "Deportivo 18 de marzo");
        estaciones.add(97, "La Villa / Basílica ");
        estaciones.add(98, "Martín Carrera");
        //L7
        estaciones.add(99, "El Rosario ");
        estaciones.add(100, "Aquiles Serdán");
        estaciones.add(101, "Camarones");
        estaciones.add(102, "Refinería ");
        estaciones.add(103, "Tacuba");
        estaciones.add(104, "San Joaquín ");
        estaciones.add(105, "Polanco");
        estaciones.add(106, "Auditorio");
        estaciones.add(107, "Constituyentes");
        estaciones.add(108, "Tacubaya");
        estaciones.add(109, "San Pedro de los Pinos");
        estaciones.add(110, "San Antonio ");
        estaciones.add(111, "Mixcoac ");
        estaciones.add(112, "Barranca del Muerto");
        //L8
        estaciones.add(113, "Garibaldi / Lagunilla");
        estaciones.add(114, "Bellas Artes");
        estaciones.add(115, "San Juan de Letrán ");
        estaciones.add(116, "Salto del Agua");
        estaciones.add(117, "Doctores");
        estaciones.add(118, "Obrera ");
        estaciones.add(119, "Chabacano");
        estaciones.add(120, "La viga");
        estaciones.add(121, "Santa Anita");
        estaciones.add(122, "Coyuya");
        estaciones.add(123, "Iztacalco");
        estaciones.add(124, "Apatlaco");
        estaciones.add(125, "Aculco");
        estaciones.add(126, "Escuadrón 201");
        estaciones.add(127, "Atlalilco");
        estaciones.add(128, "Iztapalapa");
        estaciones.add(129, "Cerro de la estrella");
        estaciones.add(130, "UAM-I");
        estaciones.add(131, "Constitución de 1917");
        //L9
        estaciones.add(132, "Tacubaya");
        estaciones.add(133, "Patriotismo");
        estaciones.add(134, "Chilpancingo");
        estaciones.add(135, "Centro Médico");
        estaciones.add(136, "Lázaro Cárdenas");
        estaciones.add(137, "Chabacano");
        estaciones.add(138, "Jamaica");
        estaciones.add(139, "Mixiuhca");
        estaciones.add(140, "Velódromo");
        estaciones.add(141, "Ciudad Deportiva");
        estaciones.add(142, "Puebla");
        estaciones.add(143, "Pantitlán ");
        //LA
        estaciones.add(144, "Pantitlán ");
        estaciones.add(145, "Agrícola Oriental");
        estaciones.add(146, "Canal de San Juan ");
        estaciones.add(147, "Tepalcates");
        estaciones.add(148, "Guelatao");
        estaciones.add(149, "Peñón Viejo");
        estaciones.add(150, "Acatitla");
        estaciones.add(151, "Santa Marta");
        estaciones.add(152, "Los Reyes");
        estaciones.add(153, "La Paz");
        //LB
        estaciones.add(154, "Ciudad Azteca");
        estaciones.add(155, "Plaza Aragón ");
        estaciones.add(156, "Olímpica");
        estaciones.add(157, "Ecatepec");
        estaciones.add(158, "Múzquiz");
        estaciones.add(159, "Río de los Remedios");
        estaciones.add(160, "Impulsora");
        estaciones.add(161, "Nezahualcóyotl");
        estaciones.add(162, "Villa de Aragón ");
        estaciones.add(163, "Bosque de Aragón");
        estaciones.add(164, "Deportivo Oceanía");
        estaciones.add(165, "Oceanía");
        estaciones.add(166, "Romero Rubio");
        estaciones.add(167, "Ricardo Flores Magón ");
        estaciones.add(168, "San Lázaro ");
        estaciones.add(169, "Morelos");
        estaciones.add(170, "Tepito");
        estaciones.add(171, "Lagunilla");
        estaciones.add(172, "Garibaldi/ Lagunilla");
        estaciones.add(173, "Guerrero ");
        estaciones.add(174, "Buenavista ");
        //L12
        estaciones.add(175, "Mixcoac");
        estaciones.add(176, "Insurgentes Sur");
        estaciones.add(177, "Hospital 20 de Noviembre");
        estaciones.add(178, "Zapata");
        estaciones.add(179, "Parque de los Venados");
        estaciones.add(180, "Eje Central");
        estaciones.add(181, "Ermita");
        estaciones.add(182, "Mexicaltzingo");
        estaciones.add(183, "Atlalilco");
        estaciones.add(184, "Culhuacán");
        estaciones.add(185, "San Andrés Tomatlán");
        estaciones.add(186, "Lomas Estrella");
        estaciones.add(187, "Calle 11");
        estaciones.add(188, "Periférico Oriente");
        estaciones.add(189, "Tezonco");
        estaciones.add(190, "Olivos");
        estaciones.add(191, "Nopalera");
        estaciones.add(192, "Zapotitlán ");
        estaciones.add(193, "Tlaltenco");
        estaciones.add(194, "Tláhuac ");


        estaciones.add(195, "Indios Verdes");
        estaciones.add(196, "Deportivo 18 de marzo");
        estaciones.add(197, "Euzkaro");
        estaciones.add(198, "Potrero");
        estaciones.add(199, "La Raza");
        estaciones.add(200, "Circuito");
        estaciones.add(201, "San Simón ");
        estaciones.add(202, "Manuel González ");
        estaciones.add(203, "Buenavista ");
        estaciones.add(204, "El Chopo");
        estaciones.add(205, "Revolución ");
        estaciones.add(206, "Plaza de la República ");
        estaciones.add(207, "Reforma ");
        estaciones.add(208, "Hamburgo");
        estaciones.add(209, "Insurgentes ");
        estaciones.add(210, "Durango");
        estaciones.add(211, "Álvaro Obregón ");
        estaciones.add(212, "Sonora");
        estaciones.add(213, "Campeche ");
        estaciones.add(214, "Chilpancingo ");
        estaciones.add(215, "Nuevo León ");
        estaciones.add(216, "La Piedad ");
        estaciones.add(217, "Polifórum");
        estaciones.add(218, "Nápoles");
        estaciones.add(219, "Colonia del Valle");
        estaciones.add(220, "CD. De los Deportes");
        estaciones.add(221, "Parque Hundido");
        estaciones.add(222, "Félix Cuevas");
        estaciones.add(223, "Rio Churubusco");
        estaciones.add(224, "Teatro de los Insurgentes");
        estaciones.add(225, "José María Velasco");
        estaciones.add(226, "Francia");
        estaciones.add(227, "Olivo");
        estaciones.add(228, "Altavista ");
        estaciones.add(229, "La Bombilla ");
        estaciones.add(230, "Doctor Gálvez");
        estaciones.add(231, "Ciudad Universitaria ");
        estaciones.add(232, "Centro Cultural Universitario");
        estaciones.add(233, "Perisur");
        estaciones.add(234, "Villa Olimpica");
        estaciones.add(235, "Corregidora");
        estaciones.add(236, "Ayuntamiento");
        estaciones.add(237, "Fuentes Brotamtes");
        estaciones.add(238, "Santa Úrsula ");
        estaciones.add(239, "La Joya ");
        estaciones.add(240, "El Caminero");
        estaciones.add(241, "Tepalcates");
        estaciones.add(242, "General Antonio de León ");
        estaciones.add(243, "Constitución de Apatzingán ");
        estaciones.add(244, "CCH Oriente");
        estaciones.add(245, "Leyes de Reforma");
        estaciones.add(246, "Río Frío ");
        estaciones.add(247, "Rojo Gómez ");
        estaciones.add(248, "Río Mayo");
        estaciones.add(249, "Río Tecolutla ");
        estaciones.add(250, "El Rodeo");
        estaciones.add(251, "UPIICSA");
        estaciones.add(252, "Iztacalco");
        estaciones.add(253, "Goma");
        estaciones.add(254, "Tlacotal");
        estaciones.add(255, "Canela");
        estaciones.add(256, "Metro Coyuya");
        estaciones.add(257, "Coyuya");
        estaciones.add(258, "La Viga");
        estaciones.add(259, "Andrés Molina Enríquez ");
        estaciones.add(260, "Las Américas ");
        estaciones.add(261, "Xola");
        estaciones.add(262, "Álamos ");
        estaciones.add(263, "Centro SCOP");
        estaciones.add(264, "Doctor Vértiz");
        estaciones.add(265, "Etiopía ");
        estaciones.add(266, "Amores");
        estaciones.add(267, "Viaducto");
        estaciones.add(268, "Nuevo León ");
        estaciones.add(269, "Escandón");
        estaciones.add(270, "Patriotismo");
        estaciones.add(271, "De la Salle");
        estaciones.add(272, "Parque Lira");
        estaciones.add(273, "Tacubaya");
        estaciones.add(274, "Antonio Maceo");
        estaciones.add(275, "Del Moral");
        estaciones.add(276, "Canal de San Juan ");
        estaciones.add(277, "Nicolás Bravo");
        estaciones.add(278, "Pueblo Santa Cruz Atoyac");
        estaciones.add(279, "Miguel Laurent");
        estaciones.add(280, "División del Norte");
        estaciones.add(281, "Eugenia");
        estaciones.add(282, "Luz Saviñón ");
        estaciones.add(283, "Etiopía ");
        estaciones.add(284, "Obrero Mundial");
        estaciones.add(285, "Centro Médico ");
        estaciones.add(286, "Doctor Márquez ");
        estaciones.add(287, "Hospital General ");
        estaciones.add(288, "Jardín Pushkin");
        estaciones.add(289, "Cuauhtémoc ");
        estaciones.add(290, "Balderas");
        estaciones.add(291, "Juárez ");
        estaciones.add(292, "Hidalgo");
        estaciones.add(293, "Mina");
        estaciones.add(294, "Guerrero");
        estaciones.add(295, "R. Flores Magón ");
        estaciones.add(296, "Tlatelolco");
        estaciones.add(297, "Tolnáhuac");
        estaciones.add(298, "Circuito");
        estaciones.add(299, "Hospital la Raza");
        estaciones.add(300, "Héroe de Nacozari");
        estaciones.add(301, "Cuitláhuac");
        estaciones.add(302, "Coltongo");
        estaciones.add(303, "Magdalena de las Salinas");
        estaciones.add(304, "Poniente 128");
        estaciones.add(305, "Poniente 134");
        estaciones.add(306, "Montevideo ");
        estaciones.add(307, "Poniente 146");
        estaciones.add(308, "La Patera");
        estaciones.add(309, "Júpiter");
        estaciones.add(310, "Tres Anegas");
        estaciones.add(311, "Progreso Nacional ");
        estaciones.add(312, "San José de la Escalera");
        estaciones.add(313, "Tenayuca");
        estaciones.add(314, "Buenavista ");
        estaciones.add(315, "La Raza");
        estaciones.add(316, "Alameda Oriente");
        estaciones.add(317, "Calle 6");
        estaciones.add(318, "Pantitlán ");
        estaciones.add(319, "Archivo General de la Nación");
        estaciones.add(320, "Morelos");
        estaciones.add(321, "Ferrocarril de Cintura");
        estaciones.add(322, "Mixcalco");
        estaciones.add(323, "Teatro del Pueblo");
        estaciones.add(324, "República de Argentina ");
        estaciones.add(325, "República de Chile");
        estaciones.add(326, "Teatro Blanquita");
        estaciones.add(327, "Bellas Artes");
        estaciones.add(328, "Hidalgo");
        estaciones.add(329, "Museo San Carlos");
        estaciones.add(330, "México Tenochtitlan");
        estaciones.add(331, "Alcaldía Cuauhtémoc");
        estaciones.add(332, "Buenavista ");
        estaciones.add(333, "Plaza de la República ");
        estaciones.add(334, "Amajac");
        estaciones.add(335, "Defensoría Pública");
        estaciones.add(336, "Vocacional 5");
        estaciones.add(337, "Juárez");
        estaciones.add(338, "Mercados de San Juan");
        estaciones.add(339, "Eje Central");
        estaciones.add(340, "El Salvador");
        estaciones.add(341, "Isabel la Católica");
        estaciones.add(342, "Museo de la Ciudad");
        estaciones.add(343, "Pino Suárez");
        estaciones.add(344, "Las Cruces");
        estaciones.add(345, "La Merced");
        estaciones.add(346, "Mercado Sonora");
        estaciones.add(347, "20 de Noviembre");
        estaciones.add(348, "Pino Suárez Sur");
        estaciones.add(349, "San Pablo");
        estaciones.add(350, "Mercado Sonora Sur");
        estaciones.add(351, "Cecilio Robelo");
        estaciones.add(352, "Hospital Balbuena");
        estaciones.add(353, "Ingeniero Eduardo Molina");
        estaciones.add(354, "Moctezuma");
        estaciones.add(355, "San Lázaro");
        estaciones.add(356, "Aeropuerto T1");
        estaciones.add(357, "Aeropuerto T2");
        estaciones.add(358, "Preparatoria 1");
        estaciones.add(359, "DIF Xochimilco");
        estaciones.add(360, "Circuito Cuemanco");
        estaciones.add(361, "Muyuguarda");
        estaciones.add(362, "Cañaverales");
        estaciones.add(363, "Calzada del Hueso");
        estaciones.add(364, "Vista Hermosa");
        estaciones.add(365, "Las Bombas");
        estaciones.add(366, "Tepetlapa");
        estaciones.add(367, "La Virgen");
        estaciones.add(368, "Manuela Sáenz");
        estaciones.add(369, "ESIME Culhuacán ");
        estaciones.add(370, "Cafetales");
        estaciones.add(371, "Calzada Taxqueña");
        estaciones.add(372, "Barrio San Antonio");
        estaciones.add(373, "Pueblo de los Reyes");
        estaciones.add(374, "Ganaderos");
        estaciones.add(375, "Ermita-Iztapalapa");
        estaciones.add(376, "Atanasio G. Sarabia");
        estaciones.add(377, "Escuadrón 201");
        estaciones.add(378, "Churubusco Oriente");
        estaciones.add(379, "Aculco");
        estaciones.add(380, "Apatlaco");
        estaciones.add(381, "Canal Apatlaco");
        estaciones.add(382, "Colegio de Bachilleres 3");
        estaciones.add(383, "Oriente 116");
        estaciones.add(384, "Recreo");
        estaciones.add(385, "Metro Coyuya");
        estaciones.add(386, "Hospital General Troncoso");
        estaciones.add(387, "Mixiuhca");
        estaciones.add(388, "Avenida del Taller");
        estaciones.add(389, "Venustiano Carranza");
        estaciones.add(390, "Moctezuma ");
        estaciones.add(391, "San Lázaro");
        estaciones.add(392, "Archivo General de la Nacion");
        estaciones.add(393, "Mercado Morelos");
        estaciones.add(394, "Deportivo Eduardo Molina");
        estaciones.add(395, "Canal del Norte ");
        estaciones.add(396, "Río Consulado");
        estaciones.add(397, "Río Santa Coleta");
        estaciones.add(398, "Oriente 101");
        estaciones.add(399, "Victoria ");
        estaciones.add(400, "Talismán");
        estaciones.add(401, "Rio de Guadalupe");
        estaciones.add(402, "San Juan de Aragón");
        estaciones.add(403, "Preparatoria 3");
        estaciones.add(404, "El Coyol");
        estaciones.add(405, "Vasco de Quiroga ");
        estaciones.add(406, "5 de Mayo");
        estaciones.add(407, "314 M. New´s Divine");
        estaciones.add(408, "Rio de los Remedios");
        estaciones.add(409, "Villa de Aragón");
        estaciones.add(410, "Francisco Morazán");
        estaciones.add(411, "Colegio de Bachilleres 9");
        estaciones.add(412, "La Pradera");
        estaciones.add(413, "Volcán Fuego");
        estaciones.add(414, "A. Providencia");
        estaciones.add(415, "D. Los Galeana");
        estaciones.add(416, "416 Poniente");
        estaciones.add(417, "Loreto Fabela");
        estaciones.add(418, "Pueblo S. J. de Aragón");
        estaciones.add(419, "Casas Alemán ");
        estaciones.add(420, "Gran Canal");
        estaciones.add(421, "San Juan de Aragón");
        estaciones.add(422, "Hospital la Villa");
        estaciones.add(423, "Martín Carrera");
        estaciones.add(424, "Gustavo A. Madero");
        estaciones.add(425, "Hospital Infantil la Villa");
        estaciones.add(426, "De los Misterios");
        estaciones.add(427, "La Villa");
        estaciones.add(428, "Deportivo 18 de marzo");
        estaciones.add(429, "Riobamba");
        estaciones.add(430, "I.P.N");
        estaciones.add(431, "San Bartolo");
        estaciones.add(432, "I. del Petróleo");
        estaciones.add(433, "Lindavista-Vallejo");
        estaciones.add(434, "Montevideo");
        estaciones.add(435, "Norte 45");
        estaciones.add(436, "Norte 59");
        estaciones.add(437, "Tecnoparque");
        estaciones.add(438, "UAM Azcapotzalco");
        estaciones.add(439, "F.F.C.C. Nacionales");
        estaciones.add(440, "De las Culturas");
        estaciones.add(441, "Colegio de Bachilleres 1");
        estaciones.add(442, "El Rosario");
        estaciones.add(443, "482");
        estaciones.add(444, "414");
        estaciones.add(445, "416 Oriente");
        estaciones.add(446, "Campo Marte");
        estaciones.add(447, "Auditorio");
        estaciones.add(448, "Antropología");
        estaciones.add(449, "Gandhi");
        estaciones.add(450, "Chapultepec");
        estaciones.add(451, "La Diana");
        estaciones.add(452, "El Ángel");
        estaciones.add(453, "El Ahuehuete");
        estaciones.add(454, "Hamburgo");
        estaciones.add(455, "Reforma");
        estaciones.add(456, "París");
        estaciones.add(457, "Amajac");
        estaciones.add(458, "El Caballito");
        estaciones.add(459, "Hidalgo");
        estaciones.add(460, "Glorieta Violeta");
        estaciones.add(461, "Garibaldi / Lagunilla");
        estaciones.add(462, "Glorieta Cuitláhuac");
        estaciones.add(463, "Tres Culturas");
        estaciones.add(464, "Peralvillo");
        estaciones.add(465, "Mercado Beethoven");
        estaciones.add(466, "Misterios");
        estaciones.add(467, "Clave");
        estaciones.add(468, "Robles Domínguez");
        estaciones.add(469, "Excélsior");
        estaciones.add(470, "Necaxa");
        estaciones.add(471, "Av. Talismán");
        estaciones.add(472, "Garrido");
        estaciones.add(473, "Gustavo A. Madero");
        estaciones.add(474, "Hospital Infantil la Villa");
        estaciones.add(475, "Indios Verdes");
        estaciones.add(476, "De los Misterios");
        estaciones.add(477, "Indios Verdes");
        estaciones.add(478, "Ticomán");
        estaciones.add(479, "La Pastora");
        estaciones.add(480, "Campos Revolución ");
        estaciones.add(481, "Tlalpexco");
        estaciones.add(482, "Cuautepec");
        estaciones.add(483, "Constitución de 1917");
        estaciones.add(484, "Quetzalcóatl");
        estaciones.add(485, "Torres Buenavista");
        estaciones.add(486, "Xalpa");
        estaciones.add(487, "Lomas de la Estancia");
        estaciones.add(488, "San Miguel Teotongo");
        estaciones.add(489, "Santa Marta");
        estaciones.add(490, "Tasqueña");
        estaciones.add(491, "Las Torres");
        estaciones.add(492, "Ciudad Jardín ");
        estaciones.add(493, "La Virgen");
        estaciones.add(494, "Xotepingo");
        estaciones.add(495, "Nezahualpilli");
        estaciones.add(496, "Registro Federal");
        estaciones.add(497, "Textitlán");
        estaciones.add(498, "El Vergel");
        estaciones.add(499, "Estadio Azteca");
        estaciones.add(500, "Huipulco");
        estaciones.add(501, "Xomali");
        estaciones.add(502, "Periférico Participación Ciudadana");
        estaciones.add(503, "Tepepan");
        estaciones.add(504, "La Noria");
        estaciones.add(505, "Huichapan");
        estaciones.add(506, "Francisco Goitia");
        estaciones.add(507, "Xochimilco");
        estaciones.add(508, "Central del Norte D.C.S");
        estaciones.add(509, "C.C.H. Vallejo ");
        estaciones.add(510, "Instituto del Petróleo N ");
        estaciones.add(511, "Montevideo");
        estaciones.add(512, "U.H. Lindavista Vallejo ");
        estaciones.add(513, "Instituto del Petróleo S");
        estaciones.add(514, "Poniente 128");
        estaciones.add(515, "Tesorería ");
        estaciones.add(516, "Poniente 118");
        estaciones.add(517, "Poniente 112 D.C.S");
        estaciones.add(518, "Poniente 106");
        estaciones.add(519, "La Raza");
        estaciones.add(520, "Río Consulado D.C.S");
        estaciones.add(521, "Felipe Villanueva D.C.S");
        estaciones.add(522, "Chopin D.C.S");
        estaciones.add(523, "Granados D.C.S");
        estaciones.add(524, "Manuel González D.C.S");
        estaciones.add(525, "Flores Magón D.C.S");
        estaciones.add(526, "Luna D.C.S");
        estaciones.add(527, "República de Perú D.C.S");
        estaciones.add(528, "Bellas Artes D.C.S");
        estaciones.add(529, "Independencia ");
        estaciones.add(530, "República de Uruguay D.C.S");
        estaciones.add(531, "Vizcaínas D.C.S");
        estaciones.add(532, "Salto del agua D.C.S");
        estaciones.add(533, "Fray Servando T. de Mier D.C.S");
        estaciones.add(534, "Dr. Pascua");
        estaciones.add(535, "Doctores D.C.S");
        estaciones.add(536, "Monumento a Cárdenas D.C.S");
        estaciones.add(537, "Manuel J. Othon D.C.S");
        estaciones.add(538, "Obrera D.C.S");
        estaciones.add(539, "Lázaro Cárdenas D.C.S");
        estaciones.add(540, "Viaducto D.C.S");
        estaciones.add(541, "Morena D.C.S");
        estaciones.add(542, "Centro SCOP D.C.S");
        estaciones.add(543, "Cumbres de Acultzingo D.C.S");
        estaciones.add(544, "Luz Saviñon D.C.S");
        estaciones.add(545, "Cumbres de Maltrata  D.C.S");
        estaciones.add(546, "Eugenia D.C.S");
        estaciones.add(547, "Ángel Urraza D.C.S");
        estaciones.add(548, "Matías Romero");
        estaciones.add(549, "Miguel Laurent D.C.S");
        estaciones.add(550, "Municipio Libre D.C.S");
        estaciones.add(551, "Emiliano Zapata D.C.S");
        estaciones.add(552, "Popocatépetl D.C.S");
        estaciones.add(553, "Ajusco D.C.S");
        estaciones.add(554, "Xicoténcatl D.C.N ");
        estaciones.add(555, "Hidalgo");
        estaciones.add(556, "América D.C.S");
        estaciones.add(557, "Central ");
        estaciones.add(558, "Cerro Huitzilac");
        estaciones.add(559, "Central del Sur D.C.S");
        estaciones.add(560, "Central del Sur D.C.N ");
        estaciones.add(561, "Tasqueña");
        estaciones.add(562, "División del Norte");
        estaciones.add(563, "América D.C.N ");
        estaciones.add(564, "Irlanda");
        estaciones.add(565, "Xicoténcatl D.C.N ");
        estaciones.add(566, "Churubusco");
        estaciones.add(567, "Ajusco D.C.N ");
        estaciones.add(568, "Popocatepetl D.C.N ");
        estaciones.add(569, "Emiliano Zapata D.C.N ");
        estaciones.add(570, "Municipio Libre D.C.N ");
        estaciones.add(571, "Miguel Laurent D.C.N ");
        estaciones.add(572, "Ángel Urraza D.C.N ");
        estaciones.add(573, "HIFF");
        estaciones.add(574, "Eugenia D.C.N ");
        estaciones.add(575, "Cumbres de Maltrata D.C.N ");
        estaciones.add(576, "Luz Saviñon D.C.N ");
        estaciones.add(577, "Cumbres de Acultzingo D.C.N ");
        estaciones.add(578, "Centro SCOP D.C.N ");
        estaciones.add(579, "Morena D.C.N ");
        estaciones.add(580, "Viaducto D.C.N ");
        estaciones.add(581, "Lázaro Cárdenas D.C.N ");
        estaciones.add(582, "Antonio Solís");
        estaciones.add(583, "Obrera D.C.N ");
        estaciones.add(584, "Manuel J. Othon D.C.N ");
        estaciones.add(585, "Monumento a Cárdenas D.C.N ");
        estaciones.add(586, "Doctores D.C.N ");
        estaciones.add(587, "Fray Servando T. de Mier D.C.N ");
        estaciones.add(588, "Salto del Agua D.C.N ");
        estaciones.add(589, "Vizcaínas D.C.N ");
        estaciones.add(590, "República de Uruguay D.C.N ");
        estaciones.add(591, "Francisco I. Madero ");
        estaciones.add(592, "Bellas Artes D.C.N ");
        estaciones.add(593, "República de Perú D.C.N ");
        estaciones.add(594, "Luna D.C.N ");
        estaciones.add(595, "Flores Magón D.C.N ");
        estaciones.add(596, "Manuel González D.C.N ");
        estaciones.add(597, "Granados D.C.N ");
        estaciones.add(598, "Chopin D.C.N ");
        estaciones.add(599, "Felipe Villanueva D.C.N ");
        estaciones.add(600, "Rio Consulado D.C.N ");
        estaciones.add(601, "Poniente 112 D.C.N ");
        estaciones.add(602, "Central del Norte D.C.N ");
        estaciones.add(603, "Cetram Pantitlán D.C");
        estaciones.add(604, "Calz. Ignacio Zaragoza D.C");
        estaciones.add(605, "Puebla D.C");
        estaciones.add(606, "Puerta 8 D.C");
        estaciones.add(607, "Ciudad Deportiva D.C");
        estaciones.add(608, "Velódromo D.C");
        estaciones.add(609, "Fernando Iglesias Calderón D.C");
        estaciones.add(610, "Mixiuca");
        estaciones.add(611, "Industria");
        estaciones.add(612, "Congreso de la Unión D.C");
        estaciones.add(613, "Jamaica");
        estaciones.add(614, "Del Taller");
        estaciones.add(615, "Torno D.C");
        estaciones.add(616, "Calz. de la Viga D.C");
        estaciones.add(617, "Clavijero D.C");
        estaciones.add(618, "Tlalpan");
        estaciones.add(619, "5 de Febrero D.C");
        estaciones.add(620, "Isabel la Católica D.C");
        estaciones.add(621, "Bolívar D.C");
        estaciones.add(622, "Eje Central D.C");
        estaciones.add(623, "Dr. Andrade D.C");
        estaciones.add(624, "Dr. Vértiz D.C");
        estaciones.add(625, "Niños Héroes");
        estaciones.add(626, "Dr. Lucio D.C");
        estaciones.add(627, "Cuauhtémoc D.C");
        estaciones.add(628, "Mérida D.C");
        estaciones.add(629, "Orizaba D.C");
        estaciones.add(630, "Tonalá D.C");
        estaciones.add(631, "Monterrey");
        estaciones.add(632, "Insurgentes D.C");
        estaciones.add(633, "Álvaro Obregón D.C");
        estaciones.add(634, "Sonora");
        estaciones.add(635, "Durango");
        estaciones.add(636, "Chapultepec D.C");
        estaciones.add(637, "Chapultepec D.P");
        estaciones.add(638, "Sinaloa");
        estaciones.add(639, "Parque España");
        estaciones.add(640, "Álvaro Obregón D.P");
        estaciones.add(641, "Insurgentes D.P");
        estaciones.add(642, "Querétaro");
        estaciones.add(643, "Tonalá D.P");
        estaciones.add(644, "Orizaba D.P");
        estaciones.add(645, "Mérida D.P");
        estaciones.add(646, "Cuauhtémoc D.P");
        estaciones.add(647, "Dr. Lucio D.P");
        estaciones.add(648, "Dr. Jiménez");
        estaciones.add(649, "Dr. Vértiz D.P");
        estaciones.add(650, "Dr. Andrade D.P");
        estaciones.add(651, "Eje Central D.P");
        estaciones.add(652, "Bolívar D.P");
        estaciones.add(653, "Isabel la Católica D.P");
        estaciones.add(654, "5 de Febrero D.P");
        estaciones.add(655, "José Antonio Torres");
        estaciones.add(656, "Clavijero D.P");
        estaciones.add(657, "Calz. de la Viga D.P");
        estaciones.add(658, "Callejón Resurrección");
        estaciones.add(659, "Torno D.P");
        estaciones.add(660, "Congreso de la Unión D.P");
        estaciones.add(661, "Cucurpe");
        estaciones.add(662, "Aconchi");
        estaciones.add(663, "Antonio Peña y Reyes");
        estaciones.add(664, "Fernando Iglesias Calderón D.P");
        estaciones.add(665, "Retorno 39");
        estaciones.add(666, "Genaro García");
        estaciones.add(667, "Radamés Treviño");
        estaciones.add(668, "Velódromo D.P");
        estaciones.add(669, "ESEF");
        estaciones.add(670, "Palacio de los Deportes");
        estaciones.add(671, "Av. Río Churubusco");
        estaciones.add(672, "Ciudad Deportiva D.P");
        estaciones.add(673, "Puerta 8 D.P");
        estaciones.add(674, "Puebla D.P");
        estaciones.add(675, "Calz. Ignacio Zaragoza D.P");
        estaciones.add(676, "Cetram Pantitlán D.P");
        estaciones.add(677, "Museo de Transportes Eléctricos D.M.N.");
        estaciones.add(678, "Plutarco Elías Calles D.M.N.");
        estaciones.add(679, "Miravalle");
        estaciones.add(680, "Bélgica");
        estaciones.add(681, "Rumania");
        estaciones.add(682, "Eje Central D.M.N.");
        estaciones.add(683, "Tokio D.M.N.");
        estaciones.add(684, "Dr. Vértiz ");
        estaciones.add(685, "División del Norte D.M.N.");
        estaciones.add(686, "Parque de los Venados");
        estaciones.add(687, "Cuauhtemoc D.M.N.");
        estaciones.add(688, "Zapata 1 D.M.N.");
        estaciones.add(689, "Zapata 2  D.M.N.");
        estaciones.add(690, "Aniceto Ortega D.M.N.");
        estaciones.add(691, "Hospital 20 de Noviembre D.M.N.");
        estaciones.add(692, "Adolfo Prieto");
        estaciones.add(693, "Moras D.M.N.");
        estaciones.add(694, "San Francisco D.M.N.");
        estaciones.add(695, "Tejocotes");
        estaciones.add(696, "Insurgentes Sur D.M.N.");
        estaciones.add(697, "Jerez");
        estaciones.add(698, "Augusto Rodín D.M.N.");
        estaciones.add(699, "Poussin D.M.N.");
        estaciones.add(700, "Patriotismo D.M.N");
        estaciones.add(701, "Mixcoac Norte");
        estaciones.add(702, "Mixcoac Sur ");
        estaciones.add(703, "Patriotismo D.M.T.E");
        estaciones.add(704, "Poussin D.M.T.E");
        estaciones.add(705, "Augusto Rodín D.M.T.E");
        estaciones.add(706, "Santander");
        estaciones.add(707, "Insurgentes Sur D.M.T.E");
        estaciones.add(708, "Oso");
        estaciones.add(709, "San Francisco D.M.T.E");
        estaciones.add(710, "Moras D.M.T.E");
        estaciones.add(711, "Hospital 20 de Noviembre D.M.T.E");
        estaciones.add(712, "Gabriel Mancera");
        estaciones.add(713, "Aniceto Ortega D.M.T.E");
        estaciones.add(714, "Zapata 2 D.M.T.E");
        estaciones.add(715, "Zapata 1 D.M.T.E");
        estaciones.add(716, "Av. México");
        estaciones.add(717, "Cuauhtemoc D.M.T.E");
        estaciones.add(718, "Uxmal");
        estaciones.add(719, "Tajín");
        estaciones.add(720, "Petén ");
        estaciones.add(721, "División del Norte D.M.T.E");
        estaciones.add(722, "Tokio D.M.T.E");
        estaciones.add(723, "Eje Central  D.M.T.E");
        estaciones.add(724, "Canarias");
        estaciones.add(725, "Balboa");
        estaciones.add(726, "Plutarco Elías Calles D.M.T.E");
        estaciones.add(727, "Sur 69-A");
        estaciones.add(728, "Sur 73");
        estaciones.add(729, "Arboledas");
        estaciones.add(730, "Museo de Transportes Eléctricos D.M.T.E");
        estaciones.add(731, "Metro Blvd. Puerto Aéreo D.E.R");
        estaciones.add(732, "Norte 13 D.E.R");
        estaciones.add(733, "Norte 25");
        estaciones.add(734, "Terminal Aérea ");
        estaciones.add(735, "Av. del Peñón D.E.R");
        estaciones.add(736, "Morelos");
        estaciones.add(737, "Oceania D.E.R");
        estaciones.add(738, "Calle 549");
        estaciones.add(739, "Av. 535");
        estaciones.add(740, "Calle 525");
        estaciones.add(741, "Calle 509 D.E.R");
        estaciones.add(742, "Av. 506");
        estaciones.add(743, "3A Cda. Av. 503");
        estaciones.add(744, "Dtvo. Francisco Zarco");
        estaciones.add(745, "Norte 92 D.E.R");
        estaciones.add(746, "Norte 84-A");
        estaciones.add(747, "Eduardo Molina D.E.R");
        estaciones.add(748, "Norte 72-A D.E.R");
        estaciones.add(749, "Norte 70");
        estaciones.add(750, "Congreso de la Unión D.E.R");
        estaciones.add(751, "Norte 58");
        estaciones.add(752, "F.C. Hidalgo");
        estaciones.add(753, "Graciela D.E.R");
        estaciones.add(754, "Calz. de Guadalupe D.E.R");
        estaciones.add(755, "Calz. de los Misterios D.E.R");
        estaciones.add(756, "Buen Tono D.E.R");
        estaciones.add(757, "Del Monte");
        estaciones.add(758, "Norte 1-G");
        estaciones.add(759, "Calz. Vallejo D.E.R");
        estaciones.add(760, "Calle 28");
        estaciones.add(761, "Central Sur D.E.R");
        estaciones.add(762, "Calle 12 D.E.R");
        estaciones.add(763, "Av. Jardin D.E.R");
        estaciones.add(764, "Ceylan D.E.R");
        estaciones.add(765, "Cerdeña");
        estaciones.add(766, "Plan de San Luis D.E.R");
        estaciones.add(767, "Av. Nueces");
        estaciones.add(768, "Platanales");
        estaciones.add(769, "Glorieta Camarones D.E.R");
        estaciones.add(770, "Salónica D.E.R");
        estaciones.add(771, "Norte 87");
        estaciones.add(772, "2A Cda. de Tula");
        estaciones.add(773, "22 de Febrero D.E.R");
        estaciones.add(774, "Minerva");
        estaciones.add(775, "Nueva Jerusalén");
        estaciones.add(776, "Ahuacatitla");
        estaciones.add(777, "Zaragoza");
        estaciones.add(778, "Andalucía");
        estaciones.add(779, "F.F.C.C. Nacionales D.E.R");
        estaciones.add(780, "Santo Domingo D.E.R");
        estaciones.add(781, "16 de Septiembre");
        estaciones.add(782, "Metro Aquiles Serdán D.E.R");
        estaciones.add(783, "Sauces");
        estaciones.add(784, "Ahuehuetes");
        estaciones.add(785, "Tierra Indómita");
        estaciones.add(786, "CCH. Azcapotzalco D.E.R");
        estaciones.add(787, "Tierra Nueva");
        estaciones.add(788, "Av. del Rosario");
        estaciones.add(789, "Hacienda el Rosario");
        estaciones.add(790, "Osa Mayor");
        estaciones.add(791, "Renacimiento");
        estaciones.add(792, "Alfareros D.E.R");
        estaciones.add(793, "Cultura Nahuatl D.E.R");
        estaciones.add(794, "Hidrógeno");
        estaciones.add(795, "Carbono");
        estaciones.add(796, "Bachilleres 1");
        estaciones.add(797, "Aztacalco");
        estaciones.add(798, "Depósito El Rosario");
        estaciones.add(799, "Av. de los Angeles");
        estaciones.add(800, "Tierra Negra");
        estaciones.add(801, "El Rosario D.E.R");
        estaciones.add(802, "El Rosario D.M.B.P.A");
        estaciones.add(803, "Av. de las Culturas");
        estaciones.add(804, "Mecánicos");
        estaciones.add(805, "Pescadores");
        estaciones.add(806, "Cultura Nahuatl D.M.B.P.A");
        estaciones.add(807, "Alfareros D.M.B.P.A");
        estaciones.add(808, "Aquiles Serdán");
        estaciones.add(809, "Rancho San Juan de Dios");
        estaciones.add(810, "Hacienda de Sotelo");
        estaciones.add(811, "CCH. Azcapotzalco D.M.B.P.A");
        estaciones.add(812, "Puente de Guerra");
        estaciones.add(813, "Zempoaltecas");
        estaciones.add(814, "Metro Aquiles Serdán D.M.B.P.A");
        estaciones.add(815, "Centéotl");
        estaciones.add(816, "Santo Domingo D.M.B.P.A");
        estaciones.add(817, "F.F.C.C. Nacionales D.M.B.P.A");
        estaciones.add(818, "Hidalgo");
        estaciones.add(819, "Camarones");
        estaciones.add(820, "Av. Azcapotzalco");
        estaciones.add(821, "22 de Febrero D.M.B.P.A");
        estaciones.add(822, "Nubia");
        estaciones.add(823, "Salónica D.M.B.P.A");
        estaciones.add(824, "Glorieta Camarones D.M.B.P.A");
        estaciones.add(825, "Av. Cuitlahuac");
        estaciones.add(826, "Piña");
        estaciones.add(827, "Plan de San Luis D.M.B.P.A");
        estaciones.add(828, "F.C. Central");
        estaciones.add(829, "Ceylan D.M.B.P.A");
        estaciones.add(830, "Av. Jardin D.M.B.P.A");
        estaciones.add(831, "Calle 12 D.M.B.P.A");
        estaciones.add(832, "Central Sur D.M.B.P.A");
        estaciones.add(833, "Calz. Vallejo D.M.B.P.A");
        estaciones.add(834, "Auer");
        estaciones.add(835, "Real del Monte");
        estaciones.add(836, "Buen Tono D.M.B.P.A");
        estaciones.add(837, "Calz. de los Misterios D.M.B.P.A");
        estaciones.add(838, "Calz. de Guadalupe D.M.B.P.A");
        estaciones.add(839, "Graciela D.M.B.P.A");
        estaciones.add(840, "F.H. Hidalgo");
        estaciones.add(841, "Norte 54-A");
        estaciones.add(842, "Congreso de la Union D.M.B.P.A");
        estaciones.add(843, "Norte 66-A");
        estaciones.add(844, "Norte 72-A D.M.B.P.A");
        estaciones.add(845, "Eduardo Molina D.M.B.P.A");
        estaciones.add(846, "Norte 84");
        estaciones.add(847, "Norte 92 D.M.B.P.A");
        estaciones.add(848, "Av. 503");
        estaciones.add(849, "Calle 509 D.M.B.P.A");
        estaciones.add(850, "Pekin");
        estaciones.add(851, "Formosa");
        estaciones.add(852, "Oceanía D.M.B.P.A");
        estaciones.add(853, "Transvaal");
        estaciones.add(854, "Persia");
        estaciones.add(855, "Av. del Peñón D.M.B.P.A");
        estaciones.add(856, "Norte 37");
        estaciones.add(857, "Norte 33");
        estaciones.add(858, "Norte 25 I");
        estaciones.add(859, "Norte 25 II");
        estaciones.add(860, "Norte 21");
        estaciones.add(861, "Norte 17");
        estaciones.add(862, "Norte 13 D.M.B.P.A");
        estaciones.add(863, "Oriente 180");
        estaciones.add(864, "Blvd. Puerto Áereo");
        estaciones.add(865, "Clínica 14 IMSS");
        estaciones.add(866, "Plaza Aeropuerto");
        estaciones.add(867, "Metro Blvd. Puerto Áereo D.M.B.P.A");
        estaciones.add(868, "San Felipe de Jesús D.A.H");
        estaciones.add(869, "Sierra Hermosa");
        estaciones.add(870, "Tepatitlan");
        estaciones.add(871, "Calpulalpan D.A.H");
        estaciones.add(872, "Villa de Ayala D.A.H");
        estaciones.add(873, "Estado de Tabasco D.A.H");
        estaciones.add(874, "Estado de Oaxaca D.A.H");
        estaciones.add(875, "Estado de Morelos D.A.H");
        estaciones.add(876, "Constitucion de la República D.A.H");
        estaciones.add(877, "Estado de Sinaloa D.A.H");
        estaciones.add(878, "Camino de la Unión");
        estaciones.add(879, "Camino Sur");
        estaciones.add(880, "Deportivo los Galeana D.A.H");
        estaciones.add(881, "Puerto de Mazatlán D.A.H");
        estaciones.add(882, "Puerto de Tampico D.A.H");
        estaciones.add(883, "Camino de San Juan de Aragón ");
        estaciones.add(884, "Puerto de Tlacotalpan D.A.H");
        estaciones.add(885, "Puerto de Barra de Nautla");
        estaciones.add(886, "Puerto de Acapulco D.A.H");
        estaciones.add(887, "Puerto de San Blas");
        estaciones.add(888, "Puerto de Coatzacoalcos");
        estaciones.add(889, "Puerto Cadíz D.A.H");
        estaciones.add(890, "Gran Canal D.A.H");
        estaciones.add(891, "Arcos de Aragón D.A.H");
        estaciones.add(892, "Eduardo Molina D.A.H");
        estaciones.add(893, "San Juan D.A.H");
        estaciones.add(894, "FF.CC. Hidalgo ");
        estaciones.add(895, "Martín Carrera D.A.H");
        estaciones.add(896, "Casa del Peregrino Juan Diego D.A.H");
        estaciones.add(897, "5 de Febrero D.A.H");
        estaciones.add(898, "General Martín Carrera");
        estaciones.add(899, "General Mariano Salas");
        estaciones.add(900, "Zaragoza");
        estaciones.add(901, "Montevideo");
        estaciones.add(902, "Garrido");
        estaciones.add(903, "Ricarte");
        estaciones.add(904, "Fortuna");
        estaciones.add(905, "Euzkaro");
        estaciones.add(906, "Río Blanco");
        estaciones.add(907, "Victoria D.A.H");
        estaciones.add(908, "Excelsior");
        estaciones.add(909, "Basilio Romo Anguiano");
        estaciones.add(910, "Robles Domínguez ");
        estaciones.add(911, "Donizetti");
        estaciones.add(912, "Río Consulado D.A.H");
        estaciones.add(913, "Beethoven");
        estaciones.add(914, "Juventino Rosas");
        estaciones.add(915, "Manuel González ");
        estaciones.add(916, "Tlatelolco  D.A.H");
        estaciones.add(917, "Flores Magón ");
        estaciones.add(918, "González Bocanegra");
        estaciones.add(919, "Garibaldi D.A.H");
        estaciones.add(920, "Moctezuma");
        estaciones.add(921, "Lerdo");
        estaciones.add(922, "Av. Hidalgo D.A.H");
        estaciones.add(923, "Av. Hidalgo D.S.F.J");
        estaciones.add(924, "Metro Hidalgo");
        estaciones.add(925, "Valerio Trujano");
        estaciones.add(926, "Garibaldi D.S.F.J");
        estaciones.add(927, "Matamoros");
        estaciones.add(928, "Constancia");
        estaciones.add(929, "Canal del Norte");
        estaciones.add(930, "Cobre");
        estaciones.add(931, "Hierro");
        estaciones.add(932, "Telurio");
        estaciones.add(933, "Río Consulado D.S.F.J");
        estaciones.add(934, "Julieta");
        estaciones.add(935, "Noe");
        estaciones.add(936, "Henry Ford");
        estaciones.add(937, "Victoria D.S.F.J");
        estaciones.add(938, "Joyas");
        estaciones.add(939, "Talismán ");
        estaciones.add(940, "Moctezuma");
        estaciones.add(941, "Hidalgo");
        estaciones.add(942, "5 de Febrero D.S.F.J");
        estaciones.add(943, "Cuauhtémoc ");
        estaciones.add(944, "Gral. Vicente Villada");
        estaciones.add(945, "Plaza Mariana");
        estaciones.add(946, "Calz. San Juan de Aragón ");
        estaciones.add(947, "Casa del Peregrino Juan Diego D.S.F.J");
        estaciones.add(948, "Martín Carrera D.S.F.J");
        estaciones.add(949, "Hospital de la Villa");
        estaciones.add(950, "San Juan D.S.F.J");
        estaciones.add(951, "Eduardo Molina D.S.F.J");
        estaciones.add(952, "Clínica 23 IMSS");
        estaciones.add(953, "Arcos de Aragón D.S.F.J");
        estaciones.add(954, "Gran Canal D.S.F.J");
        estaciones.add(955, "Puerto de Cádiz D.S.F.J");
        estaciones.add(956, "Puerto de Cozumel");
        estaciones.add(957, "Puerto de Guaymas");
        estaciones.add(958, "Puerto de Acapulco D.S.F.J");
        estaciones.add(959, "Puerto Progreso");
        estaciones.add(960, "Puerto Tlacotalpan D.S.F.J");
        estaciones.add(961, "Loreto Fabela");
        estaciones.add(962, "Puerto de Tampico D.S.F.J");
        estaciones.add(963, "Puerto de Mazatlán D.S.F.J");
        estaciones.add(964, "Deportivo los Galeana D.S.F.J");
        estaciones.add(965, "Francisco Morazán ");
        estaciones.add(966, "Constitución de la República  D.S.F.J");
        estaciones.add(967, "Estado de Sinaloa D.S.F.J");
        estaciones.add(968, " Estado de Tamaulipas");
        estaciones.add(969, "Estado de Morelos D.S.F.J");
        estaciones.add(970, "Estado de Oaxaca D.S.F.J");
        estaciones.add(971, "Estado de Tabasco D.S.F.J");
        estaciones.add(972, "Villa de Ayala D.S.F.J");
        estaciones.add(973, "Calpulalpan D.S.F.J");
        estaciones.add(974, "Valle Zambezi");
        estaciones.add(975, "Valle de Yukón");
        estaciones.add(976, "Valle del Yang Tse");
        estaciones.add(977, "San Felipe de Jesús D.S.F.J");
        estaciones.add(978, "El Rosario D.C");
        estaciones.add(979, "Av. de las Culturas");
        estaciones.add(980, "Mecánicos");
        estaciones.add(981, "Pescadores");
        estaciones.add(982, "Cultura Náhuatl D.C");
        estaciones.add(983, "Alfareros D.C");
        estaciones.add(984, "Aquiles Serdán ");
        estaciones.add(985, "Rancho San Juan de Dios");
        estaciones.add(986, "Hacienda de Sotelo");
        estaciones.add(987, "C.C.H Azcapotzalco D.C");
        estaciones.add(988, "Puente de Guerra");
        estaciones.add(989, "Zempoaltecas");
        estaciones.add(990, "Metro Aquiles Serdán D.C");
        estaciones.add(991, "Centéotl");
        estaciones.add(992, "Santo Domingo D.C");
        estaciones.add(993, "FF.CC. Nacionales D.C");
        estaciones.add(994, "Hidalgo");
        estaciones.add(995, "Camarones");
        estaciones.add(996, "Av. Azcapotzalco");
        estaciones.add(997, "22 de Febrero");
        estaciones.add(998, "Nubia");
        estaciones.add(999, "Salónica D.C");
        estaciones.add(1000, "Glorieta de Camarones D.C");
        estaciones.add(1001, "Poniente 62 D.C");
        estaciones.add(1002, "Clavería D.C");
        estaciones.add(1003, "Begonias");
        estaciones.add(1004, "Poniente 44 D.C");
        estaciones.add(1005, "J. Sánchez Trujillo D.C");
        estaciones.add(1006, "Calz. México - Tacuba D.C");
        estaciones.add(1007, "Mar Cantábrico D.C");
        estaciones.add(1008, "Felipe Carrillo Puerto D.C");
        estaciones.add(1009, "Av. Marina Nacional D.C");
        estaciones.add(1010, "Lago Bolsena D.C");
        estaciones.add(1011, "Laguna de Términos D.C");
        estaciones.add(1012, "Laguna de Mayrán D.C");
        estaciones.add(1013, "Lago Alberto D.C");
        estaciones.add(1014, "Newton D.C");
        estaciones.add(1015, "Homero D.C");
        estaciones.add(1016, "Horacio D.C");
        estaciones.add(1017, "Av. Presidente Mazarik D.C");
        estaciones.add(1018, "Campos Elíseos D.C");
        estaciones.add(1019, "Tolstoi");
        estaciones.add(1020, "Chapultepec D.C");
        estaciones.add(1021, "Chapultepec D.E.R");
        estaciones.add(1022, "Victor Hugo");
        estaciones.add(1023, "Mariano Escobedo");
        estaciones.add(1024, "Campos Elíseos D.E.R");
        estaciones.add(1025, "Av. Presidente Mazarik D.E.R");
        estaciones.add(1026, "Horacio D.E.R");
        estaciones.add(1027, "Homero D.E.R");
        estaciones.add(1028, "Newton D.E.R");
        estaciones.add(1029, "Lago Alberto D.E.R");
        estaciones.add(1030, "Laguna de Mayrán D.E.R");
        estaciones.add(1031, "Laguna de Términos D.E.R");
        estaciones.add(1032, "Lago Bolsena D.E.R");
        estaciones.add(1033, "Av. Marina Nacional D.E.R");
        estaciones.add(1034, "Lago Garda");
        estaciones.add(1035, "Felipe Carrillo Puerto D.E.R");
        estaciones.add(1036, "Mar Cantábrico D.E.R");
        estaciones.add(1037, "Calz. México - Tacuba D.E.R");
        estaciones.add(1038, "J. Sánchez Trujillo D.E.R");
        estaciones.add(1039, "Poniente 44 D.E.R");
        estaciones.add(1040, "Poniente 48 ");
        estaciones.add(1041, "Clavería D.E.R");
        estaciones.add(1042, "Poniente 62 D.E.R");
        estaciones.add(1043, "Glorieta de Camarones D.E.R");
        estaciones.add(1044, "Salónica D.E.R");
        estaciones.add(1045, "Norte 87");
        estaciones.add(1046, "2da cda. de Tula");
        estaciones.add(1047, "22 de Febrero");
        estaciones.add(1048, "Minerva");
        estaciones.add(1049, "Nueva Jerusalén ");
        estaciones.add(1050, "Ahuacatitla");
        estaciones.add(1051, "Zaragoza");
        estaciones.add(1052, "Andalucía ");
        estaciones.add(1053, "FF.CC. Nacionales D.E.R");
        estaciones.add(1054, "Santo Domingo D.E.R");
        estaciones.add(1055, "16 de Septiembre");
        estaciones.add(1056, "Metro Aquiles Serdán D.E.R");
        estaciones.add(1057, "Sauces");
        estaciones.add(1058, "Ahuehuetes");
        estaciones.add(1059, "Tierra Indómita");
        estaciones.add(1060, "C.C.H. Azcapotzalco");
        estaciones.add(1061, "Tierra Nueva");
        estaciones.add(1062, "Av. del Rosario");
        estaciones.add(1063, "Hacienda el Rosario");
        estaciones.add(1064, "Osa Mayor");
        estaciones.add(1065, "Renacimiento");
        estaciones.add(1066, "Alfareros D.E.R");
        estaciones.add(1067, "Cultura Náhuatl D.E.R");
        estaciones.add(1068, "Hidrógeno ");
        estaciones.add(1069, "Carbono");
        estaciones.add(1070, "Bachilleres 1");
        estaciones.add(1071, "Aztacalco");
        estaciones.add(1072, "Depósito el Rosario");
        estaciones.add(1073, "Av. de los Ángeles ");
        estaciones.add(1074, "Tierra Negra");
        estaciones.add(1075, "El Rosario D.E.R");
        estaciones.add(1076, "Tláhuac D.C.U");
        estaciones.add(1077, "Tlaltenco D.C.U");
        estaciones.add(1078, "Zapotitlán D.C.U");
        estaciones.add(1079, "Nopalera D.C.U");
        estaciones.add(1080, "Olivos D.C.U");
        estaciones.add(1081, "Tezonco D.C.U");
        estaciones.add(1082, "Periférico Oriente D.C.U");
        estaciones.add(1083, "Calle 11 D.C.U");
        estaciones.add(1084, "Lomas Estrella D.C.U");
        estaciones.add(1085, "Bahamas");
        estaciones.add(1086, "Progreso");
        estaciones.add(1087, "Emiliano Zapata D.C.U");
        estaciones.add(1088, "San Andrés Tomatlán D.C.U");
        estaciones.add(1089, "Luis Galvani D.C.U");
        estaciones.add(1090, "Samiel F.B. Morse");
        estaciones.add(1091, "Gobernación D.C.U");
        estaciones.add(1092, "Culhuacán D.C.U");
        estaciones.add(1093, "16 de Septiembre");
        estaciones.add(1094, "San Francisco");
        estaciones.add(1095, "Miguel Hidalgo D.C.U");
        estaciones.add(1096, "Escuela Naval Militar D.C.U");
        estaciones.add(1097, "Sta. Isabel Tola D.C.U");
        estaciones.add(1098, "CECyT No. 13 D.C.U");
        estaciones.add(1099, "Cerro del Cubiltete");
        estaciones.add(1100, "Canal de Miramontes D.C.U");
        estaciones.add(1101, "Tasqueña");
        estaciones.add(1102, "División del Norte D.C.U");
        estaciones.add(1103, "Ohio D.C.U");
        estaciones.add(1104, "América");
        estaciones.add(1105, "Asia");
        estaciones.add(1106, "Puente San Francisco D.C.U");
        estaciones.add(1107, "Cuadrante San Francisco D.C.U");
        estaciones.add(1108, "Moctezuma D.C.U");
        estaciones.add(1109, "Pino");
        estaciones.add(1110, "Melchor Ocampo D.C.U");
        estaciones.add(1111, "Zaragoza D.C.U");
        estaciones.add(1112, "Cerro del Hombre D.C.U");
        estaciones.add(1113, "Av. Universidad");
        estaciones.add(1114, "Miguel Ángel de Quevedo D.C.U");
        estaciones.add(1115, "Oxtopulco");
        estaciones.add(1116, "Cerro de Tuera");
        estaciones.add(1117, "Hermanos Vázquez D.C.U");
        estaciones.add(1118, "Av. Copilco D.C.U");
        estaciones.add(1119, "Retorno Copilco");
        estaciones.add(1120, "Ciudad Universitaria (Estadio Olimpico)");
        estaciones.add(1121, "Cuidad Universitaria");
        estaciones.add(1122, "Psicología ");
        estaciones.add(1123, "Av. Copilco D.T");
        estaciones.add(1124, "Hermanos Vázquez D.T");
        estaciones.add(1125, "Cerro Acasulco");
        estaciones.add(1126, "Cerro Xico");
        estaciones.add(1127, "Miguel Ángel de Quevedo D.T");
        estaciones.add(1128, "Cerro del Hombre D.T");
        estaciones.add(1129, "Zaragoza D.T");
        estaciones.add(1130, "Melchor Ocampo D.T");
        estaciones.add(1131, "Moctezuma  D.T");
        estaciones.add(1132, "Cuadrante San Francisco D.T");
        estaciones.add(1133, "Puente San Francisco D.T");
        estaciones.add(1134, "Fernández Leal");
        estaciones.add(1135, "Europa");
        estaciones.add(1136, "Oceanía");
        estaciones.add(1137, "Ohio D.T");
        estaciones.add(1138, "División del Norte D.T");
        estaciones.add(1139, "Central");
        estaciones.add(1140, "Cerro Huitzilac");
        estaciones.add(1141, "Central del Sur");
        estaciones.add(1142, "Canal de Miramontes D.T");
        estaciones.add(1143, "Petróleos Mexicanos");
        estaciones.add(1144, "CECyT No. 13 D.T");
        estaciones.add(1145, "Sta. Isabel Tola D.T");
        estaciones.add(1146, "Escuela Naval Militar D.T");
        estaciones.add(1147, "Miguel Hidalgo D.T");
        estaciones.add(1148, "Ejido");
        estaciones.add(1149, "San Pablo");
        estaciones.add(1150, "Avenida Tláhuac");
        estaciones.add(1151, "Culhuacán  D.T");
        estaciones.add(1152, "Gobernación  D.T");
        estaciones.add(1153, "Agrario");
        estaciones.add(1154, "Educación Pública");
        estaciones.add(1155, "Luis Galvani D.T");
        estaciones.add(1156, "San Andrés Tomatlán  D.T");
        estaciones.add(1157, "Prospero García ");
        estaciones.add(1158, "Emiliano Zapata D.T");
        estaciones.add(1159, "Víveros");
        estaciones.add(1160, "Lomas Estrella D.T");
        estaciones.add(1161, "Calle 11 D.T");
        estaciones.add(1162, "Periférico Oriente D.T");
        estaciones.add(1163, "Tezonco D.T");
        estaciones.add(1164, "Olivos D.T");
        estaciones.add(1165, "Nopalera D.T");
        estaciones.add(1166, "Zapotitlán D.T");
        estaciones.add(1167, "Tlaltenco D.T");
        estaciones.add(1168, "Tláhuac D.T");
        estaciones.add(1169, "Terminal Zacatenco");
        estaciones.add(1170, "Edif. 1 ESIME");
        estaciones.add(1171, "Edif. 2 ESIME");
        estaciones.add(1172, "Edif. 4 ESIME");
        estaciones.add(1173, "Edif. 6 ESIQIE");
        estaciones.add(1174, "Edif. 8 ESIQIE");
        estaciones.add(1175, "Edif. 10 ESIA");
        estaciones.add(1176, "Edif. 11 ESIA");
        estaciones.add(1177, "Biblioteca ESIA, Luis Enrique Erro");
        estaciones.add(1178, "Secretaria de Extensión y Difusión ");
        estaciones.add(1179, "Central de Inteligencia de Computo");
        estaciones.add(1180, "Ma. Luisa Stampa Ortigoza");
        estaciones.add(1181, "ESCOM");
        estaciones.add(1182, "Torres Lindavista");
        estaciones.add(1183, "J. Othón de Mendizábal");
        estaciones.add(1184, "Politécnico Poniente");
        estaciones.add(1185, "Montevideo");
        estaciones.add(1186, "Otavalo");
        estaciones.add(1187, "Wilfrido Massieu");
        estaciones.add(1188, "Politécnico Oriente");
        estaciones.add(1189, "J. Othón de Mendizábal");
        estaciones.add(1190, "Av. Central ");
        estaciones.add(1191, "Juan de Dios Batiz");
        estaciones.add(1192, "ESCOM");
        estaciones.add(1193, "Ma. Luisa Stampa Ortigoza");
        estaciones.add(1194, "Central de Inteligencia de Computo");
        estaciones.add(1195, "Cancha de Entrenamiento Pieles Rojas");
        estaciones.add(1196, "Edif. 11 ESIA");
        estaciones.add(1197, "Manuel de Anda y Barredo");
        estaciones.add(1198, "Edif. 8 ESIQIE");
        estaciones.add(1199, "Edif. 6 ESIQIE");
        estaciones.add(1200, "Edif. 4 ESIME");
        estaciones.add(1201, "Edif. 2 ESIME");
        estaciones.add(1202, "Edif. 1 ESIME");
        estaciones.add(1203, "CENLEX");
        estaciones.add(1204, "Planetario");
        estaciones.add(1205, "Centro de Formación e Innovación Educativa");
        estaciones.add(1206, "Escuela Nacional de Ciencias Biológicas ");
        estaciones.add(1207, "Cerrada Manuel Stampa");
        estaciones.add(1208, "Salvatierra");
        estaciones.add(1209, "Vía CETI");
        estaciones.add(1210, "Centro de Formación Educativa");
        estaciones.add(1211, "Av. 45 mts.");
        estaciones.add(1212, "Huacho");
        estaciones.add(1213, "Oroya");
        estaciones.add(1214, "El Queso");
        estaciones.add(1215, "Terminal Zacatenco");
        estaciones.add(1216, "Villa de Cortés D.T");
        estaciones.add(1217, "Javier Sorondo D.T");
        estaciones.add(1218, "José Revueltas");
        estaciones.add(1219, "Laura");
        estaciones.add(1220, "Playa Hornos");
        estaciones.add(1221, "Playa Ola Verde D.T");
        estaciones.add(1222, "Andrés Molina Enríquez ");
        estaciones.add(1223, "Playa Miramar D.T");
        estaciones.add(1224, "Playa Mirador D.T");
        estaciones.add(1225, "Playa Cortés D.T");
        estaciones.add(1226, "De la Viga D.T");
        estaciones.add(1227, "Martíres de Río Blanco D.T");
        estaciones.add(1228, "Antropólogos D.T");
        estaciones.add(1229, "V. Gómez Farías ");
        estaciones.add(1230, "Juan Álvarez ");
        estaciones.add(1231, "Estudios Churubusco");
        estaciones.add(1232, "Canal de Tezontle");
        estaciones.add(1233, "Estudios San Ángel INN");
        estaciones.add(1234, "Iztacalco");
        estaciones.add(1235, "Corales");
        estaciones.add(1236, "Zacate");
        estaciones.add(1237, "Maguey");
        estaciones.add(1238, "Río Churubusco");
        estaciones.add(1239, "Girasol");
        estaciones.add(1240, "Apatlaco D.T");
        estaciones.add(1241, "Tepalcates D.T");
        estaciones.add(1242, "FS-Miguel Lerdo de Tejada ");
        estaciones.add(1243, "FS-Francisco Zarco");
        estaciones.add(1244, "Tepalcates D.V.C");
        estaciones.add(1245, "Apatlaco  D.V.C");
        estaciones.add(1246, "Chinampas");
        estaciones.add(1247, "Raíz del Agua");
        estaciones.add(1248, "Joaquín García");
        estaciones.add(1249, "José Maria Mata");
        estaciones.add(1250, "Melchor O. Campo");
        estaciones.add(1251, "Mártires de Río Blanco  D.V.C");
        estaciones.add(1252, "De la Viga  D.V.C");
        estaciones.add(1253, "Playa Cortés D.V.C");
        estaciones.add(1254, "Playa Mirador D.V.C");
        estaciones.add(1255, "Playa Miramar D.V.C");
        estaciones.add(1256, "Playa Ola Verde D.V.C");
        estaciones.add(1257, "Plutarco Elías Calles ");
        estaciones.add(1258, "Javier Sorondo D.V.C");
        estaciones.add(1259, "Virginia");
        estaciones.add(1260, "Tlalpan");
        estaciones.add(1261, "Villa de Cortés D.V.C");
        estaciones.add(1262, "FS - Ingenieros Mecánicos");
        estaciones.add(1263, "FS - Apologistas");
        estaciones.add(1264, "FS - Metro Apatlaco");
        estaciones.add(1265, "FS - Antropólogos D.V.C");
        estaciones.add(1266, "Santa Marta");
        estaciones.add(1267, "Acahualtepec");
        estaciones.add(1268, "Acatitlán");
        estaciones.add(1269, "Tecoloxtitlán");
        estaciones.add(1270, "Iztahuatzín");
        estaciones.add(1271, "Atzintlí");
        estaciones.add(1272, "Aztahuacan");
        estaciones.add(1273, "Papalotl");
        estaciones.add(1274, "Meyehualco");
        estaciones.add(1275, "Deportivo Santa Cruz");
        estaciones.add(1276, "Tulipán");
        estaciones.add(1277, "Constitucion de 1917");
    
        
        
        for (Nodos nodo : nodosUsados) {
            int nodo1 = nodo.nodo1;
            String nombre1 = estaciones.get(nodo1-1);
            System.out.println("-" + estaciones.get(nodo1 - 1)); // Ajusta el índice
            
        }
        
        System.out.println("-" + estaciones.get(nodoFinal - 1)); // Nodo final
        
    }

    private static int calcularPrecio(List<Nodos> nodosUsados) 
    {
        int cmetro = 0;
        int cmetrobus = 0;
        int ccablebus = 0;
        int tmetro = 0;
        int tmetrobus = 0;
        int tcablebus = 0;
        int costo = 0;
        boolean metro = false;
        
        boolean metrobusL1 = false;
        boolean metrobusL2 = false;
        boolean metrobusL3 = false;
        boolean metrobusL4 = false;
        boolean metrobusL5 = false;
        boolean metrobusL6 = false;
        boolean metrobusL7 = false;
        
        boolean cablebusL1 = false;
        boolean cablebusL2 = false;
        
        
        for (Nodos nodo : nodosUsados)
        {
            int nodo1 = nodo.nodo1;
            if (nodo1 >= 1 && nodo1 <= 163)
            {
                if(!metro)
                {
                    metro = true;
                    metrobusL1 = false;
                    metrobusL2 = false; 
                    metrobusL3 = false; 
                    metrobusL4 = false;
                    metrobusL5 = false;
                    metrobusL6 = false;
                    metrobusL7 = false;
                    cablebusL1 = false; 
                    cablebusL2 = false;
                    costo += 5;
                    cmetro += 1;
                    tmetro += 1;
                }
            }
            if(nodo1 >= 164 && nodo1 <= 209)
            {
                if(!metrobusL1)
                {
                    metro = false;
                    metrobusL1 = true;
                    metrobusL2 = false;
                    metrobusL3 = false;
                    metrobusL4 = false;
                    metrobusL5 = false;
                    metrobusL6 = false;
                    metrobusL7 = false;
                    cablebusL1 = false;
                    cablebusL2 = false;
                    costo += 6;
                    cmetrobus += 6;
                    tmetrobus += 1;
                }
            }
            if (nodo1 >= 210 && nodo1 <= 246)
            {
                if(!metrobusL2)
                {
                    metro = false;
                    metrobusL1 = false;
                    metrobusL2 = true;
                    metrobusL3 = false;
                    metrobusL4 = false;
                    metrobusL5 = false;
                    metrobusL6 = false;
                    metrobusL7 = false;
                    cablebusL1 = false;
                    cablebusL2 = false;
                    costo += 6;
                    cmetrobus += 6;
                    tmetrobus += 1;
                }            
            }
            if (nodo1 >= 247 && nodo1 <= 284)
            {
                if(!metrobusL3)
                {
                    metro = false;
                    metrobusL1 = false;
                    metrobusL2 = false;
                    metrobusL3 = true;
                    metrobusL4 = false;
                    metrobusL5 = false;
                    metrobusL6 = false;
                    metrobusL7 = false;
                    cablebusL1 = false;
                    cablebusL2 = false;
                    costo += 6;
                    cmetrobus += 6;
                    tmetrobus += 1;
                }            
            }    
            if (nodo1 >= 285 && nodo1 <= 326)
            {
                boolean aeropuerto = false;
                boolean cobroextra = false;
                if(!metrobusL4)
                {
                    metro = false;
                    metrobusL1 = false;
                    metrobusL2 = false;
                    metrobusL3 = false;
                    metrobusL4 = true;
                    metrobusL5 = false;
                    metrobusL6 = false;
                    metrobusL7 = false;
                    cablebusL1 = false;
                    cablebusL2 = false;
                    tmetrobus += 1;
                    if ( nodo1 == 288 || nodo1 == 299)
                    {
                        aeropuerto = true;
                    }
                    if(aeropuerto && !cobroextra)
                    {
                        cobroextra = true;
                        costo += 30;
                        cmetrobus += 30;
                    }
                    else
                    {
                        costo += 6;
                         cmetrobus += 6;
                    }
                    
                }            
            }
            if (nodo1 >= 327 && nodo1 <= 377)
            {
                if(!metrobusL5)
                {
                    metro = false;
                    metrobusL1 = false;
                    metrobusL2 = false;
                    metrobusL3 = false;
                    metrobusL4 = false;
                    metrobusL5 = true;
                    metrobusL6 = false;
                    metrobusL7 = false;
                    cablebusL1 = false;
                    cablebusL2 = false;
                    costo += 6;
                    cmetrobus += 6;
                    tmetrobus += 1;
                }            
            }
            if (nodo1 >= 378 && nodo1 <= 414)
            {
                if(!metrobusL6)
                {
                    metro = false;
                    metrobusL1 = false;
                    metrobusL2 = false;
                    metrobusL3 = false;
                    metrobusL4 = false;
                    metrobusL5 = false;
                    metrobusL6 = true;
                    metrobusL7 = false;
                    cablebusL1 = false;
                    cablebusL2 = false;
                    costo += 6;
                    cmetrobus += 6;
                    tmetrobus += 1;
                }            
            }
            if (nodo1 >= 415 && nodo1 <= 445)
            {
                if(!metrobusL7)
                {
                    metro = false;
                    metrobusL1 = false;
                    metrobusL2 = false;
                    metrobusL3 = false;
                    metrobusL4 = false;
                    metrobusL5 = false;
                    metrobusL6 = false;
                    metrobusL7 = true;
                    cablebusL1 = false;
                    cablebusL2 = false;
                    costo += 6;
                    cmetrobus += 6;
                    tmetrobus += 1;
                }            
            }
            if (nodo1 >= 446 && nodo1 <= 451)
            {
                if(!cablebusL1)
                {
                    metro = false;
                    metrobusL1 = false;
                    metrobusL2 = false;
                    metrobusL3 = false;
                    metrobusL4 = false;
                    metrobusL5 = false;
                    metrobusL6 = false;
                    metrobusL7 = false;
                    cablebusL1 = true;
                    cablebusL2 = false;
                    costo += 7;
                    ccablebus +=1;
                    tcablebus +=1;
                }            
            }
            if (nodo1 >= 452 && nodo1 <= 458)
            {
                if(!cablebusL2)
                {
                    metro = false;
                    metrobusL1 = false;
                    metrobusL2 = false;
                    metrobusL3 = false;
                    metrobusL4 = false;
                    metrobusL5 = false;
                    metrobusL6 = false;
                    metrobusL7 = false;
                    cablebusL1 = false;
                    cablebusL2 = true;
                    costo += 7;
                    ccablebus +=1;
                    tcablebus +=1;
                }            
            }
                
        }
        System.out.println("------------------------------Transbordes------------------------------");
        System.out.println("Transbordes en metro: " + tmetro );
        System.out.println("Transbordes en metrobus: " + tmetrobus );
        System.out.println("Transbordes en cablebus: " + tcablebus );
        System.out.println("---------------------------------Costos--------------------------------");
        System.out.println("costo metro: $" + cmetro*5 + " MXN.");
        System.out.println("costo metrobus: $" + cmetrobus + " MXN.");
        System.out.println("costo cablebus: $" + ccablebus*7 + " MXN.");
       
        return costo;
         
    }
}

class Nodos {
    int nodo1;
    int nodo2;
    double peso;

    public Nodos(int nodo1, int nodo2, double peso) {
        this.nodo1 = nodo1;
        this.nodo2 = nodo2;
        this.peso = peso;
    }
}
