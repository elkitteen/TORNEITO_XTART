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
          <style>
            body { font-family: 'Segoe UI', sans-serif; background-color: #f4f7f6; margin: 40px; color: #333; }
            h1 { text-align: center; color: #2c3e50; margin-bottom: 30px; }
            table { width: 70%; max-width: 700px; margin: 0 auto; border-collapse: collapse; background: white; box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1); border-radius: 8px; overflow: hidden; }
            th, td { padding: 14px 20px; text-align: center; }
            th { background-color: #3498db; color: white; text-transform: uppercase; font-size: 13px; letter-spacing: 1px; }
            tr:nth-child(even) { background-color: #f9fbfb; }
            tr:hover { background-color: #f1f4f5; }
            td { border-bottom: 1px solid #e1e8ed; font-size: 15px; }
            tr:nth-child(1) td { font-weight: bold; color: #d35400; }
          </style>
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