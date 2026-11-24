import java.util.Scanner;

public class RekapLaundry28 {
    static void isiRekap(int[][] rekapKg, String[] jenisJasa, String[] namaHari, Scanner pembaca) {
        for (int baris = 0; baris < rekapKg.length; baris++) {
            System.out.println("Input berat cucian layanan " + jenisJasa[baris] + " (kg):");
            for (int kolom = 0; kolom < rekapKg[baris].length; kolom++) {
                System.out.print("  " + namaHari[kolom] + ": ");
                rekapKg[baris][kolom] = pembaca.nextInt();
            }
        }
    }

    static void cetakRekap(int[][] rekapKg, String[] jenisJasa, int[] tarifKg, String[] namaHari) {
        System.out.printf("\n%-15s %-10s", "Layanan", "Harga/kg");
        for (String satuHari : namaHari) {
            System.out.printf("%-8s", satuHari);
        }
        System.out.println();

        for (int baris = 0; baris < rekapKg.length; baris++) {
            System.out.printf("%-15s Rp%-8d", jenisJasa[baris], tarifKg[baris]);
            for (int kolom = 0; kolom < rekapKg[baris].length; kolom++) {
                System.out.printf("%-8d", rekapKg[baris][kolom]);
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

        String[] jenisJasa = {"Cuci Kering", "Cuci Setrika", "Setrika Saja", "Express"};
        int[] tarifKg = {6000, 8000, 5000, 12000};
        String[] namaHari = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"};
        int[][] rekapKg = new int[jenisJasa.length][namaHari.length];

        isiRekap(rekapKg, jenisJasa, namaHari, pembaca);
        cetakRekap(rekapKg, jenisJasa, tarifKg, namaHari);
        cariJasaTerlaris(rekapKg, jenisJasa);

        int urutanHariSibuk = hariTersibuk(rekapKg);
        System.out.println("Hari tersibuk: " + namaHari[urutanHariSibuk]);

        rincianOmzet(rekapKg, jenisJasa, tarifKg);

        pembaca.close();
    }
}