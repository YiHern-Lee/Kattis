#include <bits/stdc++.h>

using namespace std;

bool Compare(pair<int,int> x, pair<int,int> y) {
    return x.first < y.first;
}

int main() {
    int N = 0, B = 0;
    while (true) {
        cin >> N >> B;
        if (N < 0 && B < 0) break;
        vector<int> populations(N, 0);
        vector<int> ballot_boxes_allocated(N, 1);
        priority_queue<pair<int,int>, vector<pair<int,int>>, function<bool(pair<int,int>, pair<int,int>)>> pq(Compare);
        int total = 0;
        for (int i = 0; i < N; i++) {
            int pop;
            cin >> pop;
            populations[i] = pop;
            total += pop;
            pq.push(pair<int,int> { pop, i });
        }

        B -= N;
        
        while (B > 0) {
            pair<int,int> max = pq.top();
            pq.pop();
            ballot_boxes_allocated[max.second]++;
            pq.push(pair<int,int> { (populations[max.second] + ballot_boxes_allocated[max.second] - 1) / ballot_boxes_allocated[max.second], max.second });
            B--;
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            int people_per_box = (populations[i] + ballot_boxes_allocated[i] - 1) / ballot_boxes_allocated[i];
            if (people_per_box > max) {
                max = people_per_box;
            }
        }
        cout << max << endl;
    }
       
    return 0;
}