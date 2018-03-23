
var express = require('express');
var app = express();
var server = app.listen(80);//process.env.PORT || 3000
app.use(express.static('public'));

console.log("The server is running!");

var socket = require('socket.io');
var io = socket(server);
io.sockets.on('connection', newConnection);

function newConnection(socket){
	console.log("New connection : " + socket.id);

	socket.on('mouse', mouseMsg);

	function mouseMsg(data){
		socket.broadcast.emit('mouse', data);
		//console.log(data);
	}
}