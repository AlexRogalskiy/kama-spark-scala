### ДЗ предварительная инструкция

1) Скачать и установить Idea Community - https://www.jetbrains.com/idea/download/#section=windows
2) Установить плагин Скала
3) Скачать и установить Java JDK 11 - https://www.oracle.com/java/technologies/javase-jdk11-downloads.html
4) Скачать и установить git - https://git-scm.com/downloads
5) Скачать и учтановить локально дистрибутив Hadoop (инструкция для Windows - https://www.datasciencecentral.com/profiles/blogs/how-to-install-and-run-hadoop-on-windows-for-beginners )
6) Cкачать стартовый проект с Гитхаб c помощью команды
7) git clone https://github.com/vadopolski/otus-hadoop-homework
8) Запустить Idea и открыть скаченный проект File -> Open -> project folder/build.sbt
9) Открыть в проекте  файл src/main/scala/homework2/DataApiHomeWorkTaxi.scala
10) запустить его, Ctrl + Shift10
11) Скачать и установить docker-compose
12) Из корневой папки проекта запустить сделать запуск - docker-compose up


### ДЗ основная инструкция и задания к занятию по Spark Data API:

**Цель:** Выполнив домашнее задание Вы получите опыт работы с RDD API, DataFrame API,Dataset API. Научитесь строить аналитическую витрину на основе сырых данных, используя Spark и различные API.

#### Основная инструкция задание 1:
Загрузить данные в первый DataFrame из файла с фактическими данными поездок в Parquet (src/main/resources/data/yellow_taxi_jan_25_2018).
Загрузить данные во второй DataFrame из файла со справочными данными поездок в csv (src/main/resources/data/taxi_zones.csv)
С помощью DSL построить таблицу, которая покажет какие районы самые популярные для заказов. Результат вывести на экран и записать в файл Паркет.

**Результат:**
В консоли должны появиться данные с результирующей таблицей, в файловой системе должен появиться файл.

#### Основная инструкция задание 2:
Загрузить данные в RDD из файла со справочными данными поездок в csv (src/main/resources/data/taxi_zones.csv)

Загрузить данные в RDD из файла с фактическими данными поездок в Parquet (src/main/resources/data/yellow_taxi_jan_25_2018).
С помощью lambda построить таблицу, которая покажет В какое время происходит больше всего вызовов. Результат вывести на экран и в txt файл c пробелами.

**Результат:**
В консоли должны появиться данные с результирующей таблицей, в файловой системе должен появиться файл.
Решение оформить в github gist.



saberov
skryazhevsky
androsova
kolosov
demidov
badmaev
asylguzhin
sadovnikov
trofimov
krasilnikov
uvarov
laptenkov
popkov
loy
katkov

