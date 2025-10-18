# 📱 Guia Pocket - Bairro

Aplicativo Android em **Kotlin** desenvolvido para divulgar pequenos **serviços e comércios locais** — como lanchonetes, hospitais, mercados, entre outros.  

O app tem um visual moderno, é funcional e conta com **modo claro/escuro** 🌗 e **múltiplos idiomas** 🌍 (português e inglês).

---

## ✨ Funcionalidades

### 🧭 Lista de Serviços
- Exibe uma **ListView** com pelo menos 6 serviços cadastrados.  
- Cada item exibe **imagem, nome e categoria** com layout personalizado.  
- Ao clicar em um item, o usuário é levado para a **tela de detalhes** (Intent explícita).

### 🔗 Intents Implícitas
Na tela de detalhes, o app permite:
- Fazer ligação telefônica  
- Abrir o site do estabelecimento  
- Ver o endereço no Google Maps  
- Compartilhar informações do serviço (WhatsApp, e-mail, etc.)

### 🌓 Internacionalização e Modo Noturno
- Suporte a **português (pt-BR)** e **inglês (en)**, com detecção automática do idioma.  
- Suporte a **modo claro/escuro**, com cores definidas em `themes.xml` e `colors.xml`.

---

## 🛠️ Tecnologias Utilizadas
- **Kotlin**  
- Android Studio  
- ViewBinding  
- ListView com Adapter personalizado  
- Intents explícitas e implícitas  
- Internacionalização (`strings.xml`)  
- Tema claro/escuro (`themes.xml` / `colors.xml`)

---

## 🖼️ Design e Interface
- Layouts limpos e intuitivos  
- Ícones e cores semânticas  
- Adaptação ao tema do sistema (light/dark)  

> 📸 **Demonstrações (prints do app)**  
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

## 🎥 Demonstração em Vídeo
Assista ao vídeo mostrando o app em ação:  
👉 [Link para o vídeo](link_do_video.mp4)

---

📚 Projeto desenvolvido para a disciplina **DMO1** — Desenvolvimento Mobile.
