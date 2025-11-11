import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

class Barang {
    private String nama;
    private int jumlah;
    private int harga;

    public Barang(String nama, int jumlah, int harga) {
        this.nama = nama;
        this.jumlah = jumlah;
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public int getJumlah() {
        return jumlah;
    }

    public int getHarga() {
        return harga;
    }

    public int getSubtotal() {
        return jumlah * harga;
    }
}

class Transaksi {
    private String staff;
    private int totalBarang;
    private int uangDibayar;
    private Barang[] daftarBarang;

    public Transaksi(String staff, int totalBarang, int uangDibayar) {
        this.staff = staff;
        this.totalBarang = totalBarang;
        this.uangDibayar = uangDibayar;
        this.daftarBarang = new Barang[totalBarang];
    }

    public void inputBarang(Scanner input) {
        for (int i = 0; i < totalBarang; i++) {
            System.out.println("\nBarang ke-" + (i + 1));
            System.out.print("Nama barang        : ");
            String nama = input.nextLine();

            System.out.print("Jumlah pembelian   : ");
            int jumlah = input.nextInt();

            System.out.print("Harga satuan       : ");
            int harga = input.nextInt();
            input.nextLine();

            daftarBarang[i] = new Barang(nama, jumlah, harga);
        }
    }

    private int hitungTotal() {
        int total = 0;
        for (Barang b : daftarBarang) {
            total += b.getSubtotal();
        }
        return total;
    }

    public void cetakStruk() {
        int total = hitungTotal();
        int kembali = uangDibayar - total;

        System.out.println("            MUHAMMAD ABYAN             ");
        System.out.println("           255150400111019             ");
        System.out.println("---------------------------------------");

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy               HH.mm", new Locale("id", "ID"));
        String waktu = now.format(formatter);
        System.out.printf("%15s\n", waktu);

        Random ran = new Random();
        int ren1 = 100000 + ran.nextInt(900000);
        int ren2 = 100 + ran.nextInt(900);

        System.out.printf("%-18s:%18s\n", "Receipt Number ", ren1);
        System.out.printf("%-18s:%15s%3s\n", "Order ID", "FM", ren2);
        System.out.printf("%-18s:%18s\n", "Collected by : ", staff);
        System.out.println("---------------------------------------");

        for (Barang b : daftarBarang) {
            System.out.printf("%-15s %2dx @Rp%-6d Rp.%2d\n",
                    b.getNama(), b.getJumlah(), b.getHarga(), b.getSubtotal());
        }

        System.out.println("---------------------------------------");
        System.out.printf("%-20s %14s\n", "Total", "Rp. " + total);
        System.out.printf("%-20s %14s\n", "Bayar", "Rp. " + uangDibayar);
        System.out.printf("%-20s %14s\n", "Kembali", "Rp. " + kembali);
        System.out.println("---------------------------------------");
        System.out.println("   Terima Kasih Telah Berbelanja di    ");
        System.out.printf("%25s\n", "FILKOM-MART");
        System.out.println("=======================================");
    }
}

public class filkommart {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan nama staff : ");
        String staff = input.nextLine();

        System.out.print("Masukkan jumlah barang: ");
        int totalBarang = input.nextInt();

        System.out.print("Berapa jumlah uang kamu : ");
        int uang = input.nextInt();
        input.nextLine();

        Transaksi transaksi = new Transaksi(staff, totalBarang, uang);
        transaksi.inputBarang(input);
        transaksi.cetakStruk();

        input.close();
    }
}
