function map(x, a, b, c, d){
	return c + (((b - x) / (b - a)) * (d - c));
}
function distance(x1, y1, x2, y2){
	return Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2));
}