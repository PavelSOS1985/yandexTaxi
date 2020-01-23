import java.util.*;

public class Level1 {
    public static int Unmanned(int L, int N, int[][] track) {
        if (N <= 0 || track[0][0] >= L) return L;
        int distToTrafLights = track[0][0];
        int moment = track[0][0];
        boolean trafLights;
        int tempTimeOnTL;
        // расчет времени по всем светофорам
        for (int i = 0; i < N; i++) {
            if (i != 0) {
                if (track[i][0] < L) {
                    moment += (track[i][0] - track[i - 1][0]);
                    distToTrafLights += (track[i][0] - track[i - 1][0]);
                } else {
                    break;
                }
                if (track[i][0] < track[i - 1][0]) break;
            }
            trafLights = false;
            tempTimeOnTL = 0;
            while (tempTimeOnTL <= moment) {
                for (int j = 1; j < 3; j++) {
                    tempTimeOnTL += track[i][j];
                    if (tempTimeOnTL > moment) break;
                    trafLights = !trafLights;
                }
            }
            if (!trafLights) {
                moment += (tempTimeOnTL - moment);
            }
        }
        return moment + (L - distToTrafLights);
    }
}