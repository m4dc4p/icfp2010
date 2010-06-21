#include <cstdlib>
#include <cstdio>
#include <cassert>

typedef int trit;

const int NOTRITS = -1;
const int BADNUM  = -2;

const bool echo = false;

int read_char() {
  int c = getchar();
  if (echo) {
    printf("%c", c);
  }
  return c;
}

void skip() {
  int c;
  do {
    c = read_char();
  } while (c != '\n');
}

// read a trit, passing through whitespace, and dropping comments from # to eol
trit read_trit() {
  while (!feof(stdin)) {
    int c = read_char();
    switch (c) {
    case '0' :
    case '1' : 
    case '2' :
      return c;
    case '#' :
      skip();
      return read_trit();
    default :
      if (!echo) {
	printf("%c", c);
      }
    }
  }
  return NOTRITS;
}


// reads a structural number: encoded as in nums.list
int read_s_number() {
  const int off[] = { 0, 3, 6, 15, 42, 123, 366, 1095 };

  switch (read_trit()) {
  case '0' : return 0;
  case '1' : return 1;
  case '2' : {
    if (read_trit() != '2') {
      //assert (false);
      return BADNUM;
    }

    int ntrits = read_s_number();
    if (ntrits < 0) {
      skip();
      return BADNUM;
    }

    //printf("looking for N trits: %d\n", ntrits);
    if (ntrits == 0) {
      return 2;
    }
    int num = 0;
    for (int i=0; i<ntrits; i+=1) {
      num *= 3;
      num += (read_trit() - '0');
    }
    num += off[ntrits];
    return num;
  }
  case NOTRITS: {
    return NOTRITS;
  }
  default: assert(false);
  }
}


// reads a fuel number: encoded as in nums.list
int read_f_number() {
  switch (read_trit()) {
  case '0' : return 0;
  case '1' :
    return (read_trit() - '0');

  case '2' : {
    if (read_trit() != '2') {
      //assert (false);
      return BADNUM;
    }

    int ntrits = read_f_number() + 2;
    if (ntrits < 2) {
      skip();
      return BADNUM;
    }

    //printf("looking for N trits: %d\n", ntrits);
    int num = 0;
    for (int i=0; i<ntrits; i+=1) {
      num *= 3;
      num += (read_trit() - '0');
    }
    return num;
  }
  case NOTRITS: {
    return NOTRITS;
  }
  default: assert(false);
  }
}


int main(int argc, char* argv[]) {
  int car = 0;
  while (!feof(stdin)) {
    car += 1;
    printf("CAR %d\n", car);
    int chambers = read_s_number();
    printf("%d chambers.\n", chambers);
    for (int c=0; c<chambers; c++) {
      printf("  chamber %d -- \n", c);
      int upipe = read_s_number();
      printf("    upper (%d sections):", upipe);
      for (int s=0; s<upipe; s++) {
	int fuel = read_f_number();
	assert(fuel<6);
	printf(" %d", fuel + 1);
      }
      switch (read_trit()) {
      case '0':
	printf("\n    MAIN\n");
	break;
      case '1':
	printf("\n    AUXILIARY\n");
	break;
      default: assert(false);
      }
      int lpipe = read_s_number();
      printf("    lower (%d sections):", lpipe);
      for (int s=0; s<lpipe; s++) {
	int fuel = read_f_number();
	assert (fuel<6);
	printf(" %d", fuel + 1);
      }
      printf("\n");
    }
    printf("==================\n");
  }
  return EXIT_SUCCESS;
}
