# MongoDB
O exercício é um modelo de tarefas a serem cumpridas então o
readme deve ficar em um formato enunciado/resposta:

## Ex. 1 - Aquecendo com os pets
### 1.1 - Adicione outro Peixe e um Hamster com nome Frodo
<code>
db.pets.insert({name: "Frodo", species: "Hamster"})<br> db.pets.insert({name: "Frodo", species: "Peixe"})
</code>

### 1.2 - Faça uma contagem dos pets na coleção
<code>db.pets.count()</code>

### 1.3 - Retorne apenas um elemento o método prático possível
<code>db.pets.findOne()</code>

### 1.4 - Identifique o ID para o Gato Kilha.
<code>db.pets.find({"name": "Kilha"}, {"_id": 1 })</code>

### 1.5 - Faça uma busca pelo ID e traga o Hamster Mike
<code>db.pets.find({"_id" : ObjectId("5ea7aab6e9039b95f67ba68a")})</code>

### 1.6 - Use o find para trazer todos os Hamsters
<code>db.pets.find({"species" : "Hamster"})</code>

### 1.7 - Use o find para listar todos os pets com nome Mike
<code>db.pets.find({"name" : "Mike"})</code>

### 1.8 - Liste apenas o documento que é um Cachorro chamado Mike
<code>db.pets.find({"name" : "Mike", "species" : "Cachorro"})</code>

## Ex. 2 – Mama mia!
### 2.1 - Liste/Conte todas as pessoas que tem exatamente 99 anos. Você pode usar um count para indicar a quantidade.

<code>db.italians.find({"age": 99}).count()</code>

### 2.2 - Identifique quantas pessoas são elegíveis atendimento prioritário (pessoas com mais de 65 anos)
<code>db.italians.find({"age": { "$te": 65 }}).count()</code>

### 2.3 - Identifique todos os jovens (pessoas entre 12 a 18 anos).
<code>db.italians.find({"age": { "$gte": 12, "$lte": 18 }}).count()</code>

### 2.4 - Identifique quantas pessoas tem gatos, quantas tem cachorro e quantas não tem nenhum dos dois
<code>
<u>Gatos</u><br>db.italians.find({cat: { $exists: true }}).count()</code><br><br><br>
<code>
<u>Cachorros</u><br>db.italians.find({dog: { $exists: true }}).count()</code><br><br><br>
<code>
<u>Quantas não tem nenhum dos dois</u><br> db.italians.find({cat: { $exists: false }, dog: {$exists: false}}).count() <br>
</code>
### 2.5 - Liste/Conte todas as pessoas acima de 60 anos que tenham gato
<code>db.italians.find({"age": { "$gt": 60 }}, {cat: { $exists: true }}).count()</code>

### 2.6 - Liste/Conte todos os jovens com cachorro
<code>db.italians.find({"age": { "$gte": 12, "$lte": 18 }}, dog: {$exists: true}).count()</code>

### 2.7 - Utilizando o $where, liste todas as pessoas que tem gato e cachorro
<code>db.italians.find({ $where: "this.cat !== null && this.dog !== null" })</code>

### 2.8 - Liste todas as pessoas mais novas que seus respectivos gatos.
<code>db.italians.find({ $and: [ { cat: { $exists: true }}, {$where: "this.age < this.cat.age"}]}); </code>

<code>
db.italians.find({ $and: [ { cat: { $exists: true }}, {$where: "this.age < this.cat.age"}]}).count() 
</code>

### 2.9 - Liste as pessoas que tem o mesmo nome que seu bichano (gato ou cachorro)
<code>db.italians.find({ $where: "this.cat && (this.firstname === this.cat.name)"}).count()</code>
<br><br><br>
<code>
db.italians.find( { $or: [ { $where: "this.cat && (this.firstname === this.cat.name)"}, { $where: "this.dog && (this.firstname === this.dog.name)"} ]})
</code>

### 2.10 - Projete apenas o nome e sobrenome das pessoas com tipo de sangue de fator RH negativo R:<br><br>
<code>db.italians.find({ bloodType: /.*-$/ }, {firstname: 1, surname: 1})
</code>

### 2.11 - Projete apenas os animais dos italianos. Devem ser listados os animais com nome e idade. Não mostre o identificado do mongo (ObjectId)

<code>db.italians.find({ $where: "this.cat || this.dog" }, {"cat.name": 1, "dog.name": 1, _id: 0})</code>

### 2.12 - Quais são as 5 pessoas mais velhas com sobrenome Rossi?
<code>db.italians.find({ surname: "Rossi" }).limit(5).sort({ age: -1 })</code>

### 2.13 - Crie um italiano que tenha um leão como animal de estimação. um nome e idade ao bichano
<code>db.italians.insert( { "firstname": "Joe", "surname": "Doe", "leao": {name: "Mike", age: 18}})</code>

### 2.14 - Infelizmente o Leão comeu o italiano. Remova essa pessoa usando o Id.
<code>db.italians.deleteOne({"_id" : ObjectId("5ea7b0d9e9039b95f67bcda2")})</code>

### 2.15 - Passou um ano. Atualize a idade de todos os italianos e dos bichanos em (1)
<code>db.italians.update({}, {$inc: { age: 1, "cat.age": 1, "doc.age": 1 }}, { multi: true })</code>

### 2.16 - O Corona Vírus chegou na Itália e misteriosamente atingiu pessoas somente com gatos e de 66 anos. Remova esses italianos.
<code>db.italians.remove( { age: 66, cat: { $exists: true } })</code>

### 2.17 - Utilizando o framework agregate, liste apenas as pessoas com nomes iguais a sua respectiva mãe e que tenha gato ou cachorro.
<code>db.italians.aggregate([ {'$match': { mother: { $exists: 1} }}, {'$match': { $or: [ {cat: {$exists: 1} }, {dog: {$exists: 1} } ] }}, {'$project': { "firstname": 1, "mother": 1, "isEqual": { "$cmp": ["$firstname","$mother.firstname"]} }}, {'$match': {"isEqual": 0}} ])</code>

### 2.18 - Utilizando aggregate framework, faça uma lista de nomes única de nomes. Faça isso usando apenas o primeiro nome
<code>db.italians.aggregate([{ $group: { _id: "$firstname" } }])</code>

### 2.19 - Agora faça a mesma lista do item acima, considerando nome completo.
<code>db.italians.aggregate([{ $group: { _id: {firstname: "$firstname", surname: "$surname" } }} ])</code>

### 2.20 - Procure pessoas que gosta de Banana ou Maçã, tenham cachorro ou gato,mais de 20 e menos de 60 anos.
<code>db.italians.find( { $and: [ {"age": { "$gt": 20, "$lt": 60 }}, {$or: [{ dog: {$exists: true}}, {cat: {$exists: true}}]}, { $or: [ {favFruits: ["Banana"]}, {favFruits: ["Maçã"]}]} ]})</code>

## Ex. 3 - Stockbrokers
Analise um pouco a estrutura dos dados novamente e em seguida, responda as seguintes perguntas:

### 3.1 - Liste as ações com profit acima de 0.5 (limite a 10 o resultado)
<code>db.stocks.find( { "Profit Margin": { "$gt": 0.5 } } ).limit(10)</code>

### 3.2 - Liste as ações com perdas (limite a 10 novamente)
<code>db.stocks.find( { "Profit Margin": { "$lt": 0 } } ).limit(10)</code>

### 3.3 - Liste as 10 ações mais rentáveis
<code>db.stocks.find().limit(10).sort({ "Profit Margin": -1 })</code>

### 3.4 - Qual foi o setor mais rentável?
<code>db.stocks.find({}, {"Sector": 1}).limit(1).sort({ "Profit Margin": -1 })</code>

### 3.5 - Ordene as ações pelo profit e usando um cursor, liste as ações.
<code>var cursor = db.stocks.find().sort({ "Profit Margin": -1 }); cursor.forEach(function(x){printjson(x);});</code>

### 3.6 - Renomeie o campo “Profit Margin” para apenas “profit”.
<code>db.stocks.updateMany( {}, { $rename: { "Profit Margin": "profit" } } )</code>

### 3.7 - Agora liste apenas a empresa e seu respectivo resultado
<code>db.stocks.find({}, {"Company": 1, "profit": 1})</code>

### 3.8 - Analise as ações. É uma bola de cristal na sua mão... Quais as três ações você investiria?
<code>db.stocks.find({} , {_id: 1, Sector: 1}).limit(3).sort({ "Profit Margin": -1 })</code><br>
<code>
{ "_id" : ObjectId("52853801bb1177ca391c1af3"), "Sector" : "Basic Materials" }<br>
{ "_id" : ObjectId("52853802bb1177ca391c1b69"), "Sector" : "Financial" }<br>
{ "_id" : ObjectId("5285380bbb1177ca391c2c3c"), "Sector" : "Basic Materials" }<br>
</code>

### 3.9 - Liste as ações agrupadas por setor
<code>db.stocks.aggregate([{ $group: { _id: "$Sector" } }])</code>

## Ex. 4 – Fraude na Enron!
### 4.1 - Liste as pessoas que enviaram e-mails (de forma distinta, ou seja, sem repetir). Quantas pessoas são?
<code>db.stocks.aggregate([{ $group: { _id: "$sender" } }])<br> db.stocks.aggregate([{ $group: { _id: "$sender" } }, { $count: "quantidade_pessoas" }]) { "quantidade_pessoas" : 2201 }
</code>

### 4.2 - Contabilize quantos e-mails tem a palavra “fraud”
<code>db.stocks.find( { "text": /(^|\s)fraud($|\s)/ } ).count()
</code>
