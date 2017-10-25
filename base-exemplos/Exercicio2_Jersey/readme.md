#Run
./gradlew appRun

# Exemplo de payloads
Alterar status - 
POST - http://localhost:8081/Exercicio2_Jersey/api/estoques/Estoque 2/2/status
{"status" : "indisponivel"}
PUT - http://localhost:8081/Exercicio2_Jersey/api/produtos/2
{
     "descricao": "Produto 1 - atualizado"
}
