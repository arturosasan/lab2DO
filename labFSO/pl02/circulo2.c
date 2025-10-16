#include <stdio.h>

#define PI 3.1416

int main() {

 float area, radio;

 float areaC(float radio) {

 	return(PI * (radio * radio));
 }

 radio = 10;
 area = areaC(radio);
 printf("Area del circulo de radio %f es %f\n", radio, area);

}
