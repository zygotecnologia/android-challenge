# Zygo Programming Challenge - Android Developer

## Estrutura do projeto

O projeto usa como base a estrutura recomendada pelo Google em uma versão simplificada:

![Estrutura do Google](https://i.imgur.com/U0iuikB.png)
(Estrutura do Google a esquerda, estrutura do projeto a direita)

Como o projeto trabalha apenas com API sem uso de banco de dados não havia necessidade tanto da camada de repository como da camada de banco de dados.

## Comunicação entre camadas

Para comunicação entre as camadas é utilizado um combo de livedata e coroutine:

![Estrutura do Google](https://i.imgur.com/eeAPJUa.png)

Livedata tem uma integração incrível com a camada de view e suas bibliotecas, como a lyfecicle e a data binding, logo foi a escolha para comunicação com essa camada. 

Coroutines são bem mais poderosas na hora de realizar trabalho assíncrono possuindo mais modificadores e opções de manipulação e gerência de dados, além de sua simplicidade, logo foi a escolhe para comunicação com a camada de serviços. Uma alternativa seria o Rx, porém como o projeto já estava operando com coroutines não faria sentido a troca.

## Outros pontos sobre o projeto

No projeto em alguns pontos foi iniciado uma estrutura de data binding, mas não houve um cenário que seu uso trouxesse grandes ganhos, logo não foi usado.

Como biblioteca de DI foi usado o Hilt, pois além de ser uma biblioteca poderosa e simples é a biblioteca que tenho mais experiência de uso.
