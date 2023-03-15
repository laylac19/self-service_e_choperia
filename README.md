# Choperia e Self-service do Pulini

Projeto de desenvolvimento de softawe realizado na disciplina de `Laboratório de Engenharia de Software` (7º período da faculade de Sistemas de Informação - IFES Colatina), onde o projeto é utilizado para  gerenciar a `Choperia e Self-service do Pulini`.

Esse projeto envolve a criação de um software que possa gerenciar as atividades da choperia e self-service, permitindo que o estabelecimento possa realizar o controle de estoque, gestão de pedidos, cobrança de clientes, entre outras funcionalidades importantes. Nele é realizado o uso de equpamentos integrados, como:
- Leitor de Código de Barras;
- Cartão RFID;
- Impressora Epson;
- Microterminal;
- Balança;
- Leitor / Gravador Cartão RFID.
 
Foi utilizado ferramentas como Java, Angular / HTML / CSS / JavaScript,
Spring, Docker, JUnit, Postgresl, JPA, Feign Client, Liquibase, Git e padrões de projeto.

![Projeto](choperia%20Self-servic%20do%20Pulini.png)

## Requisitos

> Perfil de Usuários 

Criar um cadastro de usuário, que irá registrar o usuário em um dos perfis abaixo. Ao entrar no sistema o usuário será identificado através da leitura biométrica, com exceção do cliente (Consulta da conta), e somente as interfaces desse perfil ficarão acessíveis. 

- **Fiscal de Entrada** – Registra o nome, telefone, CPF e email do cliente no cartão RFID. Na saída ele verifica se o cliente pagou a consumação no caixa;

- **Caixa** – Recebe os cartões RFID do cliente e efetua a cobrança. 

- **Cliente** – Consulta saldo da conta via microterminal e consome chope.

- **Cozinheiro** – Analisa o que tem que repor no self-servisse, executa a saída do estoque da cozinha.

- **Fiscal Self-Service** – Cuida para que todos os clientes registrem o consumo e informa a cozinha o que deve ser reposto via sistema.

- **Administrador** – Cadastra usuários, imprimir relatórios. 

- **Repositor de Estoque** – Responsável por analisar e repor o estoque de chope e da cozinha.

#

> Estoque do Restaurante

- Deve haver um cadastro de produtos, cada produto possui um código de barras, saldo em estoque, preço de compra, descrição, unidade e ponto de encomenda.

- Considere que cada produto possui um código de barras que vai ser utilizado para efetuar a entrada e a saída do estoque.

- Cada leitura do código de barras na interface de entrada de produtos, deve incrementar em uma unidade o estoque do produto.

- Caso o produto não venha com código de barras, deve haver uma opção para imprimir o código de barras do produto na impressora de código de barras.
O perfil de funcionário que tem acesso a essa funcionalidade é o de Fiscal de Estoque.

#

> Caixa

- O caixa recebe o cartão de um ou mais clientes e informa ao sistema, que efetua a somatória e libera os cartões para saída.

- Ao finalizar a venda o sistema deve emitir um cupom, semelhante ao fiscal, descrevendo os itens consumidos, a quantidade, o valor, os cartões pagos e no final, o valor total.

- Ao finalizar a venda, os produtos deverão ser baixados no estoque e a venda registrada para o cliente que passou o primeiro cartão.

# 

> Entrada e Saída de Clientes

- Ao entrar no estabelecimento o cliente deve se identificar e efetuar um cadastro com nome, telefone, email e cpf.

- O sistema não deve permitir cadastros duplicados.

- Cada cliente é relacionado a um cartão RFID ao entrar, e deve apresentar o mesmo ao sair.

- Ninguém entra sem cartão RFID.

- O sistema deve fornecer uma interface para registrar a entrada do cliente e outra para conferir se o cliente acertou o consumo no caixa.

#

> Cozinha

- Na cozinha deve existir uma interface em que o cozinheiro pode verificar quais pratos deve repor no self-service.

- Quem registra os pratos para reposição é o fiscal do self-service.

- O cozinheiro também retira produtos do estoque conforme sua necessidade.

#

> Self-Service

- Local no qual os clientes escolhem os petiscos vendidos por peso e registrados no cartão de RFID do cliente pelo fiscal.

- Deve haver uma opção na qual o fiscal registre a falta de algum item do self-service para a cozinha.

- O sistema deve ler o peso de forma automática diretamente da balança e registrar no cartão do cliente.

#

> Consulta Conta Cliente

- No estabelecimento existe diversos microterminais, nos quais o cliente pode digitar o código do seu cartão e o sistema retorna qual o valor da sua conta naquele instante.

#

> Estoque de Chope

- O Repositor de Estoque é responsável por analisar e repor o estoque de chope e da Cozinha.
- A entrada é feita por barris de 100 L, cada um com uma etiqueta de RFID (UDF).

- A saída é feita pelo cliente, que consome exatamente uma caneca de 500ml por retirada, via cartão de RFID (UF).

- Pode existir diversos tipos de Chope, cada um com um valor por caneca e valor de compra diferente.

- Considere que todo barril de chope que entrar, estará a disposição dos clientes para iniciar o consumo.

#

> Cadastro de Usuários

- Deve existir uma interface de cadastro de usuários, na qual deve-se informar o nome do usuário e a leitura biométrica.

- Somente o administrador tem acesso a essa interface.

- Cada interface no sistema deve ser armazenado um registro no banco de dados e o acesso liberado no cadastro do usuário.

- Quando o usuário fizer o login, de acordo com o perfil ou as interfaces liberadas, o sistema direciona o acesso dinamicamente as interfaces no sistema.

# 

> Relatórios

- Deve ser possível imprimir os seguintes relatórios em uma folha A4 ou gerar um pdf:
    1. Relatório que descreva o saldo de cada produto do estoque da cozinha e do chope.

    2. Relatório que demonstre somente os produtos que estão com estoque no momento da encomenda.
    
    3. Um relatório dos chopes mais consumidos em um determinado período.
    
    4. Um relatório listando o nome do cliente e o valor comprado em um intervalo de data, ordenado pelo valor.
    
    5. Uma opção de envio de email para todos os clientes que compraram em um determinado período de tempo. O texto deve ser informado no momento do envio.

    6. Um relatório que demonstre a receita x despesas em um período.
