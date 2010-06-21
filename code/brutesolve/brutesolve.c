#include <stdio.h>

typedef int matrix;

typedef enum { true, false } bool;

#define MAT(dim, a, row, col)  a[(dim)*(row) + (col)]

enum chamber_type { CH_MAIN, CH_AUX };

struct fuel {
    int ingr;
    int tanks;
    matrix *mats[];
};

struct pipe {
    int nsect;
    int sect[];
};

struct chamber {
    struct pipe u;
    struct pipe l;
    enum chamber_type type;
};

struct car {
    int ncham;
    struct chamber *cham;
};

static void mult(int dim, matrix *dst, matrix *a, matrix *b)
{
    int row, col, i;
    for (row = 0; row < dim; row++) {
        for (col = 0; col < dim; col++) {
            int sum = 0;
            for (i = 0; i < dim; i++) {
                sum += MAT(dim,a,row,i) * MAT(dim,b,i,col);
            }
            MAT(dim,a,row,col) = sum;
        }
    }
}

static bool compatible(struct car *car, struct fuel *fuel)
{
    int cham;
    for (cham = 0; cham < car->ncham; cham++) {
        // TODO
    }
    return false;
}

int main(int argc, char **argv)
{
    return 0;
}
