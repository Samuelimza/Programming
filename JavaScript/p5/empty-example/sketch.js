
function setup(){
  createCanvas(400, 400);
  angleMode(DEGREES);
}

function draw(){
  background(0);
  translate(200, 200);
  rotate(-90);

  let hr = hour() //% 12;
  let mn = minute();
  let sc = second();

  strokeWeight(8);
  stroke(0, 255, 210);
  noFill();
  let end = map(sc, 0, 60, 0, 360);
  arc(0, 0, 300, 300, 0, end);

  let end1 = map(mn, 0, 60, 0, 360);
  arc(0, 0, 280, 280, 0, end1);

  let end2 = map(hr % 12, 0, 12, 0, 360);
  arc(0, 0, 260, 260, 0, end2);

}



























// // var mic, fft;

// // function setup() {
// //    createCanvas(710,400);
// //    noFill();

// //    mic = new p5.AudioIn();
// //    mic.start();
// //    fft = new p5.FFT();
// //    fft.setInput(mic);
// // }

// // function draw() {
// //    background(200);

// //    var spectrum = fft.analyze();
// //    fill(255);
// //    ellipse(width / 2, height / 2, spectrum[int(spectrum.length / 4)]);
// // }
// // var xspacing = 8;   // Distance between each horizontal location
// // var w;              // Width of entire wave
// // var maxwaves = 4;   // total # of waves to add together

// // var theta = 0.0;
// // var amplitude = new Array(maxwaves);   // Height of wave
// // // Value for incrementing X, to be calculated 
// // // as a function of period and xspacing
// // var dx = new Array(maxwaves);         
// // // Using an array to store height values
// // // for the wave (not entirely necessary)
// // var yvalues;                          

// // function setup() {
// //   createCanvas(710, 400);
// //   frameRate(30);
// //   colorMode(RGB, 255, 255, 255, 100);
// //   w = width + 16;

// //   for (var i = 0; i < maxwaves; i++) {
// //     amplitude[i] = random(10,30);
// //     var period = random(100,300); // Num pixels before wave repeats
// //     dx[i] = (TWO_PI / period) * xspacing;
// //   }

// //   yvalues = new Array(floor(w/xspacing));
// // }

// // function draw() {
// //   background(0);
// //   calcWave();
// //   renderWave();
// // }

// // function calcWave() {
// //   // Increment theta (try different values 
// //   // for 'angular velocity' here
// //   theta += 0.02;

// //   // Set all height values to zero
// //   for (var i = 0; i < yvalues.length; i++) {
// //     yvalues[i] = 0;
// //   }
 
// //   // Accumulate wave height values
// //   for (var j = 0; j < maxwaves; j++) {
// //     var x = theta;
// //     for (var i = 0; i < yvalues.length; i++) {
// //       // Every other wave is cosine instead of sine
// //       if (j % 2 == 0)  yvalues[i] += sin(x)*amplitude[j];
// //       else yvalues[i] += cos(x)*amplitude[j];
// //       x+=dx[j];
// //     }
// //   }
// // }

// // function renderWave() {
// //   // A simple way to draw the wave with an ellipse at each location
// //   noStroke();
// //   fill(255,50);
// //   ellipseMode(CENTER);
// //   for (var x = 0; x < yvalues.length; x++) {
// //     ellipse(x*xspacing,width/2+yvalues[x],16,16);
// //   }
// // }
// // var yoff = 0.0;        // 2nd dimension of perlin noise

// // function setup() {
// //   createCanvas(710, 400);
// // }

// // function draw() {
// //   background(51);

// //   fill(255);
// //   // We are going to draw a polygon out of the wave points
// //   beginShape(); 
  
// //   var xoff = 0;       // Option #1: 2D Noise
// //   // var xoff = yoff; // Option #2: 1D Noise
  
// //   // Iterate over horizontal pixels
// //   for (var x = 0; x <= width; x += 10) {
// //     // Calculate a y value according to noise, map to 
    
// //     // Option #1: 2D Noise
// //     var y = map(noise(xoff, yoff), 0, 1, 200,300);

// //     // Option #2: 1D Noise
// //     // var y = map(noise(xoff), 0, 1, 200,300);
    
// //     // Set the vertex
// //     vertex(x, y); 
// //     // Increment x dimension for noise
// //     xoff += 0.05;
// //   }
// //   // increment y dimension for noise
// //   yoff += 0.01;
// //   vertex(width, height);
// //   vertex(0, height);
// //   endShape(CLOSE);
// // }

// var w;
// var columns;
// var rows;
// var board;
// var next;

// function setup() {
//   createCanvas(720, 400);
//   w = 20;
//   // Calculate columns and rows
//   columns = floor(width/w);
//   rows = floor(height/w);
//   // Wacky way to make a 2D array is JS
//   board = new Array(columns);
//   for (var i = 0; i < columns; i++) {
//     board[i] = new Array(rows);
//   } 
//   // Going to use multiple 2D arrays and swap them
//   next = new Array(columns);
//   for (i = 0; i < columns; i++) {
//     next[i] = new Array(rows);
//   }
//   init();
// }

// function draw() {
//   background(255);
//   generate();
//   for ( var i = 0; i < columns;i++) {
//     for ( var j = 0; j < rows;j++) {
//       if ((board[i][j] == 1)) fill(0);
//       else fill(255); 
//       stroke(0);
//       rect(i*w, j*w, w-1, w-1);
//     }
//   }

// }

// // reset board when mouse is pressed
// function mousePressed() {
//   init();
// }

// // Fill board randomly
// function init() {
//   for (var i = 0; i < columns; i++) {
//     for (var j = 0; j < rows; j++) {
//       // Lining the edges with 0s
//       if (i == 0 || j == 0 || i == columns-1 || j == rows-1) board[i][j] = 0;
//       // Filling the rest randomly
//       else board[i][j] = floor(random(2));
//       next[i][j] = 0;
//     }
//   }
// }

// // The process of creating the new generation
// function generate() {

//   // Loop through every spot in our 2D array and check spots neighbors
//   for (var x = 1; x < columns - 1; x++) {
//     for (var y = 1; y < rows - 1; y++) {
//       // Add up all the states in a 3x3 surrounding grid
//       var neighbors = 0;
//       for (var i = -1; i <= 1; i++) {
//         for (var j = -1; j <= 1; j++) {
//           neighbors += board[x+i][y+j];
//         }
//       }

//       // A little trick to subtract the current cell's state since
//       // we added it in the above loop
//       neighbors -= board[x][y];
//       // Rules of Life
//       if      ((board[x][y] == 1) && (neighbors <  2)) next[x][y] = 0;           // Loneliness
//       else if ((board[x][y] == 1) && (neighbors >  3)) next[x][y] = 0;           // Overpopulation
//       else if ((board[x][y] == 0) && (neighbors == 3)) next[x][y] = 1;           // Reproduction
//       else                                             next[x][y] = board[x][y]; // Stasis
//     }
//   }

//   // Swap!
//   var temp = board;
//   board = next;
//   next = temp;
// }