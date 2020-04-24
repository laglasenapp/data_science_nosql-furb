## Redinsgo
O Redinsgo é basicamente um bingo com estruturas em um banco chave/valor.

### Pré-requisito
Possuir o banco Redis instalado localmente na porta 6379<br>
Possuir JRE 8.0 ou posterior instalado.

### Execução
Importar o projeto em uma IDE como Eclipse e invocar o método Bingo#main
Executar o comando <code>mvn package</code>, executar o comando <code>java -jar target/Bingo-0.0.1-SNAPSHOT-jar-with-dependencies.jar</code>
 
É possível alterar as propriedades através de um segundo arquivo que pode ser passado como pârametro -DpropertiesPath


### Resultado

Rodada: 81<br>
Número sorteado: 47<br>
Aumentando o score do usuário user:8 novo score: 15.0<br>
Aumentando o score do usuário user:11 novo score: 13.0<br>
Aumentando o score do usuário user:18 novo score: 15.0<br>
Aumentando o score do usuário user:40 novo score: 10.0<br>
Aumentando o score do usuário user:41 novo score: 13.0<br>
Aumentando o score do usuário user:43 novo score: 10.0<br>
<br>
-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+<br>
<br>
Lista de vencedores:<br>
Nome= user:8 Cartela [Numeros=[98, 67, 44, 46, 47, 17, 52, 56, 27, 60, 29, 93, 61, 94, 63]]<br>
Nome= user:18 Cartela [Numeros=[32, 34, 98, 38, 7, 9, 47, 49, 50, 83, 86, 23, 57, 27, 92]]<br>
