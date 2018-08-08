# FCompiler

**Progetto per l'esame di Compilatori ed Interpreti (Informatica Magistrale @ UniBo) A.A. 2017/18**  
Tutte le informazioni relative alle specifiche ed alle scelte implementative effettuate sono contenute nel report presente in `docs/report/`.

### Autori
  * [Giulia Cantini](https://github.com/giulic3) - giulia.cantini2@studio.unibo.it
  * [Matteo Del Vecchio](https://github.com/matteodelv) - matteo.delvecchio6@studio.unibo.it
  * [Simone Preite](https://github.com/simonepreite) - simone.preite@studio.unibo.it

### Istruzioni Eclipse (versione utilizzata: Oxygen.3a (4.7.3a))

1. Decomprimere l'archivio .zip.
2. Aprire Eclipse, selezionare File > Open Projects from File System…, nella schermata successiva cliccare su Directory, selezionare la cartella FCompiler del progetto appena decompresso e premere "Finish".
3. Nel Project Explorer a sinistra, fare click destro sul progetto FCompiler, e selezionare Build Path -> Configure Build Path.
4. Nella tab Libraries cliccare sul bottone Add JARs... e selezionare tutte le librerie presenti in `source/lib/`. Fare Apply (Apply and Close).
5. Eseguire una delle due configurazioni disponibili:
	- **FCompiler Main**: carica il contenuto di un file .fool in input (`code/input.fool` di default), ne mostra l'AST, il codice generato ed il risultato dell'esecuzione sulla macchina virtuale
	- **FCompiler YAML Testing**: carica ed esegue una suite di 56 test (`testSuite.yml`). Per ulteriori informazioni sul testing e sulla scrittura di test personalizzati, fare riferimento alla sezione Testing nel report finale.

**NB:** La console di output di Eclipse NON supporta l'escaping dei caratteri ANSI per i colori; di conseguenza, il risultato della configurazione di test potrebbe mostrare caratteri quali `[32m` o `[0m`. **Tale risultato non ha nessuna ripercussione sull'esecuzione dei test.** In caso si volesse l'output pulito e con il supporto dei colori, è necessario installare un plugin:

1. Avendo aperto Eclipse, cliccare su Help > Eclipse Marketplace...
2. Nella nuova finestra cercare *ANSI Escape in Console* e cliccare su Install.
3. Seguire la procedura per l'installazione, accettando la licenza e riavviando Eclipse.
4. Alla successiva esecuzione dei test, la console dovrebbe mostrare l'output correttamente visualizzato. Nel caso ciò non dovesse accadere, cliccare sull'icona **A cerchiata** nell'angolo in alto a destra della consolo e rieseguire.

### Istruzioni IntelliJ IDEA (versione utilizzata: 2018.1.6)

1. Decomprimere l'archivio .zip.
2. Aprire IntelliJ IDEA, selezionare File > Open, selezionare la cartella FCompiler appena decompressa e premere OK
3. Nella tab Project sulla sinistra, fare click destro sulla cartella `source/lib`, e selezionare Add as Library…, quindi cliccare OK
4. Eseguire una delle due configurazioni disponibili:
	- **FCompiler Main**: carica il contenuto di un file .fool in input (`code/input.fool` di default), ne mostra l'AST, il codice generato ed il risultato dell'esecuzione sulla macchina virtuale
	- **FCompiler YAML Testing**: carica ed esegue una suite di 56 test (`testSuite.yml`). Per ulteriori informazioni sul testing e sulla scrittura di test personalizzati, fare riferimento alla sezione Testing nel report finale.

### FCompiler.jar: Istruzioni per l'uso
Nella directory del progetto è anche presente il file `FCompiler.jar` che rappresenta l'eseguibile del progetto utilizzabile da linea di comando e con file in input.  

```
$ java -jar FCompiler.jar -h
  usage: FCompiler.jar
   -a,--ast                     Show AST after semantic checks (no effects
                                in test mode)
   -h,--help                    Show help message
   -i,--input <FOOL-filepath>   Specify FOOL code input file (REQUIRED)
   -t,--test                    Use input file as YAML test suite
   -v,--verbose                 Use verbose execution showing everything,
                                from AST to assembly (no effect in test
                                mode)
```
