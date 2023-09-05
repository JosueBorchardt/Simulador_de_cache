# Simulador de cache

##Sumário
[Introdução](#introdução)
[Requisitos](#requisitos)
[Estruturação dos Arquivos](#estrura-dos-aqruivos)
[Instruções de uso](#instruções-de-uso)


## Introdução

Esse trabalho foi desenvolvido com a linguagem de programação java ultilizando o ambiente do "Visual Studio Code". Sua implementação foi pensada para executar no terminal do windows. 

## Requisitos

- É necessário que a JVM esteja instalado no computador
- O projeto foi projetado com arquivos que executam no terminal do windows, caso seja necessário executar no Linux é possível que precise de adaptações

## Estrura dos Aqruivos

O projeto é composto pelos seguintes arquivos e seus conteúdos:

- `src`
    - Códigos implmentados em java

- `bin`   
    - Pastas e os arquivos compilados dos códigos implementados
    - Arquivo `.bat` que inicializa a main quando chamado no terminal e passado os parâmetros corretos
    - Pasta com os arquivos binários (`.bin`) que serão ultilizados na execução do código 
 
## Instruções de uso

1. Abrir o Prompt de Comando
2. Entrar no diretório da pasta `bin`
3. Executar o comando:
    cache_simulator <nsets> <bsize> <assoc> <substituição> <flag_saida> <arquivo_de_entrada> 