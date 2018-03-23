function circle(x, y, r){
	c.beginPath();
	c.arc(x, y, r, 0, 2*Math.PI);
	c.stroke();
}
function line(x1, y1, x2, y2){
	c.moveTo(x1, y1);
	c.lineTo(x2, y2);
	c.stroke();
}
function rectangle(x, y, a, b){
	c.rect(x, y, a, b);
	c.stroke();
}
function fillRectangle(x, y, w, h, r, g, b){
	c.fillStyle = "rgb("+r+", "+g+", "+b+")";
	c.fillRect(x, y, w, h);
}
function translate(x, y){
	c.translate(x, y);
}
function rotate(theta){
	c.rotate((theta * Math.PI) / 180);
}