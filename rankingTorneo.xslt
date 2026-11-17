<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <!-- Definimos la configuración de salida con un nombre -->
  <xsl:output name="html-output" method="html" encoding="UTF-8" indent="yes"/>

  <xsl:template match="/">
    <!-- Forzamos la creación del archivo con extensión .html -->
    <xsl:result-document href="ranking.html" format="html-output">
      <html>
        <head>
          <title>Ranking del Torneo</title>
          <link rel="stylesheet" href="ranking.css"/>
        </head>
        <body>
          <h1>Clasificación del Torneo</h1>
          <table>
            <thead>
              <tr>
                <th>Posición</th>
                <th>ID</th>
                <th>Nombre</th>
                <th>Puntuación</th>
              </tr>
            </thead>
            <tbody>
              <xsl:for-each select="//jugador">
                <xsl:sort select="puntuacion" data-type="number" order="descending"/>
                <tr>
                  <td><xsl:value-of select="position()"/></td>
                  <td><xsl:value-of select="@id"/></td>
                  <td><xsl:value-of select="nombre"/></td>
                  <td><xsl:value-of select="puntuacion"/></td>
                </tr>
              </xsl:for-each>
            </tbody>
          </table>
        </body>
      </html>
    </xsl:result-document>
  </xsl:template>
</xsl:stylesheet>