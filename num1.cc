#include <cstdlib>
#include <cstdio>
#include <cassert>

typedef int trit;

const int NOTRITS = -1;
const int BADNUM  = -2;

bool echo = false;

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

//
int read_number() {
  const int off[] = { 0, 3, 6, 15, 42, 123, 366, 1095 };

  switch (read_trit()) {
  case '0' : return 0;
  case '1' : return 1;
  case '2' : {
    if (read_trit() != '2') {
      //assert (false);
      return BADNUM;
    }

    int ntrits = read_number();
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

int main(int argc) {
  if (argc > 1) {
    echo = true;
  }

  while (!feof(stdin)) {
    if (argc > 1) {
      printf(" ", read_number());
    } else {
      printf("%d ", read_number());
    }
  }
  return EXIT_SUCCESS;
}
