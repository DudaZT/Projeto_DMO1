# Guia Pocket - Bairro

Aplicativo Android em **Kotlin** desenvolvido para divulgar pequenos serviços e comércios do bairro X, como lanchonetes, costureiras, oficinas e barbearias.  

O app é funcional, visualmente atrativo e conta com suporte a **modo claro/escuro** e **múltiplos idiomas** (português e inglês).

---

## ?? Funcionalidades

### Lista de Serviços
- Exibe uma **ListView** com ao menos 6 serviços cadastrados.
- Cada item mostra **imagem, nome e categoria** usando layout personalizado.
- Clique em um item abre a **tela de detalhes** via Intent explícita.

### Funcionalidades com Intents Implícitas
A partir da tela de detalhes, o app permite:
- ?? Realizar ligação telefônica  
- ?? Abrir o site do estabelecimento  
- ?? Visualizar endereço no Google Maps  
- ?? Compartilhar os dados do serviço com outro app (WhatsApp, e-mail, etc.)

### Internacionalização e Modo Noturno
- Suporte a **português (pt-BR)** e **inglês (en)**.  
  O idioma é detectado automaticamente ao iniciar.
- Respeita o **modo claro/escuro** do sistema, com cores definidas em `themes.xml` e `colors.xml`.

---

## ??? Tecnologias Utilizadas
- Kotlin  
- Android Studio  
- ViewBinding  
- ListView com Adapter personalizado  
- Intents explícitas e implícitas  
- Internacionalização (strings.xml)  
- Tema claro/escuro (themes.xml / colors.xml)  

---

## ?? Design e Interface
- Layouts limpos e intuitivos  
- Ícones e cores semânticas  
- Adaptação automática ao tema do sistema  

> **Aqui você pode adicionar prints do app:**
>
> **Modo Claro:**  
> ![Modo Claro](caminho_para_imagem_claro.png)
>
> **Modo Escuro:**  
> ![Modo Escuro](caminho_para_imagem_escuro.png)
>
> **Português:**  
> ![Português](caminho_para_imagem_pt.png)
>
> **Inglês:**  
> ![Inglês](caminho_para_imagem_en.png)

---

## ?? Demonstração
> **Vídeo curto demonstrando o app em funcionamento (até 30s):**  
> [Link para o vídeo](link_do_video.mp4)

---


