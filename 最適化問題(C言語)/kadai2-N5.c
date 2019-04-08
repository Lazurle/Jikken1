#include<stdio.h>
int main(void) {
    int i, j, k, l, m;
    int i1, j1, k1, l1, m1;
    int min;
    int sum = 0;
    int countWhile = 0;
    int min1, min2, min3, min4, min5, min6, min7, min8, min9, min10;
    int d[5][5] ={{0, 3, 8, 4, 2},
                   {3, 0, 5, 2, 1},
                   {8, 5, 0, 6, 3},
                   {4, 2, 6, 0, 2},
                   {2, 1, 3, 2, 0}};

    while (countWhile < 100000000) {
    i = 0;
    min = 100;
    for (j = 0; j < 5; j++) {
        if (i == j) {
            continue;
        }

        if (d[i][j] < min) {
            min = d[i][j];
            j1 = j;
        }
    }

    sum += min;

    min = 100;
    min1 = 100;
    min2 = 100;
    for (k = 0; k < 5; k++) {
        if (i == j1 || j1 == k || i == k) {
            continue;
        }

        if (d[i][k] < min) {
            min1 = d[i][k];
            k1 = k;
        }
        if (d[j1][k] < min) {
            min2 = d[j1][k];
            k1 = k;
        }

        if (min1 < min2) {
            sum += d[i][k];
        } else {
            sum += d[j1][k];
        }
    }

    //sum += d[k1][0];
    min = 100;
    min1 = 100;
    min2 = 100;
    for (l = 0; l < 5; l++) {
        if (i == j1 || j1 == k1 || k1 == l || i == k1 || j1 == l || i == l) {
            continue;
        }

        if (d[i][l] < min) {
            min1 = d[i][l];
            l1 = l;
        }
        if (d[j1][l] < min) {
            min2 = d[j1][l];
            l1 = l;
        }
        if (d[k1][l] < min) {
            min3 = d[k1][l];
            l1 = l;
        }

        min = min1;
        if (min2 < min) {
            min = min2;
        }
        if (min3 < min) {
            min = min3;
        }
    }

    sum += min;

    min = 100;
    min1 = 100;
    min2 = 100;
    for (m = 0; m < 5; m++) {
        if (i == j1 || j1 == k1 || k1 == l1 || l1 == m || i == k1 || j1 == l1 || k1 == m || i == l1 || j1 == m || i == m) {
            continue;
        }

        if (d[i][m] < min) {
            min1 = d[i][m];
            m1 = m;
        }
        if (d[j1][m] < min) {
            min2 = d[j1][m];
            m1 = m;
        }
        if (d[k1][m] < min) {
            min3 = d[k1][m];
            m1 = m;
        }

        if (d[l1][m] < min) {
            min4 = d[l1][m];
            m1 = m;
        }

        min = min1;
        if (min2 < min) {
            min = min2;
        }
        if (min3 < min) {
            min = min3;
        }

        if (min4 < min) {
            min = min4;
        }
    }

    sum += min;

    sum += d[m1][0];

    if (countWhile == 0) printf("%d\n", sum);
    countWhile++;
}

    return 0;
}
