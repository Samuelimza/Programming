function Particle(x, y, r, m, mobile){
	this.mobile = mobile;
	this.r = r;
	this.m = m;
	this.pos = new Vector(x, y);
	this.vel = new Vector(0, 0);
	this.acc = new Vector(0, 0);
	this.update = function(dt){
		if(this.mobile){
			/*if(this.pos.x < r && this.vel.x < 0){
				//this.vel.x += 1;
				this.vel.x *= -1;
			}
			if(this.pos.x > wd - r && this.vel.x > 0){
				//this.vel.x -= 1;
				this.vel.x *= -1;
			}
			if(this.pos.y < r && this.vel.y < 0){
				//this.vel.y += 1;
				this.vel.y *= -1;
			}
			if(this.pos.y > ht - r && this.vel.y > 0){
				//this.vel.y -= 1;
				this.vel.y *= -1;
			}*/
			
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
	this.toGravitationalForce = function(particle){
		var thisPos = copy(this.pos);
		var thatPos = copy(particle.pos);
		thatPos.sub(thisPos);
		var d = thatPos.magnitude;
		var mag = (G * this.m * particle.m)/(d * d);
		//if(mag > 10){
		//	mag = 10;
		//}
		thatPos.setMagnitude(mag);
		return thatPos;
		
	}
	this.applyForce = function(force){
		var f = copy(force);
		f.multiply(1/this.m);
		this.acc.add(f);
	}
	this.copyParticle = function(){
		var temp = new Particle(0, 0, 0, 0);
		temp.pos = copy(this.pos);
		temp.vel = copy(this.vel);
		temp.acc = copy(this.acc);
		temp.r = this.r;
		temp.m = this.m;
		temp.mobile = this.mobile;
		return temp;
	}
	this.show = function(){
		circle(this.pos.x, this.pos.y, r);
	}
}