# PageRank con Spark y Scala

Este repositorio contiene una práctica sencilla de implementación del algoritmo **PageRank** usando **Apache Spark GraphX** y un dataset generado con Python.

## Archivos
- `pagerank_gencsv.py`: genera un dataset aleatorio de páginas y enlaces (`dataset.csv`).
- `pagerank.scala`: implementa PageRank con GraphX y muestra el top 10 y bottom 10 de páginas.

## Ejecución
1. Genera el dataset:
   ```bash
   python pagerank_gencsv.py
   ```
2.	Ejecuta el algoritmo en Spark:
   ```bash
   spark-shell -i pagerank.scala
   ```
## Más info
Esta práctica forma parte de un post de mi newsletter 👉 [https://www.bulletin.lvrpiz.com](https://www.bulletin.lvrpiz.com)
