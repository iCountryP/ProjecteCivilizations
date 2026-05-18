const express = require('express');
const fs = require('fs');
const path = require('path');
const hbs = require('hbs');
//const MySQL = require('./utilsMySQL');

const app = express();
const port = 3000;



// ======================================================
// DATOS FAKE
// ======================================================

const civilizacionesFake = [];

for (let i = 1; i <= 100; i++) {

    civilizacionesFake.push({
        id: i,
        nombre: `Civilización ${i}`,
        descripcion: `Descripción de la civilización ${i}`,
        historia: `Historia completa de la civilización ${i}`,
    });

}

// Detectar si estem al Proxmox (si és pm2)
const isProxmox = !!process.env.PM2_HOME;
/*
// Iniciar connexió MySQL
const db = new MySQL();
if (!isProxmox) {
  db.init({
    host: '127.0.0.1',
    port: 3306,
    user: 'root',
    password: 'aws',
    database: 'sakila'
  });
} else {
  db.init({
    host: '127.0.0.1',
    port: 3306,
    user: 'super',
    password: '1234',
    database: 'escola'
  });
}
*/
// Static files - ONLY ONCE
app.use(express.static('public'))
app.use(express.urlencoded({ extended: true }))

// Disable cache
app.use((req, res, next) => {
  res.setHeader('Cache-Control', 'no-store, no-cache, must-revalidate, proxy-revalidate');
  res.setHeader('Pragma', 'no-cache');
  res.setHeader('Expires', '0');
  res.setHeader('Surrogate-Control', 'no-store');
  next();
});

// Handlebars
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'hbs');
app.set('view options', {layout: 'layouts/main'});

// Registrar "Helpers .hbs" aquí
hbs.registerHelper('eq', (a, b) => a == b);
hbs.registerHelper('gt', (a, b) => a > b);

// Partials de Handlebars
hbs.registerPartials(path.join(__dirname, 'views', 'partials'));

// Route
app.get('/', async (req, res) => {
  res.render('index');
});

app.get('/batallas', async (req, res) => {
  res.render('batallas');
});


app.get('/batallasCivil', async (req, res) => {
  res.render('batallasCivil');
});

app.get('/informe', async (req, res) => {
  res.render('informe');
});
/*
app.get('/civilizaciones', async (req, res) => {
  res.render('civilizaciones');
});
*/


// ======================================================
// RUTA PRINCIPAL
// ======================================================

app.get('/civilizaciones', (req, res) => {

    const pagina = parseInt(req.query.pagina);

    // ==================================================
    // ÍNDICE PAGINADO
    // ==================================================

    if (!pagina) {

        // página del índice
        const indexPagina = parseInt(req.query.index) || 1;

        // máximo 10 nombres por página
        const limitIndex = 20;

        // OFFSET
        const offsetIndex = (indexPagina - 1) * limitIndex;

        // slice del índice
        const civilizacionesIndex = civilizacionesFake.slice(
            offsetIndex,
            offsetIndex + limitIndex
        );

        // total páginas índice
        const totalIndex = civilizacionesFake.length;

        const totalPaginasIndex = Math.ceil(
            totalIndex / limitIndex
        );

        // navegación índice
        const paginasIndex = [];

        let inicio = indexPagina - 2;
        let fin = indexPagina + 2;

        if (inicio < 1) {
            inicio = 1;
            fin = 5;
        }

        if (fin > totalPaginasIndex) {
            fin = totalPaginasIndex;
            inicio = totalPaginasIndex - 4;
        }

        if (inicio < 1) inicio = 1;

        for (let i = inicio; i <= fin; i++) {

            paginasIndex.push({
                numero: i,
                activa: i === indexPagina
            });

        }

        return res.render('civilizaciones', {

            modo: 'index',

            civilizaciones: civilizacionesIndex,

            paginasIndex,

            anteriorIndex:
                indexPagina > 1
                    ? indexPagina - 1
                    : null,

            siguienteIndex:
                indexPagina < totalPaginasIndex
                    ? indexPagina + 1
                    : null

        });

    }

    // =========================
    // DETALLE
    // =========================

    const civilizacion = civilizacionesFake.find(
        c => c.id === pagina
    );

    if (!civilizacion) {
        return res.status(404).send('No existe esa civilización');
    }

    const total = civilizacionesFake.length;
    const totalPaginas = total;

    const paginas = [];

    // ===============================
    // VENTANA: -2 ... +2
    // ===============================

    let inicio = pagina - 2;
    let fin = pagina + 2;

    // Ajustes si estás cerca del inicio
    if (inicio < 1) {
        inicio = 1;
        fin = 5;
    }

    // Ajustes si estás cerca del final
    if (fin > totalPaginas) {
        fin = totalPaginas;
        inicio = totalPaginas - 4;
    }

    // Evitar negativos
    if (inicio < 1) inicio = 1;

    for (let i = inicio; i <= fin; i++) {
        paginas.push({
            numero: i,
            activa: i === pagina
        });
    }

    const anterior = pagina > 1 ? pagina - 1 : null;
    const siguiente = pagina < totalPaginas ? pagina + 1 : null;

    res.render('civilizaciones', {
        modo: 'detalle',
        civilizacion,
        anterior,
        siguiente,
        paginas
    });

});

app.get('/programadores', async (req, res) => {
  res.render('programadores');
});

// Start server
const httpServer = app.listen(port, () => {
  console.log(`http://localhost:${port}`);
  console.log(`http://localhost:${port}/batallas`);
  console.log(`http://localhost:${port}/civilizaciones`);
  console.log(`http://localhost:${port}/programadores`);
});

// Graceful shutdown
process.on('SIGINT', async () => {
  //await db.end();
  httpServer.close();
  process.exit(0);
});