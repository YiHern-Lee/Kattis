#include<bits/stdc++.h>

using namespace std;

int main() {
    int nAirports, days, nFlights;
    cin >> nAirports >> days >> nFlights;
    vector<vector<vector<int>>> flights(days);
    vector<vector<int>> airports(nAirports, vector<int>(days, 0));
    for (int i = 0; i < nFlights; i++) {
        int u, v, d, z;
        cin >> u >> v >> d >> z;
        vector<int> flight = { u - 1, v - 1, z };
        flights[d - 1].push_back(flight);
    }

    for (int i = 0; i < nAirports * days; i++) {
        int u, day, c;
        cin >> u >> day >> c;
        airports[u - 1][day - 1] = c;
    }

    bool optimal = true;

    for (int i = 0; i < days; i++) {
        for (vector<int> flight : flights[i]) {
            airports[flight[0]][i] -= flight[2];
            if (airports[flight[0]][i] < 0) {
                optimal = false;
                goto end_loop;
            }
        }
        for (vector<int> flight : flights[i]) {
            airports[flight[1]][i] += flight[2];
        }
        if (i < days - 1) {
            for (int j = 0; j < nAirports; j++) {
                airports[j][i + 1] += airports[j][i];
            }
        }
    }
    end_loop:

    if (optimal) {
        cout << "optimal" << endl;
    } else {
        cout << "suboptimal" << endl;
    }

    return 0;
}