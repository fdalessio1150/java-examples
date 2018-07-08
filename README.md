# Java-examples 

# Pacote Admin - Criação do banco
UPDATE system.local SET cluster_name = 'local_cluster' where key='local';

create keyspace BD001 with replication={'class':'SimpleStrategy', 'replication_factor':1};

CREATE TABLE BD001.TB001_CLIENT (
CLI_NOME text,
CLI_SEXO text,
CLI_ID UUID,
PRIMARY KEY (CLI_NOME, CLI_SEXO)); 

# Testes utilizando o Curl
curl --header "Content-Type: application/json" \
  --request POST \
  --data '[{"nome":"Felipe", "sexo":"Masculino"}]' \
  http://localhost:8080/client/Felipe
  
curl --header "Content-Type: application/json" \
  --request POST \
  --data '[{"nome":"Michelle", "sexo":"Feminino"}]' \
  http://localhost:8080/client/Michelle
  
curl --request GET http://localhost:8080/clients

curl --request GET http://localhost:8080/clients?name=Felipe
