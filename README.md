# 📱 Guia Pocket - Bairro

Aplicativo Android em **Kotlin** desenvolvido para divulgar pequenos **serviços e comércios locais** — como lanchonetes, hospitais, mercados, entre outros.  

O app tem um visual moderno, é funcional e conta com **modo claro/escuro** 🌗 e **múltiplos idiomas** 🌍 (português e inglês).

---

## 🚀 O que há de novo?

### ⚡ Melhor Desempenho (RecyclerView)
- Lista principal refeita com **RecyclerView**, muito mais leve.
- Itens continuam exibindo **imagem, nome e categoria** com layout próprio.

### 📝 Cadastro de Locais
Agora os usuários podem cadastrar novos estabelecimentos:
- Escolher imagem da galeria (via ActivityResultLauncher)
- Preencher nome, categoria, telefone, site, endereço e descrição
- Salvar no banco com **Room**

### 💾 Persistência com Room
- Locais cadastrados ficam armazenados no banco interno.
- Imagens são salvas como **URI (String)**.

### 🔍 Filtro em Tempo Real
- Campo de busca no topo filtra a lista enquanto o usuário digita.

---

## ✨ Funcionalidades

### 🧭 Lista de Serviços
- RecyclerView com ViewHolder customizado
- Filtro por nome
- Clique vai para a tela de detalhes

### 📝 Cadastro de Novos Locais
- Seleção de imagem da galeria
- Formulário completo
- Retorno usando ActivityResultLauncher

### 🔗 Intents Implícitas na Tela de Detalhes
- Ligar
- Abrir site
- Maps
- Compartilhar

### 🌍 Internacionalização + Modo Noturno
- Português e inglês
- Light/Dark Mode automático

---

## 🛠️ Tecnologias Utilizadas
- **Kotlin**  
- Android Studio  
- RecyclerView
- Adapter personalizado
- Room (DAO, Entity, Database)
- ViewBinding
- ConstraintLayout
- ActivityResultLauncher
- Intents implícitas e explícitas
- Internacionalização
- Tema claro/escuro

---

## 🖼️ Design e Interface
- Layouts limpos e intuitivos  
- Ícones e cores semânticas  
- Adaptação ao tema do sistema (light/dark)  

> 📸 **Demonstrações (prints do app)**  

### 🌞 Modo Claro
<p align="left">
  <img src="https://github.com/DudaZT/Projeto_DMO1/blob/main/img/modoclaro1.png" width="200"/>
  <img src="https://github.com/DudaZT/Projeto_DMO1/blob/main/img/modoclaro2.png" width="200"/>
  <img src="https://github.com/DudaZT/Projeto_DMO1/blob/main/img/modoclaro3.png" width="200"/>
</p>

### 🌙 Modo Escuro
<p align="left">
  <img src="https://github.com/DudaZT/Projeto_DMO1/blob/main/img/modoescuro1.png" width="200"/>
  <img src="https://github.com/DudaZT/Projeto_DMO1/blob/main/img/modoescuro2.png" width="200"/>
  <img src="https://github.com/DudaZT/Projeto_DMO1/blob/main/img/modoescuro3.png" width="200"/>
</p>

### Português
<p align="left">
  <img src="https://github.com/DudaZT/Projeto_DMO1/blob/main/img/modoclaro1.png" width="200"/>
  <img src="https://github.com/DudaZT/Projeto_DMO1/blob/main/img/modoclaro2.png" width="200"/>
  <img src="https://github.com/DudaZT/Projeto_DMO1/blob/main/img/modoclaro3.png" width="200"/>
</p>

### Inglês
<p align="left">
  <img src="https://github.com/DudaZT/Projeto_DMO1/blob/main/img/ingles1.png" width="200"/>
  <img src="https://github.com/DudaZT/Projeto_DMO1/blob/main/img/ingles2.png" width="200"/>
  <img src="https://github.com/DudaZT/Projeto_DMO1/blob/main/img/ingles3.png" width="200"/>
</p>

---

## 🎥 Demonstração em Vídeo

📹 **[Clique Aqui - Demonstração do App](https://github.com/DudaZT/Projeto_DMO1/blob/main/video/videodemonstracao.mp4)**

---

## 🎥 Explicação Completa

📹 **[Clique aqui para assistir à explicação do código](https://github.com/DudaZT/Projeto_DMO1/blob/main/video/videoexplicativo.mp4)**

📚 Projeto desenvolvido para a disciplina **DMO1** — Desenvolvimento Mobile.
