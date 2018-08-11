float Q = 100;
Mass[] masses;
Mass m1, m2;
float k = 3;

void setup(){
  size(600, 600);
  masses = new Mass[10];
  for(int i = 0; i < masses.length; i++){
    masses[i] = new Mass(int(random(width)), int(random(height)), false);
  }
  m1 = new Mass(100, 100, false);
  m2 = new Mass(300, 300, true);
  m1.vel.x = k;
  //m2.vel.x = -k;
}

void draw(){
  background(51);
  m1.feelGravitationalForce(m2);
  m2.feelGravitationalForce(m1);
  m1.update();
  m2.update();
  m1.show();
  m2.show();
}
