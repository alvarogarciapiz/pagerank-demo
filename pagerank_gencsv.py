import csv
import random

numero_webs = 100
numero_enlaces = 1000
enlaces = set()

while len(enlaces) < numero_enlaces:
    web_origen = random.randint(0, numero_webs - 1)
    web_destino = random.randint(0, numero_webs - 1)
    if web_origen != web_destino:
        enlaces.add((web_origen, web_destino))

with open('dataset.csv', mode='w', newline='') as fichero:
    writer = csv.writer(fichero)
    writer.writerow(['web_origen', 'web_destino'])
    for enlace in enlaces:
        writer.writerow(enlace)