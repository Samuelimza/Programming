PVector p1 = new PVector(100, 100);
PVector p2 = new PVector(400, 400);
float Q = 0.001;

void setup(){
  size(600, 600);
}

void draw(){
  background(51);
  float dist = p1.dist(p2);
  float distSq = dist * dist;
  float force = Q / distSq;
}
