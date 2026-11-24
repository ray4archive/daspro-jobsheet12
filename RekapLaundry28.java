import java.util.Scanner;

public class RekapLaundry28 {
    static void isiRekap(int[][] rekapKg, String[] jenisJasa, Scanner pembaca) {
        for (int baris = 0; baris < rekapKg.length; baris++) {
            System.out.println("Input berat cucian layanan " + jenisJasa[baris] + " (kg):");
            for (int kolom = 0; kolom < rekapKg[baris].length; kolom++) {
                System.out.print("  Hari ke-" + (kolom + 1) + ": ");
                rekapKg[baris][kolom] = pembaca.nextInt();
            }
        }
    }

    static void cetakRekap(int[][] rekapKg, String[] jenisJasa, int[] tarifKg) {
        System.out.printf("\n%-15s %-10s", "Layanan", "Harga/kg");
        for (int kolom = 0; kolom < rekapKg[0].length; kolom++) {
            System.out.printf("%-11s", "Hari ke-" + (kolom + 1));
        }
        System.out.println();

        for (int baris = 0; baris < rekapKg.length; baris++) {
            System.out.printf("%-15s Rp%-8d", jenisJasa[baris], tarifKg[baris]);
            for (int kolom = 0; kolom < rekapKg[baris].length; kolom++) {
                System.out.printf("%-11d", rekapKg[baris][kolom]);
            }
            System.out.println();
        }
    }

    static int totalPerLayanan(int[][] data, int idxLayanan) {
        int akumulasiKg = 0;
        for (int kolom = 0; kolom < data[idxLayanan].length; kolom++) {
            akumulasiKg += data[idxLayanan][kolom];
        }
        return akumulasiKg;
    }

    static void cariJasaTerlaris(int[][] rekapKg, String[] jenisJasa) {
        int posisiTerlaris = 0;
        int rekorKg = totalPerLayanan(rekapKg, 0);

        for (int baris = 1; baris < rekapKg.length; baris++) {
            int capaianKg = totalPerLayanan(rekapKg, baris);
            if (capaianKg > rekorKg) {
                rekorKg = capaianKg;
                posisiTerlaris = baris;
            }
        }
        System.out.println("\nLayanan dengan berat tertinggi: " + jenisJasa[posisiTerlaris] + " (" + rekorKg + " kg)");
    }

    static int hariTersibuk(int[][] data) {
        int posisiHariSibuk = 0;
        int rekorHarian = 0;

        for (int kolom = 0; kolom < data[0].length; kolom++) {
            int bebanHarian = 0;
            for (int baris = 0; baris < data.length; baris++) {
                bebanHarian += data[baris][kolom];
            }
            if (kolom == 0 || bebanHarian > rekorHarian) {
                rekorHarian = bebanHarian;
                posisiHariSibuk = kolom;
            }
        }
        return posisiHariSibuk;
    }

    static void rincianOmzet(int[][] rekapKg, String[] jenisJasa, int[] tarifKg) {
        int omzetKeseluruhan = 0;
        System.out.println("\nPendapatan Setiap Layanan:");
        for (int baris = 0; baris < rekapKg.length; baris++) {
            int omzetPerJasa = totalPerLayanan(rekapKg, baris) * tarifKg[baris];
            omzetKeseluruhan += omzetPerJasa;
            System.out.println("- " + jenisJasa[baris] + ": Rp" + omzetPerJasa);
        }
        System.out.println("Total Pendapatan Keseluruhan: Rp" + omzetKeseluruhan);
    }

    public static void main(String[] args) {
        Scanner pembaca = new Scanner(System.in);

        System.out.print("Masukkan jumlah layanan: ");
        int banyakJasa = pembaca.nextInt();
        System.out.print("Masukkan jumlah hari: ");
        int banyakHari = pembaca.nextInt();
        pembaca.nextLine();

        String[] jenisJasa = new String[banyakJasa];
        int[] tarifKg = new int[banyakJasa];
        int[][] rekapKg = new int[banyakJasa][banyakHari];

        for (int baris = 0; baris < banyakJasa; baris++) {
            System.out.print("Nama layanan ke-" + (baris + 1) + ": ");
            jenisJasa[baris] = pembaca.nextLine();
            System.out.print("Harga per kg " + jenisJasa[baris] + ": ");
            tarifKg[baris] = pembaca.nextInt();
            pembaca.nextLine();
        }

        isiRekap(rekapKg, jenisJasa, pembaca);
        cetakRekap(rekapKg, jenisJasa, tarifKg);
        cariJasaTerlaris(rekapKg, jenisJasa);

        int urutanHariSibuk = hariTersibuk(rekapKg);
        System.out.println("Hari tersibuk: Hari ke-" + (urutanHariSibuk + 1));

        rincianOmzet(rekapKg, jenisJasa, tarifKg);

        pembaca.close();
    }
}