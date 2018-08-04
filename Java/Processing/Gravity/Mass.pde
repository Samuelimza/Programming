class Mass{
  PVector pos;
  PVector vel;
  PVector acc;
  float m = 10;
  
  Mass(int x, int y){
    pos = new PVector(x, y);
    vel = new PVector(0, 0);
    acc = new PVector(0, 0);
  }
  
  void update(){
    vel.add(acc);
    pos.add(vel);
    acc.mult(0);
  }
  
  void applyForce(PVector force){
    acc.add(force.mult(1.0 / m));
  }
  
  void show(){
    ellipse(pos.x, pos.y, 10, 10);
  }
}
