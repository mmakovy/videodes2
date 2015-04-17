# GUI #

**Hlavne okno**
http://videodes2.googlecode.com/svn/trunk/MainFrame2.PNG

V tabulkach su zobrazene videa z databaze. Ak je textovy retazec dlhsi ako sirka stlpca, je pri ukazani kurzorom na bunku zobrazeny cely retazec. Z hlavneho okna je mozne pridavat nove video, zmazat video z databaze, vypisat vsetky videa z databaze, vyhladavat podla nazvu filmu, rezisera, herca, roku vydania, zanru a krajiny, importovat z ODS suboru a exportovat do ODS suboru.

**Pridanie videa**

![http://videodes2.googlecode.com/svn/trunk/addVideo.png](http://videodes2.googlecode.com/svn/trunk/addVideo.png)
![http://videodes2.googlecode.com/svn/trunk/imdb.png](http://videodes2.googlecode.com/svn/trunk/imdb.png)

Pridanie videa je mozne dvoma sposobmi. Bud manualne, kedy je potrebne vyplnit vsetky hodnoty a vybrat aspon jeden zaner alebo je mozne pouzit IMDB parser, ktory vsetky informacie o filme (okrem krajiny) ziska zo stranky imdb.com.

**Zmazanie videa**
http://videodes2.googlecode.com/svn/trunk/delete.PNG

Zmazanie videa z databaze prebieha tak, ze najskor je potrebne v tabulke oznacit dane videa (viac pomocou multiselectu), kliknut na "Delete video" a potvrdit svoje rozhodnutie v dialogu.

**Vyhladavanie videa**

http://videodes2.googlecode.com/svn/trunk/search.PNG
![http://videodes2.googlecode.com/svn/trunk/genres.png](http://videodes2.googlecode.com/svn/trunk/genres.png)

Vyhladavanie je mozne podla nazvu filmu, rezisera, herca, roku vydania, krajiny zadanim hladanej hodnoty. Nie je potrebne zadavat presnu hodnotu, staci cast retazca. Vyhladavanie nie je case sensitive. Pri hladani podla zanru je potrebne oznacit zanre, ktore chceme vyhladat.

**Import a export databaze**

![http://videodes2.googlecode.com/svn/trunk/import.png](http://videodes2.googlecode.com/svn/trunk/import.png)

Import a export prebieha z/do formatu ODS. Pri importe je mozny multiselect. Pri importe je potrebne, aby tabulka obsahovala vsetky atributy v zahlavi inak nebude subor importovany.