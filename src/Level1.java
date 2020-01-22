import java.util.*;

public class Level1 {
    public static int Unmanned(int L, int N, int[][] track) {
        if (N <= 0) return L;

        int distToTrafLights = track[0][0];
        int moment = track[0][0];
        boolean trafLights = false;
        int tempTimeOnTL;

        // расчет времени по всем светофорам
        for (int i = 0; i < N; i++) {
            if (i != 0) {
                moment += (track[i][0] - track[i - 1][0]);
                distToTrafLights += (track[i][0] - track[i - 1][0]);
            }
            // расчет времени на светофоре
            if (i == 0) {
                tempTimeOnTL = track[i][0];
                while (tempTimeOnTL >= 0) {
                    for (int j = 1; j < 3; j++) {
                        if (tempTimeOnTL < 0) break;
                        tempTimeOnTL -= track[i][j];
                    }
                }
                moment -= tempTimeOnTL;
            } else {
                tempTimeOnTL = 0;
                while (tempTimeOnTL < moment) {
                    for (int j = 1; j < 3; j++) {
                        tempTimeOnTL += track[i][j];
                        trafLights = j == 2;
                        if (tempTimeOnTL >= moment) break;
                    }
                }
                if (!trafLights) {
                    moment += (tempTimeOnTL - moment);
                }
            }
        }
        return moment + (L - distToTrafLights);
    }
}