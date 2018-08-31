PFont font;
boolean adding = true;
boolean clickedForSelect = false;
int sMouseX, sMouseY;
ArrayList<Marker> markers = new ArrayList<Marker>();
PImage myMap;
void setup(){
  size(600, 600);
  font = createFont("Arial", 17, true);
  textAlign(CENTER, CENTER);
  textFont(font);
  Marker m = new Marker(100, 100, 0);
  markers.add(m);
  myMap = loadImage("D:/NewFolder/Osama/Programming/Java/Processing/GA2/Map.png");
  //myMap.loadPixels();
}

void draw(){
  background(myMap);
  fill(255, 255, 0);
  text("Adding: " + adding, 50, 10);
  for(int i = 0; i < markers.size(); i++){
    Marker marker = markers.get(i);
    marker.update();
    marker.show();
  }
}

void saveMarkers(){
  JSONArray markerArray = new JSONArray();
  for(int i = 0; i < markers.size(); i++){
    Marker current = markers.get(i);
    JSONObject markerObject = new JSONObject();
    markerObject.setInt("x", current.x);
    markerObject.setInt("y", current.y);
    markerObject.setInt("w", current.w);
    markerObject.setInt("h", current.h);
    markerObject.setInt("index", current.index);
    markerObject.setInt("score", current.score);
    markerArray.setJSONObject(i, markerObject);
  }
  saveJSONArray(markerArray, "data/markers1.json");
}

void mouseClicked(){
  if(adding){
    markers.add(new Marker(mouseX, mouseY, markers.size()));
  }else{
    clickedForSelect = true;
    sMouseX = mouseX;
    sMouseY = mouseY;
  }
}

void keyPressed(){
  if(key == 'z'){
    saveMarkers();
  }
  if(key == ' '){
    adding = !adding;
    if(adding){
      for(int i = 0; i < markers.size(); i++){
        markers.get(i).selected = false;
      }
    }
  }
  for(int i = 0; i < markers.size(); i++){
    Marker current = markers.get(i);
    if(current.selected){
      if(key == 'w'){
        current.y--;
      }else if(key == 's'){
        current.y++;
      }else if(key == 'a'){
        current.x--;
      }else if(key == 'd'){
        current.x++;
      }else if(key == 'i'){
        current.h++;
      }else if(key == 'k'){
        current.h--;
      }else if(key == 'j'){
        current.w++;
      }else if(key == 'l'){
        current.w--;
      }else if(key == 'r'){
        current.index++;
      }else if(key == 'f'){
        current.index--;
      }else if(key == 'y'){
        current.score++;
      }else if(key == 'h'){
        current.score--;
      }
    }
  }
}
