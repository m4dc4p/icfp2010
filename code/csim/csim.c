/* To run:
 * gcc -Wall -O2 -o csim csim.c && ./csim
 */

#include <time.h>

#include <stdio.h>
#include <stdlib.h>

#define MAX_LINE 1024
#define MAX_GATES 1024
#define STEPS 17

typedef int trit;

struct gate {
    trit *inl, *inr;
    trit outl, outr;
};

// Yes, all these variables are global... I know, I'll burn in hell for it.
static trit ext_in, *ext_out;

static struct gate gates[MAX_GATES];
static int ngates;

// This input is from the task description. Should generate the key when given
// to key circuit.
//static trit input[] = {0,2,2,2,2,2,2,0,2,1,0,1,1,0,0,1,1};

// Server gives this input
static trit input[STEPS] = {0,1,2,0,2,1,0,1,2,1,0,2,0,1,2,0,2};

static int cur_step;

static void step(void)
{
    int i;
    for (i = 0; i < ngates; i++) {
        trit inl = *gates[i].inl;
        trit inr = *gates[i].inr;
        gates[i].outl = (inl - inr + 3) % 3;
        gates[i].outr = (inl * inr + 2) % 3;
    }
}

#if 0
static void find0(char *name)
{
    for (cur_step = 0; ; cur_step++) {
        if (cur_step < STEPS) {
            ext_in = input[cur_step];
        } else {
            if (cur_step == 40) {
                printf("%s", name);
                fflush(stdout);
            }
            ext_in = rand() % 3;
        }
        step();
        if (*ext_out != 0) {
            if (cur_step >= 40) {
                printf("Up to %d zeros\n", cur_step);
            }
            break;
        }
    }
}
#endif

static void simulate(char *name)
{
    printf("%s", name);
    for (cur_step = 0; cur_step < STEPS; cur_step++) {
        ext_in = input[cur_step];
        step();
        putchar('0' + *ext_out);
    }
    putchar('\n');
}

// Parser buffer
static char buf[MAX_LINE];
static char *p;

static void skip_whitespace(void)
{
    while (*p == ' ')
        p++;
}

static int skip_delim(void)
{
    char c = *p++;
    if (c == ',') {
        return 1;
    } else if (c == ':') {
        return 0;
    } else {
        abort();
    }
}

static trit *parse_dst(void)
{
    if (*p == 'X') {
        p++;
        return &ext_in;
    } else {
        int x = 0;
        while (*p >= '0' && *p <= '9') {
            x = 10*x + (*p - '0');
            p++;
        }
        if (x >= MAX_GATES)
            abort();

        if (*p == 'L') {
            p++;
            return &gates[x].outl;
        } else if (*p == 'R') {
            p++;
            return &gates[x].outr;
        } else {
            abort();
        }
    }
}

static void parse_gate(int i)
{
    // e.g. 12R13R0#1R12R
    gates[i].inl = parse_dst();
    gates[i].inr = parse_dst();

    if (*p++ != '0' || *p++ != '#')
        abort();

    // ignore the outputs
    parse_dst();
    parse_dst();
}

int main(int argc, char *argv[])
{
    srand(time(0));

    while (fgets(buf, sizeof buf, stdin)) {
        int gate = 0;
        p = buf;

        parse_dst(); // skip this
        skip_delim();
        do {
            skip_whitespace();
            parse_gate(gate);
            gates[gate].outl = 0; // prepare for computation
            gates[gate].outr = 0; // prepare for computation
            gate++;
        } while (skip_delim());
        skip_whitespace();
        ext_out = parse_dst();

        ngates = gate;

        simulate(buf);
    }

    return 0;
}
