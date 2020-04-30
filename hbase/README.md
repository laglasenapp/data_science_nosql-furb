# HBase

### Exercício 1
### Agora, de dentro da imagem faça os seguintes procedimentos:

### 1.1 Crie a tabela com 2 famílias de colunas: a. personal-data b. professional-data
<code>create 'italians', 'personal-data', 'professional-data'</code>

### 1.2 Importe o arquivo via linha de comando
<code>hbase shell /tmp/italians.txt</code>

### 2 Operações:

### 2.1 - Adicione mais 2 italianos mantendo adicionando informações como data de nascimento nas informações pessoais e um atributo de anos de experiência nas informações profissionais;
<code>put 'italians', '11', 'personal-data:name', 'Luiz Alfonso Glasenapp'</code><br>
<code>put 'italians', '11', 'personal-data:city', 'Blumenau'</code><br>
<code>put 'italians', '11', 'professional-data:role', 'Lead Developer e Arquiteto Java'</code><br>
<code>put 'italians', '11', 'professional-data:salary', '10000'</code><br>
<code>put 'italians', '12', 'personal-data:name', 'Nikola Teska'</code><br>
<code>put 'italians', '12', 'personal-data:city', 'Smijan'</code><br>
<code>put 'italians', '12', 'professional-data:role', 'Engenheiro elétrico'</code><br>
<code>put 'italians', '12', 'professional-data:salary', '15000'</code><br>

### 2.2 - Adicione o controle de 5 versões na tabela de dados pessoais.
<code>alter 'italians', NAME => 'personal-data', VERSIONS => 5</code>

### 2.3 - Faça 5 alterações em um dos italianos;
<code>put 'italians', '11', 'personal-data:salary', '10000'</code><br>
<code>put 'italians', '11', 'personal-data:salary', '15000'</code><br>
<code>put 'italians', '11', 'personal-data:salary', '20000'</code><br>
<code>put 'italians', '11', 'personal-data:salary', '25000'</code><br>
<code>put 'italians', '11', 'personal-data:salary', '30000'</code><br>

### 2.4 - Com o operador get, verifique como o HBase armazenou o histórico.
<code>get 'italians', '11', {COLUMN => 'personal-data:salary', VERSIONS => 5}</code>

<pre>COLUMN                 CELL</pre><br>
<code>personal-data:salary timestamp=1588267515319, value=30000</code><br>
<code>personal-data:salary timestamp=1588267509864, value=25000</code><br>
<code>personal-data:salary timestamp=1588267506499, value=20000</code><br>
<code>personal-data:salary timestamp=1588267502945, value=15000</code><br>
<code>personal-data:salary timestamp=1588267499231, value=10000</code><br>

### 2.5 - Utilize o scan para mostrar apenas o nome e profissão dos italianos.
<code>scan 'italians', { COLUMNS => ['personal-data:name', 'professional-data:role'] }</code>

### 2.6 - Apague os italianos com row id ímpar:
<code>deleteall 'italians', '1'</code><br>
<code>deleteall 'italians', '3'</code><br>
<code>deleteall 'italians', '5'</code><br>
<code>deleteall 'italians', '7'</code><br>
<code>deleteall 'italians', '9'</code><br>
<code>deleteall 'italians', '11'</code><br>

### 2.7 - Crie um contador de idade 55 para o italiano de row id 5
<code>incr 'italians', '5', 'personal-data:age', '55'</code><br>

### 2.8 - Incremente a idade do italiano em 1
<code>incr 'italians', '5', 'personal-data:age'</code><br>
<code>COUNTER VALUE = 56</code>