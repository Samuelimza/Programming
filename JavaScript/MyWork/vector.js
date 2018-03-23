function Vector(x, y){
	this.x = x;
	this.y = y;
	this.magnitude = Math.sqrt(x*x + y*y);

	this.updateMagnitude = function(){
		this.magnitude = Math.sqrt(x*x + y*y);
	}
	this.add = function(vector){
		this.x += vector.x;
		this.y += vector.y;
		this.updateMagnitude();
	}
	this.sub = function(vector){
		this.x -= vector.x;
		this.y -= vector.y;
		this.updateMagnitude();
	}
	this.setMagnitude = function(newMagnitude){
		this.x *= newMagnitude/this.magnitude;
		this.y *= newMagnitude/this.magnitude;
		this.magnitude = newMagnitude;
	}
	this.multiply = function(amount){
		this.x *= amount;
		this.y *= amount;
		this.updateMagnitude();
	}
	this.rotate = function(angle){
		var s = Math.sin(angle);
		var c = Math.cos(angle);
		var newX = (this.x)*c - (this.y)*s;
		var newY = (this.x)*s + (this.y)*c;
		this.x = newX;
		this.y = newY;
	}
	
}
function copy(vector){
	var duplicate = new Vector(vector.x, vector.y);
	return duplicate;
}
function randomVector(){
	var vector = new Vector(1, 0);
	var randomAngle = Math.random()*2*Math.PI;
	vector.rotate(randomAngle);
	return vector;
}