const express = require('express');
const fs = require('fs');
const path = require('path');
const hbs = require('hbs');
//const MySQL = require('./utilsMySQL');

const app = express();
const port = 3000;

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

app.get('/civilizacion', async (req, res) => {
  res.render('civilizacion');
});

app.get('/programadores', async (req, res) => {
  res.render('programadores');
});

app.get('/informe', async (req, res) => {
  res.render('informe');
});

// Start server
const httpServer = app.listen(port, () => {
  console.log(`http://localhost:${port}`);
  console.log(`http://localhost:${port}/batallas`);
  console.log(`http://localhost:${port}/civilizacion`);
  console.log(`http://localhost:${port}/programadores`);
});

// Graceful shutdown
process.on('SIGINT', async () => {
  //await db.end();
  httpServer.close();
  process.exit(0);
});