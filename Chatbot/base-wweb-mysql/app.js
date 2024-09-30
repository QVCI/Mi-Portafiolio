const { createBot, createProvider, createFlow, addKeyword } = require('@bot-whatsapp/bot');
const QRPortalWeb = require('@bot-whatsapp/portal');
const WebWhatsappProvider = require('@bot-whatsapp/provider/web-whatsapp');
const MySQLAdapter = require('@bot-whatsapp/database/mysql');



const mysql = require('mysql2');


const connection = mysql.createConnection
({
  host: MYSQL_DB_HOST,
  user: MYSQL_DB_USER,
  password: MYSQL_DB_PASSWORD,
  database: MYSQL_DB_NAME,
  port: MYSQL_DB_PORT,
});


connection.connect((err) => 
  {
  if (err) 
    {
    console.error('Error conectando a la base de datos:', err);
    return;
  }
  console.log('Conectado a la base de datos.');
});

const database = new MySQLAdapter
({
  host: MYSQL_DB_HOST,
  user: MYSQL_DB_USER,
  password: MYSQL_DB_PASSWORD,
  database: MYSQL_DB_NAME,
  port: MYSQL_DB_PORT,
});


let nombre, edad, sexo, sitsen, estudios, ocupacion, entidad, municipio, texto;
let p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, papoyo;
let Validacion = false;
let respuestas;

let DatosUsuarios = {};



const flowEncuesta = addKeyword(['Acepto'])
  .addAnswer('Primero unas preguntas básicas para comenzar:')
  .addAnswer("En caso de contestarse de manera incorrecta, se reenviara la pregunta.", { capture: false })
  .addAnswer('¿Podrías decirme tu nombre?', { capture: true }, (ctx) => 
    {
    GuardarDatosUsuarios(ctx.from, { nombre: ctx.body })
  })
  .addAnswer('Ingresa tu edad', { capture: true }, (ctx, { fallBack }) => 
    {
    try 
    {
      let anos = parseInt(ctx.body); 
      if (anos >= 5 && anos <= 100) 
      {
        edad = anos; 

        GuardarDatosUsuarios(ctx.from, { edad: edad })
      }
      else 
      {
        return fallBack(); 
      }
    }
    catch (error) 
    {
      return fallBack(); 
    }

  })
  .addAnswer('Ingresa tu género (Masculino / Femenino / Otro)', { capture: true }, (ctx, { fallBack }) => {
    GuardarDatosUsuarios(ctx.from, { sexo: ctx.body })
  })
  .addAnswer('¿Cuál es tu estado civil?', { capture: true }, (ctx) => {
    GuardarDatosUsuarios(ctx.from, { sitsen: ctx.body })
  })
  .addAnswer('¿Cuál es tu grado de estudios?', { capture: true }, (ctx) => {
    GuardarDatosUsuarios(ctx.from, { estudios: ctx.body })
  })
  .addAnswer('¿Cuál es tu ocupación?', { capture: true }, (ctx) => {
    GuardarDatosUsuarios(ctx.from, { ocupacion: ctx.body })
  })
  .addAnswer('¿Cuál es tu entidad de residencia?', { capture: true }, (ctx) => {
    GuardarDatosUsuarios(ctx.from, { entidad: ctx.body })
  })
  .addAnswer('¿Cuál es tu municipio de residencia?', { capture: true }, (ctx) => {
    GuardarDatosUsuarios(ctx.from, { municipio: ctx.body })
  })
 
  .addAnswer('Empecemos con el Test.', { capture: false })
  .addAnswer("En caso de contestarse de manera incorrecta, se reenviara la pregunta.", { capture: false })
  //se ha omitido la información recabada
  .addAnswer('Por último, dime como te has sentido los últimos días', { capture: true }, async (ctx) => {
    GuardarDatosUsuarios(ctx.from, { texto: ctx.body })
    const datosUsuario = ObtenerDatosUsuarios(ctx.from);

    console.log(`
        Nombre: ${datosUsuario.nombre}
        Edad: ${datosUsuario.edad}
        Sexo: ${datosUsuario.sexo}
        Situación Sentimental: ${datosUsuario.sitsen}
        Estudios: ${datosUsuario.estudios}
        Ocupación: ${datosUsuario.ocupacion}
        Entidad: ${datosUsuario.entidad}
        Municipio: ${datosUsuario.municipio}
        Texto: ${datosUsuario.texto}
        Validación: ${datosUsuario.Validacion}
        P1: ${datosUsuario.p1}
        P2: ${datosUsuario.p2}
        P3: ${datosUsuario.p3}
        P4: ${datosUsuario.p4}
        P5: ${datosUsuario.p5}
        P6: ${datosUsuario.p6}
        P7: ${datosUsuario.p7}
        P8: ${datosUsuario.p8}
        P9: ${datosUsuario.p9}
        P10: ${datosUsuario.p10}
        P11: ${datosUsuario.p11}
        P12: ${datosUsuario.p12}
        P13: ${datosUsuario.p13}
        P14: ${datosUsuario.p14}
        P15: ${datosUsuario.p15}
        P16: ${datosUsuario.p16}
        P17: ${datosUsuario.p17}
        P18: ${datosUsuario.p18}
        P19: ${datosUsuario.p19}
        P20: ${datosUsuario.p20}
        P21: ${datosUsuario.p21}
        `);

    if (datosUsuario.Validacion) {
      console.log("empieza el guardado")
      connection.query(
        'INSERT INTO ENCUESTA (NOMBRE, EDAD, SEXO, SITUACIONSENTIMENTAL, ESTUDIOS, OCUPACION, ENTIDAD, MUNICIPIO, TEXTO, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)',
        [datosUsuario.nombre, datosUsuario.edad, datosUsuario.sexo, datosUsuario.sitsen, datosUsuario.estudios, datosUsuario.ocupacion,
        datosUsuario.entidad, datosUsuario.municipio, datosUsuario.texto, datosUsuario.p1, datosUsuario.p2, datosUsuario.p3,
        datosUsuario.p4, datosUsuario.p5, datosUsuario.p6, datosUsuario.p7, datosUsuario.p8, datosUsuario.p9, datosUsuario.p10,
        datosUsuario.p11, datosUsuario.p12, datosUsuario.p13, datosUsuario.p14, datosUsuario.p15, datosUsuario.p16, datosUsuario.p17,
        datosUsuario.p18, datosUsuario.p19, datosUsuario.p20, datosUsuario.p21],
        (error, results) => {
          if (error) {
            console.error('Error al guardar en la base de datos:', error);
            return '❌ Hubo un error al guardar tus respuestas. Por favor, inténtalo de nuevo más tarde.';
          } else {
            console.log("Se guardó en la BD");
            EliminarDatosUsuario(ctx.from);
            return '✅ Tus respuestas han sido guardadas. ¡Gracias por participar!';
          }
        }
      );

    }
    else {
      console.log("Validación incorrecta, datos no publicados")
    }

  })

  .addAnswer("Muchas gracias por contestar mi encuesta ✌️");



const flowPrincipal = addKeyword(['hola', 'ole', 'alo'])
  .addAnswer(["Hola, Mi nombre es ..."])
  .addAnswer(["... busca recopilar información para ayudar a el diagnostico de la depresión"])
  .addAnswer(["*Politica de privacidad:* .... únicamente almacenara la informació, tu nombre será eliminado de manera posterior, manteniendo el anonimato."])

  .addAnswer(
    [
      '*Acepto* para comenzar',

    ],
    null,
    null,
    [flowEncuesta]
  )


const bot = createBot({
  provider: createProvider(WebWhatsappProvider),
  flow: createFlow([flowPrincipal]),
  database: database
});




QRPortalWeb({ bot });



function procesarRespuesta(cadena, respuestas) {
  
  if (respuestas.hasOwnProperty(cadena)) {
   
    return respuestas[cadena];
  } else {

    return 'Respuesta no válida.';
  }
}

function GuardarDatosUsuarios(NumeroTelefono, dato) {
  if (!DatosUsuarios[NumeroTelefono]) {
    DatosUsuarios[NumeroTelefono] = {};
  }
  DatosUsuarios[NumeroTelefono] = { ...DatosUsuarios[NumeroTelefono], ...dato };
}

function ObtenerDatosUsuarios(NumeroTelefono) {
  return DatosUsuarios[NumeroTelefono] || {};
}
function EliminarDatosUsuario(NumeroTelefono) {
  if (DatosUsuarios[NumeroTelefono]) {
    delete DatosUsuarios[NumeroTelefono];
    console.log(`Datos del usuario con número ${NumeroTelefono} eliminados.`);
  } else {
    console.log(`No se encontraron datos para el usuario con número ${NumeroTelefono}.`);
  }
}
