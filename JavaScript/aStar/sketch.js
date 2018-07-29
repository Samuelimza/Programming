function removeFromArray(arr, elt){
	for (var i = arr.length - 1; i >= 0; i--) {
		if(arr[i] == elt){
			arr.splice(i, 1);
		}
	}
}

function heuristic(a, b){
	var d = abs(a.x - b.x) + abs(a.y - b.y);
	//var d = dist(a.x, b.x, a.y, b.y);
	return d;
}

var cols = 40;
var rows = 40;
var grid = new Array(cols);
var path = [];

var openSet = [];
var closedSet = [];
var start, end;
var w, h;

function Spot(i, j){
	this.x = i;
	this.y = j;
	this.f = 0;
	this.g = 0;
	this.h = 0;
	this.neighbours = [];
	this.cameFrom = undefined;
	this.wall = false;

	if(random(1) < 0.5){
		this.wall = true;
	}

	this.show = function(col){
		fill(col);
		if(this.wall){
			fill(0);
		}
		noStroke();
		rect(this.x * w, this.y * h, w - 1, h - 1);
	}

	this.addNeighbours = function(grid){
		var i = this.x;
		var j = this.y;
		if(i < cols - 1){
			this.neighbours.push(grid[i + 1][j]);
		}
		if(i > 0){
			this.neighbours.push(grid[i - 1][j]);
		}
		if(j < rows - 1) {
			this.neighbours.push(grid[i][j + 1]);
		}
		if(j > 0){
			this.neighbours.push(grid[i][j - 1]);
		}
		if(i > 0 && j > 0){
			this.neighbours.push(grid[i - 1][j - 1]);
		}
		if(i > 0 && j < rows - 1){
			this.neighbours.push(grid[i - 1][j + 1]);
		}
		if(i < cols - 1 && j > 0){
			this.neighbours.push(grid[i + 1][j - 1]);
		}
		if(i < cols - 1 && j < rows - 1){
			this.neighbours.push(grid[i + 1][j + 1]);
		}
	}
}

function setup(){
	createCanvas(400, 400);
	console.log('A*');

	w = width / cols;
	h = height / rows;

	for (var i = 0; i < cols; i++){
		grid[i] = new Array(rows);
	}

	for (var i = 0; i < cols; i++){
		for (var j = 0; j < rows; j++){
			grid[i][j] = new Spot(i, j);
		}
	}

	for (var i = 0; i < cols; i++){
		for (var j = 0; j < rows; j++){
			grid[i][j].addNeighbours(grid);
		}
	}

	start = grid[0][0]
	//end = grid[cols - 1][4]
	end = grid[cols - 1][rows - 1]
	start.wall = false;
	end.wall = false;

	openSet.push(start);
}

function draw(){
	if (openSet.length > 0){
		var winner = 0;
		for (var i = 0; i < openSet.length; i++) {
			if(openSet[i].f < openSet[winner].f){
				winner = i;
			}
		}
		var current = openSet[winner];

		if(openSet[winner] === end){
			noLoop();
			console.log("DONE!");
		}

		removeFromArray(openSet, current);
		closedSet.push(current);

		var neighbours = current.neighbours;
		for (var i = 0; i < neighbours.length; i++) {
			var neighbour = neighbours[i];
			if(!closedSet.includes(neighbour) && !neighbour.wall){
				var tempG = current.g + 1;
				if(openSet.includes(neighbour)){
					if(tempG < neighbour.g){
						neighbour = tempG;
					}
				} else{
					neighbour.g = tempG;
					openSet.push(neighbour)
				}

				neighbour.h = heuristic(neighbour, end);
				neighbour.f = neighbour.g + neighbour.h;
				neighbour.cameFrom = current;
			}
		}

	}else{

	}
	background(0);
	for (var i = 0; i < cols; i++){
		for (var j = 0; j < rows; j++){
			grid[i][j].show(color(255));
		}
	}

	for (var i = 0; i < closedSet.length; i++){
		closedSet[i].show(color(255, 0, 0));
	}

	for (var i = 0; i < openSet.length; i++){
		openSet[i].show(color(0, 255, 0));	
	}

	path = [];
	var temp = current;
	path.push(temp);
	while(temp.cameFrom){
		path.push(temp.cameFrom);
		temp = temp.cameFrom;
	}

	for (var i = 0; i < path.length; i++) {
		path[i].show(color(0, 0, 255));
	}
}