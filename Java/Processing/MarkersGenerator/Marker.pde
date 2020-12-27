class Marker{
  int score = 0, index;
  int x, y, w, h;
  boolean selected = false;
  
  Marker(int x, int y, int index){
    this.index = index;
    this.x = x;
    this.y = y;
    w = 50;
    h = 10;
  }
  
  void update(){
    if(clickedForSelect){
      if(sMouseX < x + w / 2 && sMouseX > x - w / 2 && sMouseY < y + h / 2 && sMouseY > y - h / 2){
        for(int i = 0; i < markers.size(); i++){
          markers.get(i).selected = false;
        }
        this.selected = true;
        clickedForSelect = false;
      }
    }
  }
  
  void show(){
    rectMode(CENTER);
    stroke(255, 0, 0);
    noFill();
    rect(x, y, w, h);
    fill(0, 0, 255);
    text("" + index + ", " + score, x, y);
  }
}
