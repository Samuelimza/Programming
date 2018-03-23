var socket;

function setup(){
	createCanvas(200, 200);
	background(51);
	socket = io('http://localhost:80');
	socket.on('mouse', newDrawing);
}

function newDrawing(data){
	noStroke();
	fill(255, 0, 100);
	ellipse(data.x, data.y, 16, 16);
}

function mouseDragged(){
	console.log('Sending ' + mouseX + ',' + mouseY);
	var data = {
		x: mouseX,
		y: mouseY
	}
	socket.emit('mouse', data);
	noStroke();
	fill(255);
	ellipse(mouseX, mouseY, 16, 16);
}

function draw(){
	// background(51);
	// ellipse(mouseX, mouseY, 60, 60);
}