SocketTalk - Real-time Messaging Application 🗨️💬
SocketTalk is a simple, real-time messaging application built using Java that allows users to connect and communicate with each other over a network. It provides a client-server architecture where multiple clients can send and receive messages using socket connections. 🌐

Features ✨

* Real-time Chat: Instant messaging between clients. ⏱️💬
* User-friendly Interface: Built with Java Swing, the interface allows easy message sending and receiving. 👨‍💻💻
* Network-based Communication: Uses socket programming to establish connections between the client and server. 🌐🔌
* Multiple Clients: Multiple clients can connect to the server and chat simultaneously. 👥👥👥

  
----------------------------------------------------------------------------------------------------

 Technologies Used 🛠️
 
* Java: The core programming language used for building the application. ☕
* Java Swing: Used for creating the graphical user interface (GUI). 🎨
* Socket Programming: Employed for communication between the client and server. 🔌📡
* Object Serialization: Used to send and receive messages as objects between the client and server. 📦
* JComboBox: Allows clients to select the user they want to communicate with. 🧑‍🤝‍🧑
* How It Works ⚙️
  
----------------------------------------------------------------------------------------------------
Server Side:

* The server listens for incoming connections on port 9999. 📡
* It handles multiple clients and broadcasts messages to online clients. 📢
* When a client connects, it sends a list of connected clients' IP addresses to update the user list. 📋
  
Client Side:

* Clients can send messages to the server, which broadcasts them to all connected clients. 📨
* The client interface allows users to input a nickname, select online users, and send messages. 📝💬

----------------------------------------------------------------------------------------------------
How to Run 🏃‍♂️

Prerequisites ⚠️

  Java 8 or higher installed on your machine. ☕📦
  
Running the Server 🚀

  1) Download or clone the repository. 💾
  2) Open a terminal and navigate to the project directory. 🖥️
  3) Compile and run the server using the following commands:

javac servidor.java
java servidor

The server will start and wait for client connections. ⏳

----------------------------------------------------------------------------------------------------

Running the Client 🧑‍💻

* After running the server, download or clone the repository. 💻
* Open a terminal and navigate to the project directory. 🖥️
* Compile and run the client using the following commands:

javac Cliente.java
java Cliente

* Enter your nickname and the IP address of the server to connect. ✨
----------------------------------------------------------------------------------------------------

Best Practices ✅

* Socket Handling: Ensure proper exception handling and socket closure to avoid memory leaks or connection issues. 🛡️
* Thread Safety: Use separate threads for handling client-server communication to ensure smooth UI interaction and background processing. 🧵🔒
* Code Modularity: Keep server-side and client-side code modular for easier maintenance and updates. 📦⚙️
----------------------------------------------------------------------------------------------------


🎥 Demo Video

Watch the following video to see the app in action. It demonstrates how the server and client connect and communicate over the network. The video showcases how users can send messages, view available IPs, and participate in group conversations easily.

https://github.com/user-attachments/assets/e38edf10-7e98-4214-8058-32c40d603a53
