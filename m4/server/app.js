const express = require('express');
const fs = require('fs');
const path = require('path');
const hbs = require('hbs');
const MySQL = require('./utilsMySQL');

const app = express();
const port = 3000;

// Detectar si estem al Proxmox (si és pm2)
const isProxmox = !!process.env.PM2_HOME;

const db = new MySQL();

if (!isProxmox) {
  db.init({
    host: '127.0.0.1',
    port: 3306,
    user: 'root',
    password: 'aws',
    database: 'dominion'
  });
} else {
  db.init({
    host: '127.0.0.1',
    port: 3306,
    user: 'super',
    password: '1234',
    database: 'dominion'
  });
}

// Static files
app.use(express.static('public'));
app.use(express.urlencoded({ extended: true }));

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
app.set('view options', { layout: 'layouts/main' });

hbs.registerHelper('eq', (a, b) => a == b);
hbs.registerHelper('gt', (a, b) => a > b);

hbs.registerPartials(path.join(__dirname, 'views', 'partials'));

// routes
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

app.get('/programadores', async (req, res) => {
  res.render('programadores');
});

app.get('/civilizaciones', async (req, res) => {
  try {

    const pagina = parseInt(req.query.pagina);

    // ==================================================
    // INDEX MODE
    // ==================================================
    if (!pagina) {

      const indexPagina = parseInt(req.query.index) || 1;
      const limitIndex = 20;
      const offsetIndex = (indexPagina - 1) * limitIndex;

      // TOTAL CIVILIZACIONES
      const totalRows = await db.query(`
        SELECT COUNT(*) AS total FROM CIVILIZATION;
      `);

      const totalIndex = totalRows[0].total;
      const totalPaginasIndex = Math.ceil(totalIndex / limitIndex);

      // DATA INDEX
      const civilizacionesRows = await db.query(`
        SELECT
          civilization_id AS id,
          name AS nombre
        FROM CIVILIZATION
        ORDER BY civilization_id
        LIMIT ${limitIndex} OFFSET ${offsetIndex};
      `);

      const civilizacionesJson = db.table_to_json(civilizacionesRows, {
        id: 'number',
        nombre: 'string'
      });

      // PAGINACIÓN
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

      const data = {
        modo: 'index',
        civilizaciones: civilizacionesJson,
        paginasIndex,
        anteriorIndex: indexPagina > 1 ? indexPagina - 1 : null,
        siguienteIndex: indexPagina < totalPaginasIndex ? indexPagina + 1 : null,
        paginaActual: indexPagina
      };

      return res.render('civilizaciones', data);
    }

    // ==================================================
    // DETALLE MODE
    // ==================================================

    const paginaId = parseInt(req.query.pagina, 10);

    if (!Number.isInteger(paginaId) || paginaId <= 0) {
      return res.status(400).send('Paràmetre id invàlid');
    }

    const civilizacionRows = await db.query(`
      SELECT
        civilization_id AS id,
        name AS nombre,
        wood_amount,
        iron_amount,
        food_amount,
        mana_amount,
        technology_defense_level,
        technology_attack_level,
        game_over
      FROM CIVILIZATION
      WHERE civilization_id = ${paginaId}
      LIMIT 1;
    `);

    if (!civilizacionRows || civilizacionRows.length === 0) {
      return res.status(404).send('No existe esa civilización');
    }

    const civilizacionJson = db.table_to_json(civilizacionRows, {
      id: 'number',
      nombre: 'string',
      wood_amount: 'number',
      iron_amount: 'number',
      food_amount: 'number',
      mana_amount: 'number',
      technology_defense_level: 'number',
      technology_attack_level: 'number',
      game_over: 'number'
    })[0];

    const totalRows = await db.query(`
      SELECT COUNT(*) AS total FROM CIVILIZATION;
    `);

    const totalPaginas = totalRows[0].total;

    // PAGINACIÓN DETALLE
    const paginas = [];

    let inicio = paginaId - 2;
    let fin = paginaId + 2;

    if (inicio < 1) {
      inicio = 1;
      fin = 5;
    }

    if (fin > totalPaginas) {
      fin = totalPaginas;
      inicio = totalPaginas - 4;
    }

    if (inicio < 1) inicio = 1;

    for (let i = inicio; i <= fin; i++) {
      paginas.push({
        numero: i,
        activa: i === paginaId
      });
    }

    const anterior = paginaId > 1 ? paginaId - 1 : null;
    const siguiente = paginaId < totalPaginas ? paginaId + 1 : null;

    const data = {
      modo: 'detalle',
      civilizacion: civilizacionJson,
      paginas,
      anterior,
      siguiente
    };

    return res.render('civilizaciones', data);

  } catch (err) {
    console.error(err);
    res.status(500).send('Error consultant la base de dades');
  }
});

// ======================================================
// SERVER
// ======================================================

const httpServer = app.listen(port, () => {
  console.log(`http://localhost:${port}`);
  console.log(`http://localhost:${port}/batallas`);
  console.log(`http://localhost:${port}/civilizaciones`);
  console.log(`http://localhost:${port}/programadores`);
});

process.on('SIGINT', async () => {
  httpServer.close();
  process.exit(0);
});