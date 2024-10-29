let socket = new WebSocket("ws://localhost:8080/chat");
	
socket.onopen = function(event) {
    console.log("Conexión establecida");
};

socket.onmessage = function(event) {
    let messageArea = document.getElementById("messageArea");
    let messageDiv = document.createElement("div");
	messageDiv.className = "flex border border-black justify-start w-64 rounded-md"
    messageDiv.textContent = event.data;

    messageArea.appendChild(messageDiv);

    messageArea.scrollTop = messageArea.scrollHeight; 
};

socket.onerror = function(event) {
    console.error("Error en la conexión WebSocket:", event);
};

function sendMessage() {
    let username = document.getElementById("username").value;
    let messageInput = document.getElementById("messageInput").value;

    if (username.trim() === '' || messageInput.trim() === '') {
        alert("Por favor, introduce un nombre de usuario y un mensaje.");
        return;
    }

    let fullMessage = `${username}: ${messageInput}`;
    socket.send(fullMessage); 
}

function clearMessages() {
    document.getElementById("messageArea").innerHTML = '';
}