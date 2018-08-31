int mapWidth, mapHeight;

int noOfCars = 50;
Car[] cars = new Car[noOfCars];
GeneticAlgorithm ga = new GeneticAlgorithm();
ArrayList<Marker> markers;
PImage myMap;
PImage LeftView;

PFont f;
boolean manual = false;

void setup(){
 size(1056, 600);
 textAlign(LEFT, TOP);
 //create font object of arial with 25 size
 f = createFont("Arial", 25, true);
 textFont(f);
 rectMode(CENTER);
 noStroke();
 myMap = loadImage("data/Map.png");
 mapWidth = myMap.width;
 mapHeight = myMap.height;
 myMap.loadPixels();
 LeftView = loadImage("data/LeftView.png");
 for(int i = 0; i < cars.length; i++){
   int species = (i / (noOfCars / 5)) + 1;
   cars[i] = new Car(370, 30, species);
 }
 markers = new ArrayList<Marker>();
 loadMarkers("D:/NewFolder/Osama/Programming/Java/Processing/GA2/data");
}

void draw(){
 background(51);
 //background(myMap);
 translate(228, 0);
 image(LeftView, -228, 0);
 image(myMap, 0, 0);
 stroke(4);
 fill(187, 252, 184, 150);
 text("Gen: " + ga.generation, 0, 0);
 for(int i = 1; i < cars.length; i++){
   cars[i].update();
   cars[i].show();
 }
 cars[0].update();
 cars[0].show();
 ga.update();
 for(int i = 0; i < markers.size(); i++){
   markers.get(i).show();
 }
}

  
void loadMarkers(String path){
  JSONArray markersArray = loadJSONArray(path + "/markers1.json");
  for(int i = 0; i < 22; i++){
    JSONObject mj = markersArray.getJSONObject(i);
    markers.add(new Marker(mj.getInt("x"), mj.getInt("y"), mj.getInt("w"), mj.getInt("h"), mj.getInt("index"), mj.getInt("score")));
  }
}

void keyPressed(){
  if(key == ' '){
    ga.reproduce();
  }
  if(key == 'm'){
    ga.saveGeneration(cars);
    manual = !manual;
  }
}

void keyReleased(){

}
