import java.util.Scanner;

public class BankSampah28 {
    static double[] inputSetoran(int n) {
        Scanner input = new Scanner(System.in);
        double[] setoran = new double[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Masukkan berat setoran nasabah ke-" + (i + 1) + " (kg): ");
            setoran[i] = input.nextDouble();
        }
        return setoran;
    }

    static void tampilSetoran(double[] setoran) {
        System.out.println("\nDaftar Setoran Sampah:");
        for (int i = 0; i < setoran.length; i++) {
            System.out.println("Nasabah ke-" + (i + 1) + ": " + setoran[i] + " kg");
        }
    }

    static double totalSetoran(double[] setoran) {
        double total = 0;
        for (double s : setoran) {
            total += s;
        }
        return total;
    }

    static double hitungSaldo(double berat, double hargaPerKg) {
        return berat * hargaPerKg;
    }

    static int nasabahTerbanyak(double[] setoran) {
        int maxIdx = 0;
        for (int i = 1; i < setoran.length; i++) {
            if (setoran[i] > setoran[maxIdx]) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan jumlah nasabah: ");
        int n = input.nextInt();

        double[] setoran = inputSetoran(n);
        tampilSetoran(setoran);

        System.out.println("\nTotal berat setoran: " + totalSetoran(setoran) + " kg");

        System.out.println("\nSaldo Nasabah:");
        for (int i = 0; i < setoran.length; i++) {
            System.out.println("Nasabah ke-" + (i + 1) + ": Rp" + hitungSaldo(setoran[i], 3000));
        }

        int indeksMax = nasabahTerbanyak(setoran);
        System.out.println("\nNasabah setoran terbanyak: Nasabah ke-" + (indeksMax + 1) + " (" + setoran[indeksMax] + " kg)");

        input.close();
    }
}