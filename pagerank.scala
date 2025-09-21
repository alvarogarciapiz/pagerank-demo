import org.apache.spark.graphx._
import org.apache.spark.rdd.RDD

/* Se lee el CSV, se crean los enlaces (aristas) del grafo y se
convierten los datos a Long para que GraphX pueda trabajar con ellos */
val dataset = spark.read.option("header", "true").option("inferSchema", "true").csv("dataset.csv")

val enlaces: RDD[Edge[Int]] = dataset.rdd.map(fila => {
  val origen = fila.getAs[Int]("web_origen").toLong
  val destino = fila.getAs[Int]("web_destino").toLong
  Edge(origen, destino, 1)
})

// Muestra de los enlaces
println("\nMuestra de los enlaces:")
enlaces.take(5).foreach(println)

// Crear los vértices a partir de los enlaces (aristas)
val vertices: RDD[(VertexId, Int)] = enlaces.flatMap(arista => Seq((arista.srcId, 1), (arista.dstId, 1))).distinct()

// Construyo el grafo
val grafo = Graph(vertices, enlaces)

// Calculo el PageRank con una tolerancia de 0.001 (GraphX iterará el algoritmo hasta que la diferencia entre dos iteraciones consecutivas sea menor que 0.001)
val pagerank = grafo.pageRank(0.001).vertices

// Muestra de los resultados
println("\nMuestra de resultados del PageRank:")
pagerank.take(5).foreach(println)

// Obtengo las 10 páginas con más y menos PageRank para verlos
val top10 = pagerank.sortBy(_._2, ascending = false).take(10)
val peores10 = pagerank.sortBy(_._2, ascending = true).take(10)

// Se muestran los resultados de las 10 páginas con más y menos PageRank
println("\n10 páginas con un PageRank más alto:")
top10.foreach { case (id, pagerank) =>
  println(s"  > La web $id tiene un PageRank de: $pagerank")
}

println("\n10 páginas con un PageRank más bajo:")
peores10.foreach { case (id, pagerank) =>
  println(s"  > La web $id tiene un PageRank de: $pagerank")
}