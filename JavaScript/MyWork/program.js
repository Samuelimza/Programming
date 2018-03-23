//------------------------------------------------------
var k = 0.4;
var nl = 10;
var dt = 0.1;
var particles = [];
var start = 1;
function mouseClick(){
	
}

function setup(){
	for(var i = 100; i <= 200; i += 10){
		particles.push(new myP(i, 300, true, null, null))
	}
	for(var i = 0; i < particles.length; i++){
		if((i - 1) >= 0){
			particles[i].pl = particles[i - 1];
		}
		if((i + 1) < particles.length){
			particles[i].pr = particles[i +1];
		}
	}
	particles[0].mov = false;
	particles[particles.length - 1].mov = false;
	particles[6].vel.y = -2;
	//particles[0].vel.x = 3;
}


function draw(){
	fillRectangle(0, 0, 600, 600, 255, 255, 255);
	for(var i = 0; i < particles.length; i++){
		particles[i].update();
		particles[i].show();
		start = 0;
	}

}

function myP(x, y, mov, pl, pr){
	this.pos = new Vector(x, y);
	this.vel = new Vector(0, 0);
	this.acc = new Vector(0, 0);
	this.mov = mov;
	this.pl = pl;
	this.pr = pr;
	this.show = function(){
		circle(this.pos.x, this.pos.y, 5);
	}
	this.update = function(){
		if(this.mov){
			this.forces();
			//this.vel.add(this.acc);
			//this.pos.add(this.vel);
			var tVel = copy(this.acc);
			tVel.multiply(dt);
			this.vel.add(tVel);
			
			var tPos1 = copy(this.vel);
			tPos1.multiply(dt);
			
			var tPos2 = copy(this.acc);
			tPos2.multiply(0.5*dt*dt);
			
			tPos1.add(tPos2);
			this.pos.add(tPos1);
			
			this.acc.multiply(0);
		}
	}
	this.debug = function(){
		
	}
	this.forces = function(){
		if(this.pl != null){
			//var thisPos = copy(this.pos);
			//var thatPos = copy(this.pl.pos);
			//thatPos.sub(thisPos);
			var dx = this.pl.pos.x - this.pos.x;
			var dy = this.pl.pos.y - this.pos.y;
			var d = Math.sqrt(dx * dx + dy * dy);
			var mag = k * (d);
			var tt = new Vector(dx, dy);
			if(start == 0){console.log(mag);}
			tt.setMagnitude(mag / 10);
			this.acc.add(tt);
		}
		
		if(this.pr != null){
			//var thisPos1 = copy(this.pos);
			//var thatPos1 = copy(this.pr.pos);
			//thatPos1.sub(thisPos1);
			var dx1 = this.pr.pos.x - this.pos.x;
			var dy1 = this.pr.pos.y - this.pos.y;
			var d1 = Math.sqrt(dx1 * dx1 + dy1 * dy1);
			var mag1 = k * (d1);
			var tt1 = new Vector(dx, dy);	
			tt1.setMagnitude(mag1 / 10);
			this.acc.add(tt1);
			if(start == 0){
				console.log(mag1);
				//line(0, 0, this.acc.x * 100, this.acc.y * 100);
			}
		}
	}
}










