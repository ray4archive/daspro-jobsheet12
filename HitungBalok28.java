import java.util.Scanner;

public class HitungBalok28 {
    static int hitungLuas(int pjg, int lb) {
        int Luas = pjg * lb;
        return Luas;
    }

    static int hitungVolume(int tinggi, int a, int b) {
        int volume = hitungLuas(a, b) * tinggi; // Diperbaiki: dikali (*), bukan ditambah (+)
        return volume;
    }

    static int hitungLuasPermukaanBalok(int p, int l, int t) {
        int luasPermukaan = 2 * (hitungLuas(p, l) + hitungLuas(p, t) + hitungLuas(l, t));
        return luasPermukaan;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int p, l, t, L, lp, vol;

        // Perulangan untuk 3 balok dengan ukuran berbeda
        for (int i = 1; i <= 3; i++) {
            System.out.println("=== Balok ke-" + i + " ===");
            System.out.println("Masukkan panjang");
            p = input.nextInt();

            System.out.println("Masukkan lebar");
            l = input.nextInt();

            System.out.println("Masukkan tinggi");
            t = input.nextInt();

            L = hitungLuas(p, l);
            System.out.println("Luas Persegi Panjang adalah " + L);

            lp = hitungLuasPermukaanBalok(p, l, t);
            vol = hitungVolume(t, p, l);

            System.out.println("Luas Permukaan Balok ke-" + i + " adalah " + lp);
            System.out.println("Volume Balok ke-" + i + " adalah " + vol + "\n");
        }

        input.close();
    }
}